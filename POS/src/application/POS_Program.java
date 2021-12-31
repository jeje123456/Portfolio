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
	boolean notEquals = true;
	
	/**
	 * 메뉴 등록
	 */
	public void generateMenu() {	
		coffee.add(new Coffee("아메리카노", 2000));
		coffee.add(new Coffee("카페라떼", 3000));
		coffee.add(new Coffee("카푸치노", 3000));
		coffee.add(new Coffee("바닐라라떼", 3700));
		
		latte.add(new Latte("초콜릿라떼", 3200));
		latte.add(new Latte("그린티라떼", 3200));
		latte.add(new Latte("밀크티라떼", 3200));
		latte.add(new Latte("민트초코라떼", 3200));
	}
	/**
	 * 기존 고객 등록
	 */
	public void generateCustomer() {
		customerList.add(new Customer("01000000000", "123", 10000));
		customerList.add(new Customer("01011111111", "456", 200));
	}
	/**
	 * 카테고리 선택
	 */
	public void selectCategory() {
		while(true) {
			System.out.println(" ┏━━━━━━ 주문 메뉴 ━━━━━━┓");
			System.out.println(" ┃        Coffee       ┃");
			System.out.println(" ┃        Latte        ┃");
			System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━┛");	
			System.out.println("주문할 메뉴의 종류를 선택해주세요 : ");		
			System.out.println("(주문 완료시 '결제'를 입력해주세요)");		
			String category = scanner.nextLine();
			category = category.replaceAll("[^\\uAC00-\\uD7A3a-zA-Z]", "");//문자만 남기기
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
			System.out.println("━━━━━━━━━━━ 주문내역 ━━━━━━━━━━━");

			for(DrinkMenu orderEnd : order) {
				System.out.printf("%s : %d원 \n", orderEnd.getName(), orderEnd.getPrice());
			}
			System.out.printf("총 주문 금액 : %d원 \n", total);
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			if (total > 0) {
			put_Point();
			} else {
				System.out.println("주문 내역이 없습니다. 프로그램을 종료합니다.");
				scanner.close();
				System.exit(0);
			}
		}else if(category.equals("coffee")||category.equals("커피")) {
			selectCoffee(coffee); // 커피메뉴 출력 및 선택 실행
		}else if(category.equals("latte")||category.equals("라떼")) {
			selectLatte(latte); // 라떼메뉴 출력 및 선택 실행
		}else {
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}
	}
	/**
	 * 커피 선택시 커피메뉴 출력
	 * @param menu
	 */
	private void selectCoffee(List<Coffee> menu) {
		System.out.println("━━━━━━━ 주문 메뉴 ━━━━━━━");
		menu.forEach(M -> System.out.println(M));
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.print("음료를 선택해주세요 : ");
		String drink = scanner.nextLine();
		drink = drink.replaceAll("[^\\uAC00-\\uD7A3]", ""); //한글만 남기기
		System.out.println();
		
		for(Coffee cff : coffee) {
			if(drink.equals(cff.getName())){
				int n = cff.getDrinkNumber();
				putOrder(menu.get(n-1));
				notEquals = false;
			}
		}
		if(notEquals) {
			System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			selectCoffee(coffee);
		}
	}
	/**
	 * 라떼 선택시 라떼메뉴 출력
	 * @param menu
	 */
	private void selectLatte(List<Latte> menu) {
		System.out.println("━━━━━━━ 주문 메뉴 ━━━━━━━");
		menu.forEach(m -> System.out.println(m));
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.print("음료를 선택해주세요 : ");
		String drink = scanner.nextLine();
		drink = drink.replaceAll("[^\\uAC00-\\uD7A3]", "");
		System.out.println();
		
		for(Latte ltt : latte) {
			if(drink.equals(ltt.getName())){
				int n = ltt.getDrinkNumber();
				putOrder(menu.get(n-5));
				notEquals = false;
			}
		}
		if(notEquals) {
			System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			selectLatte(latte);
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
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
			total = total + orders.getPrice();
		}
	}
	/**
	 * 포인트 적립 선택
	 */
	private void put_Point() {
		System.out.println("포인트를 적립하시겠습니까? : Y / N");
		String put_point = scanner.nextLine();
		put_point = put_point.replaceAll("[^a-zA-Z]","");
		put_point = put_point.toLowerCase();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		if (put_point.equals("y")) {
			checkID(); // ID확인 실행
		}else if (put_point.equals("n")) {
			System.out.println("포인트를 적립하지 않습니다. 결제를 진행합니다.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			paymentType(); // 결제 실행
		}else {
			System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			put_Point();
		}
	}	
	/**
	 * ID 존재 유무 확인
	 */
	private void checkID() {
		System.out.println("포인트 번호가 있습니까? Y / N");
		String check_ID = scanner.nextLine();
		check_ID = check_ID.replaceAll("[^a-zA-Z]","");
		check_ID = check_ID.toLowerCase();
		
		if (check_ID.equals("y")) {
			System.out.println("전화번호를 입력해주세요 : ");
			String customerID = scanner.nextLine();
			customerID = customerID.replaceAll("[^0-9]", "");//숫자만 남기기
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			putPoint(customerID);
		}else if (check_ID.equals("n")) {
			System.out.println("고객등록을 진행합니다.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			newCustomer();
		}else {
			System.out.println("잘못입력하셨습니다. 다시 입력해주세요");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			checkID();
		}
	}
	/**
	 * 고객등록메소드
	 */
	public void newCustomer() {
		System.out.println("전화번호를 입력해주세요 : ");
		String S_newID = scanner.nextLine();
		S_newID = S_newID.replaceAll("[^0-9]", "");
		try {
			int I_newID = Integer.parseInt(S_newID);
			String newID = Integer.toString(I_newID);
			for (Customer newid : customerList) {
				if (newID.equals(newid.getId())) {
					System.out.println("이미 등록된 번호입니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					putPoint(newID);
					paymentType();
				}
			}
			System.out.println("비밀번호를 입력해주세요 : ");
			String newPassword = scanner.nextLine();
			newPassword = newPassword.replace(" ", ""); //공백만 제거
			
			customerList.add(new Customer(newID, newPassword, 0));
			
			System.out.println("고객등록이 완료되었습니다.");
			for(Customer newId : customerList) {
				if(newID.equals(newId.getId())) {
					System.out.printf("고객님의 고객번호는 %d번 입니다.", newId.getCustomerNumber());
					System.out.println();
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					putPoint(newID);
				}
			}
		} catch (Exception e) {
			System.out.println("입력된 값에 숫자가 없습니다. 다시 입력해주세요.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			newCustomer();
		}			
	}
	/**
	 * 포인트 적립 메소드
	 */
	private void putPoint(String customerID) {
		for(Customer cid : customerList) {
			if (customerID.equals(cid.getId())) {
				int cn = cid.getCustomerNumber();
				int cp = cid.getCustomerPoint();
				int newPoint = (int)(total * 0.01);				
				System.out.println("포인트를 조회합니다.");
				System.out.printf("고객번호 %d님의 기존 포인트는 %d입니다.\n", cn, cp);		
				cp += newPoint;
				cid.setCustomerPoint(cp);
				customerList.set(cn-1, cid);
				System.out.printf("고객번호 %d님 %d 포인트가 적립 되었습니다. \n", cn, newPoint);
				System.out.printf("현재 포인트는 %d입니다.\n", cid.getCustomerPoint());
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("포인트를 사용하시겠습니까? Y / N");
				String use_Point = scanner.nextLine();
				use_Point = use_Point.replaceAll("[^a-zA-Z]", "");
				use_Point = use_Point.toLowerCase();			
				if(use_Point.equals("y")) {
					checkPessword(customerID);
				} else if (use_Point.equals("n")) {
					System.out.println("포인트를 사용하지 않습니다. 결제를 진행합니다.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					paymentType();
				} else {
					System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					putPoint(customerID);
				}
			}
		}
		System.out.println("등록되지 않은 고객입니다. 고객등록을 진행합니다.");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		newCustomer();				
	}
	/**
	 * 비밀번호 확인
	 * @param customerID
	 */
	private void checkPessword(String customerID) {
		System.out.println("비밀번호를 입력해 주세요.");
		String check_password = scanner.nextLine();
		check_password = check_password.replace(" ","");
		
		for(Customer thiscutomer : customerList) {
			if (customerID.equals(thiscutomer.getId())) {					
				if(check_password.equals(thiscutomer.getPassword())) {
					usePoint(customerID, check_password);
				}else {
					System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요.");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					checkPessword(customerID);
				}							
			}
		}
	}
	/**
	 * 포인트로 결제
	 * @param customerID
	 */
	private void usePoint(String customerID, String check_password) {
		for(Customer thiscutomer : customerList) {
			if (customerID.equals(thiscutomer.getId())) {		
				if(check_password.equals(thiscutomer.getPassword())) {
					int cn = thiscutomer.getCustomerNumber();
					int cp = thiscutomer.getCustomerPoint();
					
					System.out.println("사용하실 포인트를 입력해주세요");
					String S_point = scanner.nextLine();
					S_point = S_point.replaceAll("[^0-9]", "");//숫자만 남기기
					try {
						int point = Integer.parseInt(S_point);
						System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");						
						if(point <= cp) {				
							if (total > point) {
								cp -= point;
								thiscutomer.setCustomerPoint(cp);
								customerList.set(cn-1, thiscutomer);
								total -= point;
								System.out.printf("고객번호 %d님 %d 포인트가 사용되었습니다.\n", cn, point);
								System.out.printf("현재 포인트는 %d입니다.\n", thiscutomer.getCustomerPoint());
								System.out.printf("결제하실 금액은 %d원 입니다.\n", total);
								paymentType();
							}else if(total <= point) {
							thiscutomer.setCustomerPoint(cp);
							System.out.printf("고객번호 %d님 %d 포인트가 사둉되었습니다.\n", cn, total);
							cp -= total;
							customerList.set(cn-1, thiscutomer);
							System.out.printf("남은 포인트는 %d입니다.\n", thiscutomer.getCustomerPoint());
							System.out.println("결제가 완료되었습니다. 안녕히 가세요");
							scanner.close();
							System.exit(0);
							}
						}else {
							System.out.println("입력하신 포인트가 소유 포인트보다 많습니다. 다시입력해주세요.");
							System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
							usePoint(customerID, check_password);
						}
					} catch (Exception e) {
						System.out.println("입력된 값에 숫자가 없습니다. 다시 입력해주세요.");
						System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
						usePoint(customerID, check_password);
					}
					
				}
			}
		}
	}
	/**
	 * 결제타입 선택	
	 */
	public void paymentType() {
		System.out.println("CASH");
		System.out.println("CARD");		
		System.out.println("원하시는 결제 방식을 선택하여주세요 : ");
		String payment = scanner.nextLine();
		payment = payment.replaceAll("[^a-zA-Z]","");
		payment = payment.toLowerCase();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		if (payment.equals("cash")) {
			paymentOnCash();
		}else if(payment.equals("card")){
			paymentOnCard();
		}else {
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			paymentType();
		}	
	}
	/**
	 * 현금 계산
	 */
	public void paymentOnCash() {
		System.out.printf("지불하실 금액을 입력해주세요 : ");	
		String S_cash = scanner.nextLine();
		S_cash = S_cash.replaceAll("[^0-9]", "");//숫자만 남기기
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		try {
			int cash = Integer.parseInt(S_cash);
			if (cash > total) {
				System.out.printf("%d원을 지불하셨습니다. 거스름돈은 %d원 입니다.\n", cash, cash - total);
				System.out.println("결제가 완료되었습니다. 안녕히 가세요");
				scanner.close();
				System.exit(0);
			}else if(cash == total) {
				System.out.printf("%d원을 지불하셨습니다.\n", cash);
				System.out.println("결제가 완료되었습니다. 안녕히 가세요");
				scanner.close();
				System.exit(0);
			}else if(cash < total){
				total -= cash;
				System.out.printf("%d원을 더 지불하셔야 합니다.\n", total);	
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				paymentType();
			}
		} catch (Exception e) {
			System.out.println("입력된 값에 숫자가 없습니다. 다시 입력해주세요.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			paymentOnCash();
		}	
		
	}
	/**
	 * 카드 계산
	 */
	public void paymentOnCard() {
		
		System.out.println("결제가 완료되었습니다. 안녕히 가세요");
		scanner.close();
		System.exit(0);
		
	}
}
