package com.tsi84.testapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tsi84.testapp.model.ConstVariables;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ThirdActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ThirdActivity.class.getSimpleName();

    private ImageView mImageSample;
    private Button mBtnStart;
    private Button mBtnRepeat;
    private Button mBtnStop;
    private Button mBtnClose;

    private CompositeDisposable mRxCompDisposable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);

        mBtnRepeat = findViewById(R.id.btn_repeat);
        mBtnRepeat.setOnClickListener(this);

        mBtnStop = findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(this);

        mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);

        mImageSample = findViewById(R.id.img_sample);

        //// RxAndroid disposable init
        if (mRxCompDisposable == null) {
            mRxCompDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                rxLoadingImage();
                break;

            case R.id.btn_repeat:
                rxRepeatTest();
                break;

            case R.id.btn_stop:
                rxCompositeDisposeAll();
                break;

            case R.id.btn_close:
                finish();
                break;
        }
    }

    private class loadingBitmap implements Callable<Bitmap> {
        int resId;

        public loadingBitmap(int resourceId) {
            resId = resourceId;
        }

        @Override
        public Bitmap call() throws Exception {
            final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);

            // test sleep..
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }

    //// RxAndroid loading image
    private void rxLoadingImage() {
        Callable<Bitmap> callableBitmap = new loadingBitmap(R.drawable.flo);
        mRxCompDisposable.add(Observable.fromCallable(callableBitmap)
                .doOnComplete(() -> Log.i(TAG, "RxLoading -------------> doOnComplete()"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Bitmap>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        Log.d(TAG, "RxLoading.onStart");
                        showLoadingDialog();
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.d(TAG, "RxLoading.onNext");
                        if (bitmap != null) {
                            mImageSample.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "RxLoading.onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "RxLoading.onComplete");
                        hideLoadingDialog();
                    }
                })
        );
    }

    //// RxAndroid Repeat Test
    private void rxRepeatTest() {
        // repeat 3 seconds event show toast
        mRxCompDisposable.add(Observable.interval(0, ConstVariables.RX_TEST_REPEAT_MILLISECOND, TimeUnit.MILLISECONDS)
                .doOnDispose(() -> Log.i(TAG, "RxRepeat -------------> doOnDispose()"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "RxRepeat.onNext: " + aLong);
                        Toast.makeText(getApplicationContext(), "TEST SHOW: " + aLong, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "RxRepeat.onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "RxRepeat.onComplete");
                    }
                })
        );
    }


    private void rxCompositeDisposeAll() {
        if (mRxCompDisposable != null) {
            Log.i(TAG, "RxCompositeDisposeAll()");
            mRxCompDisposable.clear();      // must be apply for later add disposable
//            mRxCompDisposable.dispose();  // final dispose (later not added disposable)
        }
    }
}
