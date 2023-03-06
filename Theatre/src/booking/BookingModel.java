package booking;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import dao.Booking;
import dao.Theatre;

public class BookingModel {
	BookingController bookingcontroller;

	public BookingModel(BookingController bookingController) {
		this.bookingcontroller = bookingController;
		VT.add(new Theatre(1,"LAcinema", "DADA", 250, 140));
		VT.add(new Theatre(2,"Sonamena", "Varisu", 50, 120));
		VT.add(new Theatre(3,"MagaStar", "Vaathi", 100, 140));
		VT.add(new Theatre(4,"Inox", "Don", 45, 100));
		VT.add(new Theatre(5,"Rohini", "Manithan", 150, 120));
	}
	public BookingModel(String theatname, String moviename, int ticketno, int ticketrate) {

	}
	ArrayList<Theatre> Bookingdetails = new ArrayList<>();
	ArrayList<Theatre> VT = new ArrayList<>();
	ArrayList<Booking> bookings=new ArrayList<>();
	public void checkaddTheatre(int theatreno,String theatname, String moviename, int ticketno, int ticketrate) {
		if (Bookingdetails.add(new Theatre(theatreno,theatname, moviename, ticketno, ticketrate))) {
			bookingcontroller.addtheatresuccessfully();
		} else
			bookingcontroller.addtheatrefailure();
	}


	public void bookTheatre(int theatreNo, int ticketcount) {
		LinkedHashSet<Object>  booking =new LinkedHashSet<>();
		for(Theatre b:VT) {
			if(b.getTheatreno()==theatreNo)
			{
				if(b.getticketcount()> 0 || ticketcount <= b.getticketcount())
				{
			             b.setTicketcount(b.getticketcount()-ticketcount);
			             bookings.add(new Booking(b.getTheatreno(),b.gettheatname(),ticketcount,b.getticketrate()*ticketcount));
			             booking.add("Theatre No:     "+b.getTheatreno());
			             booking.add("Theatre Name:   "+b.gettheatname());
			             booking.add("Movie Name:     "+b.getmoviename());
			             booking.add("Booked Tickets: "+ticketcount);
			             booking.add("Total Cost:     "+b.getticketrate()*ticketcount);    
			             bookingcontroller.ticketbookingsuccess(booking);       
				}
				else
					bookingcontroller.ticketbookingfailed();
					
			}
			
		}		
		
	}
	
	public void viewTheatre()
	{
		bookingcontroller.viewTheatres(VT);
		
	}
	public void viewBookings()
	{
		bookingcontroller.viewBookings(bookings);
		
	}
	
	
	
	
}
