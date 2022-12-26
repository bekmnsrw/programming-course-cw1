package ru.kpfu.itis.cw.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.dto.forms.SignUpForm;
import ru.kpfu.itis.cw.repository.UserRepository;
import ru.kpfu.itis.cw.services.validation.ErrorEntity;
import ru.kpfu.itis.cw.services.validation.Validator;

import java.util.Optional;

@AllArgsConstructor
public class ValidatorImpl implements Validator {
    private final UserRepository userRepository;

    @Override
    public Optional<ErrorEntity> validateRegistration(SignUpForm signUpForm) {
        if (signUpForm.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}
