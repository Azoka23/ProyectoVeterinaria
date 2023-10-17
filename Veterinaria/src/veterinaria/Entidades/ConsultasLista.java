
package veterinaria.Entidades;

// Clase que representa la entidad Consulta en la lista de consultas
public class ConsultasLista {
    // Atributos privados que representan las propiedades de una consulta en la lista
    private int idConsulta; // Identificador único de la consulta
    private String consulta; // Nombre o descripción corta de la consulta
    private String descripcion; // Descripción detallada de la consulta

    // Constructor vacío de la clase ConsultasLista
    public ConsultasLista() {
    }

    // Constructor que inicializa todos los atributos de la clase ConsultasLista excepto el idConsulta
    public ConsultasLista(String consulta, String descripcion) {
        this.consulta = consulta;
        this.descripcion = descripcion;
    }

    // Constructor que inicializa todos los atributos de la clase ConsultasLista
    public ConsultasLista(int idConsulta, String consulta, String descripcion) {
        this.idConsulta = idConsulta;
        this.consulta = consulta;
        this.descripcion = descripcion;
    }

    // Métodos getters y setters para acceder a los atributos privados

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método toString que devuelve una representación en cadena del objeto ConsultasLista
    @Override
    public String toString() {
        return consulta; // Devuelve el nombre o descripción corta de la consulta
    }
}
