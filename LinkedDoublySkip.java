// Name:
// ID:
//=============================
public class LinkedDoublySkip<E> {
	
	//Node inner class
	public static class Node<T> {
		private T data;
		private Node<T> next, prevSkip;
		Node(T data, Node<T> next, Node<T> prevSkip) {
			this.data = data;
			this.next = next;
			this.prevSkip = prevSkip;
		}
		public T getData() { return data;}
		public Node<T> getNext(){ return next; }
		public Node<T> getPrevSkip(){ return prevSkip;}
	}

	private Node<E> head;
	private int size;

	public void add(E object) { // Q1.1 (8 marks)

		if (object==null)
			throw new IllegalStateException("Empty object can't be added!");

	
		//Add your code here
		
		Node<E> temp= new Node<E>(object, null, null);
		if (head == null) {
			temp.prevSkip = null;
			head = temp;
		}
		else if (head.next==null) {
			temp.prevSkip = head;
			head.next = temp;
		}
		else if (head.next.next == null) {
			temp.prevSkip = head;
			head.next.next = temp;
		}
		else {
			Node<E> ptr = head;
			while (true) {
				if (ptr.next == null) {
					temp.prevSkip = ptr.prevSkip.next;
					ptr.next = temp;
					break;
				}
				ptr = ptr.next;
			}
		}
		size++;

	}

	public LinkedDoublySkip() { // Q1.2 (2 marks)
		
		//Add your code here
		head = null;
		size = 0;

	}

	public LinkedDoublySkip(E[] array) { // Q1.3 (5 marks)
		if (array == null)
			throw new NullPointerException("array cannot be null");

		for(E a:array) {
			add(a);
		}
		
		//Add your code here


	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return head == null;
	}

	public E getElementAt(int index) { // Q1.4 (6 marks)
		if (index < 0 || index >=  size)
			throw new IllegalArgumentException("The index parameter is out of range");

		//Add your code here
		Node<E> ptr = head;
		for(int i = 0; i<size;i++) {
			if(i==index) {
				break;
			}
			else {
				ptr = ptr.next;
			}
		}

		return ptr.data; //fix this line once implemented
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if(head==null) return buffer.toString();

		Node<E> p=head;
		while(p.next!=null) {
				buffer.append(p.data);
				p=p.next;
				buffer.append(", ");
			}
		buffer.append(p.data);
		
		return buffer.toString();
	}

	//LinkedDoublyIterator inner class
	private class LinkedDoublyIterator implements Iterator<E> { // Q1.5 (9 marks)

		private Node<E> current;
		boolean res;
		
		// ADD other instance variables here (if needed!!)
 
        public LinkedDoublyIterator() {
        	res = false;
			//Add your code here
        	current = head;
        }

        public E next() {
        	E val = current.data;
        	if(res) {
        		current = current.prevSkip;
        		return val;
        	}
        	
        	if(current.next==null) {
        		current = current.prevSkip;
        		res = true;
        	}
        	else {
        		current = current.next;
        	}
        	return val; //fix this line once implemented
        }

        public boolean hasNext(){
			//Add your code here
        	
        	return current!=null; //fix this line once implemented
		}

	}

	public Iterator<E> iterator() {
		return new LinkedDoublyIterator();
	}
}
