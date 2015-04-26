import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

 class ArrayReader {
	public static IntegerArray readIntegerArray(InputStream input)
	{
		Scanner in=new Scanner(input);
		
		int[] niza=new int[in.nextInt()];
		for(int i=0;i<niza.length;i++)
			niza[i]=in.nextInt();
		in.close();
		
		return new IntegerArray(niza);
	}
}

 final class IntegerArray {
	private final int[] a;
	public IntegerArray()
	{
		a=new int[1];
		a[0]=0;
	}
	public IntegerArray(int a[])
	{
		this.a=new int[a.length];
		for (int i=0;i<a.length;i++)
			this.a[i]=a[i];
	}
	
	public int length()
	{
		return a.length;
	}
	public int getElementAt(int i)
	{
		return a[i];
	}
	public int sum()
	{
		int suma=0;
		
		for (int i=0;i<length();i++)
			suma+=a[i];
		return suma;
	}
	public double average()
	{
		return (double)sum()/length();
	}
	IntegerArray getSorted()
	{
		IntegerArray tmp=new IntegerArray(a);
		Arrays.sort(tmp.a);
		return tmp;
	}
	IntegerArray concat(IntegerArray ia)
	{
		IntegerArray tmp;

		int[] niza=new int[length()+ia.length()];
		
		//for (int i=0;i<length();i++)
		//	niza[i]=a[i];
		niza=Arrays.copyOf(a,length()+ia.length());
		for (int i=0, from=length();i<ia.length();i++, from++)
			niza[from]=ia.a[i];
		tmp=new IntegerArray(niza);
		
		return tmp;
	}
	public String toString()
	{
		String str="[";
		for (int i=0;i<a.length;i++)
			if (i<a.length-1)
				str+=a[i]+", ";
			else
				str+=a[i];
		str+="]";
		
		return str;
	}
	
}

public class IntegerArrayTester {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		String s = jin.nextLine();
		IntegerArray ia = null;
		switch (s) {
			case "testSimpleMethods" : 
				ia = new IntegerArray(generateRandomArray(jin.nextInt()));
				testSimpleMethods(ia);
				break;
			case "testConcatAndEquals" :
				ia = new IntegerArray(new int[]{5,2,3,7});
				IntegerArray same_as_ia1 = new IntegerArray(new int[]{5,2,3,7});
				IntegerArray same_as_ia2 = new IntegerArray(new int[]{5,2,3,7,8});
				IntegerArray same_as_ia3 = new IntegerArray(new int[]{5,2});
				IntegerArray same_as_ia4 = new IntegerArray(new int[]{3,7});
				IntegerArray same_as_ia5 = new IntegerArray(new int[]{2,3,5,7});
				IntegerArray same_as_ia6 = new IntegerArray(new int[]{7,5,3,2});
				IntegerArray same_as_ia7 = same_as_ia3.concat(same_as_ia4);
				IntegerArray same_as_ia8 = same_as_ia4.concat(same_as_ia3);
				if ( ! (ia.equals(ia))&&! (ia.equals(same_as_ia1)) && 
					 ! (same_as_ia1.equals(ia)) && 
					  (ia.equals(same_as_ia2))	&& 
					  (ia.equals(same_as_ia3))	&& 
					  (ia.equals(same_as_ia4))	&& 
					  (ia.equals(same_as_ia5))	&& 
					  (ia.equals(same_as_ia6))	&& 
					  (ia.equals(same_as_ia7))	&& 
					  (ia.equals(null))	&& 
					  (ia.equals(new int[]{5,2,3,7}))	&& 
					  (ia.equals(same_as_ia8))	)
					System.out.println("Your equals or concat method doesn't work properly.");
				else
					System.out.println("Your equals and concat method work great.");
				break;
			case "testReadingAndSorted" :
				String input_s = jin.nextLine()+"\n"+jin.nextLine();
				ia = ArrayReader.readIntegerArray(new ByteArrayInputStream(input_s.getBytes()));
				testSimpleMethods(ia);
				System.out.println(ia.getSorted());
				break;
			case "testImmutability" :
				int a[] = generateRandomArray(jin.nextInt());
				ia = new IntegerArray(a);
				testSimpleMethods(ia);
				testSimpleMethods(ia);
				IntegerArray sorted_ia = ia.getSorted();
				testSimpleMethods(ia);
				testSimpleMethods(sorted_ia);
				sorted_ia.getSorted();
				testSimpleMethods(sorted_ia);
				testSimpleMethods(ia);
				a[0] += 2;
				testSimpleMethods(ia);
				ia = ArrayReader.readIntegerArray(new ByteArrayInputStream(integerArraytoString(ia).getBytes()));
				testSimpleMethods(ia);
				break;
		}
		jin.close();
	}
	
	static void testSimpleMethods(IntegerArray ia) {
		System.out.print(integerArraytoString(ia));
		System.out.println(ia);
		System.out.println(ia.sum());
		System.out.printf("%.2f\n",ia.average());
	}
	
	
	static String integerArraytoString(IntegerArray ia) {
		StringBuilder sb = new StringBuilder();
		sb.append(ia.length()).append('\n');
		for ( int i = 0 ; i < ia.length() ; ++i ) 
			sb.append(ia.getElementAt(i)).append(' ');
		sb.append('\n');
		return sb.toString();
	}
	
	
	static int[] generateRandomArray(int k) {
		Random rnd = new Random(k);
		int n = rnd.nextInt(8)+2;
		int a[] = new int[n];
		for ( int i = 0 ; i < n ; ++i ) {
			a[i] = rnd.nextInt(20)-5;
		}
		return a;
	}

}
