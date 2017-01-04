package sunxl8.android_lib.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private ProgressDialog dialogLoading;
    private AlertDialog dialog;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
        init();
        initView();
        initData();
    }

    protected abstract int setContentViewId();

    protected abstract void init();

    protected abstract void initView();

    protected abstract void initData();

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLoading() {
        if (dialogLoading == null) {
            dialogLoading = new ProgressDialog(this);
        }
        dialogLoading.show();
    }

    protected void showDialog(String msg) {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", null)
                    .create();
        }
        dialog.setMessage(msg);
        dialog.show();
    }

    protected void dismissDialog() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
        }
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
