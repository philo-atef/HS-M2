package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
public class StartHS extends JFrame{

	private JButton OPP;
	private JFrame frame;


    public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	private void makeFrameFullSize(JFrame aFrame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    aFrame.setSize(screenSize.width, screenSize.height);
	}

	public StartHS(){
    //	super();
    	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 	   // aFrame.setSize(screenSize.width, screenSize.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
         frame = new JFrame();

        JPanel panel = new JPanel();

        panel.setBackground(new Color(0, 0, 0, 0));
        OPP = new JButton();
        OPP.setPreferredSize(new Dimension(280,105));
        OPP.setIcon(new ImageIcon("st2.png"));


      

        panel.add(OPP);
    
        float s=(screenSize.height)*50/(108);
       float h= (screenSize.height)*(80)/110;
        //System.out.print(s+" "+ h);
        panel.setBorder(BorderFactory.createEmptyBorder((int)h, screenSize.width,(int)s, screenSize.width));
        panel.add(OPP);
        BottomPanel buttomPanel = new BottomPanel();
        buttomPanel.add(panel);
     
        frame.add(buttomPanel,BorderLayout.CENTER);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    	revalidate();
		repaint();
    }

    @SuppressWarnings("serial")
    class BottomPanel extends JPanel{
      
        protected void paintComponent(Graphics g) {

          super.paintComponent(g);
                      
            
			try {
				Image image = ImageIO.read(new File("Start.jpg"));
				g.drawImage(image, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
             
			
      }
    }
    public JButton getOPP() {
		return OPP;
	}

	public void setOPP(JButton oPP) {
		OPP = oPP;
	}

}