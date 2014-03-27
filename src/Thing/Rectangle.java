package Thing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Rectangle extends Thing {

	private int length;

	public Rectangle(int length) {
		setRadius(length);
		setPreferredSize(new Dimension(length, length));
	}

	public Rectangle(int length, Color color) {
		this(length);
		setColor(color);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(0, 0, length, length);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, length - 1, length - 1);
		repaint();
	}

	public void setRadius(int radius) {
		this.length = radius;
	}

	public int getRadius() {
		return length;
	}

	@Override
	protected boolean enteredRegion(int x, int y) {
		return true;
	}

}