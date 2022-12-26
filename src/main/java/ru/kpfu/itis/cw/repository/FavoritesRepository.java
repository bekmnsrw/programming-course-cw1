package ru.kpfu.itis.cw.repository;

import ru.kpfu.itis.cw.dto.Favorites;

import java.util.List;

public interface FavoritesRepository {
    void save(Long userId, Long contentId);
    List<Long> findByUserId(Long id);
}
