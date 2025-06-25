package de;

public class angelvilca {
	//Dibujo de la Nave
	g.setColor(Color.CYAN);                    // Color cyan/azul claro
	int[] x = {naveX, naveX-15, naveX+15};     // Coordenadas X del triángulo
	int[] y = {naveY-15, naveY+15, naveY+15}; // Coordenadas Y del triángulo  
	g.fillPolygon(x, y, 3);                   // Dibuja triángulo (nave)

}
