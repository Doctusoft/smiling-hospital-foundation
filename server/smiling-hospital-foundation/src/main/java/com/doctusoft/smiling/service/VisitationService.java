package com.doctusoft.smiling.service;

import java.util.List;

import org.joda.time.DateTime;

import com.doctusoft.smiling.visitation.Visitation;

public interface VisitationService {

    public List<Visitation> getVisitations(DateTime fromDate, int dayOfWeek, DateTime toDate);
}
