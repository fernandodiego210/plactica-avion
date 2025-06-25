package de;

import javax.swing.*;          // Para crear ventanas
import java.awt.*;              // Para gráficos y colores
import java.awt.event.*;        // Para eventos de teclado
import java.util.ArrayList;     // Para listas dinámicas

public class g extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    
	private int naveX = 200;        // Posición X de la nave
    private int naveY = 300;        // Posición Y de la nave
    private ArrayList<Rectangle> balas = new ArrayList<>();      // Lista de balas
    private ArrayList<Rectangle> enemigos = new ArrayList<>();   // Lista de enemigos
    private Timer timer;            // Controla la velocidad del juego
    private boolean izquierda, derecha, arriba, abajo, disparo;  // Estados de las teclas
    private int puntos = 0;         // Puntuación del jugador
    
    public g() {
        setPreferredSize(new Dimension(400, 500));  // Tamaño de la ventana
        setBackground(Color.BLACK);                 // Fondo negro
        setFocusable(true);                        // Permite recibir eventos de teclado
        addKeyListener(this);                      // Escucha las teclas
        
        timer = new Timer(20, this);               // Timer cada 20ms
        timer.start();                             // Inicia el juego
        
        // Crear enemigos iniciales
        for (int i = 0; i < 5; i++) {
            enemigos.add(new Rectangle(i * 70 + 20, 50, 30, 30));
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Limpia la pantalla y pinta el fondo negro
        
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
        
        // Mostrar texto en pantalla
        g.setColor(Color.WHITE);
        g.drawString("Puntos: " + puntos, 10, 20);
        g.drawString("Flechas = mover, ESPACIO = disparar", 10, 480);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mover nave según las teclas presionadas
        if (izquierda && naveX > 15) naveX -= 3;
        if (derecha && naveX < 385) naveX += 3;
        if (arriba && naveY > 15) naveY -= 3;
        if (abajo && naveY < 485) naveY += 3;
        
        // Crear bala al disparar
        if (disparo) {
            balas.add(new Rectangle(naveX-2, naveY-20, 4, 10));
            disparo = false;
        }
        
        // Mover balas hacia arriba
        for (int i = 0; i < balas.size(); i++) {
            balas.get(i).y -= 5;
            if (balas.get(i).y < 0) {  // Eliminar balas que salen de pantalla
                balas.remove(i);
                i--;
            }
        }
        
        // Mover enemigos hacia abajo
        for (int i = 0; i < enemigos.size(); i++) {
            enemigos.get(i).y += 2;
            if (enemigos.get(i).y > 500) {  // Eliminar enemigos que salen de pantalla
                enemigos.remove(i);
                i--;
            }
        }
        
        // Verificar colisiones bala-enemigo
        for (int i = 0; i < balas.size(); i++) {
            for (int j = 0; j < enemigos.size(); j++) {
                if (balas.get(i).intersects(enemigos.get(j))) {
                    balas.remove(i);    // Eliminar bala
                    enemigos.remove(j); // Eliminar enemigo
                    puntos += 10;       // Sumar puntos
                    i--;
                    break;
                }
            }
        }
        
        // Generar nuevos enemigos aleatoriamente
        if (Math.random() < 0.02) {
            enemigos.add(new Rectangle((int)(Math.random() * 350) + 20, 0, 30, 30));
        }
        
        repaint();  // Redibuja toda la pantalla
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // Activar movimiento cuando se presiona una tecla
        if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) derecha = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) arriba = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) abajo = true;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) disparo = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Desactivar movimiento cuando se suelta una tecla
        if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) derecha = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) arriba = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) abajo = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Método requerido pero no usado
    }
    
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Nave vs Enemigos");  // Crear ventana
        g juego = new g();                               // Crear juego
        
        ventana.add(juego);                              // Agregar juego a ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cerrar al hacer X
        ventana.setResizable(false);                     // No cambiar tamaño
        ventana.pack();                                  // Ajustar tamaño
        ventana.setLocationRelativeTo(null);             // Centrar ventana
        ventana.setVisible(true);                        // Mostrar ventana
    }
}