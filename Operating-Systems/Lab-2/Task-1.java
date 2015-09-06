//package prva;

public class TwoThreads {
	public static class Thread1 extends Thread {
		public void run() {
			System.out.println("A");
			System.out.println("B");
		}
	}

	public static class Thread2 extends Thread {
		public void run() {
			System.out.println("1");
			System.out.println("2");
		}
	}
	
	public static class ThreadAB implements Runnable {
		private String s1, s2;

		public ThreadAB(String s1, String s2) {
			this.s1 = s1;
			this.s2 = s2;
		}

		@Override
		public void run() {
			System.out.println(s1);
			System.out.println(s2);
		}
	}
	
	public static void main(String[] args) {
		// new Thread1().start();
		// new Thread2().start();
		new Thread(new ThreadAB("A", "B")).start();
		new Thread(new ThreadAB("1", "2")).start();
        //we can not predict the program out correctly!
	}
}
