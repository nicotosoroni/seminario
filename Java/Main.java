import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;

public class Main {

    public static Connection ConectarBD(){
        Connection conexion;
        String host = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "Siglo21NT-.-";
        String bd = "ceramica_db";

        System.out.println("Conectando...");

        try {
            conexion = DriverManager.getConnection(host + bd, user, pass);
            System.out.println("Conexion exitosa!");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        Connection bd = ConectarBD();
        SistemaGestor gestor = new SistemaGestor(bd);

        while (!salir) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Listar Alumnos");
            System.out.println("3. Registrar Pieza");
            System.out.println("4. Listar Piezas por Alumno");
            System.out.println("5. Listar Piezas ");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del alumno: ");
                    String nombreAlumno = scanner.nextLine();
                    System.out.print("Ingrese email del alumno: ");
                    String emailAlumno = scanner.nextLine();
                    gestor.registrarAlumno(nombreAlumno, emailAlumno);
                    break;
                case 2:
                    // Obtener la lista de alumnos desde la base de datos
                    List<Alumno> alumnos = gestor.getAlumnos();
                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados en la base de datos.");
                    } else {
                        System.out.println("Lista de Alumnos:");
                        for (Alumno alumno : alumnos) {
                            System.out.println("- ID: " + alumno.getId() + ", Nombre: " + alumno.getNombre() + ", Email: " + alumno.getEmail());
                        }
                    }
                    break;
                case 3:
                    alumnos = gestor.getAlumnos();
                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados en la base de datos.");
                        break;
                    }
                    System.out.print("Ingrese nombre de la pieza: ");
                    String nombrePieza = scanner.nextLine();
                    System.out.print("Ingrese estado de la pieza (Ej. 'En proceso', 'Finalizado'): ");
                    String estadoPieza = scanner.nextLine();

                    System.out.print("Ingrese un ID del siguiente listado de alumnos: ");
                    for (Alumno alumno : alumnos) {
                        System.out.println("- ID: " + alumno.getId() + ", Nombre: " + alumno.getNombre());
                    }
                    System.out.print("ID: ");
                    int idAlumnoPieza = scanner.nextInt();
                    scanner.nextLine();

                    // Verificar que el alumno existe
                    if (gestor.verificarAlumno(idAlumnoPieza)) {
                        gestor.registrarPieza(nombrePieza, estadoPieza, idAlumnoPieza);
                    } else {
                        System.out.println("Error: No se encontró un alumno con el ID proporcionado.");
                    }
                    break;
                case 4:
                    alumnos = gestor.getAlumnos();
                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados en la base de datos.");
                        break;
                    }
                    System.out.print("Ingrese un ID del siguiente listado de alumnos ara listar sus piezas: ");
                    for (Alumno alumno : alumnos) {
                        System.out.println("- ID: " + alumno.getId() + ", Nombre: " + alumno.getNombre());
                    }
                    System.out.print("ID: ");
                    int idAlumnoPiezas = scanner.nextInt();
                    scanner.nextLine();

                    // Obtener las piezas del alumno
                    List<Pieza> piezasAlumno = gestor.getPiezasPorAlumno(idAlumnoPiezas);
                    if (piezasAlumno.isEmpty()) {
                        System.out.println("El alumno no tiene piezas registradas o no existe.");
                    } else {
                        System.out.println("Piezas del alumno con ID " + idAlumnoPiezas + ":");
                        for (Pieza pieza : piezasAlumno) {
                            System.out.println("- ID: " + pieza.getId() + ", Nombre: " + pieza.getNombre() + ", Estado: " + pieza.getEstado());
                        }
                    }
                    break;
                case 5:
                    // Obtener todas las piezas con información del alumno asociado
                    List<String> piezasConAlumno = gestor.getPiezasConAlumno();
                    if (piezasConAlumno.isEmpty()) {
                        System.out.println("No hay piezas registradas en la base de datos.");
                    } else {
                        System.out.println("Piezas con sus respectivos alumnos:");
                        for (String piezaInfo : piezasConAlumno) {
                            System.out.println("- " + piezaInfo);
                        }
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
        try {
            bd.close(); // Cerrar conexión al final del programa
            System.out.println("Conexión cerrada.");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
        System.out.println("Programa finalizado.");
    }
}
