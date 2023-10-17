package veterinaria.AccesoADatos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import veterinaria.Entidades.Tratamiento;

public class TratamientoDAO extends DAO {
// Clase para acceder a los datos de las visitas en la base de datos

    private TratamientoDAO() {
        // Constructor privado para evitar la creación de instancias desde fuera de la clase
    }
// Clase interna para implementar el patrón Singleton

    public static TratamientoDAO obtenerInstancia() {
        return TratamientoDAOHolder.INSTANCE;
    }

    // Método para obtener la instancia única de VisitaDAO
    private static class TratamientoDAOHolder {

        private static final TratamientoDAO INSTANCE = new TratamientoDAO();
    }

    /**
     * Guarda un nuevo tratamiento en la base de datos.
     *
     * @param tratamiento El objeto Tratamiento a guardar.
     * @return El ID generado para el tratamiento.
     * @throws Exception Si el tratamiento no es válido o ocurre un error de
     * SQL.
     */
    public int guardarTratamiento(Tratamiento tratamiento) throws Exception {
        validarTratamiento(tratamiento);
        String sql = "INSERT INTO tratamientos (tipo, descripcion, importe, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tratamiento.getTipo());
            preparedStatement.setString(2, tratamiento.getDescripcion());
            preparedStatement.setDouble(3, tratamiento.getImporte());
            preparedStatement.setBoolean(4, tratamiento.isEstado());

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    /**
     * Modifica un tratamiento existente en la base de datos.
     *
     * @param tratamiento El objeto Tratamiento modificado.
     * @return El ID del tratamiento modificado.
     * @throws Exception Si el tratamiento no es válido o ocurre un error de
     * SQL.
     */
    public int modificarTratamiento(Tratamiento tratamiento) throws Exception {
        validarTratamiento(tratamiento);
        String sql = "UPDATE tratamientos SET tipo=?, descripcion=?, importe=?, estado=? WHERE idTratamiento=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tratamiento.getTipo());
            preparedStatement.setString(2, tratamiento.getDescripcion());
            preparedStatement.setDouble(3, tratamiento.getImporte());
            preparedStatement.setBoolean(4, tratamiento.isEstado());
            preparedStatement.setInt(5, tratamiento.getIdTratamiento());

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    /**
     * Realiza una baja lógica de un tratamiento en la base de datos.
     *
     * @param codigo El ID del tratamiento a dar de baja.
     * @return El resultado de la operación de baja.
     * @throws Exception Si ocurre un error de SQL.
     */
    public int bajaLogica(int codigo) throws Exception {
        String sql = "UPDATE tratamientos SET estado=? WHERE idTratamiento=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, codigo);

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    /**
     * Realiza una alta lógica de un tratamiento en la base de datos.
     *
     * @param codigo El ID del tratamiento a dar de alta.
     * @return El resultado de la operación de alta.
     * @throws Exception Si ocurre un error de SQL.
     */
    public int altaLogica(int codigo) throws Exception {
        String sql = "UPDATE tratamientos SET estado=? WHERE idTratamiento=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, codigo);

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    /**
     * Busca un tratamiento por su ID en la base de datos.
     *
     * @param idTratamiento El ID del tratamiento a buscar.
     * @return El tratamiento encontrado o null si no se encuentra.
     * @throws Exception Si ocurre un error de SQL.
     */
    public Tratamiento buscarListaTratamientoxId(int idTratamiento) {
        String sql = "SELECT * FROM `tratamientos` WHERE idTratamiento=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idTratamiento);
            resultado = consultarBase(preparedStatement);
            Tratamiento tratamiento = null;
            if (resultado.next()) {
                tratamiento = obtenerTratamientoDesdeResultado(resultado);
            }
            return tratamiento;
        } catch (SQLException ex) {
            // Captura y maneja la excepción SQL
            ex.printStackTrace(); // Este es un ejemplo básico, puedes usar un sistema de registro o lanzar una excepción personalizada
            // También puedes lanzar una excepción personalizada si lo deseas
            // throw new MiExcepcionPersonalizada("Error al buscar tratamiento por ID", ex);
            return null; // Retorna null o toma otra acción según lo que sea adecuado para tu aplicación
        }
    }

    /**
     * Obtiene una lista de todos los tratamientos almacenados en la base de
     * datos.
     *
     * @return Lista de tratamientos encontrados.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public List<Tratamiento> obtenerTratamientos() {
        List<Tratamiento> tratamientos = new ArrayList<>();
        String sql = "SELECT * FROM tratamientos";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            resultado = consultarBase(preparedStatement);
            if (resultado != null) {
                while (resultado.next()) {
                    tratamientos.add(obtenerTratamientoDesdeResultado(resultado));
                }
                resultado.close();
            }
        } catch (SQLException ex) {
            // Captura la excepción y regístrala en el sistema de registro
            ex.printStackTrace(); // Este es un ejemplo básico, podrías utilizar un sistema de registro más avanzado
            // También puedes lanzar una nueva excepción personalizada si lo deseas
        }
        return tratamientos;
    }

    /**
     * Realiza una eliminación lógica de un tratamiento en la base de datos.
     *
     * @param tipo El tipo del tratamiento a dar de baja.
     * @return El resultado de la operación de baja.
     * @throws Exception Si ocurre un error de SQL.
     */
    public int eliminarLogico(String tipo) throws Exception {
        String sql = "UPDATE tratamientos SET estado=? WHERE tipo=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, tipo);
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    /**
     * Cuenta el total de registros en la tabla de tratamientos.
     *
     * @return La cantidad total de registros.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public int contarTotalRegistros() {
        String sql = "SELECT COUNT(*) FROM tratamientos";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count + 1;
            }
    } catch (SQLException ex) {
        // Manejar la excepción si es necesario
        ex.printStackTrace(); // Otra opción es utilizar un sistema de registro para registrar el error
        // Puedes lanzar una excepción personalizada si lo deseas
        // throw new MiExcepcionPersonalizada("Error al contar los registros", ex);
    }
        return 0; // Devuelve 0 si no se encontraron registros
    }

    /**
     * Obtiene un tratamiento por su ID en la base de datos.
     *
     * @param idTratamiento El ID del tratamiento a buscar.
     * @return El tratamiento encontrado o null si no se encuentra.
     * @throws Exception Si ocurre un error de SQL.
     */
    public Tratamiento obtenerTratamientoxId(int idTratamiento) throws Exception {
        String sql = "SELECT * FROM `tratamientos` WHERE idTratamiento=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idTratamiento);
            resultado = consultarBase(preparedStatement);
            Tratamiento tratamiento = null;
            if (resultado.next()) {
                tratamiento = obtenerTratamientoDesdeResultado(resultado);
            }
            return tratamiento;
        }
    }

    /**
     * Crea un objeto Tratamiento a partir del resultado de una consulta SQL.
     *
     * @param result El resultado de la consulta SQL.
     * @return Un objeto Tratamiento con los datos obtenidos.
     * @throws SQLException Si ocurre un error de SQL.
     */
    private Tratamiento obtenerTratamientoDesdeResultado(ResultSet result) throws SQLException {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setIdTratamiento(result.getInt("idTratamiento"));
        tratamiento.setTipo(result.getString("tipo"));
        tratamiento.setDescripcion(result.getString("descripcion"));
        tratamiento.setImporte(result.getDouble("importe"));
        tratamiento.setEstado(result.getBoolean("estado"));

        return tratamiento;
    }

    /**
     * Valida si un tratamiento es válido para ser almacenado en la base de
     * datos.
     *
     * @param tratamiento El tratamiento a validar.
     * @throws Exception Si el tratamiento no es válido.
     */
    private void validarTratamiento(Tratamiento tratamiento) throws Exception {
        if (tratamiento == null) {
            throw new Exception("Debe indicar un Tratamiento");
        }
        // Puedes agregar más validaciones según tus requerimientos
    }
}
