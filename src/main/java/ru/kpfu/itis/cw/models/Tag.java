package ru.kpfu.itis.cw.models;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Data
public class Tag {
    private Long id;
    private String name;
}
