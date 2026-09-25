package Act2.Modelo.Certificación;
import Act2.Modelo.Estudiante;


public interface Certificable
{
    String ENTIDAD_EMISORA = "Universidad Tecnológica Nacional";
    String generarCertificado(Estudiante estudiante);
}
