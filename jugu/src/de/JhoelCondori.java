package de;

public class JhoelCondori {
	public class control {
		// Cuando se presiona una tecla:
		if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) derecha = true;
		if (e.getKeyCode() == KeyEvent.VK_UP) arriba = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) abajo = true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) disparo = true;

		// Cuando se suelta una tecla:
		if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = false;
		// ... etc
	}

}
