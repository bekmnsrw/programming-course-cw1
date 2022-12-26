package ru.kpfu.itis.cw.jdbc_connection;

import ru.kpfu.itis.cw.exceptions.DbConnectionException;
import ru.kpfu.itis.cw.exceptions.DbDriverException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DbConnectionException, DbDriverException {
        if (_instance == null) {
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    private Connection connection;

    private ConnectionProvider() throws DbDriverException, DbConnectionException {
        try {
            Class.forName(DriverManagerDataHolder.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DbDriverException("Unable to find driver class.", e);
        }

        try {
            connection = DriverManager.getConnection(
                    DriverManagerDataHolder.URL,
                    DriverManagerDataHolder.USERNAME,
                    DriverManagerDataHolder.PASSWORD
            );
        } catch (SQLException e) {
            throw new DbConnectionException("Unable to connect to database.", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
