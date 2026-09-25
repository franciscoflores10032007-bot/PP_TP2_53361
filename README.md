Proyecto en Java para la gestión de eventos universitarios, sus actividades (Talleres, Charlas y Cursos), inscripciones de estudiantes, certificados, persistencia y envío de tickets de acceso. Desarrollado de forma incremental a lo largo de 4 actividades de la materia Paradigmas de Programación.


Act4/
├── App.java                             
├── Excepciones/
│   └── CupoExcedidoException.java        
├── Hilos/
│   └── EnvioTicketsThread.java           
└── Modelo/
    ├── Estudiante.java
    ├── Salas.java
    ├── Inscripcion.java                 
    ├── EventoUniversitario.java
    ├── Actividades/
    │   ├── Actividad.java                
    │   ├── Taller.java
    │   ├── Charla.java
    │   └── Curso.java
    └── Certificación/
        └── Certificable.java            




- **Actividad 1** — Modelo base: `Estudiante`, `Salas`, `EventoUniversitario` y `Actividad`, con inscripciones, control de cupo (`CupoExcedidoException`) y persistencia del evento en disco (`persistirEvento()` / `recuperarEvento()`).
- **Actividad 2** — Herencia y polimorfismo: `Taller`, `Charla` y `Curso` como subtipos de `Actividad`; emisión de certificados mediante la interfaz `Certificable` (Taller y Curso son certificables, Charla no).
- **Actividad 3** — Genéricos y wildcards: `filtrarActividadesPorTipo(Class<T>)` para obtener listas tipadas (`List<Taller>`, `List<Charla>`, `List<Curso>`) y `calcularCostoMateriales(List<? extends Actividad>)` para calcular el costo de materiales de cualquier subtipo de actividad.
- **Actividad 4** — Concurrencia y clases anidadas: `Inscripcion.TicketDeAcceso` como clase anidada miembro (un ticket solo existe dentro de una inscripción confirmada) y `EnvioTicketsThread`, que extiende `Thread` para enviar los tickets en paralelo mientras el hilo principal sigue mostrando información del evento.


- Las clases del modelo implementan `Serializable` para soportar la persistencia del evento (`EventoUniversitario.persistirEvento()`), que genera un archivo `DatosEvento<id>.dat` en el directorio de ejecución.
- `Actividad`, `Taller`, `Charla` y `Curso` usan polimorfismo para `calcularCostoMateriales()` y `getTipo()`.
- `Certificable` se implementa solo en las actividades certificables (Taller, Curso), y se detecta en tiempo de ejecución con `instanceof`.
