package m.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import m.common.ConnectionFactory;
import m.model.dao.ManagerDAO;
import m.model.vo.Notice;
import m.model.vo.ReReview;
import m.model.vo.Restaurant;
import m.model.vo.Manager;
import m.model.vo.ManagerReservation;

public class ManagerService {
	private ConnectionFactory factory;
	
	public ManagerService() throws FileNotFoundException, ClassNotFoundException, IOException {
		factory=ConnectionFactory.getConnection();
	}
	
	// 매니저 로그인
	public Manager loginManager(String mngId, String mngPwd) throws SQLException, FileNotFoundException, IOException {

		Connection conn = null;
		Manager manager = null;

		conn = factory.createConnection();
		manager = new ManagerDAO().loginManager(mngId, mngPwd, conn);

		ConnectionFactory.close(conn);


		return manager;
	}
	
	// 레스토랑 가입
	public int insertRestaurant(Restaurant rest) throws SQLException, FileNotFoundException, IOException {
		Connection conn = null;
		int result = 0;
			
		conn = factory.createConnection();
		result = new ManagerDAO().insertRestaurant(rest,conn);
			
		if ( result > 0 ) 
			factory.commit(conn);
		else 
			factory.rollback(conn);

		factory.close(conn);
		
		return result;
	}
	
	// 매니저 가입
	public int insertManager(Manager mng, String restName) throws SQLException, FileNotFoundException, IOException {
		Connection conn = null;
		int result = 0;
			
		conn = factory.createConnection();
		result = new ManagerDAO().insertManager(mng,conn,restName);
			
		if ( result > 0 ) 
			factory.commit(conn);
		else 
			factory.rollback(conn);

		factory.close(conn);
		
		return result;
	}	
	
	// 예약 정보 조회
	public ArrayList<ManagerReservation> selectReservation(int restNo) throws SQLException, FileNotFoundException, IOException{
		Connection conn=factory.createConnection();
		ArrayList<ManagerReservation> list=new ManagerDAO().selectReservation(restNo, conn);
		ConnectionFactory.close(conn);
		return list;
	}
	
	// 공지 작성
	public int insertNotice(Notice notice) throws SQLException, FileNotFoundException, IOException {
		Connection conn=factory.createConnection();
		int result=new ManagerDAO().insertNotice(notice, conn);
		if(result>0)
			factory.commit(conn);
		else
			factory.rollback(conn);
		ConnectionFactory.close(conn);
		return result;
	}
	
	// 공지 정보 조회
	public ArrayList<Notice> selectNotice(int restNo) throws SQLException, FileNotFoundException, IOException{
		Connection conn=factory.createConnection();
		ArrayList<Notice> list=new ManagerDAO().selectNotice(restNo, conn);
		ConnectionFactory.close(conn);
		return list;
	}
	
	// 리뷰 전체 조회
	public ArrayList<ReReview> selectList(int restuarantNo) throws SQLException, FileNotFoundException, IOException {
		Connection conn = factory.createConnection();
		ArrayList<ReReview> list = new ManagerDAO().selectList(restuarantNo,conn);
		return list;
	}
	
	// 레스토랑 가져오기
	public Restaurant selectRestaurant(int restNo) throws SQLException, FileNotFoundException, IOException{
		Connection conn=factory.createConnection();
		Restaurant rest=new ManagerDAO().selectRestaurant(restNo, conn);
		ConnectionFactory.close(conn);
		return rest;
	}
}
