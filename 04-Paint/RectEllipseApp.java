import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class RectEllipseApp {
    public static void main (String[] args) {
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}

class RectEllipseFrame extends JFrame {
    Rect r1;
    Ellipse e1;
    Ellipse e2;
    Ellipse e3;

    RectEllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Rect + Ellipse");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30);
        this.e1 = new Ellipse(50,100, 100,30, new Color(255,0,0), new Color(0, 0, 255));
        this.e2 = new Ellipse(200,100, 30,100, new Color(255,0,0), new Color(255, 255, 0));
        this.e3 = new Ellipse(50,200, 100,50, new Color(255,0,255), new Color(0, 255, 255));
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
	this.e2.paint(g);
	this.e3.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;

    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        g.drawRect(this.x,this.y, this.w,this.h);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    Color outlineColor, backgroundColor;

    Ellipse (int x, int y, int w, int h, Color outlineColor, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.outlineColor = outlineColor;
	this.backgroundColor = backgroundColor;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g.setColor(backgroundColor);
        g.fillOval(this.x,this.y, this.w,this.h);
	g.setColor(outlineColor);
        g.drawOval(this.x,this.y, this.w,this.h);
    }
}
