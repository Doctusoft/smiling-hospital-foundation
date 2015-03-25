package com.doctusoft.smiling.equipment;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.IdGenerator;
import com.doctusoft.smiling.hospital.Hospital;
import com.doctusoft.smiling.hospital.Hospital.HospitalBuilder;
import com.google.appengine.repackaged.com.google.common.base.MoreObjects;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;


@NoArgsConstructor
@Data
@Cache
@Entity
@EqualsAndHashCode(callSuper=true, of={"name"})
public class Equipment extends BaseEntity{
	
	public static interface IndexedProperties extends
			BaseEntity.IndexedProperties {
	}

	@Id
	private String name;

	@Builder
	public Equipment(String name) {
		super();
		this.name = name;
	}

}
