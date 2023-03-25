package escuela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class Sort2 {
	private ArrayList<Integer> lista;

	public Sort2(ArrayList<Integer> lista) {
		super();
		this.lista = lista;
	}
	public void mostrarLista() {
		System.out.print("[");
		for(int x: lista)
			System.out.print(x+", ");
		System.out.println("]");
	}
	public void ordenaSeleccion() {
		ListIterator<Integer> it1= lista.listIterator();
		int i=1;
		while(it1.hasNext()) {
			int menor=it1.next();
			ListIterator<Integer>it2= lista.listIterator(i);
			while(it2.hasNext()) {
				int aux= it2.next();
				if(menor>aux) {
					it2.set(menor);
					menor=aux;
				}

			}
			it1.set(menor);
			i++;

		}
	}

	//	private void quickSort(int inicio, int last) {
	//		//Ordena usando pivotes
	//		if(inicio<last) {
	//		int pivote=obtenerPivote(inicio, last);
	//		quickSort(inicio, pivote-1);
	//		quickSort(pivote+1, last);
	//		}
	//		
	//	}
	//	private int obtenerPivote(int inicio, int last) {
	//		while(inicio<last) {
	//			while(lista.get(last)>=lista.get(inicio) && inicio<last)
	//				last--;
	//			Collections.swap(lista, inicio, last);
	//			while(lista.get(inicio)<=lista.get(last) && inicio<last)
	//				inicio++;
	//			Collections.swap(lista, inicio, last);
	//		}
	//		return inicio;
	//		
	//	}
	//	public void ordenar() {
	//		quickSort(0, lista.size()-1);
	//	}

	private void quicksort(int inicio, int fnal) {
		if(inicio <fnal ) { 
			//Verificar que la sublista no este vacía 

			int pos_pivote = (inicio + fnal)/2; 
			// Seleccionar como pivote el elemento central

			int valor_pivote = lista.get(pos_pivote); 
			// Para guardar el valor del pivote

			Collections.swap(lista, fnal, pos_pivote); mostrarLista();
			//Intercambiar el pivote con el último elemento 


			int i= inicio, j= fnal-1;
			//Crear dos variables para recorrer

			while(i<=j) {
				// El ciclo se repite mientras las variables no se crucen

				while(i<=j && lista.get(i) <= valor_pivote)
					i++; 
				//La variable i se moverá a la derecha mientras sea menor o igual que el pivote y no se haya cruzado con j

				while(i<=j && lista.get(j) > valor_pivote)
					j--; 
				//La variable j se moverá a la izquierda mientras sea mayor que el pivote y no se haya cruzado con i

				if(i<=j) {
					Collections.swap(lista, i, j); mostrarLista();}
				// Si las variables i y j no se han cruzado se intercambia sus valores
			}
			if(i!=fnal) {
				Collections.swap(lista, i, fnal);mostrarLista();}
			// Se coloca el pivote en el lugar correspondiente de forma que quede ordenado



			quicksort(inicio, i-1);
			//Llamada recursiva para la sublista de elementos menores que el pivote

			quicksort(i+1, fnal);
			//Llamada recursiva para la sublista de elementos mayores que el pivote

		}
	}

	public void QuickSort() {
		quicksort(0, lista.size()-1);
	}

	public int busquedaLineal(int valor) {
		//Busqueda lineal con el uso de iteradores

		boolean encontrado=false;
		int i=0;
		Iterator<Integer> it= lista.iterator();

		while(it.hasNext() && !encontrado)
			if(it.next()==valor)
				encontrado=true;
			else i++;
		return encontrado? i : -1;	
	}

	public boolean isSort() {
		boolean sort=true;
		int i=0;
		while(sort && i<lista.size()-1)
			if(lista.get(i)>lista.get(i+1))
				sort=false;
			else i++;
		return sort;
	}



	public static void main(String[] args) {
		boolean ordenado =true;
		int repeticiones=0;
		Sort2 sort=null;
		ArrayList<Integer> lista=new ArrayList<Integer>();
		for(int i=0; i< 5; i++)
			lista.add((int)(Math.random()*10));
		sort=new Sort2(lista);
		System.out.println("Lista sin ordenar"); sort.mostrarLista();
		sort.QuickSort();


	}

}
