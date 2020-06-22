package corrida;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
	public class Gamework extends JPanel implements ActionListener, KeyListener{
	    private int space;
	    private int width=80;
	    private int height=70;
	    private int speed;
	    private int WIDTH=500;
	    private int HEIGHT=700;
	    private int count=1;
	    private int move = 20;
	    private ArrayList<Rectangle>carsini;
	    private ArrayList <Rectangle> line;
	    private Rectangle car;
	    private Random rand;
	    BufferedImage bg;
	    BufferedImage user;
	    BufferedImage op1;
	    BufferedImage op2;
	    BufferedImage op3;
	    BufferedImage road;
	    Boolean linef=true;
	    Timer t;
	    public Gamework(){
	      
					try {
						user=ImageIO.read(getClass().getResource("/Sprites/op3.png"));
						op1=ImageIO.read(getClass().getResource("/Sprites/op1.png"));
				        op2=ImageIO.read(getClass().getResource("/Sprites/op2,png"));
				        op3=ImageIO.read(getClass().getResource("/Sprites/op4.png"));
				        bg=ImageIO.read(getClass().getResource("/Sprites/bg.png"));
				        road=ImageIO.read(getClass().getResource("/Sprites/road.png"));

					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	           
				
	
	    	t=new Timer(20,this);
	    	rand=new Random();
	    	carsini=new ArrayList<Rectangle>();
	    	line=new ArrayList<Rectangle>();
	        car=new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
	    	space=300;
	        speed=4;
	        addKeyListener(this);
	        setFocusable(true);
	        addcars(true);
	        addcars(true);
	        addlines(true);
	        addlines(true);
	        addlines(true);
	        addlines(true);
	        addlines(true);
	        addlines(true);
	        addlines(true);
	        addlines(true);

	        t.start();
	    }
	    public void addlines(Boolean first){
	        int x=WIDTH/2-2;
	        int y=700;
	        int width=4;
	        int height=100;
	        int sp=130;
	        if(first){
	            line.add(new Rectangle(x,y-(line.size()*sp),width,height));
	        }else{
	            line.add(new Rectangle(x,line.get(line.size()-1).y-sp,width,height));
	        }
	    } 
	    public void addcars(boolean first) {
	    	int positionx=rand.nextInt()%2;
	    	int x = 0;
	    	int y=0;
	    	int Width=width; 
	    	int Height=height;
	    	if(positionx==0) {
	    		x=WIDTH/2-90;
	    	}else {
	    		x=WIDTH/2+10;
	    	}
	    	if(first) {
	    		carsini.add(new Rectangle(x,y-100-(carsini.size()*space),Width,Height));
	    	}else {
	    		carsini.add(new Rectangle(x,carsini.get(carsini.size()-1).y-300,Width,Height ));
	    	}
	    		
	    }
	    public void paintComponent(Graphics g){
	    	 super.paintComponents(g);
	         g.drawImage(bg, 0, 0, null);
	         g.drawImage(road, WIDTH/2-125,0,null);
	         g.setColor(Color.WHITE);
	         for(Rectangle rect:line){
	             g.fillRect(rect.x, rect.y, rect.width, rect.height);
	         }
	         g.drawImage(user, car.x, car.y, null);
	         
	         g.setColor(Color.MAGENTA);
	         for(Rectangle rect:carsini){
	             if(rand.nextInt()%3==0){
	                 g.drawImage(op1, rect.x, rect.y, null);
	             }else if(rand.nextInt()%3==1){
	                 g.drawImage(op2, rect.x, rect.y,null);
	             }else {
	            	 g.drawImage(op3, rect.x, rect.y,null);
	             }
	             
	         }
	         g.drawString("Game over", 200, 100);
	         
	     }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	       Rectangle rect;
	       count++;
	       for(int i=0;i<carsini.size();i++) {
	    	   rect=carsini.get(i);
	    	   if(count%1000==0) {
	    		   if(move<10) {
	    			   speed++;
	    			   move+=10;
	    		   }
	    	   }
	    	   rect.y+=speed;
	       }
	       for(Rectangle r:carsini) {
	    	   if(r.intersects(car)) {
	    		   car.y=r.y+height;
	    	   }
	       }
	       for(int i=0;i<carsini.size();i++) {
	    	   rect=carsini.get(i);
	    	   if(rect.y+rect.height>HEIGHT) {
	    		   carsini.remove(rect);
	    		   addcars(false);
	    	   }
	       }
	           for(int i=0;i<line.size();i++){
	               rect=line.get(i);
	               if(count%1000==0){
	                   speed++;
	               }
	               rect.y+=speed;
	           }
	           for(int i=0;i<line.size();i++){
	               rect=line.get(i);
	               if(rect.y>HEIGHT){
	                   line.remove(rect);
	                   addlines(false);
	               }
	           }

	       
	       repaint();
	    }
	    public void moveup() {
	    	if(car.y-move<0) {
	    	}else {
	    		car.y-=move;
	    	}
	    }
	    public void movedown() {
	    	if(car.y+move+car.height>HEIGHT-1) {
	    	}else {
	    		car.y+=move;
	    	}
	    }
	    public void moveleft() {
	    	if(car.x-move<WIDTH/2-90) {
	    	}else {
	    		car.x-=move;
	    	}
	    }
	    public void moveright() {
	    	if(car.x+move>WIDTH/2+10) {
	    	}else {
	    		car.x+=move;
	    	}
	    }
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key=e.getKeyCode();
			switch(key) {
				case KeyEvent.VK_UP:
					moveup();
					break;
				case KeyEvent.VK_DOWN:
					movedown();
					break;
				case KeyEvent.VK_LEFT:
					moveleft();
					break;
				case KeyEvent.VK_RIGHT:
					moveright();
					break;
				default:
					break;
			}
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
	}
		}

