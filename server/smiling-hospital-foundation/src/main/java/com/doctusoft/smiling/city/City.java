package com.doctusoft.smiling.city;

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
@EqualsAndHashCode(of={"name"})
public class City extends BaseEntity {

	@Id
	private String name;
	
	@Builder
	public City(String name) {
		super();
		this.name = name;
	}
}
