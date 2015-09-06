//package cetvrta;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class CountThree {
	public static int NUM_RUNS = 100;
	/**
	 * Global variable which has to contain the total number of occurrences of 3
	 */
	int count = 0;
	/**
	 * TODO: define the elements necessary for the scenario
	 */
	Semaphore s;

	public void init() {
		// implement this
		s = new Semaphore(1);
	}

	class Counter extends Thread {

		public /*synchronized*/ void count(int[] data) throws InterruptedException {
			// implement this
			for (int i = 0; i < data.length; i++) {
				//synchronized (Counter.class) {
					s.acquire();
					if (data[i] == 3)
						count++;
					s.release();
				//}
			}

		}

		private int[] data;

		public Counter(int[] data) {
			this.data = data;
		}

		@Override
		public void run() {
			try {
				count(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			CountThree environment = new CountThree();
			environment.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void start() throws Exception {

		init();

		HashSet<Thread> threads = new HashSet<Thread>();
		Scanner s = new Scanner(System.in);
		int total = s.nextInt();
		
		Random rnd = new Random();
		
		for (int i = 0; i < NUM_RUNS; i++) {
			int[] data = new int[total];
			for (int j = 0; j < total; j++) {
				//data[j] = s.nextInt();
				data[j] = 1 + rnd.nextInt(10);
			}
			Counter c = new Counter(data);
			threads.add(c);
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
		System.out.println(count);

		s.close();
	}
}
