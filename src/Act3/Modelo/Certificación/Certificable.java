package Act3.Modelo.Certificación;

import Act3.Modelo.Estudiante;


public interface Certificable
{
    String ENTIDAD_EMISORA = "Universidad Tecnológica Nacional";
    String generarCertificado(Estudiante estudiante);
}
