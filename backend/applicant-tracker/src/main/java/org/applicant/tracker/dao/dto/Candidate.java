package org.applicant.tracker.dao.dto;

import org.springframework.data.relational.core.sql.In;
import org.springframework.lang.NonNull;

import java.util.Objects;

/** DTO - поступающий на конкретное направление конкретного ВУЗа */
public class Candidate implements IEntity {

    @NonNull
    private Long id;

    @NonNull
    private Long applicantId;

    @NonNull
    private Long programId;

    private Integer score;

    private Integer priority;

    private boolean isCertificateSubmitted;

    public Candidate(@NonNull Long id, @NonNull Long applicantId, @NonNull Long programId, Integer score, Integer priority, boolean isCertificateSubmitted) {
        this.id = id;
        this.applicantId = applicantId;
        this.programId = programId;
        this.score = score == null ? 0 : score;
        this.priority = priority == null ? 0 : priority;
        this.isCertificateSubmitted = isCertificateSubmitted;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(@NonNull Long applicantId) {
        this.applicantId = applicantId;
    }

    @NonNull
    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(@NonNull Long programId) {
        this.programId = programId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score == null ? 0 : score;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority == null ? 0 : priority;
    }

    public boolean isCertificateSubmitted() {
        return isCertificateSubmitted;
    }

    public void setCertificateSubmitted(boolean certificateSubmitted) {
        isCertificateSubmitted = certificateSubmitted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return isCertificateSubmitted == candidate.isCertificateSubmitted
                && Objects.equals(id, candidate.id)
                && Objects.equals(applicantId, candidate.applicantId)
                && Objects.equals(programId, candidate.programId)
                && Objects.equals(score, candidate.score)
                && Objects.equals(priority, candidate.priority);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", applicantId=" + applicantId +
                ", programId=" + programId +
                ", score=" + score +
                ", priority=" + priority +
                ", isCertificateSubmitted=" + isCertificateSubmitted +
                '}';
    }

}
