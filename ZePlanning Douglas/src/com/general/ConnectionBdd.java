package com.general;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBdd{

	/**
	 * URL de connection
	 */
	private static String url = "jdbc:postgresql://localhost:5433/ZePlanning";
	/**
	 * Nom du user
	 */
	private static String user = "postgres";
	/**
	 * Mot de passe du user
	 */
	private static String passwd = "linux";
	/**
	 * Objet Connection
	 */
	private static Connection connect;
	
	/**
	 * M�thode qui va nous retourner notre instance
	 * et la cr�er si elle n'existe pas...
	 * @return
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}	
}