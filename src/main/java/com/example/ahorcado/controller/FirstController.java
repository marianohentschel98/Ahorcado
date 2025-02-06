package com.example.ahorcado.controller;

import com.example.ahorcado.modelo.Modelo;
import com.example.ahorcado.utils.Constantes;
import com.example.ahorcado.utils.PantallaUtils;
import data.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

/**
 * Controlador para la pantalla principal del juego del ahorcado.
 * Gestiona la interacción del usuario, el estado del juego y la actualización de la interfaz gráfica.
 */
public class FirstController {
    @FXML
    private Label lblEstado, lblIntentos, lblResultado, lblBienvenido;
    @FXML
    private TextField txtLetra, txtNuevaPalabra;
    @FXML
    private Button btnIntentar, btnReiniciar, btnLetrasUtilizadas, btnAgregarPalabra, btnEliminarPalabra;
    @FXML
    private ImageView imgAhorcado;

    private Modelo modelo;
    private DatabaseManager dbManager;

    public FirstController() {
        this.dbManager = new DatabaseManager(); // Inicializar el gestor de la base de datos
    }

    public void setNombreUsuario(String nombreUsuario) {
        lblBienvenido.setText("Bienvenido, " + nombreUsuario + "!");
    }

    public void iniciarJuego() {
        modelo = new Modelo(6);
        actualizarInterfaz();
        actualizarDibujo();
        lblResultado.setText("");
        btnIntentar.setDisable(false);
    }

    @FXML
    private void handleIntentar() {
        String input = txtLetra.getText().trim();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            lblResultado.setText("Introduce una letra válida.");
            return;
        }

        char letra = input.charAt(0);
        if (modelo.intentarLetra(letra)) {
            lblResultado.setText("¡Letra acertada!");
        } else {
            lblResultado.setText(modelo.getIntentosRestantes() > 0 ? "Letra incorrecta." : "¡No quedan intentos!");
        }

        txtLetra.clear();
        actualizarInterfaz();
        actualizarDibujo();

        if (modelo.haGanado()) {
            lblResultado.setText("¡Ganaste! La palabra era: " + modelo.getPalabra());
            btnIntentar.setDisable(true);
        } else if (modelo.haPerdido()) {
            lblResultado.setText("¡Perdiste! La palabra era: " + modelo.getPalabra());
            btnIntentar.setDisable(true);
        }
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

    @FXML
    private void handleReiniciar() {
        iniciarJuego();
    }

    @FXML
    private void handleLetrasUtilizadas() {
        Set<Character> letrasIntentadas = modelo.getLetrasIntentadas();
        String letras = letrasIntentadas.stream()
                .sorted()
                .map(String::valueOf)
                .reduce((a, b) -> a + ", " + b)
                .orElse("No se han utilizado letras.");
        mostrarAlerta("Letras Utilizadas", "Letras utilizadas: " + letras);
    }

    private void actualizarInterfaz() {
        lblEstado.setText("Palabra: " + modelo.getEstado());
        lblIntentos.setText("Intentos restantes: " + modelo.getIntentosRestantes());
    }

    private void actualizarDibujo() {
        int intentosFallidos = 6 - modelo.getIntentosRestantes();
        String rutaImagen = "/com/example/ahorcado/imagenes/ahorcado" + intentosFallidos + ".png";
        URL urlImagen = getClass().getResource(rutaImagen);

        if (urlImagen != null) {
            imgAhorcado.setImage(new Image(urlImagen.toString()));
        } else {
            System.err.println("No se pudo cargar la imagen: " + rutaImagen);
        }
    }
}