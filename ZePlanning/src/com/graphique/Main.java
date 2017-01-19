package com.graphique;

public class Main {

	public static void main(String[] args)
	{
		
		vuePrincipale();
		//vueGererSalle();
		
		
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

}
