package veterinaria.AccesoADatos;

import veterinaria.Entidades.Tratamiento;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import veterinaria.Entidades.Cliente;
import veterinaria.Utilidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAO extends DAO {

    public TratamientoDAO() throws ClassNotFoundException, SQLException {
        conectarBase();
    }

    public void insertarTratamiento(Tratamiento tratamiento) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO tratamientos (tipo, descripcion, importe, activo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, tratamiento.getTipo());
            preparedStatement.setString(2, tratamiento.getDescripcion());
            preparedStatement.setDouble(3, tratamiento.getImporte());
            preparedStatement.setBoolean(4, tratamiento.isEstado());

            insertarModificarEliminar(preparedStatement);
        }

    }

    public List<Tratamiento> obtenerTratamientos() throws ClassNotFoundException, SQLException {
        List<Tratamiento> tratamientos = new ArrayList<>();
        String query = "SELECT * FROM tratamientos";

        PreparedStatement preparedStatement = conexion.prepareStatement(query);
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

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
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

    public List<Tratamiento> obtenerTratamiento() throws ClassNotFoundException, SQLException {
        List<Tratamiento> tratamientos = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
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

    private Tratamiento obtenerTratamientoDesdeResultado(ResultSet result) throws SQLException {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setIdTratamiento(result.getInt("idTratamiento"));
        tratamiento.setTipo(result.getString("tipo"));
        tratamiento.setDescripcion(result.getString("descripcion"));
        tratamiento.setImporte(result.getDouble("importe"));
        tratamiento.setEstado(result.getBoolean("estado"));

        return tratamiento;
    }
}
