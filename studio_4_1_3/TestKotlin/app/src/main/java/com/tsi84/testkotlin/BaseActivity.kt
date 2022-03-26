package com.tsi84.testkotlin

import android.app.Activity
import android.view.Window
import com.tsi84.testkotlin.dialog.LoadingDialog

open class BaseActivity : Activity() {
    private var mLoadingDialog: LoadingDialog? = null
    override fun setContentView(layoutResID: Int) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.setContentView(layoutResID)
    }

    protected fun showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this, getString(R.string.label_loading))
            mLoadingDialog!!.setCancelable(false)
        }
        mLoadingDialog!!.show()
    }

    protected fun hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog!!.isShowing) {
            mLoadingDialog!!.dismiss()
        }
        mLoadingDialog = null
    }

    protected fun setLoadingDialogText(string: String?) {
        if (mLoadingDialog != null && mLoadingDialog!!.isShowing) {
            mLoadingDialog!!.setTextMessage(string)
        }
    }
}