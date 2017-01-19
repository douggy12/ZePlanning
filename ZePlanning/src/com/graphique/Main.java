package com.graphique;

import com.general.Salle;
import com.general.SalleDAO;

public class Main {

	
	
	public static void main(String[] args)
	{
		vueReserver();
		//vuePrincipale();
//		vueGererSalle();
		
		
	}
	
	public static void vuePrincipale(){
ModelePrincipale modele = new ModelePrincipale();
		
		ControlleurPrincipale controler = new ControlleurPrincipale(modele);
		
		VuePrincipale vue = new VuePrincipale(controler);
		
		modele.addObserver(vue);
		
		controler.refresh();
	}
	
	public static void vueGererSalle(){
		VueGererSalle vue = new VueGererSalle();
	}

	public static void vueReserver()
	{
		SalleDAO salleDAO = new SalleDAO();
		Salle salle1 = salleDAO.find(1);
		String date1 = "2017-01-18";
		
		VueReserver vue1 = new VueReserver(salle1, date1);
	}
}
