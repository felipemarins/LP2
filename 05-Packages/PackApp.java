import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class PackApp {
	public static void main (String[] args){ 
		PackFrame frame = new PackFrame();
		frame.setVisible(true);
	}
}

class PackFrame extends JFrame {
	Rect r1;
	Ellipse e1;
	Line l1;

	PackFrame () {
		this.addWindowListener (
			new WindowAdapter() {
				public void windowClosing (WindowEvent e) {
					System.exit(0);
				}
			}
		);
		this.setTitle("Exercício 2.3.1 e 2.3.2");
		this.setSize(500, 500);
		this.r1 = new Rect(100, 50, 50, 100, new Color(255, 0, 0), new Color(0, 0, 255));
		this.e1 = new Ellipse(200, 50, 100, 50, new Color(0, 255, 0), new Color(255, 0, 0));
		this.l1 = new Line(200, 150, 300, 150, new Color(0,0,255));
	}

	public void paint (Graphics g) {
		super.paint(g);
		this.r1.paint(g);
		this.e1.paint(g);
		this.l1.paint(g);
	}
}
