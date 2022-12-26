package ru.kpfu.itis.cw.repository.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.cw.models.User;
import ru.kpfu.itis.cw.repository.UserRepository;

import java.sql.*;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final Connection connection;
    private final static String SQL_SAVE = "INSERT INTO users (first_name, second_name, email, hashed_password) VALUES (?, ?, ?, ?)";
    private final static String SQL_GET_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    private static final Function<ResultSet, User> mapper = row -> {
        try {
            return User.builder()
                    .id(row.getLong("id"))
                    .isAdmin(row.getBoolean("is_admin"))
                    .firstName(row.getString("first_name"))
                    .secondName(row.getString("second_name"))
                    .email(row.getString("email"))
                    .hashedPassword(row.getString("hashed_password"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getHashedPassword());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Can't save user");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Can't obtain generated key");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_EMAIL);
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = mapper.apply(resultSet);
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
