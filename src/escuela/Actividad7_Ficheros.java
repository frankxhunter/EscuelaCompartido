package escuela;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.LinkedList;

import ficheros.Convert;

public class Actividad7_Ficheros {
	public static void main(String[] args) {
		Empresa empresa=new Empresa("Guugle", 0, 0, "DatosTrabajadores.txt");
		for(int i=0; i<10; i++) {
			empresa.addTrabajador(new Trabajador("f"+i, "name "+i, i, i *100), i);
		}
		//		for(Trabajador x: empresa.obtenerTrabajadores(6))
		//			System.out.println(x.getName());

		empresa.addTrabajador2(new Trabajador("4242","Frank" , 2, 1444), 3);
		empresa.Imprimir();
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
	public Empresa(String name, int cantTrab, float sumSalarios, String nameFile) {
		super();
		this.name = name;
		this.cantTrab = cantTrab;
		this.sumSalarios = sumSalarios;
		this.fileTrab= new File(nameFile);
		createNewFile();
	}

	public File getFileTrab() {
		return fileTrab;
	}

	public void setFileTrab(File fileTrab) {
		this.fileTrab = fileTrab;
	}

	public void Imprimir() {
		RandomAccessFile file;
		try {
			file = new RandomAccessFile(fileTrab, "rw");
			byte[] arraybytes= new byte[file.readInt()];
			file.read(arraybytes);
			String name=(String)Convert.toObject(arraybytes);
			int cantT=file.readInt();
			System.out.println("Nombre:"+name+" --Cantidad de trabajadores: "+cantT+" --Suma de salarios: "+file.readFloat());

			for(int i=0;i<cantT;i++) {
				int pos=file.readInt();
				arraybytes=new byte[pos];
				file.read(arraybytes);
				Trabajador x=(Trabajador)Convert.toObject(arraybytes);
				System.out.println("Trabajador "+i+": "+x.getName());
			}



			file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			for(int i=0; i<cantTrabajadores-1; i++) {
				int bytes=file.readInt();
				byte[]trabadorByte=new byte[bytes];
				file.read(trabadorByte);
				Trabajador x=(Trabajador) Convert.toObject(trabadorByte);
				if(x.getDpto()==departamento && x.getSalario()>500)
					salida.add(x);
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salida;
	}


	public void addTrabajador(Trabajador worker, int pos) {
		cantTrab++;
		sumSalarios+=worker.getSalario();

		try {
			RandomAccessFile file=new RandomAccessFile(fileTrab, "rw");
			int tamName=file.readInt();
			file.skipBytes(tamName);

			long posAux=file.getFilePointer();
			int cantTrabajadores=file.readInt();
			file.seek(posAux);
			file.writeInt(cantTrabajadores+1);

			posAux=file.getFilePointer();
			float salarioTotal=file.readFloat();
			file.seek(posAux);
			file.writeFloat(salarioTotal+worker.getSalario());

			int i=0;
			while(i !=pos || i<cantTrabajadores) {
				int tamWorker=file.readInt();
				file.skipBytes(tamWorker);
				i++;
			}
			posAux=file.getFilePointer();

			int cantbyteExcedentes=(int) (file.length()-posAux);
			file.seek(posAux);
			byte[] datosExcedentes= new byte[cantbyteExcedentes];
			file.read(datosExcedentes);

			file.seek(posAux);
			byte[] workerByte=Convert.toBytes(worker);
			file.writeInt(workerByte.length);
			file.write(workerByte);

			file.write(datosExcedentes);

			file.close();

		} catch (IOException e) {
			//Tratamiento de la exception
		}
	}
	public void addTrabajador2(Trabajador worker, int pos) {
		cantTrab++;
		sumSalarios+=worker.getSalario();

		try {
			RandomAccessFile file=new RandomAccessFile(fileTrab, "rw");
			RandomAccessFile fileAux=new RandomAccessFile("Aux", "rw");
			int tam=file.readInt();
			fileAux.writeInt(tam);
			byte[] arrayByte=new byte[tam];
			file.read(arrayByte);
			fileAux.write(arrayByte);
			fileAux.writeInt(file.readInt()+1);
			fileAux.writeFloat(file.readFloat()+1);
			int i=0;
			while(i<cantTrab) {
				if(i==pos) {
					arrayByte=Convert.toBytes(worker);
					fileAux.writeInt(arrayByte.length);
					fileAux.write(arrayByte);
				}
				else {
					tam = file.readInt();
					arrayByte= new byte[tam];
					file.read(arrayByte);
					file.writeInt(tam);
					file.write(arrayByte);
					i++;
				}
			}
			file.close();
			fileAux.close();


		}catch(Exception e) {
			//Tratamiento de exception
		}
	}


}

//Fin