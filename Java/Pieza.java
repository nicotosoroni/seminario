public class Pieza {
    private int id;
    private String nombre;
    private Alumno alumno;
    private String estado;

    public Pieza(int id, String nombre, Alumno alumno) {
        this.id = id;
        this.nombre = nombre;
        this.alumno = alumno;
        this.estado = "Moldeado"; // Estado inicial
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
