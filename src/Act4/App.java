package Act4;

import Act4.Excepciones.CupoExcedidoException;
import Act4.Hilos.EnvioTicketsThread;
import Act4.Modelo.Actividades.Actividad;
import Act4.Modelo.Actividades.Charla;
import Act4.Modelo.Actividades.Curso;
import Act4.Modelo.Actividades.Taller;
import Act4.Modelo.Certificación.Certificable;
import Act4.Modelo.Estudiante;
import Act4.Modelo.EventoUniversitario;
import Act4.Modelo.Inscripcion;
import Act4.Modelo.Salas;

import java.util.List;

public class App {

    public static void main(String[] args) throws InterruptedException, CupoExcedidoException {

        Estudiante est1 = new Estudiante("Jorge Fernandez", "12332");
        Estudiante est2 = new Estudiante("Luisa Diaz", "11234");
        Estudiante est3 = new Estudiante("Bernardo Rojas", "12455");

        String idEvento = "EVT-100";
        EventoUniversitario evento1 = new EventoUniversitario(idEvento, "Jornadas de Robótica y Software", 2000.0, false);
        evento1.asignarSala(new Salas(1, "Sala TIC / Laboratorio 3"));

        evento1.crearActividad("Taller", 1, "Introducción a Microcontroladores", 2);
        evento1.crearActividad("Charla", 2, "Historia y Futuro de la IA", 50);
        evento1.crearActividad("Curso", 3, "Desarrollo de Software en Java", 15);

        Actividad taller = evento1.getActividad(1);
        Actividad charla = evento1.getActividad(2);
        Actividad curso = evento1.getActividad(3);

        Inscripcion i1 = taller.inscribir(est1);
        Inscripcion i2 = taller.inscribir(est2);
        try {
            taller.inscribir(est3); // excede el cupo del taller (máximo 2)
        } catch (CupoExcedidoException e) {
            System.out.println("Cupo excedido: " + e.getMessage());
        }

        charla.inscribir(est1);
        Inscripcion i3 = charla.inscribir(est3);

        Inscripcion i4 = curso.inscribir(est2);
        curso.inscribir(est3);

        System.out.println("\n--- Certificados ---");
        for (Actividad act : evento1.getActividades()) {
            if (act instanceof Certificable cert) {
                for (Inscripcion insc : act.getInscripciones()) {
                    System.out.println(cert.generarCertificado(insc.getEstudiante()));
                }
            }
        }

        System.out.println("\n--- Filtrado por tipo ---");
        List<Charla> charlas = evento1.filtrarActividadesPorTipo(Charla.class);
        List<Taller> talleres = evento1.filtrarActividadesPorTipo(Taller.class);
        List<Curso> cursos = evento1.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Charlas: " + charlas.size() + " | Costo materiales: $" + evento1.calcularCostoMateriales(charlas));
        System.out.println("Talleres: " + talleres.size() + " | Costo materiales: $" + evento1.calcularCostoMateriales(talleres));
        System.out.println("Cursos: " + cursos.size() + " | Costo materiales: $" + evento1.calcularCostoMateriales(cursos));

        i1.confirmarInscripcion();
        i3.confirmarInscripcion();
        i4.confirmarInscripcion();

        EnvioTicketsThread hiloEnvio = new EnvioTicketsThread(evento1);
        hiloEnvio.start();


        System.out.println("\n--- [" + Thread.currentThread().getName() + "] Datos del evento ---");
        evento1.mostrarDatos();

        hiloEnvio.join();


        evento1.persistirEvento();
        EventoUniversitario eventoRecuperado = evento1.recuperarEvento(idEvento);
        if (eventoRecuperado != null) {
            System.out.println("\n--- Evento recuperado desde archivo ---");
            eventoRecuperado.mostrarDatos();
        }

        System.out.println("\nTotal de eventos registrados en el sistema: " + EventoUniversitario.getCantidadEventos());
        System.out.println("Programa finalizado.");
    }
}