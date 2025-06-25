package de;
//pantalla
private int naveX = 200, naveY = 300;  // Posición de la nave
private ArrayList<Rectangle> balas = new ArrayList<>();    // Lista de balas
private ArrayList<Rectangle> enemigos = new ArrayList<>(); // Lista de enemigos
private boolean izquierda, derecha, arriba, abajo, disparo; // Controles
private int puntos = 0; // Puntaje