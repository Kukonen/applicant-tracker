package org.applicant.tracker.dao.dto;

import org.springframework.lang.NonNull;

import java.util.Objects;

/** DTO - абитуриент */
public class Applicant implements IEntity {

    @NonNull
    private Long id;

    @NonNull
    private String snils;

    private String name;

    public Applicant(@NonNull Long id, @NonNull String snils, String name) {
        this.id = id;
        this.snils = snils;
        this.name = name;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getSnils() {
        return snils;
    }

    public void setSnils(@NonNull String snils) {
        this.snils = snils;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(id, applicant.id) && Objects.equals(snils, applicant.snils) && Objects.equals(name, applicant.name);
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", snils='" + snils + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
