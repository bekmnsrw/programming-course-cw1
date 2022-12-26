package ru.kpfu.itis.cw.services.impl;

import ru.kpfu.itis.cw.dto.ContentDto;
import ru.kpfu.itis.cw.models.Content;
import ru.kpfu.itis.cw.services.ContentMapper;

public class ContentMapperImpl implements ContentMapper {

    @Override
    public Content toContentFromContentDto(ContentDto contentDto) {
        return Content.builder()
                .id(contentDto.getId())
                .name(contentDto.getName())
                .isNSFW(contentDto.getIsNSFW())
                .build();
    }
}
