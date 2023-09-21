package skart.model;

public class Products {
 
    String  productName;
    String  productDesc;
    String  productImageUrl ;
    String productCategory;
    int productPrice;
    int productCount;
    byte offer;
    String vendorId;
    
    

	public Products(String productImageUrl, String productName, String productDesc, String productCategory,
			int productPrice, int productCount, String vendorId,byte offer) {
		super();
		this.productImageUrl = productImageUrl;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.vendorId = vendorId;
		this.offer=offer;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public int getProductCount() {
		return productCount;
	}
	public String getVendorId() {
		return vendorId;
	}

	public  byte getOffer() {
		return offer;
	}

   


    
    
    
}
