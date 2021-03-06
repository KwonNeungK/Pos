import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AdjustmentUI extends JFrame {
	
	private Server parent = null;
	private JTabbedPane tPane = new JTabbedPane();
	//---입력 탭창---
	private JPanel panelLog = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelAccount = new JPanel();
	private JLabel chooseLabel = new JLabel("수입/지출");
	private JComboBox comboBox = new JComboBox();
	private JLabel contentsLabel = new JLabel("내역 : ");
	private JTextField contentsField = new JTextField();
	private JLabel countLabel = new JLabel("갯수 : ");
	private JTextField countField = new JTextField();
	private JLabel memoLabel = new JLabel("메모 : ");
	private JTextField memoField = new JTextField();
	private JLabel moneyLabel = new JLabel("금액 : ");
	private JTextField moneyField = new JTextField();
	private JButton registrationButton = new JButton("등록");
	private JButton accountOkButton = new JButton("정산완료");
	private JButton cancelButton = new JButton("취소");
	private JButton profitButton = new JButton("오늘 매출");
	
	//----table에 넣을 변수선언--
	private String selectItem = null;
	private	String product = null;
	private String count = null;
	private	String memo = null;
	private	String money = null;

	private void firstTap() {
		this.add(tPane);
		//---첫번째 탭 창 구성---
		//==입력창 panel== 
		this.comboBox.setPreferredSize(new Dimension(60,30));  //컴포넌트 사이즈 줌
		this.contentsField.setPreferredSize(new Dimension(80,30));
		this.memoField.setPreferredSize(new Dimension(100,30));
		this.moneyField.setPreferredSize(new Dimension(50,30));
		this.countField.setPreferredSize(new Dimension(30,30));
		//콤보박스에 선택지 줌
		this.comboBox.addItem("수입");
		this.comboBox.addItem("지출");
		//위치주기
		this.panelLog.add(chooseLabel);
		this.panelLog.add(comboBox);
		this.panelLog.add(contentsLabel);
		this.panelLog.add(contentsField);
		this.panelLog.add(memoLabel);
		this.panelLog.add(memoField);
		this.panelLog.add(moneyLabel);
		this.panelLog.add(moneyField);
		this.panelLog.add(countLabel);
		this.panelLog.add(countField);
		this.panelLog.add(registrationButton);
		this.panelLog.add(cancelButton);
		this.panelLog.add(profitButton);
		this.panelLog.add(accountOkButton);
		this.panelAccount.add(panelLog, BorderLayout.NORTH);


		//==입력결과 보여주는 panel==
		String columnNames[] = {"날짜","수입/지출","내역","수량","메모","금액","총금액"};

		Object rowData[][] = {	};

		DefaultTableModel dtm = new DefaultTableModel(rowData,columnNames);
		JTable table = new JTable(dtm);
		//테이블의 각각의 컬럼들 사이즈 줌
		table.getColumn("날짜").setPreferredWidth(80);
		table.getColumn("수입/지출").setPreferredWidth(80);
		table.getColumn("내역").setPreferredWidth(200);
		table.getColumn("수량").setPreferredWidth(80);
		table.getColumn("메모").setPreferredWidth(250);
		table.getColumn("금액").setPreferredWidth(50);
		JScrollPane scroll = new JScrollPane(table); //table을 스크롤panel에 넣기 table에 스크롤줌
		table.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		table.setPreferredScrollableViewportSize(new Dimension(900,400));
		this.panelAccount.add(scroll);

		tPane.addTab("정 산",panelAccount);
		//정산완료버튼 이벤트
		this.accountOkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
			}

		});
		//취소버튼 이벤트
		this.cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				contentsField.setText(" ");
				memoField.setText(" ");
				moneyField.setText(" ");
				countField.setText(" ");
			}
		});
		//등록버튼 이벤트
		this.registrationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectItem = comboBox.getSelectedItem().toString();
				product = contentsField.getText(); 
				count = countField.getText();
				memo = memoField.getText();
				money = moneyField.getText();
				Object[] temporaryObject = {20170816,selectItem,product,count,memo,money};
				dtm.addRow(temporaryObject);

				contentsField.setText(" ");
				memoField.setText(" ");
				countField.setText(" ");
				moneyField.setText(" ");
			}

		});
	}

	//---조회 탭창---
	private JPanel panelSelect = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelInquiry = new JPanel();
	private JPanel panelChart = new JPanel();
	private JRadioButton yearButton = new JRadioButton("연도별");
	private JRadioButton monthButton = new JRadioButton("월별");
	private ButtonGroup bg = new ButtonGroup();
	private YearChartPanel ycp = new YearChartPanel();
	private MonthChartPanel mcp = new MonthChartPanel();
	private void secondTap() {
		bg.add(yearButton);
 		bg.add(monthButton);
 		this.panelSelect.add(yearButton);
 		this.panelSelect.add(monthButton);
 		this.panelChart.setLayout(new CardLayout());
 		this.panelInquiry.setLayout(new BorderLayout());
 		this.panelInquiry.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));		
 		this.panelInquiry.add(panelSelect,BorderLayout.NORTH);
 		this.panelInquiry.add(panelChart,BorderLayout.CENTER);
 		
 		//연도별 버튼 클릭시 이벤트
 		this.yearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelChart.add(ycp,"year");
				CardLayout cl = (CardLayout) panelChart.getLayout();
				cl.show(panelChart,"year");
				
			}
 		});
 		//월별 버튼 클릭시 이벤트
 		this.monthButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelChart.add(mcp,"month");
				CardLayout cl = (CardLayout) panelChart.getLayout();
				cl.show(panelChart,"month");
			}
 			
 		});
 		tPane.addTab("조회", panelInquiry);
 
	}

	public AdjustmentUI(Server parent) {
		this.parent = parent;
		this.setTitle("정산");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.firstTap();
		this.secondTap();
		this.setVisible(true);
	}

}
