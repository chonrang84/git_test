package com.tsi84.testkotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.tsi84.testkotlin.model.ConstVariables

class SecondActivity : BaseActivity(), View.OnClickListener {
    private var mImageSample: ImageView? = null
    private var mBtnStart: Button? = null
    private var mBtnClose: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        mBtnStart = findViewById(R.id.btn_start)
        mBtnStart?.setOnClickListener(this)
        mBtnClose = findViewById(R.id.btn_close)
        mBtnClose?.setOnClickListener(this)
        mImageSample = findViewById(R.id.img_sample)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start -> {
                // normal
//                updateImage();
                // runnable
//                updateImageRunnable04();

                // AsyncTask
                val task = UpdateImageTask()
                task.execute(R.drawable.flo)
            }
            R.id.btn_close -> finish()
        }
    }

    private fun updateImage() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.flo)
        mImageSample!!.setImageBitmap(bitmap)
    }

    private fun updateImageRunnable01() {
        Thread(Runnable { // a potentially time consuming task
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.flo)
            mImageSample!!.post { mImageSample!!.setImageBitmap(bitmap) }
        }).start()
    }

    private fun updateImageRunnable02() {
        val handler = Handler()
        handler.post {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.flo)
            mImageSample!!.setImageBitmap(bitmap)
        }
    }

    private fun updateImageRunnable03() {
        runOnUiThread {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.flo)
            mImageSample!!.setImageBitmap(bitmap)
        }
    }

    private fun updateImageRunnable04() {
        showLoadingDialog()
        val handler = Handler()
        handler.postDelayed({
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.flo)
            mImageSample!!.setImageBitmap(bitmap)
            hideLoadingDialog()
        }, ConstVariables.VALUE_OF_MAX_LOADING.toLong())
    }

    private inner class UpdateImageTask : AsyncTask<Int?, String?, Bitmap?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingDialog()
        }

        override fun doInBackground(vararg p0: Int?): Bitmap? {
            val resourceId = p0.get(0)

            // progress UI
            publishProgress(resources.getString(R.string.message_image_loading))

            // background process
            val bitmap = resourceId?.let { BitmapFactory.decodeResource(resources, it) }

            // test sleep..
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return bitmap
        }

        override fun onProgressUpdate(vararg p0: String?) {
            super.onProgressUpdate(*p0)
            setLoadingDialogText(p0[0])
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)
            hideLoadingDialog()
            if (bitmap != null) {
                mImageSample!!.setImageBitmap(bitmap)
            }
        }
    }

    companion object {
        private val TAG = SecondActivity::class.java.simpleName
    }
}