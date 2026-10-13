package button;

import java.awt.*;
import ivisible.IVisible;
import figures.Figure;

public class Button implements IVisible {
    public int idx;
    public State state;
    private Figure fig;
    private Rectangle r;

    public Button(int idx, Figure fig, State state) {
        this.idx = idx;
        this.r = new Rectangle(50, 50);
        this.updateRectangleLocation();
        this.fig = fig;
        this.fig.setPosition(this.r.x + 10, this.r.y + 10);
        this.fig.setSize(30, 30);
        this.state = state;
    }

    private void updateRectangleLocation(){
        this.r.setLocation(10, (idx * 50) + 60);
    }

    public boolean contains(Point p){
        return r.contains(p);
    }

    public void paint(Graphics g, boolean isFocused){
        Graphics2D g2d = (Graphics2D) g;
        if(isFocused) {
            g2d.setColor(Color.LIGHT_GRAY);
        } else {
            g2d.setColor(Color.WHITE);
        }
        g2d.fill(this.r);
        g2d.setColor(Color.BLACK);
        g2d.draw(this.r);
        this.fig.paint(g, false);
    }
}
