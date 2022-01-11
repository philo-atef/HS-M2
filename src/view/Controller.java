package view;
// selecting a hero as a target for a spell does not give a message

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.KillCommand;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Pyroblast;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
//import eg.edu.guc.supermarket.controller.Controller;

import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import view.HSView;

public class Controller implements ActionListener,GameListener{
	private ChooseHero ch;
	private Endgame ending;
	private StartHS start;
	private HSView view;
	private Game model;
	private ArrayList<JButton> handCurList;
	private ArrayList<JButton> fieldCurList;
	private ArrayList<JButton> handOppList;
	private ArrayList<JButton> fieldOppList;
	private JButton clicked;
	private JButton p1;
	private JButton p2;
	private boolean reset;
	private JButton clickedLEECHspell;
	private boolean reset1;
	private JButton clickedMINIONspell;
	private boolean reset2;
	private JButton clickedHeroPower;
	private boolean resetHero;
	private JButton end;
	private JButton weapon;
	//private JTextArea error;
	private boolean text;
	private JButton you;
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	//@SuppressWarnings("deprecation")
	public Controller() throws CloneNotSupportedException, FullHandException{
       start=new StartHS ();
       start.getOPP().addActionListener(this);
		start.revalidate();
		start.repaint();
	}
	public void Startgame(Hero P1,Hero P2) throws FullHandException, CloneNotSupportedException{
	
		model=new Game(P1,P2);
		model.setListener(this);
		view=new HSView();
		Hero H=model.getCurrentHero();

		view.getDataCur().setText("Name: "+model.getCurrentHero().getName()+"\n"+
			                  "Current HP:"+model.getCurrentHero().getCurrentHP()+
			                  "\n"+"CurrentManaCrystals: "+H.getCurrentManaCrystals()+
			                  "\n"+"TotalManaCrystals: "+H.getTotalManaCrystals()+"\n"+
			                  "Cards left in deck: "+H.getDeck().size());
		view.getDataCur().setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		
		//JButton OPP= new JButton();
		
		
		Hero O=model.getOpponent();
		
		
	
		view.getDataOpp().setText("Name: "+O.getName()+"\n"+
				                  "Current HP:"+O.getCurrentHP()+
				                 "\n"+"CurrentManaCrystals: "+O.getCurrentManaCrystals()+
				                 "\n"+"TotalManaCrystals: "+O.getTotalManaCrystals()+"\n"+
				                  "Cards left in deck: "+O.getDeck().size());
		view.getDataOpp().setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		view.getOPP().addActionListener(this);
		view.getYou().addActionListener(this);
		view.getDataOpp().setEditable(false);
		view.getDataCur().setEditable(false);
		
	
		
		view.getFieldCur().removeAll();
		view.getFieldOpp().removeAll();
		view.getHandCur().removeAll();
		view.getHandOpp().removeAll();
		handCurList=new ArrayList<JButton>();
		handOppList=new ArrayList<JButton>();
		fieldCurList=new ArrayList<JButton>();
		fieldOppList=new ArrayList<JButton>();
		
		for(int i=0;i<model.getCurrentHero().getHand().size();i++){
			Card c= model.getCurrentHero().getHand().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			handCurList.add(b);
			JTextArea text=new JTextArea();
			if (!(c instanceof Minion)){
			b.setText("Name: "+c.getName()+"\n "+
					   " Mana Cost : "+c.getManaCost()+"\n "
					   +" Rarity: "+c.getRarity());
			
			text.setText("Name: "+c.getName()+"\n "+
					   " Mana Cost : "+c.getManaCost()+"\n "
					   +" Rarity: "+c.getRarity());
			text.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 16));
			}
			else{ 
				Minion M=(Minion)c;
				
				text.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Charge:   "+
						   (!M.isSleeping())+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
				text.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 16));
				
			}
			text.setPreferredSize(new Dimension(screenSize.width*15/198,screenSize.height*13/108));
			text.setEditable(false);
			text.setMaximumSize(new Dimension(screenSize.width*15/198,130));
			text.setBackground(new Color(0, 0, 0, 0));
			text.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 12));
			
			b.setFont(new Font ("Californian FB",  Font.ITALIC, 0));
			
			b.add(text);	
			
			view.getHandCur().add(b);
		}
		for(int i=0;i<model.getCurrentHero().getField().size();i++){
			Card c= model.getCurrentHero().getField().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			fieldCurList.add(b);
			JTextArea text=new JTextArea();
		 
				Minion M=(Minion)c;
				text.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Sleeping:   "+
						   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
				b.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Sleeping:   "+
						   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
				text.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 16));
				b.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 8));
			text.setPreferredSize(new Dimension(screenSize.width*15/198,screenSize.height*13/108));
			text.setEditable(false);
			text.setMaximumSize(new Dimension(screenSize.width*15/198,screenSize.height*13/108));
			text.setBackground(new Color(0, 0, 0, 0));
			text.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 13));
			b.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 13));
			b.add(text);
				
				view.getFieldCur().add(b);
		}
		
		for(int i=0;i<model.getOpponent().getField().size();i++){
			Card c= model.getOpponent().getField().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			fieldOppList.add(b);
			JTextArea text=new JTextArea();

		
			
				Minion M=(Minion)c;
				text.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Charge:   "+
						   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
		
			text.setPreferredSize(new Dimension(50,50));
			text.setEditable(false);
			text.setMaximumSize(new Dimension(150,150));
			text.setBackground(new Color(0, 0, 0, 0));
			view.revalidate();
			view.repaint();
			text.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 10));
			b.add(text);
				view.getFieldOpp().add(b);
		}
		
		O=model.getOpponent();
		view.getDataOpp().setText("Name: "+O.getName()+"\n"+
				                  "Current HP:"+O.getCurrentHP()+
				                  "\n"+"CurrentManaCrystals: "+O.getCurrentManaCrystals()+
				                  "\n"+"TotalManaCrystals: "+O.getTotalManaCrystals()+"\n"+
				                  "Cards left in deck: "+O.getDeck().size());
		view.getDataOpp().setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		for(int i=0;i<model.getOpponent().getHand().size();i++){
			Card c= model.getOpponent().getHand().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			//b.setText("Bos f Wara2ak Ya TA");
			b.setIcon(new ImageIcon("youssefpumkin.png"));
			view.getHandOpp().add(b);
			handOppList.add(b);
			//b.setText("Bos f Wara2ak Ya TA");
			b.setBorderPainted(false);b.setContentAreaFilled(false);
			b.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 20));
			//b.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 20));
		}

		view.revalidate();
		view.repaint();
	
		
		 end= new JButton();
		 weapon= new JButton();
		 
		end.setPreferredSize(new Dimension(screenSize.width*1/8,screenSize.height*3/18));//240
		weapon.setPreferredSize(new Dimension(screenSize.width*1/8,screenSize.height*3/18));//240
		end.setText("End Turn");
		end.setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		weapon.setText("Hero Power");
		weapon.setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		end.addActionListener(this);
		//view.getYou().addActionListener(this);
		weapon.addActionListener(this);
		view.getFunctions().add(end,BorderLayout.NORTH);
		view.getFunctions().add(weapon,BorderLayout.CENTER);
		// error=new JTextArea();
		// error.setPreferredSize(new Dimension(screenSize.width*2/23,screenSize.height*5/18));
		// error.setMaximumSize(new Dimension(screenSize.width*1/8,screenSize.height*5/18));
		//error.setBackground(Color.WHITE);
		//view.getFunctions().add(error,BorderLayout.SOUTH);
		view.revalidate();
		view.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==start.getOPP()){
			 start.dispose();
			 start.getFrame().dispose();
			ch=new ChooseHero();
		ch.getGo().addActionListener(this);
		ch.getMage().addActionListener(this);
		ch. getWarlock().addActionListener(this);
		ch.getPaladin().addActionListener(this);
		ch.getPriest().addActionListener(this);
		ch.getHunter().addActionListener(this);
		ch.getMage2().addActionListener(this);
		ch. getWarlock2().addActionListener(this);
		ch.getPaladin2().addActionListener(this);
		ch.getPriest2().addActionListener(this);
		ch.getHunter2().addActionListener(this);
		p1=null;
		p2=null;
		return;
		}
		if(b==ch.getMage()||b==ch.getWarlock()||b==ch.getPaladin()||b==ch.getPriest()||b==ch.getHunter()){
			p1=b;
			return;}
		
		if(b==ch.getMage2()||b==ch.getWarlock2()||b==ch.getPaladin2()||b==ch.getPriest2()||b==ch.getHunter2()){
			p2=b;
			return;}
		if(b==ch.getGo()){
			if(p1!=null && p2!=null){
				try {
					Startgame(this.find(p1),this.find(p2));
				} catch (FullHandException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 ch.dispose();
			return;}
			else return;	}
	
		
	

		if(b.getText().equals("CURRENT")){

			if (clicked!=null){
				reset=true;
				int i=0;
				for( i=0;i<fieldCurList.size();i++)
					if(fieldCurList.get(i).getText().equals(clicked.getText()))
						break;
				//int i=fieldCurList.indexOf(clicked);
				Minion attacker=model.getCurrentHero().getField().get(i);
				try {
					model.getCurrentHero().attackWithMinion(attacker, model.getCurrentHero());
				} catch (CannotAttackException | NotYourTurnException
						| TauntBypassException | NotSummonedException
						| InvalidTargetException e1) {
					// TODO Auto-generated catch block
					 text=false;
		             //error.setText(e1.getMessage());
					 JOptionPane.showMessageDialog(new JFrame(),
                			 e1.getMessage(),
                			    "Error",
                			    JOptionPane.PLAIN_MESSAGE);
		             //error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
		             view.repaint();
		             view.revalidate();
				}
			}
			if (clickedMINIONspell!=null ){
				reset2=true;
				int i=0;
				for( i=0;i<handCurList.size();i++)
					if(handCurList.get(i).getText().equals(clickedMINIONspell.getText()))
						break;
				System.out.print("pyro");
				Card attacker=model.getCurrentHero().getHand().get(i);
				if(attacker instanceof Pyroblast || attacker instanceof KillCommand ){
				try {
					model.getCurrentHero().castSpell((HeroTargetSpell)attacker, model.getCurrentHero());
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					 text=false;
		             //error.setText(e1.getMessage());
					 JOptionPane.showMessageDialog(new JFrame(),
                			 e1.getMessage(),
                			    "Error",
                			    JOptionPane.PLAIN_MESSAGE);
		             //error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
		             view.repaint();
		             view.revalidate();
				}}
			}
			if(clickedHeroPower!=null){
				System.out.println("clicked hero power on opponent");
				resetHero=true;
				Hero h= model.getCurrentHero();
				Priest p=null;
				Mage m=null;
				if(h instanceof Priest){
					p=(Priest) model.getCurrentHero();
					try {
						 ((Priest)model.getCurrentHero()).useHeroPower(model.getCurrentHero());
					} catch (NotEnoughManaException
							| HeroPowerAlreadyUsedException
							| NotYourTurnException | FullHandException
							| FullFieldException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						 text=false;
			             //error.setText(e1.getMessage());
			             //error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
						 JOptionPane.showMessageDialog(new JFrame(),
                    			 e1.getMessage(),
                    			    "Error",
                    			    JOptionPane.PLAIN_MESSAGE);
			             view.repaint();
			             view.revalidate();
					}
				}
				
				if(h instanceof Mage){
				 m=(Mage)model.getCurrentHero();
				                 try {
					m.useHeroPower(model.getCurrentHero());
				                     } catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						               | NotYourTurnException | FullHandException
						               | FullFieldException | CloneNotSupportedException e1) {
					                    // TODO Auto-generated catch block
				                    	 text=false;
							            // error.setText(e1.getMessage());
							            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
				                    	// JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
				                    	 JOptionPane.showMessageDialog(new JFrame(),
				                    			 e1.getMessage(),
				                    			    "Error",
				                    			    JOptionPane.PLAIN_MESSAGE);
							             view.repaint();
							             view.revalidate();
				}
				
				
			}
			}
		
			
		
		}
		
			
		
	
	
		if(b.getText().equals("End Turn"))
			try {
				model.endTurn();
			} catch (FullHandException e1) {
				// TODO Auto-generated catch block
				text=false;
				Card c=((FullHandException) e1).getBurned(); 
				if (c instanceof Minion){
					Minion M=(Minion)c;
				//error.setText("BURNED:"+"\n"+"Name: "+M.getName()+"\n "+
						  // " Mana Cost : "+M.getManaCost()+"\n "
						  // +" Rarity: "+M.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   //M.getCurrentHP()+ "\n"+ "   Is Charge:   "+
						   //M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
					JOptionPane.showMessageDialog(new JFrame(),
							"BURNED:"+"\n"+"Name: "+M.getName()+"\n "+
									  " Mana Cost : "+M.getManaCost()+"\n "
									   +" Rarity: "+M.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
									   M.getCurrentHP()+ "\n"+ "   Is Charge:   "+
									   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt(),"Error",
						    JOptionPane.PLAIN_MESSAGE);
					}
				else
					
				JOptionPane.showMessageDialog(new JFrame(),
						"Name: "+c.getName()+"\n "+
								   " Mana Cost : "+c.getManaCost()+"\n "
								   +" Rarity: "+c.getRarity(),"Error",
					    JOptionPane.PLAIN_MESSAGE);
	             view.repaint();
	             view.revalidate();
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if(b.getText().equals("Hero Power")){
			
			if((model.getCurrentHero() instanceof Hunter||model.getCurrentHero() instanceof Paladin||
					model.getCurrentHero() instanceof Warlock)){
				System.out.println("clicked hero power ");
				try {
					model.getCurrentHero().useHeroPower();
				} 
				catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullFieldException | CloneNotSupportedException e1)
				{
					// TODO Auto-generated catch block
					text=false;
					JOptionPane.showMessageDialog(new JFrame(),
               			 e1.getMessage(),
               			    "Error",
               			    JOptionPane.PLAIN_MESSAGE);
		             view.repaint();
		             view.revalidate();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					Card c=((FullHandException) e1).getBurned(); 
					if (c instanceof Minion){
						Minion M=(Minion)c;
						JOptionPane.showMessageDialog(new JFrame(),
								"Name: "+M.getName()+"\n "+
										   " Mana Cost : "+M.getManaCost()+"\n "
										   +" Rarity: "+M.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
										   M.getCurrentHP()+ "\n"+ "   Is Charge:   "+
										   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt(),
                   			    "Error",
                   			    JOptionPane.PLAIN_MESSAGE);
					//error.setText();
					}
					else
						//error.setText();
					 JOptionPane.showMessageDialog(new JFrame(),
							 "Name: "+c.getName()+"\n "+
									   " Mana Cost : "+c.getManaCost()+"\n "
									   +" Rarity: "+c.getRarity(),
                			    "Error",
                			    JOptionPane.PLAIN_MESSAGE);
		             //error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
		             view.repaint();
		             view.revalidate();
				}
				
				//return;
			}
			else if(model.getCurrentHero() instanceof Priest||model.getCurrentHero() instanceof Mage){
				System.out.println("clicked hero power of priest or mage ");
				resetHero=false;
				clickedHeroPower=b;
			
				
			}
		}
		else{
			
			if (handCurList.contains(b)){
				
				int i=handCurList.indexOf(b);
				Card c=model.getCurrentHero().getHand().get(i);
				if (c instanceof Minion)
				   {
					           try {
						model.getCurrentHero().playMinion(((Minion)c));
					               }
					           catch (NotYourTurnException | NotEnoughManaException
							         | FullFieldException e1) {
						              // TODO Auto-generated catch block
					        	   text=false;
					        	   //for(int i=0;)
						            // error.setText(e1.getMessage());
						            //// error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 10));
						             JOptionPane.showMessageDialog(new JFrame(),
					               			 e1.getMessage(),
					               			    "Error",
					               			    JOptionPane.PLAIN_MESSAGE);
						             view.repaint();
						             view.revalidate();
					               }
					}
					else 
					{
						if(c instanceof AOESpell)	{
					                                try {
						model.getCurrentHero().castSpell(((AOESpell)c), model.getOpponent().getField());
					                                } catch (NotYourTurnException | NotEnoughManaException e1) {
					                              	// TODO Auto-generated catch block
					                                	 text=false;
											             //error.setText(e1.getMessage());
											            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
					                                	 JOptionPane.showMessageDialog(new JFrame(),
					                                   			 e1.getMessage(),
					                                   			    "Error",
					                                   			    JOptionPane.PLAIN_MESSAGE);
											             view.repaint();
											             view.revalidate();
					}
					}
				if(c instanceof FieldSpell)
							try {
								model.getCurrentHero().castSpell((FieldSpell)c);
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								// TODO Auto-generated catch block
								 text=false;
					            // error.setText(e1.getMessage());
					            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
								 JOptionPane.showMessageDialog(new JFrame(),
				               			 e1.getMessage(),
				               			    "Error",
				               			    JOptionPane.PLAIN_MESSAGE);
					             view.repaint();
					             view.revalidate();
							}
				if(c instanceof HeroTargetSpell &&(!(c instanceof Pyroblast || c instanceof KillCommand )))
							try {
								model.getCurrentHero().castSpell((HeroTargetSpell)c, model.getOpponent());
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								// TODO Auto-generated catch block
								 text=false;
					            //// error.setText(e1.getMessage());
					            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
								 JOptionPane.showMessageDialog(new JFrame(),
				               			 e1.getMessage(),
				               			    "Error",
				               			    JOptionPane.PLAIN_MESSAGE);
					             view.repaint();
					             view.revalidate();
							}
						if(c instanceof LeechingSpell  ){
							clickedLEECHspell=b;
							reset1=false;
							
							
						}
						if(c instanceof MinionTargetSpell  ){
							clickedMINIONspell=b;
							reset2=false;
							System.out.print("spell");
							
						}
				
			}
			
					
			
			}
			else{
			if(fieldCurList.contains(b)&&(clickedLEECHspell==null && clickedMINIONspell==null )&& clickedHeroPower==null ){
				clicked=b;
				reset=false;
				return;
				}
		
			else if(fieldCurList.contains(b)&&clickedHeroPower!=null){
				resetHero=true;
				System.out.println("clicked hero power on minion");
				int g=fieldCurList.indexOf(b);
				Hero h= model.getCurrentHero();
				Priest p=null;
				Mage m=null;
				if(h instanceof Priest){
					p=(Priest) model.getCurrentHero();
					try {
						p.useHeroPower(model.getCurrentHero().getField().get(g));
					} catch (NotEnoughManaException
							| HeroPowerAlreadyUsedException
							| NotYourTurnException | FullHandException
							| FullFieldException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						 text=false;
			             //error.setText(e1.getMessage());
			            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
						 JOptionPane.showMessageDialog(new JFrame(),
		               			 e1.getMessage(),
		               			    "Error",
		               			    JOptionPane.PLAIN_MESSAGE);
			             view.repaint();
			             view.revalidate();
					}
				}
				
				if(h instanceof Mage){
				 m=(Mage)model.getCurrentHero();
				                 try {
					m.useHeroPower(model.getCurrentHero().getField().get(g));
				                     } catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						               | NotYourTurnException | FullHandException
						               | FullFieldException | CloneNotSupportedException e1) {
					                    // TODO Auto-generated catch block
				                    	 text=false;
							            // error.setText(e1.getMessage());
							            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
				                    	 JOptionPane.showMessageDialog(new JFrame(),
				                       			 e1.getMessage(),
				                       			    "Error",
				                       			    JOptionPane.PLAIN_MESSAGE);
							             view.repaint();
							             view.revalidate();
				}
				}
				
				
			}
			else{
				if(fieldOppList.contains(b)){
					if (clicked!=null){
						reset=true;
						int i=0;
						for( i=0;i<fieldCurList.size();i++)
							if(fieldCurList.get(i).getText().equals(clicked.getText()))
								break;
						//int i=fieldCurList.indexOf(clicked);
						Minion attacker=model.getCurrentHero().getField().get(i);
						int g=fieldOppList.indexOf(b);
						Minion target=model.getOpponent().getField().get(g);
						try {
							model.getCurrentHero().attackWithMinion(attacker, target);
						} catch (CannotAttackException | NotYourTurnException
								| TauntBypassException | InvalidTargetException
								| NotSummonedException e1) {
							// TODO Auto-generated catch block
							 text=false;
				            // error.setText(e1.getMessage());
				            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
							 JOptionPane.showMessageDialog(new JFrame(),
			               			 e1.getMessage(),
			               			    "Error",
			               			    JOptionPane.PLAIN_MESSAGE);
				             view.repaint();
				             view.revalidate();
						}
					}
					else if (clickedLEECHspell!=null){
						
							reset1=true;
							int i=0;
							for( i=0;i<handCurList.size();i++)
								if(handCurList.get(i).getText().equals(clickedLEECHspell.getText()))
									break;
							//int i=fieldCurList.indexOf(clicked);
							LeechingSpell attacker=(LeechingSpell) model.getCurrentHero().getHand().get(i);
							int g=fieldOppList.indexOf(b);
							Minion target=model.getOpponent().getField().get(g);
							if (g==-1)
								target = model.getCurrentHero().getField().get(g);
							try {
								model.getCurrentHero().castSpell(attacker, target);
							} catch (NotYourTurnException
									| NotEnoughManaException e1) {
								// TODO Auto-generated catch block
								 text=false;
					            // error.setText(e1.getMessage());
					            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
								 JOptionPane.showMessageDialog(new JFrame(),
				               			 e1.getMessage(),
				               			    "Error",
				               			    JOptionPane.PLAIN_MESSAGE);
					             view.repaint();
					             view.revalidate();
							}
					}
					else if (clickedMINIONspell!=null){
						
						reset2=true;
						
						int i=0;
						
						for( i=0;i<handCurList.size();i++)
							if(handCurList.get(i).getText().equals(clickedMINIONspell.getText()))
								break;
						//int i=fieldCurList.indexOf(clicked);
						MinionTargetSpell attacker=(MinionTargetSpell) model.getCurrentHero().getHand().get(i);
						int g=fieldOppList.indexOf(b);
						Minion target=model.getOpponent().getField().get(g);
						
						if (g==-1)
							target = model.getCurrentHero().getField().get(g);
					
							try {
								model.getCurrentHero().castSpell(attacker, target);
							} catch (NotYourTurnException
									| NotEnoughManaException
									| InvalidTargetException e1) {
								// TODO Auto-generated catch block
								 text=false;
					            // error.setText(e1.getMessage());
					            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
								 JOptionPane.showMessageDialog(new JFrame(),
				               			 e1.getMessage(),
				               			    "Error",
				               			    JOptionPane.PLAIN_MESSAGE);
					             view.repaint();
					             view.revalidate();
							}
						
				}
					else if(clickedHeroPower!=null){
						resetHero=true;
						System.out.println("clicked hero power on minion");
						int g=fieldOppList.indexOf(b);
						Hero h= model.getCurrentHero();
						Priest p=null;
						Mage m=null;
						if(h instanceof Priest){
							p=(Priest) model.getCurrentHero();
							try {
								p.useHeroPower(model.getOpponent().getField().get(g));
							} catch (NotEnoughManaException
									| HeroPowerAlreadyUsedException
									| NotYourTurnException | FullHandException
									| FullFieldException | CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								 text=false;
					            // error.setText(e1.getMessage());
					           //  error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
								 JOptionPane.showMessageDialog(new JFrame(),
				               			 e1.getMessage(),
				               			    "Error",
				               			    JOptionPane.PLAIN_MESSAGE);
					             view.repaint();
					             view.revalidate();
							}
						}
						
						if(h instanceof Mage){
						 m=(Mage)model.getCurrentHero();
						                 try {
							m.useHeroPower(model.getOpponent().getField().get(g));
						                     } catch (NotEnoughManaException | HeroPowerAlreadyUsedException
								               | NotYourTurnException | FullHandException
								               | FullFieldException | CloneNotSupportedException e1) {
							                    // TODO Auto-generated catch block
						                    	 text=false;
									            // error.setText(e1.getMessage());
									            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
						                    	 JOptionPane.showMessageDialog(new JFrame(),
						                       			 e1.getMessage(),
						                       			    "Error",
						                       			    JOptionPane.PLAIN_MESSAGE);
									             view.repaint();
									             view.revalidate();
						}
						}
					}
					
				}
				else
				if(fieldCurList.contains(b)){
					
					
					if (clickedLEECHspell!=null){
						
						reset1=true;
						int i=0;
						for( i=0;i<handCurList.size();i++)
							if(handCurList.get(i).getText().equals(clickedLEECHspell.getText()))
								break;
						//int i=fieldCurList.indexOf(clicked);
						LeechingSpell attacker=(LeechingSpell) model.getCurrentHero().getHand().get(i);
						int g=fieldCurList.indexOf(b);
						Minion target=model.getCurrentHero().getField().get(g);
						if (g==-1)
							target = model.getCurrentHero().getField().get(g);
						try {
							model.getCurrentHero().castSpell(attacker, target);
						} catch (NotYourTurnException
								| NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							 text=false;
				             //error.setText(e1.getMessage());
				            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
							 JOptionPane.showMessageDialog(new JFrame(),
			               			 e1.getMessage(),
			               			    "Error",
			               			    JOptionPane.PLAIN_MESSAGE);
				             view.repaint();
				             view.revalidate();
						}
				}
				else if (clickedMINIONspell!=null){
					
					reset2=true;
					
					int i=0;
					
					for( i=0;i<handCurList.size();i++)
						if(handCurList.get(i).getText().equals(clickedMINIONspell.getText())){
							
							
							break;}
					//int i=fieldCurList.indexOf(clicked);
					MinionTargetSpell attacker=(MinionTargetSpell) model.getCurrentHero().getHand().get(i);
					int g=fieldCurList.indexOf(b);
					Minion target=model.getCurrentHero().getField().get(g);
					
						try {
							
							model.getCurrentHero().castSpell(attacker, target);
						} catch (NotYourTurnException
								| NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 text=false;
				            // error.setText(e1.getMessage());
				            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
							 JOptionPane.showMessageDialog(new JFrame(),
			               			 e1.getMessage(),
			               			    "Error",
			               			    JOptionPane.PLAIN_MESSAGE);
				             view.repaint();
				             view.revalidate();
						}
					
			}
				
				}
				else if(b.getText().equals("OPPONENT"))
				{
					if (clicked!=null){
						reset=true;
						int i=0;
						for( i=0;i<fieldCurList.size();i++)
							if(fieldCurList.get(i).getText().equals(clicked.getText()))
								break;
						//int i=fieldCurList.indexOf(clicked);
						Minion attacker=model.getCurrentHero().getField().get(i);
						try {
							model.getCurrentHero().attackWithMinion(attacker, model.getOpponent());
						} catch (CannotAttackException | NotYourTurnException
								| TauntBypassException | NotSummonedException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							 text=false;
				            // error.setText(e1.getMessage());
				            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
							 JOptionPane.showMessageDialog(new JFrame(),
			               			 e1.getMessage(),
			               			    "Error",
			               			    JOptionPane.PLAIN_MESSAGE);
				             view.repaint();
				             view.revalidate();
						}
					}
					if (clickedMINIONspell!=null ){
						reset2=true;
						int i=0;
						for( i=0;i<handCurList.size();i++)
							if(handCurList.get(i).getText().equals(clickedMINIONspell.getText()))
								break;
						System.out.print("pyro");
						Card attacker=model.getCurrentHero().getHand().get(i);
						if(attacker instanceof Pyroblast || attacker instanceof KillCommand ){
						try {
							model.getCurrentHero().castSpell((HeroTargetSpell)attacker, model.getOpponent());
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							 text=false;
				            // error.setText(e1.getMessage());
				            // error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
							 JOptionPane.showMessageDialog(new JFrame(),
			               			 e1.getMessage(),
			               			    "Error",
			               			    JOptionPane.PLAIN_MESSAGE);
				             view.repaint();
				             view.revalidate();
						}}
					}
					if(clickedHeroPower!=null){
						System.out.println("clicked hero power on opponent");
						resetHero=true;
						Hero h= model.getCurrentHero();
						Priest p=null;
						Mage m=null;
						if(h instanceof Priest){
							p=(Priest) model.getCurrentHero();
							try {
								p.useHeroPower(model.getOpponent());
							} catch (NotEnoughManaException
									| HeroPowerAlreadyUsedException
									| NotYourTurnException | FullHandException
									| FullFieldException | CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								 text=false;
					            // error.setText(e1.getMessage());
					           //  error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
								 JOptionPane.showMessageDialog(new JFrame(),
				               			 e1.getMessage(),
				               			    "Error",
				               			    JOptionPane.PLAIN_MESSAGE);
					             view.repaint();
					             view.revalidate();
							}
						}
						
						if(h instanceof Mage){
						 m=(Mage)model.getCurrentHero();
						                 try {
							m.useHeroPower(model.getOpponent());
						                     } catch (NotEnoughManaException | HeroPowerAlreadyUsedException
								               | NotYourTurnException | FullHandException
								               | FullFieldException | CloneNotSupportedException e1) {
							                    // TODO Auto-generated catch block
						                    	 text=false;
									            // error.setText(e1.getMessage());
									           //  error.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 15));
						                    	 JOptionPane.showMessageDialog(new JFrame(),
						                       			 e1.getMessage(),
						                       			    "Error",
						                       			    JOptionPane.PLAIN_MESSAGE);
									             view.repaint();
									             view.revalidate();
						}
						
						
					}
					}
				
					
				}
				
				}
		}
		}
			refresh();
		
			view.revalidate();
			view.repaint();
			
		
	}
	@SuppressWarnings("deprecation")
	public void refresh(){
		
		//view.refresh();
		Hero H=model.getCurrentHero();
		view.getDataCur().setText("Name: "+model.getCurrentHero().getName()+"\n"+
				                  "Current HP:"+model.getCurrentHero().getCurrentHP()+
				                  "\n"+"CurrentManaCrystals: "+H.getCurrentManaCrystals()+
				                  "\n"+"TotalManaCrystals: "+H.getTotalManaCrystals()+"\n"+
				                  "Cards left in deck: "+H.getDeck().size());
		view.getDataCur().setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		//JTextArea text=new JTextArea();
		
		view.getFieldCur().removeAll();
		view.getFieldOpp().removeAll();
		view.getHandCur().removeAll();
		view.getHandOpp().removeAll();
		handCurList=new ArrayList<JButton>();
		handOppList=new ArrayList<JButton>();
		fieldCurList=new ArrayList<JButton>();
		fieldOppList=new ArrayList<JButton>();
		
		for(int i=0;i<model.getCurrentHero().getHand().size();i++){
			Card c= model.getCurrentHero().getHand().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			handCurList.add(b);
			JTextArea text=new JTextArea();
			if (!(c instanceof Minion)){
			b.setText("Name: "+c.getName()+"\n "+
					   " Mana Cost : "+c.getManaCost()+"\n "
					   +" Rarity: "+c.getRarity());
			b.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 10));
			
			text.setText("Name: "+c.getName()+"\n "+
					   " Mana Cost : "+c.getManaCost()+"\n "
					   +" Rarity: "+c.getRarity());
			text.setBackground(new Color(0, 0, 0, 0));
			}
			else{ 
				Minion M=(Minion)c;
				
				text.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Charge:   "+
						   (!M.isSleeping())+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
				
				
			}
			text.setPreferredSize(new Dimension(screenSize.width*15/198,130));
			text.setEditable(false);
			text.setMaximumSize(new Dimension(screenSize.width*15/198,130));
			text.setBackground(new Color(0, 0, 0, 0));
			text.setFont(new Font("Californian FB", Font.BOLD | Font.ITALIC, 12));
			b.setFont(new Font ("TimesRoman",  Font.ITALIC, 0));
			//b.setBackground(new Color(0, 0, 0, 0));
			view.revalidate();
			view.repaint();
			//bte3mel hagat 3'areeba
			
			b.add(text);	
			
			view.getHandCur().add(b);
		}
		for(int i=0;i<model.getCurrentHero().getField().size();i++){
			Card c= model.getCurrentHero().getField().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			fieldCurList.add(b);
			JTextArea text=new JTextArea();
		 
				Minion M=(Minion)c;
				text.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Sleeping:   "+
						   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
				b.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Sleeping:   "+
						   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
			//	b.setBounds(new Rectangle(50, 150, 0, 0));
				
				text.setPreferredSize(new Dimension(screenSize.width*15/198,screenSize.height*13/108));
				text.setEditable(false);
				text.setMaximumSize(new Dimension(screenSize.width*15/198,screenSize.height*22/108));
				text.setBackground(new Color(0, 0, 0, 0));
				text.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 10));
			b.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 0));
			b.add(text);
				
				view.getFieldCur().add(b);
		}
		
		for(int i=0;i<model.getOpponent().getField().size();i++){
			Card c= model.getOpponent().getField().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			fieldOppList.add(b);
			JTextArea text=new JTextArea();

		
			
				Minion M=(Minion)c;
				text.setText("Name: "+c.getName()+"\n "+
						   " Mana Cost : "+c.getManaCost()+"\n "
						   +" Rarity: "+c.getRarity()+ "\n"+"Attack: " +M.getAttack()+ "\n"+ "Current HP: "+
						   M.getCurrentHP()+ "\n"+ "   Is Sleeping:   "+
						   M.isSleeping()+ "\n"+"Is Divine: "+M.isDivine()+"\n"+"Is Taunt: "+  M.isTaunt());
		
				text.setPreferredSize(new Dimension(screenSize.width*15/198,screenSize.height*13/108));
				text.setEditable(false);
				text.setMaximumSize(new Dimension(screenSize.width*15/198,screenSize.height*22/108));
				text.setBackground(new Color(0, 0, 0, 0));
				text.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 13));
			b.add(text);
				view.getFieldOpp().add(b);
		}
		
		Hero O=model.getOpponent();
		view.getDataOpp().setText("Name: "+O.getName()+"\n"+
				                  "Current HP:"+O.getCurrentHP()+
				                  "\n"+"CurrentManaCrystals: "+O.getCurrentManaCrystals()+
				                  "\n"+"TotalManaCrystals: "+O.getTotalManaCrystals()+"\n"+
				                  "Cards left in deck: "+O.getDeck().size());
		view.getDataOpp().setFont(new Font ("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 16));
		for(int i=0;i<model.getOpponent().getHand().size();i++){
			Card c= model.getOpponent().getHand().get(i);
			JButton b= new JButton();
			b.addActionListener(this);
			//b.setText("Bos f Wara2ak Ya TA");
			view.getHandOpp().add(b);
			handOppList.add(b);
			//b.setText("Bos f Wara2ak Ya TA");
			b.setIcon(new ImageIcon("youssefpumkin.png"));
			b.setBorderPainted(false);b.setContentAreaFilled(false);
			
			b.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 20));
			b.setFont(new Font ("Californian FB", Font.BOLD | Font.ITALIC, 20));
		}
	
		//if (text)
			//error.setText("");;
		if (reset)
			clicked=null;
		if (reset1)
			clickedLEECHspell=null;
		if (reset2)
			clickedMINIONspell=null;
		if (resetHero)
			clickedHeroPower=null;
		reset=true;
		text=true;
		reset1=true;
		reset2=true;
		resetHero=true;
		view.revalidate();
		view.repaint();
	
	}
	public  Hero find(JButton b){
		if (b==ch.getHunter()||b==ch.getHunter2())
			try {
				return new Hunter();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (b==ch.getWarlock()||b==ch.getWarlock2())
			try {
				return new Warlock();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (b==ch.getPriest()||b==ch.getPriest2())
			try {
				return new Priest();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (b==ch.getPaladin()||b==ch.getPaladin2())
			try {
				return new Paladin();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (b==ch.getMage()||b==ch.getMage2())
			try {
				return new Mage();
			} catch (IOException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException, FullHandException {

		new Controller();
		
	}
	@Override
	public void onGameOver() {
		view.dispose();
		ending=new Endgame();
		if(model.getCurrentHero().getCurrentHP()!=0)
		ending.getEnd().setText("ALF ALF ALF MABROOK"+"\n"+"\n"+"\n"+model.getCurrentHero().getName());
		else 
			ending.getEnd().setText("ALF ALF ALF MABROOK"+"\n"+"\n"+"\n"+model.getOpponent().getName());
		// TODO Auto-generated method stub
		ending.getEnd().setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC , 100));
	}
}