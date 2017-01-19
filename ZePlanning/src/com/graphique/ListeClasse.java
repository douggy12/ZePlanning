package com.graphique;

import java.util.ArrayList;
import java.util.Iterator;

import com.general.DAO;
import com.general.Salle;
import com.general.SalleDAO;



public class ListeClasse {

	private ArrayList<Salle> listesalle = new ArrayList<Salle>();
	
	public ArrayList<Salle> getListesalle() {
		return listesalle;
	}

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
	
	public void creerListe()
	{
		for (int i = 1; i < SalleDAO.count(); i++)
		{
			listesalle.add(SalleDAO.find(i));
		}
		
	}
	
	public void afficherListe()
	{
		Iterator<Salle> it = listesalle.iterator();
		while (it.hasNext())
		{
			Salle s = it.next();
			System.out.println(s.getNomSalle().toString());
		}
	}
	
}
