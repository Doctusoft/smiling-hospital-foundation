package com.doctusoft.smiling.smile;

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
@EqualsAndHashCode(callSuper=true, of={"smileId"})
public class Smile extends BaseEntity {
	
	@Id
	private String smileId;
	private String from;
	private String to;
	private String date;
	
	@Builder
	public Smile(String from, String to, String date) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
	}
}
