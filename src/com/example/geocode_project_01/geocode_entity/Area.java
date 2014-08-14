package com.example.geocode_project_01.geocode_entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Area implements Parcelable {

	@SerializedName("southwest")
	private Location southWest;

	@SerializedName("northeast")
	private Location northEast;

	public Area() {
	}

	public Area(Parcel in) {
		this.northEast = in.readParcelable(Location.class.getClassLoader());
		this.southWest = in.readParcelable(Location.class.getClassLoader());
	}

	public Location getSouthWest() {
		return southWest;
	}

	public void setSouthWest(Location southWest) {
		this.southWest = southWest;
	}

	public Location getNorthEast() {
		return northEast;
	}

	public void setNorthEast(Location northEast) {
		this.northEast = northEast;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(northEast, 0);
		dest.writeParcelable(southWest, 0);
	}

	public static final Parcelable.Creator<Area> CREATOR = new Parcelable.Creator<Area>() {
		public Area createFromParcel(Parcel in) {
			return new Area(in);
		}

		public Area[] newArray(int size) {
			return new Area[size];
		}
	};
}
