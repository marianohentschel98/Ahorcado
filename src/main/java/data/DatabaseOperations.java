package data;

import java.util.List;

public interface DatabaseOperations {
    void insertarPalabra(String palabra);
    List<String> obtenerPalabras();
    void eliminarPalabra(String palabra);
}
