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

import org.footware.shared.dto.TagDTO;

/**
 * Class for ER mapping of Tags
 */
public class Tag extends DbEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	@Column(length=16)
	private String tag;
	private Track track;
	
	protected Tag() {
	}
	
	/**
	 * Creates a new Tag
	 * @param tag name of the tag
	 */
	public Tag(Track track, String tag) {
		this.tag = tag;
	}

	/**
	 * Creates a new Tag from DTO
	 * @param tag tag received from web frontend 
	 */
	public Tag(TagDTO tag) {
		this.id = tag.getId();
		this.tag = tag.getTag();
	}

	/**
	 * Create a new object from the db id
	 * @param id
	 */
	public Tag(Long id) {
		this.id = id;
	}

	@Override
	protected String getTable() {
		return "tag";
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Gets the tag
	 * @return tag the tag name
	 */
	public String getTag() {
		return getStrValue("tag", null);
	}

	/**
	 * Sets / Renames the tag
	 * @param tag new tag name
	 */
	public void setTag(String tag) {
		setStrValue("tag", tag);
	}

	/**
	 * Gets the track
	 * @return the track
	 */
	public Track getTrack() {
		return new Track(getLongValue("track_id", defaultId));
	}
	
	/**
	 * Sets the track for this tag
	 * @param track new track for this tag
	 */
	public void setTrack(Track track) {
		setLongValue("track_id", track.getId());
	}

	/**
	 * Gets the TagDTO for the current Tag state
	 * @return the TagDTO for the current Tag state
	 */
	public TagDTO getTagDTO() {
		return new TagDTO(track.getTrackDTO(), tag);
	}
}
