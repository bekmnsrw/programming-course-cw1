package ru.kpfu.itis.cw.models;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Content {
    private Long id;
    private String name;
    private Boolean isNSFW;
    private String tag1;
    private String tag2;
}
