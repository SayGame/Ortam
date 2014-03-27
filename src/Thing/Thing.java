package Thing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public abstract class Thing extends JComponent implements ActionListener,
		MouseMotionListener, MouseListener {

	protected int x;
	protected int y;
	protected int speed_x;
	protected int speed_y;
	protected Color color;
	protected Color currentColor;
	protected Timer time = new Timer(100, this);
	protected long timing = 0;
	protected MouseMotionListener mouseMotion;
	private boolean initialEnterance = false;
	private boolean graviting = false;
	private MouseEvent event;

	public Thing() {
		setX(0);
		setY(0);
		setSpeed_x(0);
		setSpeed_y(0);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (color != null)
			g.setColor(color);
	}

	public void setMouseListener(MouseMotionListener m) {
		addMouseListener(this);
		addMouseMotionListener(this);
		mouseMotion = m;
	}

	public void gravity() {
		speed_y = (int) (timing);
		move();
	}

	public void move() {
		for (int i = 0; i < Math.max(speed_x, speed_y); i++) {
			if (i < speed_x)
				x++;
			if (i < speed_y)
				y++;
		}
	}

	public void gravityOn() {
		time.start();
	}

	public void gravityOff() {
		time.stop();
		timing = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeed_x(int speed_x) {
		this.speed_x = speed_x;
	}

	public void setSpeed_y(int speed_y) {
		this.speed_y = speed_y;
	}

	public void setColor(Color color) {
		this.color = color;
		this.currentColor = color;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timing++;
		gravity();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (initialEnterance)
			mouseMotion.mouseDragged(event);
		else
			initialEnterance = false;
	}

	protected abstract boolean enteredRegion(int x, int y);

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		initialEnterance = enteredRegion(e.getX(), e.getY());
		if (initialEnterance) {
			event = e;
			graviting = time.isRunning();
			if (time.isRunning()) {
				gravityOff();
			}

			color = Color.LIGHT_GRAY;
			repaint();
			mouseMotion.mouseMoved(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (initialEnterance) {
			if (graviting)
				gravityOn();
			color = currentColor;
			repaint();
		}
	}

}
