package com.doctusoft.smiling.report;

import com.doctusoft.smiling.BaseDAOImpl;
import com.doctusoft.smiling.report.Report;
import com.doctusoft.smiling.report.ReportDAO;

class ReportDAOImpl extends BaseDAOImpl<Report> implements ReportDAO {

	public ReportDAOImpl() {
		super(Report.class);
	}
}
