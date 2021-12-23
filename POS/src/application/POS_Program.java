package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customers.Customer;
import menu.Coffee;
import menu.DrinkMenu;
import menu.Latte;

public class POS_Program {
	// 스캐너
	Scanner scanner = new Scanner(System.in);
	
	// 메뉴 리스트
	List<Coffee> coffee = new ArrayList<>();
	List<Latte> latte = new ArrayList<>();			
	// 주문내역
	List<DrinkMenu> order = new ArrayList<>();	
	// 고객 리스트
	List<Customer> customerList = new ArrayList<>();
	
	int total; // 총 주문 금액
	boolean Proceed = true;
	
	/**
	 * 메뉴 등록
	 */
	public void generateMenu() {	
		coffee.add(new Coffee("아메리카노", 2000));
		coffee.add(new Coffee("카페라떼", 3000));
		coffee.add(new Coffee("카푸치노", 3000));
		coffee.add(new Coffee("바닐라라떼", 3700));
		
		latte.add(new Latte("초콜릿라뗴", 3200));
		latte.add(new Latte("그린티라떼", 3200));
		latte.add(new Latte("밀크티라떼", 3200));
		latte.add(new Latte("민트초코라떼", 3200));
	}
	/**
	 * 기존 고객 등록
	 */
	public void generateCustomer() {
		customerList.add(new Customer("01000000000", 123, 100));
		customerList.add(new Customer("01011111111", 456, 200));
	}
	/**
	 * 카테고리 선택
	 */
	public void selectCategory() {
		while(Proceed) {
			System.out.println("┏━━ 주문 메뉴 ━━┓");
			System.out.println("┃    Coffee   ┃");
			System.out.println("┃    Latte    ┃");
			System.out.println("┗━━━━━━━━━━━━━┛");
			System.out.println("주문 완료시 '결제'를 입력해주세요");			
			System.out.print("주문할 메뉴의 종류를 선택해주세요 : ");		
			String category = scanner.nextLine();
			category = category.replace(" ", "");// 입력된 글자 공란 없애기
			category = category.toLowerCase(); // 입력된 글자 소문자로 변형
			System.out.println();
				
			printMenu(category);	
		}
	}
	/**
	 * 주문진행 or 결제진행
	 * @param category
	 */
	public void printMenu(String category) {
		if (category.equals("결제")) {
			System.out.println("주문종료");
			//결과출력
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			for(DrinkMenu orderEnd : order) {
				System.out.printf("%s : %d원 \n", orderEnd.getName(), orderEnd.getPrice());
			}
			System.out.printf("총 주문 금액 : %d원 \n", total);
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("포인트를 적립하시겠습니까? : Y / N");
			String put_point = scanner.nextLine();
			put_point = put_point.replace(" ", "");
			put_point = put_point.toLowerCase();
			
			checkID(put_point);
			
			System.out.println("CASH");
			System.out.println("CARD");
			System.out.println("POINT");			
			System.out.printf("원하시는 결제 방식을 선택하여주세요 : ");
			
			String payment = scanner.nextLine();
			payment = payment.replace(" ", "");
			payment = payment.toLowerCase();
			
			if (payment.equals("cash")) {
				paymentOnCash();
			}
			else if(payment.equals("card")){
				paymentOnCard();
			}
			else {
				System.out.println("잘못된 입력입니다.");
			}
			Proceed = false;
		}
		else if(category.equals("coffee")) {
			selectCoffee(coffee);
		}
		else if(category.equals("latte")) {
			selectLatte(latte);
		}
		else {
			System.out.println("잘못된 입력입니다. 메뉴 종류를 다시 입력해주세요.");
		}
	}
	/**
	 * 커피 선택시 커피메뉴 출력
	 * @param menu
	 */
	private void selectCoffee(List<Coffee> menu) {
		int n;
		
		System.out.println("━━━━━━━ 주문 메뉴 ━━━━━━━");
		menu.forEach(M -> System.out.println(M));
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.print("음료를 선택해주세요 : ");
		String drink = scanner.nextLine();
		drink = drink.replace(" ", "");
		
		for(Coffee cff : coffee) {
			if(drink.equals(cff.getName())){
				n = cff.getDrinkNumber();
				putOrder(menu.get(n-1));
			}
			else {
				System.out.println("잘못된 입력입니다. 음료를 다시 선택해주세요");
			}
		}	
	}
	/**
	 * 라떼 선택시 라떼메뉴 출력
	 * @param menu
	 */
	private void selectLatte(List<Latte> menu) {
		int n;
		
		System.out.println("━━━━━━━ 주문 메뉴 ━━━━━━━");
		
		menu.forEach(m -> System.out.println(m));
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.print("음료를 선택해주세요 : ");
		String drink = scanner.nextLine();
		drink = drink.replace(" ", "");
		
		for(Latte ltt : latte) {
			if(drink.equals(ltt.getName())){
				n = ltt.getDrinkNumber();
				putOrder(menu.get(n-5));
			}
			else {
				System.out.println("잘못된 입력입니다. 음료를 다시 선택해주세요");
			}
		}	
	}

	/**
	 * 주문된 메뉴를 order에 저장
	 * @param menu
	 */
	private void putOrder(DrinkMenu menu) {
		order.add(menu);
		total = 0;
		for (DrinkMenu orders : order){
			System.out.println(orders);
			total = total + orders.getPrice();
		}
	}
	/**
	 * ID확인
	 * @param point
	 */
	private void checkID(String put_point) {
		
		switch (put_point) {
		case "y":
			System.out.println("포인트 번호가 있습니까? Y / N");
			String check_registration = scanner.nextLine();
			check_registration = check_registration.replace(" ", "");
			check_registration = check_registration.toLowerCase();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			if (check_registration.equals("y")) {
				System.out.println("전화번호를 입력해주세요 : ");
				String phoneNumber = scanner.nextLine();
				phoneNumber = phoneNumber.replaceAll("[^0-9]", " ");//숫자만 남기기
				phoneNumber = phoneNumber.replace(" ", "");//공백없애기
				putPoint(phoneNumber);
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			}
			else if (check_registration.equals("n")) {
				System.out.println("고객등록을 진행합니다.");
				putNewCustomer();
			}	
			
		case "n":
			System.out.println("결제를 진행합니다.");
			
		default:
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}
	}
	/**
	 * 포인트 적립 메소드
	 */
	private void putPoint(String phoneNumber) {
		for(Customer cid : customerList) {
			System.out.println("포인트를 조회합니다.");
			if (phoneNumber.equals(cid.getId())) {
				System.out.printf("고객번호 %d님의 기존 포인트는 %d입니다.", cid.getCustomerNumber(), cid.getCustomerPoint());
				System.out.println();
				System.out.println();
				
				int x = cid.getCustomerNumber();
				int y = cid.getCustomerPoint();
				int newPoint = (int)(total * 0.01);
				y += newPoint;
				cid.setCustomerPoint(y);
				customerList.set(x, cid);
				System.out.printf("고객번호 %d님 %d 포인트가 적립 되었습니다.", cid.getCustomerNumber(), newPoint);
				System.out.printf("현재 포인트는 %d입니다.", cid.getCustomerPoint());
				System.out.println();
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			}
			else {
				System.out.println("등록되지 않은 고객입니다. 고객등록을 진행합니다.");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				putNewCustomer();
			}
		}		
		
	}
	/**
	 * 고객등록메소드
	 */
	public void putNewCustomer() {
		System.out.println("전화번호를 입력해주세요 : ");
		String newID = scanner.nextLine();
		newID = newID.replaceAll("[^0-9]", " ");//숫자만 남기기
		newID = newID.replace(" ", "");
		
		for (Customer newid : customerList) {
			if (newID.equals(newid.getId())) {
				System.out.println("이미 등록된 번호입니다.");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				putPoint(newID);
			}
			else {
				System.out.println("비밀번호를 입력해주세요 : ");
				String newPassword_String = scanner.nextLine();
				newPassword_String = newPassword_String.replaceAll("[^0-9]", " ");
				newPassword_String = newPassword_String.replace(" ", "");
				int newPassword = Integer.parseInt(newPassword_String);
				
				customerList.add(new Customer(newID, newPassword, 0));
				
				System.out.println("고객등록이 완료되었습니다.");
				for(Customer newcid : customerList) {
					if(newID.equals(newcid.getId())) {
						System.out.printf("고객님의 고객번호는 %d번 입니다.", newcid.getCustomerNumber());
						System.out.println();
						System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
						putPoint(newID);
					}
				}
			}
		}
	}
	/**
	 * 현금 계산
	 */
	public void paymentOnCash() {
		System.out.printf("지불하실 금액을 입력해주세요 : ");
			
		int cash = scanner.nextInt();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
		if (cash >= total) {
				System.out.printf("%d원을 지불하셨습니다. 거스름돈은 %d원 입니다.\n", cash, cash - total);
				System.out.println("계산이 완료되었습니다. 안녕히 가세요.");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} 
		else {
				System.out.printf("%d원을 더 지불하셔야 합니다.\n", total - cash);		
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				differencePayment(cash);
		}		
	}
	/**
	 * 현금 차액 지불하기
	 * @param cash
	 */ 
	public void differencePayment(int cash) {
		System.out.printf("#  차액 지불하기 : ");
		int difference = scanner.nextInt();
		if (difference == total - cash) {
			System.out.println("계산이 완료되었습니다. 안녕히 가세요.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} 
		else if (difference < total - cash) {	
			
			total = (total - cash) - difference; 
			
			System.out.printf("%d원을 더 지불하셔야 합니다.\n", total);
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
			differencePayment(total);
		} 
		else {
			System.out.printf("%d원을 지불하셨습니다. 거스름돈은 %d원 입니다.\n", difference , difference - (total - cash));
			System.out.println("계산이 완료되었습니다. 안녕히 가세요.");
			System.out.println("프로그램이 종료되었습니다.");
		}
	}
	
	
	/**
	 * 카드 계산
	 */
	public void paymentOnCard() {
		
		System.out.println("영수증 필요하신가요?(y/n)");
		
		String isNeedRecipt = scanner.next();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		scanner.close();
		
		switch (isNeedRecipt) {
		case "y": 	
			System.out.println("영수증 발급이 완료되었습니다. 안녕히 가세요.");
			System.out.println("프로그램이 종료되었습니다."); break;
		case "n":
			System.out.println("계산이 완료되었습니다. 안녕히 가세요.");
			System.out.println("프로그램이 종료되었습니다."); break;
		default:		
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("프로그램을 종료합니다.");
			System.exit(0); break;
		}
	}
}
