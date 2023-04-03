package GritoBaire;

import java.io.Serializable;

public class OperadorSerializable extends Trabajador implements Serializable {
    private static final long serialVersionUID= 1L;
    private Trabajador trabajador;
    private int cantEquiposReparados;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public int getCantEquiposReparados() {
        return cantEquiposReparados;
    }

    public void setCantEquiposReparados(int cantEquiposReparados) {
        this.cantEquiposReparados = cantEquiposReparados;
    }
}
