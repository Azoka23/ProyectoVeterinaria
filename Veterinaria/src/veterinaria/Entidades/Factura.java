package veterinaria.Entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

public class Factura {

    private int idVisita;
    private LocalDate fecha;
    private Cliente cliente;
    private Mascota mascota;
    private List<Tratamiento> tratamientos;
    private double total;
    private double descuento;

    public Factura() {
    }

    public Factura(int idVisita, LocalDate fecha, Cliente cliente, Mascota mascota, List<Tratamiento> tratamientos, double total, double descuento) {
        this.idVisita = idVisita;
        this.fecha = fecha;
        this.cliente = cliente;
        this.mascota = mascota;
        this.tratamientos = tratamientos;
        this.total = total;
        this.descuento = descuento;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    // Método para generar el contenido de la factura
    public String generarFactura() {
        int numeroRemito = idVisita;
        StringBuilder factura = new StringBuilder();
        factura.append("Número de Remito: ").append(numeroRemito).append("\n\n"); // Número de remito
        factura.append("Fecha: ").append(obtenerFechaDelDia()).append("\n");
        factura.append("Cliente: ").append(cliente.getApellido()).append(", ").append(cliente.getNombre()).append("\n");
        factura.append("Mascota: ").append(mascota.getAlias()).append("\n");

        // Separador
        factura.append("----------------------------------------\n");

        factura.append("Tratamientos:\n");
        for (Tratamiento tratamiento : tratamientos) {
            factura.append("- ").append(tratamiento.getTipo()).append(": $").append(tratamiento.getImporte()).append("\n");
        }

        // Separador
        factura.append("----------------------------------------\n");

        factura.append("Total: $").append(total).append("\n");
        factura.append("Descuento por pago efectivo: $").append(descuento).append("\n");
        factura.append("Total a pagar: $").append(total - descuento).append("\n");
        factura.append("Clínica Veterinaria: Nombre, Dirección, Teléfono, etc.\n");

        return factura.toString();
    }

    private String obtenerFechaDelDia() {
        // Obtener la fecha actual del sistema
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha como una cadena en el formato deseado (por ejemplo, "dd de MMMM de yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        return fechaFormateada;
    }

    @Override
    public String toString() {
        return "Factura{" + "idVisita=" + idVisita + ", fecha=" + fecha + ", cliente=" + cliente + ", mascota=" + mascota + ", tratamientos=" + tratamientos + ", total=" + total + ", descuento=" + descuento + '}';
    }



}
