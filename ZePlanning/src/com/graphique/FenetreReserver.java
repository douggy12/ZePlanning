package com.graphique;

import java.awt.MenuBar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class FenetreReserver extends JFrame implements MouseListener{

	private JPanel container = new JPanel(); // conteneur
	private JPanel lab = new JPanel(); // panel avec les labels
	private JPanel liste = new JPanel(); //panel avec menus deroulants
	private JPanel bouRadio = new JPanel(); //panel avec boutons radios
	private JPanel intitule = new JPanel(); // panel avec intitule de la formation
	
	private JCheckBoxMenuItem bout; 
	private JLabel labelForm = new JLabel ("Formateur");
	private JLabel labelPromo = new JLabel ("Promo");
	
	private JMenu formateur;
	private JMenu promo;
	private JMenuBar barre;
	
	public FenetreReserver()
	{
		this.setTitle("Réservation");
		this.setSize(512,768);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu1();
		
		
		this.setVisible(true);
			
	}
	
	public void menu1()
	{
		this.getContentPane().add(container);
//		container.setLayout(mgr);
		lab.add(labelForm);
		lab.add(labelPromo);
//		container.add(lab, )
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
