package escuela;

import java.util.LinkedList;
import java.util.ListIterator;

public class Piramide {
	private LinkedList<Bloque> list;
	

	public Piramide(LinkedList<Bloque> list) {
		super();
		this.list = list;
	}


	public LinkedList<Bloque> getList() {
		return list;
	}
	
	public void add(Bloque x) {
		ListIterator<Bloque> it=list.listIterator();
		boolean encontrado=false;
		int comp=-1;
		while(it.hasNext() && !encontrado) {
			comp=it.next().compareTo(x);
			if(comp!=1)
				encontrado=true;
		}
		if(comp==0)
			it.add(x);
		else {
			if(it.hasPrevious() && encontrado)
				it.previous();
			it.add(x);
		}
			
	}
	public void add2(Bloque x) {
		ListIterator<Bloque> it=list.listIterator();
		boolean encontrado=false;
		while(it.hasNext() && !encontrado) 
			if(it.next().compareTo(x)!=1)
				encontrado=true;
		if(it.hasPrevious() && encontrado)
			it.previous();
		it.add(x);
	}


	public static void main(String[] args) {
		LinkedList<Bloque>list=new LinkedList<Bloque>();
		for(int i=20; i>0; i-=2) 
			list.add(new Bloque(i));
		Piramide piramide= new Piramide(list);
		piramide.add2(new Bloque(18));
		
		for(Bloque x: piramide.getList())
			System.out.print(x.getTam()+" ");

	}

}

class Bloque{
	private int tam;

	public Bloque(int tam) {
		super();
		this.tam = tam;
	}
	
	
	public int getTam() {
		return tam;
	}


	public void setTam(int tam) {
		this.tam = tam;
	}


	public int compareTo(Bloque x) {
		int salida=0;
		if(tam<x.tam)
			salida=-1;
		else if(tam>x.tam)
			salida=1;
		return salida;
	}
}
