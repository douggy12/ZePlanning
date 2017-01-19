package com.graphique;

public class ControlleurPrincipale {
	private ModelePrincipale modele;

	/**
	 * @param modele
	 */
	public ControlleurPrincipale(ModelePrincipale modele) {
		
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
	

}
