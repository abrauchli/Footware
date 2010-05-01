package org.footware.shared.dto;

import java.io.Serializable;

public class TagDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String tag;
	
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
