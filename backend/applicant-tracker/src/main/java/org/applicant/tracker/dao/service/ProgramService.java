package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.Program;
import org.applicant.tracker.dao.mapper.IProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService extends AbstractDatabaseService<IProgramMapper, Program> {

    @Autowired
    public ProgramService(IProgramMapper mapper) {
        this.mapper = mapper;
    }

}
