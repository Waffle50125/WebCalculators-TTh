package edu.kirkwood.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    private static Properties properties = new Properties();
    static {
        try(
                InputStream in = MovieDAOFactory.class.getClassLoader()
                        .getResourceAsStream("application.properties");
        ) {
            if (in == null) {
                throw new FileNotFoundException("application.properties not found");
            }
            properties.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL driver", e);
        }
        String connectionString = properties.getProperty("mysql.connectionString");
        if (connectionString == null || connectionString.isEmpty()) {
            throw new IllegalArgumentException("mysql.connectionString is missing");
        }

        try {
            Connection connection = DriverManager.getConnection(connectionString);
            if (connection.isValid(2)) {
                return connection;
            } else {
                throw new RuntimeException("Unable to connect to database");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to database", e);
        }
    }
}
