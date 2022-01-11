package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import view.StartHS.BottomPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HSView extends JFrame {
	private JTextArea dataCur;
	private JTextArea dataOpp;
	private JPanel handCur;
	private JPanel handOpp;
	private JPanel fieldCur;
	private JPanel fieldOpp;
	private JPanel field;
	private JPanel data;
	private JPanel functions;
	private JButton OPP;
	private JButton you;
	private void makeFrameFullSize(JFrame aFrame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    aFrame.setSize(screenSize.width, screenSize.height);
	}
	class ImagePanel extends JComponent {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);
	    }
	}

	// elsewhere
	
	public HSView(){
		super();
		
		BufferedImage myImage;
		
		/*try {
			myImage = ImageIO.read((new File("zorar.png")));
			this.setContentPane(new ImagePanel(myImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

		revalidate();
		repaint();
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);//philo atef 1920,1080
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		 
		  
		data= new JPanel();
		data.setPreferredSize(new Dimension(screenSize.width*5/40,screenSize.height*1/2));//atef .580
		this.add(data,BorderLayout.EAST);
		data.isForegroundSet();
        dataOpp = new JTextArea();
		dataOpp.setBackground(new Color(220,20,60,220));
		dataOpp.setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		dataOpp.setPreferredSize(new Dimension(screenSize.width*5/40,screenSize.height*9/40));//atef 290
		data.add(dataOpp,BorderLayout.NORTH);
		
		OPP= new JButton();
		OPP.setText("OPPONENT");
		OPP.setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 10));
		OPP.setPreferredSize(new Dimension(screenSize.width*5/40,screenSize.height*1/10));//240
		this.getData().add(OPP, BorderLayout.CENTER);
		
		this.getOPP().setPreferredSize(new Dimension(screenSize.width*9/160,screenSize.height*1/10));
		 you= new JButton();
		you.setText("CURRENT");
		you.setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 10));
		you.setPreferredSize(new Dimension(screenSize.width*9/160,screenSize.height*1/10));//240

		//you.addActionListener(this);
		this.getData().add(you);
		
		dataCur = new JTextArea();
		dataCur.setBackground(new Color(220,20,60,220));
		dataCur.setPreferredSize(new Dimension(screenSize.width*5/40,screenSize.height*1/5));//atef 290
		dataCur.setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		data.add(dataCur,BorderLayout.SOUTH);
		
		
		
		
	
		
		handOpp= new JPanel();
		handOpp.setPreferredSize(new Dimension(screenSize.width,screenSize.height*1/6));//atef 240
		//handOpp.setLayout(new GridLayout(1,12,100,4));
		handOpp.setLayout(new GridLayout(0,10));
		//new GridLayout()
		//handOpp.setBackground(Color.CYAN);
		handOpp.setVisible(true);
		handOpp.setBackground(new Color(0,0,0,0));
		TopPanel p = new TopPanel();
        p.add(handOpp);
     
        this.add(p,BorderLayout.NORTH);
		//this.add(handOpp,BorderLayout.NORTH);
		
		field= new JPanel();
		field.setPreferredSize(new Dimension(screenSize.width*31/40,screenSize.height*12/18));
			fieldOpp = new JPanel();
		fieldOpp.setPreferredSize(new Dimension(screenSize.width*31/40,screenSize.height*5/18));
		fieldOpp.setLayout(new GridLayout(0,7));
		fieldOpp.setBackground(new Color(0,0,0,0));
		MidTop p1 = new MidTop();
		
        p1.add(fieldOpp);
		field.add(p1,BorderLayout.NORTH);
		MidTop1 p2 = new MidTop1();
		fieldCur =new JPanel();
		fieldCur.setPreferredSize(new Dimension(screenSize.width*31/40,screenSize.height*5/18));//240
		fieldCur.setBackground(new Color(0,0,0,0));
		fieldCur.setLayout(new GridLayout(0,7));
		//fieldCur.setLayout(new GridLayout(1,7,200,4));
		 p2.add(fieldCur);
			field.add(p2,BorderLayout.SOUTH);
		this.add(field,BorderLayout.CENTER);
		//field.add(fieldCur, BorderLayout.SOUTH);
		
		
		handCur= new JPanel();
		handCur.setPreferredSize(new Dimension(screenSize.width,screenSize.height*9/36));//atf 240
		handCur.setLayout(new GridLayout(0,10));
		//handCur.setBackground(Color.PINK);
		handCur.setVisible(true);
		handCur.setBackground(new Color(0,0,0,0));
		//this.add(handCur,BorderLayout.SOUTH);
	
		functions=new JPanel();
		
		this.add(functions,BorderLayout.WEST);
		functions.setBackground(new Color(220,20,60,220));
		functions.setPreferredSize(new Dimension(screenSize.width*2/23,screenSize.height*10/18));//580
		//fun
	
		BottomPanel buttomPanel = new BottomPanel();
        buttomPanel.add(handCur);
     
        this.add(buttomPanel,BorderLayout.SOUTH);

		/**

		for(int i=0;i<14;i++){
		JButton c= new JButton();
		field.add(c);
		
		
		}
		JButton end=new JButton();
		JButton power=new JButton();
		end.setText("END TURN");
		power.setText("USE HERO PAAAWAAAAAAAR");
		handCur.add(end);
		handCur.add(power);
		field.setLayout(cur);
		field.setBackground(Color.GREEN);
		field.setVisible(true);
		this.add(field,BorderLayout.CENTER);
		
		**/
		setVisible(true);
		revalidate();
		repaint();
		
		
		
	}
	
	public JButton getYou() {
		return you;
	}

	public void setYou(JButton you) {
		this.you = you;
	}

	public JButton getOPP() {
		return OPP;
	}
	public void setOPP(JButton oPP) {
		OPP = oPP;
	}
	
	
	public JTextArea getDataCur() {
		return dataCur;
	}

	public void setDataCur(JTextArea dataCur) {
		this.dataCur = dataCur;
	}

	public JTextArea getDataOpp() {
		return dataOpp;
	}

	public void setDataOpp(JTextArea dataOpp) {
		this.dataOpp = dataOpp;
	}

	public JPanel getHandCur() {
		return handCur;
	}

	public void setHandCur(JPanel handCur) {
		this.handCur = handCur;
	}

	public JPanel getHandOpp() {
		return handOpp;
	}

	public void setHandOpp(JPanel handOpp) {
		this.handOpp = handOpp;
	}

	public JPanel getFieldCur() {
		return fieldCur;
	}

	public void setFieldCur(JPanel fieldCur) {
		this.fieldCur = fieldCur;
	}

	public JPanel getFieldOpp() {
		return fieldOpp;
	}

	public void setFieldOpp(JPanel fieldOpp) {
		this.fieldOpp = fieldOpp;
	}

	public JPanel getField() {
		return field;
	}

	public void setField(JPanel field) {
		this.field = field;
	}

	public JPanel getData() {
		return data;
	}

	public void setData(JPanel data) {
		this.data = data;
	}

	public JPanel getFunctions() {
		return functions;
	}

	public void setFunctions(JPanel functions) {
		this.functions = functions;
	}


    @SuppressWarnings("serial")
    class BottomPanel extends JPanel{
      
        protected void paintComponent(Graphics g) {

          super.paintComponent(g);
                      
            
			try {
				Image image = ImageIO.read(new File("field.jpg"));
				g.drawImage(image, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
             
			
      }
    }
    class TopPanel extends JPanel{
        
        protected void paintComponent(Graphics g) {

          super.paintComponent(g);
                      
            
			try {
				Image image = ImageIO.read(new File("field.jpg"));
				g.drawImage(image, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
             
			
      }
    }
  class MidTop extends JPanel{
        
        protected void paintComponent(Graphics g) {

          super.paintComponent(g);
                      
            
			try {
				Image image = ImageIO.read(new File("sand.jpg"));
				g.drawImage(image, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
             
			
      }
    }
  class MidTop1 extends JPanel{
      
      protected void paintComponent(Graphics g) {

        super.paintComponent(g);
                    
          
			try {
				Image image = ImageIO.read(new File("sand.jpg"));
				g.drawImage(image, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
           
			
    }
  }
	public static void main(String [] args)	{
		HSView v= new HSView();
		//System.out.println(v.field.getWidth());
	}
	
}