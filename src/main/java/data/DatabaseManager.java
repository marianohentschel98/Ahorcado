package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager implements DatabaseOperations {
    private static final String URL = "jdbc:sqlite:src/main/java/data/ahorcado.db";

    public DatabaseManager() {
        conectar();
    }

    // Conexión a la base de datos
    private void conectar() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("Conexión a SQLite establecida.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con SQLite: " + e.getMessage());
        }
    }

    @Override
    public void insertarPalabra(String palabra) {
        String sql = "INSERT INTO palabras (palabra) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, palabra.toUpperCase());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar palabra: " + e.getMessage());
        }
    }

    @Override
    public List<String> obtenerPalabras() {
        List<String> palabras = new ArrayList<>();
        String sql = "SELECT palabra FROM palabras";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                palabras.add(rs.getString("palabra"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener palabras: " + e.getMessage());
        }
        return palabras;
    }

    @Override
    public void eliminarPalabra(String palabra) {
        String sql = "DELETE FROM palabras WHERE palabra = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, palabra.toUpperCase());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar palabra: " + e.getMessage());
        }
    }
}
