package skart.model;

public class SkartOrder {
	String customer_id;
	int product_id;
	int quantity;
	String order_date;
	
	public SkartOrder(String customer_id, int product_id, int quantity,  String order_date) {
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.order_date = order_date;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public int getQuantity() {
		return quantity;
	}


	
	public String getOrder_date() {
		return order_date;
	}


}
