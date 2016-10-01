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
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTree;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


public class Board extends JPanel {
	
	int cells[][]=new int[14][14];//used to mark where pieces are placed
	int currentcolor=1;//flags which color needs to be placed
	
	int winnerflag=0;//flags who wins the game
	
	int moves[][]=new int[196][2];//used to hold all the moves on the board for undo
	int moveptr=0;//used to move through the undo array
	Image image;//used for image drawing
	
	int imageindex=0;//used to go through the image array
	int repaintcheck=0;//flag to check if the background is allowed to change
	String filestring[]=new String[5];//used to hold url strings of images
	
	//constructor
	Board(){

	}
	
	public void paint(Graphics g){//panel.getWidth
		int width=this.getWidth()-1;//get width of panel
		int height=this.getHeight()-1;//get height of panel
		
		//using images from wallpaper warrior on the internet. These are not our personal images
		filestring[0]="http://wallpaperwarrior.com/wp-content/uploads/2016/09/Wallpaper-11.jpg";//store first url
		filestring[1]="http://wallpaperwarrior.com/wp-content/uploads/2016/09/Wallpaper-21.jpg";//store second url
		filestring[2]="http://wallpaperepic.com/wp-content/uploads/2016/03/Cell-Phone-Wallpapers-AF.jpg";//store third url
		
		super.paint(g);
		//put this in a for loop and make grid
		//File backgrounds = new File(filestring[imageindex]);
		try {
			if(repaintcheck==0){//used so that the background isnt reloaded constantly
			URL url = new URL(filestring[imageindex]);//load image from url
			image=ImageIO.read(url);//load image from url
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(), null);//draw background
			repaintcheck=1;//flag to not repaint
			}
			else{//BufferedImage image=ImageIO.read(backgrounds);
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(), null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<=15;i++){//for loop for making grids
			
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
		
		Font f = new Font("Comic Sans MS", Font.BOLD, 20);//set the color of font
        g.setFont(f);
		
		if(winnerflag==1){
			//g.setColor(Color.RED);
			//g.drawString("WHITE IS THE WINNER", 150, 150);
		}
		else if(winnerflag==2){
			//g.setColor(Color.RED);
			//g.drawString("BLACK IS THE WINNER", 150, 150);
			
		}
		
	}
	
	void click(int x,int y){//used to get coordinates of the click
		int cellx=(x-this.getWidth()/30)*15/this.getWidth();//store coordinates for x
		int celly=(y-this.getHeight()/30)*15/this.getHeight();//store coordinates for y
		
		System.out.println("coords: "+cellx+","+celly);//printing coords for debugging
		
		if(cellx>=0&&cellx<=13&&celly>=0&&celly<=13){//if within the range place a piece
			this.place(cellx,celly,this.currentcolor);
		}
	}

	void place(int x, int y,int color){//method for placing pieces
		
		if(this.cells[x][y]==0){//check if intersection is free for piece
			this.cells[x][y]=color;//set the cell for a color
			
			this.moves[this.moveptr][0]=x;//increment moves stack
			this.moves[this.moveptr][1]=y;//incrment move stack
			this.moveptr++;//increment move ptr
			
			System.out.println(this.cells[x][y]);//print for debugging
			this.repaint();//repaint to show updates
			this.checkwin();//check for win
			if(this.currentcolor==1)this.currentcolor=2;else this.currentcolor=1;//change color flag
		}
	}
	
	void undo(){//undo method
		
		if(moveptr!=0)this.moveptr--;//if undo stack is empty skip
		this.cells[this.moves[this.moveptr][0]][this.moves[this.moveptr][1]]=0;//reset board area
		this.repaint();//update board
		if(this.currentcolor==1)this.currentcolor=2;else this.currentcolor=1;//reset color flag
		this.winnerflag=0;//reset winner flag
		this.checkwin();//check for win
		
	}
	
	void restart(){//restart method
		
		moveptr=0;//moveptr
		
		for(int i=0;i<=13;i++){//for loops to reset board
			for(int j=0;j<=13;j++){
				cells[i][j]=0;
			}
		}
		this.currentcolor=1;//reset color
		this.winnerflag=0;//reset winner
		this.repaint();//repaint to update board
	}
	
	void changebackground(){//change background
		repaintcheck=0;//set repaint flag to zero
		if(imageindex!=2){//move through array of images
			imageindex++;
		}
		else{
			imageindex=0;//reset array
		}
		
		this.repaint();//update board
	}
	
	void checkwin(){//check win
		//horizontal
		for(int i=0;i<=13;i++){//for loops to move through board
			for(int j=0;j<=9;j++){
				
				int whitecount=0;
				int blackcount=0;
				
				//changed 5 to 4
				for(int k=j;k<=j+4;k++){
					if(this.cells[k][i]==1){
						whitecount++;
					}
					
					if(this.cells[k][i]==2){
						blackcount++;
					}
					
					//if(whitecount==5||blackcount==5){
						//System.out.println("congratulations, you win!");
					//}
					if(whitecount==5){
						winnerflag=1;
						this.repaint();
					}
					else if(blackcount==5){
						winnerflag=2;
						this.repaint();
					}
				}
				
			}
		}
		
		//vertical
		for(int i=0;i<=13;i++){
			for(int j=0;j<=9;j++){
				
				int whitecount=0;
				int blackcount=0;
				
				//changed 5 to 4
				for(int k=j;k<=j+4;k++){
					if(this.cells[i][k]==1){
						whitecount++;
					}
					
					if(this.cells[i][k]==2){
						blackcount++;
					}
					
					//if(whitecount==5||blackcount==5){
						//System.out.println("congratulations, you win!");
					//}
					if(whitecount==5){
						winnerflag=1;
						this.repaint();
					}
					else if(blackcount==5){
						winnerflag=2;
						this.repaint();
					}
				}
				
			}
		}
		
		//diagonal-right
		for(int i=0;i<=9;i++){
			for(int j=0;j<=9;j++){
				
				int whitecount=0;
				int blackcount=0;
				
				//changed 5 to 4
				for(int k=0;k<=4;k++){
					if(this.cells[i+k][j+k]==1){
						whitecount++;
					}
					
					if(this.cells[i+k][j+k]==2){
						blackcount++;
					}
					
					//if(whitecount==5||blackcount==5){
						//System.out.println("congratulations, you win!");
					//}
					if(whitecount==5){
						winnerflag=1;
						this.repaint();
					}
					else if(blackcount==5){
						winnerflag=2;
						this.repaint();
					}
				}
				
			}
		}
		
		//diagonal-left
		for(int i=4;i<=13;i++){
			for(int j=0;j<=9;j++){
				
				int whitecount=0;
				int blackcount=0;
				
				//changed 5 to 4
				for(int k=0;k<=4;k++){
					if(this.cells[i-k][j+k]==1){
						whitecount++;
					}
					
					if(this.cells[i-k][j+k]==2){
						blackcount++;
					}
					
					//if(whitecount==5||blackcount==5){
						//System.out.println("congratulations, you win!");
					//}
					if(whitecount==5){
						winnerflag=1;
						this.repaint();
					}
					else if(blackcount==5){
						winnerflag=2;
						this.repaint();
					}
				}
				
			}
		}
		
	}
	
}

