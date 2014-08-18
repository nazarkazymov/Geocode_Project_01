package com.example.geocode_project_01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

/**
 * Broadcast Receiver that get geocode data and send it to new activity.
 * 
 * @author naz
 * 
 */
public class SendDataReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Parcelable geocodeData = intent
				.getParcelableExtra(WebRespService.EXTRA_KEY_SEND_DATA
				
		Intent i = new Intent(context, ResponseActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra(WebRespService.EXTRA_KEY_SEND_DATA, geocodeData);
		context.startActivity(i);
	}

}
