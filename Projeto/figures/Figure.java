package figures;

import java.awt.*;

public abstract class Figure extends Component {
    int x, y;
    int w, h;

    public abstract void paint(Graphics g);

    protected void updateBounds() {
        this.setBounds(x, y, w + 1, h + 1);
    }

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

        updateBounds();
    }
}
