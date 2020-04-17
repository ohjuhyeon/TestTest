package m.view;

import java.util.ArrayList;
import java.util.Scanner;

import m.controller.CustomerController;
import m.controller.ManagerController;
import m.model.vo.Customer;
import m.model.vo.Manager;
import m.model.vo.Notice;
import m.model.vo.ReReview;
import m.model.vo.ReservationTime;
import m.model.vo.Restaurant;
import m.model.vo.ManagerReservation;

public class ReservationMenu {
	private Scanner sc;
	private ManagerController mController;
	private CustomerController cController;
	private Manager manager;
	private Customer customer;
	private Restaurant restaurant;
	
	public ReservationMenu() {
		sc=new Scanner(System.in);
		mController=new ManagerController();
		cController=new CustomerController();
		manager=new Manager();
		customer=new Customer();
		restaurant=new Restaurant();
	}
	
	// 메인 메뉴
	public void mainMenu () {

		int choice = 0;
		
		do {
			
			System.out.println("======= 식당 예약 프로그램 =======");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("0. 종료하기");
			System.out.print("선택 > ");
			choice = sc.nextInt();
			
			switch (choice) {
			
			case 1 : this.insertMember(); break;
			case 2 : this.loginMember(); break;
			case 0 : System.out.println("프로그램을 종료합니다.");return;
			default : System.out.println("잘못된 번호를 선택하였습니다.");
			
			
			}
			
		} while (choice != 0);
		
	}
	
	// 로그인 메뉴
	public void loginMember() {

		int choice = 0;

		do {

			System.out.println("======= 로그인 =======");
			System.out.println("1. 매니저 로그인");
			System.out.println("2. 고객 로그인");
			System.out.println("0. 메인메뉴로 이동");
			System.out.print("선택 : ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				mController.loginManager(this.loginId(), this.loginPwd());
				break;
			case 2:
				cController.loginCustomer(this.loginId(), this.loginPwd());
				break;
			case 0:
				System.out.println("메인메뉴로 이동합니다.");
				return;
			default:
				System.out.println("잘못된 번호를 선택하였습니다.");

			}
		} while (choice != 0);

	}
	
	
	// 회원 가입 메뉴 
	public void insertMember () {
		
		int choice = 0;
		
		do {
			
			System.out.println("======= 회원가입 =======");
			System.out.println("1. 매니저로 가입");
			System.out.println("2. 고객으로 가입");
			System.out.println("0. 메인메뉴로 이동");
			System.out.print("선택 > ");
			choice = sc.nextInt();
			
			switch (choice) {
			
			case 1 : mController.insertManager(this.insertRestaurant(), this.insertManager());  break;
			case 2 : cController.insertCustomer(this.insertCustomer()); break;
			case 0 : System.out.println("메인메뉴로 이동합니다."); return;
			default : System.out.println("잘못된 번호를 선택하였습니다.");
			
			}
			
		} while (choice != 0);
		
		
	}
	
	
	// 매니저 메뉴
	public void managerMenu(Manager mng) {
		int choice = 0;
		
		manager=mng;
		restaurant=mController.selectRestaurant(mng.getRestNo());
		
		do {
			
			System.out.println();
			System.out.println("========== 매니저 메뉴 ==========");
			System.out.println("1. 예약 확인");
			System.out.println("2. 공지 작성");
			System.out.println("3. 식당 정보 확인");
			System.out.println("0. 뒤로가기");
			System.out.print("번호선택 : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1: mController.selectReservation(manager.getRestNo()); break;
			case 2: mController.insertNotice(this.inputNotice()); break;
			case 3: resInfoChk(); break;
			case 0: System.out.print("정말로 끝내시겠습니까? ( y / n ) :");
			if ( sc.next().charAt(0) == 'y') return; 
			else continue;
			default: System.out.println("잘못된 번호를 선택하였습니다.");
			System.out.println("확인하고 다시 선택하십시오.");
			
			}
			
			
			
		} while ( choice != -1);
	}
	
	// 고객 메뉴
	public void customerMenu(Customer cus) {
		customer=cus;
		
		int choice=0;
		do {
			System.out.println("= = = = = 고객 메뉴 = = = = =");
			System.out.println("1. 식당 정보 보기");
			System.out.println("2. 예약내역확인");
			System.out.println("3. 리뷰작성");
			System.out.print("번호 선택 > ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1 : 
					Restaurant rest=this.choiceRestaurant(cController.selectAll()); 
					this.resInfoChkCustomer(rest);
					break;
				case 2 : break;
				case 3 : break;
				case 0 : return;
				default : 
					System.out.println("번호를 잘못 선택하셨습니다");
					break;
			}
		}while(choice!=0);
		

	}
	
	// 식당 정보 확인(고객)
	public void resInfoChkCustomer(Restaurant rest) {
	      int choice = 9;
	      do {
	         System.out.println("============== "+ rest.getRestName() +" ==============");//식당이름 받아와서 출력
	         System.out.println("1. 공지 보기");
	         System.out.println("2. 리뷰 보기");
	         System.out.println("3. 예약 하기");
	         System.out.println("0. 뒤로가기");
	         System.out.println("번호선택 :");
	         choice = sc.nextInt();

	         switch (choice) {
	         case 1: mController.selectNotice(rest.getRestNo()); break;
	         case 2: mController.reviewAll(rest.getRestNo()); break;
	         case 3: cController.selectTime(inputResDate(rest.getRestNo())); break;
	         case 0: return;
	         default:
	            System.out.println("잘못된 번호를 선택하셨습니다 \n확인하고 다시선택하십시오. ");
	         }
	      } while (true);

	      
	  }	
	
	// 식당 정보 확인(매니저)
	public void resInfoChk() {
	      int choice = 9;
	      do {
	         System.out.println("============== "+ restaurant.getRestName() +" ==============");//식당이름 받아와서 출력
	         System.out.println("1. 공지 보기");
	         System.out.println("2. 리뷰 보기");
	         System.out.println("0. 뒤로가기");
	         System.out.println("번호선택 :");
	         choice = sc.nextInt();

	         switch (choice) {
	         case 1: mController.selectNotice(manager.getRestNo()); break;
	         case 2: mController.reviewAll(manager.getRestNo()); break;
	         case 0: return;
	         default:
	            System.out.println("잘못된 번호를 선택하셨습니다 \n확인하고 다시선택하십시오. ");
	         }
	      } while (true);

	      
	  }
	
	// 식당 선택하기
	public Restaurant choiceRestaurant(ArrayList<Restaurant> list) {
		this.displayRestaurantList(list);
		
		System.out.print("원하는 식당의 Num를 입력해주세요 : ");
		int num=sc.nextInt()-1;
		
	
		Restaurant rest=list.get(num);
		
		return rest;
	}
	
	// 아이디 입력
	public String loginId() {

		System.out.print("아이디 : ");
		return sc.next();
	}

	// 비밀번호 입력
	public String loginPwd() {
		System.out.print("비밀번호 : ");
		return sc.next();
	}
	
	// 고객 입력
	public Customer insertCustomer() {
		
		Customer customer = new Customer();
		System.out.print("고객 아이디 입력: ");
		customer.setCusId(sc.next());
		System.out.print("고객 비밀번호 입력: ");
		customer.setCusPwd(sc.next());
		System.out.print("고객 이름 입력: ");
		customer.setCusName(sc.next());
		System.out.print("고객 전화번호 입력: ");
		customer.setCusPhone(sc.next());
		
		return customer;
		
	}
	
	// 매니저 입력
	public Manager insertManager() {
		
		Manager manager = new Manager();
		System.out.println("매니저 아이디 입력: ");
		manager.setMngId(sc.next());
		System.out.println("매니저 비밀번호 입력:");
		manager.setMngPwd(sc.next());
		
		return manager;
		
	}
	

	
	// 레스토랑 입력 
	public Restaurant insertRestaurant() {
		
		Restaurant rest = new Restaurant();
		
		System.out.println("음식점 상호명 입력 : ");
		rest.setRestName(sc.next());
		System.out.println("음식점 주소 입력 : ");
		sc.nextLine();
		rest.setAddress(sc.nextLine());
		System.out.println("음식점 전화번호 입력 : ");
		rest.setRestPhone(sc.next());
		System.out.println("음식점 종류 입력 : ");
		rest.setFoodType(sc.next());
		System.out.println("음식점 수용인원 입력 : ");
		rest.setRestCapacity(sc.nextInt());
		
		return rest;
	}	
	
	// 식당 공지 조회 
	public void displayManagerNotice(ArrayList<Notice> list) {
		System.out.println("현재 조회된 공지 정보는 다음과 같습니다.");
		System.out.println("공지내용");
		
		int count=1; // 순서 출력
		for(Notice notice: list) {
			System.out.print(count+" : ");
			System.out.println(notice.toString());
			count++;
		}
	}
	
	
	// 매니저 예약 정보 조회
	public void displayManagerReservation(ArrayList<ManagerReservation> list) {
		System.out.println("현재 조회된 예약 정보는 다음과 같습니다.");
		System.out.println("고객이름\t전화번호\t날짜\t시간\t테이블수");
		for(ManagerReservation res: list) {
			System.out.println(res.toString());
		}
	}
	
	// 공지 정보 입력
	public Notice inputNotice() {
		Notice notice=new Notice();
		System.out.println("공지 내용");
		sc.nextLine();
		notice.setNoticeContent(sc.nextLine());
		
		notice.setRestNo(manager.getRestNo());
		
		return notice;
	}
	
	// 예약 정보 입력
	public ReservationTime inputResDate(int restNum) {
		ReservationTime rTime = new ReservationTime();
		
		System.out.print("원하시는 날짜를 입력하세요 (숫자8자리 - YYYY-MM-DD) : ");
		rTime.setResDate(java.sql.Date.valueOf(sc.next()));

		rTime.setRestNo(restNum);
		
		return rTime;
	}
	
	// 리뷰 출력
	public void displayMemberList(ArrayList<ReReview> list) {
		System.out.println("작성자 아이디 \t 별점 \t 리뷰");
		for(ReReview reReview : list) {
			System.out.println(reReview.toString());
		}
		// TODO Auto-generated method stub
		
	}
	
	// 식당 전체 출력
	public void displayRestaurantList(ArrayList<Restaurant> list) {
		System.out.println("====식당 목록====");
		System.out.println("식당번호 식당이름 주소 식당전화번호 음식종류 수용인원");
		
		int count=1;
		for(Restaurant restaurant : list) {
			System.out.println(count+" : "+restaurant.toString());
			count++;
		}
	}
	
	// 예약 시간 업데이트
	public void updateReservationTime(ReservationTime rTime) {
		Restaurant rest=mController.selectRestaurant(rTime.getRestNo());
		
		System.out.print("예약할 시간을 입력해주세요 : ");
		int time = sc.nextInt();
		
		int possibleTime;
		switch(time) {
		case 10: possibleTime=rTime.getTime10(); break;
		case 12: possibleTime=rTime.getTime10(); break;
		case 14: possibleTime=rTime.getTime10(); break;
		case 16: possibleTime=rTime.getTime10(); break;
		case 18: possibleTime=rTime.getTime10(); break;
		case 20: possibleTime=rTime.getTime10(); break;
		default: System.out.println("잘못된 시간을 입력하셨습니다."); return;
		}
		
		System.out.println("현재 수용 가능한 테이블 수 : "+(rest.getRestCapacity()-possibleTime));
		
		System.out.print("예약할 테이블 숫자를 입력해주세요 : ");
		int count = sc.nextInt();
		
		if(count+possibleTime>rest.getRestCapacity()) {
			System.out.println("현재 수용가능한 테이블 수는 "+(rest.getRestCapacity()-possibleTime)
					+"입니다.");
			return;
		}
		
		cController.updateReservationTime(rTime, time, count);
	}
	
	// 현재 예약 가능한 시간 출력
	public void displayReservationTime(ReservationTime rTime) {
		System.out.println("<<< 해당 시간대별 예약된 테이블 수 >>>");
		System.out.println("식당번호 / 날짜 / 10시 / 12시 / 14시 / 16시 / 18시 / 20시");
		System.out.println(rTime.toString());
	}
	
	// 예약 추가
	public void insertReservation(ReservationTime rTime, int time, int count) {
		
		cController.insertReservation(rTime, this.customer.getCusNo(), time, count);
	}
	
	// 성공 출력
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 결과 : "+message);
	}
	
	// 실패 출력
	public void displayError(String message) {
		System.out.println("서비스 요청 결과 : "+message);
	}
	
	
	
}
