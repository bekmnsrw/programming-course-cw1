package ru.kpfu.itis.cw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private Boolean isAdmin;
    private String firstName;
    private String secondName;
    private String email;
}
