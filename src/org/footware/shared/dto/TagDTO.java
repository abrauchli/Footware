package org.footware.shared.dto;

import java.io.Serializable;

public class TagDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String tag;

	/**
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Creates a new Tag
	 * @param tag name of the tag
	 */
	public TagDTO(String tag) {
		this.tag = tag;
	}
	
	/**
	 * Gets the tag
	 * @return tag the tag name
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
}
