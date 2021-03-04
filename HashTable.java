
public class HashTable<K, V> extends HashFunction implements Map {

	//private int TABLE_SIZE = 127;//Double hash Table_Sýze must be prime num
	 int TABLE_SIZE = 128;
	private HashEntry[] table;
	private int fill = 0;
	

	public void collusion_number() {
		System.out.println("Total Collusion : " + super.collusion_counter);
	}

	<K, V> HashTable() {
		createTable();
	}
	
	<K, V> HashTable(K key, V value) {
		createTable();
	}

	public void createTable() {
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}

	@Override
	public void Resize() {
		HashEntry[] oldtable = table;
		TABLE_SIZE *= 2;
		while (!super.PrimeNum(TABLE_SIZE)) {
			TABLE_SIZE++;
		}
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < oldtable.length; i++) {
			if (oldtable[i] != null) {
				int hash = SSF(oldtable[i].getLPHash().getKey());
				// int hash = PAF(oldtable[i].getLPHash().getKey());
				if (table[hash] == null) {
					table[hash] = new HashEntry(oldtable[i].getLPHash().getKey(), "");
					table[hash].setLPHash(oldtable[i].getLPHash());
				} else {
					int linear_probing = super.LinearProbing(oldtable[i].getLPHash().getKey(), TABLE_SIZE, table);
					// int double_hashing = super.DoubleHash(key, TABLE_SIZE, table);
					table[linear_probing] = new HashEntry(oldtable[i].getLPHash().getKey(), "");
					table[linear_probing].setLPHash(oldtable[i].getLPHash());
				}

			}
		}
		// System.out.println("Resizing : " + TABLE_SIZE);
	}

	@Override
	public void Put(String key, String value) {

		// Load Factor
		if ((fill / TABLE_SIZE) > 0.8) {
			Resize();
		}

		int hash = SSF(key);
		// int hash = PAF(key);

		if (table[hash] == null) {
			fill++;
			table[hash] = new HashEntry(key, value);
		} else if (table[hash] != null && table[hash].getLPHash().getKey().equals(key)) {
			table[hash].getLPHash().add(value);
		} else if (!table[hash].getLPHash().getKey().equals(key) && table[hash] != null) {
			int linear_probing = super.LinearProbing(key, TABLE_SIZE, table);
			// int double_hashing = super.DoubleHash(key, TABLE_SIZE, table);
			if (table[linear_probing] != null) {
				table[linear_probing].getLPHash().add(value);
			} else {
				fill++;
				table[linear_probing] = new HashEntry(key, value);
			}
		} 
	}

	@Override
	public void Remove(String key)  {
		int hash = SSF(key);
		// int hash = PAF(key);
		try {
			if (table[hash].getLPHash().getKey().equals(key)) {
				table[hash] = null;
				fill--;
			} else {
				int linear_probing = super.LinearProbing(key, TABLE_SIZE, table);
				// int double_hashing = super.DoubleHash(key, TABLE_SIZE, table);
				if (table[linear_probing] != null) {
					table[linear_probing] = null;
					fill--;
				} 
			}
		} catch (Exception e) {
			System.out.println("Remove Error Message !!! ");
		}

	}

	@Override
	public boolean Search(String key) {
		int hash = SSF(key);
		// int hash = PAF(key);
		if (table[hash] == null) {
			return false;
		}
		if (table[hash].getLPHash().getKey().equals(key)) {
			return true;
		} else {
			int linear_probing = super.LinearProbing(key, TABLE_SIZE, table);
			// int double_hashing = super.DoubleHash(key, TABLE_SIZE, table);
			if (table[linear_probing] == null) {
				return false;
			}
		}
		return true;

	}

	@Override
	public String get(String key) {
		int hash = SSF(key);
		// int hash = PAF(key);
		if (table[hash] == null) {
			return null;
		}
		if (table[hash].getLPHash().getKey().equals(key)) {
			return table[hash].getDisplay();
		} else {
			int linear_probing = super.LinearProbing(key, TABLE_SIZE, table);
			// int double_hashing = super.DoubleHash(key, TABLE_SIZE, table);
			if (table[linear_probing] == null) {
				return null;
			}
			return table[linear_probing].getDisplay();
		}
	}

	@Override
	int SSF(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash += key.charAt(i);
		}
		return hash % TABLE_SIZE;
	}

	@Override
	int PAF(String key) {
		int lenght = key.length();
		int hashcode = 0;
		for (int i = 0; i < lenght; i++) {
			hashcode += (key.charAt(i) * (Math.pow(33, lenght - i) % TABLE_SIZE)) % TABLE_SIZE;
		}
		return hashcode % TABLE_SIZE;

	}


}
