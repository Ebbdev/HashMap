
public class SingleLinkedList {
	Node head = null;
	String key = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SingleLinkedList(String key) {
		this.key = key;
	}

	//Special add function for HashTable
	public void add(String value) {
		Node temp = new Node(value);
		if (search(value) == -1) {
			if (head == null) {
				head = temp;
			} else {
				Node nextNode = head;
				while (nextNode.getNext() != null) {
					nextNode = nextNode.getNext();
				}
				nextNode.setNext(temp);
			}
		} else {
			get_object_index(search(value)).increasecount(); // If Hashtable has the same value, this function is called
		}
	}

	public String display() {
		Node temp = head;
		String output = "";
		int count = 0;
		while (temp != null) {
			count += temp.getNumber();
			output += temp.getData() + " Number : " + temp.getNumber() + "|" + "\n";
			temp = temp.getNext();
		}
		System.out.println("Total : " + count);
		return output;
	}

	public int size() {
		Node temp = head;
		int size_number = 0;
		while (temp != null) {
			size_number++;
			temp = temp.getNext();
		}
		return size_number;
	}

	public int search(String value) {
		if (head == null) {
			return -1;
		}
		Node temp = head;
		int count = 0;
		if (temp.getData().equals(value)) {
			return 0;
		}
		while (temp.getNext() != null) {
			if (temp.getData().equals(value) == true) {
				return count;
			}
			count++;
			temp = temp.getNext();
		}
		if (temp.getData().equals(value) == true) {
			return count;
		}
		return -1;

	}

	public Node get_object_index(int index_number) {
		int counter_index = 0;
		Node temp = head;
		if (index_number > size()) {
			return null;
		}
		if (index_number == 0) {
			return temp;
		}
		while (counter_index < index_number) {
			temp = temp.getNext();
			counter_index++;
		}
		return temp;
	}

	public String remove(String input) {
		if (head == null) {
			System.out.println("SLL is empty !");
			return null;
		} else if (head.getData().equals(input) == true) {
			if (size() == 1) {
				String remove_string = head.getData();
				head = null;
				return remove_string;
			} else {
				String remove_string = head.getData();
				head = head.getNext();
				return remove_string;
			}
		} else {
			Node prev = head;
			Node temp = prev.getNext();

			while (temp != null) {
				if (temp.getData().equals(input)) {
					prev.setNext(temp.getNext());
					return temp.getData();
				}
				prev = temp;
				temp = temp.getNext();
			}
			System.out.println("Element could not be found ");
			return null;
		}
	}
}
