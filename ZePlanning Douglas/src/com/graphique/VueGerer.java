package com.graphique;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueGerer extends JFrame
{
	private String item;
	private JLabel ajouter = new JLabel ("");
	
	private JPanel contenant = new JPanel();
	private JPanel saisie = new JPanel();
	private JTextField formulaire1= new JTextField("prénom");
	private JTextField formulaire2= new JTextField("nom");
	private JButton OK = new JButton("OK");
	
	
	public VueGerer(String item)
	{
		this.item=item;
		
		this.setTitle("Gérer "+item);
		this.setSize(512,768);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBackground(Color.WHITE);
		
		contenant.setLayout(new BorderLayout());
		getContentPane().add(contenant);
		
		
		ajouter.setText("Ajouter "+item+":");
		
		formulaire();

		this.setVisible(true);
	}
	
	void formulaire()
	{
		contenant.add(, BorderLayout.NORTH);
		saisie.add(formulaire1, BorderLayout.CENTER);
		saisie.add(formulaire2, BorderLayout.CENTER);
		saisie.add(OK, BorderLayout.SOUTH);
	}
	
}
//