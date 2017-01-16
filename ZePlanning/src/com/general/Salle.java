package com.general;

public class Salle {
	private int idSalle, numSalle, nbPC, nbBureaux, nbChaises;
	private boolean videoProjecteur, tableau;
	private String nomSalle;
	/**
	 * @param numSalle
	 * @param nbPC
	 * @param nbBureaux
	 * @param nbChaises
	 * @param videoProjecteur
	 * @param tableau
	 * @param nomSalle
	 */
	public Salle(int idSalle, int numSalle, int nbPC, int nbBureaux, int nbChaises, boolean videoProjecteur, boolean tableau,
			String nomSalle) {
		this.setIdSalle(idSalle);
		this.numSalle = numSalle;
		this.nbPC = nbPC;
		this.nbBureaux = nbBureaux;
		this.nbChaises = nbChaises;
		this.videoProjecteur = videoProjecteur;
		this.tableau = tableau;
		this.nomSalle = nomSalle;
	}
	
	public Salle(){}
	
	public int getNumSalle() {
		return numSalle;
	}
	public void setNumSalle(int numSalle) {
		this.numSalle = numSalle;
	}
	public int getNbPC() {
		return nbPC;
	}
	public void setNbPC(int nbPC) {
		this.nbPC = nbPC;
	}
	public int getNbBureaux() {
		return nbBureaux;
	}
	public void setNbBureaux(int nbBureaux) {
		this.nbBureaux = nbBureaux;
	}
	public int getNbChaises() {
		return nbChaises;
	}
	public void setNbChaises(int nbChaises) {
		this.nbChaises = nbChaises;
	}
	public boolean isVideoProjecteur() {
		return videoProjecteur;
	}
	public void setVideoProjecteur(boolean videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}
	public boolean isTableau() {
		return tableau;
	}
	public void setTableau(boolean tableau) {
		this.tableau = tableau;
	}
	public String getNomSalle() {
		return nomSalle;
	}
	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}
	public int getIdSalle() {
		return idSalle;
	}
	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}
	
	

}
