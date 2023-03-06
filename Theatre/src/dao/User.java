package dao;

public class User {
	private String name;
	private byte age;
	private String mail;
	private String pass;
	private char gender;

	

	public String getName() {
		return name;
	}

	public byte getAge() {
		return age;
	}

	public String getMail() {
		return mail;
	}

	public String getPass() {
		return pass;
	}

	public char getGender() {
		return gender;
	}

	public User(String name, byte age, String mail, String pass, char gender) {
		this.name = name;
		this.age = age;
		this.mail = mail;
		this.pass = pass;
		this.gender = gender;
	}

	public User(String mail, String pass) {
		this.mail = mail;
		this.pass = pass;
	}

}
