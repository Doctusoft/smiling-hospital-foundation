package com.doctusoft.smiling.hospital;

import com.doctusoft.smiling.BaseDAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(HospitalDAOImpl.class)
public interface HospitalDAO extends BaseDAO<Hospital>{

}
