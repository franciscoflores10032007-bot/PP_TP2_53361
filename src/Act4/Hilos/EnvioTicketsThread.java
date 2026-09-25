package Act4.Hilos;

import Act4.Modelo.Actividades.Actividad;
import Act4.Modelo.EventoUniversitario;
import Act4.Modelo.Inscripcion;

public class EnvioTicketsThread extends Thread
{
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento)
    {
        super("Hilo-EnvioTickets");
        this.evento = evento;
    }

    @Override
    public void run()
    {
        System.out.println("[" + getName() + "] Enviando tickets...");

        for (Actividad actividad : evento.getActividades()) {
            for (Inscripcion inscripcion : actividad.getInscripciones()) {
                if (inscripcion.getTicket() != null) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        return;
                    }
                    inscripcion.getTicket().enviarTicket();
                }
            }
        }

        System.out.println("[" + getName() + "] Envío finalizado.");
    }
}
