package io13;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamEx {
	public static void main(String[] args) {
		
		OutputStream os = System.out; 
		byte[] buf = new byte[10]; 
		
		int len = 0; 
		
		buf[len++] = 'A';
		buf[len++] = 'p';
		buf[len++] = 'p';
		buf[len++] = 'l';
		buf[len++] = 'e';
		buf[len++] = '\n';
		
		
		
		buf = "Banana".getBytes();
		
		try {
			
			
			os.write(buf, 0, len);
			
			
			os.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				os.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
