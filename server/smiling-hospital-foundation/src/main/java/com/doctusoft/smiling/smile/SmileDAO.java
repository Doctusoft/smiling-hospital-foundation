package com.doctusoft.smiling.smile;

import com.doctusoft.smiling.BaseDAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(SmileDAOImpl.class)
public interface SmileDAO extends BaseDAO<Smile> {

}
