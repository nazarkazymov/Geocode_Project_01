package com.example.geocode_project_01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Broadcast Receiver that get message of request finishing and show toast
 * message.
 * 
 * @author naz
 * 
 */
public class WebRespReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("First receiver");
		String message = intent
				.getStringExtra(WebRespService.EXTRA_KEY_SUCCESS_REQUEST);

		Toast.makeText(context, message, Toast.LENGTH_LONG).show();

	}

}
