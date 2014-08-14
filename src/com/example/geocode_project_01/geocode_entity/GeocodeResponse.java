package com.example.geocode_project_01.geocode_entity;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class GeocodeResponse implements Parcelable {

	private String status;

	private List<Geocode> results = new ArrayList<>();

	public GeocodeResponse() {
	}
	
	public GeocodeResponse(Parcel in) {
		this.status = in.readString();
		this.results = in.createTypedArrayList(Geocode.CREATOR);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Geocode> getResults() {
		return results;
	}

	public void setResults(List<Geocode> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		
		return "GeocodeResponse [status=" + status + ", results=" + results
				+ "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(status);
		dest.writeTypedList(results);
	}
	
	public static final Parcelable.Creator<GeocodeResponse> CREATOR = new Creator<GeocodeResponse>() {
		@Override
		public GeocodeResponse createFromParcel(Parcel source) {
			return new GeocodeResponse(source);
		}
		
		@Override
		public GeocodeResponse[] newArray(int size) {
			return new GeocodeResponse[size];
		}
		
		
	};
	
}
