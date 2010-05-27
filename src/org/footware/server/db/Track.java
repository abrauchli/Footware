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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.footware.server.gpx.model.GPXTrack;
import org.footware.server.gpx.model.GPXTrackSegment;
import org.footware.shared.dto.CommentDTO;
import org.footware.shared.dto.TagDTO;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackSegmentDTO;

/**
 * Class for ER mapping of Tracks
 */
//@NamedQueries(value = {
//		//Get all public tracks
//		@NamedQuery(name = "tracks.getAllPublic", query = "FROM Track t WHERE t.disabled=0 AND t.publicity=5"),
//		@NamedQuery(name="tracks.getTrackById", query= "FROM Track t where t.id = :id")
//	})
public class Track extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getTable() {
		return "track";
	}
	
	private User user;

	//@Column(length = 128, nullable = false)
	private String filename;
	//@Column(length = 128)
	@SuppressWarnings("unused")
	private String path;
	//@Column(length = 256)
	private String notes;
	private int publicity;
	private boolean commentsEnabled;
	private int trackpoints;
	private double length;
	private double midLatitude;
	private double midLongitude;
	//@Column(name="time_start")
	private Date startTime;
	private boolean disabled;

	//@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	//@JoinColumn(name="track_id")
	//@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Comment> comments = new LinkedList<Comment>();

	//@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	//@JoinColumn(name="track_id")
	//@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<TrackSegment> segments = new HashSet<TrackSegment>();

	//@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	//@JoinColumn(name="track_id")
	//@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<Tag> tags = new HashSet<Tag>();

	//@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	//@JoinColumn(name="track_id")
	//@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<TrackVisualization> visualizations = new HashSet<TrackVisualization>();

	protected Track() {
	}

	/**
	 * Create a track for persistence
	 * 
	 * @param u
	 *            User owning the track
	 * @param filename
	 *            filename (as uploaded) of the track
	 * @param path
	 *            path where the track is saved on the server
	 */
	public Track(User u, String filename, String path) {
		this.user = u;
		this.filename = filename;
		this.path = path;
	}

	public Track(TrackDTO track) {
		this.id = track.getId();
		if (track.getUser() != null)
			this.user = new User(track.getUser());
		this.filename = track.getFilename();
		// this.path = track.getPath(); //cannot be modified by the client
		this.notes = track.getNotes();
		this.publicity = track.getPublicity();
		this.commentsEnabled = track.isCommentsEnabled();
		this.trackpoints = track.getTrackpoints();
		this.length = track.getLength();
		this.startTime = track.getStartTime();
		//Code below not handled this way yet, append them after DB creation
		for (TrackSegmentDTO segment : track.getSegments()) 
			this.segments.add(new TrackSegment(segment));
		for (CommentDTO c : track.getComments())
			this.comments.add(new Comment(c));
		for (TagDTO t : track.getTags())
			this.tags.add(new Tag(t));
	}
	
	public Track(GPXTrack track) {
		this.trackpoints = track.getNumberOfDataPoints();
		this.length = track.getLength();
		for(GPXTrackSegment segment : track.getSegments()) {
			segments.add(new TrackSegment(segment));
		}
	}

	/**
	 * Create a new object from the db id
	 * @param id
	 */
	public Track(long id) {
		this.id = id;
	}

	/**
	 * Gets the user belonging to this track
	 * @return this track's user
	 */
	public User getUser() {
		return new User(getLongValue("user_id", defaultId));
	}

	/**
	 * Sets the user owning this track
	 * 
	 * @param user
	 *            new user owning this track
	 */
	public void setUser(User user) {
		setLongValue("user_id", user.getId());
	}

	/**
	 * Gets the tracks original filename (as uploaded)
	 * 
	 * @return the tracks original filename
	 */
	public String getFilename() {
		return getStrValue("filename", null);
	}

	/**
	 * Sets the tracks filename (as uploaded)
	 * 
	 * @param filename
	 *            new filename
	 */
	public void setFilename(String filename) {
		setStrValue("filename", filename);
	}

	public String getPath() {
		return getStrValue("path", null);
	}

	public void setPath(String path) {
		setStrValue("path", path);
	}

	/**
	 * Gets the authors track notes
	 * 
	 * @return authors track notes
	 */
	public String getNotes() {
		return getStrValue("notes", null);
	}

	/**
	 * Sets the authors track notes
	 * 
	 * @param notes
	 */
	public void setNotes(String notes) {
		setStrValue("notes", notes);
	}

	/**
	 * Gets the publicity (permission) level for this track: 0 track is private,
	 * (not yet implemented: 1 selected users, 2 selected group, 3 friends, 4
	 * registered users), 5 public
	 * 
	 * @return the publicity (permission) level for this track
	 */
	public int getPublicity() {
		return getIntValue("publicity", 0);
	}

	/**
	 * Sets the publicity (permission) level for this track: 0 track is private,
	 * (not yet implemented: 1 selected users, 2 selected group, 3 friends, 4
	 * registered users), 5 public
	 * 
	 * @param publicity
	 *            new publicity (permission) level for this track
	 */
	public void setPublicity(int publicity) {
		setIntValue("publicity", publicity);
	}

	/**
	 * Gets whether commenting this track is enabled
	 * 
	 * @return true if enabled
	 */
	public boolean isCommentsEnabled() {
		return getIntValue("comments_enabled", 0) == 1;
	}

	/**
	 * Sets whether commenting this track is enabled
	 * 
	 * @param commentsEnabled
	 *            true if enabled (existing comments will not be deleted if
	 *            disabled)
	 */
	public void setCommentsEnabled(boolean commentsEnabled) {
		setIntValue("comments_enabled", commentsEnabled ? 1 : 0);
	}

	/**
	 * Gets the number of track points for this track
	 * 
	 * @return number of track points for this track
	 */
	public int getTrackpoints() {
		return getIntValue("trackpoints", 0);
	}

	/**
	 * Sets the number of track points for this track Should probably not be
	 * used from the client side
	 * 
	 * @param trackpoints
	 *            number of track points on this track
	 */
	public void setTrackpoints(int trackpoints) {
		setIntValue("trackpoints", trackpoints);
	}

	/**
	 * Gets the length in meters of this track
	 * 
	 * @return the length in meters of this track
	 */
	public double getLength() {
		return getDblValue("length", 0.0);
	}

	/**
	 * Sets the length (in meters) of this track Should probably not be used
	 * from the client side
	 * 
	 * @param length
	 *            new length in meters for this track
	 */
	public void setLength(double length) {
		setDblValue("length", length);
	}

	/**
	 * Gets the time of the first track point in this track
	 * 
	 * @return the time of the first track point in this track
	 */
	public Date getStartTime() {
		Timestamp t = getTimestampValue("time_start", null);
		if (t != null)
			return new Date(t.getTime());
		return null;
	}

	/**
	 * Sets the time of the first track point in this track Should probably not
	 * be used from the client side
	 * 
	 * @param startTime
	 *            time of the first track point in this track
	 */
	public void setStartTime(Date startTime) {
		setTimestampValue("time_start", new Timestamp(startTime.getTime()));
	}

	/**
	 * Gets the mean latitude of the track
	 * 
	 * @return mean latitude of the track
	 */
	public double getMidLatitude() {
		return getDblValue("mid_latitude", 0);
	}

	/**
	 * Sets the mean latitude of the track
	 * 
	 * @param midLatitude
	 *            mean latitude of the track
	 */
	public void setMidLatitude(double midLatitude) {
		setDblValue("mid_latitude", midLatitude);
	}

	/**
	 * Gets the mean longitude of the track
	 * 
	 * @return mean longitude of the track
	 */
	public double getMidLongitude() {
		return getDblValue("mid_longitude", 0);
	}

	/**
	 * Sets the mean longitude of the track
	 * 
	 * @param midLongitude
	 *            mean longitude of the track
	 */
	public void setMidLongitude(double midLongitude) {
		setDblValue("mid_longitude", midLongitude);
	}

	/**
	 * Sets this track as disabled or enabled
	 * @param disabled whether to enable or disable this track
	 */
	public void setDisabled(boolean disabled) {
		setIntValue("is_disabled", disabled ? 1 : 0);
	}

	/**
	 * Gets whether this track is disabled or not
	 * @return true if disabled, false if enabled
	 */
	public boolean isDisabled() {
		return getIntValue("is_disabled", 0) == 1;
	}

	/**
	 * Gets all comments for this track
	 * 
	 * @return List of all comments for this track
	 */
	public List<Comment> getComments() {
		List<Long> ids = getForeignKeys("comment", "track_id");
		List<Comment> res = new LinkedList<Comment>();
		for (Long l : ids)
			res.add(new Comment(l));
		return res;
	}

	/**
	 * Adds a new comment to this track Note: the user writing the comment is
	 * registered in the comment object
	 * 
	 * @param comment
	 *            comment to be added to this track
	 */
	public void addComment(Comment comment) {
		//comments.add(comment);
		comment.setTrack(this);
	}
	
	/**
	 * Gets the visualizations for this track
	 * @return visualizations for this track
	 */
	public Set<TrackVisualization> getVisualizations() {
		List<Long> ids = getForeignKeys("visualization", "track_id");
		Set<TrackVisualization> res = new HashSet<TrackVisualization>();
		for (Long l : ids)
			res.add(new TrackVisualization(l));
		return res;
	}
	
	/**
	 * Adds a visualization for this track
	 * @param v visualization to add
	 */
	public void addVisualization(TrackVisualization v) {
		//visualizations.add(v);
		v.setTrack(this);
	}
	
	/**
	 * Gets the segments belonging to this track
	 * @return segments of this track
	 */
	public Set<TrackSegment> getSegments() {
		List<Long> ids = getForeignKeys("track_segment", "track_id");
		Set<TrackSegment> res = new HashSet<TrackSegment>();
		for (Long l : ids)
			res.add(new TrackSegment(l));
		return res;
	}
	
	/**
	 * Adds a new segment to this track
	 * @param s segment to add
	 */
	public void addSegment(TrackSegment s) {
		//segments.add(s);
		s.setTrack(this);
	}

	/**
	 * Gets the tags attached to this track
	 * @return set of tags attached to this track
	 */
	public Set<Tag> getTags() {
		List<Long> ids = getForeignKeys("tag", "track_id");
		Set<Tag> res = new HashSet<Tag>();
		for (Long l : ids)
			res.add(new Tag(l));
		return res;
	}
	
	/**
	 * Adds a tag to this track
	 * @param t tag to add
	 */
	public void addTag(Tag t) {
		//tags.add(t);
		t.setTrack(this);
	}
	
	/**
	 * Gets the TrackDTO object from this Track's current state
	 * 
	 * @return TrackDTO with this tracks current state
	 */
	public TrackDTO getTrackDTO() {
		TrackDTO t = new TrackDTO();
		//will be set by the user to prevent stackoverflow
		//t.setUser(user.getUserDTO());
		t.setFilename(filename);
		t.setNotes(notes);
		t.setPublicity(publicity);
		t.setCommentsEnabled(commentsEnabled);
		t.setTrackpoints(trackpoints);
		t.setLength(length);
		t.setMidLatitude(midLatitude);
		t.setMidLongitude(midLongitude);
		t.setStartTime(startTime);
		t.setDisabled(disabled);
		//Code below not handled yet, append them after object creation (after store() call)
		for (Comment c : getComments())
			t.addComment(c.getCommentDTO());
		for (TrackSegment s : getSegments())
			t.addSegment(s.getTrackSegmentDTO());
		for (Tag tag : getTags())
			t.addTag(tag.getTagDTO());
		return t;
	}

}
