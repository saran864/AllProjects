package login;

import java.util.Scanner;

import booking.BookingView;

public class LoginView {
	LoginController logincontroller;
	Scanner scanner = new Scanner(System.in);
	BookingView Bv=new BookingView();
	

	public LoginView() {
		 logincontroller = new LoginController(this);
	}

	public static void main(String[] args) {
		LoginView lv = new LoginView();
		lv.function();
	}

	public void function() {
		boolean setup=true;
		do {
			System.out.println("----------------------------------");
	         System.out.println("Welcome to Book My Show");
		    System.out.println("1.User Signup\n2.User Login\n3.Admin Login\n4.Exit");
		  System.out.println("Enter your chioce");
		int choice=scanner.nextInt();
		switch(choice) {
		case 1:
			signup();
			break;
		case 2:
			userLogin();
			break;
		case 3:
			adminLogin();
			break;
		case 4:
			setup = false;
			break;
		default:
			System.out.println("\n.....Invalid option.....\n");
			break;
		}
		}while(setup);
	}

	private void adminLogin() {
		System.out.println("Enter Email :");
		String mail = scanner.next();
		System.out.println("Enter Password :");
		String pass = scanner.next();
		logincontroller.checkadminlogin(mail, pass);
		
		
	}

	public void userLogin() {
		System.out.println("Enter Email :");
		String mail = scanner.next();
		System.out.println("Enter Password :");
		String pass = scanner.next();
		logincontroller.checkuserlogin(mail, pass);
	}

	public void signup() {
		System.out.println("Enter Name :");
		String name = scanner.next();
		System.out.println("Enter Age :");
		byte age = scanner.nextByte();
		System.out.println("Enter Email :");
		String mail = scanner.next();
		System.out.println("Enter Password :");
		String pass = scanner.next();
		System.out.println("Enter gender M/F :");
		char gender = scanner.next().charAt(0);
		logincontroller.checksignup(name, age, mail, pass, gender);
	}

	public void signupsuccessfully() {
		System.out.println("Successfully Signup");
		Bv.userBookMyShow();
		
	}
	public void signupfailure() {
		System.out.println("Sorry!!Invalid credentials..");
	}

	public void adminloginsuccessfully() {
		System.out.println("Successfully login");
		Bv.adminBookMyShow();
		
	}

	public void adminloginfailure() {
		System.out.println("Sorry!!!Invalid credentials");
		
	}

	public void userloginsuccessfully() {
		System.out.println("User Login Successfully!!!");
		Bv.userBookMyShow();
		
		
	}

	public void userloginfailure() {
		System.out.println("SORRY!!!Invalid credentials.....");	
	}
}
