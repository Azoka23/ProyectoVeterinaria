
package veterinaria.Entidades;

// Clase que representa la entidad TratamientoRealizado en el sistema
public class TratamientoRealizado {
    
    // Atributos privados que representan las propiedades de un tratamiento realizado
    private int idTratamientoRealizado; // Identificador único del tratamiento realizado
    private Visita idVisita; // Visita asociada al tratamiento realizado
    private Mascota idMascota; // Mascota asociada al tratamiento realizado
    private Tratamiento idTratamiento; // Tratamiento asociado al tratamiento realizado
    private double importe; // Importe del tratamiento realizado

    // Constructor vacío de la clase TratamientoRealizado
    public TratamientoRealizado() {
    }

    // Constructor que inicializa todos los atributos de la clase TratamientoRealizado excepto el idTratamientoRealizado
    public TratamientoRealizado(Visita idVisita, Mascota idMascota, Tratamiento idTratamiento, double importe) {
        this.idVisita = idVisita;
        this.idMascota = idMascota;
        this.idTratamiento = idTratamiento;
        this.importe = importe;
    }

    // Constructor que inicializa todos los atributos de la clase TratamientoRealizado
    public TratamientoRealizado(int idTratamientoRealizado, Visita idVisita, Mascota idMascota, Tratamiento idTratamiento, double importe) {
        this.idTratamientoRealizado = idTratamientoRealizado;
        this.idVisita = idVisita;
        this.idMascota = idMascota;
        this.idTratamiento = idTratamiento;
        this.importe = importe;
    }

    // Métodos getters y setters para acceder a los atributos privados

    public int getIdTratamientoRealizado() {
        return idTratamientoRealizado;
    }

    public void setIdTratamientoRealizado(int idTratamientoRealizado) {
        this.idTratamientoRealizado = idTratamientoRealizado;
    }

    public Visita getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Visita idVisita) {
        this.idVisita = idVisita;
    }

    public Mascota getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascota idMascota) {
        this.idMascota = idMascota;
    }

    public Tratamiento getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Tratamiento idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    // Método toString que devuelve una representación en cadena del objeto TratamientoRealizado
    @Override
    public String toString() {
        return "TratamientoRealizado{" + "idTratamientoRealizado=" + idTratamientoRealizado + ", idVisita=" + idVisita + ", idMascota=" + idMascota + ", idTratamiento=" + idTratamiento + ", importe=" + importe + '}';
    }

}
