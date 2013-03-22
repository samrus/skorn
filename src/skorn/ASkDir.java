package skorn;

public abstract class ASkDir {

	public abstract void open();
	
	public abstract boolean rename(String filepath);
	
	public abstract boolean delete();
	
	public abstract void copy(String dest) throws Exception ;
	
}
