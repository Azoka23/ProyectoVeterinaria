
package veterinaria.Entidades;



public class Cliente {
    private int idCliente;
    private int dni;
    private String apellido;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contactoNombre;
    private String contactoTelefono;    
    private boolean estado;
    private String email;

    public Cliente() {
    }

    public Cliente(int idCliente, int dni, String apellido, String nombre, String direccion, String telefono, String contactoNombre, String contactoTelefono, boolean estado, String email) {
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

    public Cliente(int dni, String apellido, String nombre, String direccion, String telefono, String contactoNombre, String contactoTelefono, boolean estado, String email) {
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

    @Override
    public String toString() {
        return "N°Cliente " + idCliente + " "+ dni + ", " + apellido + ", " + nombre ;
    }

 
    
}
