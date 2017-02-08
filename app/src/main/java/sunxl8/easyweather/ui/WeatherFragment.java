package sunxl8.easyweather.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.android.FragmentEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import sunxl8.android_lib.network.SchedulersCompat;
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

public class WeatherFragment extends WeatherBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;
    @BindView(R.id.tv_weather_des)
    TextView tvWeatherDes;
    @BindView(R.id.tv_weather_updatetime)
    TextView tvUpdateTime;
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

    private WeatherEntity entity;

    private String cityId;

    public static WeatherFragment newInstance(String code) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void initView() {
        layoutRefresh.setOnRefreshListener(this);
        layoutRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        layoutRefresh.setProgressBackgroundColorSchemeColor(Color.parseColor("#55000000"));
    }

    private boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }
    }

    @Override
    protected void initData() {
        if (isVisible) {
            mActivity.showLoading();
        }
        cityId = getArguments().getString("code");
        List<WeatherEntity> list = DBManager.queryWeatherByDateAndCityCode(
                TimeUtils.milliseconds2String(System.currentTimeMillis(), new SimpleDateFormat("yyyy-MM-dd"))
                , cityId);
        if (list != null && list.size() > 0) {
            entity = list.get(0);
            show(entity);
        } else {
            getWeather(cityId, 0);
        }
    }

    private void getWeather(String code, int type) {
        WeatherRequest.doGetWeather(code)
                .subscribe(new Subscriber<WeatherResponseEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivity.dismissLoading();
                        mActivity.showToast(e.getMessage());
                        layoutRefresh.setRefreshing(false);
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherResponseEntity entity) {
                        layoutRefresh.setRefreshing(false);
                        save(entity.getHeWeather5().get(0), type);
                    }
                });
    }

    private void save(WeatherResponseEntity.HeWeather5Bean bean, int type) {
        WeatherEntity entity = new WeatherEntity();
        entity.setCity(bean.getBasic().getCity());
        entity.setCityId(bean.getBasic().getId());
        entity.setCnty(bean.getBasic().getCnty());
        entity.setLat(bean.getBasic().getLat());
        entity.setLon(bean.getBasic().getLon());
        entity.setProv(bean.getBasic().getProv());
        entity.setUpdateTime(bean.getBasic().getUpdate().getLoc());
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
        if (type == 0) {
            entity.save();
        } else {
            entity.update(DBManager.getEntityIdByCityId(cityId));
        }
        show(entity);
    }

    private void show(WeatherEntity entity) {
        mActivity.dismissLoading();
        tvWeatherDes.setText(entity.getTxt());
        tvWeatherTemp.setText(entity.getTmp() + "℃");
        tvUpdateTime.setText("更新时间：" + entity.getUpdateTime());

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
        listBrf.add("洗车指数：" + entity.getCwBrf());
        listBrf.add("穿衣指数：" + entity.getDrsgBrf());
        listBrf.add("感冒指数：" + entity.getFluBrf());
        listBrf.add("运动指数：" + entity.getSportBrf());
        listBrf.add("旅游指数：" + entity.getTravBrf());
        listBrf.add("紫外线指数：" + entity.getUvBrf());
        itemBrf.setItemTitle("舒适度指数：" + entity.getComfBrf());
        itemBrf.setItemData(listBrf);
    }

    @Override
    public void onRefresh() {
//        Observable.timer(2000, TimeUnit.MILLISECONDS)
//                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
//                .compose(SchedulersCompat.applyIoSchedulers())
//                .subscribe(aLong -> {
//                    layoutRefresh.setRefreshing(false);
//                });
        getWeather(cityId, 1);
    }
}
