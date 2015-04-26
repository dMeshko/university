import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.*;

public class Array<E> {

	private static class Niza {
		public int[] niza;

		public Niza(int N) {
			niza = new int[N];
			for (int i = 0; i < N; i++)
				niza[i] = new Integer(0);
		}

		public void setElement(int i, int element) {
			niza[i] = element;
		}
	}

	public static int brojDoProsek(Niza niza) {
		// Vashiot kod tuka...
		int sum = 0;
		for (int i = 0; i < niza.niza.length; i++)
			sum = sum + niza.niza[i];
		final double prosek = (double) sum / niza.niza.length;
		double minDist = Math.abs(prosek - niza.niza[0]);
		int kraj = niza.niza[0];
		for (int e : niza.niza) {
			if ((Math.abs(prosek - e) < minDist)
					|| (minDist == Math.abs(prosek - e)&&e < kraj)) {
				minDist = Math.abs(prosek - e);
				kraj = e;
			}
		}

		return kraj;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);

		// Vashiot kod tuka...
		Niza niza = new Niza(N);
		for (int i = 0; i < N; i++)
			niza.setElement(i, Integer.parseInt(stdin.readLine()));
		System.out.println(brojDoProsek(niza));
	}

}
