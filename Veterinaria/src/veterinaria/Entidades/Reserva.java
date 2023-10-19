
package veterinaria.Entidades;

import java.time.LocalDate;
import java.time.LocalTime;



    public class Reserva {
        private int idReserva;
    private Cliente cliente;
    private Mascota mascota;
    private LocalTime horario;
    private boolean estado;
    private LocalDate fecha;

    public Reserva() {
    }

    public Reserva(int idReserva, Cliente cliente, Mascota mascota, LocalTime horario, boolean estado, LocalDate fecha) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.mascota = mascota;
        this.horario = horario;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Reserva(Cliente cliente, Mascota mascota, LocalTime horario, boolean estado, LocalDate fecha) {
        this.cliente = cliente;
        this.mascota = mascota;
        this.horario = horario;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", cliente=" + cliente + ", mascota=" + mascota + ", horario=" + horario + ", estado=" + estado + ", fecha=" + fecha + '}';
    }
    
    
    
    

   
}


