package com.example.geocode_project_01;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Main Activity
 * 
 * @author naz
 *
 */
public class MainActivity extends Activity {
	private EditText editText;
	private TextView errorView;

	public WebRespReceiver respondReceiver;
	public SendDataReceiver sendDataReceiver;

	public String address;
	public static final String ACTION_GET_RESPONSE = "com.example.geocode_project_01.GET_RESPONSE";
	public static final String ACTION_SEND_DATA = "com.example.geocode_project_01.SEND_DATA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button sendRequestButton = (Button) findViewById(R.id.request_button);
		sendRequestButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				editText = (EditText) findViewById(R.id.editText1);
				address = editText.getText().toString();
				// Force check for not empty input field
				if(validateInput(address) == false) {
					errorView = (TextView) findViewById(R.id.error_view);
					errorView.setVisibility(View.VISIBLE);
					return;
				}
				Intent serviceIntent = new Intent(MainActivity.this,
						WebRespService.class);
				serviceIntent.putExtra("address", address);
				startService(serviceIntent);

			}
		});

		respondReceiver = new WebRespReceiver();
		sendDataReceiver = new SendDataReceiver();

		IntentFilter intentFilterResponds = new IntentFilter(
				MainActivity.ACTION_GET_RESPONSE);
		intentFilterResponds.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(respondReceiver, intentFilterResponds);

		IntentFilter intentFilterSendData = new IntentFilter(
				MainActivity.ACTION_SEND_DATA);
		intentFilterSendData.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(sendDataReceiver, intentFilterSendData);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(respondReceiver);
		unregisterReceiver(sendDataReceiver);
	}

	/**
	 * Validate input field, checking is it empty field or not.
	 * 
	 * @param address Content of input field
	 * @return true if input field is not empty, false otherwise
	 */
	private boolean validateInput(String address) {
		
		if (address.equals("")) {
			return false;
		}
		return true;
	}
}
