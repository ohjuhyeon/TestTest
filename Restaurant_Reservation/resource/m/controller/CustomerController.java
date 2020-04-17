package m.controller;

import java.util.ArrayList;

import m.model.service.CustomerService;
import m.model.vo.Reservation;
import m.model.vo.Restaurant;
import m.view.ReservationMenu;

public class CustomerController {
	CustomerService cService;

	public CustomerController() {

		cService = new CustomerService();
	}

	public void checkReservation() {
		
		ReservationMenu rMenu = new ReservationMenu();
		
		ArrayList<Reservation> list;
		
		list = cService.checkReservation();
		
		if (!list.isEmpty()) {
			rMenu.displayReservation(list);
		} else {
			System.out.println("예약하신 기록이 없습니다.");
			
		}
	}

	public void selectAll() {
		ReservationMenu menu = new ReservationMenu();
		ArrayList<Restaurant> list = new CustomerService().selectList();

		if (!list.isEmpty()) {
			menu.displayRestaurantList(list);
		} else {
			menu.displayError("식당 전체 조회 실패");
		}
	}

	public void selectOne(String rest_Name) {
		ReservationMenu menu = new ReservationMenu();
		Restaurant restaurant = new CustomerService().selectOne(rest_Name);

		if (restaurant != null) {
			menu.displayRestaurant(restaurant);

		} else {
			menu.displayError(rest_Name + "과 일치하는 식당이 없습니다.");
		}
	}

	public void selectReviews(int restaurantNo) {
	}

	public void deleteRes(int res_No) {
		ReservationMenu rMenu = new ReservationMenu();
		int result;

		result = cService.deleteRes(res_No);
		if (result > 0) {
			System.out.println("예약이 취소 되었습니다.");
		} else {
			System.out.println("예약 취소 실패.");
		}
	}
}
