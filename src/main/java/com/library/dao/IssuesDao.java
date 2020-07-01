package com.library.dao;

import com.library.model.Books;
import com.library.model.Issues;
import com.library.model.Librarian;
import com.library.model.Member;
import com.library.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssuesDao implements Dao<Issues> {

    private final static Logger log = LogManager.getLogger();

    @Override
    public List<Issues> findAll() {
        List<Issues> issuesList = new ArrayList<>();
        String query = "SELECT Issues.Id, Books.Title, Member.Surname, Librarian.Surname, Taken_date, Brought_date " +
                "FROM Issues JOIN Books ON Books.Id = Issues.Id_book " +
                "JOIN Member ON Member.Id = Issues.Id_member " +
                "JOIN Librarian ON Librarian.Id = Issues.Id_librarian ";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Issues issues = new Issues();
                issues.setId(resultSet.getInt("Id"));
                Books books = new Books();
                books.setId(resultSet.getInt("Id"));
                books.setTitle(resultSet.getString("Title"));
                issues.setBooks(books);
                Member member = new Member();
                member.setId(resultSet.getInt("Id"));
                member.setLastName(resultSet.getString("Surname"));
                issues.setMember(member);
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("Id"));
                librarian.setLastName(resultSet.getString("Surname"));
                issues.setLibrarian(librarian);
                issues.setTakenDate(resultSet.getDate("Taken_date"));
                issues.setTakenDate(resultSet.getDate("Brought_date"));
                issuesList.add(issues);
            }
            log.info("Find all issues successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return issuesList;
    }

    @Override
    public Issues findById(Integer id) {
        String query = "SELECT Issues.Id, Books.Title, Member.Surname, Librarian.Surname, Taken_date, Brought_date " +
                "FROM Issues JOIN Books ON Books.Id = Issues.Id_book " +
                "JOIN Member ON Member.Id = Issues.Id_member " +
                "JOIN Librarian ON Librarian.Id = Issues.Id_librarian WHERE Issues.Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Issues issues = new Issues();
                issues.setId(id);
                Books books = new Books();
                books.setId(resultSet.getInt("Id"));
                books.setTitle(resultSet.getString("Title"));
                issues.setBooks(books);
                Member member = new Member();
                member.setId(resultSet.getInt("Id"));
                member.setLastName(resultSet.getString("Surname"));
                issues.setMember(member);
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("Id"));
                librarian.setLastName(resultSet.getString("Surname"));
                issues.setLibrarian(librarian);
                issues.setTakenDate(resultSet.getDate("Taken_date"));
                issues.setTakenDate(resultSet.getDate("Brought_date"));
                return issues;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return null;
    }

    public Issues findByTakenDate(Date date) {
        String query = "SELECT Issues.Id, Books.Title, Member.Surname, Librarian.Surname, Taken_date, Brought_date " +
                "FROM Issues JOIN Books ON Books.Id = Issues.Id_book " +
                "JOIN Member ON Member.Id = Issues.Id_member " +
                "JOIN Librarian ON Librarian.Id = Issues.Id_librarian WHERE Issues.Taken_date = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Issues issues = new Issues();
                issues.setId(resultSet.getInt("Id"));
                Books books = new Books();
                books.setId(resultSet.getInt("Id"));
                books.setTitle(resultSet.getString("Title"));
                issues.setBooks(books);
                Member member = new Member();
                member.setId(resultSet.getInt("Id"));
                member.setLastName(resultSet.getString("Surname"));
                issues.setMember(member);
                Librarian librarian = new Librarian();
                librarian.setId(resultSet.getInt("Id"));
                librarian.setLastName(resultSet.getString("Surname"));
                issues.setLibrarian(librarian);
                issues.setTakenDate(date);
                issues.setTakenDate(resultSet.getDate("Brought_date"));
                return issues;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Issues WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Issue can't be deleted");
            }
            log.info("Issue with id: " + id + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Issues issues) {
        String query = "DELETE FROM Issues WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, issues.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Issue can't be deleted");
            }
            log.info("Issue for id member: " + issues.getMember().getId() + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Issues issues) {
        String query = "UPDATE Issues SET Id_book = ?, Id_member = ?, Id_librarian = ?, Taken_date = ?, Brought_date = ?"
                + "WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, issues.getBooks().getId());
            preparedStatement.setInt(2, issues.getMember().getId());
            preparedStatement.setInt(3, issues.getLibrarian().getId());
            preparedStatement.setDate(4, new Date(issues.getTakenDate().getTime()));
            preparedStatement.setObject(5, new Date(issues.getBroughtDate().getTime()) {
            });
            preparedStatement.setInt(6, issues.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Issue can't be updated");
            }
            log.info("Issue with id: " + issues.getId() + " was updated");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public Issues create(Issues issues) {
        String InsertSql = "INSERT INTO Issues (Id_book, Id_member, Id_librarian, Taken_date, Brought_date) " +
                "VALUES (?,?,?,?,?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setInt(1, issues.getBooks().getId());
            preparedStatement.setInt(2, issues.getMember().getId());
            preparedStatement.setInt(3, issues.getLibrarian().getId());
            preparedStatement.setDate(4, new Date(issues.getTakenDate().getTime()));
            preparedStatement.setDate(5, new Date(issues.getBroughtDate().getTime()));
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Can't create issue");
            }
            log.info("Issue with id: " + issues.getId() + " was created");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }
}

