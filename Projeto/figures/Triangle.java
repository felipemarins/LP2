package figures;

import java.awt.*;

public class Triangle extends FillableFigure {
    Polygon shape;

    public Triangle(int x, int y, int width, int height, Color drawColor, Color fillColor) {
        super(x, y, width, height, drawColor, fillColor);
        updateShape();
    }

    // Cria uma cópia do objeto Triangle com offset de (20,20) para (x,y)
    public Figure copy() {
        return new Triangle(this.x + 20, this.y + 20, this.w, this.h, this.drawColor, this.fillColor);
    }

    public boolean contains(Point p) {
        return shape.contains(p);
    }

    public void move(int offsetX, int offsetY) {
        super.move(offsetX, offsetY);
        shape.translate(offsetX, offsetY);
    }

    public void setSizeRelativeTo(int x, int y, int originX, int originY) {
        super.setSizeRelativeTo(x, y, originX, originY);
        updateShape();
    }

    private void updateShape() {
        if (w == 0 && h == 0) {
            shape = new Polygon(new int[] { x, x, x }, new int[] { y, y, y }, 3);
        } else {
            shape = new Polygon(new int[] { x, x + w / 2, x + w }, new int[] { y + h, y, y + h }, 3);
        }
    }

    public void paint(Graphics g, boolean isFocused) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(fillColor);
        g2d.fill(shape);
        g.setColor(drawColor);
        g2d.draw(shape);
        if(isFocused) {
            this.paintFocused(g);
        }
    }
}
