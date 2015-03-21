package com.doctusoft.smiling.hospital;

import com.doctusoft.smiling.BaseDAOImpl;
import com.googlecode.objectify.ObjectifyService;

public class HospitalDAOImpl extends BaseDAOImpl<Hospital> implements HospitalDAO {

	static {
		ObjectifyService.factory().register(Hospital.class);
	}

	public HospitalDAOImpl() {
		super(Hospital.class);
	}

}
