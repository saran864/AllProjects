package login;

public class LoginController {
	private LoginView loginview;
	private LoginModel loginmodel;

	public LoginController(LoginView loginView) {
		this.loginview=loginView;
		this.loginmodel=new LoginModel(this);		
	}

	public void checksignup(String name, byte age, String mail, String pass, char gender) {
		loginmodel.checksignup(name,age,mail,pass,gender);
		
		
	}

	public void checkuserlogin(String mail, String pass) {
		loginmodel.checkuserlogin(mail,pass);
		
		
	}

	public void checkadminlogin(String mail, String pass) {
		loginmodel.checkadminlogin(mail,pass);
		
		
	}

	public void signupsuccessfully() {
		loginview.signupsuccessfully();
		
	}

	public void signupfailure() {
		loginview.signupsuccessfully();
		
	}

	public void adminLoginSuccess() {
		loginview.adminloginsuccessfully();
		
	}

	public void adminloginFailure(String string) {
		loginview.adminloginfailure();
		
	}

	
	public void userloginsuccessfully(String String) {
		loginview.userloginsuccessfully();
	}

	public void userloginfailure(String string) {
		loginview.userloginfailure();
		
	}

	

}