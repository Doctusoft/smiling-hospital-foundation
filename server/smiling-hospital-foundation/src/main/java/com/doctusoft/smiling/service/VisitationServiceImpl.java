package com.doctusoft.smiling.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.doctusoft.smiling.hospital.Hospital;
import com.doctusoft.smiling.hospital.Hospital.VisitingTime;
import com.doctusoft.smiling.hospital.HospitalDAO;
import com.doctusoft.smiling.visitation.Visitation;
import com.doctusoft.smiling.visitation.VisitationDAO;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.inject.Inject;

public class VisitationServiceImpl implements VisitationService {
    
    private final VisitationDAO visitationDAO;
    private final HospitalDAO hospitalDAO;
    
    @Inject
    public VisitationServiceImpl(VisitationDAO visitationDAO, HospitalDAO hospitalDAO) {
        this.visitationDAO = visitationDAO;
        this.hospitalDAO = hospitalDAO;
    }
    
    @Override
    public void createVisitations() {
        List<Hospital> hospitals = hospitalDAO.getAll();
        for (Hospital hospital : hospitals) {
            getVisitationDates(hospital);
        }
    }

    private void getVisitationDates(Hospital hospital) {
        for (VisitingTime visitingTime : hospital.getVisitingTimes()) {
            createVisitation(hospital, visitingTime);
        }
    }


    private void createVisitation(Hospital hospital, VisitingTime visitingTime) {
        List<DateTime> dates = createVisitationDates(DateTime.now(), visitingTime.getDayOfWeek(),
                DateTime.now().plusMonths(1));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyy-MM-dd");
        for (DateTime date : dates) {
            String id = hospital.getName() + " - " + formatter.print(date) + ": " + visitingTime.getDuration();
            if (visitationDAO.get(id) == null) {
                Visitation visitation = Visitation.builder()
                    .minVolunteers(visitingTime.getMinVolunteers())
                    .maxVolunteers(visitingTime.getMaxVolunteers())
                    .time(date)
                    .duration(visitingTime.getDuration())
                    .hospitalId(hospital.getId())
                    .build();
                visitation.setId(id);
                visitationDAO.save(visitation);
            }
        }
    }
    
    private List<DateTime> createVisitationDates(DateTime fromDate, long dayOfWeek, DateTime toDate) {
        List<DateTime> dates = Lists.newArrayList();
        for (DateTime date = fromDate; date.isBefore(toDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == dayOfWeek) {
                dates.add(date);
            }
        }
        return dates;
    }

    @Override
    public void deleteVisitation(String visitationId) {
    }
}
