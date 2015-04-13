package com.doctusoft.smiling.hospital;

import java.io.Serializable;
import java.util.List;

import com.doctusoft.smiling.hospital.Hospital.VisitingTime;
import com.googlecode.objectify.Ref;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiHospital implements Serializable {

	private String id;
	private String name;
	private String city;
	private List<VisitingTime> visitingTimes;
}
