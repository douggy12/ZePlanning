package com.graphique;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

import com.general.Reservation;
import com.general.ReservationDAO;
import com.general.Salle;
import com.general.SalleDAO;

public class ModelePlanning extends Observable {
	private ZoneId zoneid = ZoneId.of("Europe/Paris");
	private Calendar gc = new GregorianCalendar();
	
	private int weekNum = 0;
	private int year = 0;
	
	ArrayList<String> listeJours = new ArrayList<>();
	ArrayList<String> listeDates = new ArrayList<>();
	
	private ArrayList<Salle> listeSalle;
	
	
	
	SalleDAO salleDAO = new SalleDAO();
	ReservationDAO resaDAO = new ReservationDAO();

	
	

	/**
	 * Construit sur la base de la semaine actuelle
	 */
	public ModelePlanning(){
		//LocalDate now = LocalDate.now();
		//gc.set(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		//gc.set(2017, 12, 17);
		ZonedDateTime now = ZonedDateTime.now(zoneid);
		weekNum = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
		year = now.get(IsoFields.WEEK_BASED_YEAR);
		
		
		
		this.listeSalle =  salleDAO.findAll();
		
		
		
		
	}
	
	public void setSemaine(int weekNum, int year){
		this.weekNum = weekNum;
		this.year = year;


		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stringFormat = new SimpleDateFormat("EEEE d MMMM");

		gc.setFirstDayOfWeek(1);
		listeJours.clear();
		listeDates.clear();

		for (int i = 2; i <= 6 ; i++){

			gc.setWeekDate(year, weekNum, i);
			
			
			listeJours.add(stringFormat.format(gc.getTime()));
			listeDates.add(dateFormat.format(gc.getTime()));
		}
		setChanged();				//signale qu'il y a eu des changements
		notifyObservers(this); 		//previent les observateurs du changement
	}

	public int getWeekNum() {
		return weekNum;
	}

	public int getYear() {
		return year;
	}
	
	public ArrayList<String> getSemaine(){
		return listeJours;
	}
	
	public ArrayList<String> getDates(){
		return listeDates;
	}
	
	public void refreshVue(){
		
		
		setChanged();				//signale qu'il y a eu des changements
		notifyObservers(this); 		//previent les observateurs du changement
		
		

	}

	public ArrayList<Salle> getListeSalle() {
		return listeSalle;
	}
	
	public void ajouterSalle(Salle salle){
		
		salleDAO.create(salle);
		listeSalle.add(salle);
		refreshVue();
	}
	
	public void supprimerSalle(Salle salle){
		salleDAO.delete(salle);
		listeSalle.remove(salle);
		refreshVue();
	}
	
	
	
	

	public void ajouterResa(Reservation resa){
		resaDAO.create(resa);
		refreshVue();
		
	}
	
	public void supprimerResa(Reservation resa){
		resaDAO.delete(resa);
		refreshVue();
	}
	
	
	 

}
