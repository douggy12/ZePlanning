package com.general;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginUserDAO extends DAO<LoginUser> {
	
	public LoginUser find(String userid) {
		LoginUser loginUser = new LoginUser();

		try{
			ResultSet result  = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM login_user WHERE user_name =  '"+ userid+"'");

			if(result.first()){
				loginUser = new LoginUser(result.getInt("id_user"), result.getInt("user_level"), result.getString("user_name"), result.getString("user_pwd"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return loginUser;
	}

	@Override
	public LoginUser find(int id) {
		LoginUser loginUser = new LoginUser();

		try{
			ResultSet result  = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM login_user WHERE id_user = " + id);

			if(result.first()){
				loginUser = new LoginUser(id, result.getInt("user_level"), result.getString("user_name"), result.getString("user_pwd"));
				return loginUser;
			}
			else return null;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LoginUser create(LoginUser obj) {
		try{
			//recupere la dernière valeur+1 pour id
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT NEXTVAL('user_id_user_seq') as id");
			
			if(result.first()){
				int id = result.getInt("id");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO user (id_user, user_level, user_name, user_pwd ) VALUES(?,?,?,?)");
				prepare.setInt(1, id);
				prepare.setInt(2, obj.getUserLvl());
				prepare.setString(3, obj.getLogin());
				prepare.setString(4, obj.getMdp());
				
				prepare.executeUpdate();
				obj = this.find(id);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public LoginUser update(LoginUser obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeQuery("UPDATE login_user SET user_level = '" + obj.getUserLvl()+"', user_name ='"+ obj.getLogin()+"', user_pwd ='"+ obj.getMdp()+" WHERE user_id = "+obj.getIdUser());
			
			obj = this.find(obj.getIdUser());
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public void delete(LoginUser obj) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("DELETE FROM login_user WHERE id_user = "+obj.getIdUser());
			
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(int id) {
		try{
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("DELETE FROM login_user WHERE id_user = "+id);
			
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
			.executeQuery("SELECT COUNT (*) FROM login_user");
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
	public ArrayList<LoginUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
