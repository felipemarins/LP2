import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
        this.setVisible(true);
	this.getContentPane().setBackground(Color.darkGray);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.lightGray);
        int w = getWidth();
        int h = getHeight();
	for(int i = 25; i < w; i+=25){
		g2d.drawLine(0,i, w,i);
		g2d.drawLine(i,0, i,h);
	}
        //g2d.drawLine(0,h/2 - 50, w,h/2 - 50);
        //g2d.drawLine(0,h/2 + 50, w,h/2 + 50);
	g2d.fillRect(w/2 - 100, h/2 - 25, 200, 50);
	AttributedString testText = new AttributedString("test");
	testText.addAttribute(TextAttribute.SIZE, 24);
	testText.addAttribute(TextAttribute.FOREGROUND, Color.darkGray);
	g2d.drawString(testText.getIterator(), w/2-24, h/2+12);
    }
}
