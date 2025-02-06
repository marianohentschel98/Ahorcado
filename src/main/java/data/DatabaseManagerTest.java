package data;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DatabaseManagerTest {
    private static DatabaseManager dbManager;

    @BeforeEach
    void setUp() {
        dbManager = new DatabaseManager();
    }

    @Test
    void testConexionBaseDeDatos() {
        assertDoesNotThrow(() -> new DatabaseManager(), "Error al conectar con SQLite.");
    }

    @Test
    void testInsertarYObtenerPalabra() {
        dbManager.insertarPalabra("PRUEBA");
        List<String> palabras = dbManager.obtenerPalabras();
        assertTrue(palabras.contains("PRUEBA"), "La palabra 'PRUEBA' debería estar en la base de datos.");
    }

    @Test
    void testEliminarPalabra() {
        dbManager.insertarPalabra("BORRAR");
        dbManager.eliminarPalabra("BORRAR");
        List<String> palabras = dbManager.obtenerPalabras();
        assertFalse(palabras.contains("BORRAR"), "La palabra 'BORRAR' debería haber sido eliminada.");
    }
}