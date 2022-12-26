package ru.kpfu.itis.cw.services.validation;

import lombok.Getter;

@Getter
public enum ErrorEntity {
    INVALID_EMAIL(461,"Invalid email"),
    EMAIL_ALREADY_TAKEN(453, "Email already taken");

    final int status;
    final String message;

    ErrorEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
