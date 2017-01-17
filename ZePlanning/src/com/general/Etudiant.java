package com.general;

public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private int idpromo;
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param idpromo
	 */
	public Etudiant(int id, String nom, String prenom, int idpromo) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.idpromo = idpromo;
	}
	public Etudiant(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getIdpromo() {
		return idpromo;
	}
	public void setIdpromo(int idpromo) {
		this.idpromo = idpromo;
	}
	
	

}
