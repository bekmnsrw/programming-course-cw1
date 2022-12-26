package ru.kpfu.itis.cw.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SignUpForm {
    private String firstName;
    private String secondName;
    private String email;
    private String password;
}
