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
import m.model.vo.Notice;
import m.model.vo.ReReview;
import m.model.vo.Restaurant;
import m.model.vo.Manager;
import m.model.vo.ManagerReservation;

public class ManagerDAO {
	private Properties prop;
	
	public ManagerDAO() throws FileNotFoundException, IOException {
		prop=new Properties();
		prop.load(new FileReader("resources/query.properties"));
	}
	
	// 매니저 로그인
	public Manager loginManager(String mngId, String mngPwd, Connection conn) throws SQLException {
		
		
		Manager manager = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginManager");

		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, mngId);
		pstmt.setString(2, mngPwd);
		rset = pstmt.executeQuery();
			
		if (rset.next()) {
			manager = new Manager();
			manager.setMngNo(rset.getInt("MNG_NO"));
			manager.setMngId(rset.getString("MNG_ID"));
			manager.setMngPwd(rset.getString("MNG_PWD"));
			manager.setRestNo(rset.getInt("REST_NO"));
		}

		ConnectionFactory.close(rset);
		ConnectionFactory.close(pstmt);
		
		return manager;

	}
	
	// 레스토랑 가입
	public int insertRestaurant (Restaurant rest, Connection conn) throws SQLException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertRestaurant");
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, rest.getRestName());
		pstmt.setString(2, rest.getAddress());
		pstmt.setString(3, rest.getRestPhone());
		pstmt.setString(4, rest.getFoodType());
		pstmt.setInt(5, rest.getRestCapacity());
		result = pstmt.executeUpdate();

		ConnectionFactory.close(pstmt);

		
		return result;
		
	}
	
	// 매니저 회원가입
	public int insertManager (Manager manager, Connection conn, String restName) throws SQLException {
		
		// 레스토랑 넘버 받기
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		String query1=prop.getProperty("selectRestaurantByName");
	
		pstmt=conn.prepareStatement(query1);
		pstmt.setString(1, restName);
		rset=pstmt.executeQuery();
		
		ManagerReservation res=null;
		if(rset.next()) {
			res=new ManagerReservation();
			res.setResNo(rset.getInt("REST_NO"));
		}
		
		
		// 매니저 가입
		int result = 0;
		String query2 = prop.getProperty("insertManager");

		pstmt = conn.prepareStatement(query2);
		pstmt.setString(1, manager.getMngId());
		pstmt.setString(2, manager.getMngPwd());
		pstmt.setInt(3,  res.getResNo());
		result = pstmt.executeUpdate();


		ConnectionFactory.close(pstmt);
		
		return result;
		
	}
	
	// 예약 정보 조회
	public ArrayList<ManagerReservation> selectReservation(int restNo, Connection conn) throws SQLException{
		ArrayList<ManagerReservation> list=new ArrayList<ManagerReservation>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		String query=prop.getProperty("selectReservation");
		
		System.out.println("conn : "+conn);
		pstmt=conn.prepareStatement(query);
		pstmt.setInt(1, restNo);
		rset=pstmt.executeQuery();
		
		while(rset.next()) {
			ManagerReservation res=new ManagerReservation();
			res.setResNo(rset.getInt("RES_NO"));
			res.setCusName(rset.getString("CUS_NAME"));
			res.setCusPhone(rset.getString("CUS_PHONE"));
			res.setResDate(rset.getDate("RES_DATE"));
			res.setResTime(rset.getInt("RES_TIME"));
			res.setResCapacity(rset.getInt("RES_CAPACITY"));
			list.add(res);
		}
		
		ConnectionFactory.close(rset);
		ConnectionFactory.close(pstmt);
		conn.close();
		
		return list;
	}
	
	
	// 공지 작성
	public int insertNotice(Notice notice, Connection conn) throws SQLException {
		int result=0;
		PreparedStatement pstmt=null;
		String query=prop.getProperty("insertNotice");
		
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1, notice.getNoticeContent());
		pstmt.setInt(2, notice.getRestNo());
		result=pstmt.executeUpdate();
		
		ConnectionFactory.close(pstmt);
		
		return result;
	}
	
	// 공지 정보 조회
	public ArrayList<Notice> selectNotice(int restNo, Connection conn) throws SQLException{
		ArrayList<Notice> list=new ArrayList<Notice>();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
			
		String query=prop.getProperty("selectNotice");
			
		System.out.println("conn : "+conn);
		pstmt=conn.prepareStatement(query);
		pstmt.setInt(1, restNo);
		rset=pstmt.executeQuery();
			
		while(rset.next()) {
			Notice notice=new Notice();
			notice.setNoticeNo(rset.getInt("NOTICE_NO"));
			notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
			notice.setRestNo(rset.getInt("REST_NO"));
			list.add(notice);
		}
			
		ConnectionFactory.close(rset);
		ConnectionFactory.close(pstmt);
		conn.close();
			
		return list;
	}
	
	// 리뷰 조회
	public ArrayList<ReReview> selectList(int restuarantNo,Connection conn) throws SQLException {
		ArrayList<ReReview> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewAll");


		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, restuarantNo);
		rset = pstmt.executeQuery();
		list = new ArrayList<ReReview>();
		while (rset.next()) {
			ReReview reReview = new ReReview();
			reReview.setCusId(rset.getString("CUS_ID"));// 작성자 아이디
			reReview.setRating(rset.getInt("RATING"));
			reReview.setReContent(rset.getString("REV_CONTENT"));
			list.add(reReview);
		}

		rset.close();
		pstmt.close();
		conn.close();

		return list;
	}
	
	// 레스토랑 가져오기
	public Restaurant selectRestaurant(int restNo, Connection conn) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rset=null;
			
		String query=prop.getProperty("selectRestaurant");
			
		pstmt=conn.prepareStatement(query);
		pstmt.setInt(1, restNo);
		rset=pstmt.executeQuery();
			
		Restaurant rest=null;
		if(rset.next()) {
			rest=new Restaurant();
			rest.setRestNo(rset.getInt("REST_NO"));
			rest.setRestName(rset.getString("REST_NAME"));
			rest.setAddress(rset.getString("ADDRESS"));
			rest.setRestPhone(rset.getString("REST_PHONE"));
			rest.setFoodType(rset.getString("FOOD_TYPE"));
			rest.setRestCapacity(rset.getInt("REST_CAPACITY"));
		}
			
		ConnectionFactory.close(rset);
		ConnectionFactory.close(pstmt);
		conn.close();
			
		return rest;
	}	

}
