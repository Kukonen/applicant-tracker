package org.applicant.tracker.dao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Произошла внутренняя ошибка базы данных")
public class InternalDatabaseException extends DatabaseException {

    public InternalDatabaseException(String message) {
        super(message);
    }

}
