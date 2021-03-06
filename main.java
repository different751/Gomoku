/*Group Members: Tyler Coy and Sage Thongsrisubsku
 *Date:9/29/16
 *Class: CS320 
 *
 *Assignment:
 *This program is a game called Gomoku
 *The players have to get 5 of their pieces
 *in a row either vertical, horizontal, or 
 *diagonal. The players can can change 
 *the background using the pull down menu
 *and can undo moves as they play with the
 *buttons on the window. This assignment
 *was fully developed using pair programming
 *approach.
 * 
 */
package Assignment2_pairprogramming;
import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTree;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
	
	Board panel;

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
	
	//this is where all our graphics stuff was
	

	/**
	 * Create the frame.
	 */
	public Main() {
		
		
		/*getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("coords: "+e.getX()+","+e.getY());
				
				System.out.println("cell: "+(((e.getX()-70)/25))+","+(((e.getY()-26)/26)));
				
			}
		});*/
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 680);
		
		
		
		
		
		JButton btnNewButton_2 = new JButton("Quit");//quit button
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);//exit program
			}
		});
		btnNewButton_2.setBounds(356, 596, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		final Board panel_1 = new Board();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.click(e.getX(),e.getY());
				if(panel_1.winnerflag==1){
					JOptionPane.showMessageDialog(panel_1, "White is the Winner!");
					panel_1.restart();
				}
				else if(panel_1.winnerflag==2){
					JOptionPane.showMessageDialog(panel_1, "Black is the Winner!");
					panel_1.restart();
				}
			}
		});
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 11, 574, 574);
		getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.undo();
			}
		});
		btnNewButton.setBounds(257, 596, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Restart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.restart();
			}
		});
		btnNewButton_1.setBounds(158, 596, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);
		
		JMenuItem mntmChangeBackground = new JMenuItem("Change Background");
		mnSystem.add(mntmChangeBackground);
		mntmChangeBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.changebackground();
			}
		});
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnSystem.add(mntmQuit);
		getContentPane().setLayout(null);
		
	}
}

