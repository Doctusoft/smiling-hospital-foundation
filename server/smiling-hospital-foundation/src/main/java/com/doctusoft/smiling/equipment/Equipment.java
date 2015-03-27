package com.doctusoft.smiling.equipment;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


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
