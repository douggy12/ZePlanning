package com.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO extends DAO<Reservation>{

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
                                                + "AND reservation.idreservation = " + id
                                             );
            if(result.first())
            		resa = new Reservation(
            				id,
            				result.getDate("date"), 
            				new SalleDAO().find(result.getInt("id_salle")), 
            				new PromoDAO().find(result.getInt("id_promo")), 
            				new FormateurDAO().find(result.getInt("id_promo")), 
            				result.getString("reservation.matiere"), 
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
                                    		"SELECT NEXTVAL('langage_lan_id_seq') as id"
                                    );
			if(result.first()){
				int id = result.getInt("id");
    			PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    		"INSERT INTO reservation (id_reservation, date, matiere) VALUES(?, ?, ?); "
                                                    		+ "INSERT INTO join_table (id_reservation, id_salle, id_formateur, id_promo, id_ecole) VALUES(?, ?, ?, ?, ?)"
                                                    );
				prepare.setInt(1, id);
				prepare.setDate(2, obj.getDateResa());
				prepare.setString(3, obj.getMatiereResa().toString());
				prepare.setInt(4, obj.getIdResa());
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
                	"DELETE FROM reservation WHERE id_reservation = " + obj.getIdResa()+" ; "
                			+ "DELETE FROM join_table WHERE id_reservation = " + obj.getIdResa()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}
	

}
