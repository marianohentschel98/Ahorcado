package com.example.ahorcado.utils;

import com.example.ahorcado.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Clase de utilidad para gestionar las pantallas de la aplicación.
 * Proporciona métodos para mostrar y cerrar ventanas en la aplicación.
 */
public class PantallaUtils {

    /**
     * Muestra una nueva pantalla en la aplicación.
     * Carga un archivo FXML, establece la escena en la ventana proporcionada, y ajusta su tamaño.
     *
     * @param stage la ventana principal donde se cargará la pantalla
     * @param vista el nombre del archivo FXML a cargar
     * @param titulo el título de la ventana
     * @param ancho el ancho de la ventana
     * @param alto el alto de la ventana
     * @return una instancia del objeto {@link FXMLLoader} utilizado para cargar el archivo FXML
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public FXMLLoader showEstaPantalla(Stage stage, String vista, String titulo, int ancho, int alto) throws IOException {
        URL fxmlLocation = getClass().getResource("/com/example/ahorcado/vistas/" + vista);

        // Verifica si la ruta del archivo es correcta
        if (fxmlLocation == null) {
            throw new IOException("No se encontró el archivo FXML: " + vista);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);
        stage.setTitle(titulo);

        // Establece la nueva escena
        stage.setScene(scene);

        // Ajusta el tamaño de la ventana según la pantalla
        if (vista.equals("first.fxml")) {
            stage.setWidth(600); // Ancho personalizado para la segunda pantalla
            stage.setHeight(600); // Alto personalizado para la segunda pantalla
        } else {
            stage.setWidth(500); // Ancho predeterminado
            stage.setHeight(600); // Alto predeterminado
        }

        // Forzar la actualización del tamaño
        stage.setResizable(false); // Desactiva el redimensionamiento temporalmente
        stage.setResizable(true);  // Reactívalo para forzar el ajuste
        stage.show();

        return fxmlLoader;
    }

    /**
     * Cierra la pantalla actual.
     * Obtiene la ventana asociada al botón que disparó la acción y la cierra.
     *
     * @param botonDelAction el botón que disparó el evento de acción
     * @return la instancia del {@link Stage} que fue cerrada
     */
    public Stage cerrarEstaPantalla(Button botonDelAction) {
        Stage stageAhora = (Stage) botonDelAction.getScene().getWindow();
        stageAhora.close();
        return stageAhora;
    }
}
