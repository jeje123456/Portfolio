package menu;

public class DrinkMenu {
	protected String name;
	protected int price;
	protected int drinkNumber;
	private static int countDrink = 0;
	
	public DrinkMenu(String name, int price) {
		this.name = name;
		this.price = price;
		countDrink++;
		drinkNumber = countDrink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getDrinkNumber() {
		return drinkNumber;
	}

	@Override
	public String toString() {
		return name + " : " + price +"원";
	}
	
	
}

