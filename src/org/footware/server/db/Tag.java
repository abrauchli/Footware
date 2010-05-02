package org.footware.server.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.footware.shared.dto.TagDTO;

@Entity
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(length=16)
	private String tag;
	
	protected Tag() {}
	
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
