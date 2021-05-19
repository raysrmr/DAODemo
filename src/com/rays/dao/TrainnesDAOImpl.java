package com.rays.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rays.model.Trainees;

public class TrainnesDAOImpl implements TraineesDAO {

	Connection con;

	public TrainnesDAOImpl(Connection con) {
		this.con = con;
	}

	@Override
	public int insertTrainees(Trainees T) {
		int result =0;
		try {
			PreparedStatement ps = con.prepareStatement("insert into trainees values(?,?,?,?)"); // Create a Query or
																									// Statement
			ps.setInt(1, T.getAid());
			ps.setString(2, T.gettName());
			ps.setInt(3, T.gettAge());
			ps.setString(4, T.gettCity());
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error in Insert Trainees : " + e);			
		}
		return result;
	}

	@Override
	public List<Trainees> getAllTrainees() {
		List<Trainees> tList = new ArrayList<>();
		try {		
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from trainees");
			while(rs.next()) {
				tList.add(new Trainees(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			}
			System.out.println("Connected....");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in Get all Trainees :"+e);
		}
		return tList;
	}

	@Override
	public Trainees getTraineesById(int aid) {
		Trainees T = null;
		try {			
			PreparedStatement ps = con.prepareStatement("select * from trainees where aid=?");
			ps.setInt(1, aid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				T= new Trainees(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			}		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error :"+e);
		}
		return T;
	}

	@Override
	public int updateTrainees(Trainees T) {
		int result =0;
		try {
			PreparedStatement ps = con.prepareStatement("update trainees set t_name=?,t_age=?,t_city=? where aid=?"); // Create a Query or																									// Statement
			ps.setInt(4, T.getAid());
			ps.setString(1, T.gettName());
			ps.setInt(2, T.gettAge());
			ps.setString(3, T.gettCity());
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error in Update Trainees : " + e);			
		}
		return result;
	}

	@Override
	public int deleteTrainees(int aid) {
		int result =0;
		try {
			PreparedStatement ps = con.prepareStatement("delete from trainees where aid=?"); // Create a Query or																									// Statement
			ps.setInt(1,aid);			
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error in Delete Trainees : " + e);			
		}
		return result;
	}	

}
