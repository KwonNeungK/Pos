
public class MemberDis{
	private int price;

	public MemberDis(int price){
		this.price = price;

	}
	public int returnMember(){
		price = price - (price/10);

		return price;
	}
}
