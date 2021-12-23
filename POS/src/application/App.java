package application;

public class App {

	public static void main(String[] args) {
		
		POS_Program program = new POS_Program();
		
		program.generateCustomer();
		program.generateMenu();
		program.selectCategory();
	}
}
