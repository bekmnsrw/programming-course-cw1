package ru.kpfu.itis.cw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContentDto {
    private Long id;
    private String name;
    private Boolean isNSFW;
}
