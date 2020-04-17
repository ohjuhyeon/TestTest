package m.view;

import java.util.ArrayList;
import java.util.Scanner;

import m.controller.CustomerController;
import m.model.vo.Reservation;
import m.model.vo.Restaurant;

public class ReservationMenu {

	CustomerController cController;
	private Scanner sc;

	public ReservationMenu() {
		cController = new CustomerController();
		sc = new Scanner(System.in);
	}

	public void mainMenu() {

		//cController.checkReservation();
	

	int choice = 9;
	do{
		
		System.out.println("======고객======");
		System.out.println("1. 식당 정보 보기");
		System.out.println("2. 식당 선택");
		System.out.println("3. 예약 하기");
		System.out.println("4. 예약 보기");
		System.out.println("5. 예약취소");
		System.out.println("6. 리뷰작성");
		System.out.println("0. 프로그램 종료");
		System.out.println("원하는 서비스 코드를 눌러주세요: ");
		choice = sc.nextInt();

		switch (choice) {
		case 1:
			cController.selectAll();
			break;
		case 2:
			cController.selectOne(this.inputRest_Name());
			break;
		case 3:
			break;
		case 4: 
			cController.checkReservation();
			break;
		case 5:
			cController.deleteRes(inputRes_No());
			break;
		case 6:
			break;
		case 0:
			break;
		default:
			System.out.println("번호를 잘못 선택하셨습니다.");
			System.out.println("처음으로 돌아갑니다.");
			break;
		}
	}while(true); 
	
	}
	

public void displayRestaurantList(ArrayList<Restaurant> list) {
	System.out.println("====식당 목록====");
	System.out.println("식당번호 식당이름 주소 식당전화번호 음식종류 수용인원");
	for(Restaurant restaurant : list) {
		System.out.println(restaurant.toString());
	}
}

	public String inputRest_Name() {
		System.out.println("조회할 식당의 이름을 입력하세요: ");
		return sc.next();
	}

	public void displayRestaurant(Restaurant restaurant) {
		System.out.println(restaurant.toString());
	}

	public void choiceRestaurant(Restaurant restaurant) {
		System.out.println("선택할 식당의 번호를 눌러주세요: ");
		displayRestaurant(restaurant);
		int restaurantNo = sc.nextInt();// 번호입력 -> 식당이름 가져오기 -> 가져온 이름으로 식당 검색

		int i = 0;
		do {
			System.out.println("1. 공지");
			System.out.println("2. 리뷰");
			System.out.println("3. 예약");
			System.out.println("0. 종료");
			System.out.println("원하는 메뉴를 눌러주세요");
			i = sc.nextInt();

			switch (i) {
			case 1:
				break;
			case 2:
				cController.selectReviews(restaurantNo);
				break;
			case 3:
				break;
			case 0:
				System.out.println("선택하시겠습니까? (y/n): ");
				if (sc.next().charAt(0) == 'y')
					;
				break;
			}
		} while (true);
	}

	public void displayError(String message) {
		System.out.println("요청 실패");
	}

	public void displayReservation(ArrayList<Reservation> list) {
		System.out.println(list.toString());
		System.out.println();
	}
	public int inputRes_No() {
		System.out.print("예약 번호를 입력하세요 : ");
		return sc.nextInt();
	}
}
