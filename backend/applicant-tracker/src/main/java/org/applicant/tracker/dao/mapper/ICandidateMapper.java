package org.applicant.tracker.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.applicant.tracker.dao.dto.Candidate;

import java.util.List;

@Mapper
public interface ICandidateMapper extends IMapper<Candidate> {

    List<Candidate> getByApplicantId(Long applicantId);

    List<Candidate> getByProgramId(Long programId);

    Candidate getBySnils(String snils);

}
