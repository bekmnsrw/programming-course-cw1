package ru.kpfu.itis.cw.services;

import ru.kpfu.itis.cw.dto.UserDto;
import ru.kpfu.itis.cw.dto.forms.SignUpForm;
import ru.kpfu.itis.cw.models.User;

public interface UserMapper {
    User toUserFromSignUpForm(SignUpForm signUpForm);
    UserDto toUserDtoFromUser(User user);
}
