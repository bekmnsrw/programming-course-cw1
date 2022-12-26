package ru.kpfu.itis.cw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private Boolean isAdmin;
    private String firstName;
    private String secondName;
    private String email;
    private String hashedPassword;
}
