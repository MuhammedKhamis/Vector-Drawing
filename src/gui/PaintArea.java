package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

//extends JComponent to add mouse listeners
public class PaintArea extends JComponent {

	private int x1, x2, y1, y2;
	private Graphics2D g2;
	private Image image;

	// constructor to add mouse listeners
	PaintArea() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				x1 = e.getX();
				y1 = e.getY();
			}
		});

		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				x2 = e.getX();
				y2 = e.getY();
			}

		});
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			clearAll();
		}
		g.drawImage(image, 0, 0, null);
	}

	public void clearAll() {
		// white background
		g2.setPaint(Color.white);
		// borders
		g2.fillRect(0, 0, getSize().width, getSize().height);
		// black pen
		g2.setPaint(Color.black);
		repaint();
	}

}
