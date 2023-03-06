package dao;

public class Booking {
	private int theatreno;
	private String theatrename;
	private int ticketcount;
	private int totalcost;
	
	public int getTheatreno() {
		return theatreno;
	}

	public String getTheatrename() {
		return theatrename;
	}

	public int getTicketcount() {
		return ticketcount;
	}

	public int getTotalcost() {
		return totalcost;
	}

	public Booking(int theatreno, String theatrename, int ticketcount, int totalcost) {
		super();
		this.theatreno = theatreno;
		this.theatrename = theatrename;
		this.ticketcount = ticketcount;
		this.totalcost = totalcost;
	}
	
	

}
