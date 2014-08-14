package com.example.geocode_project_01.geocode_entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AddressComponent implements Parcelable {

	@SerializedName("long_name")
	private String longName;

	@SerializedName("short_name")
	private String shortName;

	private List<String> types = new ArrayList<>();

	public AddressComponent(Parcel in) {
		this.longName = in.readString();
		this.shortName = in.readString();
		this.types = in.createStringArrayList();
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(longName);
		dest.writeString(shortName);
		dest.writeStringList(types);
	}

	 public static final Parcelable.Creator<AddressComponent> CREATOR = new Parcelable.Creator<AddressComponent>() {
	        public AddressComponent createFromParcel(Parcel in) {
	            return new AddressComponent(in); 
	        }

	        public AddressComponent[] newArray(int size) {
	            return new AddressComponent[size];
	        }
	    }; 
}
