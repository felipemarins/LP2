package figures;

import java.awt.*;

public class Rect extends Figure {
    Color outlineColor, backgroundColor;

    public Rect(int x, int y, int width, int height, Color outlineColor, Color backgroundColor) {
        super(x, y, width, height);
        this.outlineColor = outlineColor;
        this.backgroundColor = backgroundColor;
    }

    public void print() {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(backgroundColor);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(outlineColor);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
