
package Cramest.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SalvaFile {
	
	protected String pathFile;
	final protected char separatore = '_';
	
	public SalvaFile(String path) {
		pathFile = path;
	}
	
	public boolean CancellaFile(){
		File file = new File(pathFile);
		if (!file.exists()) {
			return false;
		}
		if(file.delete()){
			return true;
		}else{
			return false;
		}
	}

	public String getPath() {
		return pathFile;
	}

	public boolean salva(int numero) {
		return scriviSuFile(numero);
	}

	public boolean salva(double numero) {
		return scriviSuFile(numero);
	}

	public boolean salva(float numero) {
		return scriviSuFile(numero);
	}

	public boolean salva(String valore) {
		return scriviSuFile(valore);
	}

	protected boolean scriviSuFile(int n) {
		return scriviSuFile(String.valueOf(n));
	}

	protected boolean scriviSuFile(double d) {
		return scriviSuFile(String.valueOf(d));
	}

	protected boolean scriviSuFile(float f) {
		return scriviSuFile(String.valueOf(f));
	}

	protected boolean scriviSuFile(String content) {
		if(new File(pathFile).exists()){
			CaricaFile cf = new CaricaFile(pathFile);
			content = cf.getFile() + content + "\n";
		}
		try {
			File file = new File(pathFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
