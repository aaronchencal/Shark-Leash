package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import mainPackage.Natedog.state;

public class MainPanel extends JPanel implements MouseListener, MouseMotionListener {

	private int score = 0;
	
    private int height;

    private int width;
    
    private Natedog natedog;

    private boolean mousePressed = true;
    
    private int lastX= 0;
    
    private int deltaX = 0;
    
    public ArrayList<Shark> sharks;
    
    public int frames = 0;
    
    private Bkgd bkgd;
        
    public MainPanel( int w, int h )
    {
        width = w;
        height = h;
        natedog = new Natedog();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setFocusable( true );
        this.setVisible(true);
        sharks = new ArrayList<Shark>();
        bkgd = new Bkgd();
    }

    /**
     * Game loop: 1) update events 2) draw everything 3) clear screen -> repeat
     */
    public void update( )
    {
    	if(natedog.gameOver) {
    		return;
    	}
    	score++;
    	System.out.println("Score: " + score );
    	bkgd.update( 4 + score / 200 );
    	frames++;
    	if(frames == 25) {
    		frames = 0;
    		if(Math.random() * 100 < 30) {
    		sharks.add(new Shark());
    		}
    	}
    	
    	natedog.update(deltaX, mousePressed);
    	
    	for(Shark s : sharks) s.update(3 + score / 400);
    	for(int i = 0; i < sharks.size(); i++) {
    		if(sharks.get(i).removeMe) {
    			sharks.remove(i);
    			i--;
    		}
    	}
    	for(Shark s: sharks) {
    		if(natedog.myState == state.travelling) {
    		
    			if(s.hitbox.intersects(natedog.hitbox)) {
    				s.removeMe = true;
    				natedog.gameOver();
    			}
    		} else if(natedog.myState == state.landing){ 
    			if(s.hitbox.intersects(natedog.hitbox)) {
    				s.removeMe = true;
    				natedog.useOne = true;
    			}
    		}
    	}
    	
    	repaint();
    }


    @Override
    public void paint( Graphics g )
    {
        g.clearRect( 0, 0, width, height );
        bkgd.draw(g);
        
        g.setColor( Color.black );
        
    	for(Shark s : sharks) s.draw(g);
        natedog.draw(g);
        
        if(natedog.gameOver) {
        	g.drawString("GAME OVER", 180, 400);
        }
        g.drawString("SCORE: " + score, 30, 30);
    }

    @Override
	public void mousePressed(MouseEvent e) {
    	if(natedog.gameOver) {
    		natedog.gameOver = false;
    		score = 0;
    		natedog.gameStart();
    	}
    	lastX = e.getXOnScreen();
    	mousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		deltaX = e.getXOnScreen() - lastX;
		lastX = e.getXOnScreen();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
	
	
	
	
	
	
	
	
	
	/**
	 * we dont care about these
	 */
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
