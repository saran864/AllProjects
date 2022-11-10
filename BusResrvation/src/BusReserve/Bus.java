package BusReserve;


	public class Bus {
		
		private String ac;
		private int capacity; //get and set
		
		Bus(String ac,int cap){
			this.ac = ac;
			this.capacity = cap;
		}
		
		
		public String isAc(){
			return ac;
		}
		public int getCapacity(){ //accessor method
			return capacity;
		}
		
		public void setAc(String val) { //mutators
			ac = val;
		}
		
		public void setCapacity(int cap) { //mutator
			capacity = cap;
		}
		
		public void displayBusInfo(){
			System.out.println( " Ac:" + ac + " Total Capacity: " + capacity);
		}

}
