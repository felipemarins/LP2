package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends FillableFigure {
    public Ellipse(int x, int y, int w, int h, Color drawColor, Color fillColor) {
        super(x, y, w, h, drawColor, fillColor);
    }

    public boolean contains(Point p) {
        Ellipse2D.Double e = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
        return e.contains(p);
    }

    public void print() {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(fillColor);
        g.fillOval(this.x, this.y, this.w, this.h);
        g.setColor(drawColor);
        g.drawOval(this.x, this.y, this.w, this.h);
    }
}
