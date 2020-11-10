package com.tsi84.testapp;

import android.app.Activity;
import android.view.Window;

import com.tsi84.testapp.dialog.LoadingDialog;

public class BaseActivity extends Activity {

    private LoadingDialog mLoadingDialog;

    @Override
    public void setContentView(int layoutResID) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(layoutResID);
    }

    protected void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this, getString(R.string.label_loading));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.show();
    }

    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        mLoadingDialog = null;
    }

}
