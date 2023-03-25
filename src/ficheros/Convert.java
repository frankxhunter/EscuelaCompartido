package ficheros;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Convert {
	public static byte[] toBytes(Object ob) {
		ByteArrayOutputStream baos= new ByteArrayOutputStream();
		ObjectOutputStream conver;
		try {
			 conver= new ObjectOutputStream(baos);
			 conver.writeObject(ob);
			 conver.flush();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return baos.toByteArray(); 
	}
	
	public static Object toObject(byte []array) {
		
		ObjectInputStream ois;
		Object objeto=null;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(array));
	
		 objeto = ois.readObject();
		ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objeto;
	}

}
