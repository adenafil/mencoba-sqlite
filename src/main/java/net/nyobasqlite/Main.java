package net.nyobasqlite;

import net.nyobasqlite.database.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlite:src/main/java/net/nyobasqlite/database/userdb.db";

        try {
            Connection connection = DatabaseConnection.getDataSource().getConnection();
            String sql = "select rowid, * from users";


            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("rowid");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println(id + " " + name + " | " + email);

            }

            statement.close();
            connection.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("error in jdbc : " + e.getMessage());
            e.printStackTrace();
        }

    }
}
