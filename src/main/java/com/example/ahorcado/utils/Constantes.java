package com.example.ahorcado.utils;

/**
 * Enumeración que contiene constantes utilizadas en la aplicación.
 * Define las rutas de los archivos FXML y los títulos de las ventanas de la aplicación.
 */
public enum Constantes {

    /** Ruta del archivo FXML para la pantalla del menú principal. */
    PAGINA_MENU("menu.fxml"),

    /** Ruta del archivo FXML para la pantalla del juego. */
    PAGINA_JUEGO("first.fxml"),

    /** Título de la ventana del menú principal. */
    TITULO_MENU("Menú Principal"),

    /** Título de la ventana del juego. */
    TITULO_JUEGO("Juego del Ahorcado");

    private final String descripcion; // descripción asociada a la constante

    /**
     * Constructor de la enumeración.
     * Asocia una descripción a cada constante.
     *
     * @param descripcion la descripción asociada a la constante
     */
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la descripción asociada a la constante.
     *
     * @return la descripción de la constante
     */
    public String getDescripcion() {
        return descripcion;
    }
}
