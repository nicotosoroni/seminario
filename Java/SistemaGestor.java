import java.util.ArrayList;
import java.util.List;

public class SistemaGestor {
    private List<Alumno> alumnos;
    private List<Pieza> piezas;

    public SistemaGestor() {
        this.alumnos = new ArrayList<>();
        this.piezas = new ArrayList<>();
    }

    // Método para registrar piezas
    public void registrarPieza(int id, String nombre, Alumno alumno) {
        Pieza nuevaPieza = new Pieza(id, nombre, alumno);
        piezas.add(nuevaPieza);
        System.out.println("Pieza registrada: " + nombre + " para el alumno " + alumno.getNombre());
    }

    // Método para obtener piezas por alumno
    public List<Pieza> getPiezasPorAlumno(Alumno alumno) {
        List<Pieza> piezasAlumno = new ArrayList<>();
        for (Pieza pieza : piezas) {
            if (pieza.getAlumno().equals(alumno)) {
                piezasAlumno.add(pieza);
            }
        }
        return piezasAlumno;
    }

    // Método registrar alumno
    public void registrarAlumno(int id, String nombre, String email) {
        Alumno alumno = new Alumno(id, nombre, email);
        alumnos.add(alumno);
        System.out.println("Alumno registrado correctamente.");
    }

    // Método para obtener alumnos
    public List<Alumno> getAlumnos() {
        return alumnos;
    }
}
