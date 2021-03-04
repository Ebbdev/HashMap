
public abstract class HashFunction {

	static int collusion_counter = 0;
	public Boolean PrimeNum(int number) {

		if (number % 2 == 0)
			return false;
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;

	}

	public int LinearProbing(String key, int TableSize, HashEntry[] table) {
		int j = 0;
		int hash = (key.length() * key.charAt(0) + j) % TableSize;
		while (table[hash] != null) {
			if (table[hash].getLPHash().getKey().equals(key)) {
				return hash % TableSize;
			}
			collusion_counter++;
			j++;
			hash = (key.length() * key.charAt(0) + j) % TableSize;
		}
		return hash % TableSize;
	}

	public int DoubleHash(String key, int TableSize, HashEntry[] table) {

		int j = 0;
		int q = 41;
		int key2 = 0;//h(x)
		for (int i = 0; i < key.length(); i++) {
			key2 += Math.pow(key.charAt(i),(i+1))%TableSize;
		}
		int final_hash = (key2 % TableSize + j * (q - key2 % q)) % TableSize;
		while (table[final_hash] != null) {
			if (table[final_hash].getLPHash().getKey().equals(key)) {
				return final_hash % TableSize;
			}
			collusion_counter++;
			j++;
			final_hash = (key2 % TableSize + j * (q - key2 % q)) % TableSize;
		}
		return final_hash % TableSize;
	}

	abstract int SSF(String key);

	abstract int PAF(String key);


}
