package Act4.Modelo;

import java.io.Serializable;

public class Estudiante implements Serializable
{

    private  String Nombre;

    private  String Legajo;

    public Estudiante(String nombre, String legajo) {
        Nombre = nombre;
        Legajo = legajo;
    }

    public  String getLegajo() {
        return Legajo;
    }

    public  String getNombre() {
        return Nombre;
    }

}
