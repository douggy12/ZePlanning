package com.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EcoleDAO extends DAO<Ecole> {

	@Override
	public Ecole find(int id) {
		// TODO Auto-generated method stub
		Ecole ecole = new Ecole();
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM ecole WHERE id_ecole = " + id);
			
			if(result.first()){
				ecole = new Ecole(id, result.getString("nom_ecole"), result.getString("ville_ecole"));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return ecole;
	}

	@Override
	public Ecole create(Ecole obj) {
		// TODO Auto-generated method stub
		
		try{
			//recupere la dernière valeur+1 pour id
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT NEXTVAL('ecole_id_ecole_seq') as id");
			
			if(result.first()){
				int id = result.getInt("id");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO ecole (id_ecole, nom_ecole, ville_ecole ) VALUES(?,?,?)");
				prepare.setInt(1, id);
				prepare.setString(2, obj.getNomEcole());
				prepare.setString(3, obj.getVille());
				
				prepare.executeUpdate();
				obj = this.find(id);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public Ecole update(Ecole obj) {
		// TODO Auto-generated method stub
		
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeQuery("UPDATE ecole SET ecole_nom = '" + obj.getNomEcole()+"', ecole_ville ='"+ obj.getVille()+" WHERE ecole_id = "+obj.getIdEcole());
			
			obj = this.find(obj.getIdEcole());
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public void delete(Ecole obj) {
		// TODO Auto-generated method stub
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("DELETE FROM ecole WHERE id_ecole = "+obj.getIdEcole());
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
