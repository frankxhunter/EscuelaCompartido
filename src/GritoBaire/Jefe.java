package GritoBaire;

import ficheros.Convert;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Jefe {
    private ArrayList<Operador> listaTrabajadores;
    private LinkedList<Asignacion> listaAsiganciones;
    private Queue<Equipo> colaEquiposRotos;
    private Queue<Equipo> colaSinAsignar;
    private File ficheroTrabajadores;


    public LinkedList<Operador> operadoresConTodosLosTipos(){
        LinkedList<Operador> salida=new LinkedList<>();
        for(Operador x: listaTrabajadores){
            if(x.TieneunodeCadaUno())
                salida.add(x);
        }
        return salida;
    }

    public LinkedList<Operador> listaDeTrabajadoresAFull(){
        LinkedList<Operador> salida=new LinkedList<>();
        for (Operador aux : listaTrabajadores) {
            if (aux.capacidad() < 1)
                salida.add(aux);
        }
        return salida;

    }
    public LinkedList<Operador> OperadoredConTodosLosTipos() {
        LinkedList<Operador> salida = new LinkedList<>();
        Iterator<Operador> it= listaTrabajadores.iterator();
        int numC= TipoEquipo.values().length;
        while(it.hasNext()){
            Operador aux= it.next();
            if(aux.listaEquiposPorTipos.size()==numC)
                salida.add(aux);
        }
        return salida;
    }

    public void ActualizarFichero() throws IOException, ClassNotFoundException {
        RandomAccessFile file= new RandomAccessFile(ficheroTrabajadores,"rw");

        int cantT= file.readInt();
        for(int i=0; i<cantT; i++){
            long posicion= file.getFilePointer();

            byte[] arraybyte= new byte[file.readInt()];
            file.read(arraybyte);
            OperadorSerializable opS= (OperadorSerializable)Convert.toObject(arraybyte);

            Operador op= buscarOperador(opS);
            opS.setCantEquiposReparados(opS.getCantEquiposReparados()+op.getListTerminados().size());

            file.seek(posicion);
            arraybyte=Convert.toBytes(opS);
            file.write(arraybyte.length);
            file.write(arraybyte);

            actualizarAsignaciones(op);
        }
        realizarAsignacionesNoAsignadas();
        realizarAsignaciones();
    }
    public void realizarAsignaciones(){
        while(!colaEquiposRotos.isEmpty()){
            Equipo equipo= colaEquiposRotos.poll();
            Operador operador= buscarOperadorMayorCapcidad(equipo.getTipo());
            if(operador!=null) {
                operador.getListaEquiposPorTipos().get(equipo.getTipo()).add(equipo);
                listaAsiganciones.add(new Asignacion(operador, equipo, new Date(), Equipo.cantidadDeDias(equipo)));
            }
            else
                colaSinAsignar.offer(equipo);
        }
    }
    public void realizarAsignacionesNoAsignadas(){
        Queue<Equipo> aux= new ArrayDeque<>();
        while(!colaSinAsignar.isEmpty()){
            Equipo equipo= colaSinAsignar.poll();
            Operador operador= buscarOperadorMayorCapcidad(equipo.getTipo());
            if(operador!=null)
                operador.getListaEquiposPorTipos().get(equipo.getTipo()).add(equipo);
            else
                aux.offer(equipo);
        }
        colaSinAsignar=aux;
    }

    public Operador buscarOperadorMayorCapcidad(TipoEquipo tipoE) {
        Operador salida=null;
        int mayor=0;
        for (Operador aux : listaTrabajadores) {
            if (aux.hasTipoEquipo(tipoE) && aux.capacidad() > mayor) {
                mayor = aux.capacidad();
                salida = aux;
            }
        }
        return salida;

    }
    public void actualizarAsignaciones(Operador op){
        for (Equipo equipo : op.getListTerminados()) {
            boolean encontrado = false;
            Iterator<Asignacion> iterator = listaAsiganciones.iterator();
            while (iterator.hasNext() && !encontrado)
                if (iterator.next().getEquipo().equals(equipo)) {
                    iterator.remove();
                    encontrado = true;
                }
        }
    }

    public Operador buscarOperador(OperadorSerializable op){
        Iterator <Operador> it= listaTrabajadores.iterator();
        Operador salida=null;
        while( salida==null) {
            Operador x = it.next();
            if(x.getId().equals(op.getId()))
                salida=x;
        }
        return salida;
    }


    public ArrayList<Operador> getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(ArrayList<Operador> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }

    public Queue<Equipo> getColaSinAsignar() {
        return colaSinAsignar;
    }

    public void setColaSinAsignar(Queue<Equipo> colaSinAsignar) {
        this.colaSinAsignar = colaSinAsignar;
    }

    public Queue<Equipo> getColaEquiposRotos() {
        return colaEquiposRotos;
    }

    public void setColaEquiposRotos(Queue<Equipo> colaEquiposRotos) {
        this.colaEquiposRotos = colaEquiposRotos;
    }

    public LinkedList<Asignacion> getListaAsiganciones() {
        return listaAsiganciones;
    }

    public void setListaAsiganciones(LinkedList<Asignacion> listaAsiganciones) {
        this.listaAsiganciones = listaAsiganciones;
    }
    public File getFicheroTrabajadores() {
        return ficheroTrabajadores;
    }

    public void setFicheroTrabajadores(File ficheroTrabajadores) {
        this.ficheroTrabajadores = ficheroTrabajadores;
    }
}