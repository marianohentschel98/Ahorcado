package com.example.ahorcado.modelo;

import data.DatabaseManager;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Modelo {
    private final String palabra;
    private final char[] estado;
    private int intentosRestantes;
    private final Set<Character> letrasIntentadas;
    private final DatabaseManager dbManager;

    public Modelo(int intentos) {
        this.dbManager = new DatabaseManager();
        this.palabra = elegirPalabraAleatoria().toUpperCase();
        this.estado = new char[palabra.length()];
        this.intentosRestantes = intentos;
        this.letrasIntentadas = new HashSet<>();

        for (int i = 0; i < estado.length; i++) {
            estado[i] = '_';
        }
    }

    private String elegirPalabraAleatoria() {
        List<String> palabras = dbManager.obtenerPalabras();
        if (palabras.isEmpty()) {
            throw new IllegalStateException("No hay palabras en la base de datos.");
        }
        Random random = new Random();
        return palabras.get(random.nextInt(palabras.size()));
    }

    public String getEstado() {
        return new String(estado);
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public boolean intentarLetra(char letra) {
        if (!Character.isLetter(letra)) {
            throw new IllegalArgumentException("Solo se permiten letras.");
        }

        letra = Character.toUpperCase(letra);

        if (letrasIntentadas.contains(letra)) {
            return false;
        }

        letrasIntentadas.add(letra);
        boolean acierto = false;

        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra && estado[i] == '_') {
                estado[i] = letra;
                acierto = true;
            }
        }

        if (!acierto) {
            intentosRestantes--;
        }

        return acierto;
    }

    public String getPalabra() {
        return palabra;
    }

    public boolean haGanado() {
        return palabra.equals(new String(estado));
    }

    public boolean haPerdido() {
        return intentosRestantes <= 0;
    }

    public Set<Character> getLetrasIntentadas() {
        return letrasIntentadas;
    }
}
