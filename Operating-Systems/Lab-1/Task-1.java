//package prva;

import java.io.File;
import java.io.FilenameFilter;

public class HW01_1 {

	public static void main(String[] args) {
		String dir = args[0];
		//String dir = "C:\\Users\\darrko\\Desktop\\slowFood";
		File f = new File(dir);

		File[] tmp = f.listFiles(new DirFilter(".txt"));

		float sum = 0;
		for (File s : tmp)
			sum += s.length();

		System.out.printf(
				"The Average length of all *.txt files in %s is: %.2f bytes\n",
				dir, sum / tmp.length);
	}

}

class DirFilter implements FilenameFilter {
	private String look;

	public DirFilter(String look) {
		this.look = look;
	}

	@Override
	public boolean accept(File arg0, String arg1) {
		String f = new File(arg1).getName();
		return f.indexOf(look) != -1;
	}

}
