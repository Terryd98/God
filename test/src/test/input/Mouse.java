package test.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, ActionListener, MouseMotionListener {

	public int mouseB = -1;
	public int mouseX = -1, mouseY = -1;

	public void tick() {
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();
		mouseB = e.getButton();

	}

	public void mouseReleased(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouseB = -1;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

}