//package vtora;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HW01_2 {

	public static void main(String[] args) throws IOException {
		String dir = "izvor.txt";
		File f = new File(dir);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Character> alc = new ArrayList<>();
		if (fis != null) {
			int r;
			while ((r = fis.read()) != -1)
				alc.add((char) r);

			File fo = new File("destinacija.txt");
			fo.createNewFile();
			FileOutputStream fos = new FileOutputStream(fo);
			for (int i = alc.size() - 1; i >= 0; i--)
				fos.write(alc.get(i));

			fis.close();
			fos.close();
		}
	}

}
