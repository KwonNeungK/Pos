import java.net.Socket;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class FoodList extends JDialog{

	private Server ser;
	private String num;
	
	private JTextArea area = new JTextArea();

	private void comInit(){
		//area.setText(""+ser.data.get(num));
		System.out.println(num);
		this.add(area);
	}


	public FoodList(Server self, String num){
		ser = self;
		this.num = num;
		this.setTitle("¸Þ´º");
		this.setSize(400, 250);
		this.setLocationRelativeTo(self);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.comInit();
		this.setVisible(true);
	}

}

