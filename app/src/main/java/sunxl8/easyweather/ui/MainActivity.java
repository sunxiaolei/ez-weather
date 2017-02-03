package sunxl8.easyweather.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding.view.RxView;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.easyweather.R;
import sunxl8.easyweather.base.WeatherBaseActivity;
import sunxl8.easyweather.db.CityEntity;
import sunxl8.easyweather.db.DBManager;
import sunxl8.easyweather.entity.SearchResponseEntity;
import sunxl8.easyweather.event.AddCityEvent;
import sunxl8.easyweather.network.WeatherRequest;

/**
 * Created by sunxl8 on 2017/1/12.
 */

public class MainActivity extends WeatherBaseActivity {

    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.iv_plus)
    ImageView mIvPlus;
    @BindView(R.id.indicator_main)
    CircleIndicator mIndicator;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;

    private List<String> mListTitles;
    private List<String> mListCodes;
    private List<Fragment> mListFragment;
    private MyCityAdapter mAdapter;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mListTitles = new ArrayList<>();
        mListCodes = new ArrayList<>();
        RxView.clicks(mIvPlus)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    //添加城市
                    startActivity(new Intent(this, CitySelectActivity.class));
                });
        RxView.clicks(mTvCity)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    Snackbar.with(getApplicationContext())
                            .text("删除这个城市")
                            .actionLabel("确定")
                            .actionListener(snackbar -> {
                                deleteCity();
                            })
                            .show(this);
                });
        mViewPager.setOffscreenPageLimit(3);
        RxBus.getInstance().onEvent(AddCityEvent.class)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    CityEntity city = new CityEntity();
                    city.setCity(event.getCityName());
                    city.setCode(event.getCityCode());
                    if (city.saveIfNotExist("code = ?", event.getCityCode())) {
                        addCity(event.getCityName(), event.getCityCode());
                    }
                });
    }

    @Override
    protected void initData() {
        getLocalCities();
        RxViewPager.pageSelections(mViewPager)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(integer -> {
                    if (mListTitles != null && mListTitles.size() > 0) {
                        mTvCity.setText(mListTitles.get(integer));
                    }
                });
        mListFragment = new ArrayList<>();
        for (int i = 0; i < mListTitles.size(); i++) {
            mListFragment.add(WeatherFragment.newInstance(mListCodes.get(i)));
        }
        mAdapter = new MyCityAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    private void addCity(String city, String code) {
        mListTitles.add(city);
        mTvCity.setText(city);
        mListFragment.add(WeatherFragment.newInstance(code));
        mAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(mListFragment.size());
        mIndicator.setViewPager(mViewPager);
    }

    private void deleteCity() {
        int loc = mListTitles.indexOf(mTvCity.getText().toString());
        String cityId = mListCodes.get(loc);
        DBManager.deleteCityById(cityId);
        mListTitles.clear();
        mListCodes.clear();
        mListFragment.clear();
        initData();
    }

    private void getLocalCities() {
        List<CityEntity> listCities = DBManager.queryCity();
        if (listCities != null && listCities.size() > 0) {
            for (CityEntity entity : listCities) {
                mListTitles.add(entity.getCity());
                mListCodes.add(entity.getCode());
            }
        } else {
            showLoading();
            //定位
            getLocation();
        }
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    amapLocation.getFloor();//获取当前室内定位的楼层
                    amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    searchCity(amapLocation.getCity());
                } else {
                    showToast("定位失败，ErrCode:" + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private void getLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        /**
         * 高精度定位模式：会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息。
         * 低功耗定位模式：不会使用GPS和其他传感器，只会使用网络定位（Wi-Fi和基站定位）。
         * 仅用设备定位模式：不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位，自 v2.9.0 版本支持返回地址描述信息。
         */
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

    private void searchCity(String keywords) {
        WeatherRequest.doSearchCity(keywords)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(entity -> {
                    dismissLoading();
                    if (entity.getHeWeather5().get(0).getStatus().equals("ok")) {
                        SearchResponseEntity.HeWeather5Bean.BasicBean bean = entity.getHeWeather5().get(0).getBasic();
                        addCity(bean.getCity(), bean.getId());
                    } else {
                        showToast("定位失败");
                    }
                });
    }

    class MyCityAdapter extends FragmentPagerAdapter {

        public MyCityAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mListTitles.get(position);
        }
    }
}
