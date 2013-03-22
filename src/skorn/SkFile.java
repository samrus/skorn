package skorn;

import java.io.*;
import java.awt.Desktop;

public class SkFile extends ASkFile{
	
	private File file;
	private long size;
	
	public long getSize() { 
		return size;
	}

	public SkFile(String filepath) throws Exception{	
		file = new File(filepath);
		if(file.isFile())
			size = file.length();
		else
			throw new Exception("Not a file");
	}
	
	public void open() throws IOException{	
		try{
			Desktop.getDesktop().open(file);
		}catch(IOException e){
			throw new IOException("Opening file error");
		}
	}
	
	public boolean rename(String filepath){	
		return file.renameTo(new File(filepath));
	}
	
	public boolean delete(){
		return file.delete();
	}
	
	public void copy(String filepath) throws IOException{
		
		try(
			InputStream srcFile = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()),4*1024);
			
			OutputStream destFile = new BufferedOutputStream(
										new FileOutputStream(
											filepath),4*1024);
		){
			byte buf[] = new byte[4*1024];
			
			int count = 0;
			
			while((count = srcFile.read(buf))!=-1){
				destFile.write(buf, 0, count);
			}
			
			destFile.flush();
			
		}
	
	}


	public void java() {	//launch class file with java interpreter
		
		try{
		if(file.getAbsolutePath().contains(".class")){
			
			String javafile = file.getName().substring(0, file.getName().lastIndexOf(".class"));
			String classpath = file.getParent();
			
			Runtime.getRuntime().exec("java -classpath " + classpath + " " + javafile);
				
		}
		}catch(IOException e){
			System.err.println("Failed java execution");
		}
		
	}
	
}
