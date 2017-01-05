package sunxl8.easyweather;

import android.graphics.Typeface;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import sunxl8.android_lib.utils.TimeUtils;
import sunxl8.easyweather.base.WeatherBaseActivity;
import sunxl8.easyweather.db.DBManager;
import sunxl8.easyweather.db.WeatherEntity;
import sunxl8.easyweather.entity.WeatherNowResponseEntity;
import sunxl8.easyweather.entity.WeatherResponseEntity;
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
//                .subscribe(new Subscriber<SearchResponseEntity>() {
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
//                    public void onNext(SearchResponseEntity entity) {
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
//            getWeatherNow();
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

    private void getWeatherNow() {
        WeatherRequest.doGetWeatherNow("上海")
                .subscribe(new Subscriber<WeatherNowResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        tvTest.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherNowResponseEntity entity) {
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
                + "风速（kmph）：" + entity.getSpd() + "\r\n"
                + "AQI：" + entity.getAqi() + "\r\n"
                + "CO：" + entity.getCo() + "\r\n"
                + "NO2：" + entity.getNo2() + "\r\n"
                + "O3：" + entity.getO3() + "\r\n"
                + "PM1010：" + entity.getPm10() + "\r\n"
                + "PM2.5：" + entity.getPm25() + "\r\n"
                + "空气质量：" + entity.getQlty() + "\r\n"
                + "-->：" + entity.getSo2() + "\r\n"
                + "舒适度指数：" + entity.getComfBrf() + "\r\n"
                + "-->：" + entity.getComfTxt() + "\r\n"
                + "洗车指数：" + entity.getCwBrf() + "\r\n"
                + "-->：" + entity.getCwTxt() + "\r\n"
                + "穿衣指数：" + entity.getDrsgBrf() + "\r\n"
                + "-->：" + entity.getDrsgTxt() + "\r\n"
                + "感冒指数：" + entity.getFluBrf() + "\r\n"
                + "-->：" + entity.getFluTxt() + "\r\n"
                + "运动指数：" + entity.getSportBrf() + "\r\n"
                + "-->：" + entity.getSportTxt() + "\r\n"
                + "旅游指数：" + entity.getTravBrf() + "\r\n"
                + "-->：" + entity.getTravTxt() + "\r\n"
                + "紫外线指数：" + entity.getUvBrf() + "\r\n"
                + "-->：" + entity.getUvTxt() + "\r\n";
        tvTest.setText(str);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Enoksen.ttf");
        tvTest.setTypeface(tf);
    }
}
