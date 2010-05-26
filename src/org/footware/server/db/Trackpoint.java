/*
 * Copyright 2010 Andreas Brauchli, René Buffat, Florian Widmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.footware.server.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.shared.dto.TrackpointDTO;

/**
 * Class for ER Mapping of persisted Trackpoints
 */
@Entity
public class Trackpoint extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	TrackSegment segment;
	double latitude;
	double longitude;
	double elevation;
	Date time;
	double speed;
	
	/**
	 * Protected constructor for hibernate retrieval
	 */
	protected Trackpoint() {
	}
	
	/**
	 * Create a trackpoint from a GPXTrackPoint for persistence
	 * @param seg the segment this point belongs to
	 * @param gpx the GPXTrackPoint to create this object from
	 */
	public Trackpoint(TrackSegment seg, GPXTrackPoint gpx) {
		this.segment = seg;
		this.latitude = gpx.getLatitude().doubleValue();
		this.longitude = gpx.getLongitude().doubleValue();
		this.elevation = gpx.getElevation().doubleValue();
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
	 * Gets the segment this point is part of
	 * @return the segment this point is part of
	 */
	public TrackSegment getSegment() {
		return this.segment;
	}

	/**
	 * Sets the new segment belonging to this trackpoint
	 * @param seg the new segment this trackpoint is part of
	 */
	public void setSegment(TrackSegment seg) {
		this.segment = seg;
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
	public double getElevation() {
		return elevation;
	}

	/**
	 * @param elevation set the elevation on this trackpoint
	 */
	public void setElevation(double elevation) {
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
	
	public TrackpointDTO getTrackpointDTO() {
		TrackpointDTO t = new TrackpointDTO(longitude, latitude);
		//set time, speed
		return t;
	}
}
