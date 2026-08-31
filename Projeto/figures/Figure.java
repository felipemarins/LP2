package figures;

import java.awt.*;

public abstract class Figure {
    int x, y;
    int w, h;
    Rectangle bounds;

    public Figure(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        updateBounds();
    }

    public abstract void paint(Graphics g);

    public void paintFocused(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f,
                new float[] { 4.0f, 8.0f }, 0f));
        g2d.setColor(new Color(250, 250, 0, 200));
        g2d.draw(bounds);
    }

    public abstract boolean contains(Point p);

    public boolean boundsContain(Point p) {
        return bounds.contains(p);
    }

    public void move(int offsetX, int offsetY) {
        this.x += offsetX;
        this.y += offsetY;
        updateBounds();
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
        updateBounds();
    }

    private void updateBounds() {
        bounds = new Rectangle(this.x, this.y, this.w, this.h);
    }

    public Point getOrigin(Point p) {
        Point origin = new Point(x, y);
        switch (getQuadrant(p)) {
            case 1:
                origin.x = this.x;
                origin.y = this.y + this.h;
                break;
            case 2:
                origin.x = this.x + this.w;
                origin.y = this.y + this.h;
                break;
            case 3:
                origin.x = this.x + this.w;
                origin.y = this.y;
                break;
        }
        return origin;
    }

    public Point getOffsetToClosestCorner(Point p) {
        int quadrant = getQuadrant(p);
        Point offset;
        switch (quadrant) {
            case 1:
                offset = new Point(x + w - p.x, y - p.y);
                break;
            case 2:
                offset = new Point(x - p.x, y - p.y);
                break;
            case 3:
                offset = new Point(x - p.x, y + h - p.y);
                break;
            case 4:
                offset = new Point(x + w - p.x, y + h - p.y);
                break;
            default:
                offset = new Point(0, 0);
        }
        return offset;
    }

    private int getQuadrant(Point p) {
        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();
        if (p.y < centerY) {
            if (p.x > centerX) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (p.x < centerX) {
                return 3;
            } else {
                return 4;
            }
        }
    }
}
