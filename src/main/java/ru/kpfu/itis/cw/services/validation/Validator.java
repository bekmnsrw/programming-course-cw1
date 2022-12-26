package ru.kpfu.itis.cw.services.validation;

import ru.kpfu.itis.cw.dto.forms.SignUpForm;
import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(SignUpForm signUpForm);
}
