package m.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import m.model.service.CustomerService;
import m.model.vo.Customer;
import m.model.vo.ReservationTime;
import m.model.vo.Restaurant;
import m.view.ReservationMenu;

public class CustomerController {
	public void insertCustomer (Customer customer) {
		
		ReservationMenu menu = new ReservationMenu();
		int result;
		
		try {
			result = new CustomerService().insertCustomer(customer);
			
			if (result > 0) 
				menu.displaySuccess ("고객 회원가입이 완료되었습니다.");
			else 
				menu.displayError("고객 회원가입에 실패하였습니다.");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			// menu.displayError("고객 회원가입 실패!");
		}
		
	}
	
	// 고객 로그인
	public void loginCustomer(String cusId, String cusPwd) {

		ReservationMenu menu = new ReservationMenu();
		Customer customer;

		try {
			customer = new CustomerService().loginCustomer(cusId, cusPwd);
			
			if (customer != null) {
				System.out.println("로그인에 성공하였습니다.");
				menu.customerMenu(customer);
			} else {
				menu.displayError("로그인에 실패하였습니다.");
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Time 가져오기
	public ReservationTime selectTime(ReservationTime resTime) {
		
		ReservationMenu menu = new ReservationMenu();
		ReservationTime rTime=null;
		try {
			rTime = new CustomerService().selectTime(resTime);
			
			if (rTime != null) {
				menu.displayReservationTime(rTime);
				menu.updateReservationTime(rTime);
				
			} else {
				menu.displayError("조회 실패!");
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return rTime;
	}
	
	// Time 업데이트 
	public void updateReservationTime(ReservationTime rTime, int time, int count) {
		
		ReservationMenu menu = new ReservationMenu();
		
		int result;
		try {
			result = new CustomerService().updateReservationTime(rTime, time, count);
			
			if (result > 0) {
				menu.insertReservation(rTime, time, count);
				menu.displaySuccess("예약완료!");
				
			} else {
				menu.displayError("예약실패!");
				
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}	
	
	// 예약 추가
	public void insertReservation(ReservationTime rTime, int cusNo, int time, int count) {
		
		try {
			new CustomerService().insertReservation(rTime, cusNo, time, count);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 식당 전체 출력
	public ArrayList<Restaurant> selectAll() {
		ReservationMenu menu = new ReservationMenu();
		ArrayList<Restaurant> list=null;
		try {
			list = new CustomerService().selectList();
			
			if(list.isEmpty())
				menu.displayError("식당 전체 조회 실패");

		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}//selectall	
	


	
}
















