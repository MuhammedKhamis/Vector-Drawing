package eg.edu.alexu.csd.oop.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

public class RectangleImp extends Rectangle2D.Double implements ShapeIF {

    protected int[] xCoordinates;
    protected int[] yCoordinates;
    protected Color c = Color.black;
    protected boolean fill = false;
    private String type = "Rectangle";
    private int[] center;

    public RectangleImp(int[] xCoordinates, int[] yCoordinates, ShapeIF last) {
        Arrays.sort(xCoordinates);
        Arrays.sort(yCoordinates);
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.setFrame(this.xCoordinates[0], this.yCoordinates[0], this.xCoordinates[1] - this.xCoordinates[0],
                this.yCoordinates[1] - this.yCoordinates[0]);

    }

    public RectangleImp() {

    }

    public int[] getOuter() {
        center = new int[] {(xCoordinates[0] + xCoordinates[1]) / 2 - 2, (yCoordinates[0] + yCoordinates[1]) / 2 - 2};
        return center;
    }

    public Graphics draw(final Graphics g) {
        g.drawRect(this.xCoordinates[0], this.yCoordinates[0], this.xCoordinates[1] - this.xCoordinates[0],
                this.yCoordinates[1] - this.yCoordinates[0]);
        return g;

    }

    @Override
    public Graphics2D draw(Graphics2D g) {
        if (this.isFill()) {
            g.fillRect(this.xCoordinates[0], this.yCoordinates[0], this.xCoordinates[1] - this.xCoordinates[0],
                    this.yCoordinates[1] - this.yCoordinates[0]);
        } else {
            g.drawRect(this.xCoordinates[0], this.yCoordinates[0], this.xCoordinates[1] - this.xCoordinates[0],
                    this.yCoordinates[1] - this.yCoordinates[0]);
        }
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
        return this.xCoordinates[0];
    }

    @Override
    public int getX2Int() {
        // TODO Auto-generated method stub
        return this.xCoordinates[1];
    }

    @Override
    public int getY1Int() {
        // TODO Auto-generated method stub
        return this.yCoordinates[0];
    }

    @Override
    public int getY2Int() {
        // TODO Auto-generated method stub
        return this.yCoordinates[1];
    }

    @Override
    public ShapeIF copy() {
        // TODO Auto-generated method stub
        RectangleImp r = new RectangleImp(this.xCoordinates, this.yCoordinates, null);
        r.setC(c);
        r.setFill(isFill());
        return r;
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
        int[] corX = new int[2];
        int[] corY = new int[2];
        int oldX1, oldX2, oldY1, oldY2;
        corX[0] = oldX1 = lastShape.getX1Int();
        corX[1] = oldX2 = lastShape.getX2Int();
        corY[0] = oldY1 = lastShape.getY1Int();
        corY[1] = oldY2 = lastShape.getY2Int();
        if (resize) {
            if (Math.abs(xSelected - lastShape.getX1Int()) <= 10 && Math.abs(ySelected - lastShape.getY1Int()) <= 10
                    && lastShape.getX1Int() >= x2) {

                corX[0] = lastShape.getX1Int() - (lastShape.getX1Int() - x2);
                corX[1] = lastShape.getX2Int() + (lastShape.getX1Int() - x2);
                corY[0] = lastShape.getY1Int() - (lastShape.getY1Int() - y2);
                corY[1] = lastShape.getY2Int() + (lastShape.getY1Int() - y2);
            } else if (Math.abs(xSelected - lastShape.getX1Int()) <= 10
                    && Math.abs(ySelected - lastShape.getY1Int()) <= 10 && lastShape.getX1Int() <= x2) {
                corX[0] = lastShape.getX1Int() + (x2 - lastShape.getX1Int());
                corX[1] = lastShape.getX2Int() - (x2 - lastShape.getX1Int());
                corY[0] = lastShape.getY1Int() + (y2 - lastShape.getY1Int());
                corY[1] = lastShape.getY2Int() - (y2 - lastShape.getY1Int());
            } else if (Math.abs(xSelected - lastShape.getX2Int()) <= 10
                    && Math.abs(ySelected - lastShape.getY1Int()) <= 10
                    && (lastShape.getX1Int() <= x2 || lastShape.getY2Int() <= y2 || lastShape.getY2Int() >= y2)) {

                corX[0] = lastShape.getX1Int() + (lastShape.getX2Int() - x2);
                corX[1] = lastShape.getX2Int() - (lastShape.getX2Int() - x2);
                corY[0] = lastShape.getY1Int() - (lastShape.getY1Int() - y2);
                corY[1] = lastShape.getY2Int() + (lastShape.getY1Int() - y2);
            } else if (Math.abs(xSelected - lastShape.getX1Int()) <= 10
                    && Math.abs(ySelected - lastShape.getY2Int()) <= 10) {

                corX[0] = lastShape.getX1Int() - (lastShape.getX1Int() - x2);
                corX[1] = lastShape.getX2Int() + (lastShape.getX1Int() - x2);
                corY[0] = lastShape.getY1Int() + (lastShape.getY2Int() - y2);
                corY[1] = lastShape.getY2Int() - (lastShape.getY2Int() - y2);
            } else if (Math.abs(xSelected - lastShape.getX2Int()) <= 10
                    && Math.abs(ySelected - lastShape.getY2Int()) <= 10) {
                corX[0] = lastShape.getX1Int() + (lastShape.getX2Int() - x2);
                corX[1] = lastShape.getX2Int() - (lastShape.getX2Int() - x2);
                corY[0] = lastShape.getY1Int() + (lastShape.getY2Int() - y2);
                corY[1] = lastShape.getY2Int() - (lastShape.getY2Int() - y2);
            }
        } else if (modify) {
            if (Math.abs(xSelected - oldX1) <= 10 && Math.abs(ySelected - oldY1) <= 10 && oldX1 >= x2) {
                corX[0] = oldX1 - (oldX1 - x2);
                corX[1] = oldX2;
                corY[0] = oldY1 - (oldY1 - y2);
                corY[1] = oldY2;
            } else if (Math.abs(xSelected - oldX1) <= 10 && Math.abs(ySelected - oldY1) <= 10 && oldX1 <= x2) {// upper
                // in

                corX[0] = oldX1 + (x2 - oldX1);
                corX[1] = oldX2;
                corY[0] = oldY1 + (y2 - oldY1);
                corY[1] = oldY2;
            } else if (Math.abs(xSelected - oldX2) <= 10 && Math.abs(ySelected - oldY1) <= 10
                    && (oldX1 <= x2 || oldY2 <= y2 || oldY2 >= y2)) {

                corX[0] = oldX1;
                corX[1] = oldX2 - (oldX2 - x2);
                corY[0] = oldY1 - (oldY1 - y2);
                corY[1] = oldY2;
            } else if (Math.abs(xSelected - oldX1) <= 10 && Math.abs(ySelected - oldY2) <= 10) {

                corX[0] = oldX1 - (oldX1 - x2);
                corX[1] = oldX2;
                corY[0] = oldY1;
                corY[1] = oldY2 - (oldY2 - y2);
            } else if (Math.abs(xSelected - oldX2) <= 10 && Math.abs(ySelected - oldY2) <= 10) {

                corX[0] = oldX1;
                corX[1] = oldX2 - (oldX2 - x2);
                corY[0] = oldY1;
                corY[1] = oldY2 - (oldY2 - y2);
            }
        }
        return new RectangleImp(corX, corY, null);
    }

    @Override
    public ShapeIF move(ShapeIF lastShape, int deltaX, int deltaY) {
        // TODO Auto-generated method stub
        int[] tempX = lastShape.getXCoordinates().clone();
        int[] tempY = lastShape.getYCoordinates().clone();
        for (int i = 0; i < lastShape.getXCoordinates().length; i++) {
            tempX[i] += deltaX;
        }
        for (int i = 0; i < lastShape.getYCoordinates().length; i++) {
            tempY[i] += deltaY;
        }
        return new RectangleImp(tempX, tempY, null);
    }

}
