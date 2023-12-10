package net.nyobasqlite;

import net.nyobasqlite.database.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        String jdbcUrl = "jdbc:sqlite:src/main/java/net/nyobasqlite/database/userdb.db";
//
//        try {
//            Connection connection = DatabaseConnection.getDataSource().getConnection();
//            String sql = "select rowid, * from users";
//
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                Integer id = resultSet.getInt("rowid");
//                String name = resultSet.getString("name");
//                String email = resultSet.getString("email");
//
//                System.out.println(id + " " + name + " | " + email);
//
//            }
//
//            statement.close();
//            connection.close();
//            resultSet.close();
//
//        } catch (SQLException e) {
//            System.out.println("error in jdbc : " + e.getMessage());
//            e.printStackTrace();
//        }

//        readAllData();
        insertData("mamama", "huhuhu@a.coi");
        readAllData();

    }

    public static void readAllData() {
        String sql = "select * from users";

        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        String username = resultSet.getString("name");
                        String email = resultSet.getString("email");

                        System.out.println("Username : " + username);
                        System.out.println("Email : " + email);
                    }

                }

            }

        } catch (SQLException e) {
            System.out.println("eror when read all data : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void insertData(String name, String email) {

        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            String sql = "insert into users(name, email) values(?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("error when insert data : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
