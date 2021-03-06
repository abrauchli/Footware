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

public class TagDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String tag;
	private TrackDTO track;

	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Creates a new Tag
	 * @param track track to tag
	 * @param tag name of the tag
	 */
	public TagDTO(TrackDTO track, String tag) {
		this.track = track;
		this.tag = tag;
	}

	/**
	 * Gets the tag
	 * @return the tag name
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * Sets / Renames the tag
	 * @param tag new tag name
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * Gets the track
	 * @return the track
	 */
	public TrackDTO getTrack() {
		return track;
	}
	
	/**
	 * Sets the track for this tag
	 * @param track new track for this tag
	 */
	public void setTrack(TrackDTO track) {
		this.track = track;
	}
}
