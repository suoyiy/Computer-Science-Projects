/*
Suoyi Yang                  PIC 20A JAVA With Applications
ID: 304449872               Spring 2016
Email: suoyiy@ucla.edu      Extra Credit Assignment 
Section: 1B

Honesty Pledge:
I, Suoyi Yang, pledge that this is my own independent
work, which conforms to the guidelines of academic honesty as
described in the course syllabus.
List of known bugs:
*/


import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the Canvas class that contains the drawings 
 * of the Painter GUI
 * @author Suoyi yang
 * @version 1
 *
 */
public class Canvas extends JPanel{
	
	private int index = 0;
    private Point[] points = new Point[10000000];
	private Point beginClick;
	private Point endClick;
	private ShapeInformation shapeinfo;
	private ArrayList<ShapeInformation> drawings = new ArrayList<ShapeInformation>();
	
	/**
	 * This is the Default constructor for Canvas. It sets the 
	 * JPanel to white background and create shapes using MouseListeners.
	 * 
	 */
	Canvas() {
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MouseListener() {
			/**
			 * This method set begin and end click to the point where the user first clicked. 
			 * It also adds that point into the points array.
			 * 
			 * @param e the MouseEvent for where user first clicked
			 */
			@Override 
			public void mousePressed(MouseEvent e) {
				beginClick = new Point (e.getX(), e.getY());
				endClick = beginClick;
				points[index] = new Point(e.getX(), e.getY());
		        index++;
				repaint();
			}
			
			/**
			 * This method creates a shape when mouse is released based on the 
			 * static choice variable in Painters class. It then adds the shapes to 
			 * the drawings ArrayList of ShapeInformation objects.
			 * 
			 * @param e the MouseEvent for where user released the mouse
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				
				//Create shapes with point where user clicked and point where user released click

				Shape shape = drawRectangle(beginClick.x, beginClick.y, e.getX(), e.getY());
				
				if (Painters.choice == 2 || Painters.choice == 4 ||Painters.choice == 5) {
					if (Painters.choice == 2) 
						shape = drawElipse(beginClick.x, beginClick.y, e.getX(), e.getY());
					else if (Painters.choice ==4 )
						shape = drawRectangle(beginClick.x, beginClick.y, e.getX(), e.getY());
					else
						shape = drawLine(beginClick.x, beginClick.y, e.getX(), e.getY());
					
					shapeinfo = new ShapeInformation(shape,Painters.colors,1,1);
					drawings.add(shapeinfo);		
				}
				
				else if (Painters.choice == 1 || Painters.choice == 3) {
					if (Painters.choice == 1)
						shape = drawElipse(beginClick.x, beginClick.y, e.getX(), e.getY());
					else if (Painters.choice ==3 )
						shape = drawRectangle(beginClick.x, beginClick.y, e.getX(), e.getY());
					
					shapeinfo = new ShapeInformation(shape,Painters.colors, 0,1);
					drawings.add(shapeinfo);	
				}
				
				else {
					for (int i = 0; i < index - 1; i++) {
						
						//create extremely small lines using points in points array
			        	shape =(drawLine(points[i].x, points[i].y, points[i + 1].x, 
			                		points[i + 1].y));
			        	if (Painters.choice == 0)
			        		shapeinfo = new ShapeInformation(shape, Painters.colors,1,1);
			        	else
			        		shapeinfo = new ShapeInformation(shape, Color.white, 1,7);
			        	
			        	drawings.add(shapeinfo);
			        }
				}
			
				//reset the clicks, index and array
		        beginClick = null;
		        endClick = null;
		        points= new Point[100000];
			   	index = 0;
		        repaint();
			}
	
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
			
		});
	
		this.addMouseMotionListener(new MouseMotionListener() {
			/**
			 * Method invoked when mouse is clicked and will continue to set endclick 
			 * to points where the user dragged the mouse through 
			 * and add the points into the points array.
			 * 
			 * @param e the MouseEvent
			 */
			@Override
	        public void mouseDragged(MouseEvent e) {
				
				points[index] = new Point(e.getX(), e.getY());
		        index++;
				
				endClick = new Point(e.getX(), e.getY());
				repaint();
	        }

			@Override
			public void mouseMoved(MouseEvent e) {}
	      });
	}
	
	/**
	 * This is the paintCompenent Method that draws all the shapes in 
	 * drawings ArrayList
	 * 
	 * @param g the graphics object
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)(g);
        
         //draw all shapes with their color and stroke size stored in ShapeInformation ArrayList
		for (ShapeInformation x : drawings) {
			g2.setStroke(new BasicStroke(x.getStroke()));
			g2.setColor(x.getColorline());
	        g2.draw(x.getShape());
	        g2.setColor(x.getColorfill());
	        g2.fill(x.getShape());
	        g2.setStroke(new BasicStroke(1));
		}

		//allow users to see the shapes' effect/outline before mouse released and it's painted
		if (Painters.choice ==0 || Painters.choice == 6) {
			for (int i = 0; i < index - 1; i++){
				
				if (Painters.choice ==6) {
					g2.setColor(Color.WHITE);
					g2.setStroke(new BasicStroke(7));
				}
				else {
					g2.setStroke(new BasicStroke(1));
					g2.setColor(Painters.colors);
					
				}
				g2.drawLine(points[i].x, points[i].y,points[i + 1].x, points[i + 1].y);
			}
		}
		
		//allow user to see outline/effect of shape before mouse is released and it's painted
		if (Painters.choice !=0 && Painters.choice !=6)  {
			if (beginClick != null && endClick != null) {
				Shape shape = drawRectangle(beginClick.x, beginClick.y, endClick.x, endClick.y);
				g2.setPaint(Painters.colors);
				if (Painters.choice == 1 || Painters.choice == 2) {
					shape = drawElipse(beginClick.x, beginClick.y, endClick.x, endClick.y);
				}
				else if (Painters.choice == 3 || Painters.choice == 4) {
					shape = drawRectangle(beginClick.x, beginClick.y, endClick.x, endClick.y);
				}
				else if (Painters.choice == 5) {
					shape = drawLine(beginClick.x, beginClick.y, endClick.x, endClick.y);
				}
				g2.draw(shape);
			}
		}
	}
	
	//function that creates an ellipse/circle/oval
	private Ellipse2D.Double drawElipse(int x1, int y1, int x2, int y2) {
	      return new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), 
	    		  Math.abs(y1 - y2));
	}
	
	//function that creates an rectangle/square
	private Rectangle drawRectangle(int x1, int y1, int x2, int y2) {
	      return new Rectangle (Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), 
	    		  Math.abs(y1 - y2));
	}
	
	//function that creates a line
	private Line2D.Double drawLine(int x1, int y1, int x2, int y2) {
	      return new Line2D.Double (x1, y1,x2,y2);
	}
	
	/**
	 * the Method saves the image as either PNG or JPG
	 * @throws IOException if there is trouble saving the image
	 */
	public void SavePicture() throws IOException{
		
		//allow user to choose extension and show SaveDialog allowing user to choose save location
		JFileChooser fileSaveChooser = new JFileChooser("Save a file");
        fileSaveChooser.addChoosableFileFilter(new FileNameExtensionFilter("png","png"));
        fileSaveChooser.addChoosableFileFilter(new FileNameExtensionFilter("jpg","jpg"));
        int returnVal = fileSaveChooser.showSaveDialog(fileSaveChooser);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            String path = fileSaveChooser.getSelectedFile().getPath();
            try{
            	//create a buffered image along with its white background to write onto a file
            	BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), 
            			BufferedImage.TYPE_INT_RGB);
            	Graphics g = image.createGraphics();
            	g.fillRect(0, 0, image.getWidth(), image.getHeight());
            	this.print(g);
            	
            	//write an image into a file with the proper extension 
            	String ext= fileSaveChooser.getFileFilter().getDescription();
            	ImageIO.write(image, ext, new File(path+"."+ext));
            	
            }catch(IOException err){
                err.printStackTrace();
            }   
        }
    }
}
