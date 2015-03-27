package com.doctusoft.smiling.report;

import com.doctusoft.smiling.BaseDAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ReportDAOImpl.class)
public interface ReportDAO extends BaseDAO<Report> {
}
