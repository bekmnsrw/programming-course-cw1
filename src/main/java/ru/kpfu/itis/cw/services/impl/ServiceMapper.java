package ru.kpfu.itis.cw.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.models.Content;
import ru.kpfu.itis.cw.repository.ContentRepository;
import ru.kpfu.itis.cw.repository.FavoritesRepository;
import ru.kpfu.itis.cw.repository.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ServiceMapper {
    private final FavoritesRepository favoritesRepository;
    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;

    public List<Content> findContent(Long id) {
        return favoritesRepository.findByUserId(id)
                .stream()
                .map(x -> contentRepository.findById(x).get())
                .collect(Collectors.toList());
    }
}
