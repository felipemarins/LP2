package figures;

import java.awt.*;
import javax.swing.JFrame;

public class Carro extends FillableFigure {
    Rect chassi;
    Ellipse roda1;
    Ellipse roda2;

    public Carro(int x, int y, int width, int height, Color drawColor, Color fillColor) {
        super(x, y, width, height, drawColor, fillColor);
        // Constrói objetos para as partes do carro
        updateChassi();
        updateRodas();
    }

    private int calculateRodaDiametro(){
        return Math.min(w/4, h/4);
    }

    public void move(int offsetX, int offsetY) {
        super.move(offsetX, offsetY);
        // Move todos as partes do carro
        chassi.move(offsetX, offsetY);
        roda1.move(offsetX, offsetY);
        roda2.move(offsetX, offsetY);
    }

    // Cria um novo objeto Rect para chassi com base nos valores atuais das propriedades de Carro
    private void updateChassi() {
        int rodaDiametro = calculateRodaDiametro();
        // Constrói um novo objeto Rect
        this.chassi = new Rect(x, y, w, Math.max(h - rodaDiametro/2, 0), drawColor, fillColor);
    }

    private void updateRodas(){
        int diametro = calculateRodaDiametro();
        // Constrói novos objetos Ellipse para cada uma das rodas
        this.roda1 = new Ellipse(x + diametro/2, y + h - diametro, diametro, diametro, drawColor, fillColor);
        this.roda2 = new Ellipse(x + w - (diametro + diametro/2), y + h - diametro, diametro, diametro, drawColor, fillColor);
    }

    public void setSizeRelativeTo(int x, int y, int originX, int originY){
        super.setSizeRelativeTo(x, y, originX, originY);
        // Reconstrói as partes do carro com base de acordo com o novo tamanho e x,y do Carro
        updateChassi();
        updateRodas();
    }

    // Retorna true se o p está contido em alguma das partes do carro
    public boolean contains(Point p) {
        return chassi.contains(p) || roda1.contains(p) || roda2.contains(p);
    }

    public void setDrawColor(JFrame frame) {
        super.setDrawColor(frame);
        // Atualiza a drawColor de todas as partes do carro
        chassi.setDrawColor(drawColor);
        roda1.setDrawColor(drawColor);
        roda2.setDrawColor(drawColor);
    }

    public void setFillColor(JFrame frame) {
        super.setFillColor(frame);
        // Atualiza a fillColor de todas as partes do carro
        chassi.setFillColor(fillColor);
        roda1.setFillColor(fillColor);
        roda2.setFillColor(fillColor);
    }

    public void paint(Graphics g) {
        // Chama o método paint de todas as partes do carro
        chassi.paint(g);
        roda1.paint(g);
        roda2.paint(g);
    }
}
