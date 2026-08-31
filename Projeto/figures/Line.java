package figures;

import java.awt.*;
import java.awt.geom.*;

public class Line extends Figure {
    Line2D.Double line;
    Color color;

    public Line(int x, int y, int w, int h, Color color) {
        super(x, y, w, h);
        this.color = color;
        if (w == 0 && h == 0) {
            this.line = new Line2D.Double();
        } else {
            this.line = new Line2D.Double(x, y, x + w, y + h);
        }
    }

    public boolean contains(Point p) {
        Rectangle r = new Rectangle(this.x, this.y, this.w, this.h);
        return r.contains(p);
    }

    public void move(int offsetX, int offsetY) {
        super.move(offsetX, offsetY);
        double x1 = line.getX1() + offsetX;
        double y1 = line.getY1() + offsetY;
        double x2 = line.getX2() + offsetX;
        double y2 = line.getY2() + offsetY;
        line.setLine(x1, y1, x2, y2);
    }

    public void setSizeRelativeTo(int x, int y, int originX, int originY) {
        super.setSizeRelativeTo(x, y, originX, originY);
        double x1 = line.getX1();
        double y1 = line.getY1();
        double x2 = line.getX2();
        double y2 = line.getY2();
        if (!(x1 == originX && y1 == originY) && !(x2 == originX && y2 == originY)) {
            x1 = Math.min(originX, x);
            y1 = Math.min(originY, y);
            x2 = Math.max(originX, x);
            y2 = Math.max(originY, y);
        } else {
            x1 = originX;
            y1 = originY;
            x2 = x;
            y2 = y;
        }
        line.setLine(x1, y1, x2, y2);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        g2d.draw(line);
    }
}
