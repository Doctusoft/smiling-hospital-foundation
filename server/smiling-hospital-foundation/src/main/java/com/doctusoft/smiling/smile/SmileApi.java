package com.doctusoft.smiling.smile;

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
		name = "smile",
		resource = "smile",
		clientIds = {
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID,
				Constants.WEB_CLIENT_ID,
		},
		description = "Lets you manage the smiles",
		authLevel = AuthLevel.NONE)
public class SmileApi {
	
	private final SmileDAO smileDAO;
	
	@Inject
	public SmileApi(SmileDAO smileDAO) {
		this.smileDAO = smileDAO;
	}

	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.POST, path = "/smile")
	public void createSmile(
			ApiSmile apiSmile,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {
		
		Smile smile = Smile.builder()
				.from(apiSmile.getFrom())
				.to(apiSmile.getTo())
				.date(apiSmile.getDate())
				.build();
		
		smileDAO.save(smile);
	}
	
	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/smile/{smileId}")
	public ApiSmile getSmile(
	        @Named("smileId") String smileId,
	        User user,
	        @Nullable @Named("sessionId") String sessionId)
	        throws ServiceException {
	    
	    Smile smile = smileDAO.get(smileId);
	    return convert(smile);
	}
	
	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/smile/delete/{smileId}")
	public void deleteSmile(
	        @Named("smileId") String smileId,
	        User user,
	        @Nullable @Named("sessionId") String sessionId)
	        throws ServiceException {
	    
	    Smile smile = smileDAO.get(smileId);
	    smileDAO.delete(smile);
	}
	
	private ApiSmile convert(Smile smile) {
	    return ApiSmile.builder()
	            .from(smile.getFrom())
	            .to(smile.getTo())
	            .date(smile.getDate())
	            .build();
	}
	
}
