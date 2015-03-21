package com.doctusoft.smiling;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;

@Getter
public class BaseEntity implements Serializable {

	public static interface IndexedProperties {
		String CREATED_AT = "createdAt";
		String LAST_MODIFIED_AT = "lastModifiedAt";
	}

	@Index
	private Date createdAt;
	@Index
	private Date lastModifiedAt;

	@OnSave
	public void onSave() {
		if(createdAt == null) {
			createdAt = new Date();
		}
		lastModifiedAt = new Date();
	}
}
