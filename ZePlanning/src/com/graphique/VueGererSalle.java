package com.graphique;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.general.Salle;

public class VueGererSalle extends JFrame
{
	private int salle;
	private JPanel contenant = new JPanel();
	private JPanel top = new JPanel();
	private JPanel middle = new JPanel();
	private JPanel bottom = new JPanel();
	private JPanel validation = new JPanel();
	private JButton enregistrer = new JButton("Enregistrer");
	
	//private String[] salles={"1", "2", "3", "4", "5"};
	
	private JLabel numSalleLabel = new JLabel("N° de la salle");
	private JTextField numSalle = new JTextField();
	
	private JTextField nomSalle = new JTextField();
	private JLabel nomSalleLabel = new JLabel ("Nom de la salle  ");
	private JLabel capacite = new JLabel ("Capacité (places)");
	private JLabel chaises = new JLabel ("Nombre de chaises");
	private JLabel bureaux = new JLabel ("Nombre de tables");
	private JLabel PC = new JLabel ("Nombre de PC");
	private JLabel tableau = new JLabel ("Tableau");
	private JLabel Videoprojecteur = new JLabel ("Videoprojecteur");
	
	private JTextField formCapacite = new JTextField();
	private JTextField formChaises = new JTextField();
	private JTextField formBureaux = new JTextField();
	private JTextField formPC = new JTextField();

	private JCheckBoxMenuItem tickTableau = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem tickVideopro = new JCheckBoxMenuItem();
	
	
	public VueGererSalle()
	{
		this.salle=salle;
		this.setTitle("Gérer Salle");
		this.setSize(350,480);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBackground(Color.WHITE);

		setContentPane(contenant);
		
		choixSalle();
		postes();
		equipSalle();
		validation();

		contenant.setLayout(new BoxLayout(contenant, BoxLayout.Y_AXIS));
		top.setPreferredSize(new Dimension(150, 20));
		contenant.add(top);
		middle.setAlignmentX(RIGHT_ALIGNMENT);
		contenant.add(middle);
		contenant.add(bottom);
		contenant.add(validation);
		this.setVisible(true);
	}
	
	void choixSalle()
	{
		top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
		top.add(numSalleLabel);
		top.add(numSalle);
		numSalle.setMaximumSize(new Dimension(50, 20));
		top.add(nomSalleLabel);
		top.add(nomSalle);
		nomSalle.setMaximumSize(new Dimension(150, 20));
		
	}
	
	void postes()
	{
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		
		JPanel capaLig = new JPanel();
		capaLig.add(capacite);
		Dimension boxDim = new Dimension(30, 20);
		
		capaLig.add(formCapacite);
		formCapacite.setPreferredSize(boxDim);
		middle.add(capaLig);
		JPanel chLig = new JPanel();
		chLig.add(chaises);
		chLig.add(formChaises);
		formChaises.setPreferredSize(boxDim);
		middle.add(chLig);
		JPanel burLig = new JPanel();
		burLig.add(bureaux);
		burLig.add(formBureaux);
		formBureaux.setPreferredSize(boxDim);
		middle.add(burLig);
		JPanel pcLig = new JPanel();
		pcLig.add(PC);
		pcLig.add(formPC);
		formPC.setPreferredSize(boxDim);
		middle.add(pcLig);
	}
	
	void equipSalle()
	{
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));
		bottom.add(tableau);
		bottom.add(tickTableau);
		bottom.add(Videoprojecteur);
		bottom.add(tickVideopro);
	}
	
	void validation()
	{
		validation.setLayout(new BoxLayout(validation, BoxLayout.LINE_AXIS));
		validation.add(enregistrer);
//		enregistrer.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String nomS = nomSalle.getText();
//				int numS = Integer.parseInt(numSalle.getText());
//				int capa = Integer.parseInt(formCapacite.getText());
//				int nbChaise = Integer.par
//				int nbTable
//				int nbPC
//				boolean tableau,videoProj;
//				
//				Salle salle = new Salle(0, numS, nbPC, nbChaise, nbChaise, videoProj, tableau, nomS);
//				
//			}
//		});
//	}
}
	
}