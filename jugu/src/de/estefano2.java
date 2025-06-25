package de;

public class estefano2 {

	 //Crear Bala
	if (disparo) {
	    balas.add(new Rectangle(naveX-2, naveY-20, 4, 10)); // Bala 4x10 píxeles
	    disparo = false;
	}//Mover Balas
	for (int i = 0; i < balas.size(); i++) {
	    balas.get(i).y -= 5;              // Mueve bala hacia arriba
	    if (balas.get(i).y < 0) {         // Si sale de pantalla
	        balas.remove(i);              // Elimina la bala
	        i--;
	    }
	}
	//Dibujar Balas
	g.setColor(Color.YELLOW);             // Color amarillo
	for (Rectangle bala : balas) {
	    g.fillRect(bala.x, bala.y, bala.width, bala.height);
	}
	}

}
