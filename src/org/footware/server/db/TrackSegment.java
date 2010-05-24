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
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;

/**
 * Class for ER Mapping of persisted TrackSegments,
 * the ("sub-")tracks in a single track file
 */
@Entity
public class TrackSegment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	// Statistics
	private double maxSpeed = 0.0;
	private long minElevation = Long.MAX_VALUE;
	private long maxElevation = Long.MIN_VALUE;
	private long length = 0;
	@ManyToOne(fetch=FetchType.LAZY)
	private Track track;
	@ManyToOne(fetch=FetchType.LAZY)
	private List<Trackpoint> trackpoints;
	
	/**
	 * Protected constructor for hibernate retrieval
	 */
	protected TrackSegment() {
	}
	
	/**
	 * Create a track segment from a GPXTrackSegment for persistence
	 * This also creates Trackpoints from the linked GPXTrackPoints
	 * @param gpx GPXTrackSegment to create from
	 */
	public TrackSegment(GPXTrackSegment gpx) {
		this.maxSpeed = gpx.getMaxSpeed();
		this.minElevation = gpx.getMinElevation().longValue();
		this.maxElevation = gpx.getMaxElevation().longValue();
		//TODO: this.length = gpx.getLength();
		
		//Deep replication
		for (GPXTrackPoint gpt : gpx.getPoints()) {
			Trackpoint tp = new Trackpoint(gpt);
			this.trackpoints.add(tp);
			//TODO: Check if we should persist here
		}
	}
	
	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the track belonging to this segment
	 * @return this segment's corresponding track
	 */
	public Track getTrack() {
		return track;
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * @return maximum speed encountered in this segment
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * @return maximum speed encountered in this segment
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * @return maximum speed encountered in this segment
	 */
	public long getMinElevation() {
		return minElevation;
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * @return maximum speed encountered in this segment
	 */
	public void setMinElevation(long minElevation) {
		this.minElevation = minElevation;
	}

	/**
	 * Gets the maximal elevation encountered in this segment
	 * @return the maximal elevation encountered in this segment
	 */
	public long getMaxElevation() {
		return maxElevation;
	}

	/**
	 * Sets the maximal elevation encountered in this segment
	 * @param maxElevation the maximal elevation encountered in this segment
	 */
	public void setMaxElevation(long maxElevation) {
		this.maxElevation = maxElevation;
	}

	/**
	 * Gets the segments length (TODO: in meters?)
	 * @return gets the segments length
	 */
	public long getLength() {
		return length;
	}

	/**
	 * Sets the segments length
	 * @param length sets the segment length
	 */
	public void setLength(long length) {
		this.length = length;
	}
}
