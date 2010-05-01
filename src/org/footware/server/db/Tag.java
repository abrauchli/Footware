package org.footware.server.db;

import java.io.Serializable;

import org.footware.shared.dto.TagDTO;

public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String tag;
	
	public Tag(String tag) {
		this.tag = tag;
	}
	
	public Tag(TagDTO tag) {
		this.id = tag.getId();
		this.tag = tag.getTag();
	}
	
	public long getId() {
		return id;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

}
