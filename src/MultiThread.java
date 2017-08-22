import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

public class MultiThread extends Thread{
	Server ser;
	String table;
	String msg;

	private Socket sock;

	HashMap<String, ArrayList<Order>> map = new HashMap<String, ArrayList<Order>>();

	ObjectInputStream input;

	public MultiThread(Server ser,Socket sock){
		this.ser = ser;
		this.sock = sock;
	}


	public void run(){
		try {
			OutputStream os = sock.getOutputStream(); // 데이터 보내기(스트림 개방) -> import java.io.OutputStream;
			DataOutputStream dos = new DataOutputStream(os); // 데이터 보내기 스트림 업그레이드 -> import java.io.OutputStream;

			InputStream is = sock.getInputStream(); // 데이터 받기 (스트림개방)
			DataInputStream dis = new DataInputStream(is); // 데이터 받기 스트림 업그레이드

			while(true){
				msg = dis.readUTF();
				if(msg.equals("orders")) {
					table = dis.readUTF();
					input = new ObjectInputStream(dis); 

					//map.put(table, (ArrayList<Order>)input.readObject());
					ser.arrlist(table, (ArrayList<Order>)input.readObject());

				}
				else if(msg.equals("alert")) {
					table = dis.readUTF();
					System.out.println(table + "에서 호출이 되었습니다."); // (읽기) 출력
				}
				else 
					System.out.println(msg); // (읽기) 출력
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Order> data(String a){
		return map.get(a);
	}
}
