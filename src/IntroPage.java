import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IntroPage extends JFrame{
	private JSplitPane splitPane = new JSplitPane();
	private JPanel statusPanel = new JPanel();
	private JLabel statusLabel = new JLabel("status");
	private Timer timer;
	private JTextField textField;
	private JTextField textField_1;


	public void statusBar() {

		new Thread(){
			public void run(){
				while(true){
					Date today = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
					System.out.println(sdf.format(today));
					statusLabel.setText(sdf.format(today));
				}
			}
		}.start();

		this.statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(404, 144, 57, 15);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(473, 141, 116, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(404, 180, 57, 15);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(473, 177, 116, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(401, 220, 97, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(510, 220, 97, 23);
		panel.add(btnNewButton_1);
	}

	public IntroPage(){
		this.setSize(1000, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.statusBar();
		this.setVisible(true);
	}


	public static void main(String[] args) {
		new IntroPage();
	}
}
