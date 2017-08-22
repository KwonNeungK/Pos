import java.awt.Color;
import java.awt.Font;
import java.awt.MenuComponent;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

public class Server extends JFrame{

	private String num = null;
	private String so;
	String tabelend;

	static TableColor tc = new TableColor();

	final PopupMenu pMenu = new PopupMenu("Edit"); //final�� �ؾ� �̺�Ʈ �ڵ鷯 ���డ��
	private MenuItem menu = new MenuItem("�޴�����");

	int total = 0;
	private MultiThread mt;

	private int price;
	private JButton discount = new JButton("����");
	private JTextField cash = new JTextField();
	private JLabel showPrice = new JLabel("����");

	private Server self = this;
	HashMap<String, ArrayList<Order>> foodlist = new HashMap<String, ArrayList<Order>>();

	private DefaultTableModel model;
	private JTable list;
	private JScrollPane js;

	private String[] a = new String[] {"����","����","����"};
	private JButton[] btn = new JButton[13];

	private JLabel la1 = new JLabel("��   �� : ");
	private JLabel la2 = new JLabel("������ : ");
	private JLabel la3 = new JLabel("��   �� : ");

	JTextField tf1 = new JTextField();   //���ձ�
	private JTextField tf2 = new JTextField();	 //������	
	private JTextField tf3 = new JTextField();   //�ܵ�

	private JButton complete = new JButton("�Ϸ�");
	private final JButton adjust = new JButton("����");

	private void table(){  //���̺� ��ġ

		for(int i = 0; i<btn.length;i++){
			btn[i] = new JButton((i+1)+"�����̺�");
		}
		getContentPane().add(btn[0]);
		this.btn[0].setBounds(40, 20, 100, 100);
		getContentPane().add(btn[1]);
		this.btn[1].setBounds(40, 190, 100, 100);
		getContentPane().add(btn[2]);
		this.btn[2].setBounds(40, 347, 100, 100);
		getContentPane().add(btn[3]);
		this.btn[3].setBounds(40, 500, 270, 100);
		getContentPane().add(btn[4]);
		this.btn[4].setBounds(220, 20, 100, 100);
		getContentPane().add(btn[5]);
		this.btn[5].setBounds(395, 20, 100, 100);
		getContentPane().add(btn[6]);
		this.btn[6].setBounds(380, 500, 100, 100);
		getContentPane().add(btn[7]);
		this.btn[7].setBounds(570, 20, 100, 100);
		getContentPane().add(btn[8]);
		this.btn[8].setBounds(570, 150, 100, 200);
		getContentPane().add(btn[9]);
		this.btn[9].setBounds(550, 500, 100, 100);
		getContentPane().add(btn[10]);
		this.btn[10].setBounds(220, 347, 100, 100);
	}

	public void arrlist(String a, ArrayList<Order> b){
		foodlist.put(a, b);
	}

	private void btnEvent(){  //��� ����Ʈ
		btn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Order tmp : foodlist.get("1")){
					model.addRow(new Object[] {tmp.getMenu(),tmp.getQuantity(),tmp.getPrice()*tmp.getQuantity()});
					total += tmp.getPrice()*tmp.getQuantity();
				}

				tf1.setText(""+total); 
				tabelend = "1"; // ���ڸ� ����
				total = 0;
			}
		});

		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Order tmp : foodlist.get("2")){
					model.addRow(new Object[] {tmp.getMenu(),tmp.getQuantity(),tmp.getPrice()*tmp.getQuantity()});
					total += tmp.getPrice()*tmp.getQuantity();
				}

				tf1.setText(""+total); 
				tabelend = "2"; // ���ڸ� ����
				total = 0;
			}	
		});
	}

	private void textEvent(){   //��� �� ���̺� �ʱ�ȭ
		discount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				price = Integer.parseInt(tf1.getText()); // text�ȿ� �ִ� String�� a��� ������ integer�� ����ش�.
				new DiscountDialog(self, price).setVisible(true);
				

			}
		}); // ���� ��ư�� ���� �̺�Ʈ
		
		adjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AdjustmentUI(self).setVisible(true);
				
			}
		});
		
		tf2.addActionListener(new ActionListener() {  //���
			public void actionPerformed(ActionEvent e) {
				int num1 = Integer.parseInt(tf1.getText()); //���ձ�
				int num2 = Integer.parseInt(tf2.getText()); //������
				int num3 = num2 - num1;  //�ܵ�
				tf3.setText(""+num3);
			}
		});
		
		
		
		complete.addActionListener(new ActionListener() { //�Ϸ� ��ư
			public void actionPerformed(ActionEvent e) {
				int del = model.getRowCount();
				for(int i = 0;i<del;i++){
					model.removeRow(0);
				}
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");

				if(tabelend.equals(mt.table)){
					tc.setNum("0");
				}

			}
		});

		

	}

	private void popupEvent(){ // �޴� ����
		btn[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){
				if(me.getModifiers() == me.BUTTON3_MASK) {//BUTTON3_MASK�� ���콺 ������ ��ư ���� ��
					pMenu.show(btn[0], me.getX(), me.getY());
					//					if(mt.table.equals("1")){
					//						num = "1";
					//						arr = new ArrayList<String>();
					//						for(Order tmp : mt.data()) {
					//							arr.add(tmp.getMenu()+" : "+tmp.getQuantity()+"\n");
					//						}
					//						data.put("1", arr.toString());
					//					}	
				}
			}
		});

		btn[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){
				if(me.getModifiers() == me.BUTTON3_MASK) {//BUTTON3_MASK�� ���콺 ������ ��ư ���� ��
					pMenu.show(btn[1], me.getX(), me.getY());
					//					if(mt.table.equals("2")){
					//						num = "2";
					//						arr = new ArrayList<String>();
					//						for(Order tmp : mt.data()) {
					//							arr.add(tmp.getMenu()+" : "+tmp.getQuantity()+"\n");
					//						}
					//						data.put("2", arr.toString());
					//					}	
				}
			}
		});

		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FoodList(self,num).setVisible(true);
			}
		});

		pMenu.add(menu);
		getContentPane().add(pMenu);	
	}

	private void comInit(){
		getContentPane().setLayout(null);
		model = new DefaultTableModel(a,0);
		list = new JTable(model);
		js = new JScrollPane(list);

		getContentPane().add(js);
		this.js.setBounds(700, 0, 300, 300);


		la1.setHorizontalAlignment(JLabel.LEFT);
		la2.setHorizontalAlignment(JLabel.LEFT);
		la3.setHorizontalAlignment(JLabel.LEFT);

		getContentPane().add(la1);
		this.la1.setBounds(700, 275, 80, 70);
		this.la1.setFont(new Font("���� ���", Font.BOLD, 20));

		getContentPane().add(la2);
		this.la2.setBounds(700, 303, 80, 70);
		this.la2.setFont(new Font("���� ���", Font.BOLD, 20));

		getContentPane().add(la3);
		this.la3.setBounds(700, 330, 80, 70);
		this.la3.setFont(new Font("���� ���", Font.BOLD, 20));


		getContentPane().add(tf1);
		this.tf1.setBounds(780, 300, 205, 30);
		getContentPane().add(tf2);
		this.tf2.setBounds(780, 327, 205, 30);
		getContentPane().add(tf3);
		this.tf3.setBounds(780, 354, 205, 30);

		getContentPane().add(complete);
		this.complete.setBounds(700, 384, 285, 30);
		this.discount.setBounds(700, 417, 285, 30);

		getContentPane().add(discount);
		adjust.setBounds(700, 450, 285, 30);
		
		getContentPane().add(adjust);
	}



	public Server() throws Exception{
		this.setTitle("�Ǵ� ����");
		this.setSize(1000,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.table();
		this.popupEvent();
		this.btnEvent();
		this.textEvent();
		this.comInit();
		this.setVisible(true);

		//		new Thread(){
		//			public void run(){
		//				while(true){
		//
		//					if(tc.getNum().equals("0")){
		//						self.btn[0].setBackground(Color.blue);
		//						self.btn[1].setBackground(Color.blue);
		//					}
		//
		//					if(tc.getNum().equals("1")){
		//						self.btn[0].setBackground(Color.red);
		//					}
		//
		//					if(tc.getNum().equals("2")){
		//						self.btn[1].setBackground(Color.red);
		//					}
		//
		//				}
		//			}
		//		}.start();

		ServerSocket server = new ServerSocket(20000);

		while (true) {
			System.out.println("������ ������ ����ϴ� �� �Դϴ�.");
			Socket sock = server.accept(); // ��Ʈ��ũ���� 20000��Ʈ�� ������ ������ ���
			System.out.println(sock.getInetAddress() + "���� ���� �߽��ϴ�.");
			mt = new MultiThread(self,sock);
			mt.start();
		}


	}
	public static void main(String[] args) throws Exception{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		new Server();
	}
}