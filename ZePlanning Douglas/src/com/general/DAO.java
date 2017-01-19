package com.general;


import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {

	public Connection connect = ConnectionBdd.getInstance();
	
	/**
	 * Permet de r�cup�rer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T find(int id);
	
	/**
	 * Permet de cr�er une entr�e dans la base de donn�es
	 * par rapport � un objet
	 * @param obj
	 */
	public abstract T create(T obj);
	
	/**
	 * Permet de mettre � jour les donn�es d'une entr�e dans la base 
	 * @param obj
	 */
	public abstract T update(T obj);
	
	/**
	 * Permet la suppression d'une entr�e de la base
	 * @param obj
	 */
	public abstract void delete(T obj);
	
	public abstract void deleteById(int id);
	
	public abstract int count();
	
	public abstract ArrayList<T> findAll();
}