package booking;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import dao.Booking;
import dao.Theatre;

public class BookingController {
		BookingView bookingview;
		BookingModel bookingmodel;
		

		public BookingController(BookingView bookingView) {
			this.bookingview=bookingView;
			this.bookingmodel=new BookingModel(this);		
		}

	

		public void checkaddTheatre(int theatreno,String theatname, String moviename, int ticketno, int theatrate) {
			bookingmodel.checkaddTheatre(theatreno, theatname,moviename,ticketno,theatrate);
			
		}

		public void addtheatresuccessfully() {
			bookingview.addtheatresuccessfully();		
		}

		public void addtheatrefailure() {
			bookingview.addtheatrefailure();
			
		}

		public void viewTheatres(ArrayList<Theatre> VT) {
			bookingview.viewTheatres(VT);
			
		}
		public void checkTheatre()
		{
			bookingmodel.viewTheatre();
		}

	

		public void ticketbookingsuccess(LinkedHashSet <Object> booking) {
			bookingview.ticketbookingsuccess(booking);
			
		}

		public void ticketbookingfailed() {
			bookingview.ticketbookingfailed();	
		}
		
		public void bookTheatre(int theatreNo,int ticketcount ) {
			bookingmodel.bookTheatre(theatreNo,ticketcount);
			
		}

		public void checkticketBooking() {
			
			bookingmodel.viewBookings();
		}



		public void bookTheatre() {
		
			
		}



		public void viewBookings(ArrayList<Booking> bookings) {
			bookingview.viewBooking(bookings);
			
		}



		public void checkViewTheatre() {
		     bookingmodel.viewBookings();
			
		}
	}


