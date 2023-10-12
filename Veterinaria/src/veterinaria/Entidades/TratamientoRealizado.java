
package veterinaria.Entidades;


public class TratamientoRealizado {
    private int idTratamientoRealizado;
    private Visita idVisita;
    private Mascota idMascota;
    private Tratamiento idTratamiento;
    private double importe;

    public TratamientoRealizado() {
    }

    public TratamientoRealizado(int idTratamientoRealizado, Visita idVisita, Mascota idMascota, Tratamiento idTratamiento, double importe) {
        this.idTratamientoRealizado = idTratamientoRealizado;
        this.idVisita = idVisita;
        this.idMascota = idMascota;
        this.idTratamiento = idTratamiento;
        this.importe = importe;
    }

    public TratamientoRealizado(Visita idVisita, Mascota idMascota, Tratamiento idTratamiento, double importe) {
        this.idVisita = idVisita;
        this.idMascota = idMascota;
        this.idTratamiento = idTratamiento;
        this.importe = importe;
    }

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

    @Override
    public String toString() {
        return "TratamientoRealizado{" + "idTratamientoRealizado=" + idTratamientoRealizado + ", idVisita=" + idVisita + ", idMascota=" + idMascota + ", idTratamiento=" + idTratamiento + ", importe=" + importe + '}';
    }

}
