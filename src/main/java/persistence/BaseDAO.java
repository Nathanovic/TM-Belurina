package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Database12#");
    }
}
