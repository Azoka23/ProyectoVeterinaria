
package veterinaria.AccesoADatos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Reserva;


public class ReservaDAO extends DAO{
    
    private ReservaDAO() {
        // Constructor privado para evitar la creación de instancias desde fuera de la clase
    }

    public static ReservaDAO obtenerInstancia() {
        return ReservaDAOHolder.INSTANCE;
    }

    private static class ReservaDAOHolder {

        private static final ReservaDAO INSTANCE = new ReservaDAO();
    }
     
 

    public int guardarReserva(int idCliente, int idMascota, LocalDate fecha, String horario, boolean estado) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO reservas (idCliente, idMascota, fecha, horario, estado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, idCliente);
            preparedStatement.setInt(2, idMascota);
            preparedStatement.setDate(3, Date.valueOf(fecha));
            preparedStatement.setString(4, horario);
            preparedStatement.setBoolean(5, estado);

            return insertarModificarEliminar(preparedStatement);
        }
    }

//    public int guardarReservaConNombres(LocalDate fecha, String horario, String nombreCliente, String nombreMascota, String estado) throws ClassNotFoundException, SQLException {
//        int idCliente = obtenerIdClientePorNombre(nombreCliente);
//        int idMascota = obtenerIdMascotaPorNombre(nombreMascota,idCliente);
//
//        if (idCliente == -1 || idMascota == -1) {
//            return -1; // Manejar el caso donde no se encontró el cliente o la mascota
//        }
//
//        return guardarReserva(idCliente, idMascota, fecha, horario, estado);
//        
//    }

//    private int obtenerIdClientePorNombre(String nombreCliente) throws ClassNotFoundException, SQLException {
//        ClienteDAO clienteDAO =ClienteDAO.obtenerInstancia();
//        return clienteDAO.obtenerIdClientePorNombre(nombreCliente);
//    }
//
//    private int obtenerIdMascotaPorNombre(String nombreMascota,int idCliente) throws ClassNotFoundException, SQLException {
//        MascotaDAO mascotaDAO = MascotaDAO.obtenerInstancia();
//        return mascotaDAO.obtenerIdMascotaPorNombre(nombreMascota);
//    }
}





     
