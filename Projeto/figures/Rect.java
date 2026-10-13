package figures;

import java.awt.*;

public class Rect extends FillableFigure {
    public Rect(int x, int y, int width, int height, Color drawColor, Color fillColor) {
        super(x, y, width, height, drawColor, fillColor);
    }

    // Cria uma cópia do objeto Rect com offset de (20,20) para (x,y)
    public Figure copy() {
        return new Rect(this.x + 20, this.y + 20, this.w, this.h, this.drawColor, this.fillColor);
    }

    public boolean contains(Point p) {
        Rectangle r = new Rectangle(this.x, this.y, this.w, this.h);
        return r.contains(p);
    }

    public void print() {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void paint(Graphics g, boolean isFocused) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(fillColor);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(drawColor);
        g2d.drawRect(this.x, this.y, this.w, this.h);
        if(isFocused) {
            this.paintFocused(g);
        }
    }
}
