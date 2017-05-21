package mainPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shark {
	
	private Image img;
	public Rectangle hitbox;
	public int x;
	
	public int y;
	
	public boolean removeMe = false;
	
	
	public Shark() {
		x = (int) (MainFrame.width / 2 + (Math.random() - 0.5) * 320);
		y = -100;
		hitbox = new Rectangle(x, y, 60, 80);
		try {
			img = ImageIO.read ( new File( "shark.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int speed) {
		hitbox.x = x;
		hitbox.y = y;

		y += speed;
		if(y > 900) {
			removeMe = true;
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, 80, 100, null);
	}
}
