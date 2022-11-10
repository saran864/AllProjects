
import java.io.*;
import java.util.PriorityQueue;
	

public class FileAssignment {

		public static void main(String[] args) throws IOException {
	   File file1=new File("/home/school/eclipse-workspace/Bookmyshow/src/Manager.java");
	   File file2=new File("/home/school/eclipse-workspace/Bookmyshow/src/Admin.java");
	   File file3=new File("/home/school/eclipse-workspace/Bookmyshow/src/Customer.java");
	   FileInputStream f1=null;
	   FileInputStream f2=null;
	   FileInputStream f3=null;
	   try {
		  f1=new FileInputStream(file1);
		   f2=new FileInputStream(file2);
		   f3=new FileInputStream(file3);
		   PriorityQueue pq=new PriorityQueue<>();
			 pq.add(f1.available());
			 pq.add(f2.available());
			 pq.add(f3.available());
			 System.out.println(pq);

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	   
		}

	}

