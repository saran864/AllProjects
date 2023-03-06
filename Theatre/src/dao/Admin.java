package dao;

public class Admin {
	private String mail;
	private String password;
	public String getMail() {
		return mail;
	}
	public String getPassword() {
		return password;
	}
	public Admin(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}
	

}