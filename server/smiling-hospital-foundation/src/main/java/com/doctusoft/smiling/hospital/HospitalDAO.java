package com.doctusoft.smiling.hospital;

import java.util.List;

import com.doctusoft.smiling.BaseDAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(HospitalDAOImpl.class)
public interface HospitalDAO extends BaseDAO<Hospital> {

	List<Hospital> getHospitalsInCities(Iterable<String> cities);

}
