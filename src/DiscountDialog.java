import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DiscountDialog extends JDialog{

	private Server parent = null;
	private int price;
	private JButton memDiscount = new JButton("직원할인");
	private JButton eventDiscount = new JButton("행사할인");

	private JLabel priceLabel = new JLabel("");
	private JPanel panelInput = new JPanel(new GridBagLayout());

	private void eventInit(){
		this.memDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDis md = new MemberDis(price); // MemberDis 인스턴스 생성자에 price를 넘긴다.
				parent.tf1.setText(""+md.returnMember());
				dispose();
			}
		});

		this.eventDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventDiscount ed = new EventDiscount(price);
				parent.tf1.setText(""+ed.returnEvent());
				dispose();
			}
		});
		
	}

	private void compInit(){
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(10,5,10,5);
		c.gridy = 2; c.gridx = 2;
		this.panelInput.add(memDiscount,c);
		this.memDiscount.setPreferredSize(new Dimension(130,130));
		c.gridy = 2; c.gridx = 1;
		this.panelInput.add(eventDiscount,c);
		this.eventDiscount.setPreferredSize(new Dimension(130,130));

		this.add(panelInput);
	}

	public DiscountDialog(Server parent, int price){
		this.parent = parent;
		this.price = price;
		this.setSize(300,300);
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.compInit();
		this.eventInit();

	}
}
