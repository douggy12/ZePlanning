package com.general;

public class LoginUser {
	private int idUser;
	private int userLvl;
	private String login;
	private String mdp;
	/**
	 * @param idUser
	 * @param login
	 * @param mdp
	 */
	public LoginUser(int idUser, int userlvl, String login, String mdp) {
		
		this.idUser = idUser;
		this.userLvl = userlvl;
		this.login = login;
		this.mdp = mdp;
	}
	public LoginUser(){}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getUserLvl() {
		return userLvl;
	}
	public void setUserLvl(int userLvl) {
		this.userLvl = userLvl;
	}
	
	

}
