package com.example.geocode_project_01.geocode_entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Geometry implements Parcelable {

	private Location location;

	@SerializedName("location_type")
	private String locationType;

	private Area viewport;

	private Area bounds;

	public Geometry() {
		// TODO Auto-generated constructor stub
	}
	
	public Geometry(Parcel parcel) {
		this.location = parcel.readParcelable(Location.class.getClassLoader());
		this.locationType = parcel.readString();
		this.viewport = parcel.readParcelable(Area.class.getClassLoader());
		this.bounds = parcel.readParcelable(Area.class.getClassLoader());
		
		
	}
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Area getViewport() {
		return viewport;
	}

	public void setViewport(Area viewport) {
		this.viewport = viewport;
	}

	public Area getBounds() {
		return bounds;
	}

	public void setBounds(Area bounds) {
		this.bounds = bounds;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(location, 0);
		dest.writeString(locationType);
		dest.writeParcelable(viewport, 0);
		dest.writeParcelable(bounds, 0);
	}

	public static final Parcelable.Creator<Geometry> CREATOR = new Parcelable.Creator<Geometry>() {
	        public Geometry createFromParcel(Parcel in) {
	            return new Geometry(in); 
	        }

	        public Geometry[] newArray(int size) {
	            return new Geometry[size];
	        }
	    };
}
