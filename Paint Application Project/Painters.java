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

//package Paint;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * This is the Painters class that contains the all
 * the ActionListeners, setup, and formatting of
 * the Painter GUI
 * 
 * @author Suoyi Yang
 * @version 1
 *
 */
public class Painters extends JFrame {
	private JFrame mainFrame;
	private Canvas pictures;

	private JButton unfilCircle = new JButton();
	private JButton fillCircle = new JButton();
	private JButton unfilRectangle = new JButton();
	private JButton fillRectangle = new JButton();
	private JButton line = new JButton();
	private JButton freeDraw = new JButton();
	private JButton eraser = new JButton();
	private JButton colorChoice = new JButton();
	private JTextField width = new JTextField();
	private JTextField height = new JTextField();
	public static Color colors = Color.BLACK;
	public static int choice = 0;
	JPanel optionPanel = new JPanel(); 

	/**
	 * Default constructor for the Painter Class
	 * 
	 * @param Width width of the frame
	 * @param Height height of the frame
	 * @param CloseOption int options (1 or 2) to indicate how frame will close
	 */
	Painters (int Width , int Height, int CloseOption) {
		mainFrame = new JFrame ("Painter");
		pictures = new Canvas();
		mainFrame.setSize(Width, Height);
		mainFrame.getContentPane().setBackground(Color.WHITE);
		Font defaultFont = new Font("Calibri",Font.PLAIN,18);
		mainFrame.setLocationRelativeTo(null);
		
		//Create Jmenu File, New Paint , and Save
		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.setFont(defaultFont);
		menuBar.add(file);
		
		QuitAction quitAction = new QuitAction("Quit",CloseOption);
		JMenuItem quit = new JMenuItem(quitAction);
		quit.setFont(defaultFont);
		file.add(quit);
		
		JMenuItem newPaint = new JMenuItem("New Painter");
		newPaint.setFont(defaultFont);
		file.add(newPaint);
		newPaint.addActionListener(new ActionListener () {
			
			//contains prompt and user input
			Object[] message = { "Input Width:", width, "Input Height:", height};
			
			/**
			 * Ask user to enter the size of desired new Painter GUI
			 * and creates a new Painter GUI. Closing the newly created
			 * GUI will not close down previously opened GUIs.
			 * 
			 * @param e the ActionEvent for the New Painter button
			 */
			@Override
            public void actionPerformed(ActionEvent e) {           
				
				//Display message asking user to input height and width of new Painter GUI
            	int option = JOptionPane.showConfirmDialog(mainFrame, message, 
            			"Setting the Size", JOptionPane.OK_CANCEL_OPTION);
            	if (option == JOptionPane.OK_OPTION)
            	{
            	    int w = Integer.parseInt(width.getText());
            	    int h = Integer.parseInt(height.getText());
            	    new Painters(w,h,2); 
            	}
            }
		});
       
		JMenuItem save = new JMenuItem("Save");
		save.setFont(defaultFont);
		file.add(save);
		
		//GidLayout with two columns
		optionPanel.setLayout(new GridLayout(0,2,1,1));
		
		//Add ImageIcons to all Buttons and reduce the size of image by 1/2 to make icon smaller
	    ImageIcon iconcircle = new ImageIcon(getClass().getResource("/images/circle.png"));
		Image imagecircle = iconcircle.getImage();
		imagecircle = imagecircle.getScaledInstance(imagecircle.getWidth(null)/2, 
				imagecircle.getHeight(null)/2, Image.SCALE_SMOOTH);
	    iconcircle.setImage(imagecircle);
	    
	    ImageIcon iconrectangle = new ImageIcon(getClass().getResource("/images/rectangle.png"));
		Image imagerectangle = iconrectangle.getImage();
		imagerectangle = imagerectangle.getScaledInstance(imagerectangle.getWidth(null)/2, 
				imagerectangle.getHeight(null)/2, Image.SCALE_SMOOTH);
	    iconrectangle.setImage(imagerectangle);
	    
	    
	    ImageIcon iconline = new ImageIcon(getClass().getResource("/images/line.png"));
		Image imageline = iconline.getImage();
		imageline = imageline.getScaledInstance(imageline.getWidth(null)/2, 
				imageline.getHeight(null)/2, Image.SCALE_SMOOTH);
	    iconline.setImage(imageline);
	    
	    ImageIcon iconfreedraw = new ImageIcon(getClass().getResource("/images/pencil.png"));
		Image imagefreedraw = iconfreedraw.getImage();
		imagefreedraw = imagefreedraw.getScaledInstance(imagefreedraw.getWidth(null)/3, 
				imagefreedraw.getHeight(null)/3, Image.SCALE_SMOOTH);
	    iconfreedraw.setImage(imagefreedraw);
	    
	    ImageIcon iconeraser = new ImageIcon(getClass().getResource("/images/eraser.png"));
		Image imageeraser = iconeraser.getImage();
		imageeraser = imageeraser.getScaledInstance(imageeraser.getWidth(null)/2, 
				imageeraser.getHeight(null)/2, Image.SCALE_SMOOTH);
	    iconeraser.setImage(imageeraser);
	    
	    ImageIcon iconunfilrect = new ImageIcon(getClass().getResource("/images/unfilrect.png"));
		Image imageunfilrect = iconunfilrect.getImage();
		imageunfilrect = imageunfilrect.getScaledInstance(imageunfilrect.getWidth(null)/2, 
				imageunfilrect.getHeight(null)/2, Image.SCALE_SMOOTH);
	    iconunfilrect.setImage(imageunfilrect);
	    
	    ImageIcon iconunfilcirc = new ImageIcon(getClass().getResource("/images/unfilcirc.png"));
		Image imageunfilcirc = iconunfilcirc.getImage();
		imageunfilcirc = imageunfilcirc.getScaledInstance(imageunfilcirc.getWidth(null)/2, 
				imageunfilcirc.getHeight(null)/2, Image.SCALE_SMOOTH);
	    iconunfilcirc.setImage(imageunfilcirc);
	    
	    ImageIcon iconcolor = new ImageIcon(getClass().getResource("/images/color.png"));
		Image imagecolor = iconcolor.getImage();
		imagecolor = imagecolor.getScaledInstance(imagecolor.getWidth(null)/2, 
				imagecolor.getHeight(null)/2, Image.SCALE_SMOOTH);
	    iconcolor.setImage(imagecolor);
	    
	    //add all buttons to JPanel
	    unfilRectangle.setIcon(iconunfilrect);
	    optionPanel.add(unfilRectangle);
	      
	    fillRectangle.setIcon(iconrectangle);
	    optionPanel.add(fillRectangle);
	    
	    unfilCircle.setIcon(iconunfilcirc);
	    optionPanel.add(unfilCircle);
	    
	    fillCircle.setIcon(iconcircle);
	    optionPanel.add(fillCircle);
	    
	    line.setIcon(iconline);
	    optionPanel.add(line);
	    
	    freeDraw.setIcon(iconfreedraw);
	    optionPanel.add(freeDraw);
	    
	    eraser.setIcon(iconeraser);
		optionPanel.add(eraser);
	
	    colorChoice.setIcon(iconcolor);
	    optionPanel.add(colorChoice);
	    
	    colorChoice.addActionListener(new ActionListener() {
	    	/**
			 * When color button is clicked, allow user to select color from
			 * JColorChooser dialog
			 * 
			 * @param e the ActionEvent for the color button
			 */
	    	@Override
	    	public void actionPerformed(ActionEvent e ) {
	    		colors = JColorChooser.showDialog(optionPanel, "Choose Background Color",
	    				mainFrame.getBackground());
	    	}
	    });
	    
	    unfilCircle.addActionListener( new UnfilCirclebtn());
		fillCircle.addActionListener(new FillCirclebtn() );
		unfilRectangle.addActionListener(new UnfilRectanglebtn());
		fillRectangle.addActionListener(new FillRectanglebtn() );
		line.addActionListener(new Linebtn());
		freeDraw.addActionListener(new FreeDrawbtn());
		eraser.addActionListener(new Eraserbtn());
		save.addActionListener(new SaveFile());
	    
		mainFrame.add(optionPanel, BorderLayout.WEST);
		mainFrame.add(pictures, BorderLayout.CENTER);
		
		
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(new CloseWindow(CloseOption));
		mainFrame.setVisible(true);
		
		mainFrame.getContentPane().setBackground(Color.WHITE);	
	}
	
	/**
	 * This is the FreeDrawbtn class that is an ActionListener for draw button
	 * @author Suoyi Yang
	 *
	 */
	class FreeDrawbtn implements ActionListener {
		/**
		 * When draw button is clicked, the method set static choice as 0
		 * 
		 * @param e the ActionEvent for the draw button
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			choice = 0;
		}
	}
	
	/**
	 * This is the UnfilCirclebtn class that is an ActionListener for unfilled circle button
	 * @author Suoyi Yang
	 *
	 */
	class UnfilCirclebtn implements ActionListener {
		/**
		 * When unfilled circle button is clicked, the method set static choice as 1
		 * 
		 * @param e the ActionEvent for the unfilled circle button
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			choice = 1;
		}
	}
	
	/**
	 * This is the FillCirclebtn class that is an ActionListener for filled circle button
	 * @author Suoyi Yang
	 *
	 */
	class FillCirclebtn implements ActionListener {
		/**
		 * When filled circle button is clicked, the method set static choice as 2
		 * 
		 * @param e the ActionEvent for the filled circle button
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			choice = 2;
		}
	}
	
	/**
	 * This is the UnfilRectanglebtn class that is an ActionListener 
	 * for unfilled rectangle button
	 * @author Suoyi Yang
	 *
	 */
	class UnfilRectanglebtn implements ActionListener {
		/**
		 * When unfilled rectangle button is clicked, the method set static choice as 3
		 * 
		 * @param e the ActionEvent for the unfilled rectangle button
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			choice = 3;
		}
	}
	
	/**
	 * This is the FillRectanglebtn class that is an ActionListener for filled rectangle button
	 * @author Suoyi Yang
	 *
	 */
	class FillRectanglebtn implements ActionListener {
		/**
		 * When filled rectangle button is clicked, the method set static choice as 4
		 * 
		 * @param e the ActionEvent for the filled rectangle button
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			choice = 4;
		}
	}
	
	/**
	 * This is the Linebtn class that is an ActionListener for line button
	 * @author Suoyi Yang
	 *
	 */
	class Linebtn implements ActionListener {
		/**
		 * When line button is clicked, the method set static choice as 5
		 * 
		 * @param e the ActionEvent for the line button
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			choice = 5;
		}
	}
	
	/**
	 * This is the Eraserbtn class that is an ActionListener for erase button
	 * @author Suoyi Yang
	 *
	 */
	class Eraserbtn implements ActionListener {
		/**
		 * When erase button is clicked, the method set static choice as 6
		 * 
		 * @param e the ActionEvent for the erase button
		 */
		@Override
		public void actionPerformed (ActionEvent e) {
			choice = 6;
		}
	} 
	
	/**
	 * This is the QuitAction class that is an Action for the quit button
	 * @author Suoyi Yang
	 *
	 */
	class QuitAction extends AbstractAction{
		private int option;
		
		/**
		 * default constructor for QuitAction
		 * @param text message for the user
		 * @param options indicate how to close the GUI
		 */
		QuitAction(String text, int options){
			super(text);
			option = options;
		}
		/**
		 * When quit is clicked if option is set to 1, the method close
		 * the entire program. Otherwise, if option is 2, the method just disposes 
		 * the frame without closing the program.
		 * 
		 * @param e the ActionEvent for the quit button
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
					"Quit Confirmation", JOptionPane.YES_NO_OPTION);
			
			 if (confirm == JOptionPane.YES_OPTION && option ==1) {
				System.exit(0);
			 }
			 else if (confirm == JOptionPane.YES_OPTION && option ==2) {
				 mainFrame.dispose();
			 }
		}
	}
	
	/**
	 * This is the SaveFile class that is an ActionListener for the save button
	 * @author Suoyi Yang
	 *
	 */
	private class SaveFile implements ActionListener{
		
		/**
		 * When save is clicked, the method will called the SavedPicture method in 
		 * class Canvas
		 * 
		 * @param e the ActionEvent for the save button
		 */
        @Override
        public void actionPerformed(ActionEvent e) {
        	try {
				pictures.SavePicture();
			} 
        	
        	catch (IOException e1) {
				e1.printStackTrace();
        	}
        }
    }
	
	/**
	 * This is the CloseWindow class that is an WindowAdapter for close button
	 * @author Suoyi Yang
	 *
	 */
	class CloseWindow extends WindowAdapter {
		private int option;
		
		/**
		 * Default constructor for CloseWindow Class
		 * @param options indicate how to close the window
		 */
		CloseWindow(int options) {
			option = options;
		}
		
		/**
		 * When close is clicked if option is set to 1, the method closes
		 * the entire program. Otherwise, if option is 2, the method just disposes the 
		 * frame without closing the program.
		 * 
		 * @param e the WindowEvent for the close button
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
					"Quit Confirmation", JOptionPane.YES_NO_OPTION);
			 if (confirm == JOptionPane.YES_OPTION && option == 2) {
				mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 }
				
			 else if (confirm == JOptionPane.YES_OPTION && option ==1) {
				System.exit(0);
			 }
			 else
				 mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}	
	}
	
	/**
	 * This is the main function for the Painter class that 
	 * creates a new Painter GUI
	 * 
	 * @param args command line arguments
	 */
	public static void main (String [] args){
		new Painters(1000,800,1);
	}
}
