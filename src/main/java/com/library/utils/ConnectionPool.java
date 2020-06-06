package com.library.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.RunScript;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static final String DB_URL = "jdbc:h2:mem:Library";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private final static Logger log = LogManager.getLogger();
    private static DataSource dataSource;

    public ConnectionPool() {
        try {
            dataSource = JdbcConnectionPool.create(DB_URL, USER, PASSWORD);
            InputStream create = ConnectionPool.class.getResourceAsStream("/data/CreateDB.sql");
            InputStream init = ConnectionPool.class.getResourceAsStream("/data/InitDB.sql");
            RunScript.execute(dataSource.getConnection(), new InputStreamReader(create));
            RunScript.execute(dataSource.getConnection(), new InputStreamReader(init));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private static class ConnectionPoolHolder {
        public static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }


}
