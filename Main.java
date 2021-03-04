import java.io.IOException;

public class Main {

	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		long start = System.currentTimeMillis();
		HashTable<String,String> table = new HashTable<String, String>();
		Read read = new Read();
		read.addHash(table);
		long finish = System.currentTimeMillis();
		System.out.println(" Total Time : " + (double) (finish-start)/1000); // Indexing Time
		System.out.println(HashTable.collusion_counter + " Total Collusion ");
		read.autoSearch(table); //Include Search Tests and Times
		
		
	
	}

}
