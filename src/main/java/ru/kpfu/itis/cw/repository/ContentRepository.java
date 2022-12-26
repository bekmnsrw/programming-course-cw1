package ru.kpfu.itis.cw.repository;

import ru.kpfu.itis.cw.models.Content;

import java.util.List;
import java.util.Optional;

public interface ContentRepository {
    void save(Content content);
    Optional<Content> findById(Long id);
    List<Content> findAll();

    List<Content> search(String query);
}
