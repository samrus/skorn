package utils;

import skorn.SkFile;

public class TestSkFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			SkFile file = new SkFile("/home/alexander/Hello.class");
			file.copy("/home/alexander/1/Hello.class");
			//file.java();
			file = new SkFile("/home/alexander/Hello.java");
			file.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
