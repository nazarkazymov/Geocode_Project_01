package com.example.geocode_project_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.geocode_project_01.geocode_entity.GeocodeResponse;
import com.google.gson.Gson;

import android.app.IntentService;
import android.content.Intent;


/**
 * Subclass of <code>IntentService<code> that performs of fetching geocode data 
 * and sending <code>PArcelable</code> geocode data to particular broadcast receivers.
 * 
 * @author naz
 *
 */<code>
public class WebRespService extends IntentService {
	public String SERVICE_NAME = "WebRespService";
	public static final String SENSOR = "&sensor=false";
	public static final String EXTRA_KEY_SUCCESS_REQUEST = "success";
	public static final String EXTRA_KEY_SEND_DATA = "send_data";
	public static String url = "http://maps.googleapis.com/maps/api/geocode/json?address=";

	public GeocodeResponse geocodeResponse;

	public WebRespService() {
		super("WebRespService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		getGeocodeData(intent);
		
		Intent sendDataIntent = new Intent(MainActivity.ACTION_SEND_DATA);
		sendDataIntent.addCategory(Intent.CATEGORY_DEFAULT);
		sendDataIntent.putExtra(WebRespService.EXTRA_KEY_SEND_DATA,
				geocodeResponse);
		this.sendBroadcast(sendDataIntent);
	}

	/**
	 * Method provide fetching data from google maps api server 
	 * by http connecting
	 * 
	 * @param intent intent that going to <code>onHandleIntent()</code> method
	 */
	private void getGeocodeData(Intent intent) {
		String address = intent.getStringExtra("address");
		address = address.replaceAll("\\s+", "+");
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append(address);
		urlBuilder.append(SENSOR);
		String URL = urlBuilder.toString();
		try {
			Gson gson = new Gson();
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpRequest = new HttpGet(URL);
			HttpResponse response = httpClient.execute(httpRequest);
			HttpEntity responseEntity = response.getEntity();
			InputStream input = responseEntity.getContent();
			Reader reader = new InputStreamReader(input);
			geocodeResponse = gson.fromJson(reader, GeocodeResponse.class);
			Intent responseIntent = new Intent(MainActivity.ACTION_GET_RESPONSE);
			responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
			String successMessage = getResources().getString(
					R.string.success_loading_message);
			responseIntent.putExtra(WebRespService.EXTRA_KEY_SUCCESS_REQUEST,
					successMessage);
			this.sendBroadcast(responseIntent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
