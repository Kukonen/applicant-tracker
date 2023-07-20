package org.applicant.tracker.front.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Количество цифр переданного СНИЛС не равно 11")
public class InvalidSnilsException extends Exception{

    public InvalidSnilsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidSnilsException: " + super.getMessage();
    }

}
