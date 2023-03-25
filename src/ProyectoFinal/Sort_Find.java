package ProyectoFinal;

import java.util.ArrayList;
import java.util.HashMap;


public class Sort_Find{
	
	public static  HashMap<String, Letra> contarFrecuenciaCaracteres(String input) {
		String[] caracteres = input.split("");

		HashMap<String, Letra> frecuencias = new HashMap<String, Letra>();

		for (String caracter : caracteres) 
			if (frecuencias.containsKey(caracter)) 
				frecuencias.get(caracter).aumentaFrecuencia();
			 else 
				frecuencias.put(caracter, new Letra(caracter,1));
			
		
		return frecuencias;
	}
	
	public static void mergeSort(ArrayList<Letra> letras, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(letras, left, middle);
            mergeSort(letras, middle + 1, right);
            merge(letras, left, middle, right);
        }
    }

    private static void merge(ArrayList<Letra> letras, int left, int middle, int right) {
        ArrayList<Letra> temp = new ArrayList<>(letras.subList(left, right + 1));

        int i = 0;
        int j = middle - left + 1;
        int k = left;

        while (i <= middle - left && j <= right - left) {
            if (temp.get(i).compareTo(temp.get(j)) <= 0) {
                letras.set(k, temp.get(i));
                i++;
            } else {
                letras.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        while (i <= middle - left) {
            letras.set(k, temp.get(i));
            k++;
            i++;
        }
    }



	public static void main(String[] args) {
	String prueba="Este es una cadena utilizada para saber la frecuencia de aparicion de cada caracter 1234";
	HashMap<String, Letra> mapa=Sort_Find.contarFrecuenciaCaracteres(prueba);
	ArrayList<Letra> listaLetras=new ArrayList<>(mapa.values());
	
	System.out.println(prueba);
	System.out.println("Frecuencia sin ordenar:");
	for(Letra l: listaLetras)
		System.out.print(l.getLetra()+": "+l.getFrecuencia()+"  ");
	System.out.println();
	
	Sort_Find.mergeSort(listaLetras, 0, listaLetras.size()-1);
	
	System.out.println("Frecuencia ordenda y lista para construir el arbol de Huffman:");
	for(Letra l: listaLetras)
		System.out.print(l.getLetra()+": "+l.getFrecuencia()+"  ");

}
}

class Letra implements Comparable<Letra>{
	private String letra;
	private int frecuencia;
	
	public int compareTo(Letra otraLetra) {
        return Integer.compare(this.frecuencia, otraLetra.getFrecuencia());
    }


	public Letra(String letra, int frecuencia) {
		this.setLetra(letra);
		this.setFrecuencia(frecuencia);
	}
	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
	public void aumentaFrecuencia() {
		frecuencia++;
	}
}

