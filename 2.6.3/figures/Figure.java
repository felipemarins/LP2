package figures;

import java.awt.*;

public abstract class Figure {
    int x, y;
    int w, h;
    Color outlineColor, backgroundColor;

    public Figure(int x, int y, int w, int h, Color outlineColor, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.outlineColor = outlineColor;
        this.backgroundColor = backgroundColor;
    }

    public abstract void paint(Graphics g);
}
