package Act4.Modelo.Certificación;

import Act4.Modelo.Estudiante;


public interface Certificable
{
    String ENTIDAD_EMISORA = "Universidad Tecnológica Nacional";
    String generarCertificado(Estudiante estudiante);
}
