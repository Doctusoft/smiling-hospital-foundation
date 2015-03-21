package com.doctusoft.smiling.user;

import com.doctusoft.smiling.BaseDAOImpl;
import com.googlecode.objectify.ObjectifyService;

public class SmilingUserDAOImpl extends BaseDAOImpl<SmilingUser> implements SmilingUserDAO {

	static {
		ObjectifyService.factory().register(SmilingUser.class);
	}

	public SmilingUserDAOImpl() {
		super(SmilingUser.class);
	}
}
