package com.general;

public class Promo {
	private int idPromo;
	private String nomPromo;
	private int nbEtudiant;
	private String description;
	/**
	 * @param nomPromo
	 * @param nbEtudiant
	 * @param description
	 */
	public Promo(int idPromo,String nomPromo, int nbEtudiant, String description) {
		this.setIdPromo(idPromo);
		this.nomPromo = nomPromo;
		this.nbEtudiant = nbEtudiant;
		this.description = description;
	}
	
	public Promo(){}
	
	public String getNomPromo() {
		return nomPromo;
	}
	public void setNomPromo(String nomPromo) {
		this.nomPromo = nomPromo;
	}
	public int getNbEtudiant() {
		return nbEtudiant;
	}
	public void setNbEtudiant(int nbEtudiant) {
		this.nbEtudiant = nbEtudiant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdPromo() {
		return idPromo;
	}
	public void setIdPromo(int idPromo) {
		this.idPromo = idPromo;
	}

	@Override
	public String toString() {
		return "Promo [idPromo=" + idPromo + ", nomPromo=" + nomPromo + ", nbEtudiant=" + nbEtudiant + ", description="
				+ description + "]";
	}
	
	

}
