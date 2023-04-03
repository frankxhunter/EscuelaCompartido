package escuela;



import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.LinkedList;

import ficheros.Convert;


public class Actividad7_Ficheros {
	public static void main(String[] args) {
		Empresa empresa=new Empresa("Guugle", "DatosTrabajadores.txt");
		for(int i=0; i<10; i++) {
			empresa.addTrabajador (new Trabajador("f"+i, "name "+i, i, i * 100),0);
		}
		empresa.addTrabajador(new Trabajador("4242","Frank" , 2, 1000), 10);
		empresa.Imprimir();
		empresa.getFileTrab().delete();
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
	private File fileTrab;
	public Empresa(String name, String nameFile) {
		super();
		this.name = name;
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
			file.writeInt(0);
			file.writeFloat(0);
			file.close();

		}catch(IOException e) {

		}
	}
	
	//Inciso c: Tarea Extraclase
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salida;
	}


	public void addTrabajador(Trabajador worker) {

		try {
			RandomAccessFile file=new RandomAccessFile(fileTrab, "rw");
			int tamName=file.readInt();
			file.skipBytes(tamName);

			long posAux=file.getFilePointer();
			int cantTrabajadores=file.readInt();
			float salarioTotal=file.readFloat();

			boolean encontrado=false;
			int i=0;
			while(i<cantTrabajadores && !encontrado) {
				byte[]arrayTrabajador=new byte[file.readInt()];
				file.read(arrayTrabajador);
				Trabajador x = (Trabajador)Convert.toObject(arrayTrabajador);

				if(x.getNumId().equals(worker.getNumId()))
					encontrado=true;
				i++;
			}
			if(!encontrado) {
				byte[] workerByte=Convert.toBytes(worker);
				file.writeInt(workerByte.length);
				file.write(workerByte); 
				
				file.seek(posAux);
				file.writeInt(cantTrabajadores+1);
				file.writeFloat(salarioTotal+worker.getSalario());
			}
			file.close();

		} catch (Exception e) {
			//Tratamiento de la exception
		}
	}
	//Inciso d: Tarea Extraclase
	public void addTrabajador(Trabajador worker, int pos) {
		try {
			RandomAccessFile file=new RandomAccessFile(fileTrab, "rw");
			int tamName=file.readInt();
			file.skipBytes(tamName);

			long posAux=file.getFilePointer();
			int cantTrabajadores=file.readInt();

			float salarioTotal=file.readFloat();

			boolean encontrado=false;
			int i=0;
			long posInsertar=0;

			while((i<cantTrabajadores)&& !encontrado) {
				if(i==pos)
					posInsertar=file.getFilePointer();

				byte[]arrayTrabajador=new byte[file.readInt()];
				file.read(arrayTrabajador);
				Trabajador x = (Trabajador)Convert.toObject(arrayTrabajador);

				if(x.getNumId().equals(worker.getNumId()))
					encontrado=true;

				i++;
			}
			if(!encontrado){
				byte arrayTrabajadorAgregar[] = Convert.toBytes(worker);

				if(cantTrabajadores <= pos) {
					file.writeInt(arrayTrabajadorAgregar.length);
					file.write(arrayTrabajadorAgregar);
				}else {
					long posFinal = file.getFilePointer();
					long longitudDatoExcedentes = posFinal - posInsertar;
					byte DatosExcendentes[] = new byte[(int) longitudDatoExcedentes];

					file.seek(posInsertar);
					file.read(DatosExcendentes);
					file.seek(posInsertar);

					file.writeInt(arrayTrabajadorAgregar.length);
					file.write(arrayTrabajadorAgregar);
					file.write(DatosExcendentes);
				}
				file.seek(posAux);
				file.writeInt(cantTrabajadores+1);
				file.writeFloat(salarioTotal+worker.getSalario());
			}
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//Fin