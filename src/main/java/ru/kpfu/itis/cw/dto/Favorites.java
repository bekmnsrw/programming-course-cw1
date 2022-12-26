package ru.kpfu.itis.cw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Favorites {
    private Long userId;
    private Long contentId;
}
