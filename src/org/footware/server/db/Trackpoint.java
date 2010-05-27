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
import java.sql.Timestamp;
import java.util.Date;

import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.shared.dto.TrackpointDTO;

/**
 * Class for ER Mapping of persisted Trackpoints
 */
public class Trackpoint extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getTable() {
		return "trackpoint";
	}

	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="tracksegment_id")
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
	 * Creates a trackpoint from scratch
	 */
	public Trackpoint(TrackSegment segment, double latitude, double longitude, double elevation, Date time, double speed) {
		this.segment = segment;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.time = time;
		this.speed = speed;
	}
	
	public Trackpoint(long id) {
		this.id = id;
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
	
	@Override
	public void update() {
		// TODO
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
		setLongValue("tracksegment_id", seg.getId());
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return getDblValue("longitude", 0.0);
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		setDblValue("longitude", longitude);
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return getDblValue("latitude", 0.0);
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		setDblValue("latitude", latitude);
	}

	/**
	 * @return the elevation on this trackpoint
	 */
	public double getElevation() {
		return getDblValue("elevation", 0.0);
	}

	/**
	 * @param elevation set the elevation on this trackpoint
	 */
	public void setElevation(double elevation) {
		setDblValue("elevation", elevation);
	}

	/**
	 * @return the time of this trackpoint
	 */
	public Date getTime() {
		Timestamp t = getTimestampValue("time", null);
		if (t != null)
			return new Date(t.getTime());
		return null;
	}

	/**
	 * @param time set the time for this trackpoint
	 */
	public void setTime(Date time) {
		setTimestampValue("time", new Timestamp(time.getTime()));
	}

	/**
	 * @return the momentary speed at this trackpoint
	 */
	public double getSpeed() {
		return getDblValue("speed", 0);
	}

	/**
	 * @param speed set the speed at this trackpoint
	 */
	public void setSpeed(double speed) {
		setDblValue("speed", speed);
	}
	
	public TrackpointDTO getTrackpointDTO() {
		return new TrackpointDTO(longitude, latitude, elevation, time);
	}
}
