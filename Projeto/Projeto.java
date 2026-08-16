import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.*;
import javax.swing.*;
import java.text.*;

import figures.*;

class Projeto {
    public void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
    }
}

class Frame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    State estado;
    String estadoStr;
    Figure currentFigure;
    Point currentOrigin;

    Frame() {
        this.setState(State.SELECT);
        this.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        Point p = getMousePosition();
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ESCAPE:
                                setState(State.SELECT);
                                currentFigure = null;
                                break;
                            case KeyEvent.VK_R:
                                setState(State.CREATE_RECT);
                                currentFigure = null;
                                break;
                            default:
                                break;
                        }
                    }
                });
        this.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        Point p = e.getPoint();
                        switch (estado) {
                            case CREATE_RECT:
                                currentFigure = new Rect(p.x, p.y, 0, 0, new Color(0, 0, 0), new Color(255, 255, 255));
                                figs.add(currentFigure);
                                currentOrigin = p;
                                repaint();
                            default:
                                break;
                        }
                    }

                    public void mouseReleased(MouseEvent e) {
                        currentOrigin = null;
                    }
                });
        this.addMouseMotionListener(
                new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent e) {
                        Point p = e.getPoint();
                        switch (estado) {
                            case CREATE_RECT:
                                currentFigure.setSizeRelativeTo(p.x - currentOrigin.x, p.y - currentOrigin.y,
                                        currentOrigin.x, currentOrigin.y);
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
        figs.forEach((fig) -> {
            fig.paint(g);
        });
        g.setColor(new Color(150, 150, 255));
        g.fillRect(0, 0, this.getSize().width, 50);
        g.setColor(new Color(25, 25, 25));
        AttributedString estadoAttrStr = new AttributedString("Estado atual: " + estadoStr);
        estadoAttrStr.addAttribute(TextAttribute.SIZE, 10);
        estadoAttrStr.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, 13);
        g.drawString(estadoAttrStr.getIterator(), 10, 45);
    }

    enum State {
        SELECT, CREATE_RECT
    }

    private void setState(State s) {
        switch (s) {
            case SELECT:
                this.estado = State.SELECT;
                this.estadoStr = "Selecionando figura...";
                break;
            case CREATE_RECT:
                this.estado = State.CREATE_RECT;
                this.estadoStr = "Criando retângulo...";
                break;
        }
        repaint();
    }
}
