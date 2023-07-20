package org.applicant.tracker.front.responseMap;

import org.applicant.tracker.front.entity.ApplicantResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;

public class ApplicantResponseMap {

    @NonNull
    HttpStatus status;

    @NonNull
    ApplicantResponse[] applicants;

    public ApplicantResponseMap(@NonNull HttpStatus status, @NonNull List<ApplicantResponse> applicants) {
        this.status = status;
        this.applicants =  applicants.toArray(new ApplicantResponse[0]);
    }

    @NonNull
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(@NonNull HttpStatus status) {
        this.status = status;
    }

    @NonNull
    public ApplicantResponse[] getApplicants() {
        return applicants;
    }

    public void setApplicants(@NonNull ApplicantResponse[] applicants) {
        this.applicants = applicants;
    }
}
