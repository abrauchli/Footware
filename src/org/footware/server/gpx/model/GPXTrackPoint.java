package org.footware.server.gpx.model;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;

public class GPXTrackPoint {

	BigDecimal longitude;
	BigDecimal latitude;
	BigDecimal elevation;
	DateTime time;
	
	public GPXTrackPoint(BigDecimal latitude, BigDecimal longitude, BigDecimal elevation, DateTime time) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.time = time;
	}

	/**
	 * @return the longitude
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the elevation
	 */
	public BigDecimal getElevation() {
		return elevation;
	}

	/**
	 * @param elevation the elevation to set
	 */
	public void setElevation(BigDecimal elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return the time
	 */
	public DateTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(DateTime time) {
		this.time = time;
	}
	
	
	
}
