import java.util.ArrayList;
import java.util.Scanner;

public class ControladorPiezas {
    private ArrayList<Pieza> piezas; // Lista de piezas
    private ArrayList<Alumno> alumnos; // Lista de alumnos

    public ControladorPiezas(ArrayList<Alumno> alumnos) {
        this.piezas = new ArrayList<>();
        this.alumnos = alumnos; // Inicializa la lista de alumnos
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

}
