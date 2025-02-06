package com.example.ahorcado.controller;

import com.example.ahorcado.utils.Constantes;
import com.example.ahorcado.utils.PantallaUtils;
import data.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para la pantalla del menú principal del juego del ahorcado.
 * Permite al usuario ingresar su nombre, iniciar el juego o cerrar la aplicación.
 */
public class MenuController {
    @FXML
    private TextField txtNombreUsuario, txtNuevaPalabra;
    @FXML
    private Button btnAgregarPalabra, btnEliminarPalabra;

    private DatabaseManager dbManager;

    public MenuController() {
        this.dbManager = new DatabaseManager(); // Inicializar el gestor de base de datos
    }

    @FXML
    private void handleIniciarJuego() throws IOException {
        String nombreUsuario = txtNombreUsuario.getText().trim();

        if (nombreUsuario.isEmpty()) {
            txtNombreUsuario.setPromptText("Por favor, introduce tu nombre.");
            txtNombreUsuario.setStyle("-fx-border-color: red; -fx-prompt-text-fill: red;");
            return;
        }

        Stage stage = (Stage) txtNombreUsuario.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ahorcado/vistas/first.fxml"));
        stage.getScene().setRoot(loader.load());

        FirstController controller = loader.getController();
        controller.setNombreUsuario(nombreUsuario);
        controller.iniciarJuego();
    }

    @FXML
    private void handleSalir() {
        Stage stage = (Stage) txtNombreUsuario.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAgregarPalabra() {
        String nuevaPalabra = txtNuevaPalabra.getText().trim().toUpperCase();
        if (nuevaPalabra.isEmpty()) {
            mostrarAlerta("Error", "La palabra no puede estar vacía.");
            return;
        }

        dbManager.insertarPalabra(nuevaPalabra);
        mostrarAlerta("Éxito", "Palabra añadida correctamente: " + nuevaPalabra);
        txtNuevaPalabra.clear();
    }

    @FXML
    private void handleEliminarPalabra() {
        String palabraEliminar = txtNuevaPalabra.getText().trim().toUpperCase();
        if (palabraEliminar.isEmpty()) {
            mostrarAlerta("Error", "Debes ingresar una palabra para eliminar.");
            return;
        }

        dbManager.eliminarPalabra(palabraEliminar);
        mostrarAlerta("Éxito", "Palabra eliminada correctamente: " + palabraEliminar);
        txtNuevaPalabra.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
