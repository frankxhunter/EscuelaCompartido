package GritoBaire;

import java.util.Date;

public class Asignacion {
    private Operador operador;
    private Equipo equipo;
    private Date fecha;
    private int fechaFinal;

    public Asignacion(Operador operador, Equipo equipo, Date fecha, int fechaFinal) {
        this.operador = operador;
        this.equipo = equipo;
        this.fecha = fecha;
        this.fechaFinal = fechaFinal;
    }

    public Operador getTrabajador() {
        return operador;
    }

    public void setTrabajador(Operador trabajador) {
        this.operador = trabajador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(int fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
