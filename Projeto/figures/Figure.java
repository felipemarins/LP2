package figures;

import java.awt.*;

public abstract class Figure {
    public int x, y;
    public int w, h;

    public Figure(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
    }

    public abstract void paint(Graphics g);

    public void setSizeRelativeTo(int width, int height, int originX, int originY) {
        if (width < 0) {
            this.x = originX + width;
        } else {
            this.x = originX;
        }
        this.w = Math.abs(width);

        if (height < 0) {
            this.y = originY + height;
        } else {
            this.y = originY;
        }
        this.h = Math.abs(height);
    }
}
