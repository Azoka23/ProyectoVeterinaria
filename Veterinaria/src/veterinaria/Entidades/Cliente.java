
package veterinaria.Entidades;

// Clase que representa la entidad Cliente en la base de datos
public class Cliente {
    // Atributos privados que representan las propiedades de un cliente
    private int idCliente; // Identificador único del cliente
    private int dni; // Número de documento de identidad del cliente
    private String apellido; // Apellido del cliente
    private String nombre; // Nombre del cliente
    private String direccion; // Dirección del cliente
    private String telefono; // Número de teléfono del cliente
    private String contactoNombre; // Nombre del contacto de emergencia del cliente
    private String contactoTelefono; // Número de teléfono del contacto de emergencia del cliente
    private boolean estado; // Estado del cliente (activo o inactivo)
    private String email; // Dirección de correo electrónico del cliente

    // Constructor vacío de la clase Cliente
    public Cliente() {
    }

    // Constructor que inicializa todos los atributos de la clase Cliente excepto el idCliente
    public Cliente(int dni, String apellido, String nombre, String direccion, String telefono,
                   String contactoNombre, String contactoTelefono, boolean estado, String email) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contactoNombre = contactoNombre;
        this.contactoTelefono = contactoTelefono;
        this.estado = estado;
        this.email = email;
    }

    // Constructor que inicializa todos los atributos de la clase Cliente
    public Cliente(int idCliente, int dni, String apellido, String nombre, String direccion, String telefono,
                   String contactoNombre, String contactoTelefono, boolean estado, String email) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contactoNombre = contactoNombre;
        this.contactoTelefono = contactoTelefono;
        this.estado = estado;
        this.email = email;
    }

    // Métodos getters y setters para acceder a los atributos privados

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString que devuelve una representación en cadena del objeto Cliente
    @Override
    public String toString() {
        return "N°Cliente " + idCliente + " "+ dni + ", " + apellido + ", " + nombre ;
    }
}