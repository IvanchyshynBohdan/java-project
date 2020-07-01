package com.library.dao;

import com.library.model.Member;
import com.library.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberDao implements Dao<Member> {

    private final static Logger log = LogManager.getLogger();

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Member";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("Id"));
                member.setFirstName(resultSet.getString("Name"));
                member.setLastName(resultSet.getString("Surname"));
                member.setPassword(resultSet.getString("Password"));
                member.setPhone(resultSet.getString("Phone"));
                member.setEmail(resultSet.getString("Email"));
                member.setCity(resultSet.getString("City"));
                members.add(member);
            }
            log.info("Find all members successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
            return null;
        }
        return members;
    }

    @Override
    public Member findById(Integer id) {
        String query = "SELECT * FROM Member WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(id);
                member.setFirstName(resultSet.getString("Name"));
                member.setLastName(resultSet.getString("Surname"));
                member.setPassword(resultSet.getString("Password"));
                member.setPhone(resultSet.getString("Phone"));
                member.setEmail(resultSet.getString("Email"));
                member.setCity(resultSet.getString("City"));
                return member;
            }
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    public Member findByLastName(String lastName) {
        String query = "SELECT * FROM Member WHERE Surname = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("Id"));
                member.setFirstName(resultSet.getString("Name"));
                member.setLastName(lastName);
                member.setPassword(resultSet.getString("Password"));
                member.setPhone(resultSet.getString("Phone"));
                member.setEmail(resultSet.getString("Email"));
                member.setCity(resultSet.getString("City"));
                return member;
            }
            log.info("Find member with surname: " + lastName + " successfully done");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Member WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Member can't be deleted");
            }
            log.info("Member with id: " + id + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Member member) {
        String query = "DELETE FROM Member WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, member.getId());
            if (preparedStatement.executeUpdate() == 0) {
                log.error("Member can't be deleted");
            }
            log.info("Member " + member.getLastName() + " was deleted");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Member member) {
        String query = "UPDATE Member SET Name = ?, Surname = ?, Password = ?, Phone = ?, Email = ?, City = ?" +
                "WHERE Id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setString(3, member.getPassword());
            preparedStatement.setString(4, member.getPhone());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getCity());
            preparedStatement.setInt(7, member.getId());
            if (preparedStatement.executeUpdate() == 0){
                log.error("Member can't be updated");
            }
            log.info("Member " + member.getLastName() + " was updated");
            return true;
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return false;
    }

    @Override
    public Member create(Member member) {
        String InsertSql = "INSERT INTO Member (Name, Surname, Password, Phone, Email, City) VALUES (?,?,?,?,?,?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setString(3, member.getPassword());
            preparedStatement.setString(4, member.getPhone());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getCity());
            if(preparedStatement.executeUpdate() == 0){
                log.error("Can't create member");
            }
            log.info("Member " + member.getLastName() + " was created");
        } catch (SQLException e) {
            log.error("Message: {}", e.getMessage());
        }
        return null;
    }
}
