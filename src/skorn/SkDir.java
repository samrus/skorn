package skorn;

import java.io.*;

public class SkDir extends ASkDir{
	
	private long size;
	
	public long getSize() {
		return size;
	}

	File dir;
	File[] content;
	

	public SkDir(String dirpath) throws Exception{
		dir = new File(dirpath);
		if((dir!=null) & (dir.isDirectory()) ){
			content = dir.listFiles();
			size = findOutSizeOfDir();
		}else throw new Exception("Not a directory");
	}
	
	private long findOutSizeOfDir() throws Exception{
		long sizeOfDir = 0;
		File[] file = dir.listFiles();
		
			
			for(File f:file){
				if(f.isDirectory()){
					new SkDir(f.getAbsolutePath()).findOutSizeOfDir();
				}else{
					sizeOfDir+=dir.length();
				}	
			}
		return sizeOfDir;
	}
	
	@Override
	public void open() {
		
	}

	@Override
	public boolean rename(String filepath) {
		return dir.renameTo(new File(filepath));
	}

	@Override
	public boolean delete() {
		return dir.delete();
	}

	@Override
	public void copy(String dest) throws Exception { //choose folder where to copy
		
		if(!new File(dest).exists())new File(dest).mkdir();
		
		File[] tempContent = dir.listFiles();
		
		for(File file: tempContent){
			if(file.isDirectory()){

//				System.out.println(file.getParent());
//				System.out.println(file.getParent());
//				System.out.println(dest +File.separator+ 
//								file.getParent().
//									substring(dir.getParent().
//											length()));
				new File(dest +File.separator+ 
								file.getParent().
									substring(dir.getParent().
											length())).mkdir();
				new SkDir(file.getAbsolutePath())
						.copy(dest +File.separator+ 
								file.getParent().
									substring(dir.getParent().
											length()));
			
			}else{
				
				try{
				
					//System.out.println(dest +File.separator+ file.getName());
					if(!new File(dest +File.separator+ dir.getName()).exists())
					new File(dest +File.separator+ dir.getName()).mkdir();
					
					fileCopy(file.getAbsolutePath(), dest +File.separator+ file.getName());
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	private void fileCopy(String filesrc,String filedst) throws IOException{
		
		try(
				InputStream srcFile = new BufferedInputStream(new FileInputStream(filesrc),4*1024);
				
				OutputStream destFile = new BufferedOutputStream(
											new FileOutputStream(
													filedst),4*1024);
			){
				byte buf[] = new byte[4*1024];
				
				int count = 0;
				
				while((count = srcFile.read(buf))!=-1){
					destFile.write(buf, 0, count);
				}
				
				destFile.flush();
				
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
