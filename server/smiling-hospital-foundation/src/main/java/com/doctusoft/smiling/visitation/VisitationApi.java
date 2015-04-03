package com.doctusoft.smiling.visitation;

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
        name = "visitation",
        resource = "visitation",
        clientIds = {
                        com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID,
                        Constants.WEB_CLIENT_ID,
        },
        description = "Lets you manage the visitations",
        authLevel = AuthLevel.NONE)
public class VisitationApi {

    private final VisitationDAO visitationDAO;
    
    @Inject
    public VisitationApi(VisitationDAO visitationDAO) {
        this.visitationDAO = visitationDAO;
    }
    
    @Restricted(PermissionLevel.VOLUNTEER)
    @ApiMethod(httpMethod = HttpMethod.POST, path="/visitation")
    public void createVisitation(
            ApiVisitation apiVisitation,
            User user,
            @Nullable @Named("sessionId") String sessionId)
            throws ServiceException {
        
        Visitation visitation = Visitation.builder()
                .minVolunteers(apiVisitation.getMinVolunteers())
                .maxVolunteers(apiVisitation.getMaxVolunteers())
                .time(apiVisitation.getTime())
                .build();
        
        visitationDAO.save(visitation);
    }
    
    @Restricted(PermissionLevel.REGISTRATED)
    @ApiMethod(httpMethod = HttpMethod.GET, path = "/visitation/{visitationId}")
    public ApiVisitation getVisitation(
            @Named("visitationId") String visitationId,
            User user,
            @Nullable @Named("sessionId") String sessionId)
            throws ServiceException {
        
        Visitation visitation = visitationDAO.get(visitationId);
        return convert(visitation);
    }
    
    @Restricted(PermissionLevel.REGISTRATED)
    @ApiMethod(httpMethod = HttpMethod.GET)
    public void deleteVisitation(
            @Named("visitationId") String visitationId,
            User user,
            @Nullable @Named("sessionId") String sessionId)
            throws ServiceException {
        
        Visitation visitation = visitationDAO.get(visitationId);
        visitationDAO.delete(visitation);
    }

    private ApiVisitation convert(Visitation visitation) {
        return ApiVisitation.builder()
                .minVolunteers(visitation.getMinVolunteers())
                .maxVolunteers(visitation.getMaxVolunteers())
                .time(visitation.getTime())
                .build();
    }
}
