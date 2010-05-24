package org.footware.server.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.footware.server.gpx.model.GPXTrackPoint;

/**
 * Class for ER Mapping of persisted Trackpoints
 */
@Entity
public class Trackpoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	double longitude;
	double latitude;
	int elevation;
	Date time;
	double speed;
	
	/**
	 * Protected constructor for hibernate retrieval
	 */
	protected Trackpoint() {
	}
	
	/**
	 * Create a trackpoint from a GPXTrackPoint for persistence
	 * @param gpx the GPXTrackPoint to create this object from
	 */
	public Trackpoint(GPXTrackPoint gpx) {
		this.longitude = gpx.getLongitude().doubleValue();
		this.latitude = gpx.getLatitude().doubleValue();
		this.elevation = gpx.getElevation().intValue();
		this.time = gpx.getTime().toDate();
		this.speed = gpx.getSpeed();
	}
	
	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
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
	 * @return the elevation on this trackpoint
	 */
	public int getElevation() {
		return elevation;
	}

	/**
	 * @param elevation set the elevation on this trackpoint
	 */
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return the time of this trackpoint
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time set the time for this trackpoint
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the momentary speed at this trackpoint
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed set the speed at this trackpoint
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
