package com.library.dao;

import com.library.model.Category;
import com.library.model.Publisher;
import com.library.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Dao<Category> {

    private final static Logger log = LogManager.getLogger();

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setName(resultSet.getString("Name"));
                categoryList.add(category);
            }
            log.info("Find all category successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return categoryList;
    }

    @Override
    public Category findById(Integer id) {
        String query = "SELECT * FROM Category WHERE id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(id);
                category.setName(resultSet.getString("Name"));
                return category;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Category WHERE id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Category can't be deleted");
            }
            log.info("Category with id: " + id + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Category category) {
        String query = "DELETE FROM Category WHERE Id=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, category.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Category can't be deleted");
            }
            log.info("Category " + category.getName() + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        String query = "UPDATE Category SET Name = ? WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Category can't be updated");
            }
            log.info("Category " + category.getName() + " was updated");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public Category create(Category category) {
        String InsertSql = "INSERT INTO Category (Name) VALUES (?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, category.getName());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Can't create category");
            }
            log.info("Category " + category.getName() + " was created");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }
}
