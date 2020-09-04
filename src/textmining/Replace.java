package textmining;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Replace extends Words {

	protected FileWriter writer = null;
	protected Scanner reader = null;
	protected int c = 0;
	protected int numb = 0;

	public Replace(String f, int n) {
		super(f, n);
		numb = n;
	}

	public void replace(String w1, String w2, String stFile) {

		String line = "";
		int fq = 0;
		String replaceText = "";
		String oldText = "";

		fq = super.frequency(w1);
		if (fq == 0)
			return;
		try {
			// System.out.println("c0");
			reader = new Scanner(f);
			while (reader.hasNext()) {
				line = reader.next();
				oldText += (line + " ");
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("y1");
		}
		
		oldText = oldText.replaceAll(w1, w2);
		replaceText = oldText;
		

		try {
			writer = new FileWriter(stFile);
			writer.write(replaceText);
			writer.close();
		} catch (IOException e) {
			System.err.format("y2");
		}

	}

	public int getCount() {
		return c;
	}

}
