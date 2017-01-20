package com.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormateurDAO extends DAO<Formateur>{

	@Override
	public Formateur find(int id) {
		
		Formateur formateur = new Formateur();
		
		try{
			ResultSet result  = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM formateur WHERE id_formateur = " + id);
			
			if(result.first()){
				formateur = new Formateur(id, result.getString("nom_formateur"), result.getString("prenom_formateur"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return formateur;
	}
	
public Formateur find(String nom) {
		
		Formateur formateur = new Formateur();
		
		try{
			ResultSet result  = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM formateur WHERE nom_formateur = '" + nom+"'");
			
			if(result.first()){
				formateur = new Formateur(result.getInt("id_formateur"), nom, result.getString("prenom_formateur"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return formateur;
	}

	@Override
	public Formateur create(Formateur obj) {
		
		try {
			 
			//Vu que nous sommes sous postgres, nous allons chercher manuellement
			//la prochaine valeur de la séquence correspondant à l'id de notre table
			ResultSet result = this	.connect
                                    .createStatement(
                                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    		ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    		"SELECT NEXTVAL('formateur_id_formateur_seq') as id"
                                    );
			if(result.first()){
				int id = result.getInt("id");
    			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO formateur (id_formateur, nom_formateur, prenom_formateur) VALUES(?, ?,?)");
				prepare.setInt(1, id);
				prepare.setString(2, obj.getNomFormateur());
				prepare.setString(3, obj.getPrenomFormateur());
				
				prepare.executeUpdate();
				obj = this.find(id);	
				
			}
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}
	

	@Override
	public Formateur update(Formateur obj) {
		try {
			
            this .connect	
                 .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE formateur SET "
                	+ "nom_formateur = '" + obj.getNomFormateur() + "', "
                	+ "prenom_formateur = '"+ obj.getPrenomFormateur() + "' "
                	+ "WHERE lan_id = " + obj.getIdFormateur()+";"
                	
                 );
		
		obj = this.find(obj.getIdFormateur());
    } catch (SQLException e) {
            e.printStackTrace();
    }
    
    return obj;
	}

	@Override
	public void delete(Formateur obj) {
		try {
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("DELETE FROM formateur WHERE id_formateur = "+obj.getIdFormateur());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteById(int id) {
		try {
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("DELETE FROM formateur WHERE id_formateur = "+id);
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
			.executeQuery("SELECT COUNT (*) FROM formateur");
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
	public ArrayList<Formateur> findAll() {

		ArrayList<Formateur> listeFormateur = new ArrayList<>();
		try {
            ResultSet result = this .connect
                                    .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM formateur"
                                             );
            
            		while(result.next()){
            			listeFormateur.add(new Formateur(result.getInt("id_formateur"), 
            					result.getString("nom_formateur"), 
            					result.getString("prenom_formateur")));
                            
            		}
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
			
		   return listeFormateur;

	}
	
	

}
