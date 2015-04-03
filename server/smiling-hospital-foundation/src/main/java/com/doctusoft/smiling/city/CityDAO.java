package com.doctusoft.smiling.city;

import com.doctusoft.smiling.BaseDAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(CityDAOImpl.class)
public interface CityDAO extends BaseDAO<City> {

}
