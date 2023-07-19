package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.Applicant;
import org.applicant.tracker.dao.mapper.IApplicantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService extends AbstractDatabaseService<IApplicantMapper, Applicant> {

    @Autowired
    public ApplicantService(IApplicantMapper mapper) {
        this.mapper = mapper;
    }

}
