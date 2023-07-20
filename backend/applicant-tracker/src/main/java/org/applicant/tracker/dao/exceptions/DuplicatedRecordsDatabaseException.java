package org.applicant.tracker.dao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Произошла попытка вставить в БД запись с не уникальными полями")
public class DuplicatedRecordsDatabaseException extends DatabaseException {

    public DuplicatedRecordsDatabaseException(String message) {
        super(message);
    }

}
