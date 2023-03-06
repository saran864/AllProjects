package booking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;



import booking.BookingController;
import dao.Booking;
import dao.Theatre;

public class BookingView {

		BookingController bookingcontroller;
		
		Scanner scanner = new Scanner(System.in);
		public BookingView() {
			bookingcontroller = new BookingController(this);
		}

		public static void main(String[] args) {
			BookingView bv = new BookingView();
			bv.adminBookMyShow();
		}

		public void adminBookMyShow() {
			boolean setup = true;
			do {
				System.out.println("\n 1.Add Theatres\n 2.View Theatres \n 3.TicketBookings\n 4.Exit\n");
				System.out.println("Enter your chioce");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					addTheatre();
					break;
				case 2:
					checkTheatre();
					break;
				case 3:
			        checkBooking();
					break;
				case 4:
					setup = false;
					break;
				default:
					System.out.println("\n Choose correct options\n");
					break;

				}
			} while (setup);
		}
		private void checkBooking() {
          bookingcontroller.checkticketBooking();
			
		}

		public  void viewBooking(ArrayList<Booking> bookings) {
			System.out.println("------------------------------------------------------------------");
			for (Booking booking : bookings) {
				
				System.out.println(
						booking.getTheatreno()+" \t"+booking.getTheatrename()+" \t"+booking.getTicketcount()+" \t"+booking.getTotalcost()
			);
			}
			System.out.println("------------------------------------------------------------------");
			
		}

		public void userBookMyShow() {
			boolean setup = true;
			do {

				System.out.println("\n 1.View Theatres \n 2.BookTicket \n 3.Exit\n");
				System.out.println("Enter your chioce");
				int choice = scanner.nextInt();
				switch (choice) {
				
				case 1:
					checkTheatre();
					break;
				case 2:
					bookTicket();
					break;
				case 3:
					setup = false;
					break;
				default:
					System.out.println("\n Choose correct options\n");
					break;

				}
			} while (setup);
		}

		public void viewTheatres(ArrayList<Theatre> VT) {
			System.out.println("------------------------------------------------------------------");
			for (Theatre Vt : VT) {
				
				System.out.println(
						Vt.getTheatreno()+"     "+Vt.gettheatname() + "     " + Vt.getmoviename() + "          \t" + Vt.getticketrate() + "      " + Vt.getticketcount());
			}
			System.out.println("------------------------------------------------------------------");
		}

		private void addTheatre() {
			System.out.println("Enter TheatreNo");
			int theatreno=scanner.nextInt();
			System.out.println("Enter theatre Name : ");
			String theatname = scanner.next();
			System.out.println("Enter Movie Name : ");
			String moviename = scanner.next();
			System.out.println("Enter Ticket  Numberof tickets: ");
			int ticketno = scanner.nextInt();
			System.out.println("Enter Ticket Rate in Rs₹ : ");
			int ticketrate = scanner.nextInt();
			bookingcontroller.checkaddTheatre(theatreno,theatname, moviename, ticketno, ticketrate);

		}
		private void bookTicket() {
			System.out.println("Enter TheatreNo");
			int theatreno=scanner.nextInt();
			System.out.println("Enter Ticket  Numberof tickets: ");
			int theatrecount=scanner.nextInt();
			bookingcontroller.bookTheatre(theatreno, theatrecount);
			
		}

		public void addtheatresuccessfully() {
			System.out.println("Theatre added Successfully");

		}
		public void addtheatrefailure() {
			System.out.println("Sorry!!addedFailed");

		}
		private void checkTheatre() {
			bookingcontroller.checkTheatre();
		}
		public void ticketbookingsuccess(LinkedHashSet <Object> booking) {
			System.out.println("Ticket booked Successfull");
			System.out.println("---------------------------");
			Iterator<Object> it = booking.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
				
			}
			System.out.println("---------------------------");
			
		}
		public void ticketbookingfailed() {
			System.out.println("Sorry!!!...Ticket not available....");
			
			
		}
}
