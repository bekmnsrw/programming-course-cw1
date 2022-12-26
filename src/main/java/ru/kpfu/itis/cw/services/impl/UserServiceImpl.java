package ru.kpfu.itis.cw.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.dto.UserDto;
import ru.kpfu.itis.cw.dto.forms.SignInForm;
import ru.kpfu.itis.cw.dto.forms.SignUpForm;
import ru.kpfu.itis.cw.exceptions.IncorrectPasswordException;
import ru.kpfu.itis.cw.exceptions.NotFoundException;
import ru.kpfu.itis.cw.exceptions.ValidationException;
import ru.kpfu.itis.cw.models.User;
import ru.kpfu.itis.cw.repository.UserRepository;
import ru.kpfu.itis.cw.services.HashService;
import ru.kpfu.itis.cw.services.UserMapper;
import ru.kpfu.itis.cw.services.UserService;
import ru.kpfu.itis.cw.services.validation.ErrorEntity;
import ru.kpfu.itis.cw.services.validation.Validator;

import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Validator validator;
    private final HashService hashService;

    @Override
    public UserDto signUp(SignUpForm signUpForm) {
        Optional<ErrorEntity> optionalError = validator.validateRegistration(signUpForm);

        if (optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }

        User registeredUser = userMapper.toUserFromSignUpForm(signUpForm);

        userRepository.save(registeredUser);

        return userMapper.toUserDtoFromUser(registeredUser);
    }

    @Override
    public UserDto signIn(SignInForm signInForm) {
        Optional<User> optionalUser = userRepository.findByEmail(signInForm.getEmail());

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with email " + signInForm.getEmail() + " doesn't exist");
        }

        User user = optionalUser.get();

        if (!hashService.matches(signInForm.getPassword(), user.getHashedPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }

        return userMapper.toUserDtoFromUser(user);
    }
}
