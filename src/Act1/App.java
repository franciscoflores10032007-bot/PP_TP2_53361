package Act1;

import Act1.Excepciones.CupoExcedidoException;
import Act1.Modelo.Actividades.Actividad;
import Act1.Modelo.Estudiante;
import Act1.Modelo.EventoUniversitario;
import Act1.Modelo.Salas;

public class App {

    public static void main(String[] args) {

        String idEvento = "1";
        EventoUniversitario evento1 = new EventoUniversitario(idEvento, "Introducción a la Robótica", 1500.0, false);

        Estudiante estudiante1 = new Estudiante("Jorge Fernandez", "12332");
        Estudiante estudiante2 = new Estudiante("Luisa Diaz", "11234");
        Estudiante estudiante3 = new Estudiante("Bernardo Rojas", "12455");

        Salas salita1 = new Salas(1, "Sala TIC");

        System.out.println(" ");

        evento1.asignarSala(salita1);


        evento1.crearActividad("Act1.Modelo.Actividades.Taller", 1, "Introduccion", 2);
        evento1.crearActividad("Act1.Modelo.Actividades.Charla", 2, "Historia de la Robotica", 15);

        Actividad oAct4 = evento1.getActividad(1);
        Actividad oAct5 = evento1.getActividad(2);

        try {
            oAct4.inscribir(estudiante2);
            System.out.println("Inscripción exitosa: " + estudiante2.getNombre() + " en " + oAct4.getTitulo());

            oAct4.inscribir(estudiante3);
            System.out.println("Inscripción exitosa: " + estudiante3.getNombre() + " en " + oAct4.getTitulo());

            oAct4.inscribir(estudiante1);
            System.out.println("Inscripción exitosa: " + estudiante1.getNombre() + " en " + oAct4.getTitulo());
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir en '" + oAct4.getTitulo() + "': " + e.getMessage());
        }


        EventoUniversitario eventoLeido = null;
        try {
            oAct5.inscribir(estudiante1);
            oAct5.inscribir(estudiante2);
            System.out.println("Inscripción exitosa en '" + oAct5.getTitulo() + "'");

            evento1.persistirEvento();
            eventoLeido = evento1.recuperarEvento(idEvento);

        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir en '" + oAct5.getTitulo() + "': " + e.getMessage());
        } finally {
            System.out.println("\nFlujo de inscripción y persistencia finalizado.");
        }

        System.out.println(" ");
        evento1.mostrarDatos();
        System.out.println(" ");
        System.out.println("Total de eventos registrados: " + EventoUniversitario.getCantidadEventos());

        if (eventoLeido != null) {
            System.out.println("\nEvento recuperado desde archivo:");
            eventoLeido.mostrarDatos();
        }
    }
}