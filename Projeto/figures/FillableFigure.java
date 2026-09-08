package figures;

import java.awt.*;
import javax.swing.JFrame;

public abstract class FillableFigure extends Figure {
    Color fillColor;

    public FillableFigure(int x, int y, int width, int height, Color drawColor, Color fillColor) {
        super(x, y, width, height, drawColor);
        this.fillColor = fillColor;
    }

    public void setFillColor(JFrame frame) {
        Color c = setColor(frame, "Cor de fundo", "Defina a cor de fundo (em hexcode):");
        if (c == null) {
            return;
        }
        fillColor = c;
    }
}
