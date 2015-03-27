package com.doctusoft.smiling.report;

import java.util.List;
import com.doctusoft.smiling.Constants;
import com.doctusoft.smiling.report.ApiReport;
import com.doctusoft.smiling.report.Report;
import com.doctusoft.smiling.report.ReportDAO;
import com.doctusoft.smiling.security.PermissionLevel;
import com.doctusoft.smiling.security.Restricted;
import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.AuthLevel;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.inject.Inject;

@Api(
		name = "report",
		resource = "report",
		clientIds = {
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID,
				Constants.WEB_CLIENT_ID,
		},
		description = "Lets you manage the reports",
		authLevel = AuthLevel.NONE)
public class ReportApi {

	private final ReportDAO reportDAO;

	@Inject
	public ReportApi(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	@Restricted(PermissionLevel.COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.POST, path = "/report")
	public void createReport(
			ApiReport apiReport,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		apiReport.validateAndNormalize();

		Report report = Report.builder()
					.email(				apiReport.getEmail())
					.visitationId(		apiReport.getVisitationId())
					.department(		apiReport.getDepartment())
					.numberOfChildren(	apiReport.getNumberOfChildren())
					.numberOfParents(	apiReport.getNumberOfParents())
					.contentOfOcupation(apiReport.getContentOfOcupation())
					.customShortage(	apiReport.getCustomShortage())
					.opinionOnThePeers(	apiReport.getOpinionOnThePeers())
					.problem(			apiReport.getProblem())
					.solution(			apiReport.getSolution())
					.story(				apiReport.getStory())
					.build();

		reportDAO.save(report);
	}

	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/report/{reportName}")
	public ApiReport getReport(
			@Named("reportName") String reportName,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		Report report = reportDAO.get(reportName);
		return convert(report);
	}

	@Restricted(PermissionLevel.COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/report/delete/{reportName}")
	public void deleteReport(
			@Named("reportName") String reportName,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		Report report = reportDAO.get(reportName);
		reportDAO.delete(report);
	}

	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/report")
	public List<ApiReport> getReports(
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		List<Report> reports = reportDAO.getAll();
		return convertList(reports);
	}

	private List<ApiReport> convertList(List<Report> reports) {
		List<ApiReport> apiReports = Lists.newArrayList();
		for (Report report : reports) {
			apiReports.add(convert(report));
		}
		return apiReports;
	}

	private ApiReport convert(Report report) {
		return ApiReport.builder()
				.department(		report.getDepartment())
				.numberOfChildren(	report.getNumberOfChildren())
				.numberOfParents(	report.getNumberOfParents())
				.contentOfOcupation(report.getContentOfOcupation())
				.customShortage(	report.getCustomShortage())
				.opinionOnThePeers(	report.getOpinionOnThePeers())
				.problem(			report.getProblem())
				.solution(			report.getSolution())
				.story(				report.getStory())
				.build();
	}

}
