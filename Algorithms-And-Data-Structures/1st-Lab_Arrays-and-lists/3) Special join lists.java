import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Node {
	int item;
	Node next;

	public Node(int item) {
		this.item = item;
		next = null;
	}
}

class SLL {
	private Node first;

	public SLL() {
		first = null;
	}

	public void insertLast(int item) {
		if (first == null) {
			first = new Node(item);
		} else {
			Node tmp = first;
			while (tmp.next != null)
				tmp = tmp.next;
			tmp.next = new Node(item);
		}
	}

	public Node getFirst() {
		return first;
	}

	// moj metod!
	public void print() {
		Node tmp = first;
		while (tmp != null) {
			System.out.print(tmp.item + " ");
			tmp = tmp.next;
		}
	}

	private class CIterator implements Iterator<Integer> {
		private Node curr, place, prev;

		public CIterator() {
			// TODO Auto-generated constructor stub
			place = first;
			curr = prev = null;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (place != null)
				return true;
			return false;
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			if (place == null)
				throw new NoSuchElementException();
			int item = place.item;
			prev = curr;
			curr = place;
			place = place.next;
			return item;
		}

		@Override
		public void remove() {

		}
	}

	public Iterator iterator() {
		return new CIterator();
	}
}

public class SpecialSLLJoin {

	public static SLL specialJoin(SLL l1, SLL l2) {
		Node tmp1 = l1.getFirst();
		Node tmp2 = l2.getFirst();
		SLL tmp = new SLL();
		boolean flag = true;

		while (tmp1 != null&&tmp2 != null) // go in for 1
		{
			if (flag) // insert from first
			{
				tmp.insertLast(tmp1.item);
				tmp1 = tmp1.next;
				if (tmp1 != null) {
					tmp.insertLast(tmp1.item);
					tmp1 = tmp1.next;
				}
				flag = false;
				// tmp1 = tmp1.next;
			} else {
				tmp.insertLast(tmp2.item);
				tmp2 = tmp2.next;
				if (tmp2 != null) {
					tmp.insertLast(tmp2.item);
					tmp2 = tmp2.next;
				}
				flag = true;
				// tmp2 = tmp2.next;
			}
		}
		if (tmp1 != null)
			while (tmp1 != null) {
				tmp.insertLast(tmp1.item);
				tmp1 = tmp1.next;
			}
		if (tmp2 != null)
			while (tmp2 != null) {
				tmp.insertLast(tmp2.item);
				tmp2 = tmp2.next;
			}

		return tmp;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");

		SLL lista1 = new SLL();
		SLL lista2 = new SLL();

		for (int i = 0; i < N; i++) {
			lista1.insertLast(Integer.parseInt(pomniza[i]));
		}

		s = stdin.readLine();
		N = Integer.parseInt(s);
		s = stdin.readLine();
		pomniza = s.split(" ");
		for (int i = 0; i < N; i++) {
			lista2.insertLast(Integer.parseInt(pomniza[i]));
		}

		SLL spoeni = new SLL();

		spoeni = specialJoin(lista1, lista2);
		//spoeni.print();
		
		Iterator itr = spoeni.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next());
			if (itr.hasNext())
				System.out.print(" ");
		}
		System.out.println();
	}
}
