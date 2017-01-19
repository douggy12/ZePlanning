package com.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalleDAO extends DAO<Salle>{

	@Override
	public Salle find(int id) {
		Salle salle = new Salle();
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM salle_de_cours WHERE id_salle = " + id
                                             );
            if(result.first())
            		salle = new Salle(
                                        id, 
                                        result.getInt("num_salle"),
                                        result.getInt("nb_pc"),
                                        result.getInt("nb_bureaux"),
                                        result.getInt("nb_chaises"),
                                        result.getBoolean("videoprojecteur"),
                                        result.getBoolean("tableau"),
                                        result.getString("nom_salle")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return salle;
	}

	@Override
	public Salle create(Salle obj) {
		try {
			 
			//Vu que nous sommes sous postgres, nous allons chercher manuellement
			//la prochaine valeur de la séquence correspondant à l'id de notre table
			ResultSet result = this	.connect
                                    .createStatement(
                                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    		ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    		"SELECT NEXTVAL('salle_id_salle_seq') as id"
                                    );
			if(result.first()){
				int id = result.getInt("id");
    			PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    	"INSERT INTO salle_de_cours (id_salle, nb_pc, nb_bureaux, nb_chaises, videoprojecteur, tableau, num_salle, nom_salle) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
                                                    );
				prepare.setLong(1, id);
				prepare.setInt(2, obj.getNbPC());
				prepare.setInt(3, obj.getNbBureaux());
				prepare.setInt(4, obj.getNbChaises());
				prepare.setBoolean(5, obj.isVideoProjecteur());
				prepare.setBoolean(6, obj.isTableau());
				prepare.setInt(7, obj.getNumSalle());
				prepare.setString(8, obj.getNomSalle());
				
				prepare.executeUpdate();
				obj = this.find(id);	
				
			}
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}

	@Override
	public Salle update(Salle obj) {
		try {
			
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE salle_de_cours SET "
                	+ "num_salle = '" + obj.getNumSalle() + "', "
                	+ "nb_pc = '" + obj.getNbPC() + "', "
                	+ "nb_bureaux = '" + obj.getNbBureaux() + "', "
                	+ "nb_chaises = '" + obj.getNbChaises() + "', "
                	+ "videoprojecteur = '" + obj.isVideoProjecteur() + "', "
                	+ "tableau = '" + obj.isTableau() + "', "
                	+ "nom_salle = '" + obj.getNomSalle()+"' "
                	+ "WHERE lan_id = " + obj.getIdSalle()
                 );
		
		obj = this.find(obj.getIdSalle());
    } catch (SQLException e) {
            e.printStackTrace();
    }
    
    return obj;
	}

	@Override
	public void delete(Salle obj) {
		try {
			
            this    .connect
                	.createStatement(
                         ResultSet.TYPE_SCROLL_INSENSITIVE, 
                         ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                         "DELETE FROM salle_de_cours WHERE id_salle = " + obj.getIdSalle()
                    );
		
    } catch (SQLException e) {
            e.printStackTrace();
    }
		
	}
//test

	@Override
	public void deleteById(int id) {
try {
			
            this    .connect
                	.createStatement(
                         ResultSet.TYPE_SCROLL_INSENSITIVE, 
                         ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                         "DELETE FROM salle_de_cours WHERE id_salle = " + id
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
			.executeQuery("SELECT COUNT (*) FROM salle_de_cours");
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
	public ArrayList<Salle> findAll() {
		ArrayList<Salle> listeSalle = new ArrayList<>();
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM salle_de_cours"
                                             );
            
            		while(result.next()){
            			listeSalle.add(new Salle(
                                result.getInt("id_salle"),
                                result.getInt("num_salle"),
                                result.getInt("nb_pc"),
                                result.getInt("nb_bureaux"),
                                result.getInt("nb_chaises"),
                                result.getBoolean("videoprojecteur"),
                                result.getBoolean("tableau"),
                                result.getString("nom_salle")
                            ));
            		}
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return listeSalle;
		
	}
}
