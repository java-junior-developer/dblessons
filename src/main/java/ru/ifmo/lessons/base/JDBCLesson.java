package ru.ifmo.lessons.base;

import java.sql.*;

public class JDBCLesson {
    // данные для подключения
    // 1. строка подключения
    static final String CONNECTION_STR =
            "jdbc:postgresql://localhost:5432/lessons";
    static final String LOGIN = "jjd";
    static final String PWD = "jjd";

    private static void createTable()
            throws ClassNotFoundException, SQLException {
        String create = "CREATE TABLE IF NOT EXISTS tb_books(" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(50) NOT NULL" +
                ");";
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                CONNECTION_STR, LOGIN, PWD
        )){
            try(Statement statement = connection.createStatement()){
                int res = statement.executeUpdate(create);
                System.out.println(res);
            }
        }
    }
    public static void main(String[] args) {
        try {
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
