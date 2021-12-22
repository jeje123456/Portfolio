package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import menu.DrinkMenu;

public class POS_Program {
	List<DrinkMenu> coffee = new ArrayList<>();
	List<DrinkMenu> latte = new ArrayList<>();
	
	// 스캐너
	Scanner scanner = new Scanner(System.in);
			
	// 주문내역
	List<DrinkMenu> order = new ArrayList<>();
	
	int total; // 총 주문 금액
	int n;
	boolean Proceed = true;
	
	public void generate() {
		
		coffee.add(new DrinkMenu("아메리카노", 2000));
		coffee.add(new DrinkMenu("카페 라떼", 3000));
		coffee.add(new DrinkMenu("카푸치노", 3000));
		coffee.add(new DrinkMenu("바닐라 라떼", 3700));
		
		latte.add(new DrinkMenu("초콜릿 라뗴", 3200));
		latte.add(new DrinkMenu("그린티 라떼", 3200));
		latte.add(new DrinkMenu("밀크티 라떼", 3200));
		latte.add(new DrinkMenu("민트초코 라떼", 3200));

	}
	
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
			selectMenu(coffee);
		}
		else if(category.equals("latte")) {
			selectMenu(latte);
		}
		else {
			System.out.println("잘못된 입력입니다. 메뉴 종류를 다시 입력해주세요.");
		}
	}

	private void selectMenu(List<DrinkMenu> menu) {
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
		else if (drink.equals("초콜릿라떼")) {
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

	private void putOrder(DrinkMenu menu) {
		order.add(menu);
		total = 0;
		for (DrinkMenu orders : order){
			System.out.println(orders);
			total = total + orders.getPrice();
		}
	}
}

