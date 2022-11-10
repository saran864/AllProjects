package hackerrank;

public class Encryption {

	// op =abc abcdd aaabbbbqqi
public static void main(String[] args) {
		
	String s="a3b4q2i abcd2 abc";
	 
	
	String [] stoChar=s.split(" ");
	for(int i =stoChar.length-1;i>0;i++)
	{
		 s+=stoChar[i];
	}
	
	System.out.println(s);
	
}
	
}
