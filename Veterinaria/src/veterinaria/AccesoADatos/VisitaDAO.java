package veterinaria.AccesoADatos;

import veterinaria.Entidades.Visita;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

// Clase para acceder a los datos de las visitas en la base de datos
public class VisitaDAO extends DAO {

    // Constructor privado para evitar la instancia directa de la clase
    private VisitaDAO() {
    }

    // Clase interna para implementar el patrón Singleton
    private static class VisitaDAOHolder {

        private static final VisitaDAO INSTANCE = new VisitaDAO();
    }

    // Método para obtener la instancia única de VisitaDAO
    public static VisitaDAO obtenerInstancia() {
        return VisitaDAOHolder.INSTANCE;
    }

// Método para guardar una visita en la base de datos
    public int guardarVisita(Visita visita) throws Exception {
        // Consulta SQL para insertar una nueva visita en la tabla 'visitas'
        String sql = "INSERT INTO visitas (fechaV, detallesSintomas, pesoActual, importe) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Establecer los valores en la consulta SQL
            preparedStatement.setDate(1, Date.valueOf(visita.getFechaVisita()));
            preparedStatement.setString(2, visita.getDetallesSintoma());
            preparedStatement.setDouble(3, visita.getPesoActual());
            preparedStatement.setDouble(4, visita.getImporteVisita());

            // Ejecutar la consulta y obtener el resultado de la operación
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace(); // Otra opción es lanzar una excepción personalizada si se desea manejar el error en otro lugar
            // Puedes también loggear el error y lanzar una excepción personalizada
            // throw new MiExcepcion("Error al guardar la visita en la base de datos", e);
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    // Método para contar el total de registros en la tabla 'mascotas'
    public double avgPesoM(int idMascota) throws Exception {
        String sql = "SELECT ROUND(AVG(pesoActual), 2) AS promedioPesoUltimas10Visitas "
                + "FROM visitas v "
                + "JOIN tratamientosrealizados tr ON v.idVisita = tr.idVisita "
                + "WHERE tr.idMascota = ? "
                + "ORDER BY v.fechaV DESC "
                + "LIMIT 10";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMascota);
            resultado = consultarBase(preparedStatement);

            if (resultado.next()) {
                double pesoM = resultado.getDouble(1);
                return pesoM;
            }
        } catch (SQLException ex) {
            // Manejar la excepción SQL
            ex.printStackTrace();
        }
        return 0; // Devuelve 0 si no se encontraron registros
    }

    public Collection<Visita> listarMascotasPorVisitas(int idMascotas) throws Exception {
        String sql = "SELECT DISTINCT v.idVisita, v.fechaV, v.detallesSintomas, v.pesoActual, v.importe "
                + "FROM tratamientosrealizados tr "
                + "JOIN visitas v ON tr.idVisita = v.idVisita "
                + "WHERE tr.idMascota = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMascotas);
            resultado = consultarBase(preparedStatement);

            Collection<Visita> visitas = new ArrayList();

            while (resultado.next()) {
                visitas.add(obtenerVisitaDesdeResultado(resultado));
            }

            return visitas;

        }
    }

    public Collection<Visita> listarVisitasPorCliente(int idCliente) throws Exception {

        String sql = "SELECT DISTINCT v.idVisita, v.fechaV, v.detallesSintomas, v.pesoActual, v.importe "
                + "FROM tratamientosrealizados tr "
                + "JOIN visitas v ON tr.idVisita = v.idVisita "
                + "WHERE tr.idMascota = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCliente);
            resultado = consultarBase(preparedStatement);

            Collection<Visita> visitas = new ArrayList();

            while (resultado.next()) {
                visitas.add(obtenerVisitaDesdeResultado(resultado));
            }

            return visitas;

        }
    }

    public Map<Visita, String> obtenerVisitasDeCliente(int idCliente) throws Exception {
        Map<Visita, String> visitasConAlias = new LinkedHashMap<>();

        String sql = "SELECT v.idVisita, v.fechaV, v.detallesSintomas, v.pesoActual, v.importe, "
                + "m.alias AS nombreMascota, m.sexo, m.especie, m.raza, m.colorDePelo, m.fechaNac "
                + "FROM visitas v "
                + "JOIN tratamientosrealizados tr ON v.idVisita = tr.idVisita "
                + "JOIN mascotas m ON tr.idMascota = m.idMascota "
                + "JOIN clientes c ON m.idCliente = c.idCliente "
                + "WHERE c.idCliente = ? "
                + "GROUP BY v.idVisita "
                + "ORDER BY v.fechaV ASC";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCliente);
            resultado = consultarBase(preparedStatement);

            while (resultado.next()) {
                Visita visita = obtenerVisitaDesdeResultado(resultado);
                String nombreMascota = resultado.getString("nombreMascota");
                visitasConAlias.put(visita, nombreMascota);
            }
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            ex.printStackTrace();
            // Puedes lanzar una excepción personalizada si lo deseas
            //throw new MiExcepcionPersonalizada("Error al obtener visitas desde la base de datos", ex);
        }
        return visitasConAlias;
    }

// Método para obtener una mascota desde un resultado de consulta SQL
    private Visita obtenerVisitaDesdeResultado(ResultSet result) throws SQLException {

        Visita visita = new Visita();

        visita.setIdVisita(result.getInt("idVisita"));
        visita.setFechaVisita(result.getDate("fechaV").toLocalDate());
        visita.setDetallesSintoma(result.getString("detallesSintomas"));
        visita.setPesoActual(result.getDouble("pesoActual"));
        visita.setImporteVisita(result.getDouble("importe"));

        return visita;
    }
    
        // Método para buscar una mascota por su ID en la base de datos
    public Visita obtenerVisitaPorId(int idVisita) {
        String sql = "SELECT * FROM `visitas` WHERE idVisita=?";
        Visita visita = null;

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idVisita);
            resultado = consultarBase(preparedStatement);

            if (resultado.next()) {

                visita = obtenerVisitaDesdeResultado(resultado);

            }

        } catch (SQLException ex) {
            // Manejar las excepciones de SQL
            ex.printStackTrace();
        }

        return visita;
    }
}
