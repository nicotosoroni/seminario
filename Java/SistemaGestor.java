import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaGestor {
    private List<Alumno> alumnos;
    private List<Pieza> piezas;

    private Connection conexion;
    public SistemaGestor(Connection conexion) {
        this.conexion = conexion;
        this.alumnos = new ArrayList<>();
        this.piezas = new ArrayList<>();
    }

    // Método registrar alumno
    public void registrarAlumno(String nombre, String email) {
        String sql = "INSERT INTO Students (name, email, enrollee_course) VALUES (?, ?, NULL)";
        try (Connection conn = Main.ConectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);  // Nombre del alumno
            stmt.setString(2, email);  // Email del alumno


            stmt.executeUpdate();
            System.out.println("Alumno registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el alumno: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Método para registrar una pieza en la base de datos
    public void registrarPieza(String nombre, String status, int studentId) {
        String sql = "INSERT INTO Piece (piece_name, status, student_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, status);
            stmt.setInt(3, studentId);
            stmt.executeUpdate();
            System.out.println("Pieza registrada correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al registrar la pieza: " + e.getMessage());
        }
    }



    // Método para verificar si un alumno existe en la base de datos
    public boolean verificarAlumno(int studentId) {
        String sql = "SELECT COUNT(*) FROM Students WHERE student_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe al menos un resultado
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el alumno: " + e.getMessage());
        }
        return false;
    }

    public Alumno getAlumnoPorId(int studentId) {
        String sql = "SELECT student_id, name, email FROM Students WHERE student_id = ?";
        Alumno alumno = null;

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("student_id");
                String nombre = rs.getString("name");
                String email = rs.getString("email");
                alumno = new Alumno(id, nombre, email); // Crear instancia del alumno
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el alumno: " + e.getMessage());
        }

        return alumno; // Puede ser null si el alumno no existe
    }


    public List<Pieza> getPiezasPorAlumno(int studentId) {
        List<Pieza> piezas = new ArrayList<>();
        String sql = "SELECT piece_id, piece_name, status FROM Piece WHERE student_id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            // Recuperar el alumno asociado
            Alumno alumno = getAlumnoPorId(studentId);

            if (alumno == null) {
                System.out.println("No se encontró al alumno con ID: " + studentId);
                return piezas; // Devuelve una lista vacía
            }

            while (rs.next()) {
                int id = rs.getInt("piece_id");
                String nombre = rs.getString("piece_name");
                String estado = rs.getString("status");
                piezas.add(new Pieza(id, nombre, alumno, estado)); // Crear Pieza con el Alumno
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener piezas del alumno: " + e.getMessage());
        }

        return piezas;
    }



    // Método para obtener la lista de alumnos desde la base de datos
    public List<Alumno> getAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT student_id, name, email FROM Students";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String nombre = rs.getString("name");
                String email = rs.getString("email");
                alumnos.add(new Alumno(id, nombre, email));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los alumnos: " + e.getMessage());
        }

        return alumnos;
    }

    // Método para obtener todas las piezas con el alumno asociado
    public List<String> getPiezasConAlumno() {
        List<String> piezasConAlumno = new ArrayList<>();
        String sql = """
        SELECT P.piece_id, P.piece_name, P.status, S.student_id, S.name
        FROM Piece P
        JOIN Students S ON P.student_id = S.student_id
    """;

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPieza = rs.getInt("piece_id");
                String nombrePieza = rs.getString("piece_name");
                String estado = rs.getString("status");
                int idAlumno = rs.getInt("student_id");
                String nombreAlumno = rs.getString("name");

                piezasConAlumno.add("Pieza ID: " + idPieza + ", Nombre: " + nombrePieza + ", Estado: " + estado +
                        ", Alumno ID: " + idAlumno + ", Nombre Alumno: " + nombreAlumno);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener piezas con alumno asociado: " + e.getMessage());
        }

        return piezasConAlumno;
    }
}
