package org.applicant.tracker.dao.exceptions;

import java.util.UUID;

public class DatabaseException extends Exception {

    public DatabaseException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Database exception: " + super.getMessage();
    }

}
