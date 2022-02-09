package testCases;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class MergeWordFiles {

	public static void copyImage(File source, File destination) {
	try {
		FileUtils.copyFileToDirectory(source, destination);
	} catch (IOException e) {
		System.out.println("File not found");
	}
	}
	
	public static List<String> getFiles() throws IOException {
		String location = "Tempfolder";
		File file = new File(location);
		List<String> documentsPath = new ArrayList<String>();
		if(file.isDirectory()) {
			for(File f :file.listFiles()) {
				System.out.println(f.getAbsolutePath());
				documentsPath.add(f.getAbsolutePath());
			}	
	}
		return documentsPath;
	}
	public static void merge() throws IOException {
		List<String> Path = new ArrayList<String>(getFiles());
		
	}
	
	public static void main(String [] args) throws IOException {
		//While generating the word document, use this code to make a copy of word document in a seperate folder.
		copyImage(new File("screenshots/bufferedWriter.docx"), new File("Tempfolder/")); //you can use the same path which you give when creating the word document for source parameter
		copyImage(new File("screenshots/testing.docx"), new File("Tempfolder/"));
		merge();
	
	}
}
