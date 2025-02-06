package com.example.ahorcado;

import com.example.ahorcado.controller.MenuController;
import com.example.ahorcado.utils.PantallaUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación del juego del ahorcado.
 * Inicia la aplicación y muestra la pantalla del menú principal.
 */
public class Main extends Application {

    /**
     * Método principal de JavaFX que se ejecuta al iniciar la aplicación.
     * Carga y muestra la pantalla del menú principal.
     *
     * @param stage la ventana principal de la aplicación
     * @throws Exception si ocurre un error al cargar la pantalla del menú
     */
    @Override
    public void start(Stage stage) throws Exception {
        new PantallaUtils().showEstaPantalla(stage, "menu.fxml", "Menú Principal", 400, 300);
    }

    /**
     * Método principal de la aplicación.
     * Llama al método {@link Application#launch(String...)} para iniciar la aplicación JavaFX.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}