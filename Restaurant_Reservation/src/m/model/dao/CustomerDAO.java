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
import m.model.vo.Customer;
import m.model.vo.ReservationTime;
import m.model.vo.Restaurant;
import m.model.vo.Review;

public class CustomerDAO {
	private Properties prop;
	
	public CustomerDAO () throws FileNotFoundException, IOException {
		
		prop = new Properties();
		prop.load(new FileReader("resources/query.properties"));
		
	}
	
	// 고객 회원가입
	public int insertCustomer (Customer customer, Connection conn) throws SQLException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertCustomer");
		Statement stmt = null;

		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, customer.getCusId());
		pstmt.setString(2, customer.getCusPwd());
		pstmt.setString(3, customer.getCusName());
		pstmt.setString(4, customer.getCusPhone());
		result = pstmt.executeUpdate();
		ConnectionFactory.close(pstmt);

		
		return result;
		
	}
	
	// 고객 로그인
	public Customer loginCustomer(String cusId, String cusPwd, Connection conn) throws SQLException {

		Customer customer = null;
		PreparedStatement pstmt = null;
		java.sql.ResultSet rset = null;
		String query = prop.getProperty("loginCustomer");


		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, cusId);
		pstmt.setString(2, cusPwd);
		rset = pstmt.executeQuery();

		if (rset.next()) {
			customer = new Customer();
			customer.setCusNo(rset.getInt("CUS_NO"));
			customer.setCusId(rset.getString("CUS_ID"));
			customer.setCusName(rset.getString("CUS_NAME"));
			customer.setCusPwd(rset.getString("CUS_PWD"));
			customer.setCusPhone(rset.getString("CUS_PHONE"));
		}

		ConnectionFactory.close(rset);
		ConnectionFactory.close(pstmt);

		return customer;
	}
	
	
	// reservation Time 가져오기 
	public ReservationTime selectTime(Connection conn, ReservationTime resTime) throws SQLException {
		
		ReservationTime rTime = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("reservationTime");
		

		pstmt = conn.prepareStatement(query);
		pstmt.setDate(1, resTime.getResDate());
		pstmt.setInt(2, resTime.getRestNo());
		rset = pstmt.executeQuery();
			
		if (rset.next()) {
			rTime = new ReservationTime();
				
			rTime.setResDate(rset.getDate("RES_DATE"));
			rTime.setRestNo(rset.getInt("REST_NO"));
			rTime.setTime10(rset.getInt("TIME_10"));
			rTime.setTime12(rset.getInt("TIME_12"));
			rTime.setTime14(rset.getInt("TIME_14"));
			rTime.setTime16(rset.getInt("TIME_16"));
			rTime.setTime18(rset.getInt("TIME_18"));
			rTime.setTime20(rset.getInt("TIME_20"));
		}

		ConnectionFactory.close(rset);

		return rTime;
	}
	
	// Time 업데이트
	public int updateReservationTime(Connection conn, ReservationTime rTime, int time, int count) throws SQLException {
		
		Statement stmt = null;
		int result = 0;
		
		String query = "UPDATE RESERVATION_TIME SET TIME_" + time + " = TIME_" + time + "+" + count + 
						" WHERE (TO_CHAR(RES_DATE, 'YYYYMMDD') = TO_CHAR(TO_DATE(" + rTime.getResDate().toString().replace("-", "") + "), 'YYYYMMDD')) AND "
								+ "REST_NO = " + rTime.getRestNo();
		

		stmt = conn.createStatement();
		result = stmt.executeUpdate(query);

		ConnectionFactory.close(stmt);

		return result;
	}
	
	// 예약 추가
	public int insertReservation(Connection conn, ReservationTime rTime, int cusNo, int time, int count) throws SQLException {
		
		Statement stmt = null;
		int result = 0;
		
		String query = "INSERT INTO RESERVATION VALUES(RES_NO_SEQ.NEXTVAL," + time + 
				", " + count + ", " + rTime.getRestNo() + ", " + cusNo + ", " + rTime.getResDate() + ")";
		

		stmt = conn.createStatement();
		result = stmt.executeUpdate(query);

		ConnectionFactory.close(stmt);
		
		return result;
	}
	
	// 리뷰 작성
	public int insertReview(Connection conn, Review review) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Restaurant> selectList(Connection conn) throws SQLException {
		ArrayList<Restaurant> list = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM RESTAURANT";

		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		list = new ArrayList<Restaurant>();
			
		while(rset.next()) {
			Restaurant restaurant = new Restaurant();
			restaurant.setRestNo(rset.getInt("REST_NO"));
			restaurant.setRestName(rset.getString("REST_NAME"));
			restaurant.setAddress(rset.getString("ADDRESS"));
			restaurant.setRestPhone(rset.getString("REST_PHONE"));
			restaurant.setFoodType(rset.getString("FOOD_TYPE"));
			restaurant.setRestCapacity(rset.getInt("REST_CAPACITY"));
				
			list.add(restaurant);
				
		}//while

		stmt.close();
		rset.close();

		return list;
	}//selectList
	

}
