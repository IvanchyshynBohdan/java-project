package com.library.dao;

import com.library.model.Users;
import com.library.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao implements Dao<Users> {

    private final static Logger log = LogManager.getLogger();

    @Override
    public List<Users> findAll() {
        List<Users> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                String password = resultSet.getString("Password");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                String city = resultSet.getString("City");

                Users user = new Users(id, name, surname, password, phone, email, city);
                users.add(user);
            }
            log.info("Find all users successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return users;
    }

    @Override
    public Users findById(Integer Id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Users user) {
        String deleteSql = "DELETE FROM Users WHERE Id=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            log.info("User " + user.getFirstName() + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Users user) {
        return false;
    }

    @Override
    public Users create(Users user) {
        String InsertSql = "INSERT INTO Users VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getCity());

            preparedStatement.executeUpdate();
            log.info("User " + user.getFirstName() + " was created");
        }catch (SQLException e){
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }
}
