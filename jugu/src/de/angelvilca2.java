package de;

public class angelvilca2 {

	//Configuración del Fondo y Ventana
		public j() {
		    setPreferredSize(new Dimension(400, 500)); // Tamaño 400x500 píxeles
		    setBackground(Color.BLACK);                // Fondo negro
		    setFocusable(true);                       // Para capturar teclas
		    addKeyListener(this);                     // Escuchar eventos de teclado
		    
		    timer = new Timer(20, this);              // Timer cada 20ms (50 FPS)
		    timer.start();
		}
	}

}
