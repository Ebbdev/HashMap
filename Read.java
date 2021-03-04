import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {

	static String temp = "";
	static int folder_counter = 0;

	public static String[] readParallel(String path) throws IOException {
		temp = "";
		File file = new File(path);

		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				temp += sc.nextLine().toLowerCase().replaceAll(Declaration.DELIMITERS, " ") + " ";
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * ConcurrentHashMap<String, LongAdder> wordCounts = new ConcurrentHashMap<>();
		 * 
		 * Path filePath = Paths.get(fileName);
		 * Files.readAllLines(filePath).parallelStream().parallel();
		 * Files.readAllLines(filePath).forEach(word -> { // Use an AtomicAdder to tally
		 * word counts temp += word+" ";// Increment the LongAdder for each instance of
		 * a word });
		 */
		String[] return_list = temp.split(" ");
		return return_list;
	}

	public void autoSearch(HashTable<String, String> table) throws IOException, InterruptedException {
		String[] temp = readParallel("1000.txt");
		double min = 100000;
		double max = -1;
		double avarage = System.currentTimeMillis();
		for (int i = 0; i < temp.length; i++) {
			System.out.println();
			long start = System.currentTimeMillis();
			System.out.println(temp[i] + " \n" + table.get(temp[i].replace(Declaration.DELIMITERS, "").trim().toLowerCase()));
			long finish = System.currentTimeMillis();
			if((double) (finish - start) < min) {
				min = (double) (finish - start) ;
			}
			if((double) (finish - start) > max) {
				max = (double) (finish - start) ;
			}
			
			
		}
		double avaragefinish = System.currentTimeMillis();
		System.out.println("Min time : " + min);
		System.out.println("Max time : " + max);
		System.out.println("Avarage time : " + (avaragefinish-avarage)/1000);
	}

	public static ArrayList<String> findfiles(String path) {
		File directoryPath = new File(path);
		ArrayList<String> list = new ArrayList<String>();
		for (File file : directoryPath.listFiles()) {
			list.add(file.getPath());
		}
		return list;
	}

	public static void addHash(HashTable<String, String> table) throws IOException, InterruptedException {
		Check_Words delete_words = new Check_Words();
		delete_words.Hash();
		ArrayList<String> folder = new ArrayList<String>();
		folder = findfiles("bbc\\");
		ArrayList<String> txt_folders = findfiles(folder.get(folder_counter));
		for (int i = 0; i < txt_folders.size(); i++) {
			String[] words = readParallel(txt_folders.get(i));
			for (int j = 0; j < words.length; j++) {
				if (!Check_Words.delete_words.Search(words[j].replaceAll(Declaration.DELIMITERS, "").trim())) {
					table.Put(words[j].replaceAll(Declaration.DELIMITERS, "").trim().replaceAll("\\p{C}", ""),
							txt_folders.get(i));
				}
			}

		}
		if (folder_counter + 1 < folder.size()) {
			folder_counter++;
			addHash(table);
		}

	}

}
