package com.graphique;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.general.DAO;
import com.general.EcoleDAO;
import com.general.Formateur;
import com.general.FormateurDAO;
import com.general.Promo;
import com.general.PromoDAO;
import com.general.Reservation;
import com.general.ReservationDAO;
import com.general.Salle;
import com.general.SalleDAO;

public class VueReserver extends JFrame implements MouseListener{

	private JPanel container = new JPanel(); // conteneur principal
	private JPanel liste = new JPanel(); //panel avec menus deroulants
	private JPanel bouRadio = new JPanel(); //panel avec boutons radios
	private JPanel intitule = new JPanel(); // panel avec intitule de la formation
	private JPanel boutFin = new JPanel(); //panel avec bouton en bas de la fenetre
	
	private JMenuBar barre = new JMenuBar();//barre de menu
	
	private JLabel labelRes = new JLabel("Réserver");
	private JLabel labSalle = new JLabel("#nom de la salle#");
	private JLabel promo = new JLabel("Promo");
	private JLabel formateur = new JLabel("Formateur");
	
	private JMenu salle = new JMenu("Salle");
	
	private JButton supp = new JButton("Supprimer réservation");
	private JButton ajou = new JButton("Ajouter Réservation");
	private JButton  annul = new JButton("Annuler");
	
	private TextField text = new TextField("Saisir nom du cours");
	
	private String [] tabJour = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
	
	private JMenuItem salles;
	
	private JComboBox<String> formateurs;
	private JComboBox<String> promos;
	
	String dateSel;
	Salle salleSel;

	SalleDAO salleDAO = new SalleDAO();
	PromoDAO promoDAO = new PromoDAO();
	EcoleDAO ecoleDAO = new EcoleDAO();
	FormateurDAO formateurDAO = new FormateurDAO();
	ReservationDAO resaDAO = new ReservationDAO();
	
	public VueReserver(Salle salle, String date)
	{
		this.setTitle("Réservation");
		this.setSize(600,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.salleSel = salle;
		this.dateSel = date;
		
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
		barre.add(salle);
		barre.add(labSalle);
		
		Iterator<Salle> it = salleDAO.findAll().iterator();
		while (it.hasNext())
		{
			Salle s = it.next();
			salles = new JMenuItem(s.getNomSalle());
//			salles = new JMenuItem(""+ s.getNumSalle());
//			salles.addMouseListener(this);
			salles.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					salles = (JMenuItem) (e.getSource());
					String s = salles.getText();
					
//					System.out.println(s);
					labSalle.setText(s);
				}
			});
			salle.add(salles);	
		}

			
	}

	public void menu2()
	{
		promos = new JComboBox<String>();//affiche les choix d'une promo de la BDD à cote deu menu deroulant promo
		
		Iterator<Promo> it = promoDAO.findAll().iterator();
		while (it.hasNext())
		{
			Promo s = it.next();
			promos.addItem(s.getNomPromo());
			promos.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					promos = (JComboBox<String>) (e.getSource());
					String s = promos.getSelectedItem().toString();
//					System.out.println(s);	
				}
			});
			
			promo.add(promos);

		}
//		promos.addActionListener(new boutAjouRéservation());
		formateurs = new JComboBox<String>();

		Iterator<Formateur> ite = formateurDAO.findAll().iterator();
		while (ite.hasNext())
		{
			Formateur s = ite.next();
			formateurs.addItem(s.getNomFormateur());
			formateurs.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					formateurs = (JComboBox<String>) (e.getSource());
					String s = formateurs.getSelectedItem().toString();
					//					System.out.println(s);	
				}
			});
			formateur.add(formateurs);
		}
		liste.add(formateur);//ajout dans le panel 
		liste.add(formateurs);
		liste.add(promo);
		liste.add(promos);
		liste.add(supp);
		supp.setEnabled(false);
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
			box.setEnabled(false);
			bouRadio.add(box);
		}
		
		container.add(bouRadio);
		boutFin.add(ajou);
		ajou.addActionListener(new boutAjouRéservation());
		boutFin.add(annul);
		annul.addMouseListener(this);
		ajou.addMouseListener(this);
		container.add(boutFin);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stud
			this.dispose();
	}

	public class boutAjouRéservation implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Formateur formateur = formateurDAO.find(formateurs.getSelectedItem().toString());
			Promo promo = promoDAO.find(promos.getSelectedItem().toString());

			System.out.println(promos.getSelectedItem());
			System.out.println(formateurs.getSelectedItem());
			
			Reservation reservation = new Reservation(0, dateSel, salleSel, promo, formateur, text.getText(), ecoleDAO.find(1));
		
			resaDAO.create(reservation);
			
		}
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
