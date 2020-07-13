package com.library.dao;

import com.library.model.Librarian;
import com.library.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDao implements Dao<Librarian> {

    private final static Logger log = LogManager.getLogger();

    @Override
    public List<Librarian> findAll() {
        List<Librarian> librarianList = new ArrayList<>();
        String query = "SELECT * FROM Librarian";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("Id"));
                librarian.setFirstName(resultSet.getString("Name"));
                librarian.setLastName(resultSet.getString("Surname"));
                librarian.setPassword(resultSet.getString("Password"));
                librarian.setPhone(resultSet.getString("Phone"));
                librarian.setEmail(resultSet.getString("Email"));
                librarian.setExperience(resultSet.getInt("Experience"));
                librarianList.add(librarian);
            }
            log.info("Find all librarian successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return librarianList;
    }

    @Override
    public Librarian findById(Integer id) {
        String query = "SELECT * FROM Librarian WHERE id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setId(id);
                librarian.setFirstName(resultSet.getString("Name"));
                librarian.setLastName(resultSet.getString("Surname"));
                librarian.setPassword(resultSet.getString("Password"));
                librarian.setPhone(resultSet.getString("Phone"));
                librarian.setEmail(resultSet.getString("Email"));
                librarian.setExperience(resultSet.getInt("Experience"));
                return librarian;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    public Librarian findByLastName(String lastName) {
        String query = "SELECT * FROM Librarian WHERE Surname = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("Id"));
                librarian.setFirstName(resultSet.getString("Name"));
                librarian.setLastName(lastName);
                librarian.setPassword(resultSet.getString("Password"));
                librarian.setPhone(resultSet.getString("Phone"));
                librarian.setEmail(resultSet.getString("Email"));
                librarian.setExperience(resultSet.getInt("Experience"));
                return librarian;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Librarian WHERE id=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Librarian can't be deleted");
            }
            log.info("Librarian with id: " + id + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Librarian librarian) {
        String query = "DELETE FROM Librarian WHERE Id=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, librarian.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Librarian can't be deleted");
            }
            log.info("Librarian " + librarian.getLastName() + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Librarian librarian) {
        String query = "UPDATE Librarian SET Name = ?, Surname = ?, Password = ?, Phone = ?, Email = ?, Experience = ?" +
                "WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, librarian.getFirstName());
            preparedStatement.setString(2, librarian.getLastName());
            preparedStatement.setString(3, librarian.getPassword());
            preparedStatement.setString(4, librarian.getPhone());
            preparedStatement.setString(5, librarian.getEmail());
            preparedStatement.setInt(6, librarian.getExperience());
            preparedStatement.setInt(7, librarian.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Librarian can't be updated");
            }
            log.info("Librarian " + librarian.getLastName() + " was updated");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    public Librarian validate(String email, String password) {
        String query = "SELECT * FROM Librarian WHERE Email = ? AND Password = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("Id"));
                librarian.setFirstName(resultSet.getString("Name"));
                librarian.setLastName(resultSet.getString("Surname"));
                librarian.setPassword(password);
                librarian.setEmail(email);
                librarian.setExperience(resultSet.getInt("Experience"));
                return librarian;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public Librarian create(Librarian librarian) {
        String InsertSql = "INSERT INTO Librarian (Name, Surname, Password, Phone, Email, Experience) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, librarian.getFirstName());
            preparedStatement.setString(2, librarian.getLastName());
            preparedStatement.setString(3, librarian.getPassword());
            preparedStatement.setString(4, librarian.getPhone());
            preparedStatement.setString(5, librarian.getEmail());
            preparedStatement.setInt(6, librarian.getExperience());

            if (preparedStatement.executeUpdate() == 0) {
                log.error("Can't create librarian");
            }
            log.info("Librarian " + librarian.getLastName() + " was created");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }
}
