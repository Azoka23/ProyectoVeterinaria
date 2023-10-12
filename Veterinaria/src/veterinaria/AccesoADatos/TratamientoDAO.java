package veterinaria.AccesoADatos;

import java.sql.Date;
import veterinaria.Entidades.Tratamiento;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import veterinaria.Entidades.Cliente;
import veterinaria.Utilidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import veterinaria.Entidades.Mascota;

public class TratamientoDAO extends DAO {

    public TratamientoDAO() throws ClassNotFoundException, SQLException {
        conectarBase();
    }

    public void guardarTratamiento(Tratamiento tratamiento) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO tratamientos (tipo, descripcion, importe, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tratamiento.getTipo());
            preparedStatement.setString(2, tratamiento.getDescripcion());
            preparedStatement.setDouble(3, tratamiento.getImporte());
            preparedStatement.setBoolean(4, tratamiento.isEstado());

            insertarModificarEliminar(preparedStatement);
        }

    }

    public int modificarTratamiento(Tratamiento tratamiento) throws Exception {
        // Utilidades.validar(mascota);
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
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public void bajaLogica(int codigo) throws Exception {
        String sql = "UPDATE tratamientos SET estado=? WHERE idTratamiento=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, codigo);

            insertarModificarEliminar(preparedStatement);

        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public void altaLogica(int codigo) throws Exception {
        String sql = "UPDATE tratamientos SET estado=? WHERE idTratamiento=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, codigo);

            insertarModificarEliminar(preparedStatement);

        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public Tratamiento buscarListaTratamientoxId(int idTratamiento) throws Exception {
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

    public List<Tratamiento> obtenerTratamientos() throws ClassNotFoundException, SQLException {
        List<Tratamiento> tratamientos = new ArrayList<>();
        String sql = "SELECT * FROM tratamientos";

        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        resultado = consultarBase(preparedStatement);
        if (resultado != null) {
            while (resultado.next()) {

                tratamientos.add(obtenerTratamientoDesdeResultado(resultado));
            }
            resultado.close();
        }
        return tratamientos;
    }

    public void eliminarLogico(String tipo) throws Exception {
        String sql = "UPDATE tratamientos SET estado=? WHERE tipo=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, tipo);
            insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public int contarTotalRegistros() throws Exception {
        //Cuenta la cantidad de registros y cuando devuelvo el entero necesito sumar 1
        String sql = "SELECT COUNT(*) FROM tratamientos";
        //Obtengo el idMateria max, cuando devuelvo el entero sumo 1 --
        //y en este caso si por algun motivo manualmente borre un registro de la tabla, de esta forma siempre obtengo el numero correcto
        //String sql = "SELECT MAX(idMateria) FROM materias" 
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count + 1;
            }
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
        return 0; // Devuelve 0 si no se encontraron registros
    }

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


    private Tratamiento obtenerTratamientoDesdeResultado(ResultSet result) throws SQLException {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setIdTratamiento(result.getInt("idTratamiento"));
        tratamiento.setTipo(result.getString("tipo"));
        tratamiento.setDescripcion(result.getString("descripcion"));
        tratamiento.setImporte(result.getDouble("importe"));
        tratamiento.setEstado(result.getBoolean("estado"));

        return tratamiento;
    }

    private void validarTratamiento(Tratamiento tratamiento) throws Exception {
        if (tratamiento == null) {
            throw new Exception("Debe indicar un Cliente");
        }
    }
}
