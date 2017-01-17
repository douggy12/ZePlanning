package com.graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VuePrincipale extends JFrame implements MouseListener, Observer
{
	private int nbSalles=5;
	private JPanel contenant = new JPanel(); // pour contenant
	private JPanel navi = new JPanel(); //  pour BoxLayout
	private JPanel grille = new JPanel(); // pour GridLayout
	
	private JButton navLeft = new JButton("Précédente");
	private JButton navRight = new JButton("Suivante");
	private JLabel semaine = new JLabel ("Semaine");
	private JLabel annee = new JLabel("Année");
	private JTextField yearNumber = new JTextField("0");
	private JTextField weekNumber = new JTextField("42"); // texte à updater
	private JPopupMenu popResa;
	
	private String ville="Le Mans";
	private JLabel etablissement = new JLabel(ville);
	private String ListeJours[]={"Lundi", "Mardi", "Mercredi","Jeudi","Vendredi"};
	private String ListeSalles[]={"Salle 1", "Salle 2", "Salle 3", "Salle 4", "Salle 5"};

	
	public VuePrincipale ()
	{
		this.setTitle("IMIE - Planning des salles");
		this.setSize(1280,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBackground(Color.WHITE);
		
		contenant.setLayout(new BorderLayout());
		getContentPane().add(contenant);
		
		MenuBar();
		SemNavigation();
		GrilleSemaine();
		
		PopUpReserver();
		this.setVisible(true);
		
	}
		
	void MenuBar()
	{
		JMenuBar barreMenus = new JMenuBar();
		setJMenuBar(barreMenus);
		
		JMenu consulter = new JMenu("Consulter");
		barreMenus.add(consulter);
		JMenu reserver = new JMenu("Réserver");
		barreMenus.add(reserver);
		JMenu gerer = new JMenu("Gérer");
		barreMenus.add(gerer);

		JMenuItem formateur = new JMenuItem ("Formateur");
		gerer.add(formateur);
		gerer.addSeparator();
		JMenuItem promo = new JMenuItem ("Promo");
		gerer.add(promo);
		gerer.addSeparator();
		JMenuItem etudiant = new JMenuItem ("Etudiant");
		gerer.add(etudiant);
		
		consulter.setToolTipText("Vue actuelle!"); // bulle d'aide, affectée au JMenu
	}	
	
	void SemNavigation()
	{
		contenant.add(navi, BorderLayout.NORTH);
		navi.add(annee);
		navi.add(yearNumber);
		navi.add(navLeft);
		navi.add(semaine);
		navi.add(weekNumber);
		navi.add(navRight);
	}
	
	void GrilleSemaine()
	{
		grille.setLayout(new GridLayout(0,6));
		etablissement.setBorder(BorderFactory.createLineBorder(Color.black));
		etablissement.setOpaque(true);
		etablissement.setBackground(new Color(144, 147, 146));
		//etablissement.setForeground(setFont(f););
		grille.add(etablissement);
		for (int j=0; j<ListeJours.length; j++)
		{
			JLabel jours = new JLabel(ListeJours[j]);
			jours.setBorder(BorderFactory.createLineBorder(Color.black));
			grille.add(jours);
		}
		
		for (int i=0; i<nbSalles;i++)
		{
			JLabel salle = new JLabel(ListeSalles[i]);
			salle.setBorder(BorderFactory.createLineBorder(Color.black));
			grille.add(salle);
			for (int j=0; j<ListeJours.length;j++)
			{

				JTextArea resa = new JTextArea (); // A remplacer par requete select * from reservation where salle=i and ...
				resa.setText("\n1\n2\n3\n");
				resa.setBackground(new Color(144, 147, 146));
				resa.setBorder(BorderFactory.createLineBorder(Color.black));
				grille.add(resa);
				
			}
			
			
		}
		
		contenant.add(grille, BorderLayout.CENTER);
	}
	
	void PopUpReserver()
	{
		JPopupMenu popResa = new JPopupMenu("Réserver");
		popResa.add(new JMenu("Resa"));
	
		this.addMouseListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON2)
		{
			popResa.show(getContentPane(), e.getX(), e.getY());
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
		

	
}
