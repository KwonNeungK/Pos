
public class EventDiscount {
	private int price;
	public EventDiscount(int price){
		this.price = price;
		
	}
	public int returnEvent(){
		if(price>= 10000){
			price = price - 1000;
			
		} else {
			System.out.println("만원 이하입니다.");
		}
		return price;
	}
}
