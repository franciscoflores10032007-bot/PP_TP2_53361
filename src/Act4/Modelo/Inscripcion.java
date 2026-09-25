package Act4.Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable
{
    private static int contadorTickets = 0;

    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket;

    public Inscripcion(LocalDate fecha, String estado, Estudiante estudiante)
    {
        this.fecha = fecha;
        this.estado = estado;
        this.estudiante = estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public TicketDeAcceso getTicket() {
        return ticket;
    }

    // Confirma la inscripción y genera su ticket de acceso
    public void confirmarInscripcion()
    {
        this.estado = "Confirmada";
        contadorTickets++;
        this.ticket = new TicketDeAcceso("TCK-" + contadorTickets, LocalDate.now());
    }


    public class TicketDeAcceso implements Serializable
    {
        private String idTicket;
        private LocalDate fechaEmision;

        public TicketDeAcceso(String idTicket, LocalDate fechaEmision)
        {
            this.idTicket = idTicket;
            this.fechaEmision = fechaEmision;
        }

        public String getIdTicket() {
            return idTicket;
        }

        public LocalDate getFechaEmision() {
            return fechaEmision;
        }

        public void enviarTicket()
        {
            System.out.println("[" + Thread.currentThread().getName() + "] Ticket " + idTicket
                    + " enviado a " + estudiante.getNombre());
        }
    }
}