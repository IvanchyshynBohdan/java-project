package com.library.dao;

import com.library.model.Category;
import com.library.model.Member;
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

public class PublisherDao implements Dao<Publisher> {

    private final static Logger log = LogManager.getLogger();

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publisherList = new ArrayList<>();
        String query = "SELECT * FROM Publisher";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("Id"));
                publisher.setName(resultSet.getString("Name"));
                publisher.setSite(resultSet.getString("Site"));
                publisherList.add(publisher);
            }
            log.info("Find all publisher successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return publisherList;
    }

    @Override
    public Publisher findById(Integer id) {
        String query = "SELECT * FROM Publisher WHERE id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("Id"));
                publisher.setName(resultSet.getString("Name"));
                publisher.setSite(resultSet.getString("Site"));
                return publisher;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Publisher WHERE id=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Publisher can't be deleted");
            }
            log.info("Publisher with id: " + id + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Publisher publisher) {
        String query = "DELETE FROM Publisher WHERE id=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, publisher.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Publisher can't be deleted");
            }
            log.info("Publisher " + publisher.getName() + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Publisher publisher) {
        String query = "UPDATE Publisher SET Name = ?, Site = ? WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setString(2, publisher.getSite());
            preparedStatement.setInt(3, publisher.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Publisher can't be updated");
            }
            log.info("Publisher " + publisher.getName() + " was updated");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public Publisher create(Publisher publisher) {
        String InsertSql = "INSERT INTO Publisher (Name, Site) VALUES (?,?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setString(2, publisher.getSite());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Can't create member");
            }
            log.info("Publisher " + publisher.getName() + " was created");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }
}
