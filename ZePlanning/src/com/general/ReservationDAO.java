package com.general;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;

public class ReservationDAO extends DAO<Reservation>{
	
	public Reservation findByDate(String nomSalle, String date) {
		
		Reservation resa = new Reservation();
		try {
            PreparedStatement prepare = this .connect
                                    .prepareStatement("SELECT * FROM reservation, salle_de_cours, promo, formateur, ecole, join_table "
                                            + "WHERE reservation.id_reservation = join_table.id_reservation "
                                            + "AND join_table.id_salle = salle_de_cours.id_salle "
                                            + "AND join_table.id_promo = promo.id_promo "
                                            + "AND join_table.id_formateur = formateur.id_formateur "
                                            + "AND join_table.id_ecole = ecole.id_ecole "
                                            + "AND reservation.date = ? "
                                            + "AND salle_de_cours.nom_salle = ?", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            prepare.setString(1, date);
            prepare.setString(2, nomSalle);
            ResultSet result = prepare.executeQuery();
            
           
            
            if(result.first()){
            		resa = new Reservation(
            				result.getInt("id_reservation"),
            				result.getString("date"), 
            				new SalleDAO().find(result.getInt("id_salle")), 
            				new PromoDAO().find(result.getInt("id_promo")), 
            				new FormateurDAO().find(result.getInt("id_formateur")), 
            				result.getString("matiere"), 
            				new EcoleDAO().find(result.getInt("id_ecole"))
            				);
            		
            		return resa;
            		}
            else return null;
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		return null;
	}

	@Override
	public Reservation find(int id) {
		Reservation resa = new Reservation();
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM reservation, salle_de_cours, promo, formateur, ecole, join_table "
                                                + "WHERE reservation.id_reservation = join_table.id_reservation "
                                                + "AND join_table.id_salle = salle_de_cours.id_salle "
                                                + "AND join_table.id_promo = promo.id_promo "
                                                + "AND join_table.id_formateur = formateur.id_formateur "
                                                + "AND join_table.id_ecole = ecole.id_ecole "
                                                + "AND reservation.id_reservation = " + id
                                             );
            
            if(result.first())
            		resa = new Reservation(
            				id,
            				result.getString("date"), 
            				new SalleDAO().find(result.getInt("id_salle")), 
            				new PromoDAO().find(result.getInt("id_promo")), 
            				new FormateurDAO().find(result.getInt("id_formateur")), 
            				result.getString("matiere"), 
            				new EcoleDAO().find(result.getInt("id_ecole"))
            				);
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return resa;
	}

	@Override
	public Reservation create(Reservation obj) {
		try {
			 
			
			ResultSet result = this	.connect
                                    .createStatement(
                                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    		ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    		"SELECT NEXTVAL('reservation_id_reservation_seq') as id"
                                    );
			if(result.first()){
				int id = result.getInt("id");
    			PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    		"INSERT INTO reservation (id_reservation, date, matiere) VALUES(?, ?, ?); "
                                                    		+ "INSERT INTO join_table (id_reservation, id_salle, id_formateur, id_promo, id_ecole) VALUES(?, ?, ?, ?, ?)"
                                                    );
    			System.out.println(id);
				prepare.setInt(1, id);
				prepare.setObject(2, obj.getDateResa());
				prepare.setString(3, obj.getMatiereResa().toString());
				prepare.setInt(4, id);
				prepare.setInt(5, obj.getSalleResa().getIdSalle());
				prepare.setInt(6, obj.getFormateurResa().getIdFormateur());
				prepare.setInt(7, obj.getPromoResa().getIdPromo());
				prepare.setInt(8, obj.getEcoleResa().getIdEcole());
				
				prepare.executeUpdate();
				obj = this.find(id);	
				
			}
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}
	

	@Override
	public Reservation update(Reservation obj) {
		try{	
			
			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE reservation SET "
                	+ "reservation_date = " + obj.getDateResa() + ", "
                	+ "matiere = '" + obj.getMatiereResa() + "',"
                	+ "WHERE id_reservation = " + obj.getIdResa()+"; "
                	+ "UPDATE join_table SET "
                	+ "id_salle = " + obj.getSalleResa().getIdSalle()+", "
                	+ "id_formateur = " + obj.getFormateurResa().getIdFormateur()+", "
                	+ "id_promo = " + obj.getPromoResa().getIdPromo()+", "
                	+ "id_ecole = " + obj.getEcoleResa().getIdEcole()+", "
                 );

			obj = this.find(obj.getIdResa());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	

	@Override
	public void delete(Reservation obj) {
		try {
			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM join_table WHERE id_reservation = " + obj.getIdResa()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}

	@Override
	public void deleteById(int id) {
try {
			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM reservation WHERE id_reservation = " + id+" ; "
                			+ "DELETE FROM join_table WHERE id_reservation = " + id
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}

	@Override
	public int count() {
		int count = 0;
		try {
			ResultSet result =
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE)
			.executeQuery("SELECT COUNT (*) FROM reservation");
			if(result.first())
			{
				count = result.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public ArrayList<Reservation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
