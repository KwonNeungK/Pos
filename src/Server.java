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

	final PopupMenu pMenu = new PopupMenu("Edit"); //final로 해야 이벤트 핸들러 실행가능
	private MenuItem menu = new MenuItem("메뉴보기");

	int total = 0;
	private MultiThread mt;

	private int price;
	private JButton discount = new JButton("할인");
	private JTextField cash = new JTextField();
	private JLabel showPrice = new JLabel("가격");

	private Server self = this;
	HashMap<String, ArrayList<Order>> foodlist = new HashMap<String, ArrayList<Order>>();

	private DefaultTableModel model;
	private JTable list;
	private JScrollPane js;

	private String[] a = new String[] {"음식","수량","가격"};
	private JButton[] btn = new JButton[13];

	private JLabel la1 = new JLabel("총   합 : ");
	private JLabel la2 = new JLabel("받은돈 : ");
	private JLabel la3 = new JLabel("잔   돈 : ");

	JTextField tf1 = new JTextField();   //총합금
	private JTextField tf2 = new JTextField();	 //받은돈	
	private JTextField tf3 = new JTextField();   //잔돈

	private JButton complete = new JButton("완료");
	private final JButton adjust = new JButton("정산");

	private void table(){  //테이블 위치

		for(int i = 0; i<btn.length;i++){
			btn[i] = new JButton((i+1)+"번테이블");
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

	private void btnEvent(){  //계산 리스트
		btn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Order tmp : foodlist.get("1")){
					model.addRow(new Object[] {tmp.getMenu(),tmp.getQuantity(),tmp.getPrice()*tmp.getQuantity()});
					total += tmp.getPrice()*tmp.getQuantity();
				}

				tf1.setText(""+total); 
				tabelend = "1"; // 빈자리 설정
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
				tabelend = "2"; // 빈자리 설정
				total = 0;
			}	
		});
	}

	private void textEvent(){   //계산 및 테이블 초기화
		discount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				price = Integer.parseInt(tf1.getText()); // text안에 있는 String을 a라는 변수에 integer로 담아준다.
				new DiscountDialog(self, price).setVisible(true);
				

			}
		}); // 할인 버튼에 대한 이벤트
		
		adjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AdjustmentUI(self).setVisible(true);
				
			}
		});
		
		tf2.addActionListener(new ActionListener() {  //계산
			public void actionPerformed(ActionEvent e) {
				int num1 = Integer.parseInt(tf1.getText()); //총합금
				int num2 = Integer.parseInt(tf2.getText()); //받은돈
				int num3 = num2 - num1;  //잔돈
				tf3.setText(""+num3);
			}
		});
		
		
		
		complete.addActionListener(new ActionListener() { //완료 버튼
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

	private void popupEvent(){ // 메뉴 보기
		btn[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){
				if(me.getModifiers() == me.BUTTON3_MASK) {//BUTTON3_MASK는 마우스 오른쪽 버튼 눌린 것
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
				if(me.getModifiers() == me.BUTTON3_MASK) {//BUTTON3_MASK는 마우스 오른쪽 버튼 눌린 것
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
		this.la1.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		getContentPane().add(la2);
		this.la2.setBounds(700, 303, 80, 70);
		this.la2.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		getContentPane().add(la3);
		this.la3.setBounds(700, 330, 80, 70);
		this.la3.setFont(new Font("맑은 고딕", Font.BOLD, 20));


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
		this.setTitle("권능 포스");
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
			System.out.println("서버가 연결을 대기하는 중 입니다.");
			Socket sock = server.accept(); // 네트워크에서 20000포트로 들어오는 데이터 듣기
			System.out.println(sock.getInetAddress() + "님이 접속 했습니다.");
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