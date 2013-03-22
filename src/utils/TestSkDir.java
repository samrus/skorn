package utils;



import skorn.SkDir;

public class TestSkDir {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SkDir dir = new SkDir("/home/alexander/1");
		dir.copy("/home/alexander/2");
		
	}

}
