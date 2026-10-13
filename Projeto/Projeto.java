import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.*;
import javax.swing.*;
import java.text.*;
import java.io.*;

import figures.*;
import button.Button;
import button.State;

class Projeto {
    public void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
    }
}

class Frame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Button> buts = new ArrayList<Button>();
    State estado;
    String estadoStr;
    Figure currentFigure;
    Point currentOrigin;
    Point lastMousePosition;
    Point offsetToQuadrant;

    Frame() {
        buts.add(new Button(0, new Rect(0, 0, 0, 0, Color.BLACK, Color.WHITE), State.CREATE_RECT));
        buts.add(new Button(1, new Ellipse(0, 0, 0, 0, Color.BLACK, Color.WHITE), State.CREATE_ELLIPSE));
        buts.add(new Button(2, new Line(0, 0, 0, 0, Color.BLACK), State.CREATE_LINE));
        buts.add(new Button(3, new Triangle(0, 0, 0, 0, Color.BLACK, Color.WHITE), State.CREATE_TRIANGLE));
        buts.add(new Button(4, new Carro(0, 0, 0, 0, Color.BLACK, Color.WHITE), State.CREATE_CARRO));

        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            o.close();
        } catch (Exception x) {
            System.out.println("erro ao tentar carregar proj.bin!");
        }

        this.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ESCAPE:
                                currentFigure = null;
                                setState(State.SELECT);
                                break;
                            case KeyEvent.VK_C:
                                if (currentFigure != null) {
                                    currentFigure.setDrawColor(Frame.this);
                                    repaint();
                                }
                                break;
                            case KeyEvent.VK_B:
                                if (currentFigure != null) {
                                    currentFigure.setFillColor(Frame.this);
                                    repaint();
                                }
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
                            case KeyEvent.VK_1:
                                setState(State.CREATE_CARRO);
                                currentFigure = null;
                                break;
                            case KeyEvent.VK_S:
                                setState(State.RESIZE);
                                currentFigure = null;
                                break;
                            case KeyEvent.VK_PAGE_UP:
                                if (currentFigure != null) {
                                    changeFigureZ(currentFigure, 1);
                                    repaint();
                                }
                                break;
                            case KeyEvent.VK_PAGE_DOWN:
                                if (currentFigure != null) {
                                    changeFigureZ(currentFigure, -1);
                                    repaint();
                                }
                                break;
                            case KeyEvent.VK_DELETE:
                                figs.remove(currentFigure);
                                currentFigure = null;
                                repaint();
                                break;
                            case KeyEvent.VK_2:
                                currentFigure = getNextFigure();
                                repaint();
                                break;
                            case KeyEvent.VK_3:
                                if (currentFigure != null) {
                                    currentFigure = currentFigure.copy();
                                    figs.add(currentFigure);
                                    repaint();
                                }
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
                        if(getButtonAt(p)){
                            return;
                        } else {
                            switch (estado) {
                                case SELECT:
                                    currentFigure = getFigureAt(p, false);
                                    repaint();
                                    if (currentFigure != null) {
                                        lastMousePosition = p;
                                    }
                                    break;
                                case RESIZE:
                                    currentFigure = getFigureAt(p, true);
                                    repaint();
                                    if (currentFigure != null) {
                                        currentOrigin = currentFigure.getOrigin(p);
                                        offsetToQuadrant = currentFigure.getOffsetToClosestCorner(p);
                                    }
                                    break;
                                case CREATE_RECT, CREATE_ELLIPSE, CREATE_LINE, CREATE_TRIANGLE, CREATE_CARRO:
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
                                        case CREATE_CARRO:
                                            // Cria um novo objeto carro
                                            currentFigure = new Carro(p.x, p.y, 0, 0, Color.BLACK, Color.WHITE);
                                            break;
                                    }
                                    // Adiciona figura criada à lista
                                    figs.add(currentFigure);
                                    currentOrigin = p;
                                    repaint();
                                    break;
                                default:
                                    break;
                            }
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
                            case RESIZE:
                                if (currentFigure != null) {
                                    p.translate(offsetToQuadrant.x, offsetToQuadrant.y);
                                    currentFigure.setSizeRelativeTo(p.x, p.y, currentOrigin.x, currentOrigin.y);
                                    repaint();
                                }
                                break;
                            case CREATE_RECT, CREATE_ELLIPSE, CREATE_LINE, CREATE_TRIANGLE, CREATE_CARRO:
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

                    public void mouseMoved(MouseEvent e) {
                        switch (estado) {
                            case RESIZE:
                                if (currentFigure != getFigureAt(e.getPoint(), true)) {
                                    currentFigure = getFigureAt(e.getPoint(), true);
                                    repaint();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });
        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        try {
                            FileOutputStream f = new FileOutputStream("proj.bin");
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(figs);
                            o.flush();
                            o.close();
                        } catch (Exception x) {
                            System.out.println("Erro ao gravar arquivo proj.bin!");
                        }
                    }
                });

        this.setTitle("Projeto");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setState(State.SELECT);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for(Figure fig: figs){
            fig.paint(g, currentFigure == fig);
        }
        for(Button but: buts){
            but.paint(g, but.state == estado);
        }
        g.setColor(new Color(150, 150, 255));
        g.fillRect(0, 0, this.getSize().width, 50);
        g.setColor(new Color(25, 25, 25));
        AttributedString estadoAttrStr = new AttributedString("Estado atual: " + estadoStr);
        estadoAttrStr.addAttribute(TextAttribute.SIZE, 10);
        estadoAttrStr.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, 13);
        g.drawString(estadoAttrStr.getIterator(), 10, 45);
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
            case CREATE_CARRO:
                this.estado = State.CREATE_CARRO;
                this.estadoStr = "Criando carro...";
                break;
            case RESIZE:
                this.estado = State.RESIZE;
                this.estadoStr = "Redimensionando...";
                break;
        }
        repaint();
    }

    private Figure getFigureAt(Point p, boolean anywhereInsideBounds) {
        ListIterator<Figure> figsIterator = this.figs.listIterator(figs.size());
        while (figsIterator.hasPrevious()) {
            Figure fig = figsIterator.previous();
            if (fig.contains(p) || (anywhereInsideBounds && fig.boundsContain(p))) {
                return fig;
            }
        }
        return null;
    }

    private boolean getButtonAt(Point p){
        for(Button but: buts){
            if(but.contains(p)){
                setState(but.state);
                return true;
            }
        }
        return false;
    }

    private Figure getNextFigure(){
        Figure fig = currentFigure;
        int i = 0;
        // Verifica se existe alguma figura em foco, e, caso exista, seu índice + 1 é atribuído a i
        if(fig != null){
            i = figs.indexOf(fig) + 1;
        }

        // Retorna null caso a lista de figuras esteja vazia
        if (figs.isEmpty()){
            return null;
        }
        // Retorna próxima figura, tratando figs como uma lista circular
        // Caso não exista figura em foco, retorna a primeira figura da lista
        return figs.get(i % figs.size());
    }

    private void changeFigureZ(Figure fig, int offset) {
        int i = figs.indexOf(fig);
        figs.remove(fig);
        i += offset;
        i = Math.clamp(i, 0, figs.size());
        figs.add(i, fig);
    }
}
