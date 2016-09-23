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
import javax.swing.JMenu;
import javax.swing.JTree;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Board extends JPanel {
	
	int cells[][]=new int[14][14];
	int currentcolor=1;
	
	//constructor
	Board(){

	}
	
	public void paint(Graphics g){//panel.getWidth
		int width=this.getWidth()-1;
		int height=this.getHeight()-1;
		
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
			
			g.drawLine(0,i*height/15,width,i*height/15);//draw horizontal lines for the go board
			g.drawLine(i*width/15,0,i*width/15,height);//draw vertical lines for the go board
			g.setColor(Color.BLACK);//set the line colors to black
			
		}
		
		//do for loop to render pieces
		for(int i=0;i<=13;i++){
			for(int j=0;j<=13;j++){
				
				if(cells[i][j]!=0){
					
					if(cells[i][j]==1)g.setColor(Color.WHITE);else g.setColor(Color.BLACK);
					
					g.fillOval((width*i)/15+(width/23), (height*j)/15+(height/23), width/20, height/20);
					
				}
				
			}
		}
		
	}
	
	void click(int x,int y){
		int cellx=(x-this.getWidth()/30)*15/this.getWidth();
		int celly=(y-this.getHeight()/30)*15/this.getHeight();
		
		System.out.println("coords: "+cellx+","+celly);
		
		if(cellx>=0&&cellx<=13&&celly>=0&&celly<=13){
			this.place(cellx,celly,this.currentcolor);
		}
	}

	void place(int x, int y,int color){
		
		if(this.cells[x][y]==0){
			this.cells[x][y]=color;
			System.out.println(this.cells[x][y]);
			this.repaint();
			if(this.currentcolor==1)this.currentcolor=2;else this.currentcolor=1;
		}
	}
	
}
