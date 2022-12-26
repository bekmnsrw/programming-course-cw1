package ru.kpfu.itis.cw.services;

import ru.kpfu.itis.cw.dto.ContentDto;
import ru.kpfu.itis.cw.models.Content;

public interface ContentMapper {
    Content toContentFromContentDto(ContentDto contentDto);
}
