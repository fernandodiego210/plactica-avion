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
    
    public j() {
        setPreferredSize(new Dimension(400, 500));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        
        timer = new Timer(20, this);
        timer.start();
        
        // Crear algunos enemigos
        for (int i = 0; i < 5; i++) {
            enemigos.add(new Rectangle(i * 70 + 20, 50, 30, 30));
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Dibujar nave (triángulo azul)
        g.setColor(Color.CYAN);
        int[] x = {naveX, naveX-15, naveX+15};
        int[] y = {naveY-15, naveY+15, naveY+15};
        g.fillPolygon(x, y, 3);
        
        // Dibujar balas (rectángulos amarillos)
        g.setColor(Color.YELLOW);
        for (Rectangle bala : balas) {
            g.fillRect(bala.x, bala.y, bala.width, bala.height);
        }
        
        // Dibujar enemigos (rectángulos rojos)
        g.setColor(Color.RED);
        for (Rectangle enemigo : enemigos) {
            g.fillRect(enemigo.x, enemigo.y, enemigo.width, enemigo.height);
        }
        
        // Mostrar puntos
        g.setColor(Color.WHITE);
        g.drawString("Puntos: " + puntos, 10, 20);
        g.drawString("Flechas = mover, ESPACIO = disparar", 10, 480);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mover nave
        if (izquierda && naveX > 15) naveX -= 3;
        if (derecha && naveX < 385) naveX += 3;
        if (arriba && naveY > 15) naveY -= 3;
        if (abajo && naveY < 485) naveY += 3;
        
        // Crear bala
        if (disparo) {
            balas.add(new Rectangle(naveX-2, naveY-20, 4, 10));
            disparo = false;
        }
        
        // Mover balas hacia arriba
        for (int i = 0; i < balas.size(); i++) {
            balas.get(i).y -= 5;
            if (balas.get(i).y < 0) {
                balas.remove(i);
                i--;
            }
        }
        
        // Mover enemigos hacia abajo
        for (int i = 0; i < enemigos.size(); i++) {
            enemigos.get(i).y += 2;
            if (enemigos.get(i).y > 500) {
                enemigos.remove(i);
                i--;
            }
        }
        
        // Verificar colisiones bala-enemigo
        for (int i = 0; i < balas.size(); i++) {
            for (int j = 0; j < enemigos.size(); j++) {
                if (balas.get(i).intersects(enemigos.get(j))) {
                    balas.remove(i);
                    enemigos.remove(j);
                    puntos += 10;
                    i--;
                    break;
                }
            }
        }
        
        // Agregar enemigos nuevos aleatoriamente
        if (Math.random() < 0.02) {
            enemigos.add(new Rectangle((int)(Math.random() * 350) + 20, 0, 30, 30));
        }
        
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) derecha = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) arriba = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) abajo = true;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) disparo = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) derecha = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) arriba = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) abajo = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Nave vs Enemigos");
        j juego = new j();
        
        ventana.add(juego);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}