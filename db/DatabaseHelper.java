package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:password_manager.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS credentials (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "service TEXT NOT NULL," +
                        "username TEXT NOT NULL," +
                        "password TEXT NOT NULL" +
                        ");";
                stmt.execute(sql);
                System.out.println("✅ Banco de dados conectado e tabela pronta.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar ou criar o banco de dados:");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}