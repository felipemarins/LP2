package figures;

import java.awt.*;
import javax.swing.*;
import ivisible.IVisible;

public abstract class Figure implements IVisible {
    int x, y;
    int w, h;
    Color drawColor;
    Rectangle bounds;

    public Figure(int x, int y, int width, int height, Color drawColor) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.drawColor = drawColor;
        updateBounds();
    }

    public abstract Figure copy();

    protected void paintFocused(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f,
                new float[] { 4.0f, 8.0f }, 0f));
        g2d.setColor(new Color(255, 0, 0, 200));
        g2d.draw(bounds);
        g2d.setStroke(new BasicStroke());
    }

    public void setDrawColor(JFrame frame) {
        Color c = setColor(frame, "Cor de contorno", "Defina a cor de contorno (em hexcode):");
        if (c == null) {
            return;
        }
        drawColor = c;
    }

    public void setDrawColor(Color c){
        drawColor = c;
    }

    public abstract void setFillColor(JFrame frame);

    public abstract void setFillColor(Color c);

    protected Color setColor(JFrame frame, String title, String message) {
        String s = JOptionPane.showInputDialog(frame, message, title, JOptionPane.QUESTION_MESSAGE);
        while (s != null && !s.matches("[0-9a-fA-F]{1,6}")) {
            s = JOptionPane.showInputDialog(frame, "Valor inválido!\n" + message, title, JOptionPane.ERROR_MESSAGE);
        }
        if (s == null) {
            return null;
        }
        return new Color(Integer.parseInt(s, 16));
    }

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
