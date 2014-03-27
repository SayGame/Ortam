import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import Thing.Ball;
import Thing.Rectangle;
import Thing.Thing;
import Thing.Triangle;

public class Frame extends JFrame {

	Thing thing[] = new Thing[100];

	Paint paint = new Paint();

	JButton gravity = new JButton("Gravity: Off");

	JPanel panel = new JPanel();

	int iterator = -1;

	public Frame() {
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		buildFrame();
	}

	public void buildFrame() {

		add(new Listener(), BorderLayout.NORTH);

		for (int i = 0; i < thing.length && thing[i] != null; i++)
			paint.add(thing[i]);
		for (int i = 0; i < thing.length && thing[i] != null; i++)
			thing[i].setMouseListener(paint);

		add(paint, BorderLayout.CENTER);

		gravity.addActionListener(new Listener());
		gravity.setBackground(Color.RED);
		add(gravity, BorderLayout.SOUTH);
	}

	public static void main(String args[]) {
		Frame frame = new Frame();
		frame.setVisible(true);
		frame.setSize(1300, 1000);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	class Paint extends JPanel implements ActionListener, MouseMotionListener {

		Timer time = new Timer(0, this);

		Paint() {
			setBackground(Color.WHITE);
			time.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < thing.length && thing[i] != null; i++)
				thing[i].setLocation(thing[i].getX(), thing[i].getY());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			for (int i = 0; i < thing.length && thing[i] != null; i++)
				if (e.getSource() == thing[i]) {
					thing[i].setX(getMousePosition().x - e.getX());
					thing[i].setY(getMousePosition().y - e.getY());
				}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			paint.removeAll();
			paint.add(e.getComponent());
			for (int i = 0; i < thing.length && thing[i] != null; i++){
				if(e.getComponent() == thing[i])
					continue;
				else
					paint.add(thing[i]);
			}
			revalidate();
		}
	}

	class Listener extends JPanel implements ActionListener {

		JButton add = new JButton("Add");
		JRadioButton ball = new JRadioButton("Ball");
		JRadioButton tri = new JRadioButton("Triangle");
		JRadioButton rect = new JRadioButton("Rectangle");
		ButtonGroup group1 = new ButtonGroup();
		JLabel label = new JLabel("Size:");
		JTextField size = new JTextField(3);
		JButton choose = new JButton("Color");
		JColorChooser color = new JColorChooser();

		Listener() {
			super(new FlowLayout());
			group1.add(ball);
			group1.add(tri);
			group1.add(rect);
			add(ball);
			add(tri);
			add(rect);
			add(label);
			add(size);
			choose.setBackground(Color.WHITE);
			add(choose);
			add(add);
			choose.addActionListener(this);
			add.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == gravity)
				if (e.getActionCommand() == "Gravity: Off") {
					gravity.setText("Gravity: On");
					for (int i = 0; i < thing.length && thing[i] != null; i++)
						thing[i].gravityOn();
					gravity.setBackground(Color.GREEN);
				}

				else {
					gravity.setText("Gravity: Off");
					for (int i = 0; i < thing.length && thing[i] != null; i++)
						thing[i].gravityOff();
					gravity.setBackground(Color.RED);
				}
			else if (e.getSource() == add) {
				remove(color);
				choose.setBackground(color.getColor());
				try {
					if (ball.isSelected())
						thing[++iterator] = new Ball(Integer.parseInt(size
								.getText()));
					else if (tri.isSelected())
						thing[++iterator] = new Triangle(Integer.parseInt(size
								.getText()));
					else if (rect.isSelected())
						thing[++iterator] = new Rectangle(Integer.parseInt(size
								.getText()));
					thing[iterator].setColor(color.getColor());

					paint.add(thing[iterator]);
					thing[iterator].setMouseListener(paint);
					revalidate();
				} catch (Exception ex) {

				}

			}

			else if (e.getSource() == choose) {
				add(color, 6);
				revalidate();
			}

		}
	}

}
