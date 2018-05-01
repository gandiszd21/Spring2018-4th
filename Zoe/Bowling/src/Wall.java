

import java.awt.Color;
import java.awt.Graphics;

public class Wall {
	private int x;
	public static final int WIDTH=30;

	public Wall(int x) {
		this.x = x;
	}
	
	public int getX(){
		return x;
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0xf8b195));
		g.fillRect(x, 0, WIDTH, MainClass.HEIGHT);

	}
}
