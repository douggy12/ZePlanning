package com.graphique;

import java.util.ArrayList;

import com.general.DAO;
import com.general.Salle;
import com.general.SalleDAO;

public class ListeClasse {

	public ArrayList<Salle> listesalle = new ArrayList<Salle>();
	
	public Salle s;
	
	DAO<Salle> SalleDAO =  new SalleDAO();
	
	public ListeClasse()
	{
		
	}
	
	public void ajouterSalle(Salle s)
	{
		this.s = s;
		listesalle.add(s);
		
	}
	
	public void supprimerSalle(Salle s)
	{
		this.s = s;
		listesalle.remove(s);
		
	}
	
//	public void creerListe()
//	{
//		for (int i = 0; i < SalleDAO.c)
//		
//	}
	
}
