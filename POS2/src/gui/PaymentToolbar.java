package gui;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class PaymentToolbar extends JToolBar{

	private static final long serialVersionUID = 1L;

	public PaymentToolbar() {
		final JButton PaymentButton = new JButton("Payment");
		final JButton CancleButton = new JButton("Cancle");
		final JButton TotalSalesButton = new JButton("Total sales");

		add(PaymentButton);
		add(CancleButton);
		add(TotalSalesButton);
	}
}
