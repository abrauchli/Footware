package org.footware.server.gpx.model;

import java.math.BigDecimal;
import org.joda.time.DateTime;

public class GPXTrackPoint {

	BigDecimal longitude;
	BigDecimal latitude;
	BigDecimal elevation;
	DateTime time;
	double speed = 0.0;

	public GPXTrackPoint(BigDecimal latitude, BigDecimal longitude,
			BigDecimal elevation, DateTime time) {
		testLongitudeValue(longitude);
		testLatitudeValue(latitude);
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
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(BigDecimal longitude) {
		testLongitudeValue(longitude);
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(BigDecimal latitude) {
		testLatitudeValue(latitude);
		this.latitude = latitude;
	}

	/**
	 * @return the elevation
	 */
	public BigDecimal getElevation() {
		return elevation;
	}

	/**
	 * @param elevation
	 *            the elevation to set
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
	 * @param time
	 *            the time to set
	 */
	public void setTime(DateTime time) {
		this.time = time;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	private void testLatitudeValue(BigDecimal latitude) {
		if (latitude.doubleValue() < -90.0 && latitude.doubleValue() > 90.0) {
			throw new IllegalArgumentException("Latitude value "
					+ latitude.doubleValue()
					+ " not in range -90.0 <= x <= 90.0");
		}
	}

	private void testLongitudeValue(BigDecimal longitude) {
		if (longitude.doubleValue() < -180.0 && longitude.doubleValue() > 180.0) {
			throw new IllegalArgumentException("Longitude value "
					+ longitude.doubleValue()
					+ " not in range -180.0 <= x <= 180.0");
		}
	}

}
