package com.general;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAO<Reservation> reservationDAO = new ReservationDAO();
		
		//System.out.println(reservationDAO.find(1));
		
		DAO<Salle> salleDAO = new SalleDAO();
		DAO<Promo> promoDAO = new PromoDAO();
		DAO<Formateur> formateurDAO = new FormateurDAO();
		DAO<Ecole> ecoleDAO = new EcoleDAO();
		
		//LocalDate date1 = LocalDate.of(2017, 1, 19);
		
		
		
		
		//System.out.println(date1);
		//System.out.println(formateurDAO.find(3));
	
		
		//Reservation nResa1 = new Reservation(date1, salleDAO.find(1) , promoDAO.find(2), formateurDAO.find(2), "HoulaHoop", ecoleDAO.find(1));
		 //reservationDAO.create(nResa1);
		
		//Promo nPromo1 = new Promo("Harissa", 8, "une promo qui pique !");
		//promoDAO.create(nPromo1);
		
		

	}

}
