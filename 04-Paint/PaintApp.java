import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1;
    Rect r2;
    Rect r3;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30, new Color(200,50,50), new Color(50,200,50));
	this.r2 = new Rect(50,80, 30,70, new Color(50,200,50), new Color(50,50,200));
	this.r3 = new Rect(80,80, 70,70, new Color(50,50,200), new Color(200,50,50));
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    Color outlineColor, backgroundColor;

    Rect (int x, int y, int w, int h, Color outlineColor, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.outlineColor = outlineColor;
	this.backgroundColor = backgroundColor;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g.setColor(backgroundColor);
	g2d.fillRect(this.x,this.y, this.w,this.h);
	g.setColor(outlineColor);
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}
