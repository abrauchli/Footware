package org.footware.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TrackPointDTO implements IsSerializable {
	
	private double latitude;
	private double longitude;
	
	public TrackPointDTO() {
	}
	
	public TrackPointDTO(double longitude, double latitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
