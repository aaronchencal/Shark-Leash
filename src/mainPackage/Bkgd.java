package mainPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bkgd {
	private Image bkgd;
	public int y;
	
	public boolean removeMe = false;
	
	
	public Bkgd() {
		try {
			bkgd = ImageIO.read ( new File( "bkgd.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		y = 0;
	}
	
	public void update(int speed) {
		y += speed;
		if(y >= 800) {
			y = 0;
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(bkgd, 0, y-800, 400, 800, null);
		g.drawImage(bkgd, 0, y, 400, 800, null);
	}
}
