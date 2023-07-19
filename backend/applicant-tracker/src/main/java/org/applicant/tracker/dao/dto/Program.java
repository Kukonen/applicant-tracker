package org.applicant.tracker.dao.dto;

import org.applicant.tracker.enums.EducationForm;
import org.applicant.tracker.enums.PaymentType;
import org.springframework.lang.NonNull;

import java.util.Objects;

/** DTO - программа обучения */
public class Program implements IEntity {

    @NonNull
    private Long id;

    @NonNull
    private String university;

    private int places;

    @NonNull
    private String name;

    @NonNull
    private EducationForm form;

    @NonNull
    private PaymentType type;

    public Program(@NonNull Long id, @NonNull String university, int places, @NonNull String name, @NonNull EducationForm form, @NonNull PaymentType type) {
        this.id = id;
        this.university = university;
        this.places = places;
        this.name = name;
        this.form = form;
        this.type = type;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getUniversity() {
        return university;
    }

    public void setUniversity(@NonNull String university) {
        this.university = university;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public EducationForm getForm() {
        return form;
    }

    public void setForm(@NonNull EducationForm form) {
        this.form = form;
    }

    @NonNull
    public PaymentType getType() {
        return type;
    }

    public void setType(@NonNull PaymentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return places == program.places
                && Objects.equals(id, program.id)
                && Objects.equals(university, program.university)
                && Objects.equals(name, program.name)
                && form == program.form
                && type == program.type;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", university='" + university + '\'' +
                ", places=" + places +
                ", name='" + name + '\'' +
                ", form=" + form +
                ", type=" + type +
                '}';
    }

}
