package org.applicant.tracker.front.entity;

import org.applicant.tracker.enums.EducationForm;
import org.applicant.tracker.enums.PaymentType;
import org.springframework.lang.NonNull;

public class ApplicantResponse {

    @NonNull
    private String university;

    private int programPlaces;

    @NonNull
    private String programName;

    @NonNull
    private String educationForm;

    @NonNull
    private String paymentType;

    private int score;

    private int priority;

    private boolean isCertificateSubmitted;

    public ApplicantResponse(@NonNull String university, int programPlaces, @NonNull String programName,
                             @NonNull EducationForm educationForm, @NonNull PaymentType paymentType,
                             int score, int priority, boolean isCertificateSubmitted) {

        this.university = university;
        this.programPlaces = programPlaces;
        this.programName = programName;
        this.educationForm = educationForm.name(); // Преобразуем к стринге
        this.paymentType = paymentType.name(); // Преобразуем к стринге
        this.score = score;
        this.priority = priority;
        this.isCertificateSubmitted = isCertificateSubmitted;
    }

    @NonNull
    public String getUniversity() {
        return university;
    }

    public void setUniversity(@NonNull String university) {
        this.university = university;
    }

    public int getProgramPlaces() {
        return programPlaces;
    }

    public void setProgramPlaces(int programPlaces) {
        this.programPlaces = programPlaces;
    }

    @NonNull
    public String getProgramName() {
        return programName;
    }

    public void setProgramName(@NonNull String programName) {
        this.programName = programName;
    }

    @NonNull
    public String getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(@NonNull String educationForm) {
        this.educationForm = educationForm;
    }

    @NonNull
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@NonNull String paymentType) {
        this.paymentType = paymentType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCertificateSubmitted() {
        return isCertificateSubmitted;
    }

    public void setCertificateSubmitted(boolean certificateSubmitted) {
        isCertificateSubmitted = certificateSubmitted;
    }
}
