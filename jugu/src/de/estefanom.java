package de;

public class estefanom {

	for (int i = 0; i < balas.size(); i++) {
	    for (int j = 0; j < enemigos.size(); j++) {
	        if (balas.get(i).intersects(enemigos.get(j))) { // Si chocan
	            balas.remove(i);        // Elimina bala
	            enemigos.remove(j);     // Elimina enemigo
	            puntos += 10;          // Suma puntos
	            i--;
	            break;
	        }
	    }
	 }
}
