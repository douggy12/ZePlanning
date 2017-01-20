package com.graphique;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueGererPromo extends JFrame
{	
	private JLabel ajouter = new JLabel ("Ajouter Promo");
	private JButton supprimer = new JButton ("Supprimer Promo");
	private JTextField formulaire= new JTextField("nom");
	private JButton enregistrer = new JButton("Enregistrer");

	private String[] promos={"DL JMQ", "POE GEN DL", "Alternance"}; // tableau de formateurs à générer avec 1 requête?
	
	private JComboBox<String> listePromos = new JComboBox<String> (promos);
	
	private JPanel contenant = new JPanel();
	
	private JPanel actionPan = new JPanel();
	private JPanel formPan = new JPanel();
	private JPanel validPan = new JPanel();
	
	private JPanel saisie = new JPanel();
	private JPanel listing = new JPanel();

	
	public VueGererPromo()
	{
		this.setTitle("Gérer Promo");
		this.setSize(256,256);
		this.setLocation(this.getX()+this.getWidth()*2, this.getHeight()/2);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBackground(Color.WHITE);
		
		contenant.setLayout(new BorderLayout());
		setContentPane(contenant);
		
		formulaire();
		listing();

		contenant.add(saisie, BorderLayout.NORTH);
		contenant.add(listing, BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	void formulaire()
	{
		saisie.setLayout(new BoxLayout(saisie, BoxLayout.PAGE_AXIS));
		actionPan.add(ajouter);
		formPan.add(formulaire);
		validPan.add(enregistrer);
		saisie.add(actionPan);
		saisie.add(formPan);
		saisie.add(validPan);
	}
	
	void listing()
	{
		listing.setLayout(new BoxLayout(listing, BoxLayout.PAGE_AXIS));
		listing.add(listePromos);
		listing.add(supprimer);
	}
}
