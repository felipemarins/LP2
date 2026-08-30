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

    public void paintFocused(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f,
                new float[] { 4.0f, 8.0f }, 0f));
        g2d.setColor(new Color(250, 250, 0, 200));
        g2d.drawRect(x, y, w, h);
    }

    public void move(int offsetX, int offsetY) {
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
