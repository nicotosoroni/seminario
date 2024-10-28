import java.util.ArrayList;
import java.util.Scanner;

public class ControladorPiezas {
    private ArrayList<Pieza> piezas; // Lista de piezas
    private ArrayList<Alumno> alumnos; // Lista de alumnos

    public ControladorPiezas(ArrayList<Alumno> alumnos) {
        this.piezas = new ArrayList<>();
        this.alumnos = alumnos; // Inicializa la lista de alumnos
    }

    // Método para registrar una nueva pieza
    public void registrarPieza(int id, String nombre, String nombreAlumno) {
        // Busca el alumno por nombre
        Alumno alumno = buscarAlumnoPorNombre(nombreAlumno);
        if (alumno != null) {
            Pieza nuevaPieza = new Pieza(id, nombre, alumno);
            piezas.add(nuevaPieza);
            System.out.println("Pieza registrada: " + nombre + " para el alumno " + alumno.getNombre());
        } else {
            System.out.println("Alumno no encontrado: " + nombreAlumno);
        }
    }

    // Método para buscar un alumno por su nombre
    private Alumno buscarAlumnoPorNombre(String nombre) {
        for (Alumno alumno : alumnos) {
            if (alumno.getNombre().equalsIgnoreCase(nombre)) {
                return alumno;
            }
        }
        return null; // Retorna null si no se encuentra el alumno
    }

    // Método para actualizar el estado de una pieza
    public void actualizarEstadoPieza(int id) {
        for (Pieza pieza : piezas) {
            if (pieza.getId() == id) {
                System.out.print("Ingrese el nuevo estado para la pieza (moldeado, primer horneado, esmaltado, horneada final): ");
                Scanner scanner = new Scanner(System.in);
                String nuevoEstado = scanner.nextLine();
                pieza.setEstado(nuevoEstado);
                System.out.println("Estado actualizado para la pieza: " + pieza.getNombre() + " a " + nuevoEstado);
                return;
            }
        }
        System.out.println("Pieza no encontrada con ID: " + id);
    }

    // Método para listar piezas por alumno
    public void listarPiezasPorAlumno(String nombreAlumno) {
        System.out.println("Piezas para el alumno " + nombreAlumno + ":");
        for (Pieza pieza : piezas) {
            if (pieza.getAlumno().getNombre().equalsIgnoreCase(nombreAlumno)) {
                System.out.println("ID: " + pieza.getId() + ", Nombre: " + pieza.getNombre() + ", Estado: " + pieza.getEstado());
            }
        }
    }
}
