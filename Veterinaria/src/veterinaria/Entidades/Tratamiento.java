
package veterinaria.Entidades;

// Clase que representa la entidad Tratamiento en el sistema
public class Tratamiento {

    // Atributos privados que representan las propiedades de un tratamiento
    private int idTratamiento; // Identificador único del tratamiento
    private String tipo; // Tipo de tratamiento (por ejemplo, cirugía, vacunación, etc.)
    private String descripcion; // Descripción detallada del tratamiento
    private double importe; // Costo del tratamiento
    private boolean estado; // Estado del tratamiento (activo o inactivo)

    // Constructor vacío de la clase Tratamiento
    public Tratamiento() {
    }

    // Constructor que inicializa todos los atributos de la clase Tratamiento excepto el idTratamiento
    public Tratamiento(String tipo, String descripcion, double importe, boolean estado) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.importe = importe;
        this.estado = estado;
    }

    // Constructor que inicializa todos los atributos de la clase Tratamiento
    public Tratamiento(int idTratamiento, String tipo, String descripcion, double importe, boolean estado) {
        this.idTratamiento = idTratamiento;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.importe = importe;
        this.estado = estado;
    }

    // Métodos getters y setters para acceder a los atributos privados

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método toString que devuelve una representación en cadena del objeto Tratamiento
    @Override
    public String toString() {
        return "Tratamientos{" + "idTratamientos=" + idTratamiento + ", tipo=" + tipo + ", descripcion=" + descripcion + ", importe=" + importe + ", estado=" + estado + '}';
    }
}
