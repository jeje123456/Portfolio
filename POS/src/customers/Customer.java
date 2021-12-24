package customers;

public class Customer {
	private String id;
	private String password;
	private int customerNumber;
	private int customerPoint;	
	private static int count;
	
	public Customer(String id, String password, int customerPoint) {
		this.id = id;
		this.password = password;
		this.customerPoint = customerPoint;
		count++;
		customerNumber = count;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCustomerPoint() {
		return customerPoint;
	}

	public void setCustomerPoint(int d) {
		this.customerPoint = d;
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}

	public static int getCount() {
		return count;
	}
	@Override
	public String toString() {
		return  "고객번호 : "+ customerNumber + "고객 전화번호 : " + id + ", 비밀번호 : " + password + "]";
	}	
}
