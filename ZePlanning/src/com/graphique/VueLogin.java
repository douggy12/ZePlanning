package com.graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.general.LoginUser;
import com.general.LoginUserDAO;

public class VueLogin extends JFrame
{
	private JPanel contenant = new JPanel();
	private JPanel messagePan = new JPanel();
	private JLabel message = new JLabel();

	private JPanel bienvenuePan = new JPanel();
	private JLabel bienvenue = new JLabel ("Bienvenue à l' IMIE\n");

	private JPanel logPan = new JPanel();
	private JLabel logLabel = new JLabel ("Login ");
	private JTextField formLogin = new JTextField("");


	private JPanel pwdPan = new JPanel();
	private JLabel pwd = new JLabel ("Password");
	private JTextField formPwd = new JTextField("");

	private JButton valider = new JButton("Valider");

	private String logString="";
	private String pwdString="";
	private int userLevel;
	private boolean loginOK=false;

	public VueLogin()
	{
		this.setTitle("Identification");
		this.setSize(256,256);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(contenant);
		affichage();
		recuperation();
		
		

		contenant.setLayout(new BoxLayout(contenant, BoxLayout.PAGE_AXIS));
		contenant.add(bienvenuePan);
		contenant.add(messagePan);
		contenant.add(logPan);
		contenant.add(pwdPan);
		contenant.add(valider);
		this.setVisible(true);
	}

	public void affichage()
	{
		bienvenuePan.add(bienvenue);
		messagePan.add(message);
		bienvenue.setBorder(BorderFactory.createLineBorder(Color.black));
		contenant.add(bienvenuePan, BorderLayout.NORTH);

		formLogin.setPreferredSize(new Dimension(150, 20));
		formPwd.setPreferredSize(new Dimension(150, 20));

		logPan.add(logLabel);
		logPan.add(formLogin);
		contenant.add(logPan, BorderLayout.CENTER);

		pwdPan.add(pwd);
		pwdPan.add(formPwd);
		contenant.add(pwdPan, BorderLayout.SOUTH);
	}

	public void recuperation()
	{
		valider.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				logString=formLogin.getText();
				pwdString=formPwd.getText();
				userLevel=seLoguer();
				lancer();
				// on instancie la vue principale avec le userLevel :
				//VuePrincipale vue = new VuePrincipale(controler, userLevel);
				

			}
		});
		
	}


	public int seLoguer()
	{		
		LoginUserDAO loginuser = new LoginUserDAO();
		LoginUser login = loginuser.find(logString);
		
		
		if (login.getLogin()!=null)
		{
			if (login.getLogin().equals(logString))
			{
				if (login.getMdp().equals(pwdString))
				{
					messagePan.setOpaque(true);
					message.setForeground(new Color(33, 130,28));
					message.setText("Login OK!");
					loginOK=true;
					this.dispose();
					
					
				}
				else 
				{	
					messagePan.setOpaque(true);
					message.setForeground(new Color(255, 25,0));
					message.setText("Mot de passe erronné!");
					loginOK=false;
					this.formPwd.setText("");
					this.pwdString="";
				}
			}
		}
		else
		{
			messagePan.setOpaque(true);
			message.setForeground(new Color(255, 25,0));
			message.setText("Login erronné!");
			loginOK=false;
			this.formLogin.setText("");
			this.logString="";
		}
		if (loginOK)
		{
			return login.getUserLvl();
		}
		else return 404;
	}

	public int getUserLevel()
	{
		return userLevel;
	}
	
	public void lancer(){
		
		
		switch(userLevel){
		case 0 : 
			System.out.println("Lancer Planning Etudiant");
			break;
		case 1 :
			System.out.println("Lancer Planning Formateur");
			break;
		case 2 :
			System.out.println("go");
			vuePrincipale();
			break;
		case 3 :
			System.out.println("Lancer Vue Admin");
			break;
		default:
			System.out.println("erreur");
		}
	}
	public void vuePrincipale(){
		ModelePlanning modele = new ModelePlanning();
		
		ControlleurPlanning controler = new ControlleurPlanning(modele);
		
		VuePlanning vue = new VuePlanning(controler);
		
		modele.addObserver(vue);
		
		controler.refresh();
	}
}
