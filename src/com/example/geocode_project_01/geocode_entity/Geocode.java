package com.example.geocode_project_01.geocode_entity;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Geocode implements Parcelable {

	private List<String> types = new ArrayList<>();

	@SerializedName("formatted_address")
	private String formattedAddress;

	@SerializedName("address_components")
	private List<AddressComponent> addressComponents = new ArrayList<>();

	private Geometry geometry;

	private boolean patrialMatch;

	public Geocode() {
	}
	
	public Geocode(Parcel in) {
		this.types = in.createStringArrayList();
		this.formattedAddress = in.readString();
		this.addressComponents = in.createTypedArrayList(AddressComponent.CREATOR);
		this.geometry = in.readParcelable(Geometry.class.getClassLoader());
		this.patrialMatch = (boolean) in.readValue(Boolean.class.getClassLoader());
	}
	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public List<AddressComponent> getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents(List<AddressComponent> addressComponents) {
		this.addressComponents = addressComponents;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public boolean isPatrialMatch() {
		return patrialMatch;
	}

	public void setPatrialMatch(boolean patrialMatch) {
		this.patrialMatch = patrialMatch;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringList(types);
		dest.writeString(formattedAddress);
		dest.writeTypedList(addressComponents);
		dest.writeParcelable(geometry, 0);
		dest.writeValue(Boolean.valueOf(patrialMatch));
	}

	 public static final Parcelable.Creator<Geocode> CREATOR = new Parcelable.Creator<Geocode>() {
	        public Geocode createFromParcel(Parcel in) {
	            return new Geocode(in); 
	        }

	        public Geocode[] newArray(int size) {
	            return new Geocode[size];
	        }
	    };
}
