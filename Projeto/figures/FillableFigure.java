package figures;

import java.awt.*;

public abstract class FillableFigure extends Figure {
    Color fillColor;

    public FillableFigure(int x, int y, int width, int height, Color drawColor, Color fillColor) {
        super(x, y, width, height, drawColor);
        this.fillColor = fillColor;
    }

    public void setFillColor(Color c) {
        fillColor = c;
    }
}
