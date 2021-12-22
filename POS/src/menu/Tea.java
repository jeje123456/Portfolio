package menu;

import java.util.ArrayList;
import java.util.List;

public class Tea extends Menu{

	public Tea(String name, int price) {
		super(name, price);

		List<Menu> tea = new ArrayList<Menu>();
		
		tea.add(new Menu("카모마일", 3500));
		tea.add(new Menu("얼그레이", 3500));
		tea.add(new Menu("루이보스", 3500));
		tea.add(new Menu("페퍼민트", 3500));
		tea.add(new Menu("레드베리", 3500));
	}

}
