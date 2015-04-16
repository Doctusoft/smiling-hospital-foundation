package com.doctusoft.smiling.hospital;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Collections;
import java.util.List;

import com.doctusoft.smiling.BaseDAOImpl;
import com.google.common.collect.Iterables;

class HospitalDAOImpl extends BaseDAOImpl<Hospital> implements HospitalDAO {

	public HospitalDAOImpl() {
		super(Hospital.class);
	}

	@Override
	public List<Hospital> getHospitalsInCities(Iterable<String> cities) {
		if(Iterables.isEmpty(cities)) {
			return Collections.emptyList();
		}
		return ofy().load().type(entityClass).filter(Hospital.IndexedProperties.CITY + " in", cities).list();
	}

}
