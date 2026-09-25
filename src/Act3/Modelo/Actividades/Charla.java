package Act3.Modelo.Actividades;

public class Charla extends Actividad
{
    private static String disertante = "Jorge Fuentes";

    public Charla(int id, String titulo, int cupoMaximo)
    {
        super(id, titulo, cupoMaximo);
    }

    public static String getDisertante()
    {
        return disertante;
    }

    @Override
    public double calcularCostoMateriales()
    {
        return 5000;
    }

    @Override
    public String getTipo()
    {
        return "Act3.Modelo.Actividades.Charla";
    }
}