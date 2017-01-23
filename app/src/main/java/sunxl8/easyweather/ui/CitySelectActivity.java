package sunxl8.easyweather.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.easyweather.R;
import sunxl8.easyweather.base.WeatherBaseActivity;
import sunxl8.easyweather.entity.SearchResponseEntity;
import sunxl8.easyweather.event.AddCityEvent;
import sunxl8.easyweather.network.WeatherRequest;

/**
 * Created by sunxl8 on 2017/1/23.
 */

public class CitySelectActivity extends WeatherBaseActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    private SearchResponseEntity.HeWeather5Bean.BasicBean bean;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_selectcity;
    }

    @Override
    protected void initView() {
        RxTextView.afterTextChangeEvents(etSearch)
                .throttleLast(1000, TimeUnit.MILLISECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                        searchCity(etSearch.getText().toString());
                    }
                });
        RxView.clicks(tvSearch)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    RxBus.getInstance().post(new AddCityEvent(bean.getId(), bean.getCity()));
                    finish();
                });
    }

    private void searchCity(String keywords) {
        WeatherRequest.doSearchCity(keywords)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(entity -> {
                    if (entity.getHeWeather5().get(0).getStatus().equals("ok")) {
                        bean = entity.getHeWeather5().get(0).getBasic();
                        tvSearch.setVisibility(View.VISIBLE);
                        tvSearch.setText(bean.getCity());
                    } else {
                        tvSearch.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    protected void initData() {

    }
}
