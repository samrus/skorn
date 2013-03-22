package skorn;

import java.io.IOException;

public abstract class ASkFile {
	
	public abstract void open() throws IOException;
	
	public abstract boolean rename(String filepath);
	
	public abstract boolean delete();
	
	public abstract void copy (String filepath) throws IOException;
	
	public abstract void java();
	
}
