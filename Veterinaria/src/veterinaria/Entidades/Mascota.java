package veterinaria.Entidades;

import java.time.LocalDate;

public class Mascota {

    private int idMascota;

    private String alias;
    private Sexo sexo;
    private String especie;
    private String raza;
    private String colorDePelo;
    private LocalDate fechaNacimiento;
    private double pesoMedia;
    private double pesoActual;
    private Cliente idCliente;
    private boolean estado;

    public Mascota() {
    }

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

    @Override
    public String toString() {
        return "Mascota{" + "idMascota=" + idMascota + ", alias=" + alias + ", sexo=" + sexo + ", especie=" + especie + ", raza=" + raza + ", colorDePelo=" + colorDePelo + ", fechaNacimiento=" + fechaNacimiento + ", pesoMedia=" + pesoMedia + ", pesoActual=" + pesoActual + ", idCliente=" + idCliente + ", estado=" + estado + '}';
    }

 
}
