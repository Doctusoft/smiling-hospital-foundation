package com.doctusoft.smiling.hospital;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.IdGenerator;
import com.google.appengine.repackaged.com.google.common.base.MoreObjects;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@NoArgsConstructor
@Data
@Cache
@Entity
@EqualsAndHashCode(callSuper=true, of={})
public class Hospital extends BaseEntity {

	@Id
	private String id;
	@Index
	private String name;

	@Builder
	public Hospital(String id, String name) {
		super();
		this.id = MoreObjects.firstNonNull(id, IdGenerator.createId());
		this.name = name;
	}


}
