package sunxl8.easyweather.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import rx.functions.Action1;
import sunxl8.easyweather.R;
import sunxl8.easyweather.base.WeatherBaseActivity;
import sunxl8.easyweather.db.CityEntity;
import sunxl8.easyweather.db.DBManager;

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
    private List<Fragment> mListFragment;
    private MyCityAdapter mAdapter;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mListTitles = new ArrayList<>();
        RxView.clicks(mIvPlus)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    //添加城市
                    CityEntity city = new CityEntity();
                    city.setCity("北京");
                    if (city.saveIfNotExist("city = ?", "北京")){
                        addCity("北京");
                    }
                });
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void initData() {
        getLocalCities();
        RxViewPager.pageSelections(mViewPager)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(integer -> {
                    mTvCity.setText(mListTitles.get(integer));
                });
        mListFragment = new ArrayList<>();
        for (int i = 0; i < mListTitles.size(); i++) {
            mListFragment.add(new WeatherFragment());
        }
        mAdapter = new MyCityAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    private void addCity(String city){
        mListTitles.add(city);
        mListFragment.add(new WeatherFragment());
        mAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(mListFragment.size());
        mIndicator.setViewPager(mViewPager);
    }

    private void getLocalCities() {
        List<CityEntity> listCities = DBManager.queryCity();
        if (listCities != null && listCities.size() > 0) {
            for (CityEntity entity : listCities) {
                mListTitles.add(entity.getCity());
            }
        } else {
            mListTitles.add("上海");
        }
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
