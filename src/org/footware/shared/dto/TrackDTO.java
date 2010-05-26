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

package org.footware.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * DTO Class for Tracks 
 */
public class TrackDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private UserDTO user;
	private String filename;
	//private String path;
	private String notes;
	private int publicity;
	private boolean commentsEnabled;
	private int trackpoints;
	private double length;
	private double midLongitude;
	private double midLatitude;
	private Date startTime;
	private List<CommentDTO> comments = new LinkedList<CommentDTO>();
	private List<TrackSegmentDTO> segments = new LinkedList<TrackSegmentDTO>();
	private Set<TagDTO> tags = new HashSet<TagDTO>();

	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Gets the user belonging to this track
	 * @return this track's user
	 */
	public UserDTO getUser() {
		return user;
	}
	
	/**
	 * Sets the user owning this track
	 * @param user new user owning this track
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	/**
	 * Gets the tracks original filename (as uploaded)
	 * @return the tracks original filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Sets the tracks filename (as uploaded)
	 * @param filename new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
//	@Deprecated
//	public String getPath() {
//		return path;
//	}
//	
//	@Deprecated
//	public void setPath(String path) {
//		this.path = path;
//	}
	
	/**
	 * Gets the authors track notes
	 * @return authors track notes
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * Sets the authors track notes
	 * @param notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * Gets the publicity (permission) level for this track
	 * @return the publicity (permission) level for this track
	 */
	public int getPublicity() {
		return publicity;
	}
	
	/**
	 * Sets the publicity (permission) level for this track
	 * @param publicity new publicity (permission) level for this track
	 */
	public void setPublicity(int publicity) {
		this.publicity = publicity;
	}
	
	/**
	 * Gets whether commenting this track is enabled
	 * @return true if enabled
	 */
	public boolean isCommentsEnabled() {
		return commentsEnabled;
	}
	
	/**
	 * Sets whether commenting this track is enabled
	 * @param commentsEnabled true if enabled (existing comments will not be deleted if disabled)
	 */
	public void setCommentsEnabled(boolean commentsEnabled) {
		this.commentsEnabled = commentsEnabled;
	}
	
	/**
	 * Gets the number of track points for this track
	 * @return number of track points for this track
	 */
	public int getTrackpoints() {
		return trackpoints;
	}
	
	/**
	 * Sets the number of track points for this track
	 * Should probably not be used from the client side
	 * @param trackpoints number of track points on this track
	 */
	public void setTrackpoints(int trackpoints) {
		this.trackpoints = trackpoints;
	}
	
	/**
	 * Gets the length in meters of this track
	 * @return the length in meters of this track
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Sets the length (in meters) of this track
	 * Should probably not be used from the client side
	 * @param length new length in meters for this track
	 */
	public void setLength(double length) {
		this.length = length;
	}
	
	/**
	 * Gets the time of the first track point in this track
	 * @return the time of the first track point in this track
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the time of the first track point in this track
	 * Should probably not be used from the client side
	 * @param startTime time of the first track point in this track
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Gets all comments for this track
	 * @return List of all comments for this track
	 */
	public List<CommentDTO> getComments() {
		return comments;
	}

	/**
	 * Gets the mean longitude of the track
	 * @return mean longitude of the track
	 */
	public double getMidLongitude() {
		return midLongitude;
	}

	/**
	 * Sets the mean longitude of the track
	 * @param midLongitude mean longitude of the track
	 */
	public void setMidLongitude(double midLongitude) {
		this.midLongitude = midLongitude;
	}

	/**
	 * Gets the mean latitude of the track
	 * @return mean latitude of the track
	 */
	public double getMidLatitude() {
		return midLatitude;
	}

	/**
	 * Sets the mean latitude of the track
	 * @param midLatitude mean latitude of the track
	 */
	public void setMidLatitude(double midLatitude) {
		this.midLatitude = midLatitude;
	}
	

	/**
	 * @return the segments
	 */
	public List<TrackSegmentDTO> getSegments() {
		return segments;
	}
	
	/**
	 * @param segment the segment to add
	 */
	public void addSegment(TrackSegmentDTO segment) {
		segments.add(segment);
	}

	/**
	 * Adds a new comment to this track
	 * Note: the user writing the comment is registered in the comment object
	 * @param comment comment to be added to this track
	 */
	public void addComment(CommentDTO comment) {
		if (comments == null)
			comments = new ArrayList<CommentDTO>();
		comments.add(comment);
	}

	/**
	 * Adds a tag to this track
	 * @param tagDTO tag to add
	 */
	public void addTag(TagDTO t) {
		tags.add(t);
	}

	/**
	 * Gets the tags set on this track
	 * @return tags for this track
	 */
	public Set<TagDTO> getTags() {
		return tags;
	}
}
