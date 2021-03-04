import java.io.IOException;

public class Check_Words extends Read {

	public static HashTable<String, String> delete_words = new HashTable<String, String>(null, null);

	public HashTable<String, String> getDelete_words() {
		return delete_words;
	}

	public void Hash() throws IOException, InterruptedException {
		String[] temp = super.readParallel("stop_words_en.txt");
		for (int i = 0; i < temp.length; i++) {
			delete_words.Put(temp[i].replaceAll("\\p{C}", ""), temp[i].replaceAll("\\p{C}", "").toLowerCase());
		}
		HashFunction.collusion_counter = 0; // Must be reset collusion number because stop_words_en hashtable's may
		// increases collusion_counter
	}
}
