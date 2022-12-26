package ru.kpfu.itis.cw.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.dto.ContentDto;
import ru.kpfu.itis.cw.repository.ContentRepository;
import ru.kpfu.itis.cw.services.ContentMapper;
import ru.kpfu.itis.cw.services.ContentService;

@AllArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Override
    public void save(ContentDto contentDto) {
        contentRepository.save(contentMapper.toContentFromContentDto(contentDto));
    }
}
