package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import menu.Coffee;
import menu.DrinkMenu;
import menu.Latte;

public class POS_Program {
	List<Coffee> coffee = new ArrayList<>();
	List<Latte> latte = new ArrayList<>();
	
	// 스캐너
	Scanner scanner = new Scanner(System.in);
			
	// 주문내역
	List<DrinkMenu> order = new ArrayList<>();
	
	int total; // 총 주문 금액
	int n;
	boolean Proceed = true;
	/**
	 * 메뉴 등록
	 */
	public void generate() {
		
		coffee.add(new Coffee("아메리카노", 2000));
		coffee.add(new Coffee("카페 라떼", 3000));
		coffee.add(new Coffee("카푸치노", 3000));
		coffee.add(new Coffee("바닐라 라떼", 3700));
		
		latte.add(new Latte("초콜릿 라뗴", 3200));
		latte.add(new Latte("그린티 라떼", 3200));
		latte.add(new Latte("밀크티 라떼", 3200));
		latte.add(new Latte("민트초코 라떼", 3200));

	}
	/**
	 * 주문 메뉴의 대분류 입력
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
	 * 주문 메뉴의 대분류 선택에 따른 결과
	 * @param category
	 */
	public void printMenu(String category) {
		if (category.equals("결제")) {
			System.out.println("주문종료");
			//결과출력
			System.out.println("========================");
			for(DrinkMenu orderEnd : order) {
				System.out.printf("%s : %d원 \n", orderEnd.getName(), orderEnd.getPrice());
			}
			System.out.printf("총 주문 금액 : %d원 \n", total);
			scanner.close();
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
	 * 커피를 선택했을때 커피메뉴 출력 및 음료 선택
	 * @param menu
	 */
	private void selectCoffee(List<Coffee> menu) {
		System.out.println("━━━━━━━ 주문 메뉴 ━━━━━━━");
		for(DrinkMenu showMenu : menu) {
			System.out.println(showMenu);
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("음료를 선택해주세요 : ");
		String drink = scanner.nextLine();
		drink = drink.replace(" ", "");
		
		if (drink.equals("아메리카노")) {
			n = 0;
			putOrder(menu.get(n));
		}
		else if (drink.equals("카페라떼")) {
			n = 1;
			putOrder(menu.get(n));
		}
		else if (drink.equals("카푸치노")) {
			n = 2;
			putOrder(menu.get(n));
		}
		else if (drink.equals("바닐라라떼")) {
			n = 3;
			putOrder(menu.get(n));
		}
		else {
			System.out.println("잘못된 입력입니다. 음료를 다시 선택해주세요");
		}
	}
	/**
	 * 라떼를 선택했을때 라떼메뉴 출력 및 음료 선택
	 * @param menu
	 */
	private void selectLatte(List<Latte> menu) {
		System.out.println("━━━━━━━ 주문 메뉴 ━━━━━━━");
		for(DrinkMenu showMenu : menu) {
			System.out.println(showMenu);
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("음료를 선택해주세요 : ");
		String drink = scanner.nextLine();
		drink = drink.replace(" ", "");
		
		if (drink.equals("초콜릿라떼")) {
			n = 0;
			putOrder(menu.get(n));
		}
		else if (drink.equals("그린티라떼")) {
			n = 1;
			putOrder(menu.get(n));
		}
		else if (drink.equals("밀크티라떼")) {
			n = 2;
			putOrder(menu.get(n));
		}
		else if (drink.equals("민트초코라떼")) {
			n = 3;
			putOrder(menu.get(n));
		}
		else {
			System.out.println("잘못된 입력입니다. 음료를 다시 선택해주세요");
		}
	}
	/**
	 * 전체 주문 내역 출력 및 총 결제금액 출력
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
}


