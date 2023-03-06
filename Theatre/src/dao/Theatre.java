package dao;


	public class Theatre {
		private String theatname;
		private String moviename;
		private int ticketcount;
		private int ticketno;
		private int ticketrate;
		private int theatreno;
		
	
		

		public void setTicketcount(int ticketcount) {
			this.ticketcount = ticketcount;
		}

		public int getTheatreno() {
			return theatreno;
		}

		public void setTicketCount(int theatreno) {
			this.theatreno = theatreno;
		}

	

		public Theatre( int theatreno,String theatname, String moviename, int ticketcount, int ticketrate) {
			this.theatreno=theatreno;
			this.theatname = theatname;
			this.moviename = moviename;
			this.ticketcount = ticketcount;
			this.ticketrate = ticketrate;
		}

		public int getticketno() {
			return ticketno;
		}

		public int getticketrate() {
			return ticketrate;
		}

		public String getmoviename() {
			return moviename;
		}

		public int getticketcount() {
			return ticketcount;
		}

		public String gettheatname() {
			
			return theatname;
		}
		

	
}
