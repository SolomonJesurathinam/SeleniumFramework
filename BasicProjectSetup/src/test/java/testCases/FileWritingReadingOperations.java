package testCases;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;



public class FileWritingReadingOperations {
	
	public static void fileWriterr() throws IOException {
		String location = "screenshots/filewriting.txt";
		String content = "File writing method 1";
		
		FileWriter fileWriter = new FileWriter(location);
		fileWriter.write(content);
		fileWriter.close();
	}

	public static void fileBufferedWriterr() throws IOException {
		String location = "screenshots/bufferedWriter.docx";
		String content = "File writing method 2";
		
		FileWriter fileWriter = new FileWriter(location);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(content);
		bufferedWriter.close();
	}
	
	public static void fileOutputStreamm() throws IOException {
		String location = "screenshots/OutputStream.txt";
		String content = "File writing method 3";
		
		FileOutputStream fileOutputStream = new FileOutputStream(location);
		byte[] text = content.getBytes();
		fileOutputStream.write(text);
		fileOutputStream.close();
	}
	
	public static void WritewithPath() throws IOException {
		String location = "screenshots/path.txt";
		String content = "File writing method 4";
	
		Path path = Paths.get(location);
		Files.write(path, content.getBytes());
	}
	
	public static void LoadImagesandBuffertoWord() throws IOException {
		String location = "screenshots";
		File file = new File(location);
		
		FilenameFilter filenameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File location, String name) {
				if(name.endsWith(".png")){
				return true;
				}
				else {
					return false;
				}
			
			}	
		};
		
		if(file.isDirectory()) {
			for(File f :file.listFiles(filenameFilter)) {
				BufferedImage img = null;
				img = ImageIO.read(f);
				System.out.println(f.getAbsolutePath());
			}	
		}		
	}
	
	public static void main(String[] args) throws IOException {
		fileWriterr();
		fileBufferedWriterr();
		fileOutputStreamm();
		WritewithPath();
		LoadImagesandBuffertoWord();
	}
}
