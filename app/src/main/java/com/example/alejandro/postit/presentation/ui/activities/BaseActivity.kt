package com.example.alejandro.postit.presentation.ui.activities


import com.example.alejandro.postit.utils.LoggerUtils
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alejandro.postit.R
import com.example.alejandro.postit.presentation.presenter.BaseView


abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected var TAG = ""

    protected var layout = 0

    protected var progressDialog: ProgressDialog? = null


    /////////////////////////////////////////////
    //// LIFE CYCLE
    /////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.progressDialog = ProgressDialog(this)

        this.setContentView(this.layout)

        this.initializePresenters()

        LoggerUtils.logMessage(TAG, "onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()

        LoggerUtils.logMessage(TAG, "onDestroy()")
    }

    override fun onStart() {
        super.onStart()

        this.configViews()

        this.loadData()

        LoggerUtils.logMessage(TAG, "onStart()")
    }

    override fun onStop() {
        super.onStop()

        this.resetData()

        LoggerUtils.logMessage(TAG, "onStop()")
    }

    override fun onResume() {
        super.onResume()
        LoggerUtils.logMessage(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        LoggerUtils.logMessage(TAG, "onPause()")
    }

    /////////////////////////////////////////////
    //// HELPERS
    /////////////////////////////////////////////

    /**
     * Override in children to initialize presenters.
     */
    protected open fun initializePresenters() {}

    /**
     * Override in children to load necessary data.
     */
    protected open fun loadData() {

    }

    /**
     * Override in children to reset necessary data.
     */
    protected open fun resetData() {

    }

    /**
     * Override in children to customize views (colors, backgrounds, etc)
     */
    protected open fun configViews() {}

    override fun showProgress() {
        if (!this.progressDialog!!.isShowing) {
            this.progressDialog = ProgressDialog.show(
                this, "",
                resources.getString(R.string.loading), true
            )
        }
    }

    override fun hideProgress() {
        if (this.progressDialog!!.isShowing) {
            this.progressDialog!!.dismiss()
        }
    }

}
