package com.doctusoft.smiling.service;

import java.util.List;

import org.joda.time.DateTime;

import com.doctusoft.smiling.visitation.Visitation;
import com.doctusoft.smiling.visitation.VisitationDAO;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.inject.Inject;

public class VisitationServiceImpl implements VisitationService {
    
    private final VisitationDAO visitationDAO;
    
    @Inject
    public VisitationServiceImpl(VisitationDAO visitationDAO) {
        this.visitationDAO = visitationDAO;
    }

    @Override
    public List<Visitation> getVisitations(DateTime fromDate, int dayOfWeek, DateTime toDate) {
        List<Visitation> visitations = Lists.newArrayList();
        
        
        return visitations;
    }

}
