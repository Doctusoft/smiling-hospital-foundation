package com.doctusoft.smiling.hospital;

import java.util.List;
import java.util.Set;

import com.doctusoft.smiling.Constants;
import com.doctusoft.smiling.security.AuthenticationService;
import com.doctusoft.smiling.security.PermissionLevel;
import com.doctusoft.smiling.security.Restricted;
import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.AuthLevel;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.inject.Inject;

@Api(
		name = "hospital",
		resource = "hospital",
		clientIds = {
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID,
				Constants.WEB_CLIENT_ID,
		},
		description = "Lets you manage the hospitals",
		authLevel = AuthLevel.NONE)
public class HospitalApi {

	private final HospitalDAO hospitalDAO;
	private final AuthenticationService authenticationService;

	@Inject
	public HospitalApi(HospitalDAO hospitalDAO, AuthenticationService authenticationService) {
		this.hospitalDAO = hospitalDAO;
		this.authenticationService = authenticationService;
	}

	@Restricted(PermissionLevel.NATIONAL_COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.POST, path = "/hospital")
	public void createHospital(
			ApiHospital apiHospital,
			User user,
			@Nullable @Named("sessionId") String sessionId)
					throws ServiceException {

		Hospital hospital = Hospital.builder()
				.name(apiHospital.getName())
				.city(apiHospital.getCity())
				.visitingTimes(apiHospital.getVisitingTimes())
				.build();

		hospitalDAO.save(hospital);
	}

	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/hospital/{hospitalId}")
	public ApiHospital getHospital(
			@Named("hospitalId") String hospitalId,
			User user,
			@Nullable @Named("sessionId") String sessionId)
					throws ServiceException {

		Hospital hospital = hospitalDAO.get(hospitalId);
		return convert(hospital);
	}

	@Restricted(PermissionLevel.NATIONAL_COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/hospital")
	public List<ApiHospital> getHospitals(
			User user,
			@Nullable @Named("sessionId") String sessionId)
					throws ServiceException {

		List<Hospital> hospitals = hospitalDAO.getAll();
		return convertList(hospitals);
	}

	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/hospital/near")
	public List<ApiHospital> getHospitalsNearToMe(
			User user,
			@Nullable @Named("sessionId") String sessionId)
					throws ServiceException {

		Set<String> cities = authenticationService.getUserContext().getCities();
		List<Hospital> hospitals = hospitalDAO.getHospitalsInCities(cities);
		List<ApiHospital> apiHospitals = convertList(hospitals);
		return apiHospitals;
	}

	private List<ApiHospital> convertList(List<Hospital> hospitals) {
		List<ApiHospital> apiHospitals = Lists.newArrayList();
		for (Hospital hospital : hospitals) {
			apiHospitals.add(convert(hospital));
		}
		return apiHospitals;
	}

	private ApiHospital convert(Hospital hospital) {
		return ApiHospital.builder()
				.id(hospital.getId())
				.name(hospital.getName())
				.city(hospital.getCity())
				.visitingTimes(hospital.getVisitingTimes())
				.build();
	}
}
