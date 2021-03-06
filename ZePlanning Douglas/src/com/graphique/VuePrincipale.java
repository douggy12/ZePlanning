package com.graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JSpinner.ListEditor;

import com.general.DAO;
import com.general.Reservation;
import com.general.ReservationDAO;
import com.general.Salle;
import com.general.SalleDAO;

public class VuePrincipale extends JFrame implements MouseListener, Observer
{
	private ControlleurPrincipale controler;
	
	private JPanel contenant = new JPanel(); // pour contenant
	private JPanel navi = new JPanel(); //  pour BoxLayout
	private JPanel grille = new JPanel(); // pour GridLayout
	
	JLabel lundi = new JLabel("lundi");
	JLabel mardi = new JLabel("mardi");
	JLabel mercredi = new JLabel("mercredi");
	JLabel jeudi = new JLabel("jeudi");
	JLabel vendredi = new JLabel("vendredi");
	
	private JButton navLeft = new JButton("Pr�c�dente");
	private JButton navRight = new JButton("Suivante");
	private JLabel semaine = new JLabel ("Semaine");
	private JLabel annee = new JLabel("Ann�e");
	
	private ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
	private int weekNum = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
	private int year = now.get(IsoFields.WEEK_BASED_YEAR);
	private JTextField yearNumber = new JTextField(Integer.toString(year));
	private JTextField weekNumber = new JTextField(Integer.toString(weekNum)); // texte � updater
	private JPopupMenu popResa;
	
	private String ville="Le Mans";
	private JLabel etablissement = new JLabel(ville);
	
	DAO<Salle> salleDAO = new SalleDAO();
	ReservationDAO reservationDAO = new ReservationDAO();
	
	private ArrayList<String> listeJours= new ArrayList<>();
	private ArrayList<String> listeDates = new ArrayList<>();
	private ArrayList<Salle> listeSalle = salleDAO.findAll();
	private ArrayList<Reservation> listeResa;
	
	private String formateur;
	private String promo;
	private String matiere;

	
	public VuePrincipale (ControlleurPrincipale controler)
	{
		this.controler = controler;
		this.setTitle("IMIE - Planning des salles");
		this.setSize(1280,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBackground(Color.WHITE);
				
		contenant.setLayout(new BorderLayout());
		setContentPane(contenant);
			
		init();
		refresh();
		this.setVisible(true);
		
		
		
		
	}
	public void init(){
		
		
		MenuBar();
		SemNavigation();
		GrilleSemaine();
		PopUpReserver();
	}
		
	void MenuBar()
	{
		JMenuBar barreMenus = new JMenuBar();
		setJMenuBar(barreMenus);
		
		JMenu consulter = new JMenu("Consulter");
		barreMenus.add(consulter);
		JMenu reserver = new JMenu("R�server");
		barreMenus.add(reserver);
		JMenu gerer = new JMenu("G�rer");
		barreMenus.add(gerer);

		JMenuItem formateur = new JMenuItem ("Formateur");
		gerer.add(formateur);
		gerer.addSeparator();
		JMenuItem promo = new JMenuItem ("Promo");
		gerer.add(promo);
		gerer.addSeparator();
		JMenuItem etudiant = new JMenuItem ("Etudiant");
		gerer.add(etudiant);
		
		consulter.setToolTipText("Vue actuelle!"); // bulle d'aide, affect�e au JMenu
	}	
	
	void SemNavigation()
	{
		contenant.add(navi, BorderLayout.NORTH);
		navi.add(annee);
		navi.add(yearNumber);
		navi.add(navLeft);
		navLeft.addActionListener(new btnSemaine());
		navi.add(semaine);
		navi.add(weekNumber);
		navi.add(navRight);
		navRight.addActionListener(new btnSemaine());
	}
	
	
	
	void GrilleSemaine(){
		
		grille.removeAll();
		grille.setLayout(new GridLayout(0,6));
		etablissement.setBorder(BorderFactory.createLineBorder(Color.black));
		etablissement.setOpaque(true);
		etablissement.setBackground(new Color(144, 147, 146));
		//etablissement.setForeground(setFont(f););
		grille.add(etablissement);
		
		
		grille.add(lundi);
		lundi.setBorder(BorderFactory.createLineBorder(Color.black));
		
		grille.add(mardi);
		mardi.setBorder(BorderFactory.createLineBorder(Color.black));
		
		grille.add(mercredi);
		mercredi.setBorder(BorderFactory.createLineBorder(Color.black));
		
		grille.add(jeudi);
		jeudi.setBorder(BorderFactory.createLineBorder(Color.black));
		
		grille.add(vendredi);
		vendredi.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/*for (int j=0; j<listeJours.size(); j++)
		{
			JLabel jours = new JLabel(listeJours.get(j));
			jours.setBorder(BorderFactory.createLineBorder(Color.black));
			grille.add(jours);
		}*/
		
		for (int i=0; i<listeSalle.size();i++)
		{
			JLabel salle = new JLabel(listeSalle.get(i).getNomSalle());
			
			
			salle.setBorder(BorderFactory.createLineBorder(Color.black));
			grille.add(salle);
			
			for (int j=0; j<listeJours.size();j++)
			{
				Reservation resa = reservationDAO.findByDate(listeSalle.get(i).getNomSalle(), listeDates.get(j));
				
				if(resa != null){
					
					JTextArea resaPanel = new JTextArea (); // A remplacer par requete select * from reservation where salle=i and ...
					resaPanel.setText(resa.getPromoResa().getNomPromo()
							+"\n"+resa.getFormateurResa().getPrenomFormateur()+" "+resa.getFormateurResa().getNomFormateur()
							+"\n"+resa.getMatiereResa());
					resaPanel.setBackground(new Color(144, 147, 146));
					resaPanel.setBorder(BorderFactory.createLineBorder(Color.black));
					grille.add(resaPanel);
				}
				else {
					
					JPanel blanco = new JPanel();
					blanco.setBackground(Color.WHITE);
					grille.add(blanco);
				}
				
				
				
			}
			
			
			
			
		}
		System.out.println(listeDates.size());
		
		contenant.add(grille, BorderLayout.CENTER);
	}
	
	void PopUpReserver()
	{
		JPopupMenu popResa = new JPopupMenu("R�server");
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
	class btnSemaine implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == navLeft){
				controler.setDate(false);
			}
			else controler.setDate(true);
			
		}
		
	}
	public void refresh(){
		controler.setDate();
	}

	@Override
	public void update(Observable o, Object obj) {
		// TODO Auto-generated method stub
		if(o instanceof ModelePrincipale){
			
			ModelePrincipale mod = (ModelePrincipale)obj;
			yearNumber.setText(mod.getYear()+"");
			weekNumber.setText(mod.getWeekNum()+"");
			
			listeJours = mod.getSemaine();
			listeDates = mod.getDates();
			
			lundi.setText(mod.getSemaine().get(0));
			mardi.setText(mod.getSemaine().get(1));
			mercredi.setText(mod.getSemaine().get(2));
			jeudi.setText(mod.getSemaine().get(3));
			vendredi.setText(mod.getSemaine().get(4));
			
			GrilleSemaine();
			
			
			
		}
	}
	
		

	
}
