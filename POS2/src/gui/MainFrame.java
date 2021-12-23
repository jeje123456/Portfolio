package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public MainFrame(String title) {
		super("카페POS");
		
		setVisible(true); // 창을 보이게 함
		setSize(600,400); // 가로세로 길이 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 종료시 프로그램 종료	
		
		add(new MainPanel(),BorderLayout.CENTER);
		add(new MenuToolbar(),BorderLayout.NORTH);
		add(new PaymentToolbar(),BorderLayout.SOUTH);
	}

}
