package veterinaria.AccesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Entidades.TratamientoRealizado;
import veterinaria.Entidades.Visita;

// Clase que maneja las operaciones de acceso a datos para los tratamientos realizados
public class TratamientoRealizadoDAO extends DAO {

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private TratamientoRealizadoDAO() {
    }

    // Clase interna que contiene la instancia única de TratamientoRealizadoDAO (patrón Singleton)
    private static class TratamientoRealizadoDAOHolder {

        private static final TratamientoRealizadoDAO INSTANCE = new TratamientoRealizadoDAO();
    }

    // Método para obtener la instancia única de TratamientoRealizadoDAO (patrón Singleton)
    public static TratamientoRealizadoDAO obtenerInstancia() {
        return TratamientoRealizadoDAOHolder.INSTANCE;
    }

    // Método para guardar un tratamiento realizado en la base de datos
    public int guardarTratamientoRealizado(TratamientoRealizado tratamientoRealizado) throws Exception {
        String sql = "INSERT INTO tratamientosrealizados (idVisita, idMascota, idTratamiento, importe ) VALUES (?, ?, ?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Establecer los valores de los parámetros en la consulta SQL
            preparedStatement.setInt(1, tratamientoRealizado.getIdVisita().getIdVisita());
            preparedStatement.setInt(2, tratamientoRealizado.getIdMascota().getIdMascota());
            preparedStatement.setInt(3, tratamientoRealizado.getIdTratamiento().getIdTratamiento());
            preparedStatement.setDouble(4, tratamientoRealizado.getImporte());

            // Llamar al método de la clase base para insertar el tratamiento realizado en la base de datos
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace(); // Otra opción es lanzar una excepción personalizada si se desea manejar el error en otro lugar
            // Puedes también loggear el error y lanzar una excepción personalizada
            // throw new MiExcepcion("Error al guardar el tratamiento realizado en la base de datos", e);
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    public List<TratamientoRealizado> obtenerTratamientosDeMascota(int idMascota) throws Exception {
        List<TratamientoRealizado> tratamientosRealizados = new ArrayList<>();
        String sql = "SELECT tr.idTratamientoRealizados, tr.idVisita,tr.idMascota,tr.idTratamiento,tr.importe "
                + "FROM tratamientosrealizados tr "
                + "JOIN tratamientos t ON tr.idTratamiento = t.idTratamiento "
                + "JOIN visitas v ON tr.idVisita = v.idVisita "
                + "WHERE tr.idMascota = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMascota);

            resultado = consultarBase(preparedStatement);

            while (resultado.next()) {

                TratamientoRealizado tratamientoRealizado = obtenerTratamientoRealizadoDesdeResultado(resultado);
                tratamientosRealizados.add(tratamientoRealizado);
            }
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            ex.printStackTrace();
            // Puedes lanzar una excepción personalizada si lo deseas
            // throw new MiExcepcionPersonalizada("Error al obtener tratamientos desde la base de datos", ex);
        }

        return tratamientosRealizados;
    }

//    private TratamientoRealizado obtenerTratamientoDesdeResultado(ResultSet result) throws SQLException {
//
////            // Atributos privados que representan las propiedades de un tratamiento realizado
////    private int idTratamientoRealizado; // Identificador único del tratamiento realizado
////    private Visita idVisita; // Visita asociada al tratamiento realizado
////    private Mascota idMascota; // Mascota asociada al tratamiento realizado
////    private Tratamiento idTratamiento; // Tratamiento asociado al tratamiento realizado
////    private double importe; // Importe del tratamiento realizado
//        TratamientoRealizado tratamientoRealizado = new TratamientoRealizado();
//        tratamientoRealizado.setIdTratamientoRealizado(result.getInt("idTratamiento"));
//        tratamientoRealizado.setTipo(result.getString("tipo"));
//        tratamientoRealizado.setDescripcion(result.getString("descripcion"));
//        tratamientoRealizado.setImporte(result.getDouble("importe"));
//        tratamientoRealizado.setEstado(result.getBoolean("estado"));
//
//        return tratamientoRealizado;
//    }
    public TratamientoRealizado obtenerTratamientoRealizadoDesdeResultado(ResultSet result) throws SQLException {
        TratamientoRealizado tratamientoRealizado = new TratamientoRealizado();
        try {
            VisitaDAO visitaD = VisitaDAO.obtenerInstancia();
            MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();
            TratamientoDAO tratamientoD = TratamientoDAO.obtenerInstancia();
//            JOptionPane.showMessageDialog(null, result.getInt("idTratamientoRealizados") + " " + result.getInt("idVisita") + " " + result.getInt("idMascota") + " ");
//            JOptionPane.showMessageDialog(null, result.getDouble("importe") );

            tratamientoRealizado.setIdTratamientoRealizado(result.getInt("idTratamientoRealizados"));
            tratamientoRealizado.setIdVisita(visitaD.obtenerVisitaPorId(result.getInt("idVisita")));
            tratamientoRealizado.setIdMascota(mascotaD.obtenerMascotaPorId(result.getInt("idMascota")));
            tratamientoRealizado.setIdTratamiento(tratamientoD.obtenerTratamientoxId(result.getInt("idTratamiento")));
            tratamientoRealizado.setImporte(result.getDouble("importe"));
        } catch (Exception ex) {
            Logger.getLogger(TratamientoRealizadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            // Manejar la excepción o simplemente loguearla
            tratamientoRealizado = null; // Puedes devolver null o un objeto TratamientoRealizado predeterminado
        }
        return tratamientoRealizado;
    }

}
