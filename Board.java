import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

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
import java.net.URL;
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
	
	int moves[][]=new int[196][2];
	int moveptr=0;
	Image image;//used for image drawing
	
	int imageindex=0;
	int repaintcheck=0;//flag to check if the background is allowed to change
	String filestring[]=new String[5];//used to hold url strings of images
	
	//constructor
	Board(){

	}
	
	public void paint(Graphics g){//panel.getWidth
		int width=this.getWidth()-1;
		int height=this.getHeight()-1;
		
		//using images from wallpaper warrior on the internet. These are not our personal images
		filestring[0]="http://wallpaperwarrior.com/wp-content/uploads/2016/09/Wallpaper-11.jpg";
		filestring[1]="http://wallpaperwarrior.com/wp-content/uploads/2016/09/Wallpaper-21.jpg";
		filestring[2]="http://wallpaperwarrior.com/wp-content/uploads/2016/08/Soccer-Wallpaper-8.jpg";
		
		super.paint(g);
		//put this in a for loop and make grid
		//File backgrounds = new File(filestring[imageindex]);
		try {
			if(repaintcheck==0){
			URL url = new URL(filestring[imageindex]);
			image=ImageIO.read(url);
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(), null);
			repaintcheck=1;
			}
			else{//BufferedImage image=ImageIO.read(backgrounds);
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(), null);
			}
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
			
			this.moves[this.moveptr][0]=x;
			this.moves[this.moveptr][1]=y;
			this.moveptr++;
			
			System.out.println(this.cells[x][y]);
			this.repaint();
			this.checkwin();
			if(this.currentcolor==1)this.currentcolor=2;else this.currentcolor=1;
		}
	}
	
	void undo(){
		
		if(moveptr!=0)this.moveptr--;
		this.cells[this.moves[this.moveptr][0]][this.moves[this.moveptr][1]]=0;
		this.repaint();
		if(this.currentcolor==1)this.currentcolor=2;else this.currentcolor=1;
		
	}
	
	void restart(){
		
		moveptr=0;
		
		for(int i=0;i<=13;i++){
			for(int j=0;j<=13;j++){
				cells[i][j]=0;
			}
		}
		this.currentcolor=1;
		this.repaint();
	}
	
	void changebackground(){
		repaintcheck=0;
		if(imageindex!=2){
			imageindex++;
		}
		else{
			imageindex=0;
		}
		
		this.repaint();
	}
	
	void checkwin(){
		
	}
	
}

