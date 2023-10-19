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
import veterinaria.Entidades.Sexo;

public class ReservaDAO extends DAO {

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
//Llevo toda la reserva por fecha

    public List<Reserva> buscarListaReservasxfecha(LocalDate fecha) throws Exception {
        String sql = "SELECT * FROM reservas WHERE fecha=? ORDER BY  horario ASC ";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setDate(1, Date.valueOf(fecha));
            resultado = consultarBase(preparedStatement);
            Reserva reserva = null;
            List<Reserva> reservas = new ArrayList();
            while (resultado.next()) {
                reservas.add(obtenerReservaDesdeResultado(resultado))  ;
            }
            return reservas;
        }
    }
    //Llevo la reserva completa

    private Reserva obtenerReservaDesdeResultado(ResultSet result) throws SQLException {
        ClienteDAO clienteDAO = ClienteDAO.obtenerInstancia();
        MascotaDAO mascotaDAO = MascotaDAO.obtenerInstancia();
        Reserva reserva = new Reserva();

//        try {
        reserva.setIdReserva(result.getInt("idReserva"));
        reserva.setCliente(clienteDAO.obtenerClientexId(result.getInt("idCliente")));
        reserva.setMascota(mascotaDAO.obtenerMascotaPorId(result.getInt("idMascota")));
        reserva.setHorario(result.getTime("horario").toLocalTime());
        reserva.setEstado(result.getBoolean("estado"));
        reserva.setFecha(result.getDate("fecha").toLocalDate());

        return reserva;
    }

}
