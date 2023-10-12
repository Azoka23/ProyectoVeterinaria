package veterinaria.Entidades;

import java.time.LocalDate;

public class Visita {

    private int idVisita;
  
    private LocalDate fechaVisita;
    private String detallesSintoma;
    private double pesoActual;
    private double importeVisita;

    public Visita() {
    }

    public Visita(int idVisita, LocalDate fechaVisita, String detallesSintoma, double pesoActual, double importeVisita) {
        this.idVisita = idVisita;
        this.fechaVisita = fechaVisita;
        this.detallesSintoma = detallesSintoma;
        this.pesoActual = pesoActual;
        this.importeVisita = importeVisita;
    }

    public Visita(LocalDate fechaVisita, String detallesSintoma, double pesoActual, double importeVisita) {
        this.fechaVisita = fechaVisita;
        this.detallesSintoma = detallesSintoma;
        this.pesoActual = pesoActual;
        this.importeVisita = importeVisita;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
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

    public double getImporteVisita() {
        return importeVisita;
    }

    public void setImporteVisita(double importeVisita) {
        this.importeVisita = importeVisita;
    }

    @Override
    public String toString() {
        return "Visita{" + "idVisita=" + idVisita + ", fechaVisita=" + fechaVisita + ", detallesSintoma=" + detallesSintoma + ", pesoActual=" + pesoActual + ", importeVisita=" + importeVisita + '}';
    }

}
