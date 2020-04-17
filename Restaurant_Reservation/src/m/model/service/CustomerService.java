package m.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import m.common.ConnectionFactory;
import m.model.dao.CustomerDAO;
import m.model.vo.Customer;
import m.model.vo.ReservationTime;
import m.model.vo.Restaurant;

public class CustomerService {
	private ConnectionFactory factory;
	
	public CustomerService () throws FileNotFoundException, ClassNotFoundException, IOException {
		
		factory = ConnectionFactory.getConnection();
		
	}
	
	public int insertCustomer (Customer customer) throws SQLException, FileNotFoundException, IOException {
		
		Connection conn = null;
		int result = 0;
		

		conn = factory.createConnection();
		result = new CustomerDAO().insertCustomer(customer, conn);
			
		if ( result > 0 ) 
			factory.commit(conn);
		else 
			factory.rollback(conn);

		factory.close(conn);

		return result;
	}
	
	// 고객 로그인
	public Customer loginCustomer(String cusId, String cusPwd) throws SQLException, FileNotFoundException, IOException {

		Connection conn = null;
		Customer customer = null;

		conn = factory.createConnection();
		customer = new CustomerDAO().loginCustomer(cusId, cusPwd, conn);

		ConnectionFactory.close(conn);


		return customer;
	}
	
	// Time 가져오기
	public ReservationTime selectTime(ReservationTime resTime) throws SQLException, FileNotFoundException, IOException {
		Connection conn = factory.createConnection();
		ReservationTime rTime = new CustomerDAO().selectTime(conn, resTime);
		ConnectionFactory.close(conn);
		
		return  rTime;
	}
	
	// Time 업데이트
	public int updateReservationTime(ReservationTime rTime, int time, int count) throws FileNotFoundException, SQLException, IOException {
		
		Connection conn = factory.createConnection();
		int result = new CustomerDAO().updateReservationTime(conn, rTime, time, count);
		
		if (result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}
		ConnectionFactory.close(conn);
		return result;
	}
	
	
	// 예약 추가
	public int insertReservation(ReservationTime rTime, int cusNo, int time, int count) throws FileNotFoundException, SQLException, IOException {
		
		Connection conn = factory.createConnection();
		int result = new CustomerDAO().insertReservation(conn, rTime, cusNo, time, count);
		
		if (result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}
		ConnectionFactory.close(conn);
		return result;
	}

	// 식당 전체 출력
	public ArrayList<Restaurant> selectList() throws SQLException, FileNotFoundException, IOException {
		Connection conn = null;
		ArrayList<Restaurant> list = null;		

		conn = factory.createConnection();
		list = new CustomerDAO().selectList(conn);

		factory.close(conn);

		return list;
	}//selectList	
	
	
	
}
