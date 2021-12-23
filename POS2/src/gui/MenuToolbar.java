package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

class CoffeebuttonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new CoffeeFrame();
		MainFrame mainFrame = new MainFrame(null);
		mainFrame.setVisible(false);
	}
	
}

public class MenuToolbar extends JToolBar{

	private static final long serialVersionUID = 1L;

	public MenuToolbar() {
		final JButton CoffeeButton = new JButton("Coffee");
		final JButton LatteButton = new JButton("Latte");
		final JButton TeaButton = new JButton("Tea");
		
		// 버튼에 이벤트 입력
		CoffeeButton.addActionListener(new CoffeebuttonListener());
		
		// 버튼 붙이기
		add(CoffeeButton);
		add(LatteButton);
		add(TeaButton);
	}
}
