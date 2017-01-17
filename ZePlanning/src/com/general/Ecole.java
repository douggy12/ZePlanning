package com.general;

public class Ecole {
	private int idEcole;
	private String nomEcole, ville;
	/**
	 * @param idEcole
	 * @param nomEcole
	 */
	public Ecole(String nomEcole, String ville) {
		
		
		this.nomEcole = nomEcole;
		this.setVille(ville);
	}
	
	public Ecole(){}
	
	public int getIdEcole() {
		return idEcole;
	}
	public void setIdEcole(int idEcole) {
		this.idEcole = idEcole;
	}
	public String getNomEcole() {
		return nomEcole;
	}
	public void setNomEcole(String nomEcole) {
		this.nomEcole = nomEcole;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Ecole [idEcole=" + idEcole + ", nomEcole=" + nomEcole + ", ville=" + ville + "]";
	}
	
	

}
