package com.doctusoft.smiling;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;

import com.googlecode.objectify.annotation.OnSave;

@Getter
public class BaseEntity implements Serializable {

	private Date createdAt;
	private Date lastModifiedAt;

	@OnSave
	public void onSave() {
		if(createdAt == null) {
			createdAt = new Date();
		}
		lastModifiedAt = new Date();
	}
}
