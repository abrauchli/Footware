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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.footware.server.gpx.model.GPXTrackPoint;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.footware.shared.dto.TrackSegmentDTO;
import org.footware.shared.dto.TrackpointDTO;

/**
 * Class for ER Mapping of persisted TrackSegments, the ("sub-")tracks in a
 * single track file
 */
public class TrackSegment extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getTable() {
		return "tracksegment";
	}

	// Statistics
	private double maxSpeed = 0.0;
	private int minElevation = Integer.MAX_VALUE;
	private int maxElevation = Integer.MIN_VALUE;
	private double length = 0.0;

	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="track_id")
	private Track track;

	//@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	//@JoinColumn(name="tracksegment_id")
	//@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Trackpoint> trackpoints = new LinkedList<Trackpoint>();

	/**
	 * Constructor for hibernate retrieval
	 */
	public TrackSegment() {
	}

	/**
	 * Create a track segment from a GPXTrackSegment for persistence This also
	 * creates Trackpoints from the linked GPXTrackPoints
	 * 
	 * @param gpx
	 *            GPXTrackSegment to create from
	 */
	public TrackSegment(GPXTrackSegment gpx) {
		this.maxSpeed = gpx.getMaxSpeed();
		this.minElevation = gpx.getMinElevation().intValue();
		this.maxElevation = gpx.getMaxElevation().intValue();
		this.length = (long) gpx.getLength();

		for (GPXTrackPoint gpt : gpx.getPoints()) {
			Trackpoint tp = new Trackpoint(this, gpt);
			this.trackpoints.add(tp);
		}
	}

	/**
	 * Create a new object from the db id
	 * @param id
	 */
	public TrackSegment(Long id) {
		this.id = id;
	}
	
	/**
	 * Create a track segment from a TrackSegmentDTO for persistence This also
	 * creates Trackpoints from the linked TrackSegmentDTO
	 * 
	 * @param gpx
	 *            GPXTrackSegment to create from
	 */
	
	
	public TrackSegment(TrackSegmentDTO segment) {
		// Deep replication
		for (TrackpointDTO gpt : segment.getPoints()) {
			Trackpoint tp = new Trackpoint(this,gpt.getLatitude(),gpt.getLongitude(),gpt.getElevation(),gpt.getTime(),0);
			this.trackpoints.add(tp);
			// TODO
		}
	}

	@Override
	public void update() {
		// TODO
	}
	
	/**
	 * Gets the track belonging to this segment
	 * 
	 * @return this segment's corresponding track
	 */
	public Track getTrack() {
		return new Track(getLongValue("track_id", defaultId));
	}

	/**
	 * Sets the track this segment belongs to
	 * 
	 * @param track
	 *            new owner of this segment
	 */
	public void setTrack(Track track) {
		setLongValue("track_id", track.getId());
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * 
	 * @return maximum speed encountered in this segment
	 */
	public double getMaxSpeed() {
		return getDblValue("max_speed", 0);
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * 
	 * @return maximum speed encountered in this segment
	 */
	public void setMaxSpeed(double maxSpeed) {
		setDblValue("max_speed", maxSpeed);
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * 
	 * @return maximum speed encountered in this segment
	 */
	public double getMinElevation() {
		return getDblValue("min_elevation", 0.0);
	}

	/**
	 * Gets the maximum speed encountered in this segment
	 * 
	 * @return maximum speed encountered in this segment
	 */
	public void setMinElevation(double minElevation) {
		setDblValue("min_elevation", 0);
	}

	/**
	 * Gets the maximal elevation encountered in this segment
	 * 
	 * @return the maximal elevation encountered in this segment
	 */
	public long getMaxElevation() {
		return getLongValue("max_elevation", 0);
	}

	/**
	 * Sets the maximal elevation encountered in this segment
	 * 
	 * @param maxElevation
	 *            the maximal elevation encountered in this segment
	 */
	public void setMaxElevation(double maxElevation) {
		setDblValue("max_elevation", maxElevation);
	}

	/**
	 * Gets the segments length in meters
	 * 
	 * @return gets the segments length
	 */
	public double getLength() {
		return getDblValue("length", 0.0);
	}

	/**
	 * Sets the segments length
	 * 
	 * @param length
	 *            sets the segment length
	 */
	public void setLength(double length) {
		setDblValue("length", length);
	}

	/**
	 * Gets the trackpoints of this segment
	 * 
	 * @return all segment's trackpoints
	 */
	public List<Trackpoint> getTrackpoints() {
		List<Long> ids = getForeignKeys("tag", "track_id");
		List<Trackpoint> res = new LinkedList<Trackpoint>();
		for (Long l : ids)
			res.add(new Trackpoint(l));
		return res;
	}
	
	/**
	 * Appends a trackpoint to this segment
	 * 
	 * @param p
	 *            trackpoint to add
	 */
	public void addTrackpoint(Trackpoint p) {
		this.trackpoints.add(p);
		//TODO
	}

	/**
	 * Gets the DTO for this TrackSegment's current state
	 * 
	 * @return DTO for this TrackSegment's current state
	 */
	public TrackSegmentDTO getTrackSegmentDTO() {
		TrackSegmentDTO s = new TrackSegmentDTO();
		for (Trackpoint p : getTrackpoints())
			s.addPoint(p.getTrackpointDTO());
		return s;
	}
}
