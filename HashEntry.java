
public class HashEntry {
	
	private SingleLinkedList LPHash;

	HashEntry() {
	}

	public HashEntry(String key, String value) {
		LPHash = new SingleLinkedList(key);
		LPHash.add(value);
	}

	public SingleLinkedList getLPHash() {
		return LPHash;
	}

	public void setLPHash(SingleLinkedList lPHash) {
		LPHash = lPHash;
	}

	public String getDisplay() {
		return LPHash.display();
	}

	public void SetKey(String key) {
		LPHash.setKey(key);
	}
}
