package veterinaria.Entidades;

import java.time.LocalDate;

// Clase que representa la entidad Mascota en el sistema
public class Mascota {

    // Atributos privados que representan las propiedades de una mascota
    private int idMascota; // Identificador único de la mascota
    private String alias; // Alias o nombre de la mascota
    private Sexo sexo; // Género de la mascota (por ejemplo, macho o hembra)
    private String especie; // Especie de la mascota (por ejemplo, perro, gato, etc.)
    private String raza; // Raza de la mascota
    private String colorDePelo; // Color del pelaje de la mascota
    private LocalDate fechaNacimiento; // Fecha de nacimiento de la mascota
    private double pesoMedia; // Peso medio esperado de la mascota
    private double pesoActual; // Peso actual de la mascota
    private Cliente idCliente; // Cliente al que pertenece la mascota
    private boolean estado; // Estado de la mascota (activo o inactivo)

    // Constructor vacío de la clase Mascota
    public Mascota() {
    }

    // Constructor que inicializa todos los atributos de la clase Mascota excepto el idMascota
    public Mascota(String alias, Sexo sexo, String especie, String raza, String colorDePelo, LocalDate fechaNacimiento, double pesoMedia, double pesoActual, Cliente idCliente, boolean estado) {
        this.alias = alias;
        this.sexo = sexo;
        this.especie = especie;
        this.raza = raza;
        this.colorDePelo = colorDePelo;
        this.fechaNacimiento = fechaNacimiento;
        this.pesoMedia = pesoMedia;
        this.pesoActual = pesoActual;
        this.idCliente = idCliente;
        this.estado = estado;
    }

    // Constructor que inicializa todos los atributos de la clase Mascota
    public Mascota(int idMascota, String alias, Sexo sexo, String especie, String raza, String colorDePelo, LocalDate fechaNacimiento, double pesoMedia, double pesoActual, Cliente idCliente, boolean estado) {
        this.idMascota = idMascota;
        this.alias = alias;
        this.sexo = sexo;
        this.especie = especie;
        this.raza = raza;
        this.colorDePelo = colorDePelo;
        this.fechaNacimiento = fechaNacimiento;
        this.pesoMedia = pesoMedia;
        this.pesoActual = pesoActual;
        this.idCliente = idCliente;
        this.estado = estado;
    }

    // Métodos getters y setters para acceder a los atributos privados

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColorDePelo() {
        return colorDePelo;
    }

    public void setColorDePelo(String colorDePelo) {
        this.colorDePelo = colorDePelo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getPesoMedia() {
        return pesoMedia;
    }

    public void setPesoMedia(double pesoMedia) {
        this.pesoMedia = pesoMedia;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método toString que devuelve una representación en cadena del objeto Mascota
    @Override
    public String toString() {
        return "Mascota{" + "idMascota=" + idMascota + ", alias=" + alias + ", sexo=" + sexo + ", especie=" + especie + ", raza=" + raza + ", colorDePelo=" + colorDePelo + ", fechaNacimiento=" + fechaNacimiento + ", pesoMedia=" + pesoMedia + ", pesoActual=" + pesoActual + ", idCliente=" + idCliente + ", estado=" + estado + '}';
    }
}
