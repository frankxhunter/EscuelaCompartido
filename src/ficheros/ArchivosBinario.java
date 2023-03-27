package ficheros;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class ArchivosBinario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		Estudiante estu= new Estudiante("Frank", 21);
		
		try {
			byte[] array= Convert.toBytes(estu);
			RandomAccessFile fichero= new RandomAccessFile("primerFicherBinario.txt", "rw");
			long cantbytes= fichero.readLong();
			byte[]arrayBytesestudiante= new byte[(int) cantbytes];
			fichero.read(arrayBytesestudiante);
			estu=(Estudiante)Convert.toObject(arrayBytesestudiante);
			System.out.println(estu.getNombre()+ estu.getEdad());
//			fichero.writeLong(array.length);
//			fichero.write(array);
			fichero.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//para abrir el fichero o crearlo
		
		
	}
	
	public void leerEncabezado() {
		try {
			RandomAccessFile ficheCompact= new RandomAccessFile("Documentos.arc", "rw");
			long cantBytesEnc=29;
			byte[] arrayCountFiles= new byte[2];
			
			ficheCompact.read(arrayCountFiles);
			
			Integer count= (Integer)Convert.toObject(arrayCountFiles);
			
			boolean found = false;
			
			while(!found && count!=0) {
				byte[ ]arrayBytesEnc= new byte[(int) cantBytesEnc];
				
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Estudiante implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int edad;
	public Estudiante(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}
