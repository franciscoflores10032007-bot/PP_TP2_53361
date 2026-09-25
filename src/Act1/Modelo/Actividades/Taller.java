package Act1.Modelo.Actividades;

public class Taller extends Actividad
{
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo)
    {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = false;
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
        return "Act1.Modelo.Actividades.Taller";
    }
}