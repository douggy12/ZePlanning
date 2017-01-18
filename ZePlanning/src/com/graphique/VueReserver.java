package com.graphique;

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.SeparatorUI;

public class VueReserver extends JFrame implements MouseListener{

	private JPanel container = new JPanel(); // conteneur principal
//	private JPanel lab = new JPanel(); // panel avec les labels
	private JPanel liste = new JPanel(); //panel avec menus deroulants
	private JPanel bouRadio = new JPanel(); //panel avec boutons radios
	private JPanel intitule = new JPanel(); // panel avec intitule de la formation
	private JMenuBar barre = new JMenuBar();//barre de menu
	
	private JLabel labelRes = new JLabel("Réserver");
	
	private JMenu formateur = new JMenu("Formateur");
	private JMenu promo = new JMenu("Promo");
	private JButton annul = new JButton("Supprimer réservation");
	
	private TextField text = new TextField("Saisir nom du cours");
	
	String [] tabJour = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
//	String [] salle =  new listeSalle;
	
	public VueReserver()
	{
		this.setTitle("Réservation");
		this.setSize(480,480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu1();
		menu2();
		menu3();
		menu4();
		
		this.setVisible(true);
			
	}
	
	public void menu1()
	{
		this.getContentPane().add(container);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		//ajout de la barre de menu
		setJMenuBar(barre);
		
		barre.add(labelRes);
		barre.add(new JMenu("Salle"));
		
		
		
	}

	public void menu2()
	{
		liste.add(formateur);
		liste.add(promo);
		liste.add(annul);
		container.add(liste);
	}
	
	public void menu3()
	{
		
		intitule.add(new JLabel("Intitulé du cours"));
		intitule.add(text);
		container.add(intitule);		
	}
	
	public void menu4()
	{
		 
		for (int i=0; i<tabJour.length; i++)
		{
			JCheckBox box = new JCheckBox(tabJour[i]);
			bouRadio.add(box);
		}
		
		
		container.add(bouRadio);
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
