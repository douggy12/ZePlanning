package com.graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.BoxLayout;
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
import javax.swing.SwingConstants;
import javax.swing.JSpinner.ListEditor;

import com.general.DAO;
import com.general.Reservation;
import com.general.ReservationDAO;
import com.general.Salle;
import com.general.SalleDAO;

public class VuePlanning extends JFrame implements MouseListener, Observer, ActionListener
{
	private ControlleurPlanning controler;
	
	private JPanel contenant = new JPanel(); // pour contenant
	private JPanel navi = new JPanel(); //  pour BoxLayout
	private JPanel grille = new JPanel(); // pour GridLayout
	
	Font dateFont = new Font("Arial", Font.PLAIN, 16);
	
	JLabel lundi = new JLabel("lundi",SwingConstants.CENTER);
	JLabel mardi = new JLabel("mardi",SwingConstants.CENTER);
	JLabel mercredi = new JLabel("mercredi",SwingConstants.CENTER);
	JLabel jeudi = new JLabel("jeudi",SwingConstants.CENTER);
	JLabel vendredi = new JLabel("vendredi",SwingConstants.CENTER);
	
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
	
	
	
	private String ville="Campus du Mans";
	private JLabel etablissement = new JLabel(ville,SwingConstants.CENTER);
	
	DAO<Salle> salleDAO = new SalleDAO();
	ReservationDAO reservationDAO = new ReservationDAO();
	
	private ArrayList<String> listeJours= new ArrayList<>();
	private ArrayList<String> listeDates = new ArrayList<>();
	
	private Font salleFont = new Font("Arial", Font.BOLD, 18);
	
	
	
	public VuePlanning (ControlleurPlanning controler)
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
		
		
	}
		
	void MenuBar()
	{
		JMenuBar barreMenus = new JMenuBar();
		setJMenuBar(barreMenus);
		
		JMenu consulter = new JMenu("Consulter");
		barreMenus.add(consulter);
		JMenu reserver = new JMenu("R�server");
		barreMenus.add(reserver);
		JMenu gerer = new JMenu("Ajouter");
		barreMenus.add(gerer);

		JMenuItem formateur = new JMenuItem ("Formateur");
		gerer.add(formateur);
		formateur.addActionListener(new ajouterFormateur());
		gerer.addSeparator();
		JMenuItem promo = new JMenuItem ("Promo");
		gerer.add(promo);
		promo.addActionListener(new ajouterPromo());
		gerer.addSeparator();
		JMenuItem addSalle = new JMenuItem ("Salle");
		addSalle.addActionListener(new AjouterSalle());
		gerer.add(addSalle);
		
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
	
	
	
	void GrilleSemaine(ModelePlanning modele){
		
		grille.removeAll();
		grille.setLayout(new GridLayout(0,6));
		etablissement.setBorder(BorderFactory.createLineBorder(Color.black));
		etablissement.setOpaque(true);
		etablissement.setBackground(new Color(144, 147, 146));
		//etablissement.setForeground(setFont(f););
		grille.add(etablissement);
		
		
		grille.add(lundi);
		lundi.setBorder(BorderFactory.createLineBorder(Color.black));
		lundi.setFont(dateFont);
		
		grille.add(mardi);
		mardi.setBorder(BorderFactory.createLineBorder(Color.black));
		mardi.setFont(dateFont);
		
		grille.add(mercredi);
		mercredi.setBorder(BorderFactory.createLineBorder(Color.black));
		mercredi.setFont(dateFont);
		
		grille.add(jeudi);
		jeudi.setBorder(BorderFactory.createLineBorder(Color.black));
		jeudi.setFont(dateFont);
		
		grille.add(vendredi);
		vendredi.setBorder(BorderFactory.createLineBorder(Color.black));
		vendredi.setFont(dateFont);
		
		/*for (int j=0; j<listeJours.size(); j++)
		{
			JLabel jours = new JLabel(listeJours.get(j));
			jours.setBorder(BorderFactory.createLineBorder(Color.black));
			grille.add(jours);
		}*/
		
		for (int i=0; i<modele.getListeSalle().size();i++)
		{
			JLabel sallePanel = new JLabel(modele.getListeSalle().get(i).getNomSalle(),SwingConstants.CENTER);
			
			
			sallePanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			sallePanel.setFont(salleFont);
			
			
			grille.add(sallePanel);
			sallePanel.addMouseListener(new deleteSalleListener(modele.getListeSalle().get(i)));
			
			for (int j=0; j<listeJours.size();j++)
			{
				Reservation resa = reservationDAO.findByDate(modele.getListeSalle().get(i).getNomSalle(), listeDates.get(j));
				
				if(resa != null){
					JPanel resaPanel = new JPanel();
					//resaPanel.setLayout(new BoxLayout(resaPanel, BoxLayout.Y_AXIS));
					
					
					JLabel resaPanelPromo = new JLabel(resa.getPromoResa().getNomPromo()); // A remplacer par requete select * from reservation where salle=i and ...
					resaPanelPromo.setAlignmentX(CENTER_ALIGNMENT);
					resaPanelPromo.setForeground(Color.WHITE);
					
					JLabel resaPanelForm = new JLabel(resa.getFormateurResa().getPrenomFormateur()+" "+resa.getFormateurResa().getNomFormateur());
					JLabel resaPanelMatiere = new JLabel(resa.getMatiereResa());	
					
					resaPanel.setBackground(new Color(144, 147, 146));
					resaPanel.setBorder(BorderFactory.createLineBorder(Color.black));
					resaPanel.setLayout(new BoxLayout(resaPanel, BoxLayout.Y_AXIS));
					
					resaPanel.add(resaPanelPromo);
					resaPanel.add(resaPanelForm);
					resaPanel.add(resaPanelMatiere);
					resaPanel.addMouseListener(new resaListener(resa));
					
					grille.add(resaPanel);
					
				}
				else {
					
					JPanel blanco = new JPanel();
					blanco.setBackground(Color.WHITE);
					blanco.addMouseListener(new addResaListener(modele.getListeSalle().get(i),listeDates.get(j)));
					grille.add(blanco);
				}
				
				
				
			}
			
			
			
			
		}
		System.out.println(listeDates.size());
		
		contenant.add(grille, BorderLayout.CENTER);
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
	
	class AjouterSalle implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			VueGererSalle vueGS = new VueGererSalle(controler);
			
		}
		
	}
	
	class resaListener implements MouseListener {
		
		Reservation resa;

		/**
		 * 
		 */
		public resaListener(Reservation resa) {
			super();
			this.resa = resa;
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (e.getButton() == MouseEvent.BUTTON3){
				JPopupMenu popup = new JPopupMenu();
				JMenuItem del = new JMenuItem("supprimer");
				popup.add(del);
				popup.show(e.getComponent(), e.getX(), e.getY());
				
				del.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						controler.supprimerResa(resa);
						
						
					}
				});
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
		
		
	}
	
	class addResaListener implements MouseListener{
		Salle salle;
		String date;
		

		/**
		 * 
		 */
		public addResaListener(Salle salle, String date) {
			super();
			this.salle = salle;
			this.date = date;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton()== MouseEvent.BUTTON3){
				JPopupMenu popup = new JPopupMenu();
				JMenuItem add = new JMenuItem("ajouter");
				popup.add(add);
				popup.show(e.getComponent(), e.getX(), e.getY());
				add.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						VueReserver vuereserver = new VueReserver(salle,date,controler);
						
					}
				});
			}
		}

		/**
		 * 
		 */
		

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
		
	}
	
	class deleteSalleListener implements MouseListener{
		Salle salle;
		/**
		 * 
		 */
		public deleteSalleListener(Salle salle) {
			super();
			this.salle = salle;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getButton()== MouseEvent.BUTTON3){
				JPopupMenu popup = new JPopupMenu();
				JMenuItem del = new JMenuItem("supprimer");
				popup.add(del);
				popup.show(e.getComponent(), e.getX(), e.getY());
				del.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						controler.supprimerSalle(salle);
						
						
					}
				});
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
		
	}
	
	class ajouterFormateur implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			VueGererFormateur vueGF = new VueGererFormateur();
			
		}
		
	}
	
	class ajouterPromo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			VueGererPromo vueGP = new VueGererPromo();
			
		}
		
	}
	
	@Override
	public void update(Observable o, Object obj) {
		// TODO Auto-generated method stub
		if(o instanceof ModelePlanning){
			
			ModelePlanning mod = (ModelePlanning)obj;
			yearNumber.setText(mod.getYear()+"");
			weekNumber.setText(mod.getWeekNum()+"");
			
			listeJours = mod.getSemaine();
			listeDates = mod.getDates();
			
			lundi.setText(mod.getSemaine().get(0));
			mardi.setText(mod.getSemaine().get(1));
			mercredi.setText(mod.getSemaine().get(2));
			jeudi.setText(mod.getSemaine().get(3));
			vendredi.setText(mod.getSemaine().get(4));
			//this.setVisible(false);
			//this.setVisible(true);
			
			GrilleSemaine(mod);
			
			this.validate();
			this.repaint();
			System.out.println("update");
			
			
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

	}
	
		

	
}
