package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

public class ChooseHero extends JFrame {
	private JTextArea head;
	private JTextArea side;
	private JPanel center;
	private JPanel P1;
	private JPanel P2;
	private JButton go;
	private JButton hunter;
	private JButton mage;
	private JButton warlock;
	private JButton paladin;
	private JButton priest;
	private JButton hunter2;
	private JButton mage2;
	private JButton warlock2;
	private JButton paladin2;
	private JButton priest2;
	
	public  ChooseHero(){
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);//philo atef 1920,1080
		//setSize(1920,1080);//philo atef 1920,1080
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setIconImage((new Image("Start.jpg")));
		setVisible(true);
		head= new JTextArea();
		head.setBackground(new Color(96, 40, 0, 200));
		head.setText("-------------CHOOSE YOUR HERO!-------------");
		head.setEditable(false);
		head.setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC , 74));
		head.setPreferredSize(new Dimension(screenSize.width,(screenSize.height*10)/108));//atef .580
		this.add(head,BorderLayout.NORTH);
		side= new JTextArea();
		side.setBackground(new Color(250, 120, 0, 200));
		side.setText("P1"+"\n"+"\n"+"\n"+"P2");
		side.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC , 100));
		side.setPreferredSize(new Dimension(screenSize.width*2/20,screenSize.height*60/108));//atef .580
		side.setEditable(false);
		this.add(side,BorderLayout.WEST);
		P1 =new JPanel();
		P2=new JPanel();
		hunter= new JButton();
		mage= new JButton();
		warlock= new JButton();
		paladin= new JButton();
		priest= new JButton();
		
		
		hunter2= new JButton();
		mage2= new JButton();
		warlock2= new JButton();
		paladin2= new JButton();
		priest2= new JButton();
		
		mage.setIcon(new ImageIcon("Mage.png"));
		warlock.setIcon(new ImageIcon("Warlock.png"));
		paladin.setIcon(new ImageIcon("Paladin.png"));
		priest.setIcon(new ImageIcon("Priest.png"));
		hunter.setIcon(new ImageIcon("Hunter.png"));
		
		// mage.setText("mage");
		// warlock.setText("warlock");
		// paladin.setText("paladin");
		// priest.setText("priest");
		// hunter.setText("hunter");
		
		// mage.setFont(new Font ("TimesRoman", Font.BOLD , 0));
		// warlock.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
		// paladin.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
		// priest.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
		// hunter.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
		
		mage2.setIcon(new ImageIcon("Mage.png"));
		warlock2.setIcon(new ImageIcon("Warlock.png"));
		paladin2.setIcon(new ImageIcon("Paladin.png"));
		priest2.setIcon(new ImageIcon("Priest.png"));
		hunter2.setIcon(new ImageIcon("Hunter.png"));
		
		mage.setBorderPainted(false);mage.setContentAreaFilled(false);
		warlock.setBorderPainted(false);warlock.setContentAreaFilled(false);
		paladin.setBorderPainted(false);paladin.setContentAreaFilled(false);
		priest.setBorderPainted(false);priest.setContentAreaFilled(false);
		hunter.setBorderPainted(false);hunter.setContentAreaFilled(false);
		
		mage2.setBorderPainted(false);mage2.setContentAreaFilled(false);
		warlock2.setBorderPainted(false);warlock2.setContentAreaFilled(false);
		paladin2.setBorderPainted(false);paladin2.setContentAreaFilled(false);
		priest2.setBorderPainted(false);priest2.setContentAreaFilled(false);
		hunter2.setBorderPainted(false);hunter2.setContentAreaFilled(false);
		
		//mage.setBackground(new Color(0,0,0,0));
		// mage2.setText("mage");
		 //warlock2.setText("warlock");
		 //paladin2.setText("paladin");
		// priest2.setText("priest");
		// hunter2.setText("hunter");
		 
		 
		 
		 
		P1.add(hunter);
		P1.add(mage);
		P1.add(paladin);
		P1.add(warlock);
		P1.add(priest);
		
		P2.add(hunter2);
		P2.add(mage2);
		P2.add(paladin2);
		P2.add(warlock2);
		P2.add(priest2);
		center=new JPanel();
		this.add(center,BorderLayout.CENTER);
		center.setBackground(new Color(240, 94, 35, 200));
		center.add(P1,BorderLayout.NORTH);
		center.add(P2,BorderLayout.SOUTH);
		
		go=new JButton();
		go.setIcon(new ImageIcon("st2.png"));
		//go.setMaximumSize(new Dimension(screenSize.width,9));
		this.add(go,BorderLayout.SOUTH);
		revalidate();
		repaint();
	}
	
	public JTextArea getHead() {
		return head;
	}

	public void setHead(JTextArea head) {
		this.head = head;
	}

	public JTextArea getSide() {
		return side;
	}

	public void setSide(JTextArea side) {
		this.side = side;
	}

	public JPanel getCenter() {
		return center;
	}

	public void setCenter(JPanel center) {
		this.center = center;
	}

	public JPanel getP1() {
		return P1;
	}

	public void setP1(JPanel p1) {
		P1 = p1;
	}

	public JPanel getP2() {
		return P2;
	}

	public void setP2(JPanel p2) {
		P2 = p2;
	}

	public JButton getGo() {
		return go;
	}

	public void setGo(JButton go) {
		this.go = go;
	}

	public JButton getHunter() {
		return hunter;
	}

	public void setHunter(JButton hunter) {
		this.hunter = hunter;
	}

	public JButton getMage() {
		return mage;
	}

	public void setMage(JButton mage) {
		this.mage = mage;
	}

	public JButton getWarlock() {
		return warlock;
	}

	public void setWarlock(JButton warlock) {
		this.warlock = warlock;
	}

	public JButton getPaladin() {
		return paladin;
	}

	public void setPaladin(JButton paladin) {
		this.paladin = paladin;
	}

	public JButton getPriest() {
		return priest;
	}

	public void setPriest(JButton priest) {
		this.priest = priest;
	}

	public JButton getHunter2() {
		return hunter2;
	}

	public void setHunter2(JButton hunter2) {
		this.hunter2 = hunter2;
	}

	public JButton getMage2() {
		return mage2;
	}

	public void setMage2(JButton mage2) {
		this.mage2 = mage2;
	}

	public JButton getWarlock2() {
		return warlock2;
	}

	public void setWarlock2(JButton warlock2) {
		this.warlock2 = warlock2;
	}

	public JButton getPaladin2() {
		return paladin2;
	}

	public void setPaladin2(JButton paladin2) {
		this.paladin2 = paladin2;
	}

	public JButton getPriest2() {
		return priest2;
	}

	public void setPriest2(JButton priest2) {
		this.priest2 = priest2;
	}

	public static void main(String [] args)	{
		ChooseHero v= new ChooseHero();
		//System.out.println(v.field.getWidth());
	}
}
