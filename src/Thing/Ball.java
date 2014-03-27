package Thing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Ball extends Thing {

	private int radius;

	public Ball(int radius) {
		setRadius(radius);
		setPreferredSize(new Dimension(radius, radius));
	}

	public Ball(int radius, Color color) {
		this(radius);
		setColor(color);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(0, 0, radius, radius);
		g.setColor(Color.BLACK);
		g.drawOval(0, 0, radius, radius);
		repaint();
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	@Override
	protected boolean enteredRegion(int x, int y) {
		return (distance(new Point(x, y), new Point(radius / 2, radius / 2)) < (radius / 2));
	}

	private int distance(Point p1, Point p2) {
		return (int) Math.sqrt(Math.pow(p2.x - p1.x, 2)
				+ Math.pow(p2.y - p1.y, 2));
	}

}
