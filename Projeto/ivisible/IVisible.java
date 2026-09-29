package ivisible;

import java.awt.Graphics;
import java.awt.Point;

public interface IVisible {
    boolean contains(Point p);
    void paint(Graphics g);
}
