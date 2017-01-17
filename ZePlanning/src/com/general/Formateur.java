package com.general;

public class Formateur {
	private int idFormateur;
	private String nomFormateur;
	private String prenomFormateur;
	/**
	 * @param nomFormateur
	 * @param prenomFormateur
	 */
	public Formateur(String nomFormateur, String prenomFormateur) {
		
		this.nomFormateur = nomFormateur;
		this.prenomFormateur = prenomFormateur;
	}
	
	public Formateur(){}
	
	public String getNomFormateur() {
		return nomFormateur;
	}
	public void setNomFormateur(String nomFormateur) {
		this.nomFormateur = nomFormateur;
	}
	public String getPrenomFormateur() {
		return prenomFormateur;
	}
	public void setPrenomFormateur(String prenomFormateur) {
		this.prenomFormateur = prenomFormateur;
	}

	public int getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}

	@Override
	public String toString() {
		return "Formateur [idFormateur=" + idFormateur + ", nomFormateur=" + nomFormateur + ", prenomFormateur="
				+ prenomFormateur + "]";
	}
	
	
	

}
