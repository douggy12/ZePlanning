package com.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PromoDAO extends DAO<Promo> {

	@Override
	public Promo find(int id) {
		Promo promo = new Promo();

		try {
			ResultSet result = this .connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
							ResultSet.CONCUR_READ_ONLY
							).executeQuery(
									"SELECT * FROM promo WHERE id_promo = " + id
									);
			if(result.first())
				promo = new Promo(id, result.getString("nom_promo"), result.getInt("nb_etudiant"), result.getString("description"));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return promo;
	}

	public Promo find(String nom) {
		Promo promo = new Promo();

		try {
			ResultSet result = this .connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
							ResultSet.CONCUR_READ_ONLY
							).executeQuery(
									"SELECT * FROM promo WHERE nom_promo = " + nom
									);
			if(result.first())
				promo = new Promo(result.getInt("id_promo"), result.getString("nom_promo"), result.getInt("nb_etudiant"), result.getString("description"));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return promo;
	}


	

	@Override
	public Promo create(Promo obj) {
		try {
			 
			//Vu que nous sommes sous postgres, nous allons chercher manuellement
			//la prochaine valeur de la séquence correspondant à l'id de notre table
			ResultSet result = this	.connect
                                    .createStatement(
                                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    		ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    		"SELECT NEXTVAL('promo_id_promo_seq') as id"
                                    );
			if(result.first()){
				int id = result.getInt("id");
    			PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    	"INSERT INTO promo (id_promo, nom_promo, nb_etudiant, description) VALUES(?, ?, ?, ?)"
                                                    );
				prepare.setLong(1, id);
				prepare.setString(2, obj.getNomPromo());
				prepare.setInt(3, obj.getNbEtudiant());
				prepare.setString(4, obj.getDescription());
				
				prepare.executeUpdate();
				obj = this.find(id);	
				
			}
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}
	

	@Override
	public Promo update(Promo obj) {
		try {
			
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE promo SET nom_promo = '" + obj.getNomPromo() + "' "
                 + "nb_etudiant = '" + obj.getNbEtudiant() + "', "
                 + "description = '" + obj.getDescription() + "' "
                 + "WHERE lan_id = " + obj.getIdPromo()
                 );
		
		obj = this.find(obj.getIdPromo());
    } catch (SQLException e) {
            e.printStackTrace();
    }
    
    return obj;

	}

	@Override
	public void delete(Promo obj) {
		try {
			
            this    .connect
                	.createStatement(
                         ResultSet.TYPE_SCROLL_INSENSITIVE, 
                         ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                         "DELETE FROM promo WHERE id_promo = " + obj.getIdPromo()
                    );
		
    } catch (SQLException e) {
            e.printStackTrace();
    }
		
	}
	
	public int count(){
		int count = 0;
		try {
			ResultSet result =
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE)
			.executeQuery("SELECT COUNT (*) FROM promo");
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
	public void deleteById(int id) {
try {
			
            this    .connect
                	.createStatement(
                         ResultSet.TYPE_SCROLL_INSENSITIVE, 
                         ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                         "DELETE FROM promo WHERE id_promo = " + id
                    );
		
    } catch (SQLException e) {
            e.printStackTrace();
    }
		
		
	}

	@Override
	public ArrayList<Promo> findAll() {

		ArrayList<Promo> listePromo = new ArrayList<>();
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM promo"
                                             );
            
            		while(result.next()){
            			listePromo.add(new Promo(
            					result.getInt("id_promo"), 
            					result.getString("nom_promo"), 
            					result.getInt("nb_etudiant"), 
            					result.getString("description")));
                            
            		}
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
			
		   return listePromo;

	}

}
