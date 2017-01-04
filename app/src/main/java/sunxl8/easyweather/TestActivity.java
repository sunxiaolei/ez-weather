package sunxl8.easyweather;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;
import sunxl8.android_lib.utils.TimeUtils;
import sunxl8.easyweather.base.WeatherBaseActivity;
import sunxl8.easyweather.db.DBManager;
import sunxl8.easyweather.db.WeatherEntity;
import sunxl8.easyweather.entity.CityWeatherResponseEntity;
import sunxl8.easyweather.network.WeatherRequest;

public class TestActivity extends WeatherBaseActivity {

    private WeatherEntity entity;

    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

//        WeatherRequest.doSearchCity("上海")
//                .subscribe(new Subscriber<CitySearchResponseEntity>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        tvTest.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CitySearchResponseEntity entity) {
//                        tvTest.setText(entity.getHeWeather5().get(0).getStatus());
//                    }
//                });

        //get local
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
        WeatherRequest.doGetWeatherNow("上海")
                .subscribe(new Subscriber<CityWeatherResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        tvTest.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(CityWeatherResponseEntity entity) {
                        save(entity.getHeWeather5().get(0));
                    }
                });
    }

    private void save(CityWeatherResponseEntity.HeWeather5Bean bean) {
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
        entity.save();
        show(entity);
    }

    private void show(WeatherEntity entity) {
        String str = "天气状况：" + entity.getTxt() + "\r\n"
                + "城市名称：" + entity.getCity() + "\r\n"
                + "国家：" + entity.getCnty() + "\r\n"
                + "城市ID：" + entity.getCityId() + "\r\n"
                + "城市维度：" + entity.getLat() + "\r\n"
                + "城市经度：" + entity.getLon() + "\r\n"
                + "城市所属省份：" + entity.getProv() + "\r\n"
                + "日期：" + entity.getDate() + "\r\n"
                + "天气状况代码：" + entity.getCode() + "\r\n"
                + "体感温度：" + entity.getFl() + "\r\n"
                + "相对湿度（%）：" + entity.getHum() + "\r\n"
                + "降水量（mm）：" + entity.getPcpn() + "\r\n"
                + "气压：" + entity.getPres() + "\r\n"
                + "温度：" + entity.getTmp() + "\r\n"
                + "能见度（km）：" + entity.getVis() + "\r\n"
                + "风向（360度）：" + entity.getDeg() + "\r\n"
                + "风向：" + entity.getDir() + "\r\n"
                + "风力：" + entity.getSc() + "\r\n"
                + "风速（kmph）：" + entity.getSpd() + "\r\n";
        tvTest.setText(str);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Enoksen.ttf");
        tvTest.setTypeface(tf);
    }
}
