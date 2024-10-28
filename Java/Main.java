import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaGestor gestor = new SistemaGestor();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Listar Alumnos");
            System.out.println("3. Registrar Pieza");
            System.out.println("4. Listar Piezas");
            System.out.println("5. Listar Piezas por Alumno");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del alumno: ");
                    int idAlumno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese nombre del alumno: ");
                    String nombreAlumno = scanner.nextLine();
                    System.out.print("Ingrese email del alumno: ");
                    String emailAlumno = scanner.nextLine();
                    gestor.registrarAlumno(idAlumno, nombreAlumno, emailAlumno);
                    break;
                case 2:
                    // Listar alumnos utilizando el método getAlumnos
                    List<Alumno> alumnos = gestor.getAlumnos();
                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    } else {
                        System.out.println("Lista de Alumnos:");
                        for (Alumno alumno : alumnos) {
                            System.out.println("- " + alumno.getNombre() + " (ID: " + alumno.getId() + ")");
                        }
                    }
                    break;
                case 3:
                    List<Alumno> listaAlumnos = gestor.getAlumnos();
                    if (listaAlumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                        break;
                    }
                    System.out.print("Ingrese ID de la pieza: ");
                    int idPieza = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese nombre de la pieza: ");
                    String nombrePieza = scanner.nextLine();

                    // Mostrar los alumnos para seleccionar
                    System.out.println("Seleccione un alumno:");
                    for (int i = 0; i < gestor.getAlumnos().size(); i++) {
                        System.out.println(i + ". " + gestor.getAlumnos().get(i).getNombre());
                    }
                    int indiceAlumno = scanner.nextInt();
                    scanner.nextLine();

                    Alumno alumnoSeleccionado = gestor.getAlumnos().get(indiceAlumno);
                    gestor.registrarPieza(idPieza, nombrePieza, alumnoSeleccionado);
                    break;
                case 4:
                    // Falta implementar la funcionalidad para listar todas las piezas
                    break;
                case 5:
                    // Listar piezas por alumno
                    listaAlumnos = gestor.getAlumnos();
                    if (listaAlumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                        break;
                    }
                    System.out.println("Seleccione un alumno:");
                    for (int i = 0; i < gestor.getAlumnos().size(); i++) {
                        System.out.println(i + ". " + gestor.getAlumnos().get(i).getNombre());
                    }
                    int alumnoIndex = scanner.nextInt();
                    scanner.nextLine();

                    Alumno alumnoParaListar = gestor.getAlumnos().get(alumnoIndex);
                    List<Pieza> piezasAlumno = gestor.getPiezasPorAlumno(alumnoParaListar);
                    System.out.println("Piezas del alumno " + alumnoParaListar.getNombre() + ":");
                    for (Pieza pieza : piezasAlumno) {
                        System.out.println("- " + pieza.getNombre());
                    }
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }
}
