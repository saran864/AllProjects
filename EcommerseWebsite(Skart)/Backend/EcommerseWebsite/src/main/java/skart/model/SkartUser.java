package skart.model;


public class SkartUser {


	private String name;
	private int age;
	private String username;
	private String password;
	private String address;
	private String role;
	

	
	public SkartUser(String name, int age, String username, String password, String address,String role) {
		this.name = name;
		this.age = age;
		this.username = username;
		this.password = password;
		this.address = address;
		this.role=role;

	}
	public SkartUser(String username, String password) {

		this.username = username;
		this.password = password;
		
	}
	
	public String getRole() {
		return role;
	}
	
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}
}
