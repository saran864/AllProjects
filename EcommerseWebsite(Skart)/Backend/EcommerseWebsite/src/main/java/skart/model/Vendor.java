package skart.model;

import java.io.InputStream;

import javax.servlet.http.Part;

public class Vendor {
	String name;
	String companyname;
	int age;
	String username;
	String password;
	String address;
	String role;
	InputStream file;

	public Vendor(String name, String  company_name, int age, String username, String password,String address, String role,InputStream file) {
		this.name = name;
		this.companyname = company_name;
		this.age = age;
		this.username = username;
		this.password = password;
		this.address = address;
		this.role=role;
		this.file=file;
	}

	public String getName() {
		return name;
	}

	public String getCompanyName() {
		return companyname;
	}

	public int getAge() {
		return age;
	}

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public InputStream getFile() {
		return file;
	}


	public String getAddress() {
		return address;
	}

	public String getRole() {
		return role;
	}

}
