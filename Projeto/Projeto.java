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
    Point lastMousePosition;

    Frame() {
        this.setState(State.SELECT);
        this.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ESCAPE:
                                currentFigure = null;
                                setState(State.SELECT);
                                break;
                            case KeyEvent.VK_R:
                                setState(State.CREATE_RECT);
                                currentFigure = null;
                                break;
                            case KeyEvent.VK_E:
                                setState(State.CREATE_ELLIPSE);
                                currentFigure = null;
                                break;
                            case KeyEvent.VK_L:
                                setState(State.CREATE_LINE);
                                currentFigure = null;
                                break;
                            case KeyEvent.VK_T:
                                setState(State.CREATE_TRIANGLE);
                                currentFigure = null;
                                break;
                            case KeyEvent.VK_DELETE:
                                figs.remove(currentFigure);
                                currentFigure = null;
                                repaint();
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
                            case SELECT:
                                currentFigure = getFigureAt(p);
                                repaint();
                                if (currentFigure != null) {
                                    lastMousePosition = p;
                                }
                                break;
                            case CREATE_RECT, CREATE_ELLIPSE, CREATE_LINE, CREATE_TRIANGLE:
                                switch (estado) {
                                    case CREATE_RECT:
                                        currentFigure = new Rect(p.x, p.y, 0, 0, Color.BLACK, Color.WHITE);
                                        break;
                                    case CREATE_ELLIPSE:
                                        currentFigure = new Ellipse(p.x, p.y, 0, 0, Color.BLACK, Color.WHITE);
                                        break;
                                    case CREATE_LINE:
                                        currentFigure = new Line(p.x, p.y, 0, 0, Color.BLACK);
                                        break;
                                    case CREATE_TRIANGLE:
                                        currentFigure = new Triangle(p.x, p.y, 0, 0, Color.BLACK, Color.WHITE);
                                        break;
                                }
                                figs.add(currentFigure);
                                currentOrigin = p;
                                repaint();
                                break;
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
                            case SELECT:
                                if (currentFigure != null) {
                                    currentFigure.move(p.x - lastMousePosition.x, p.y - lastMousePosition.y);
                                    lastMousePosition = p;
                                    repaint();
                                }
                                break;
                            case CREATE_RECT, CREATE_ELLIPSE, CREATE_LINE, CREATE_TRIANGLE:
                                if (currentFigure != null) {
                                    currentFigure.setSizeRelativeTo(p.x, p.y,
                                            currentOrigin.x, currentOrigin.y);
                                    repaint();
                                }
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
        if (currentFigure != null) {
            currentFigure.paintFocused(g);
        }
        g.setColor(new Color(150, 150, 255));
        g.fillRect(0, 0, this.getSize().width, 50);
        g.setColor(new Color(25, 25, 25));
        AttributedString estadoAttrStr = new AttributedString("Estado atual: " + estadoStr);
        estadoAttrStr.addAttribute(TextAttribute.SIZE, 10);
        estadoAttrStr.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, 13);
        g.drawString(estadoAttrStr.getIterator(), 10, 45);
    }

    enum State {
        SELECT, CREATE_RECT, CREATE_ELLIPSE, CREATE_LINE, CREATE_TRIANGLE
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
            case CREATE_ELLIPSE:
                this.estado = State.CREATE_ELLIPSE;
                this.estadoStr = "Criando elipse...";
                break;
            case CREATE_LINE:
                this.estado = State.CREATE_LINE;
                this.estadoStr = "Criando linha...";
                break;
            case CREATE_TRIANGLE:
                this.estado = State.CREATE_TRIANGLE;
                this.estadoStr = "Criando triângulo...";
                break;
        }
        repaint();
    }

    private Figure getFigureAt(Point p) {
        ListIterator<Figure> figsIterator = this.figs.listIterator(figs.size());
        while (figsIterator.hasPrevious()) {
            Figure fig = figsIterator.previous();
            if (fig.contains(p)) {
                return fig;
            }
        }
        return null;
    }
}
