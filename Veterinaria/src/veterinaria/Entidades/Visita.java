
package veterinaria.Entidades;

import java.time.LocalDate;

public class Visita {
    private int idVisita;
    private Mascota idMascota;
    private Tratamiento idTratamiento;
    private LocalDate fechaVisita;
    private String detallesSintoma;
    private double pesoActual;

    public Visita() {
    }

    public Visita(int idVisitas, Mascota idMascotas, Tratamiento idTratamientos, LocalDate fechaVisita, String detallesSintomas, double pesoActual) {
        this.idVisita = idVisitas;
        this.idMascota = idMascotas;
        this.idTratamiento = idTratamientos;
        this.fechaVisita = fechaVisita;
        this.detallesSintoma = detallesSintomas;
        this.pesoActual = pesoActual;
    }

    public Visita(Mascota idMascotas, Tratamiento idTratamientos, LocalDate fechaVisita, String detallesSintomas, double pesoActual) {
        this.idMascota = idMascotas;
        this.idTratamiento = idTratamientos;
        this.fechaVisita = fechaVisita;
        this.detallesSintoma = detallesSintomas;
        this.pesoActual = pesoActual;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
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

    public LocalDate getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(LocalDate fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getDetallesSintoma() {
        return detallesSintoma;
    }

    public void setDetallesSintoma(String detallesSintoma) {
        this.detallesSintoma = detallesSintoma;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    @Override
    public String toString() {
        return "Visita{" + "idVisitas=" + idVisita + ", idMascotas=" + idMascota + ", idTratamientos=" + idTratamiento + ", fechaVisita=" + fechaVisita + ", detallesSintomas=" + detallesSintoma + ", pesoActual=" + pesoActual + '}';
    }


    


}
