package figures;

import java.awt.*;

public class Ellipse {
    int x, y;
    int w, h;
    Color outlineColor, backgroundColor;

    public Ellipse(int x, int y, int w, int h, Color outlineColor, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.outlineColor = outlineColor;
        this.backgroundColor = backgroundColor;
    }

    public void print() {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillOval(this.x, this.y, this.w, this.h);
        g.setColor(outlineColor);
        g.drawOval(this.x, this.y, this.w, this.h);
    }
}
