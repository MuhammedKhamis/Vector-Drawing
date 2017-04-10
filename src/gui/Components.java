package gui;

import javax.swing.JFrame;

class Components {
	
	private JFrame frame;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Components s = new Components();
	}
	
	Components(){
		start();
	}
	private void start()
	{
		frame = new JFrame("Paint");
		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
	
}
