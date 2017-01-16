package com.general;

import java.sql.Date;

public class Reservation {
	
	private int idResa;
	private Date dateResa;
	private Salle salleResa;
	private Promo promoResa;
	private Formateur formateurResa;
	private String matiereResa;
	private Ecole ecoleResa;
	
	
	/**
	 * @param dateResa
	 * @param salleResa
	 * @param promoResa
	 * @param formateurResa
	 * @param matiereResa
	 */
	public Reservation(int idResa, Date dateResa, Salle salleResa, Promo promoResa, Formateur formateurResa, String matiereResa, Ecole ecoleResa) {
		
		this.idResa = idResa;
		this.dateResa = dateResa;
		this.salleResa = salleResa;
		this.promoResa = promoResa;
		this.formateurResa = formateurResa;
		this.matiereResa = matiereResa;
		this.ecoleResa = ecoleResa;
	}
	
	
	
	



	public Reservation(){}


	public Date getDateResa() {
		return dateResa;
	}


	public void setDateResa(Date dateResa) {
		this.dateResa = dateResa;
	}


	public Salle getSalleResa() {
		return salleResa;
	}


	public void setSalleResa(Salle salleResa) {
		this.salleResa = salleResa;
	}


	public Promo getPromoResa() {
		return promoResa;
	}


	public void setPromoResa(Promo promoResa) {
		this.promoResa = promoResa;
	}


	public Formateur getFormateurResa() {
		return formateurResa;
	}


	public void setFormateurResa(Formateur formateurResa) {
		this.formateurResa = formateurResa;
	}


	public String getMatiereResa() {
		return matiereResa;
	}


	public void setMatiereResa(String matiereResa) {
		this.matiereResa = matiereResa;
	}


	public Ecole getEcoleResa() {
		return ecoleResa;
	}


	public void setEcoleResa(Ecole ecoleResa) {
		this.ecoleResa = ecoleResa;
	}


	public int getIdResa() {
		return idResa;
	}


	public void setIdResa(int idResa) {
		this.idResa = idResa;
	}
	
	@Override
	public String toString() {
		return "Reservation [\nidResa=" + idResa + ", \ndateResa=" + dateResa + ", \nsalleResa=" + salleResa + ", \npromoResa="
				+ promoResa + ", \nformateurResa=" + formateurResa + ", \nmatiereResa=" + matiereResa + ", \necoleResa="
				+ ecoleResa + "\n]";
	}

}
