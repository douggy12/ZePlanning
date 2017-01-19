package com.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EtudiantDAO extends DAO<Etudiant> {

	@Override
	public Etudiant find(int id) {
		Etudiant etudiant = new Etudiant();

		try {
			ResultSet result = this .connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
							ResultSet.CONCUR_READ_ONLY
							).executeQuery(
									"SELECT * FROM etudiant WHERE id_etudiant = " + id
									);
			if(result.first())
				etudiant = new Etudiant(id, result.getString("nom_etudiant"), result.getString("prenom_etudiant"), result.getInt("id_promo"));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return etudiant;
	}

	@Override
	public Etudiant create(Etudiant obj) {
		try{
			//recupere la dernière valeur+1 pour id
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT NEXTVAL('etudiant_id_etudiant_seq') as id");
			
			if(result.first()){
				int id = result.getInt("id");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO etudiant (id_etudiant, nom_etudiant, prenom_etudiant, id_promo ) VALUES(?,?,?,?)");
				prepare.setInt(1, id);
				prepare.setString(2, obj.getNom());
				prepare.setString(3, obj.getPrenom());
				prepare.setInt(4, obj.getIdpromo());
				
				prepare.executeUpdate();
				obj = this.find(id);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public Etudiant update(Etudiant obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeQuery("UPDATE etudiant SET etudiant_nom = '" + obj.getNom()+"', etudiant_prenom ='"+ obj.getPrenom()+"', id_promo ='"+ obj.getIdpromo()+" WHERE etudiant_id = "+obj.getId());
			
			obj = this.find(obj.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public void delete(Etudiant obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("DELETE FROM etudiant WHERE id_etudiant = "+obj.getId());
			
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(int id) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("DELETE FROM etudiant WHERE id_etudiant = "+id);
			
		}catch(SQLException e){
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
			.executeQuery("SELECT COUNT (*) FROM etudiant");
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
	public ArrayList<Etudiant> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
