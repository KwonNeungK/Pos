import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = 2478671524360384162L;

	private String menu;  //음식명
	
	private int quantity; //수량
	
	private int price;   //가격
	
	
	public Order(String menu, int quantity, int price) {
		this.menu = menu; 
		this.quantity = quantity;
		this.price = price;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getTotalPrice() {
		return getPrice() * getQuantity();
	}
}