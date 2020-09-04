package textmining;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Words {
	
	public static String fileWords[];
	public static HashMap<String, Integer> hM = null;
	public static HashMap<String, Integer> hMCopy = null;
	protected static Vector<String> vStr = null;
	protected static String[] fileWNoRep = null;
	
	protected static File f = null;
	protected static File copy = null;
	protected static FileWriter dummyFile = null;
	protected static FileWriter analysisFile = null;
	
	public static String strFile = "";
	
	public int frec = 0;
		
	public Words() {
		
	}
	
	private static String retWord(String w) {
		int puntos = 0;
		for(int i = 0; i < w.length(); i++) {
			if(w.charAt(i) == '.' || w.charAt(i) == ',' || w.charAt(i) == ';')
				puntos++;
		}
		
		if(puntos != 0) {
			char[] word = new char[w.length() - puntos];
		

			
			for(int i = 0; i < w.length() - 1; i++) {
				word[i] = w.charAt(i);
			}
			
			String finalWord = "";
			for(int i = 0; i < word.length; i++) {
				finalWord += word[i];
			}
			
			return finalWord;
		}
		
		return w;
	}
	
	public Words(String file, int n) {
		fileWords = new String[n];
		f = new File(file);
		copy = new File(file);
		hM = new HashMap<>();
		hMCopy = new HashMap<>();
		vStr = new Vector<>();
		
		strFile = file;
		
		String line = "";
		int i = 0;
		try {
			Scanner input = new Scanner(f);
            while (input.hasNext()) {
            	line = input.next();
            	
            	fileWords[i] = retWord(line);         
                i++;
            	
            }            
            input.close();
        }
		catch(Exception ex) {
			System.err.format("X");
		}
	}
	
	protected File getFile() {
		return f;
	}
	
	public String[] getFileWords() {
		return fileWords;
	}
	
	public void setStrFile(String stF) {
		strFile = stF;
	}
	
	public String getStrFile() {
		return strFile;
	}
	
	private void setWord(String w, int f) {
		hM.put(w, f);
		hMCopy.put(w, f);
		frec = f;	
		
	}
	
	public int frequency(String w) {
		int f = 0;
		
		for(int i = 0; i < fileWords.length; i++) {
			if(fileWords[i].equals(w))
				f++;
		}
		
	
		setWord(w, f);
		return f;
	}
	
	public void printWords() {
		
		System.out.println("Estas son las palabras que has buscado: ");
		for (String name: hM.keySet()){
            String key = name.toString();
            String value = hM.get(name).toString();  
            System.out.println(key + " " + value);  
		} 
		System.out.println("\n");

	}
	
	public static Vector<String> find(char x) {

		String line = "";

		for(int i = 0; i < fileWords.length; i++) {
			line = fileWords[i];
			if(line.charAt(0) == x || line.charAt(0) == Character.toUpperCase(x)) {				
				vStr.add(line);
			}
			
		}
		return vStr;
	}
	
	public String[] getMax() {

		int mat[] = {0, 0, 0};
		
		String maxStr[] = new String[3];
		for(int i = 0; i < fileWNoRep.length; i++) {
			
            String key = fileWNoRep[i]; 
            int value = frequency(fileWNoRep[i]);  
            
            if(value > mat[0]) {
            	mat[2] = mat[1];
            	maxStr[2] = maxStr[1];
            	mat[1] = mat[0];
            	maxStr[1] = maxStr[0];
            	mat[0] = value;
            	maxStr[0] = key;
            }
            
            else if(value > mat[1]) {
        		mat[2] = mat[1];
        		maxStr[2] = maxStr[1];
        		mat[1] = value;
        		maxStr[1] = key;
        	}
            
        	else if(value > mat[2]) {
        		mat[2] = value;
        		maxStr[2] = key;
        	}

        }

		return maxStr;
		
	}
	
	private String[] getMax(HashMap<String, Integer> hm) {
		
		for(int i = 0; i < fileWords.length; i++) {
			hm.put(fileWords[i], frequency(fileWords[i]));
		}
		
		HashMap<String, Integer> hcopy = hm;
		
		int mat[] = {0, 0, 0};

		String maxStr[] = new String[3];
		for(int i = 0; i < fileWNoRep.length; i++) {
			
            String key = fileWNoRep[i]; 
            int value = frequency(fileWNoRep[i]);  
            
            if(value > mat[0]) {
            	mat[2] = mat[1];
            	maxStr[2] = maxStr[1];
            	mat[1] = mat[0];
            	maxStr[1] = maxStr[0];
            	mat[0] = value;
            	maxStr[0] = key;
            }
            
            else if(value > mat[1]) {
        		mat[2] = mat[1];
        		maxStr[2] = maxStr[1];
        		mat[1] = value;
        		maxStr[1] = key;
        	}
            
        	else if(value > mat[2]) {
        		mat[2] = value;
        		maxStr[2] = key;
        	}
            
        	hcopy.remove(key);

        }

		return maxStr;
	}
	
	public FileWriter getAnalysis() throws IOException {
		
		analysisFile = new FileWriter("C:\\Users\\D-ani\\Desktop\\Analysis.txt");
		int fr = 0;
		int si=0;
		int let=0;
		int total=0;
		String [] pal1 = new String[fileWords.length];
		for(int i=0;i<pal1.length;i++)
			pal1[i]= "";
		for(int i=0; i < fileWords.length;i++) {
			for(int j=0;j<pal1.length;j++)
				if( pal1[j].compareTo(fileWords[i])==0)
					si=1;
			if(si==0) {
				pal1[let]=fileWords[i];
				let++;
				total++;
				}
			else
				si=0;	
		}
		si=0;
		let=0;
		
		String [] pal =new String[total];
		for(int i=0;i<pal.length;i++)
			pal[i]="";
		for(int i=0;i< fileWords.length;i++) {
			for(int j=0;j<pal.length;j++)
				if(pal[j].compareTo(fileWords[i])==0)
					si=1;
			if(si==0) {
				pal[let]=fileWords[i];
				let++;
			}
			else
				si=0;
		}
		
		fileWNoRep = pal;
		
		analysisFile.write("Lista de las palabras en este documentos y sus apariciones: \n");
		
		for(int i = 0; i < pal.length; i++) {
			if((pal[i].compareTo("de") != 0) && (pal[i].compareTo("el") != 0) && (pal[i].compareTo("él") != 0) && (pal[i].compareTo("ella") != 0) && (pal[i].compareTo("un") != 0) && (pal[i].compareTo("desde") != 0) && (pal[i].compareTo("así") != 0)  && (pal[i].compareTo("una") != 0)) {
				fr = frequency(pal[i]);
				analysisFile.write("La palabra " +pal[i] + " se escribió " + fr + " veces.\n");
			}
		}
		
		analysisFile.write("Las 3 palabras más buscadas en el documento (va de mayor a menor uso): \n");
		
		String max[] = new String[3];
		max = getMax();
		for(int i = 0; i < max.length; i++) {
			analysisFile.write(max[i] + " ");
		}
		
		
		analysisFile.write("\nLas 3 palabras más usadas en el documento (va de mayor a menor uso): \n");
		
		String maxFile[] = new String[3];
		maxFile = getMax(hM);
		for(int i = 0; i < max.length; i++) {
			analysisFile.write(maxFile[i] + " ");
		}
		
		analysisFile.close();
		
		return analysisFile;
		
	}
	
	public FileWriter copyFile(String str) throws IOException {
		
		dummyFile = new FileWriter(str);
		int fr = 0;
		for(int i = 0; i < fileWords.length; i++) {
			fr = frequency(fileWords[i]);
			dummyFile.write(fileWords[i] + " ");
		}

		dummyFile.close();
		
		return dummyFile;
		
	}
	
	
	public static void print(List<?> list) {
		
		for(int i = 0; i < list.size(); i ++) {

			Object object = list.get(i);
			System.out.printf("%2d) %s\n", i, object);
		
		}
	}

}
