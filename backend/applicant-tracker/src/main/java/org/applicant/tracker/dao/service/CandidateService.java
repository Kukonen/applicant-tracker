package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.Candidate;
import org.applicant.tracker.dao.exceptions.NoContentDatabaseException;
import org.applicant.tracker.dao.mapper.ICandidateMapper;
import org.applicant.tracker.dao.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService extends AbstractDatabaseService<ICandidateMapper, Candidate> {

    @Autowired
    public CandidateService(ICandidateMapper mapper) {
        this.mapper = mapper;
    }

    public List<Candidate> getByApplicantId(Long applicantId) {
        return mapper.getByApplicantId(applicantId);
    }

    public List<Candidate> getByProgramId(Long programId) {
        return mapper.getByProgramId(programId);
    }

    public List<Candidate> getBySnils(String snils) throws DatabaseException {
        List<Candidate> candidate = mapper.getBySnils(snils);

        if (candidate.isEmpty()) {
            throw new NoContentDatabaseException("Абитуриент с таким СНИЛС не был обнаружен в таблице!");
        }

        return candidate;
    }

}
