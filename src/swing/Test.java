package swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import eg.edu.alexu.csd.oop.calculator.Calc1;

/**
 * GUI for calculator.
 * @author Arsanuos
 */
public class Test {
	/**
	 * The frame.
	 */
	private JFrame frame;
	/**
	 * The input textBox.
	 */
	private JTextField textField;
	/**
	 * Instance of calculator class.
	 */
	private Calc1 temp;
	/**
	 * lb for the result.
	 */
	private JLabel lb2;

	/**
	 * main method for GUI startup.
	 * @param args
	 *            i don't know.
	 */
	public static void main(final String[] args) {
		new Test();
	}

	/**
	 * class constructor.
	 */
	public Test() {
		temp = new Calc1();
		construct();
	}

	/**
	 * function to construct the frame and add every element to it.
	 * @author Arsanuos.
	 */
	public final void construct() {
		final int two = 2;
		final int three = 3;
		final int four = 4;
		final int five = 5;
		final int fourtyFive = 45;
		final int oneSevenFive = 175;
		final int fifteen = five * three;
		final int difY = 30;
		final int difx = 60;
		final int frameX = 280;
		final int frameY = 320;
		final int prevX = 10;
		final int prevY = 10;
		final int nextY = 10;
		final int tBoxSizeX = 10;
		final int tBoxSizeY = 40;
		final int tLocX = two * difY + three * difx;
		final int tLocY = 30;
		final int lb1Bou1 = 15;
		final int lb1Bou2 = 30;
		final int lb1Bou3 = 100;
		final int lb2Bou1 = 75;
		final int lb2Bou2 = 30;
		final int lb2Bou3 = 100;
		final int lb2Bou4 = 100;
		final int bt1X = 20;
		final int bt1Y = 90;
		frame = new JFrame("Calculator");
		frame.setSize(frameX, frameY);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		mkbt("Prev", prevX, prevY);
		mkbt("Next", three * (difx + three), nextY);
		textField = new JTextField();
		textField.setBounds(tBoxSizeX, tBoxSizeY, tLocX, tLocY);
		frame.add(textField);
		JLabel lb = new JLabel();
		lb.setBounds(lb1Bou1, lb1Bou2, lb1Bou3, lb1Bou3);
		lb.setText("Result is :");
		frame.add(lb);
		lb2 = new JLabel();
		lb2.setBounds(lb2Bou1, lb2Bou2, lb2Bou3, lb2Bou4);
		frame.add(lb2);
		mkbt("1", bt1X, bt1Y);
		mkbt("2", bt1X + difx, bt1Y);
		mkbt("3", bt1X + two * difx, bt1Y);
		mkbt("+", bt1X + three * difx, bt1Y);
		mkbt("4", bt1X, bt1Y + difY);
		mkbt("5", bt1X + difx, bt1Y + difY);
		mkbt("6", bt1X + two * difx, bt1Y + difY);
		mkbt("- ", bt1X + three * difx, bt1Y + difY);
		mkbt("7", bt1X, bt1Y + two * difY);
		mkbt("8", bt1X + difx, bt1Y + two * difY);
		mkbt("9", bt1X + two * difx, bt1Y + two * difY);
		mkbt("*", bt1X + three * difx, bt1Y + two * difY);
		mkbt("0", bt1X + difx, bt1Y + three * difY);
		mkbt("(", bt1X, bt1Y + three * difY);
		mkbt(")", bt1X + two * difx, bt1Y + three * difY);
		mkbt("/", bt1X + three * difx, bt1Y + three * difY);
		mkbt(".", bt1X + three * difx, bt1Y + four * difY);
		mkbt("Save", bt1X, bt1Y + four * difY);
		mkbt("Load", fourtyFive + fifteen + difx, bt1Y + four * difY);
		mkbt("Calculate", bt1X, bt1Y + five * difY);
		mkbt("Enter", oneSevenFive + three, bt1Y + five * difY);
		frame.setVisible(true);
	}

	/**
	 * print x.
	 * @param x
	 *            value to be printed.
	 */
	public final void print(final Object x) {
		System.out.println(x);
	}

	/**
	 * @param name
	 *            button name.
	 * @param x
	 *            location x axis.
	 * @param y
	 *            location y axis.
	 * @return button.
	 */
	public final JButton mkbt(final String name, final int x, final int y) {
		JButton bt = new JButton(name);
		Dimension size = bt.getPreferredSize();
		final int fourtyOne = 41;
		if (size.width < fourtyOne) {
			size.width = fourtyOne;
		}
		bt.setBounds(x, y, size.width, size.height);
		frame.add(bt);
		String invalid = "Invalid expression.";
		if (name == "Calculate") {
			bt.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
					String t = null;
					try {
					if (!textField.getText().isEmpty()) {
						temp.input(textField.getText());
						t = temp.getResult();
				if (t == null || t.equalsIgnoreCase("null")) {
				JOptionPane.showMessageDialog(frame, invalid);
							} else {
						lb2.setText(temp.getResult());
							}
						} else {
				JOptionPane.showMessageDialog(frame, invalid);
						}
					} catch (RuntimeException e5) {
				JOptionPane.showMessageDialog(frame, invalid);
					}
				}
			});
		} else if (name == "Save") {
			bt.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
					try {
				JFileChooser saveFile = new JFileChooser();
						saveFile.showSaveDialog(null);
			temp.setFile(saveFile.getSelectedFile().getPath());
				if (temp.getFile().contains(".txt")) {
							temp.save();
						} else {
					temp.setFile(temp.getFile() + ".txt");
							temp.save();
						}
					} catch (RuntimeException e4) {
			JOptionPane.showMessageDialog(frame, "Can't save file");
					}
				}
			});
		} else if (name == "Load") {
			bt.addActionListener(new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
					try {
				JFileChooser loadFile = new JFileChooser();
						loadFile.showOpenDialog(null);
			temp.setFile(loadFile.getSelectedFile().getName());
						temp.load();
				textField.setText(temp.current());
					} catch (RuntimeException e3) {
			JOptionPane.showMessageDialog(frame, "Can't load file");
					}
				}
			});
		} else if (name == "Prev") {
			bt.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
					String prev = temp.prev();
					if (prev == null) {
			String noPre = "There isn't any previous expression";
			JOptionPane.showMessageDialog(frame, noPre);
					} else {
						textField.setText(prev);
					}
				}
			});
		} else if (name == "Next") {
			bt.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
					String next = temp.next();
					if (next == null) {
			String noNext = "There isn't any next expression";
			JOptionPane.showMessageDialog(frame, noNext);
					} else {
						textField.setText(next);
					}
				}
			});
		} else if (name == "Enter") {
			bt.addActionListener(new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
					try {
			if (!textField.getText().isEmpty()) {
				temp.input(textField.getText());
						} else {
				JOptionPane.showMessageDialog(frame, invalid);
						}
					} catch (RuntimeException e5) {
			JOptionPane.showMessageDialog(frame, invalid);
					}
				}
			});
		} else {
			bt.addActionListener(new ActionListener() {
		public void actionPerformed(final ActionEvent e) {
			textField.setText(textField.getText() + name);
				}
			});
		}
		return bt;
	}
}
