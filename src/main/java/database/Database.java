package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:sqlite:database/ProjektParkolas.db";

    public static Connection connect() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "Az SQLite JDBC driver nem található! " +
                            "Ellenőrizd a Maven dependency-t.", e
            );
        }

        return DriverManager.getConnection(URL);
    }
}
