package GritoBaire;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public abstract class Trabajador {
    protected String nombre;
    protected String id;
    protected int edad;
    protected String sexo;
    protected int annosExp;
    protected int cantEquiposMax;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getAnnosExp() {
        return annosExp;
    }

    public void setAnnosExp(int annosExp) {
        this.annosExp = annosExp;
    }

    public int getCantEquiposMax() {
        return cantEquiposMax;
    }

    public void setCantEquiposMax(int cantEquiposMax) {
        this.cantEquiposMax = cantEquiposMax;
    }
}
