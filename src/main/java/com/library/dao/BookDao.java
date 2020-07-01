package com.library.dao;

import com.library.model.Books;
import com.library.model.Category;
import com.library.model.Librarian;
import com.library.model.Publisher;
import com.library.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDao implements Dao<Books> {

    private final static Logger log = LogManager.getLogger();

    @Override
    public List<Books> findAll() {
        List<Books> bookList = new ArrayList<>();
        String query = "SELECT Books.Id, Title, Author, Category.Name, Publisher.Name, Price FROM Books " +
                "JOIN Publisher ON Publisher.Id = Books.Id_publisher " +
                "JOIN Category ON Category.Id = Books.Id_category ";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Books books = new Books();
                books.setId(resultSet.getInt("Id"));
                books.setTitle(resultSet.getString("Title"));
                books.setAuthor(resultSet.getString("Author"));
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setName(resultSet.getString("Name"));
                books.setCategory(category);
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("Id"));
                publisher.setName(resultSet.getString("Name"));
                books.setPublisher(publisher);
                books.setPrice(resultSet.getFloat("Price"));
                bookList.add(books);
            }
            log.info("Find all books successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return bookList;
    }

    @Override
    public Books findById(Integer id) {
        String query = "SELECT Books.Id, Title, Author, Category.Name, Publisher.Name, Price FROM Books " +
                "JOIN Publisher ON Publisher.Id = Books.Id_publisher " +
                "JOIN Category ON Category.Id = Books.Id_category WHERE Books.Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Books books = new Books();
                books.setId(id);
                books.setTitle(resultSet.getString("Title"));
                books.setAuthor(resultSet.getString("Author"));
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setName(resultSet.getString("Name"));
                books.setCategory(category);
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("Id"));
                publisher.setName(resultSet.getString("Name"));
                books.setPublisher(publisher);
                books.setPrice(resultSet.getFloat("Price"));
                return books;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    public Books findByTitle(String title) {
        String query = "SELECT Books.Id, Title, Author, Category.Name, Publisher.Name, Price FROM Books " +
                "JOIN Publisher ON Publisher.Id = Books.Id_publisher " +
                "JOIN Category ON Category.Id = Books.Id_category WHERE Books.Title = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Books books = new Books();
                books.setId(resultSet.getInt("Id"));
                books.setTitle(title);
                books.setAuthor(resultSet.getString("Author"));
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setName(resultSet.getString("Name"));
                books.setCategory(category);
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("Id"));
                publisher.setName(resultSet.getString("Name"));
                books.setPublisher(publisher);
                books.setPrice(resultSet.getFloat("Price"));
                return books;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Books WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Book can't be deleted");
            }
            log.info("Book with id: " + id + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Books books) {
        String query = "DELETE FROM Books WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, books.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Book can't be deleted");
            }
            log.info("Book \"" + books.getTitle() + "\" was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Books books) {
        String query = "UPDATE Books SET Title = ?, Author = ?, Id_category = ?, Id_publisher = ?, Price = ? WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, books.getTitle());
            preparedStatement.setString(2, books.getAuthor());
            preparedStatement.setInt(3, books.getCategory().getId());
            preparedStatement.setInt(4, books.getPublisher().getId());
            preparedStatement.setFloat(5, books.getPrice());
            preparedStatement.setInt(6, books.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Book can't be updated");
            }
            log.info("Book \"" + books.getTitle() + "\" was updated");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public Books create(Books books) {
        String query = "INSERT INTO Books (Title, Author, Id_category, Id_publisher, Price) VALUES (?,?,?,?,?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, books.getTitle());
            preparedStatement.setString(2, books.getAuthor());
            preparedStatement.setInt(3, books.getCategory().getId());
            preparedStatement.setInt(4, books.getPublisher().getId());
            preparedStatement.setFloat(5, books.getPrice());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Can't create book");
            }
            log.info("Book \"" + books.getTitle() + "\" was created");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }
}
