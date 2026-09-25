package Act3.Modelo;

import Act3.Modelo.Actividades.Actividad;
import Act3.Modelo.Actividades.Charla;
import Act3.Modelo.Actividades.Curso;
import Act3.Modelo.Actividades.Taller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable
{
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;
    private Salas sala;
    private List<Actividad> lActividad;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito)
    {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.lActividad = new ArrayList<Actividad>();
        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro)
    {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.lActividad = new ArrayList<Actividad>();
        cantidadEventos++;
    }

    public static int getCantidadEventos()
    {
        return cantidadEventos;
    }

    public double calcularCostoEstimado()
    {
        if (this.gratuito)
        {
            return 0.0;
        }

        double costoActividades = 0.0;
        for (Actividad actividad : this.lActividad)
        {
            costoActividades += actividad.calcularCostoMateriales();
        }

        return (this.costoBase + costoActividades) * 1.21;
    }

    public void asignarSala(Salas sala)
    {
        this.sala = sala;
        System.out.println("Se ha asignado la sala " + sala);
    }

    public Salas getSala()
    {
        return sala;
    }


    public void crearActividad(String tipo, int id, String titulo, int cupo)
    {
        Actividad actividad;

        if (tipo.endsWith("Charla") || tipo.equalsIgnoreCase("Charla"))
        {
            actividad = new Charla(id, titulo, cupo);
        }
        else if (tipo.endsWith("Taller") || tipo.equalsIgnoreCase("Taller"))
        {
            actividad = new Taller(id, titulo, cupo);
        }
        else if (tipo.endsWith("Curso") || tipo.equalsIgnoreCase("Curso"))
        {
            actividad = new Curso(id, titulo, cupo);
        }
        else
        {
            throw new IllegalArgumentException("Tipo de actividad desconocido: " + tipo);
        }

        this.lActividad.add(actividad);
    }

    // Agrega este getter en EventoUniversitario para poder consultar las actividades:
    public List<Actividad> getActividades()
    {
        return this.lActividad;
    }

    public Actividad getActividad(int id)
    {
        for (Actividad act : this.lActividad)
        {
            if (act.getId() == id)
            {
                return act;
            }
        }
        return null;
    }

    public void mostrarDatos()
    {
        System.out.println("El id es: " + id);
        System.out.println(" ");
        System.out.println("El titulo es " + titulo);
        System.out.println(" ");
        System.out.println("El costo base es " + calcularCostoEstimado());
        System.out.println(" ");
        System.out.println("La sala es " + sala.getNombre() + " y su id es " + sala.getId());
        System.out.println(" ");

        for (Actividad actividad : this.lActividad)
        {
            actividad.mostrarIdentificacion();
            System.out.println(" ");
            System.out.println("----------INSCRIPTOS--------");
            System.out.println(" ");
            actividad.mostrarInscripciones();
        }
    }


    public boolean persistirEvento()
    {
        try (FileOutputStream fos = new FileOutputStream("DatosEvento" + this.id + ".dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(this);
            System.out.println("Evento guardado en: DatosEvento" + this.id + ".dat");
            return true;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No se pudo crear o abrir el archivo para persistir el evento: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("Error de entrada/salida al persistir el evento: " + e.getMessage());
        }
        finally
        {
            System.out.println("Proceso de persistencia (grabado) finalizado.");
        }
        return false;
    }


    public EventoUniversitario recuperarEvento(String id)
    {
        EventoUniversitario evento = null;

        try (FileInputStream fis = new FileInputStream("DatosEvento" + id + ".dat");
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            evento = (EventoUniversitario) ois.readObject();
            System.out.println("Evento recuperado correctamente desde DatosEvento" + id + ".dat");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No se encontró el archivo de datos del evento: " + e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("No se pudo reconstruir el objeto leído (clase no encontrada): " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("Error de entrada/salida al leer el evento: " + e.getMessage());
        }
        finally
        {
            System.out.println("Proceso de lectura finalizado.");
        }

        return evento;
    }

    public <T extends Act3.Modelo.Actividades.Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Act3.Modelo.Actividades.Actividad act : this.lActividad) {
            if (tipo.isInstance(act)) {
                resultado.add(tipo.cast(act));
            }
        }
        return resultado;
    }

    public double calcularCostoMateriales(List<? extends Act3.Modelo.Actividades.Actividad> actividades) {
        double costoTotal = 0.0;
        if (actividades != null) {
            for (Act3.Modelo.Actividades.Actividad act : actividades) {
                costoTotal += act.calcularCostoMateriales();
            }
        }
        return costoTotal;
    }
}