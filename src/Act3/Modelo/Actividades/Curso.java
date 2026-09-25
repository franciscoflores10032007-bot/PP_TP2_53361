package Act3.Modelo.Actividades;

import Act3.Modelo.Certificación.Certificable;
import Act3.Modelo.Estudiante;

public class Curso extends Actividad implements Certificable
{
    private int nivel;

    public Curso(int id, String titulo, int cupoMaximo)
    {
        super(id, titulo, cupoMaximo);
        this.nivel = 1;
    }

    public Curso(int id, String titulo, int cupoMaximo, int nivel)
    {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    public int getNivel()
    {
        return nivel;
    }

    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }

    @Override
    public double calcularCostoMateriales()
    {
        return 3500.0 * (nivel > 0 ? nivel : 1);
    }

    @Override
    public String getTipo()
    {
        return "Act3.Modelo.Actividades.Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante)
    {
        return "Certificado emitido por [" + ENTIDAD_EMISORA + "]: Se certifica que el estudiante "
                + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo()
                + ") ha aprobado el curso '" + getTitulo() + "' de nivel " + this.nivel + ".";
    }
}