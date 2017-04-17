package eg.edu.alexu.csd.oop.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JComponent;

import actions.Actions;
import saveAndLoad.IF.LoadIF;
import saveAndLoad.IF.SaveIF;
import saveAndLoad.Imp.LoadImp;
import saveAndLoad.Imp.SaveImp;

//extends JComponent to add mouse listeners
public class PaintArea extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String button = "move";
	private int x1, x2 = x1, y1, y2 = y1, lastX, lastY, counter = 0;
	private Graphics2D g2;
	private Image image;
	private ShapeIF currentShapeObject;
	private boolean resize = false, modify = false, delete = false, fill = false;
	private Color c = Color.black;
	private LinkedList<ShapeIF> shapes = new LinkedList<ShapeIF>();
	private int resizePos = -1;
	private Stack<Actions> undo = new Stack<Actions>();
	private Stack<Actions> redo = new Stack<Actions>();
	private int[] corX, corY, tempX, tempY;
	private boolean drawingTriangle = false;
	private int firstX, firstY, secondX, secondY;
	private int xSelected, ySelected;
	private ShapeIF currentModifyObject;
	private boolean lastFill = false;
	private int[] cntr = null;
	private SaveIF saver;
	private LoadIF loader;
	private String path;

	// constructor to add mouse listeners
	PaintArea() {
		setDoubleBuffered(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				y2 = e.getY();
				x2 = e.getX();
				corX = new int[2];
				corY = new int[2];
				corX[0] = x1;
				corX[1] = x2;
				corY[0] = y1;
				corY[1] = y2;
				switch (button) // 0 for line, 1 for rect, 2 for oval
				{
				case "Line":
					currentShapeObject = new LineSeg(x1, x2, y1, y2);
					break;
				case "Rectangle":
					currentShapeObject = new RectangleImp(corX, corY, null);
					break;
				case "Ellipse":
					currentShapeObject = new Ellipse(x1, x2, y1, y2);
					break;
				}
				// i don't understand this part
				if (button != "move") {
					if (drawingTriangle) {
						counter++;
						if (counter == 1) {
							button = "Triangle";
							firstX = x1;
							firstY = y1;
							secondX = x2;
							secondY = y2;
							// remove line form triagnle after move
							if (resizePos != -1 && shapes.get(resizePos).getType() == "Line") {
								shapes.remove(resizePos);
								currentShapeObject = null;
							}
						} else {
							button = "move";
							counter = 0;
							drawingTriangle = false;
							if (shapes.size() >= 1 && shapes.getLast().getType() == "Line") {
								shapes.removeLast();
							}
							if (Math.abs(secondX - x1) <= 15 && Math.abs(secondY - y1) <= 15) {
								currentShapeObject = new TriangleImp(new int[] { firstX, secondX, x2 },
										new int[] { firstY, secondY, y2 });
								// remove the last line.
								undo.pop();
							} else {
								currentShapeObject = null;
							}
						}
					} else {
						button = "move";
						redo.clear();
					}
				} else if (delete) {
					delete = false;
					undo.push(new Actions(shapes.get(resizePos).copy(), null, resizePos));
					shapes.set(resizePos, null);
					resizePos = -1;
					cntr = null;

				}
				if (currentShapeObject != null) {
					currentShapeObject.setC(c);
					if (resizePos != -1) {
						shapes.add(resizePos, currentShapeObject);
						if (drawingTriangle && !resize && !modify) {
							// remove the line(in triangle) form undo.
							currentModifyObject = null;
							drawingTriangle = false;
						}
						if (currentModifyObject != null)
							// to handle selecting the line during drawing
							if (currentModifyObject.getType() == "Line" && currentShapeObject.getType() == "Triangle") {
								undo.push(new Actions(null, currentShapeObject.copy(), resizePos));
							} else {
								undo.push(
										new Actions(currentModifyObject.copy(), currentShapeObject.copy(), resizePos));
							}
						else
							undo.push(new Actions(null, currentShapeObject.copy(), resizePos));
					} else {
						shapes.addLast(currentShapeObject);
						undo.push(new Actions(null, currentShapeObject.copy(), shapes.size() - 1));
					}
					print(resizePos);
					currentShapeObject = null;
					cntr = null;
				}
				resizePos = -1;
				repaint();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				x1 = e.getX();
				y1 = e.getY();
				for (int i = shapes.size() - 1; i >= 0; i--) {
					if (shapes.get(i) == null) {
						continue;
					}
					if (shapes.get(i).co((int) x1, (int) y1)) {
						resizePos = i;
						cntr = shapes.get(resizePos).getOuter();
						System.out.println(shapes.get(i).toString());
						System.out.println("Found Point");
						xSelected = x1;
						ySelected = y1;
						currentModifyObject = shapes.get(resizePos).copy();
						if (currentModifyObject.getType() == "Triangle") {
							tempX = shapes.get(resizePos).getXCoordinates().clone();
							tempY = shapes.get(resizePos).getYCoordinates().clone();
						}
						if (fill) {
							ShapeIF temp = shapes.get(resizePos).copy();
							shapes.get(resizePos).setC(c);
							shapes.get(resizePos).setFill(fill);
							undo.push(new Actions(temp.copy(), shapes.get(resizePos).copy(), resizePos));
							fill = false;
						}
						c = shapes.get(resizePos).getC();
						break;
					} else {
						resizePos = -1;
						cntr = null;
					}
				}
				lastX = x1;
				lastY = y1;
			}
		});

		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				x2 = e.getX();
				y2 = e.getY();
				if (button == "Resize" && resizePos != -1 && (resize || modify)) {
					c = shapes.get(resizePos).getC();
					lastFill = shapes.get(resizePos).isFill();
					currentShapeObject = currentModifyObject.modify(currentModifyObject, xSelected, ySelected, x1, x2,
							y1, y2, resize, modify);
					currentShapeObject.setC(c);
					currentShapeObject.setFill(lastFill);
					// undo.push(new Actions(shapes.get(resizePos),
					// currentShapeObject, resizePos));
					shapes.set(resizePos, currentShapeObject);
					cntr = null;
				} else if (button == "move" && resizePos != -1) {
					ShapeIF tmp = shapes.get(resizePos);
					int deltaX, deltaY;
					deltaX = (x2 - lastX);
					deltaY = (y2 - lastY);
					currentShapeObject = currentModifyObject.move(tmp, deltaX, deltaY);
					cntr[0] += deltaX;
					cntr[1] += deltaY;
					currentShapeObject.setC(tmp.getC());
					currentShapeObject.setFill(tmp.isFill());
					// undo.push(new Actions(shapes.get(resizePos),
					// currentShapeObject, resizePos));
					// Waring we commented this line.
					shapes.set(resizePos, currentShapeObject);
				} else if (!drawingTriangle && button != "move" && button != "Resize") {
					corX = new int[2];
					corY = new int[2];
					corX[0] = x1;
					corX[1] = x2;
					corY[0] = y1;
					corY[1] = y2;
					switch (button) // 0 for line, 1 for rect, 2 for oval
					{
					case "Line":
						currentShapeObject = new LineSeg(x1, x2, y1, y2);
						break;
					case "Rectangle":
						currentShapeObject = new RectangleImp(corX, corY, null);
						break;
					case "Ellipse":
						currentShapeObject = new Ellipse(x1, x2, y1, y2);
						break;
					default:
						Loader l = new Loader();
						l.setPathToJar(path);
						Constructor<ShapeIF> constructor = l.invokeClassMethod(button);
						try {
							currentShapeObject = constructor.newInstance(corX, corY);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
				// i changed this part form inside to outside
				// and else if above
				// and .copy() in mouse pressed
				// this part i don't understand
				if (drawingTriangle && !resize) {
					currentShapeObject = new LineSeg(x1, x2, y1, y2);
				}
				if (currentShapeObject != null) {
					currentShapeObject.setC(c);
				}
				repaint();
				lastX = x2;
				lastY = y2;
			}

		});
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		// image to draw null ==> we create
		image = createImage(getSize().width, getSize().height);
		g2 = (Graphics2D) image.getGraphics();
		// to make line more beautiful
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// clear draw area
		clearAll(Color.black);

		for (int i = 0; i < shapes.size() - 1; i++) {
			if (shapes.get(i) == null) {
				continue;
			}
			for (int j = i + 1; j < shapes.size(); j++) {
				if (shapes.get(j) == null) {
					continue;
				}
				if (shapes.get(i).equals(shapes.get(j))) {
					shapes.remove(j);
				}
			}
		}
		for (int counter = shapes.size() - 1; counter >= 0; counter--) {
			if (shapes.get(counter) != null) {
				g2.setColor(shapes.get(counter).getC());
				g2.setPaint(shapes.get(counter).getC());
				g2 = shapes.get(counter).draw(g2);
			}
		}

		// draws the current Shape Object if it is not null
		if (currentShapeObject != null) {
			g2.setPaint(currentShapeObject.getC());
			g2 = currentShapeObject.draw(g2);
		}
		if (cntr != null) {
			g2.setColor(Color.BLACK);
			g2.drawRect(cntr[0], cntr[1], 3, 3);
		}

		g.drawImage(image, 0, 0, null);
		clearAll(Color.black);
	}

	public void clearAll(Paint c) {

		// white background
		g2.setPaint(Color.white);
		g2.setBackground(Color.white);
		// borders
		g2.fillRect(0, 0, getSize().width, getSize().height);
		// black pen
		g2.setPaint(c);
		repaint();
	}

	public void clearAllComp() {
		if (!shapes.isEmpty()) {
			shapes.clear();
		}
		g2.setPaint(Color.white);
		g2.setBackground(Color.white);
		// borders
		g2.fillRect(0, 0, getSize().width, getSize().height);
		// black pen
		g2.setPaint(Color.black);
		currentShapeObject = null;
		repaint();

	}

	public void yellow() {
		c = Color.yellow;
	}

	public void blue() {
		c = Color.blue;
	}

	public void black() {
		c = Color.black;
	}

	public void green() {
		c = Color.green;
	}

	public void red() {
		c = Color.red;
	}

	public void setB(String o) {
		drawingTriangle = false;
		if (o.contains("Line")) {
			this.resize = false;
			this.modify = false;
			button = "Line";
		} else if (o.contains("Rectangle")) {
			this.modify = false;
			this.resize = false;
			button = "Rectangle";
		} else if (o.contains("Ellipse")) {
			this.modify = false;
			this.resize = false;
			button = "Ellipse";
		} else if (o.contains("Triangle")) {
			this.modify = false;
			drawingTriangle = true;
			button = "Triangle";
			this.resize = false;
		} else if (o.contains("Circle")) {
			this.modify = false;
			button = "Circle";
			this.resize = false;
		} else if (o.contains("Resize")) {
			// add button delete, move and modify.
			this.resize = true;
			this.modify = false;
			button = "Resize";
		} else if (o.contains("Delete")) {
			this.delete = true;
		} else if (o.contains("Fill")) {
			this.fill = true;
		} else if (o.contains("Modify")) {
			this.resize = false;
			this.modify = true;
			button = "Resize";
		} else {
			button = o;
		}
	}

	public void setTri(int[] x, int[] y) {
		corX = x;
		corY = y;
	}

	public void undoRedo(boolean change) {
		if (change) {
			if (!redo.isEmpty()) {
				undo.push(redo.pop());
				print(undo.peek().executeRedo().toString());
				print(undo.peek().getIndex());
				print(shapes.size());
				shapes.set(undo.peek().getIndex(), undo.peek().executeRedo());
			}
		} else {
			if (!undo.isEmpty()) {
				redo.push(undo.pop());
				shapes.set(redo.peek().getIndex(), redo.peek().executeUndo());
			}
		}
		repaint();

	}

	public void setColor(Color c) {
		this.c = c;
		g2.setColor(c);
	}

	public void print(Object x) {
		System.out.println(x);
	}

	public void save(String path, String ext) {
		saver = new SaveImp();
		if (ext.equals(".xml")) {
			saver.read(undo, redo, shapes, path, true);
		} else if (ext.equals(".json")) {
			saver.read(undo, redo, shapes, path, false);
		}
		saver = null;
	}

	public void load(String path, String ext) {
		// TODO Auto-generated method stub
		boolean done = false;
		loader = new LoadImp(path);
		if (ext.equals(".xml"))
			done = loader.readAsXml();
		else if (ext.equals(".json"))
			done = loader.readAsJson();
		if (done) {
			redo = loader.loadRedo();
			undo = loader.loadUndo();
			shapes = loader.loadShapes();
			repaint();
		}
		loader = null;

	}

	public void setPath(String path) {
		this.path = path;
	}
}
