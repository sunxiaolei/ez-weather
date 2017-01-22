package sunxl8.easyweather.ui;

import android.app.AlertDialog;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import dmax.dialog.SpotsDialog;
import rx.Subscriber;
import sunxl8.android_lib.utils.TimeUtils;
import sunxl8.easyweather.R;
import sunxl8.easyweather.base.WeatherBaseFragment;
import sunxl8.easyweather.db.DBManager;
import sunxl8.easyweather.db.WeatherEntity;
import sunxl8.easyweather.entity.WeatherResponseEntity;
import sunxl8.easyweather.network.WeatherRequest;
import sunxl8.easyweather.widget.WeatherItem;

/**
 * Created by sunxl8 on 2017/1/22.
 */

public class WeatherFragment extends WeatherBaseFragment {

    @BindView(R.id.tv_weather_des)
    TextView tvWeatherDes;
    @BindView(R.id.tv_weather_temp)
    TextView tvWeatherTemp;
    @BindView(R.id.item_aqi)
    WeatherItem itemAqi;
    @BindView(R.id.item_detail)
    WeatherItem itemDetail;
    @BindView(R.id.item_wind)
    WeatherItem itemWind;
    @BindView(R.id.item_brf)
    WeatherItem itemBrf;

    private AlertDialog dialog;
    private WeatherEntity entity;

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        dialog = new SpotsDialog(mActivity, R.style.LoadingDialog);
        dialog.show();
        List<WeatherEntity> list = DBManager.queryWeatherByDate(
                TimeUtils.milliseconds2String(System.currentTimeMillis(), new SimpleDateFormat("yyyy-MM-dd")));
        if (list != null && list.size() > 0) {
            entity = list.get(0);
            show(entity);
        } else {
            getWeather();
        }
    }

    private void getWeather() {
        WeatherRequest.doGetWeather("上海")
                .subscribe(new Subscriber<WeatherResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherResponseEntity entity) {
                        save(entity.getHeWeather5().get(0));
                    }
                });
    }

    private void save(WeatherResponseEntity.HeWeather5Bean bean) {
        WeatherEntity entity = new WeatherEntity();
        entity.setCity(bean.getBasic().getCity());
        entity.setCityId(bean.getBasic().getId());
        entity.setCnty(bean.getBasic().getCnty());
        entity.setLat(bean.getBasic().getLat());
        entity.setLon(bean.getBasic().getLon());
        entity.setProv(bean.getBasic().getProv());
        entity.setDate(TimeUtils.date2String(TimeUtils.string2Date(bean.getBasic().getUpdate().getLoc(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm")), new SimpleDateFormat("yyyy-MM-dd")));
        entity.setCode(bean.getNow().getCond().getCode());
        entity.setTxt(bean.getNow().getCond().getTxt());
        entity.setFl(bean.getNow().getFl());
        entity.setHum(bean.getNow().getHum());
        entity.setPcpn(bean.getNow().getPcpn());
        entity.setPres(bean.getNow().getPres());
        entity.setTmp(bean.getNow().getTmp());
        entity.setVis(bean.getNow().getVis());
        entity.setDeg(bean.getNow().getWind().getDeg());
        entity.setDir(bean.getNow().getWind().getDir());
        entity.setSc(bean.getNow().getWind().getSc());
        entity.setSpd(bean.getNow().getWind().getSpd());

        entity.setAqi(bean.getAqi().getCity().getAqi());
        entity.setCo(bean.getAqi().getCity().getCo());
        entity.setNo2(bean.getAqi().getCity().getNo2());
        entity.setO3(bean.getAqi().getCity().getO3());
        entity.setPm10(bean.getAqi().getCity().getPm10());
        entity.setPm25(bean.getAqi().getCity().getPm25());
        entity.setQlty(bean.getAqi().getCity().getQlty());
        entity.setSo2(bean.getAqi().getCity().getSo2());

        entity.setComfBrf(bean.getSuggestion().getComf().getBrf());
        entity.setComfBrf(bean.getSuggestion().getComf().getTxt());
        entity.setCwBrf(bean.getSuggestion().getCw().getBrf());
        entity.setCwTxt(bean.getSuggestion().getCw().getTxt());
        entity.setDrsgBrf(bean.getSuggestion().getDrsg().getBrf());
        entity.setDrsgTxt(bean.getSuggestion().getDrsg().getTxt());
        entity.setFluBrf(bean.getSuggestion().getFlu().getBrf());
        entity.setFluTxt(bean.getSuggestion().getFlu().getTxt());
        entity.setSportBrf(bean.getSuggestion().getSport().getBrf());
        entity.setSportTxt(bean.getSuggestion().getSport().getTxt());
        entity.setTravBrf(bean.getSuggestion().getTrav().getBrf());
        entity.setTravTxt(bean.getSuggestion().getTrav().getTxt());
        entity.setUvBrf(bean.getSuggestion().getUv().getBrf());
        entity.setUvTxt(bean.getSuggestion().getUv().getTxt());
        entity.save();
        show(entity);
    }

    private void show(WeatherEntity entity) {
        dialog.dismiss();
        tvWeatherDes.setText(entity.getTxt());
        tvWeatherTemp.setText(entity.getTmp() + "℃");

        List<String> listAqi = new ArrayList<>();
        listAqi.add("AQI：" + entity.getAqi());
        listAqi.add("CO：" + entity.getCo());
        listAqi.add("NO2：" + entity.getNo2());
        listAqi.add("O3：" + entity.getO3());
        listAqi.add("PM1010：" + entity.getPm10());
        listAqi.add("PM2.5：" + entity.getPm25());
        listAqi.add("SO2：" + entity.getSo2());
        itemAqi.setItemTitle("空气质量");
        itemAqi.setItemSpec(entity.getQlty());
        itemAqi.setItemData(listAqi);

        List<String> listDetail = new ArrayList<>();
        listDetail.add("体感温度：" + entity.getFl());
        listDetail.add("相对湿度（%）：" + entity.getHum());
        listDetail.add("降水量（mm）：" + entity.getPcpn());
        listDetail.add("气压：" + entity.getPres());
        listDetail.add("能见度（km）：" + entity.getVis());
        itemDetail.setItemTitle("详情");
        itemDetail.setItemData(listDetail);

        List<String> listWind = new ArrayList<>();
        listWind.add("风向（360度）：" + entity.getDeg());
        listWind.add("风向：" + entity.getDir());
        listWind.add("风力：" + entity.getSc());
        listWind.add("风速（kmph）：" + entity.getSpd());
        itemWind.setItemTitle("风");
        itemWind.setItemData(listWind);

        List<String> listBrf = new ArrayList<>();
        listBrf.add("舒适度指数：" + entity.getComfBrf());
        listBrf.add("洗车指数：" + entity.getCwBrf());
        listBrf.add("穿衣指数：" + entity.getDrsgBrf());
        listBrf.add("感冒指数：" + entity.getFluBrf());
        listBrf.add("运动指数：" + entity.getSportBrf());
        listBrf.add("旅游指数：" + entity.getTravBrf());
        listBrf.add("紫外线指数：" + entity.getUvBrf());
        itemBrf.setItemTitle("生活指数");
        itemBrf.setItemData(listBrf);
    }
}
