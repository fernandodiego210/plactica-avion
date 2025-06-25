package de;

public class principal {
	package de;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.ArrayList;

	public class j extends JPanel implements ActionListener, KeyListener {
	 
		private static final long serialVersionUID = 1L;
		private int naveX = 200;
	    private int naveY = 300;
	    private ArrayList<Rectangle> balas = new ArrayList<>();
	    private ArrayList<Rectangle> enemigos = new ArrayList<>();
	    private Timer timer;
	    private boolean izquierda, derecha, arriba, abajo, disparo;
	    private int puntos = 0;
}
