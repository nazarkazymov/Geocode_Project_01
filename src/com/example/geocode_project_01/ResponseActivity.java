package com.example.geocode_project_01;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.geocode_project_01.geocode_entity.AddressComponent;
import com.example.geocode_project_01.geocode_entity.Geocode;
import com.example.geocode_project_01.geocode_entity.GeocodeResponse;

/**
 * Activity that showing all geocode data by instance of <code>ListView</code>
 * 
 * 
 * @author naz
 *
 */
public class ResponseActivity extends Activity {

	private List<String> listItem;
	private String items[];
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		showGeocodeData();
	}

	/**
	 * Method gets geocode data and shows this data providing
	 * <code>ListView</code>
	 */
	private void showGeocodeData() {
		String numberOfResult = getResources().getString(R.string.count_results);
		String nameLongitude = getResources().getString(R.string.longitude);
		String nameLatitude = getResources().getString(R.string.latitude);
		
		int countOfResults = 0;
		Intent i = getIntent();
		GeocodeResponse geocodeResponse = (GeocodeResponse) i
				.getParcelableExtra(WebRespService.EXTRA_KEY_SEND_DATA);
		listItem = new ArrayList<>();
		for (Geocode info : geocodeResponse.getResults()) {
			listItem.add(numberOfResult + ++countOfResults + ":");
			List<AddressComponent> addressComponents = info
					.getAddressComponents();
			for (AddressComponent addressComponent : addressComponents) {

				listItem.add(addressComponent.getLongName());
			}

			listItem.add(info.getFormattedAddress());
			String latitude = String.valueOf(info.getGeometry().getLocation()
					.getLat());
			String longitude = String.valueOf(info.getGeometry().getLocation()
					.getLng());
			
			listItem.add(nameLatitude + latitude);
			listItem.add(nameLongitude + longitude);
			
			listItem.add("\n");
			
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				R.layout.list_item, listItem);
		listView = (ListView) findViewById(R.id.geo_list);
		listView.setAdapter(adapter);
	}
}
