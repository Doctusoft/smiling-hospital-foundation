package com.doctusoft.smiling.visitation;

import org.joda.time.DateTime;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.doctusoft.smiling.BaseEntity;
import com.doctusoft.smiling.hospital.Hospital;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@NoArgsConstructor
@Data
@Cache
@Entity
@EqualsAndHashCode(of={"visitationId"})
public class Visitation extends BaseEntity {

    @Id
    private String id;
    private long minVolunteers;
    private long maxVolunteers;
    private DateTime time;
    private String duration;
    private String hospitalId;
    
    @Builder
    public Visitation(long minVolunteers, long maxVolunteers, DateTime time, String duration, String hospitalId) {
        super();
        this.minVolunteers = minVolunteers;
        this.maxVolunteers = maxVolunteers;
        this.time = time;
        this.duration = duration;
        this.hospitalId = hospitalId;
    }
}
