package com.general;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAO<Reservation> reservationDAO = new ReservationDAO();
		
		System.out.println(reservationDAO.find(1));
		

	}

}
