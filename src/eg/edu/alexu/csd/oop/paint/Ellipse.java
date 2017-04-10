package eg.edu.alexu.csd.oop.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Ellipse2D.Double implements ShapeIF {
	/**
	 * raduis2 of the ellipse
	 */
	protected int radius1;
	protected int radius2;
	private String type = "Ellipse";
	protected Color c = Color.black;
	protected boolean fill = false;
	protected int x1, x2, y1, y2;
	private int[] center;
	protected int[] xCoordinates;
	protected int[] yCoordinates;
	/**
	 * (centerx,centery) center coordinates radius is the circle radius
	 */
	protected int centerX, centerY;

	public Ellipse(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.centerX = Integer.min(x1, x2);
		this.centerY = Integer.min(y1, y2);
		int a = Math.abs(x2 - x1);
		int b = Math.abs(y2 - y1);
		this.radius1 = a;
		this.radius2 = b;
		this.setFrame(centerX, centerY, radius1, radius2);
		center = new int[] {centerX + a / 2 - 2, centerY + b / 2 - 2};
	}

	public int[] getOuter() {
		return center;
	}

	/**
	 * Getting type of the class for saving and loading.
	 * 
	 * @return type of the class as string.
	 */
	public String getType() {
		return type;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public Graphics2D draw(Graphics2D g) {
		if (this.isFill()) {
			g.fillOval(centerX, centerY, radius1, radius2);
		} else {
			g.drawOval(centerX, centerY, radius1, radius2);
		}
		return g;
	}

	@Override
	public Graphics draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawOval(centerX, centerY, radius1, radius2);
		return g;
	}

	@Override
	public boolean co(int x, int y) {
		// TODO Auto-generated method stub

		return this.contains((double) x, (double) y);
	}

	@Override
	public int getX1Int() {
		// TODO Auto-generated method stub
		return this.x1;
	}

	@Override
	public int getX2Int() {
		// TODO Auto-generated method stub
		return this.x2;
	}

	@Override
	public int getY1Int() {
		// TODO Auto-generated method stub
		return this.y1;
	}

	@Override
	public int getY2Int() {
		// TODO Auto-generated method stub
		return this.y2;
	}

	@Override
	public int[] getXCoordinates() {
		// TODO Auto-generated method stub
		return this.xCoordinates;
	}

	@Override
	public int[] getYCoordinates() {
		// TODO Auto-generated method stub
		return this.yCoordinates;
	}

	@Override
	public ShapeIF copy() {
		// TODO Auto-generated method stub
		Ellipse e = new Ellipse(this.x1, this.x2, this.y1, this.y2);
		e.setC(c);
		e.setFill(isFill());
		return e;
	}

	@Override
	public int getCenterX1() {
		// TODO Auto-generated method stub
		return (int) this.getCenterX();
	}

	@Override
	public int getCenterY1() {
		// TODO Auto-generated method stub
		return (int) this.getCenterY();
	}

	@Override
	public ShapeIF modify(ShapeIF lastShape, int xSelected, int ySelected, int x1, int x2, int y1, int y2,
			boolean resize, boolean modify) {
		// TODO Auto-generated method stub
		int pre = 50;
		boolean found = false;
		if (resize) {
			while (!found) {
				if (Math.abs(xSelected - lastShape.getX1Int()) <= pre
						&& Math.abs(ySelected - lastShape.getY1Int()) <= pre && lastShape.getX1Int() >= x2) {

					found = true;
					x1 = lastShape.getX1Int() - (lastShape.getX1Int() - x2);
					x2 = lastShape.getX2Int() + (lastShape.getX1Int() - x2);
					y1 = lastShape.getY1Int() - (lastShape.getY1Int() - y2);
					y2 = lastShape.getY2Int() + (lastShape.getY1Int() - y2);
				} else if (Math.abs(xSelected - lastShape.getX1Int()) <= pre
						&& Math.abs(ySelected - lastShape.getY1Int()) <= pre && lastShape.getX1Int() <= x2) {// upper
																												// left
																												// in

					found = true;
					x1 = lastShape.getX1Int() + (x2 - lastShape.getX1Int());
					x2 = lastShape.getX2Int() - (x2 - lastShape.getX1Int());
					y1 = lastShape.getY1Int() + (y2 - lastShape.getY1Int());
					y2 = lastShape.getY2Int() - (y2 - lastShape.getY1Int());
				} else if (Math.abs(xSelected - lastShape.getX2Int()) <= pre
						&& Math.abs(ySelected - lastShape.getY1Int()) <= pre
						&& (lastShape.getX1Int() <= x2 || lastShape.getY2Int() <= y2 || lastShape.getY2Int() >= y2)) {

					found = true;
					x1 = lastShape.getX1Int() + (lastShape.getX2Int() - x2);
					x2 = lastShape.getX2Int() - (lastShape.getX2Int() - x2);
					y1 = lastShape.getY1Int() - (lastShape.getY1Int() - y2);
					y2 = lastShape.getY2Int() + (lastShape.getY1Int() - y2);
				} else if (Math.abs(xSelected - lastShape.getX1Int()) <= pre
						&& Math.abs(ySelected - lastShape.getY2Int()) <= pre) {
					found = true;
					x1 = lastShape.getX1Int() - (lastShape.getX1Int() - x2);
					x2 = lastShape.getX2Int() + (lastShape.getX1Int() - x2);
					y1 = lastShape.getY1Int() + (lastShape.getY2Int() - y2);
					y2 = lastShape.getY2Int() - (lastShape.getY2Int() - y2);
				} else if (Math.abs(xSelected - lastShape.getX2Int()) <= pre
						&& Math.abs(ySelected - lastShape.getY2Int()) <= pre) {
					found = true;
					x1 = lastShape.getX1Int() + (lastShape.getX2Int() - x2);
					x2 = lastShape.getX2Int() - (lastShape.getX2Int() - x2);
					y1 = lastShape.getY1Int() + (lastShape.getY2Int() - y2);
					y2 = lastShape.getY2Int() - (lastShape.getY2Int() - y2);
				}
				pre += 10;
			}
		} else if (modify) {
			int oldX1 = lastShape.getX1Int();
			int oldX2 = lastShape.getX2Int();
			int oldY1 = lastShape.getY1Int();
			int oldY2 = lastShape.getX2Int();
			while (!found) {
				if (Math.abs(xSelected - oldX1) <= pre && Math.abs(ySelected - oldY1) <= pre && oldX1 >= x2) {
					found = true;
					x1 = oldX1 - (oldX1 - x2);
					x2 = oldX2;
					y1 = oldY1 - (oldY1 - y2);
					y2 = oldY2;
				} else if (Math.abs(xSelected - oldX1) <= pre && Math.abs(ySelected - oldY1) <= pre && oldX1 <= x2) {// upper
																														// left
																														// in

					found = true;
					x1 = oldX1 + (x2 - oldX1);
					x2 = oldX2;
					y1 = oldY1 + (y2 - oldY1);
					y2 = oldY2;
				} else if (Math.abs(xSelected - oldX2) <= pre && Math.abs(ySelected - oldY1) <= pre
						&& (oldX1 <= x2 || oldY2 <= y2 || oldY2 >= y2)) {

					found = true;
					x1 = oldX1;
					x2 = oldX2 - (oldX2 - x2);
					y1 = oldY1 - (oldY1 - y2);
					y2 = oldY2;
				} else if (Math.abs(xSelected - oldX1) <= pre && Math.abs(ySelected - oldY2) <= pre) {

					found = true;
					x1 = oldX1 - (oldX1 - x2);
					x2 = oldX2;
					y1 = oldY1;
					y2 = oldY2 - (oldY2 - y2);
				} else if (Math.abs(xSelected - oldX2) <= pre && Math.abs(ySelected - oldY2) <= pre) {
					found = true;
					x1 = oldX1;
					x2 = oldX2 - (oldX2 - x2);
					y1 = oldY1;
					y2 = oldY2 - (oldY2 - y2);
				}
				pre += 10;
			}
		}
		return new Ellipse(x1, x2, y1, y2);
	}

	@Override
	public ShapeIF move(ShapeIF lastShape, int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		return new Ellipse(lastShape.getX1Int() + deltaX, lastShape.getX2Int() + deltaX, lastShape.getY1Int() + deltaY,
				lastShape.getY2Int() + deltaY);
	}

}
