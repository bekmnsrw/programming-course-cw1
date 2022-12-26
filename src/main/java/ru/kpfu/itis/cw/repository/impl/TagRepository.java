package ru.kpfu.itis.cw.repository.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.models.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TagRepository implements ru.kpfu.itis.cw.repository.TagRepository {
    private final Connection connection;
    private static final String SQL_SAVE = "INSERT INTO tag(name) VALUES(?)";
    private final static String SQL_GET_BY_NAME = "SELECT * FROM tag WHERE name = ?";
    private final static String SQL_GET_BY_CONTENT_ID = "SELECT tag_id FROM content_tag WHERE content_id = ?";
    private final static String SQL_GET_BY_TAG_ID = "SELECT content_id FROM content_tag WHERE tag_id = ?";
    private final static String SQL_SAVE_CONTENT_TAG = "INSERT INTO content_tag(content_id, tag_id) VALUES(?, ?)";

    @Override
    public void save(Tag tag) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Can't save tag");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                tag.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Can't obtain generated key");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<Tag> findById(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME);
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Tag tag = Tag.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .build();
                    return Optional.of(tag);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void saveToContentTag(Long contentId, Long tagId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_CONTENT_TAG, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, contentId);
            preparedStatement.setLong(2, tagId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Long> findTagsByContent(Long contentId) {
        List<Long> tags = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_CONTENT_ID);
            preparedStatement.setLong(1, contentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tags.add(resultSet.getLong("content_id"));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return tags;
    }

    @Override
    public List<Long> findContentByTags(Long tagId) {
        List<Long> content = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_TAG_ID);
            preparedStatement.setLong(1, tagId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    content.add(resultSet.getLong("tag_id"));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return content;
    }
}
