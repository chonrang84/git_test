package com.tsi84.testapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.tsi84.testapp.R;

public class LoadingDialog extends Dialog {

	private TextView mTextMessage;

	public LoadingDialog(Context context) {
		this(context, null);
	}

	public LoadingDialog(Context context, String message) {
		super(context);

		// set property
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// layout
		setContentView(R.layout.loading_dialog);
		mTextMessage = (TextView) findViewById(R.id.progress_text);

		if (message != null) {
			mTextMessage.setText(message);
		}
	}

	public void setTextMessage(String message) {
		if (mTextMessage != null) {
			mTextMessage.setText(message);
		}
	}
}