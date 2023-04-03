package GritoBaire;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

public class Operador extends Trabajador{
    Hashtable<TipoEquipo, LinkedList<Equipo>> listaEquiposPorTipos;
    LinkedList<Equipo> listTerminados;

    public Hashtable<TipoEquipo, LinkedList<Equipo>> getListaEquiposPorTipos() {
        return listaEquiposPorTipos;
    }

    public void setListaEquiposPorTipos(Hashtable<TipoEquipo, LinkedList<Equipo>> listaEquiposPorTipos) {
        this.listaEquiposPorTipos = listaEquiposPorTipos;
    }

    public LinkedList<Equipo> getListTerminados() {
        return listTerminados;
    }

    public void setListTerminados(LinkedList<Equipo> listTerminados) {
        this.listTerminados = listTerminados;
    }
    public int capacidad(){
        int cantequipos=0;
        for(LinkedList<Equipo> x: listaEquiposPorTipos.values())
            cantequipos+=x.size();
        return cantEquiposMax-cantequipos;
    }
    public boolean hasTipoEquipo(TipoEquipo tipoE){
        return getListaEquiposPorTipos().containsKey(tipoE);
    }
    public boolean TieneunodeCadaUno(){
        boolean salida=true;
        Iterator<LinkedList<Equipo>> it =listaEquiposPorTipos.values().iterator();
        while(salida && it.hasNext()){
            if(it.next().size()<1)
                salida=false;
        }
        return salida;
    }
}
