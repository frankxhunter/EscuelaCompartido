package escuela;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.LinkedList;

import ficheros.Convert;

public class Actividad7_Ficheros {
	public static void main(String[] args) {
		
	}
}

class Trabajador implements Serializable{
	private static final long serialVersionUID = 1L;
	private String numId;
	private String name;
	private int dpto;
	private float salario;
	public Trabajador(String numId, String name, int dpto, float salario) {
		super();
		this.numId = numId;
		this.name = name;
		this.dpto = dpto;
		this.salario = salario;
	}
	public String getNumId() {
		return numId;
	}
	public void setNumId(String numId) {
		this.numId = numId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDpto() {
		return dpto;
	}
	public void setDpto(int dpto) {
		this.dpto = dpto;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
}

class Empresa{
	private String name;
	private int cantTrab;
	private float sumSalarios;
	private File fileTrab;
	public Empresa(String name, int cantTrab, float sumSalarios) {
		super();
		this.name = name;
		this.cantTrab = cantTrab;
		this.sumSalarios = sumSalarios;
	}
	public void createNewFile() {
		try {
			RandomAccessFile file=new RandomAccessFile(fileTrab, "rw");
			byte []byteName= Convert.toBytes(name);
			file.writeInt(byteName.length);
			file.write(byteName);
			file.writeInt(cantTrab);
			file.writeFloat(sumSalarios);
			file.close();
			
		}catch(IOException e) {
			
		}
	}
	public LinkedList<Trabajador> obtenerTrabajadores(int departamento){
		LinkedList<Trabajador> salida= new LinkedList<Trabajador>();
		
		try {
			RandomAccessFile file=new RandomAccessFile(fileTrab, "rw");
			int tamName=file.readInt();
			file.skipBytes(tamName);
			int cantTrabajadores=file.readInt();
			file.readInt();
			for(int i=0; i<cantTrabajadores; i++) {
				int bytes=file.readInt();
				byte[]trabadorByte=new byte[bytes];
				file.read(trabadorByte);
				Trabajador x=(Trabajador) Convert.toObject(trabadorByte);
				if(x.getDpto()==departamento && x.getSalario()>500)
					salida.add(x);
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return salida;
	}
	public void insertarTrabajador(Trabajador worker, int pos) {
		try {
			RandomAccessFile file=new RandomAccessFile(fileTrab, "rw");
			int tamName=file.readInt();
			file.skipBytes(tamName);
			int cantTrabajadores=file.readInt();
			file.readInt();
			int i=0;
			while(i !=pos || i<cantTrabajadores) {
				int tamWorker=file.readInt();
				file.skipBytes(tamWorker);
			}
			long posActual=file.getFilePointer();
			int cantbyteExcedentes=(int) (file.length()-posActual);
			byte[] datosExcedentes= new byte[cantbyteExcedentes];
			
			file.seek(posActual);
			byte workerByte[]=Convert.toBytes(worker);
			file.writeInt(workerByte.length);
			file.write(workerByte);
			
			file.write(datosExcedentes);
			
			file.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}