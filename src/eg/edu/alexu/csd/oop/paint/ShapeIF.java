package eg.edu.alexu.csd.oop.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

public interface ShapeIF {

	Color c = Color.black;
	boolean fill = false;
	String type = null;

	public Graphics2D draw(Graphics2D g);

	public Graphics draw(Graphics g);

	public Color getC();

	public void setC(Color c);

	public boolean isFill();

	public void setFill(boolean fill);

	public boolean co(int x, int y);

	public String getType();

	public int getX1Int();

	public int getX2Int();

	public int getY1Int();

	public int getY2Int();

	public int[] getXCoordinates();

	public int[] getYCoordinates();

	public ShapeIF copy();

	public int getCenterX1();

	public int getCenterY1();

	public ShapeIF modify(ShapeIF lastShape, int xSelected, int ySelected, int x1, int x2, int y1, int y2,
			boolean resize, boolean modify);

	public ShapeIF move(ShapeIF lastShape, int deltaX, int deltaY);
	
	public int[] getOuter();

}
