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

    public void move(int offsetX, int offsetY){
        this.x += offsetX;
        this.y += offsetY;
    }

    public void setSizeRelativeTo(int x, int y, int originX, int originY) {
        int width = x - originX;
        if (width < 0) {
            this.x = originX + width;
        } else {
            this.x = originX;
        }
        this.w = Math.abs(width);

        int height = y - originY;
        if (height < 0) {
            this.y = originY + height;
        } else {
            this.y = originY;
        }
        this.h = Math.abs(height);
    }
}
