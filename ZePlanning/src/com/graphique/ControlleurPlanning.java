package com.graphique;

import com.general.Reservation;
import com.general.Salle;
import com.general.SalleDAO;

public class ControlleurPlanning {
	private ModelePlanning modele;

	/**
	 * @param modele
	 */
	public ControlleurPlanning(ModelePlanning modele) {
		
		this.modele = modele;
	}
	
	public void setDate(int weekNb, int year){
		modele.setSemaine(weekNb, year);
	}
	public void setDate(boolean sens){
		if(!sens)modele.setSemaine(modele.getWeekNum()-1, modele.getYear());
		else modele.setSemaine(modele.getWeekNum()+1, modele.getYear());

	}
	public void setDate(){
		modele.setSemaine(modele.getWeekNum(), modele.getYear());
	}
	public void refresh(){
		modele.refreshVue();
		System.out.println("fresh");

	}
	public void ajouterSalle(Salle salle){
		modele.ajouterSalle(salle);
		
	}
	
	public void supprimerSalle(Salle salle){
		modele.supprimerSalle(salle);
	}
	
	public void ajouterResa(Reservation resa){
		modele.ajouterResa(resa);
	}
	
	public void supprimerResa(Reservation resa){
		modele.supprimerResa(resa);
	}
	

}
