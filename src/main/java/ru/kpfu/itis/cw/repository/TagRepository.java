package ru.kpfu.itis.cw.repository;

import ru.kpfu.itis.cw.models.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    void save(Tag tag);
    Optional<Tag> findById(String name);
    void saveToContentTag(Long contentId, Long tagId);
    List<Long> findTagsByContent(Long contentId);
    List<Long> findContentByTags(Long tagId);
}
