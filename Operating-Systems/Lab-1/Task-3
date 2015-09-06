//package treta;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HW01_3 {

	public static void main(String[] args) throws IOException {
		String dir = "izvor.txt";
		
		File f = new File(dir);
		BufferedInputStream bin = null;
		try {
			bin = new BufferedInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (bin!=null){
			ArrayList<Character> alc = new ArrayList<>();
			int r;
			
			while ((r=bin.read())!=-1)
				alc.add((char)r);
			
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("destinacija.txt")));
			//no need for creating the file..
			Collections.reverse(alc);
			
			for (char c:alc)
				bout.write(c);
			
			bout.close();
			bin.close();
		}
	}

}
