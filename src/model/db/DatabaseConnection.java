package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasse für die Datenbankverbindung
 */
public class DatabaseConnection {
    private static Connection instance;
    private static final String URL = "jdbc:mysql://caworks-sl.de:3306/booking";

    private DatabaseConnection() {
    }

    /**
     * Gibt die Instanz der Datenbankverbindung zurück
     *
     * @return Instanz
     */
    public static Connection getInstance() {
        if (instance == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instance = DriverManager.getConnection(URL, DatabaseCredentials.USERNAME, DatabaseCredentials.PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
