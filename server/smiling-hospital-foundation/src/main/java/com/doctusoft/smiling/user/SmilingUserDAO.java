package com.doctusoft.smiling.user;

import com.doctusoft.smiling.BaseDAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(SmilingUserDAOImpl.class)
public interface SmilingUserDAO extends BaseDAO<SmilingUser> {

}
