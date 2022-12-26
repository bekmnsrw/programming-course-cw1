package ru.kpfu.itis.cw.services;

import ru.kpfu.itis.cw.dto.UserDto;
import ru.kpfu.itis.cw.dto.forms.SignInForm;
import ru.kpfu.itis.cw.dto.forms.SignUpForm;

public interface UserService {
    UserDto signUp(SignUpForm signUpForm);
    UserDto signIn(SignInForm signInForm);
}
