package figures;

import java.awt.*;

public class Ellipse extends Figure {
    public Ellipse(int x, int y, int w, int h, Color outlineColor, Color backgroundColor) {
        super(x, y, w, h, outlineColor, backgroundColor);
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
