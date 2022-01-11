package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PanelTest {
	
    public static void main(String[] args) {
/*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PanelTest test = new PanelTest();
                test.createUI();
            }
        };
        SwingUtilities.invokeLater(runnable);*/
    	int x=5;
    	int y=6;
    	System.out.println("X is"+x+""+"Y is "+y);
    	int tmp=x;
    	x=y;
    	y=tmp;
    	System.out.println("X is"+x+""+"Y is "+y);
    }

    public void createUI(){
        JFrame frame = new JFrame("Panel Test");

        JPanel panel = new JPanel();

        panel.setBackground(new Color(0, 0, 0, 0));
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(280,105));
        button.setIcon(new ImageIcon("st2.png"));

        JLabel label = new JLabel("I am a label");
        label.setFont(new Font("Arial", Font.BOLD, 15));

        JTextField textField = new JTextField();

        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
    
        
        panel.setBorder(BorderFactory.createEmptyBorder(800, 1920, 500, 1920));
    	//JLabel pic = new JLabel(new ImageIcon("Start.jpg"));
        BottomPanel buttomPanel = new BottomPanel();
        buttomPanel.add(panel);
       // buttomPanel.add(pic);
        frame.add(buttomPanel,BorderLayout.CENTER);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @SuppressWarnings("serial")
    class BottomPanel extends JPanel{
      
        protected void paintComponent(Graphics g) {

          super.paintComponent(g);
                      
              Image image;
			try {
				image = ImageIO.read(new File("Start.jpg"));
				g.drawImage(image, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
             
			
      }
    }
}