import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import figures.*;

class Projeto {
    public void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
    }
}

class Frame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();

    Frame() {
        this.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        Point p = getMousePosition();
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_R:
                                Rect rect = new Rect(p.x, p.y, 100, 50, new Color(0, 0, 0), new Color(255, 255, 255));
                                figs.add(rect);
                                repaint();
                                break;
                            case KeyEvent.VK_E:
                                Ellipse ellipse = new Ellipse(p.x, p.y, 100, 50, new Color(0, 0, 0),
                                        new Color(255, 255, 255));
                                figs.add(ellipse);
                                repaint();
                                break;
                            default:
                                break;
                        }
                    }
                });
        this.setTitle("Projeto");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Figure fig : figs) {
            fig.paint(g);
        }
    }
}
