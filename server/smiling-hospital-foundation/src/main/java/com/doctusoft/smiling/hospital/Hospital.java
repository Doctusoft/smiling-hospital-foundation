package com.doctusoft.smiling.hospital;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.IdGenerator;
import com.google.common.base.MoreObjects;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@NoArgsConstructor
@Data
@Cache
@Entity
@EqualsAndHashCode(callSuper=true, of={"id"})
public class Hospital extends BaseEntity {

	public static interface IndexedProperties extends BaseEntity.IndexedProperties {
		String CITY = "city";
	}

	@Id
	private String id;
	@Unindex
	private String name;
	@Index
	private String city;


	@Builder
	public Hospital(String id, String name, String city) {
		super();
		this.id = MoreObjects.firstNonNull(id, IdGenerator.createId());
		this.name = name;
		this.city = city;
	}


}
