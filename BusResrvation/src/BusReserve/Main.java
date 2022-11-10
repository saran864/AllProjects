package BusReserve;
import java.util.Scanner; //nested package/ hierarchical package
import java.util.ArrayList;
import java.util.HashMap;
public class Main {
	



		public static void main(String[] args) {
			
		ArrayList<Bus> buses =new ArrayList<>();
			HashMap<Integer,ArrayList> bus = new HashMap<Integer,ArrayList>();
			bus.put(1,buses.get(1));
			bus.put(2,buses.get(2));
			bus.put(3,buses.get(3));
			   System.out.println();
			ArrayList<Booking> bookings = new ArrayList<Booking>();
			
			buses.add(new Bus("Ax",2));
			buses.add(new Bus("NonAc",50));
			buses.add(new Bus("Ac",48));
			
			int userOpt = 1;
			Scanner scanner = new Scanner(System.in);
			
			for(Bus b:buses) {
				b.displayBusInfo();
			}
			
			while(userOpt==1) {
				System.out.println("1.Book\n2.Exit");
				userOpt = scanner.nextInt();
				if(userOpt == 1) {
					Booking booking = new Booking();
					if(booking.isAvailable(bookings,buses)) {
						bookings.add(booking);
						System.out.println("Your booking is confirmed");
					}
					else
						System.out.println("Sorry. Bus is full. Try another bus or date.");
				}
			}
		}

	}


