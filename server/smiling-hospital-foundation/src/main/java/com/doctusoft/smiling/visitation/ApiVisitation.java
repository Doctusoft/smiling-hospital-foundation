package com.doctusoft.smiling.visitation;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiVisitation implements Serializable {

    private String id;
    private long minVolunteers;
    private long maxVolunteers;
    private DateTime time;
    private String duration;
    private String hospitalId;
}
