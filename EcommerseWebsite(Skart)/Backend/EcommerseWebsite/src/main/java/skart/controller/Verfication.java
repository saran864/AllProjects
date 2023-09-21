package skart.controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;

import skart.model.Vendor;

public class Verfication {
	
	public String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}
	
	public boolean emailVerification(String toemail,String otp)
	{
		boolean test= false;
		String sendEmail="yoursskart@gmail.com";
		String sendPassword="gwklepyzkkpdklfv";
		String toEmail=toemail;
		try {
		 
			    Properties ps = new Properties();
			    ps.put("mail.smtp.host", "smtp.gmail.com");		
		        ps.put("mail.smtp.auth", "true");
		        ps.put("mail.smtp.port", "587");
		        ps.put("mail.smtp.starttls.enable", "true");
				
			Session session=Session.getInstance(ps,new Authenticator()
					{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(sendEmail,sendPassword);
				}
			}
		);
			//session.setDebug(true);
			Message msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendEmail));
			msg.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail) );	
			msg.setSubject("Skart Email Verification");
			msg.setText("Please verify your skart account using this code: "+otp+".");
			System.out.println("Sending");
			Transport.send(msg);
			System.out.println("Messege Sent successfully");
			test=true;
		}catch( Exception e)
		{
			e.printStackTrace();
		}
		return test;
		
		
	}
	
		
	
	public boolean vendorsEmailVerification(Vendor vendor)
	{
	
		boolean test= false;
		String sendEmail="yoursskart@gmail.com";
		String sendPassword="gwklepyzkkpdklfv";
		String toEmail=vendor.getUserName();
		try {
			
		
			    Properties ps = new Properties();
			    ps.put("mail.smtp.host", "smtp.gmail.com");		
		        ps.put("mail.smtp.auth", "true");
		        ps.put("mail.smtp.port", "587");
		        ps.put("mail.smtp.starttls.enable", "true");
				
			Session session=Session.getInstance(ps,new Authenticator()
					{
				
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(sendEmail,sendPassword);
				}
				
					}
					);
			session.setDebug(true);
			Message msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendEmail));
			msg.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail) );	
			msg.setSubject("Skart VendorRequest");
			msg.setText("Dear "+vendor.getCompanyName()+",\n"
					+ "\n"
					+ "Thank you for your interest in selling your products with our company, SKART. We appreciate your consideration and would like to explore this opportunity further.\n"
					+ "\n"
					+ "Please note that all vendor requests are subject to approval by our administration team. We kindly ask for your patience as we review your application and consider whether your products align with our company's vision and standards.\n"
					+ "\n"
					+ "Once our administration team has reviewed your application, we will send you an email with our response. In the meantime, please feel free to contact us with any questions or concerns you may have.\n"
					+ "\n"
					+ "Thank you for your understanding, and we look forward to potentially working with you.\n"
					+ "\n"
					+ "Best regards,\n"
					+ "[Saran(Admin)]\n"
					+ "SKART");
			System.out.println("Sending");
			Transport.send(msg);
			System.out.println("Messege Send successfully");
			test=true;
		}catch( Exception e)
		{
			e.printStackTrace();
		}
		return test;
		
		
	}
	
	
	
	

	public static boolean vendorMail(JSONObject updatedData) {
		boolean test= false;
		String sendEmail="yoursskart@gmail.com";
		String sendPassword="gwklepyzkkpdklfv";
		String toEmail=updatedData.getString("vendorId");
		
		try {
		 
			    Properties ps = new Properties();
			    ps.put("mail.smtp.host", "smtp.gmail.com");		
		        ps.put("mail.smtp.auth", "true");
		        ps.put("mail.smtp.port", "587");
		        ps.put("mail.smtp.starttls.enable", "true");
				
			Session session=Session.getInstance(ps,new Authenticator()
					{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(sendEmail,sendPassword);
				}
			}
					);
			session.setDebug(true);
			Message msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendEmail));
			msg.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail) );	
			msg.setSubject("Skart VendorRequest ");
			msg.setText(updatedData.getString("mailText"));
			System.out.println("Sending");
			Transport.send(msg);
			System.out.println("Messege Sent successfully");
			test=true;
		}catch( Exception e)
		{
			e.printStackTrace();
		}
		return test;
	}
	

}
