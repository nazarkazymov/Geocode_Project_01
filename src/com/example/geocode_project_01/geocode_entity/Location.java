package com.example.geocode_project_01.geocode_entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

	private double lat;
	private double lng;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}
	
	public Location(Parcel in) {
		this.lat = in.readDouble();
		this.lng = in.readDouble();
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeDouble(lat);
		dest.writeDouble(lng);
	}
	
	 public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
	        public Location createFromParcel(Parcel in) {
	            return new Location(in); 
	        }

	        public Location[] newArray(int size) {
	            return new Location[size];
	        }
	    };
}
