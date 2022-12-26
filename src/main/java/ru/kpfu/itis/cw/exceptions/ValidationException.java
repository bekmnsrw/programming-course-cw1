package ru.kpfu.itis.cw.exceptions;

import lombok.Getter;
import ru.kpfu.itis.cw.services.validation.ErrorEntity;

@Getter
public class ValidationException extends RuntimeException {
    private final ErrorEntity entity;

    public ValidationException(ErrorEntity entity) {
        super(entity.getMessage());
        this.entity = entity;
    }

    public ValidationException(ErrorEntity entity, String message) {
        super(message);
        this.entity = entity;
    }
}
