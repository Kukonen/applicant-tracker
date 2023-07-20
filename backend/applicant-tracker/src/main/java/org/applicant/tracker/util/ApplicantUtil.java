package org.applicant.tracker.util;

import org.applicant.tracker.front.exceptions.InvalidSnilsException;
import org.springframework.stereotype.Service;

@Service
public class ApplicantUtil {

    public String formatSnils(String snils) {
        return snils.replaceAll("[^\\d]", "");
    }

    public String checkAndFormatSnils(String snils) throws InvalidSnilsException {
        String formattedSnils = formatSnils(snils);

        if (formattedSnils.length() != 11) {
            throw new InvalidSnilsException("Длина СНИЛС не равна 11!");
        }

        return formattedSnils;
    }

}
