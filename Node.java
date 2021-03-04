
public class Node {
	private String data = null;
	private int number = 1;
	private Node next = null;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Node() {

	}

	public void increasecount() {
		setNumber(getNumber() + 1);
	}

	public Node(String data) {
		this.data = data;
		this.number = 1;
	}

	public Node(String data, Node node) {
		this.data = data;
		this.next = node;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
