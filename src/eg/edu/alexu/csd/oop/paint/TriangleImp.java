package eg.edu.alexu.csd.oop.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Arrays;

public class TriangleImp extends Polygon implements ShapeIF {

	protected int[] xCoordinates;
	protected int[] yCoordinates;
	protected int numberOfSides = 3;
	protected Color c = Color.black;
	protected boolean fill = false;
	private String type = "Triangle";
	private int[] center;

	public TriangleImp(int[] xCoordinates, int[] yCoordinates) {

		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
		// int[] frameXCoordinates = new int[] {1000000000, 0};
		// int[] frameYCoordinates = new int[] {1000000000, 0};
		int sumX = 0, sumY = 0;
		for (int i = 0; i < numberOfSides; i++) {
			this.addPoint(xCoordinates[i], yCoordinates[i]);
			// frameXCoordinates[0] = Math.min(frameXCoordinates[0],
			// xCoordinates[i]);
			// frameXCoordinates[1] = Math.max(frameXCoordinates[1],
			// xCoordinates[i]);
			// frameYCoordinates[0] = Math.min(frameYCoordinates[0],
			// yCoordinates[i]);
			// frameYCoordinates[1] = Math.max(frameYCoordinates[1],
			// yCoordinates[i]);
			sumX += xCoordinates[i];
			sumY += yCoordinates[i];
		}
		// frameXCoordinates[0]-=5 ;
		// frameXCoordinates[1]+=5;
		// frameYCoordinates[0]-=5;
		// frameYCoordinates[1]+=5;
		sumX /= 3;
		sumY /= 3;

		center = new int[] { sumX - 2, sumY - 2 };
	}

	public int[] getOuter() {
		return center;
	}

	public final Graphics draw(final Graphics g) {
		if (this.isFill()) {
			g.fillPolygon(this.xCoordinates, this.yCoordinates, this.numberOfSides);
		}

		g.drawPolygon(this.xCoordinates, this.yCoordinates, this.numberOfSides);

		return g;
	}

	@Override
	public Graphics2D draw(Graphics2D g) {
		if (this.isFill()) {
			g.fillPolygon(this.xCoordinates, this.yCoordinates, this.numberOfSides);
		}

		g.drawPolygon(this.xCoordinates, this.yCoordinates, this.numberOfSides);

		return g;
	}

	@Override
	public boolean co(int x, int y) {
		// TODO Auto-generated method stub
		return this.contains((double) x, (double) y);
	}

	public int[] getXCoordinates() {
		return this.xCoordinates;
	}

	public int[] getYCoordinates() {
		return this.yCoordinates;
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

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public int getX1Int() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX2Int() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY1Int() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY2Int() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ShapeIF copy() {
		// TODO Auto-generated method stub
		TriangleImp t = new TriangleImp(this.xCoordinates, this.yCoordinates);
		t.setC(c);
		t.setFill(isFill());
		return t;
	}

	@Override
	public int getCenterX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCenterY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ShapeIF modify(ShapeIF lastShape, int xSelected, int ySelected, int x1, int x2, int y1, int y2,
			boolean resize, boolean modify) {
		// TODO Auto-generated method stub
		int[] corX = lastShape.getXCoordinates().clone();
		int[] corY = lastShape.getYCoordinates().clone();
		int pre = 30;
		for (int i = 0; i < corX.length; i++) {
			if (Math.abs(xSelected - corX[i]) <= pre && Math.abs(ySelected - corY[i]) <= pre) {
				corX[i] = x2;
				corY[i] = y2;
				break;
			}
		}
		return new TriangleImp(corX, corY);
	}

	@Override
	public ShapeIF move(ShapeIF lastShape, int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		int[] tempX = lastShape.getXCoordinates().clone();
		int[] tempY = lastShape.getYCoordinates().clone();
		for (int i = 0; i < tempX.length; i++) {
			tempX[i] += deltaX;
		}
		for (int i = 0; i < tempY.length; i++) {
			tempY[i] += deltaY;
		}
		return new TriangleImp(tempX, tempY);
	}

}
