public interface Map {

	/// Hashtable has to have these function 
	
	void Resize();

	void Put(String key, String value) ;

	void Remove(String key) throws Exception;

	boolean Search(String key);
	
	String get(String key);
	
}
