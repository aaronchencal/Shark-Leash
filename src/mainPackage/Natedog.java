package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Natedog {

	private Image Nate;
	private Image jumpnate;

	public Rectangle hitbox;

	public int x;

	public int y;

	private int frames = 0;

	public state myState = state.travelling;

	public boolean useOne = false;

	public enum state {
		flying, landing, travelling
	}
	
	public boolean gameOver = false;

	public Natedog() {
		x = MainFrame.width / 2;
		y = 500;
		hitbox = new Rectangle(x, y, 80, 80);
		try {
			Nate = ImageIO.read ( new File( "guyonshark.png"));
			jumpnate = ImageIO.read( new File("hopNate.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(int changex, boolean pressed) {
		frames++;
		x += changex;
		if (!pressed) {
			switch (myState) {
			case flying:
				if (y <= 500) {
					FLY();
				} else {
					// game over;
					gameOver();
				}
				break;
			case landing:
				LAND();
				if (y >= 500) {
					// game over
				}
				break;
			case travelling:
				myState = state.flying;
				frames = 0;
				break;
			}
		} else {
			switch (myState) {
			case flying:
				if (y < 500) {
					myState = state.landing;
				}
				break;
			case landing:
				if (y < 500) {
					LAND();
				} else {
					// check for game over
					if (useOne) {
						myState = state.travelling;
						useOne = false;
					} else {
						gameOver();
					}
				}
				break;
			case travelling:
				y = 500;
				break;
			}
		}
		x = Math.max(x, 0);
		x = Math.min(x, 320);

		hitbox.x = x;
		hitbox.y = y;
	}

	public void FLY() {
		double deltay = -6 * frames + 0.5 * frames * frames;
		if (deltay <= 0) {
			y += deltay;
		} else {
			y += 2;
		}
	}

	public void LAND() {
		if (y < 500) {
			y += 10;
		} else {
			y = 500;
		}
	}

	public void draw(Graphics g) {
		if (myState == state.flying) {
			g.drawImage(jumpnate, x, y, 80, 80, null);
		} else {
			g.drawImage(Nate, x, y, 80, 80, null);
		}
//		g.drawOval(x, y, 40, 40);
		g.setColor(Color.BLACK);
		int width = (int) (((500 - y) / 200.0) * 40);
		g.fillOval(x + 40 - width / 2, 540, width, 5);
	}

	public void gameOver() {
		gameOver = true;
	}
	public void gameStart() {
		myState = state.travelling;
		x = MainFrame.width / 2;
		y = 500;
		hitbox = new Rectangle(x, y, 40, 40);
	}
}
