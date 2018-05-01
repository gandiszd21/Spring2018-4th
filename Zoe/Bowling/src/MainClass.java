import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



public class MainClass extends JFrame implements Runnable {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	
	private Ball myBall;
	private ArrayList<Pin> myPins;
	private ArrayList<Wall> myWalls;
	
	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}

	BufferedImage offscreen, background;
	Graphics bg;

	public MainClass() {
		myBall = new Ball(370, 730);
		myWalls = new ArrayList<Wall>();
		myWalls.add(new Wall(220));
		myWalls.add(new Wall(540));
		myPins = new ArrayList<Pin>();
		myPins.add(new Pin(384, 135));
		myPins.add(new Pin(350, 100));
		myPins.add(new Pin(418, 100));
		myPins.add(new Pin(316, 65));
		myPins.add(new Pin(452, 65));
		myPins.add(new Pin(384, 65));
		
		
		offscreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offscreen.getGraphics();
		try {
			background = ImageIO.read(MainClass.class
					.getResourceAsStream("Resources/BowlingLane2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
		this.addMouseListener(myBall);
	}

	public void paint(Graphics g) {
		bg.drawImage(background, 0, 0, null);
		for(Pin p : myPins)p.draw(bg);
		for(Wall w : myWalls)w.draw(bg);

		// ball
		myBall.draw(bg);
		
		

	
		
		g.drawImage(offscreen, 0, 0, null);

	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myBall.update();
			for(Wall w : myWalls) myBall.hit(w);
			for(Pin p : myPins)p.checkHit(myBall);
			repaint();
			
		}

	}

}
