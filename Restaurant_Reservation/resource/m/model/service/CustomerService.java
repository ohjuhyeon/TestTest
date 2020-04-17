package m.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import m.common.ConnectionFactory;
import m.model.dao.CustomerDAO;
import m.model.vo.Reservation;
import m.model.vo.Restaurant;

public class CustomerService {
	ConnectionFactory factory;

	public CustomerService() {
		factory = ConnectionFactory.getInstance();
	}

	public ArrayList<Reservation> checkReservation() {
		Connection conn = null;
		ArrayList<Reservation> list = null;
		try {
			conn = factory.createConnection();
			list = new CustomerDAO().checkReservation(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;

	}

	public ArrayList<Restaurant> selectList() {
		Connection conn = null;
		ArrayList<Restaurant> list = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "RESERVATION", "RESERVATION");
			list = new CustomerDAO().selectList(conn);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}// selectList

	public Restaurant selectOne(String rest_Name) {
		Connection conn = null;
		Restaurant restaurant = null;

		try {
			conn = factory.createConnection();
			restaurant = new CustomerDAO().selectOne(rest_Name, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return restaurant;
	}// selectone

	public int deleteRes(int res_No) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new CustomerDAO().deleteRes(res_No, conn);
			if (result > 0) {
				factory.commit(conn);
			} else { 
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(conn);
		}

		return result;
	}

}
