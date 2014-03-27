package Thing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Triangle extends Thing {

	private int length;

	public Triangle(int length) {
		setLength(length);
		setPreferredSize(new Dimension(length, length));
	}

	public Triangle(int length, Color color) {
		this(length);
		setColor(color);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillPolygon(
				new int[] { length / 2,
						length / 2 + (int) (length * Math.sqrt(3) / 4),
						length / 2 - (int) (length * Math.sqrt(3) / 4) },
				new int[] { 0, length / 2 + length / 4, length / 2 + length / 4 },
				3);
		g.setColor(Color.BLACK);
		g.drawPolygon(
				new int[] { length / 2,
						length / 2 + (int) (length * Math.sqrt(3) / 4),
						length / 2 - (int) (length * Math.sqrt(3) / 4) },
				new int[] { 0, length / 2 + length / 4, length / 2 + length / 4 },
				3);
		repaint();
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	@Override
	protected boolean enteredRegion(int x, int y) {

		boolean s1 = (y > (int) (Math.sqrt(3) * length / 2.0 - Math.sqrt(3) * x));
		boolean s2 = (y > (int) (Math.sqrt(3) * x - Math.sqrt(3) * length / 2.0));
		boolean s3 = (y < (int) (Math.sqrt(3) * length / 2.3));

		return s1 && s2 && s3;
	}

}
