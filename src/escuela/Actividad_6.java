package escuela;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Actividad_6 {

	LinkedList<Profesor> lista;


	public Actividad_6(LinkedList<Profesor> lista) {
		super();
		this.lista = lista;
	}

	public Profesor obtenerProfesor(String cc, int edad) {
		Profesor x= null;
		Iterator<Profesor> it= lista.iterator();
		while(it.hasNext() && x== null) {
			Profesor y = it.next();
			if(y.getCat().equalsIgnoreCase(cc) && y.getEdad()==edad)
				x=y;
		}
		return x;
	}
	public Profesor obtenerProfesor(String cc, int edad, int i, int j) {
		int medio = (i+j)/2;
		Profesor salida= null;
		ListIterator<Profesor> it=lista.listIterator(medio);
		Profesor valor=it.next();
		if(valor.getCat().contentEquals(cc) && valor.getEdad()==edad)
			salida= valor;
//		else if(valor)
		return salida;
	}
	
	public void ordenaSeleccion() {
		ListIterator<Profesor> it1= lista.listIterator();
		int i=1;
		while(it1.hasNext()) {
			Profesor menor=it1.next();
			ListIterator<Profesor>it2= lista.listIterator(i);
			while(it2.hasNext()) {
				Profesor aux= it2.next();
				if(menor.esMayor(aux)) {
					it2.set(menor);
					menor=aux;
				}
				
			}
			it1.set(menor);
			i++;

		}
	}


	public static void main(String[] args) {


	}

}

class Profesor{
	private String Cat;
	private int edad;
	public String getCat() {
		return Cat;
	}
	public void setCat(String cat) {
		Cat = cat;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public boolean esMayor(Profesor x){
		return true;
	}

}
