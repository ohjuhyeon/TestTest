package m.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import m.common.ConnectionFactory;
import m.model.vo.Reservation;
import m.model.vo.Restaurant;

public class CustomerDAO {
	
	Properties prop;
	public CustomerDAO() {
		prop = new Properties();
		
		try {
			prop.load(new FileReader("resource/query.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Reservation> checkReservation(Connection conn){
		ArrayList list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("checkReservation");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Reservation>();
			while(rset.next()) {
				Reservation reservation = new Reservation();
				reservation.setRes_NO(rset.getInt("RES_NO"));
				reservation.setTime(rset.getInt("RES_TIME"));
				reservation.setRes_Capacity(rset.getInt("REST_NO"));
				reservation.setRest_No(rset.getInt("REST_NO"));
				reservation.setCus_No(rset.getInt("CUS_NO"));
				reservation.setDate(rset.getDate("RES_DATE"));
				list.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(stmt);
			ConnectionFactory.close(rset);
		}
		return list;
	}
	
	public ArrayList<Restaurant> selectList(Connection conn) {
		ArrayList<Restaurant> list = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM RESTAURANT";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Restaurant>();
			
			while(rset.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRest_No(rset.getInt("REST_NO"));
				restaurant.setRest_Name(rset.getString("REST_NAME"));
				restaurant.setAddr(rset.getString("ADDRESS"));
				restaurant.setRest_Phone(rset.getString("REST_PHONE"));
				restaurant.setFood_Type(rset.getString("FOOD_TYPE"));
				restaurant.setRest_Capa(rset.getInt("REST_CAPACITY"));
				
				list.add(restaurant);
				
			}//while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
	
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}//selectList
	
	
	public Restaurant selectOne(String rest_Name, Connection conn) {
		Restaurant restaurant = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM RESTAURANT WHERE REST_NAME = ?";
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, rest_Name);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					restaurant = new Restaurant();
					restaurant.setRest_No(rset.getInt("REST_NO"));
					restaurant.setRest_Name(rset.getString("REST_NAME"));
					restaurant.setAddr(rset.getString("ADDRESS"));
					restaurant.setRest_Phone(rset.getString("REST_PHONE"));
					restaurant.setFood_Type(rset.getString("FOOD_TYPE"));
					restaurant.setRest_Capa(rset.getInt("REST_CAPACITY"));
				}//if
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					rset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} 
		return restaurant;	
	}//selectOne

	public int deleteRes(int res_No, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteReservation");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, res_No);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}
}

	
	

