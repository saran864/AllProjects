package skart.model;

public class SkartCart {
	int product_id;
	String customer_id;

	public SkartCart(int product_id, String customer_id) {
		this.product_id = product_id;
		this.customer_id = customer_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

}
