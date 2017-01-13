package sunxl8.easyweather.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;

import butterknife.ButterKnife;
import sunxl8.android_lib.base.BaseActivity;
import sunxl8.easyweather.R;

/**
 * Created by sunxl8 on 2017/1/12.
 */

public class MainFragment extends BottomSheetFragment {

    protected BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (BaseActivity) getActivity();
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
