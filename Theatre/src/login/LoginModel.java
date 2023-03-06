package login;

import java.util.ArrayList;

import dao.User;

public class LoginModel {
	private LoginController logincontroller;

	public LoginModel(LoginController loginController) {
		this.logincontroller=loginController;
		
	}
	ArrayList<User>UserCredentials=new ArrayList<>();

	public void checksignup(String name, byte age, String mail, String pass, char gender) {
		if(UserCredentials.add(new User(name,age,mail,pass,gender))) {
			logincontroller.signupsuccessfully();
		}
		else
			logincontroller.signupfailure();
			
		}
		

	public void checkuserlogin(String mail, String pass) {
		
		for(User c : UserCredentials)
		if(c.getMail().equals(mail) && c.getPass().equals(pass)) {
			
			logincontroller.userloginsuccessfully(mail);
		}
		else
			logincontroller.userloginfailure("mail and password wrong!!!");
		
		
	}

	public void checkadminlogin(String mail, String pass) {
		if (mail.equals("Sownd") && pass.equals("2001")) {
			logincontroller.adminLoginSuccess();
		} else
			logincontroller.adminloginFailure("Invalid");
		
		
		
	}
}
