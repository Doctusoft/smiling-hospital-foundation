package com.doctusoft.smiling.city;

import com.doctusoft.smiling.Constants;
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
import com.google.inject.Inject;

@Api(
		name = "city",
		resource = "city",
		clientIds = {
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID,
				Constants.WEB_CLIENT_ID,
		},
		description = "Lets you manage the cities",
		authLevel = AuthLevel.NONE)
public class CityApi {
	
	private final CityDAO cityDAO;
	
	@Inject
	public CityApi(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	
	@Restricted(PermissionLevel.NATIONAL_COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.POST, path ="/city")
	public void createCity(
			ApiCity apiCity,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {
		
		City city = City.builder()
					.name(apiCity.getName())
					.build();
		cityDAO.save(city);
	}
	
	@Restricted(PermissionLevel.NATIONAL_COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/city/delete/{cityName}")
	public void deleteCity(
			@Named("cityName") String cityName,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {
		
		City city = cityDAO.get(cityName);
		cityDAO.(city);
	}

}
