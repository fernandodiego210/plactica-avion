package de;

public class fernandosucasaire {
	private int naveX = 200, naveY = 300;  // Posición de la nave
	private ArrayList<Rectangle> balas = new ArrayList<>();    // Lista de balas
	private ArrayList<Rectangle> enemigos = new ArrayList<>(); // Lista de enemigos
	private boolean izquierda, derecha, arriba, abajo, disparo; // Controles
	private int puntos = 0; // Puntaje
//Crear Enemigos Iniciales:
	for (int i = 0; i < 5; i++) {
	    enemigos.add(new Rectangle(i * 70 + 20, 50, 30, 30)); // 5 enemigos en fila
	}
//Mover Enemigos:
	for (int i = 0; i < enemigos.size(); i++) {
	    enemigos.get(i).y += 2;           // Mueve hacia abajo
	    if (enemigos.get(i).y > 500) {    // Si sale de pantalla
	        enemigos.remove(i);           // Elimina enemigo
	        i--;
	    }
	}
//Generar Enemigos Aleatorios:
	if (Math.random() < 0.02) {           // 2% probabilidad cada frame
	    enemigos.add(new Rectangle((int)(Math.random() * 350) + 20, 0, 30, 30));
	}
//Dibujar Enemigos:
	g.setColor(Color.RED);                // Color rojo
	for (Rectangle enemigo : enemigos) {
	    g.fillRect(enemigo.x, enemigo.y, enemigo.width, enemigo.height);
	}
}
