package ru.kpfu.itis.cw.repository.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.repository.FavoritesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FavoritesRepositoryImpl implements FavoritesRepository {
    private final Connection connection;
    private static final String SQL_SAVE = "INSERT INTO favorites(content_id, user_id) VALUES(?, ?)";
    private static final String SQL_FIND = "SELECT content_id FROM favorites WHERE user_id = ?";

    @Override
    public void save(Long userId, Long contentId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, contentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Long> findByUserId(Long id) {
        List<Long> content = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND);
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    content.add(resultSet.getLong("content_id"));
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
