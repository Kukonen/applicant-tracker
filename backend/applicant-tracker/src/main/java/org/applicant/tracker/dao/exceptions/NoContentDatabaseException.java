package org.applicant.tracker.dao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Данные с запрашиваемыми идентификаторами не были обнаружены в базе данных")
public class NoContentDatabaseException extends DatabaseException{

    public NoContentDatabaseException(String message) {
        super(message);
    }

}
