package sunxl8.easyweather.ui;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.BottomSheetLayout.State;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import sunxl8.easyweather.R;
import sunxl8.easyweather.base.WeatherBaseActivity;

import static com.flipboard.bottomsheet.BottomSheetLayout.State.HIDDEN;

/**
 * Created by sunxl8 on 2017/1/12.
 */

public class MainActivity extends WeatherBaseActivity {

    @BindView(R.id.layout_main)
    BottomSheetLayout layoutMain;
    @BindView(R.id.layout_main_bottom)
    RelativeLayout layoutMainBottom;
    @BindView(R.id.btn_show)
    Button btnShow;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    float x, y;

    @Override
    protected void initView() {

        MainFragment fragment = new MainFragment();
        layoutMain.showWithSheetView(LayoutInflater.from(this).inflate(R.layout.fragment_main, layoutMain, false));
//        fragment.show(getSupportFragmentManager(),R.id.layout_main);
        RxView.clicks(btnShow)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    layoutMain.showWithSheetView(LayoutInflater.from(this).inflate(R.layout.fragment_main, layoutMain, false));
//                    fragment.show(getSupportFragmentManager(),R.id.layout_main);
                });


        layoutMain.addOnSheetStateChangeListener(new BottomSheetLayout.OnSheetStateChangeListener() {
            @Override
            public void onSheetStateChanged(State state) {
                switch (state){
                    case HIDDEN:
                        layoutMain.dismissSheet();
                        System.out.println("HIDDEN");
                        break;
                    case EXPANDED:
                        System.out.println("EXPANDED");
                        break;
                    case PEEKED:
                        System.out.println("PEEKED");
                        break;
                    case PREPARING:
                        System.out.println("PREPARING");
                        break;

                }
            }
        });


    }

    @Override
    protected void initData() {

    }
}
