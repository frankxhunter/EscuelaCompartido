package ParaElPArcial;

import java.util.*;

public class AgenciaDeVuelos {
    private ArrayList<Vuelo> listaVuelos;
    private Queue<Solicitud> listaSolicitudes;
    private Queue<Solicitud> solicitudesPendientes;

    public void AtenderSolicitudes(){
        while(!listaSolicitudes.isEmpty()){
            Solicitud solicitud= listaSolicitudes.poll();
            boolean asignado = false;
            Iterator<Vuelo> it= listaVuelos.iterator();
            while(it.hasNext() && !asignado)
                asignado = it.next().asignarSiTieneCapacidad(solicitud);
            if(!asignado)
                solicitudesPendientes.offer(solicitud);
        }
    }
    public LinkedList<Reservacion> reasignarVueloSiEsPosible(Vuelo vuelo){
        LinkedList<Reservacion> noAsignados= new  LinkedList<Reservacion>();
        Iterator<DiasSemana> it=vuelo.getListaRessevaciones().keys().asIterator();
        while(it.hasNext()){
            DiasSemana dia= it.next();
            Iterator<Reservacion> iterator= vuelo.getListaRessevaciones().get(dia).iterator();
            while(iterator.hasNext()) {
                Reservacion reservacion= iterator.next();
                Iterator<Vuelo> iterator1 = listaVuelos.iterator();
                boolean asignado = false;
                while(iterator1.hasNext() && !asignado)
                    asignado= iterator1.next().asignarSiTieneCapacidad(reservacion,vuelo.getDestino(),dia);
                if(!asignado)
                    noAsignados.add(reservacion);
                iterator.remove();
            }
        }
        return noAsignados;
    }
 }

class Vuelo{
    private String id;
    private int capacidad;
    private String destino;
    private Hashtable<DiasSemana, ArrayList<Reservacion>> listaRessevaciones;

    public String getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getDestino() {
        return destino;
    }

    public boolean asignarSiTieneCapacidad(Solicitud solicitud){
        boolean salida = false;
        if(listaRessevaciones.containsKey(solicitud.getDia())
                && solicitud.getDestino().equalsIgnoreCase(destino)){
            ArrayList<Reservacion> listaReservacionesDia= listaRessevaciones.get(solicitud.getDia());
            if(listaReservacionesDia.size()<capacidad){
                salida=true;
                Reservacion reservacion= new Reservacion(solicitud.getPasaporte(),
                        solicitud.getNombre(),listaReservacionesDia.size()+1 );
                listaReservacionesDia.add(reservacion);
            }
        }
        return salida;
    }
    public boolean asignarSiTieneCapacidad(Reservacion reservacion, String destino, DiasSemana dia){
        boolean salida = false;
        if(listaRessevaciones.containsKey(dia)
                && destino.equalsIgnoreCase(destino)){
            ArrayList<Reservacion> listaReservacionesDia= listaRessevaciones.get(dia);
            if(listaReservacionesDia.size()<capacidad){
                salida=true;
                listaReservacionesDia.add(reservacion);
            }
        }
        return salida;
    }

    public Hashtable<DiasSemana, ArrayList<Reservacion>> getListaRessevaciones() {
        return listaRessevaciones;
    }
}

class Reservacion{
    private String pasaporte;
    private String nombre;
    private int numAsiento;

    public Reservacion(String pasaporte, String nombre, int numAsiento) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.numAsiento = numAsiento;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }
}

class Solicitud{
    private String pasaporte;
    private String nombre;
    private String destino;
    private DiasSemana dia;

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public DiasSemana getDia() {
        return dia;
    }

    public void setDia(DiasSemana dia) {
        this.dia = dia;
    }
}

enum DiasSemana{Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo}
