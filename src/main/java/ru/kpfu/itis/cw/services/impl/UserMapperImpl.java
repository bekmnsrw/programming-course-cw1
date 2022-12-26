package ru.kpfu.itis.cw.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.dto.UserDto;
import ru.kpfu.itis.cw.dto.forms.SignUpForm;
import ru.kpfu.itis.cw.models.User;
import ru.kpfu.itis.cw.services.HashService;
import ru.kpfu.itis.cw.services.UserMapper;

@AllArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final HashService hashService;

    @Override
    public User toUserFromSignUpForm(SignUpForm signUpForm) {
        return User.builder()
                .firstName(signUpForm.getFirstName())
                .secondName(signUpForm.getSecondName())
                .email(signUpForm.getEmail())
                .hashedPassword(hashService.hashPassword(signUpForm.getPassword()))
                .build();
    }

    @Override
    public UserDto toUserDtoFromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .isAdmin(user.getIsAdmin())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .build();
    }
}
