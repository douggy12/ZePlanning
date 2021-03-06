package com.graphique;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Observable;

public class ModelePrincipale extends Observable {
	private ZoneId zoneid = ZoneId.of("Europe/Paris");
	private Calendar gc = new GregorianCalendar();
	
	private int weekNum = 0;
	private int year = 0;
	
	ArrayList<String> listeJours = new ArrayList<>();
	ArrayList<String> listeDates = new ArrayList<>();

	
	

	/**
	 * Construit sur la base de la semaine actuelle
	 */
	public ModelePrincipale(){
		//LocalDate now = LocalDate.now();
		//gc.set(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		//gc.set(2017, 12, 17);
		ZonedDateTime now = ZonedDateTime.now(zoneid);
		weekNum = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
		year = now.get(IsoFields.WEEK_BASED_YEAR);
		
		System.out.println(weekNum + "-"+year);
		
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
	
	
	 
	/* Ne sert � rien
	private String dateFormat(Object obj){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,M,d");
		return dateFormat.format(obj);
	}
*/

}
