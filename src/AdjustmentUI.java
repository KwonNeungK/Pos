import java.awt.BorderLayout;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

public class AdjustmentUI extends JDialog {
	
	private Server parent = null;
	
	private JTabbedPane tPane = new JTabbedPane();
	//---�Է� ��â---
	private JPanel panelLog = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel panelAccount = new JPanel();
	private JLabel chooseLabel = new JLabel("����/����");
	private JComboBox comboBox = new JComboBox();
	private JLabel contentsLabel = new JLabel("���� : ");
	private JTextField contentsField = new JTextField();
	private JLabel countLabel = new JLabel("���� : ");
	private JTextField countField = new JTextField();
	private JLabel memoLabel = new JLabel("�޸� : ");
	private JTextField memoField = new JTextField();
	private JLabel moneyLabel = new JLabel("�ݾ� : ");
	private JTextField moneyField = new JTextField();
	private JButton registrationButton = new JButton("���");
	private JButton accountOkButton = new JButton("����Ϸ�");
	private JButton cancelButton = new JButton("���");
	private JButton profitButton = new JButton("���� ����");
	
	//----table�� ���� ��������--
	private String selectItem = null;
	private	String product = null;
	private String count = null;
	private	String memo = null;
	private	String money = null;

	private void FirstTap() {
		this.add(tPane);
		//---ù��° �� â ����---
		//==�Է�â panel== 
		this.comboBox.setPreferredSize(new Dimension(60,30));  //������Ʈ ������ ��
		this.contentsField.setPreferredSize(new Dimension(80,30));
		this.memoField.setPreferredSize(new Dimension(100,30));
		this.moneyField.setPreferredSize(new Dimension(50,30));
		this.countField.setPreferredSize(new Dimension(30,30));
		//�޺��ڽ��� ������ ��
		this.comboBox.addItem("����");
		this.comboBox.addItem("����");
		//��ġ�ֱ�
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

		
		//==�Է°�� �����ִ� panel==
		String columnNames[] = {"��¥","����/����","����","����","�޸�","�ݾ�","�ѱݾ�"};

		Object rowData[][] = {	};

		DefaultTableModel dtm = new DefaultTableModel(rowData,columnNames);
		JTable table = new JTable(dtm);
		//���̺��� ������ �÷��� ������ ��
		table.getColumn("��¥").setPreferredWidth(80);
		table.getColumn("����/����").setPreferredWidth(80);
		table.getColumn("����").setPreferredWidth(200);
		table.getColumn("����").setPreferredWidth(80);
		table.getColumn("�޸�").setPreferredWidth(250);
		table.getColumn("�ݾ�").setPreferredWidth(50);
		JScrollPane scroll = new JScrollPane(table); //table�� ��ũ��panel�� �ֱ� table�� ��ũ����
		table.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
		table.setPreferredScrollableViewportSize(new Dimension(900,400));
		this.panelAccount.add(scroll);

		tPane.addTab("�� ��",panelAccount);
		//����Ϸ��ư �̺�Ʈ
		this.accountOkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
			}

		});
		//��ҹ�ư �̺�Ʈ
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
		//��Ϲ�ư �̺�Ʈ
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

	//---��ȸ ��â---
	private JPanel panelSelect = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel yearEventPanel = new JPanel();
	private JPanel monthEventPanel = new JPanel();
	private JPanel panelInquiry = new JPanel();
	private JCheckBox yearBox = new JCheckBox("������");
	private JCheckBox monthBox = new JCheckBox("����");
	private ButtonGroup bg = new ButtonGroup();

	//===������ �޺��ڽ� �̺�Ʈ �� ���� ��Ʈ
	private DefaultCategoryDataset yeargetDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series = "profit";
		String type1 = "2015";
		String type2 = "2016";
		String type3 = "2017";

		dataset.addValue(4000000, series, type1);
		dataset.addValue(3000000, series, type2);
		dataset.addValue(8000000, series, type3);

		return dataset;
	}
	private JFreeChart yearChart = ChartFactory.createLineChart(
			"",
			"YEAR",
			"Profit",
			yeargetDataset(),
			PlotOrientation.VERTICAL,
			false,
			true,
			false
			);

	//===���� �޺��ڽ� �̺�Ʈ �� ���� ��Ʈ
	private DefaultCategoryDataset monthgetDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series = "profit";
		String type1 = "jan";
		String type2 = "Feb";
		String type3 = "Mar";
		String type4 = "Apr";
		String type5 = "May";

		dataset.addValue(150000, series, type1);
		dataset.addValue(500000, series, type2);
		dataset.addValue(300000, series, type3);
		dataset.addValue(600000, series, type4);
		dataset.addValue(900000, series, type5);

		return dataset;
	}
	private JFreeChart monthChart = ChartFactory.createLineChart(
			"",
			"Month",
			"Profit",
			monthgetDataset(),
			PlotOrientation.VERTICAL,
			false,
			true,
			false
			);
	private void SecondTap() {
		bg.add(yearBox);
 		bg.add(monthBox);
 		this.panelSelect.add(yearBox);
 		this.panelSelect.add(monthBox);
 		this.panelInquiry.setLayout(new BorderLayout());
 		this.panelInquiry.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));		
 		this.panelInquiry.add(panelSelect,BorderLayout.NORTH);
 		//===������ checkbox�̺�Ʈ===
 		this.yearBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChartPanel yearCP = new ChartPanel(yearChart);
				panelInquiry.setBorder(new LineBorder(Color.BLUE));
				panelInquiry.add(yearCP,BorderLayout.CENTER);
			}
 		});
 		
 		//===���� checkbox �̺�Ʈ===
 		new Thread(){
 			public void run() {
 				monthBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChartPanel monthCP = new ChartPanel(monthChart);
				panelInquiry.setBorder(new LineBorder(Color.RED));
				panelInquiry.add(monthCP,BorderLayout.CENTER);
			}
 		});
 			}
 		}.start();
 		tPane.addTab("��ȸ", panelInquiry);
 
	}
	public AdjustmentUI(Server parent) {
		this.parent = parent;
		this.setTitle("����");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.FirstTap();
		this.SecondTap();
		this.setVisible(true);
	}

}
