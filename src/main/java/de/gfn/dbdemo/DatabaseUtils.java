package de.gfn.dbdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseUtils {

    private static final String DSN = "jdbc:sqlite:data.db";

    private DatabaseUtils() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DSN);
    }
}
