package com.graphique;

public class Main {

	
	
	public static void main(String[] args)
	{
		
		//vueLogin();
		vuePrincipale();

		
				
	}
	
	
	
	public static void vueLogin(){
		VueLogin vue = new VueLogin();
		
		
	}
	public static void vuePrincipale(){
		ModelePlanning modele = new ModelePlanning();
		
		ControlleurPlanning controler = new ControlleurPlanning(modele);
		
		VuePlanning vue = new VuePlanning(controler);
		
		modele.addObserver(vue);
		
		controler.refresh();
	}
}
