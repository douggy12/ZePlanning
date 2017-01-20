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

public class VueGererFormateur extends JFrame
{	
	private JLabel ajouter = new JLabel ("Ajouter Formateur");
	private JButton supprimer = new JButton ("Supprimer Formateur");
	private JTextField formulaire1= new JTextField("nom");
	private JTextField formulaire2= new JTextField("prénom");
	private JButton enregistrer = new JButton("Enregistrer");
	
	

	private String[] formateurs={"Riri", "Fifi", "Loulou", "Mickey", "Donald"}; // tableau de formateurs à générer avec 1 requête?
	
	private JComboBox<String> listeFormateurs = new JComboBox<String> (formateurs);
	
	private JPanel contenant = new JPanel();
	
	private JPanel actionPan = new JPanel();
	private JPanel formPan = new JPanel();
	private JPanel validPan = new JPanel();
	
	private JPanel saisie = new JPanel();
	private JPanel listing = new JPanel();
	
	public VueGererFormateur()
	{
		this.setTitle("Gérer Formateur");
		this.setSize(280,256);
		this.setLocation(this.getX()+this.getWidth(), this.getHeight()/2);
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
		formPan.add(formulaire1);
		formPan.add(formulaire2);
		validPan.add(enregistrer);
		saisie.add(actionPan);
		saisie.add(formPan);
		saisie.add(validPan);
	}
	
	void listing()
	{
		listing.setLayout(new BoxLayout(listing, BoxLayout.PAGE_AXIS));
		listing.add(listeFormateurs);
		listing.add(supprimer);
	}	
}