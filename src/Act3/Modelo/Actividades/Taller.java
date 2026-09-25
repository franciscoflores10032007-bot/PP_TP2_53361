package Act3.Modelo.Actividades;

import Act3.Modelo.Certificación.Certificable;
import Act3.Modelo.Estudiante;

public class Taller extends Actividad implements Certificable
{
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo)
    {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = false;
    }

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook)
    {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    public boolean isRequiereNotebook()
    {
        return requiereNotebook;
    }

    public void setRequiereNotebook(boolean requiereNotebook)
    {
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales()
    {
        return requiereNotebook ? 5000 : 2000;
    }

    @Override
    public String getTipo()
    {
        return "Act3.Modelo.Actividades.Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante)
    {
        return "Certificado emitido por [" + ENTIDAD_EMISORA + "]: Se certifica que el estudiante "
                + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo()
                + ") completó el taller '" + getTitulo() + "'.";
    }
}