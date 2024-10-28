public class ProcesoProduccion {
    // Estados del proceso de producción
    public static final String MOLDEADO = "Moldeado";
    public static final String PRIMER_HORNEADO = "Primer Horneado";
    public static final String ESMALTADO = "Esmaltado";
    public static final String HORNEADA_FINAL = "Horneada Final";

    // Método para obtener el siguiente estado
    public static String obtenerSiguienteEstado(String estadoActual) {
        switch (estadoActual) {
            case MOLDEADO:
                return PRIMER_HORNEADO;
            case PRIMER_HORNEADO:
                return ESMALTADO;
            case ESMALTADO:
                return HORNEADA_FINAL;
            default:
                return "Finalizado";
        }
    }
}
