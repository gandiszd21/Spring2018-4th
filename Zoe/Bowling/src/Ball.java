import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Ball implements MouseListener{
	public static final double GRAVITY = 0.1;
	private double vX, vY, x, y;
	public static final int SIZE = 60;
	
	
	public Ball(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		
		g.setColor(new Color(0x355c7d));
		g.fillOval((int)x, (int)y, SIZE, SIZE);
	}
	
	public void update(){
		x += vX;
		y += vY;
	}
	
	public void hit(Wall w){
		if(x>w.getX()+w.WIDTH)return;
		if(x+SIZE<w.getX()) return;
		vX *=-1;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private MouseEvent press;
	@Override
	public void mousePressed(MouseEvent e) {
		press = e;
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		double dX = e.getX() - press.getX();
		double dY = e.getY() - press.getY();
		vX = dX*0.1;
		vY = dY*0.1;
		
	}

	public double getX() {
		return x;
	}

	public double
	getY() {
		return y;
	}

	
}
