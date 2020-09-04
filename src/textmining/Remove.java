package textmining;

public class Remove extends Replace{

	protected String file = "";
	protected String fW[] = null;
	
	public Remove(String f, int n) {
		super(f, n);
		file = f;
	}
	
	public void remove(String w) {
		super.replace(w, "", file);
		int j = 0;
		fW = new String[fileWords.length - frequency(w)];
		for(int i = 0; i < fileWords.length; i++) {
			if(fileWords[i].compareTo(w) != 0) {
				fW[j] = fileWords[i];
				j++;
			}
		}
		fileWords = fW;
		frec = 0;
	}
	
}
