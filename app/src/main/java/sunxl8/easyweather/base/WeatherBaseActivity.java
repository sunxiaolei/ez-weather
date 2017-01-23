package sunxl8.easyweather.base;

import android.app.AlertDialog;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import sunxl8.android_lib.base.BaseActivity;
import sunxl8.easyweather.R;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class WeatherBaseActivity extends BaseActivity {

    private AlertDialog loadingDialog;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @Nullable
    @BindView(R.id.tv_toolbar_title)
    protected TextView toolbarTitle;
    @Nullable
    @BindView(R.id.iv_toolbar_icon)
    protected ImageView toolbarPlus;

    @Override
    protected void init() {
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new SpotsDialog(this, R.style.LoadingDialog);
        }
        loadingDialog.show();
    }


    public void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

}