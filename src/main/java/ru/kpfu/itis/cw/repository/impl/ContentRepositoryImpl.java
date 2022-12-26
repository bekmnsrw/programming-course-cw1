package ru.kpfu.itis.cw.repository.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.models.Content;
import ru.kpfu.itis.cw.repository.ContentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
public class ContentRepositoryImpl implements ContentRepository {
    private final Connection connection;
    private final static String SQL_SAVE = "INSERT INTO content (name, is_nsfw) VALUES (?, ?)";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM content WHERE id = ?";
    private final static String SQL_GET_ALL_CONTENT = "SELECT * FROM content";
    private final static String SQL_FIND = "SELECT * FROM content WHERE name ILIKE ";

    private static String sqlGenerator(String query) {
        return SQL_FIND + "\'%" + query + "%\'";
    }

    private static final Function<ResultSet, Content> mapper = row -> {
        try {
            return Content.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .isNSFW(row.getBoolean("is_nsfw"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public void save(Content content) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, content.getName());
            preparedStatement.setBoolean(2, content.getIsNSFW());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Can't save content");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                content.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Can't obtain generated key");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Content> findById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Content content = mapper.apply(resultSet);
                    return Optional.of(content);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Content> findAll() {
        List<Content> content = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_CONTENT);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    content.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return content;
    }

    @Override
    public List<Content> search(String query) {
        String sql = sqlGenerator(query);

        List<Content> content = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    content.add(mapper.apply(resultSet));
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
