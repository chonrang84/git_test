package com.tsi84.testapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tsi84.testapp.model.ConstVariables;

public class SecondActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = SecondActivity.class.getSimpleName();

    private ImageView mImageSample;
    private Button mBtnStart;
    private Button mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);
        mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
        mImageSample = findViewById(R.id.img_sample);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                updateImage();
//                updateImageRunnable04();
//                UpdateImageTask task = new UpdateImageTask();
//                task.execute(R.drawable.flo);
                break;

            case R.id.btn_close:
                finish();
                break;
        }
    }

    private void updateImage() {
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flo);
        mImageSample.setImageBitmap(bitmap);
    }

    private void updateImageRunnable01() {
        new Thread(new Runnable() {
            public void run() {
                // a potentially time consuming task
                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flo);
                mImageSample.post(new Runnable() {
                    public void run() {
                        mImageSample.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    private void updateImageRunnable02() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flo);
                mImageSample.setImageBitmap(bitmap);
            }
        });
    }

    private void updateImageRunnable03() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flo);
                mImageSample.setImageBitmap(bitmap);
            }
        });
    }

    private void updateImageRunnable04() {
        showLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flo);
                mImageSample.setImageBitmap(bitmap);
                hideLoadingDialog();
            }
        }, ConstVariables.VALUE_OF_MAX_LOADING);
    }

    private class UpdateImageTask extends AsyncTask<Integer, String, Bitmap> {

        public UpdateImageTask() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoadingDialog();
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            int resourceId = params[0];

            // progress UI
            publishProgress(getResources().getString(R.string.message_image_loading));

            // background process
            final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);

            // test sleep..
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            setLoadingDialogText(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            hideLoadingDialog();
            if (bitmap != null) {
                mImageSample.setImageBitmap(bitmap);
            }
        }
    }
}
