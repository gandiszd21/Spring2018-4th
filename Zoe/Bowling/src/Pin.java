

import java.awt.Color;
import java.awt.Graphics;

public class Pin {
	private int x,y;
	private boolean down;
	
	public Pin(int x, int y){
		down = false;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g){
		if(down)return;
		g.setColor(new Color( 0xF67280));
		g.fillRoundRect(x, y, 25, 45, 7, 110);
		
	}
	
	public void checkHit(Ball b){
		double dX = x - b.getX();
		double dY = y - b.getY();
		if(Math.sqrt(dX*dX + dY*dY)<30){
			down = true;
		
		
		}
	}
}
	

	

