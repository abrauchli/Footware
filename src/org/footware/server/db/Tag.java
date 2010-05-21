package org.footware.server.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.footware.shared.dto.TagDTO;

/**
 * Class for ER mapping of Tags
 */
@Entity
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(length=16)
	private String tag;
	
	protected Tag() {}
	
	/**
	 * Creates a new Tag
	 * @param tag name of the tag
	 */
	public Tag(String tag) {
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
	 * Gets the id of the corresponding DB row
	 * @return the ID of the row in the DB
	 */
	public long getId() {
		return id;
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
