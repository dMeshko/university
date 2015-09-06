//package cetvrta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class HW01_4 {

	public static void main(String[] args) throws IOException {
		//String dir = "C:\\Users\\darrko\\Desktop\\os.txt";
		//String word = "darko";
		String dir = args[0];
		String word = args[1];
		File f = new File(dir);
		BufferedReader bfr = null;

		try {
			bfr = new BufferedReader(new FileReader(f));
			int no = 0;
			String tmp;

			while ((tmp = bfr.readLine()) != null) {
				StringTokenizer tkn = new StringTokenizer(tmp);
				while (tkn.hasMoreTokens()) {
					String tmp1 = tkn.nextToken();
					if (tmp1.equals(word))
						no++;
				}
			}

			System.out.printf("Word(%s) occurence in %s found %d times\n",
					word, dir, no);

			bfr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
