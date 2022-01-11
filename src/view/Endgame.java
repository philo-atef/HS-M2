package view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class Endgame  extends JFrame {
	JTextArea end;
	public Endgame(){
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);//philo atef 1920,1080
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		end = new JTextArea();
		end.setBackground(Color.RED);
		this.add(end);
		end.setEditable(false);
	}
	public JTextArea getEnd() {
		return end;
	}
	public void setEnd(JTextArea end) {
		this.end = end;
	}
	
}
