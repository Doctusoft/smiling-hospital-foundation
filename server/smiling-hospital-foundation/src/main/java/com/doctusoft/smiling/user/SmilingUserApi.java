package com.doctusoft.smiling.user;

import com.doctusoft.smiling.Constants;
import com.doctusoft.smiling.security.PermissionLevel;
import com.doctusoft.smiling.security.Restricted;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.AuthLevel;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.inject.Inject;

@Api(
		name = "user",
		resource = "user",
		clientIds = {
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID,
				Constants.WEB_CLIENT_ID,
		},
		description = "Lets you manage the users",
		authLevel = AuthLevel.NONE)
public class SmilingUserApi {

	private final SmilingUserDAO smilingUserDAO;

	@Inject
	public SmilingUserApi(SmilingUserDAO smilingUserDAO) {
		super();
		this.smilingUserDAO = smilingUserDAO;
	}

	@Restricted(PermissionLevel.VISITOR)
	@ApiMethod(httpMethod = HttpMethod.POST, path = "/user")
	public void registerUser(
			RegistrationData registrationData,
			User user,
			@Nullable @Named("sessionId") String sessionId) {

		registrationData.validateAndNormalize();

		// TODO fill the other fields
		smilingUserDAO.save(SmilingUser.builder()
				.permissionLevel(PermissionLevel.VOLUNTEER)
				.email(registrationData.getEmail())
				.name(registrationData.getName())
				.cities(registrationData.getCities())
				.build());
	}

	@ApiMethod(httpMethod = HttpMethod.PUT, path = "/user/{userId}")
	public void updateUser() {

	}
}
