package Database;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseConnection {

    private static final Path DB_FILE = Path.of("database", "projektParkolas.db");

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        Path dbPath = DB_FILE.toAbsolutePath().normalize();
        if (!Files.isRegularFile(dbPath)) {
            throw new SQLException("Az adatbázisfájl nem található: " + dbPath);
        }

        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        }
        return connection;
    }
}
