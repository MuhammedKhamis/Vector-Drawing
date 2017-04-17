package eg.edu.alexu.csd.oop.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * Line segment class.
 * 
 * @author Arsanuos
 */
public class LineSeg extends Line2D.Double implements ShapeIF {

    /**
     * (x1,y1) first point (x2,y2) second point
     */
    private int x1, y1, x2, y2;
    /**
     * identify type of the class.
     */
    private String type = "Line";

    protected Color c = Color.black;
    protected boolean fill = false;
    private final int HIT_BOX_SIZE = 2;
    private int[] center;

    public LineSeg(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y2 = y2;
        this.y1 = y1;
        center = new int[] {(x1 + x2) / 2 - 2, (y1 + y2) / 2 - 2};
    }

    public int[] getOuter() {
        return center;
    }

    /**
     * Get coordinates x of the first point
     * 
     * @return x1
     */
    public int getX1Int() {
        return x1;
    }

    /**
     * Get coordinates y of the first point
     * 
     * @return y1
     */
    public int getY1Int() {
        return y1;
    }

    /**
     * Get coordinates x of the second point
     * 
     * @return x2
     */
    public int getX2Int() {
        return x2;
    }

    /**
     * Get coordinates y of the second point
     * 
     * @return y2
     */
    public int getY2Int() {
        return y2;
    }

    /**
     * Get coordinates x of the first point
     * 
     * @return x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * Get coordinates y of the first point
     * 
     * @return y1
     */
    public final double getY1() {
        return y1;
    }

    /**
     * Get coordinates x of the second point
     * 
     * @return x2
     */
    public final double getX2() {
        return x2;
    }

    /**
     * Get coordinates y of the second point
     * 
     * @return y2
     */
    public final double getY2() {
        return y2;
    }

    /**
     * draw line using the information of each line(x1,y1,x2,y2)
     * 
     * @param g
     *            Graphics2D class to modify it.
     * @return g to be repainted in GUI.
     */
    public final Graphics draw(final Graphics g) {
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        return g;
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

    /**
     * get class type.
     * 
     * @return type of the class
     */
    public final String getType() {
        return type;
    }

    @Override
    public Graphics2D draw(Graphics2D g) {
        // TODO Auto-generated method stub
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        return g;
    }

    @Override
    public boolean co(int x, int y) {
        // TODO Auto-generated method stub
        int boxX = x - HIT_BOX_SIZE / 2;
        int boxY = y - HIT_BOX_SIZE / 2;

        int width = HIT_BOX_SIZE;
        int height = HIT_BOX_SIZE;
        if (this.intersects(boxX, boxY, width, height)) {
            return true;
        }
        return false;
    }

    @Override
    public int[] getXCoordinates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getYCoordinates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShapeIF copy() {
        // TODO Auto-generated method stub
        LineSeg l = new LineSeg(this.x1, this.x2, this.y1, this.y2);
        l.setC(c);
        l.setFill(isFill());
        return l;
    }

    @Override
    public int getCenterX1() {
        // TODO Auto-generated method stub

        return (x1 + x2) / 2;
    }

    @Override
    public int getCenterY1() {
        // TODO Auto-generated method stub
        return (y1 + y2) / 2;
    }

    @Override
    public ShapeIF modify(ShapeIF lastShape, int xSelected, int ySelected, int x1, int x2, int y1, int y2,
            boolean resize, boolean modify) {
        // TODO Auto-generated method stub
        if (Math.abs(xSelected - lastShape.getX1Int()) <= 20 && Math.abs(ySelected - lastShape.getY1Int()) <= 20) {
            x1 = x2;
            y1 = y2;
            x2 = lastShape.getX2Int();
            y2 = lastShape.getY2Int();
        } else if (Math.abs(xSelected - lastShape.getX2Int()) <= 20
                && Math.abs(ySelected - lastShape.getY2Int()) <= 20) {
            x1 = lastShape.getX1Int();
            y1 = lastShape.getY1Int();
        }
        return new LineSeg(x1, x2, y1, y2);
    }

    @Override
    public ShapeIF move(ShapeIF lastShape, int deltaX, int deltaY) {
        // TODO Auto-generated method stub
        return new LineSeg(lastShape.getX1Int() + deltaX, lastShape.getX2Int() + deltaX, lastShape.getY1Int() + deltaY,
                lastShape.getY2Int() + deltaY);
    }
}
