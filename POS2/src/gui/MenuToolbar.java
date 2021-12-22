package gui;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class MenuToolbar extends JToolBar{

	private static final long serialVersionUID = 1L;

	public MenuToolbar() {
		final JButton CoffeeButton = new JButton("Coffee");
		final JButton LatteButton = new JButton("Latte");
		final JButton TeaButton = new JButton("Tea");
		
		add(CoffeeButton);
		add(LatteButton);
		add(TeaButton);
	}
}
