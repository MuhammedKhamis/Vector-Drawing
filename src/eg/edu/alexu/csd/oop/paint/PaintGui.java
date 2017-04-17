package eg.edu.alexu.csd.oop.paint;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.awt.event.ActionEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;

import javax.swing.JFileChooser;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;

public class PaintGui {

	private JButton yellow, red, green, blue, moreColors, clear;
	// private JTextField textField, textField_1, textField_2, textField_3,
	// textField_4, textField_5;
	// private JPanel panel_1;
	private JFrame frame;
	private JLabel status = new JLabel();
	private PaintArea area = new PaintArea();
	private JPanel panel;
	// private boolean flag = true;
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(yellow)) {
				area.yellow();
			} else if (e.getSource().equals(red)) {
				area.red();
			} else if (e.getSource().equals(green)) {
				area.green();
			} else if (e.getSource().equals(blue)) {
				area.blue();
			} else if (e.getSource().equals(moreColors)) {
				Color c = JColorChooser.showDialog(null, "Select another Color", Color.BLACK);
				if (c != null) {
					area.setColor(c);
				}
			} else if (e.getSource().equals(clear)) {
				area.clearAllComp();
			} else if (e.paramString().contains("Plug")) {

				JFileChooser loadFile = new JFileChooser();
				int returnVal = loadFile.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					Loader l = new Loader();
					l.setPathToJar(loadFile.getSelectedFile().getPath());
					area.setPath(loadFile.getSelectedFile().getPath());
					String[] names = l.names();
					if (names.length != 0) {
						frame.setVisible(false);
						makeButtons(names);
					} else {
						JOptionPane.showMessageDialog(null, "No classes found.");
					}
				}
			} else {
				area.setB(e.paramString());
			}
		}
	};
	private JMenuItem load;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintGui window = new PaintGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PaintGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 690, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblColors = new JLabel("Colors");
		GridBagConstraints gbc_lblColors = new GridBagConstraints();
		gbc_lblColors.insets = new Insets(0, 0, 5, 0);
		gbc_lblColors.gridx = 0;
		gbc_lblColors.gridy = 0;
		panel.add(lblColors, gbc_lblColors);

		yellow = new JButton("Yellow");
		GridBagConstraints gbc_yellow = new GridBagConstraints();
		gbc_yellow.fill = GridBagConstraints.BOTH;
		gbc_yellow.insets = new Insets(0, 0, 5, 0);
		gbc_yellow.gridx = 0;
		gbc_yellow.gridy = 1;
		yellow.addActionListener(actionListener);
		panel.add(yellow, gbc_yellow);

		red = new JButton("Red");
		GridBagConstraints gbc_red = new GridBagConstraints();
		gbc_red.fill = GridBagConstraints.BOTH;
		gbc_red.insets = new Insets(0, 0, 5, 0);
		gbc_red.gridx = 0;
		gbc_red.gridy = 2;
		red.addActionListener(actionListener);
		panel.add(red, gbc_red);

		green = new JButton("Green");
		GridBagConstraints gbc_green = new GridBagConstraints();
		gbc_green.fill = GridBagConstraints.BOTH;
		gbc_green.insets = new Insets(0, 0, 5, 0);
		gbc_green.gridx = 0;
		gbc_green.gridy = 3;
		green.addActionListener(actionListener);
		panel.add(green, gbc_green);

		blue = new JButton("Blue");
		GridBagConstraints gbc_blue = new GridBagConstraints();
		gbc_blue.fill = GridBagConstraints.BOTH;
		gbc_blue.insets = new Insets(0, 0, 5, 0);
		gbc_blue.gridx = 0;
		gbc_blue.gridy = 4;
		blue.addActionListener(actionListener);
		panel.add(blue, gbc_blue);

		moreColors = new JButton("More Colors");
		GridBagConstraints gbc_moreColors = new GridBagConstraints();
		gbc_moreColors.fill = GridBagConstraints.BOTH;
		gbc_moreColors.insets = new Insets(0, 0, 5, 0);
		gbc_moreColors.gridx = 0;
		gbc_moreColors.gridy = 5;
		moreColors.addActionListener(actionListener);
		panel.add(moreColors, gbc_moreColors);

		JLabel lblShapes = new JLabel("Shapes");
		GridBagConstraints gbc_lblShapes = new GridBagConstraints();
		gbc_lblShapes.insets = new Insets(0, 0, 5, 0);
		gbc_lblShapes.gridx = 0;
		gbc_lblShapes.gridy = 6;
		panel.add(lblShapes, gbc_lblShapes);

		JButton line = new JButton("Line");
		GridBagConstraints gbc_line = new GridBagConstraints();
		gbc_line.fill = GridBagConstraints.BOTH;
		gbc_line.insets = new Insets(0, 0, 5, 0);
		gbc_line.gridx = 0;
		gbc_line.gridy = 7;
		line.addActionListener(actionListener);
		panel.add(line, gbc_line);

		JButton rectangle = new JButton("Rectangle");
		GridBagConstraints gbc_rectangle = new GridBagConstraints();
		gbc_rectangle.fill = GridBagConstraints.BOTH;
		gbc_rectangle.insets = new Insets(0, 0, 5, 0);
		gbc_rectangle.gridx = 0;
		gbc_rectangle.gridy = 9;
		rectangle.addActionListener(actionListener);
		panel.add(rectangle, gbc_rectangle);

		JButton triangle = new JButton("Triangle");
		GridBagConstraints gbc_triangle = new GridBagConstraints();
		gbc_triangle.fill = GridBagConstraints.BOTH;
		gbc_triangle.insets = new Insets(0, 0, 5, 0);
		gbc_triangle.gridx = 0;
		gbc_triangle.gridy = 10;
		triangle.addActionListener(actionListener);
		panel.add(triangle, gbc_triangle);

		JButton ellipse = new JButton("Ellipse");
		GridBagConstraints gbc_ellipse = new GridBagConstraints();
		gbc_ellipse.fill = GridBagConstraints.BOTH;
		gbc_ellipse.insets = new Insets(0, 0, 5, 0);
		gbc_ellipse.gridx = 0;
		gbc_ellipse.gridy = 11;
		ellipse.addActionListener(actionListener);
		panel.add(ellipse, gbc_ellipse);

		clear = new JButton("Clear");
		GridBagConstraints gbc_clear = new GridBagConstraints();
		gbc_clear.fill = GridBagConstraints.BOTH;
		gbc_clear.insets = new Insets(0, 0, 5, 0);
		gbc_clear.gridx = 0;
		gbc_clear.gridy = 12;
		clear.addActionListener(actionListener);
		panel.add(clear, gbc_clear);

		JButton delete = new JButton("Delete");
		GridBagConstraints gbc_delete = new GridBagConstraints();
		gbc_delete.fill = GridBagConstraints.HORIZONTAL;
		gbc_delete.insets = new Insets(0, 0, 5, 0);
		gbc_delete.gridx = 0;
		gbc_delete.gridy = 13;
		delete.addActionListener(actionListener);
		panel.add(delete, gbc_delete);

		JButton resize = new JButton("Resize");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 14;
		resize.addActionListener(actionListener);
		panel.add(resize, gbc_btnNewButton);

		JButton modify = new JButton("Modify");
		GridBagConstraints gbc_modify = new GridBagConstraints();
		gbc_modify.insets = new Insets(0, 0, 5, 0);
		gbc_modify.fill = GridBagConstraints.BOTH;
		gbc_modify.gridx = 0;
		gbc_modify.gridy = 15;
		modify.addActionListener(actionListener);
		panel.add(modify, gbc_modify);

		JButton fill = new JButton("Fill");
		GridBagConstraints gbc_fill = new GridBagConstraints();
		gbc_fill.fill = GridBagConstraints.BOTH;
		gbc_fill.insets = new Insets(0, 0, 5, 0);
		gbc_fill.gridx = 0;
		gbc_fill.gridy = 16;
		fill.addActionListener(actionListener);
		panel.add(fill, gbc_fill);

		Panel panel_2 = new Panel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel_2.add(status);
		
		frame.getContentPane().add(area, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Save files", "json", "xml"));
				int returnValue = fileChooser.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String name = selectedFile.getName();
					String ext = null;
					if (name.lastIndexOf(".") != -1) {
						ext = name.substring(name.lastIndexOf("."), name.length());
						area.save(selectedFile.getAbsolutePath(), ext);
					} else {
						ext = new String(".xml");
						if (!name.equals(""))
							area.save(selectedFile.getAbsolutePath() + ext, ext);

					}
				}
			}
		});
		file.add(save);

		load = new JMenuItem("Load");
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Save files", "json", "xml"));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					if (selectedFile.exists()) {
						String name = selectedFile.getName();
						String ext = null;
						if (name.lastIndexOf(".") != -1)
							ext = name.substring(name.lastIndexOf("."), name.length());
						else {
							ext = new String(".xml");
						}
						if (!name.equals(""))
							area.load(selectedFile.getAbsolutePath(), ext);

					} else {
						JOptionPane.showMessageDialog(null, "You must select an existing file!");
					}
				}

			}
		});
		file.add(load);

		JSeparator separator = new JSeparator();
		file.add(separator);

		JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				area.undoRedo(false);
			}
		});
		file.add(undo);

		JMenuItem redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				area.undoRedo(true);
			}
		});
		file.add(redo);

		JMenu tools = new JMenu("Tools");
		menuBar.add(tools);

		JMenuItem plugIn = new JMenuItem("Add Plug-in");
		plugIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		plugIn.addActionListener(actionListener);
		tools.add(plugIn);

	}

	public void makeButtons(String[] names) {
		int value = 17;
		for (int i = 0; i < names.length; i++) {
			JButton fill = new JButton(names[i]);
			GridBagConstraints gbc_fill = new GridBagConstraints();
			gbc_fill.fill = GridBagConstraints.BOTH;
			gbc_fill.insets = new Insets(0, 0, 5, 0);
			gbc_fill.gridx = 0;
			gbc_fill.gridy = value;
			value++;
			fill.addActionListener(actionListener);
			panel.add(fill, gbc_fill);
			frame.setBounds(100, 100, 755, 655);
		}
		frame.setVisible(true);
	}
}
