package veterinaria.AccesoADatos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import veterinaria.Entidades.TratamientoRealizado;


// Clase que maneja las operaciones de acceso a datos para los tratamientos realizados
public class TratamientoRealizadoDAO extends DAO {

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private TratamientoRealizadoDAO() {}

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

}
