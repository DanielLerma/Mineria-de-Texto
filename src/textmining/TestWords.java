package textmining;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class TestWords {
	
	public static int getNumbWords(File f) {
		int i = 0;
		String line = "";
		try {
			Scanner input = new Scanner(f);
            while (input.hasNext()) {
            	line = input.next();
                i++;
            }
            input.close();
        }
		catch(Exception ex) {
			System.err.format("X");
		}
		System.out.println(i);
		return i;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\Users\\D-ani\\Desktop\\captmidn.txt");
		int numb = getNumbWords(file);
		
		Words w1 = new Words("C:\\Users\\D-ani\\Desktop\\captmidn.txt", numb);
		w1.copyFile("C:\\Users\\D-ani\\Desktop\\captmidn2.txt");
		System.out.println("Un: " +w1.frequency("Un"));
		System.out.println("robot: " +w1.frequency("robot"));
		
		Replace r1 = new Replace("C:\\\\Users\\\\D-ani\\\\Desktop\\\\captmidn.txt", numb);
		r1.replace("robot", "JP", "C:\\\\Users\\\\D-ani\\\\Desktop\\\\captmidn2.txt");
		System.out.println("JP: " +r1.frequency("JP"));
		
		Remove r2 = new Remove("C:\\\\Users\\\\D-ani\\\\Desktop\\\\captmidn2.txt", numb);
		System.out.println("JP: " +r2.frequency("JP"));
		r2.remove("JP");
		System.out.println("JP: " +r2.frequency("JP"));
		System.out.println("daño: "+r2.frequency("daño"));
		System.out.println("humano: " +r2.frequency("humano"));
		System.out.println("entre: " +r2.frequency("entre"));
		
		Vector<String> v = Words.find('l');
		Words.print(v);
		
		r2.printWords();
		
		r2.getAnalysis();
		
		
		String maxWords[] = r2.getMax();
		for(int i = 0; i < maxWords.length; i++) {
			System.out.println(maxWords[i]);
		}
		
		System.out.println("en:" +r2.frequency("en"));
		System.out.println("la:" +r2.frequency("la"));
		System.out.println("Un:" +r2.frequency("Un"));
		
		
		
	}

}
