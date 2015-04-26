import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLL {
	private class Node {
		int item;
		Node next;

		public Node(int item) {
			this.item = item;
			next = null;
		}
	}

	private class CIterator implements Iterator {

		Node place, curr, prev;

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
		public Object next() {
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

	Node first;

	public SLL() {
		first = null;
	}

	public void insertLast(int item) {
		if (first != null) {
			Node tmp = first;
			while (tmp.next != null)
				tmp = tmp.next;
			Node tmp1 = new Node(item);
			tmp1.next = null;
			tmp.next = tmp1;
		} else {
			Node tmp = new Node(item);
			tmp.next = null;
			first = tmp;
		}
	}

	public SLL joinLists(SLL l) {
		SLL tmp = new SLL();
		Node tmp1 = first;
		Node tmp2 = l.first;
		while (tmp1 != null&&tmp2 != null) {
			if (tmp1.next != null)
				if (tmp1.item == tmp1.next.item) {
					tmp1 = tmp1.next;
					continue;
				}
			if (tmp2.next != null)
				if (tmp2.item == tmp2.next.item) {
					tmp2 = tmp2.next;
					continue;
				}
			if (tmp1.item < tmp2.item) {
				tmp.insertLast(tmp1.item);
				tmp1 = tmp1.next;
			} else if (tmp2.item < tmp1.item) {
				tmp.insertLast(tmp2.item);
				tmp2 = tmp2.next;
			} else {
				// they are equal
				tmp.insertLast(tmp1.item);
				tmp1 = tmp1.next;
				tmp2 = tmp2.next;
			}
		}
		if (tmp1 != null)
			while (tmp1 != null) {
				if (tmp1.next!=null)
					if (tmp1.item==tmp1.next.item)
					{
						tmp1=tmp1.next;
						continue;
					}
				tmp.insertLast(tmp1.item);
				tmp1 = tmp1.next;
			}
		if (tmp2 != null)
			while (tmp2 != null) {
				tmp.insertLast(tmp2.item);
				tmp2 = tmp2.next;
			}
		// remove duplicates
		tmp1 = tmp.first;
		// remove duplicates

		return tmp;
	}

	public void print() {
		Node tmp = first;
		while (tmp != null) {
			System.out.println(tmp.item);
			tmp = tmp.next;
		}
	}

	public Iterator iterator() {
		return new CIterator();
	}
}

public class SLLJoinLists {

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
		spoeni = lista1.joinLists(lista2);
		// spoeni.print();

		Iterator<Integer> it = spoeni.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext())
				System.out.print(" ");
		}
		System.out.println();
	}
}
