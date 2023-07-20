package org.applicant.tracker.front.controller;

import org.applicant.tracker.dao.dto.Candidate;
import org.applicant.tracker.dao.dto.Program;
import org.applicant.tracker.dao.exceptions.DatabaseException;
import org.applicant.tracker.dao.service.ApplicantService;
import org.applicant.tracker.dao.service.CandidateService;
import org.applicant.tracker.dao.service.ProgramService;
import org.applicant.tracker.front.entity.ApplicantResponse;
import org.applicant.tracker.front.responseMap.ApplicantResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApplicantController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    ProgramService programService;

    @Autowired
    ApplicantService applicantService;


    @GetMapping(value = "/applicant/{snils}")
    public ApplicantResponseMap getApplicants(@PathVariable String snils) {

        System.out.println(snils);
        System.out.println(applicantService.getAll());
        System.out.println(candidateService.getAll());

        try {
            // candidatePrograms - Все направления, на которые подал найденный по СНИЛС абитуриент. В каждом он - кандидат
            List<Candidate> candidatePrograms = candidateService.getBySnils(snils);

            // Инициализируем список, который закинем в ответ сервера. Это найденные кандидатуры абитуриента (направления на которые подал)
            List<ApplicantResponse> applicantResponses = new ArrayList<>();


            for (Candidate candidate : candidatePrograms) {
                // Из каждой кандидатуры абитуриента достаём его направление
                Program program = programService.getById(candidate.getProgramId());

                // Формируем ответ на основе информации о кандидатуре и добавляем его в список ответов
                applicantResponses.add(new ApplicantResponse(program.getUniversity(),
                        program.getPlaces(), program.getName(), program.getForm(), program.getType(),
                        candidate.getScore(), candidate.getPriority(), candidate.isCertificateSubmitted()));
            }

            return new ApplicantResponseMap(HttpStatus.OK, applicantResponses);

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

    }

}
