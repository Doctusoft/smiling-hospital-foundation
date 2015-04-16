package com.doctusoft.smiling.hospital;

import java.util.List;

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
import com.googlecode.objectify.annotation.Unindex;

@NoArgsConstructor
@Data
@Cache
@Entity
@EqualsAndHashCode(of={"id"})
public class Hospital extends BaseEntity {
    
        @Entity
        @Data
        @Index
        public class VisitingTime {
            @Id
            private String id;
            private long minVolunteers;
            private long maxVolunteers;
            private long dayOfWeek;
            private String duration;
        }

	public static interface IndexedProperties extends BaseEntity.IndexedProperties {
		String CITY = "city";
	}

	@Id
	private String id;
	@Unindex
	private String name;
	@Index
	private String city;
	private List<VisitingTime> visitingTimes;


	@Builder
	public Hospital(String id, String name, String city, List<VisitingTime> visitingTimes) {
		super();
		this.id = MoreObjects.firstNonNull(id, IdGenerator.createId());
		this.name = name;
		this.city = city;
		this.visitingTimes = visitingTimes;
	}


}
