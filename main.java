mport java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTree;

public class Main extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void paint(Graphics g){//panel.getWidth
		super.paint(g);
		//put this in a for loop and make grid
		File pope = new File("C:\\Users\\Tyler\\workspace\\Assignment2_pairprogramming\\src\\TNzMXds.jpg");
		

		try {
			BufferedImage image=ImageIO.read(pope);
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<=15;i++){
			
			int width=this.getWidth();
			int height=this.getHeight();
			int hoffset=10+this.getWidth()/10;
			int voffset=10+this.getHeight()/10;
			
			g.drawLine(hoffset,i*height/20+voffset,15*width/20+hoffset,i*height/20+voffset);//draw horizontal lines for the go board
			g.drawLine(i*width/20+hoffset,voffset,i*width/20+hoffset,15*height/20+voffset);//draw vertical lines for the go board
			g.setColor(Color.BLACK);//set the line colors to black
		}
		
	}
	

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 522);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);
		
		JMenuItem mntmChangeBackground = new JMenuItem("Change Background");
		mnSystem.add(mntmChangeBackground);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnSystem.add(mntmQuit);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(205, 438, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Restart");
		btnNewButton_1.setBounds(106, 438, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quit");
		btnNewButton_2.setBounds(304, 438, 89, 23);
		getContentPane().add(btnNewButton_2);
		
	}
}
