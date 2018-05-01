import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JFrame implements ActionListener {
	public static final int WIDTH = 300;
	public static final int HEIGHT = 450;

	int row;
	int col;

	ArrayList<Button> numberBtns;
	ArrayList<Button> opBtns;
	Label display;
	char op;
	double result;
	boolean clear;
	Font f;
	
	private Button addButton(int row, int col, String label) {
		clear = false;
		result = 0.0;
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridy = row;
		c.gridx = col;
		Button b = new Button(label);
		b.setFont(f);
		b.setBackground(new Color(0xC2C2C2)); //COLOR
		b.addActionListener(this);
		this.add(b, c);
		return b;
		}
	

	


	public MainClass() {
		this.setLayout(new GridBagLayout());

		InputStream is = MainClass.class
				.getResourceAsStream("/resources/calcFont.ttf");
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, is)
					.deriveFont(33f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		numberBtns = new ArrayList<Button>();
		opBtns = new ArrayList<Button>();
		numberBtns.add(addButton(4, 0, "0"));
		for (int i = 1; i < 10; i++) {
			numberBtns.add(addButton(3 - ((i - 1) / 3), (i - 1) % 3, "" + i));
		}
		opBtns.add(addButton(4, 1, "."));
		opBtns.add(addButton(4, 2, "="));
		opBtns.add(addButton(4, 3, "+"));
		opBtns.add(addButton(3, 3, "-"));
		opBtns.add(addButton(2, 3, "x"));
		opBtns.add(addButton(1, 3, "÷"));
		opBtns.add(addButton(0, 0, "C"));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridy = 0;
		c.gridx = 1;
		c.gridwidth = 3;
		display = new Label("0");
		display.setAlignment(Label.CENTER);
		display.setBackground(new Color(0xCBECF5));//COLOR
		display.setFont(f.deriveFont(30f));
		this.add(display, c);
		
	}

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setVisible(true);
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		double temp = 0;
		if(clear){
			result = Double.parseDouble(display.getText());
			display.setText("0");
			clear = false;
		}
		String s = display.getText();
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberBtns.get(i)) {
				if (s.equals("0"))
					s = "" + i;
				else
					s += i;
			display.setText(s);
			}
		}

		for (Button b : opBtns) {
			if (e.getSource() == b) {
				switch (b.getLabel()) {
				case ".":
					if (!s.contains("."))
						s += ".";
					display.setText(s);
					break;
				case "+":
					display.setText(doOperation(display.getText()));
					op = '+';
					clear = true;
					break;
				case "-":
					display.setText(doOperation(display.getText()));
					op = '-';
					clear = true;
					break;
				case "x":
					display.setText(doOperation(display.getText()));
					op = 'x';
					clear = true;
					break;
				case "÷":
					display.setText(doOperation(display.getText()));
					op = '÷';
					clear = true;
					break;
				case "=":
					display.setText(doOperation(display.getText()));
					op = '=';
					clear = true;
					break;
				case "C":
					if (!s.contains("C"))
						s = "0";
					display.setText(s);
					break;
					
				}
			}

		}
	}
	
	private String doOperation(String temp){
		switch(op){
		case '+' : return ""+(result+Double.parseDouble(temp));
		case '-' : return ""+(result-Double.parseDouble(temp));
		case 'x' : return ""+(result*Double.parseDouble(temp));
		case '÷' : return ""+(result/Double.parseDouble(temp));
		}
		return temp;
	}
}
