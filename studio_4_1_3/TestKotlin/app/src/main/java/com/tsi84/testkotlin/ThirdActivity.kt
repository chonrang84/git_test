package com.tsi84.testkotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.tsi84.testkotlin.model.ConstVariables
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws

class ThirdActivity : BaseActivity(), View.OnClickListener {
    private var mImageSample: ImageView? = null
    private var mBtnStart: Button? = null
    private var mBtnRepeat: Button? = null
    private var mBtnStop: Button? = null
    private var mBtnClose: Button? = null
    private var mRxCompDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        mBtnStart = findViewById(R.id.btn_start)
        mBtnStart?.setOnClickListener(this)
        mBtnRepeat = findViewById(R.id.btn_repeat)
        mBtnRepeat?.setOnClickListener(this)
        mBtnStop = findViewById(R.id.btn_stop)
        mBtnStop?.setOnClickListener(this)
        mBtnClose = findViewById(R.id.btn_close)
        mBtnClose?.setOnClickListener(this)
        mImageSample = findViewById(R.id.img_sample)

        //// RxAndroid disposable init
        if (mRxCompDisposable == null) {
            mRxCompDisposable = CompositeDisposable()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start -> rxLoadingImage()
            R.id.btn_repeat -> rxRepeatTest()
            R.id.btn_stop -> rxCompositeDisposeAll()
            R.id.btn_close -> finish()
        }
    }

    private inner class loadingBitmap(var resId: Int) : Callable<Bitmap> {
        @Throws(Exception::class)
        override fun call(): Bitmap {
            val bitmap = BitmapFactory.decodeResource(resources, resId)

            // test sleep..
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return bitmap
        }

    }

    //// RxAndroid loading image
    private fun rxLoadingImage() {
        val callableBitmap: Callable<Bitmap> = loadingBitmap(R.drawable.flo)
        mRxCompDisposable!!.add(Observable.fromCallable(callableBitmap)
                .doOnComplete { Log.i(TAG, "RxLoading -------------> doOnComplete()") }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Bitmap?>() {
                    override fun onStart() {
                        super.onStart()
                        Log.d(TAG, "RxLoading.onStart")
                        showLoadingDialog()
                    }

                    override fun onNext(bitmap: Bitmap) {
                        Log.d(TAG, "RxLoading.onNext")

                        mImageSample!!.setImageBitmap(bitmap)

                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "RxLoading.onError")
                    }

                    override fun onComplete() {
                        Log.d(TAG, "RxLoading.onComplete")
                        hideLoadingDialog()
                    }
                })
        )
    }

    //// RxAndroid Repeat Test
    private fun rxRepeatTest() {
        // repeat 3 seconds event show toast
        mRxCompDisposable!!.add(Observable.interval(0, ConstVariables.RX_TEST_REPEAT_MILLISECOND.toLong(), TimeUnit.MILLISECONDS)
                .doOnDispose { Log.i(TAG, "RxRepeat -------------> doOnDispose()") }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Long?>() {
                    override fun onNext(aLong: Long) {
                        Log.d(TAG, "RxRepeat.onNext: $aLong")
                        Toast.makeText(applicationContext, "TEST SHOW: $aLong", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "RxRepeat.onError: $e")
                    }

                    override fun onComplete() {
                        Log.d(TAG, "RxRepeat.onComplete")
                    }
                })
        )
    }

    private fun rxCompositeDisposeAll() {
        if (mRxCompDisposable != null) {
            Log.i(TAG, "RxCompositeDisposeAll()")
            mRxCompDisposable!!.clear() // must be apply for later add disposable
            //            mRxCompDisposable.dispose();  // final dispose (later not added disposable)
        }
    }

    companion object {
        private val TAG = ThirdActivity::class.java.simpleName
    }
}