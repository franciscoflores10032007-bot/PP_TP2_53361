package Act3.Modelo;



import java.io.Serializable;

public class Salas implements Serializable
{


    private int id;
    private String Nombre;

    public Salas(int id, String nombre)
    {
        this.id = id;
        Nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre()
    {
        return Nombre;
    }

    @Override
    public String toString() {
        return "Act3.Modelo.Salas{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}