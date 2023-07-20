package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.Candidate;
import org.applicant.tracker.dao.mapper.ICandidateMapper;
import org.applicant.tracker.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService extends AbstractDatabaseService<ICandidateMapper, Candidate> {

    @Autowired
    public CandidateService(ICandidateMapper mapper) {
        this.mapper = mapper;
    }

    List<Candidate> getByApplicantId(Long applicantId) {
        return mapper.getByApplicantId(applicantId);
    }

    List<Candidate> getByProgramId(Long programId) {
        return mapper.getByProgramId(programId);
    }

    Candidate getBySnils(String snils) throws DatabaseException {
        Candidate candidate = mapper.getBySnils(snils);

        if (candidate == null) {
            throw new DatabaseException("Абитуриент с таким СНИЛС не был обнаружен в таблице!");
        }

        return candidate;
    }

}
