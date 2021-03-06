package sunxl8.easyweather.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.security.cert.CertSelector;
import java.util.List;

import sunxl8.android_lib.utils.AndroidUtils;
import sunxl8.easyweather.R;

/**
 * Created by sunxl8 on 2017/1/22.
 */

public class WeatherItem extends LinearLayout {

    private Context mContext;
    private TextView tvTitle;
    private TextView tvSpec;
    private ListView lvItem;
    private ArrayAdapter mAdapter;

    public WeatherItem(Context context) {
        super(context);
        mContext = context;
    }

    public WeatherItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_weather_item, this, true);
        tvTitle = (TextView) findViewById(R.id.tv_weather_item_title);
        tvSpec = (TextView) findViewById(R.id.tv_weather_spec);
        lvItem = (ListView) findViewById(R.id.lv_weather_item);
    }

    public void setItemTitle(String title) {
        tvTitle.setText(title);
    }

    public void setItemSpec(String spec) {
        //优，良，轻度污染，中度污染，重度污染，严重污染
        switch (spec) {
            case "优":
                tvSpec.setTextColor(Color.parseColor("#4CAF50"));
                tvSpec.setText(spec);
                break;
            case "良":
                tvSpec.setTextColor(Color.parseColor("#00BCD4"));
                tvSpec.setText(spec);
                break;
            case "轻度污染":
                tvSpec.setTextColor(Color.parseColor("#FF7043"));
                tvSpec.setText("轻度");
                break;
            case "中度污染":
                tvSpec.setTextColor(Color.parseColor("#E91E63"));
                tvSpec.setText("中度");
                break;
            case "重度污染":
                tvSpec.setTextColor(Color.parseColor("#6D4C41"));
                tvSpec.setText("重度");
                break;
            case "严重污染":
                tvSpec.setTextColor(Color.parseColor("#212121"));
                tvSpec.setText("爆表");
                break;
            default:
                tvSpec.setText(spec);
                break;
        }
        Typeface tfC = Typeface.createFromAsset(mContext.getAssets(), "fonts/byxs.ttf");
        tvSpec.setTypeface(tfC);
        tvSpec.setVisibility(View.VISIBLE);

    }

    public void setItemData(List<String> data) {
        mAdapter = new ArrayAdapter(mContext, R.layout.item_weather, data);
        lvItem.setAdapter(mAdapter);
        AndroidUtils.setListViewHeightBasedOnChildren(lvItem);
    }
}
