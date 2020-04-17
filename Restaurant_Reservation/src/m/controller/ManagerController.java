package m.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import m.model.service.ManagerService;
import m.model.vo.Notice;
import m.model.vo.ReReview;
import m.model.vo.Restaurant;
import m.model.vo.Manager;
import m.model.vo.ManagerReservation;
import m.view.ReservationMenu;

public class ManagerController {
	
	// 매니저 로그인
	public void loginManager(String mngId, String mngPwd) {
		
		ReservationMenu menu = new ReservationMenu();
		Manager manager;
		
		try {
			manager = new ManagerService().loginManager(mngId, mngPwd);
			
			if ( manager != null ) {
				System.out.println("로그인에 성공하였습니다.");
				menu.managerMenu(manager);
			} else {
				menu.displayError("로그인에 실패하였습니다.");
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// 레스토랑 가입
	public void insertManager (Restaurant rest,Manager mng) {
		
		ReservationMenu menu = new ReservationMenu();
		int resultRestaurant;
		int resultManager;
		
		try {
			resultRestaurant = new ManagerService().insertRestaurant(rest);
			resultManager=new ManagerService().insertManager(mng, rest.getRestName());
			
			if ( resultRestaurant > 0 && resultManager>0) 
				menu.displaySuccess("매니저 회원가입이 완료되었습니다.");
			else 
				menu.displayError("매니저 회원가입에 실패하였습니다.");
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
			// menu.displayError("매니저 회원가입 실패!");
		}
		
	}
	
	
	
	// 매니저 예약 정보 확인
	public void selectReservation(int restNo) {
		ReservationMenu menu=new ReservationMenu();
		
		try {
			ArrayList<ManagerReservation> list=new ManagerService().selectReservation(restNo);
			
			if(!list.isEmpty())
				menu.displayManagerReservation(list);
			else
				menu.displayError("예약 정보 없음");
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			menu.displayError("예약 정보 불러오기 실패!");
		}
	}
	
	// 공지 작성
	public void insertNotice(Notice notice) {
		ReservationMenu menu=new ReservationMenu();
		int result=0;
		
		try {
			result=new ManagerService().insertNotice(notice);
			
			if(result>0)
				menu.displaySuccess("공지 작성 성공!");
			else
				menu.displayError("공지 작성 실패!");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
			// menu.displayError("공지 작성 오류!");
		}
	}
	
	// 공지 정보 확인
	public void selectNotice(int restNo) {
		ReservationMenu menu=new ReservationMenu();
		
		try {
			ArrayList<Notice> list=new ManagerService().selectNotice(restNo);
			
			if(!list.isEmpty())
				menu.displayManagerNotice(list);
			else
				menu.displayError("공지 정보 없음");
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
			// menu.displayError("공지 정보 불러오기 실패!");
		}
	}
	
	// 리뷰 확인
	public void reviewAll(int restuarantNo) {
		ReservationMenu menu = new ReservationMenu();
		ArrayList<ReReview> list;
		try {
			list = new ManagerService().selectList(restuarantNo);
			if (!list.isEmpty()) { // 리스트가 있으면
				menu.displayMemberList(list);
			} else {
				menu.displayError("리뷰가 없습니다.");
			}
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 레스토랑 가져오기
	public Restaurant selectRestaurant(int restNo) {
		ReservationMenu menu=new ReservationMenu();
		Restaurant rest=null;
		
		try {
			rest=new ManagerService().selectRestaurant(restNo);
			
			if(rest!=null)
				return rest;
			else 
				menu.displayError("레스토랑 정보 없음");
				
		} catch (ClassNotFoundException | SQLException | IOException e) {
			menu.displayError("레스토랑 정보 불러오기 실패!");
		}
		
		return rest;
	}
	
}
