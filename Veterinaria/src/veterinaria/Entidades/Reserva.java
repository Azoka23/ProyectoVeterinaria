
package veterinaria.Entidades;



    public class Reserva {
    private String cliente;
    private String mascota;
    private String estado;

    public Reserva() {
    }
    
    

    public Reserva(String cliente, String mascota, String estado) {
        this.cliente = cliente;
        this.mascota = mascota;
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public String getMascota() {
        return mascota;
    }

    public String getEstado() {
        return estado;
    }

    void setEstado(String reservado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


