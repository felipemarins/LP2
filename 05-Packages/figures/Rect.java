package figures;

import java.awt.*;

public class Rect {
    int x, y;
    int w, h;
    Color outlineColor, backgroundColor;

    public Rect (int x, int y, int w, int h, Color outlineColor, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.outlineColor = outlineColor;
	this.backgroundColor = backgroundColor;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g.setColor(backgroundColor);
	g2d.fillRect(this.x,this.y, this.w,this.h);
	g.setColor(outlineColor);
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}
