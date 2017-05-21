package mainPackage;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	private MainPanel mainPanel;
	
	public static int width = 400;
	public static int height = 800;
	
	public MainFrame() { 
	   this.setSize(width, height);
	   setLocationRelativeTo(null);
       mainPanel = new MainPanel(width, height);
       this.add( mainPanel );
       setVisible( true );
       setResizable(false);
	}
	
	 public static void main( String[] args ) throws InterruptedException
	    {
	        MainFrame mainFrame = new MainFrame();
	        while ( true )
	        {
	            mainFrame.mainPanel.update();
	            Thread.sleep( 10 );
	        }
	    }

}
