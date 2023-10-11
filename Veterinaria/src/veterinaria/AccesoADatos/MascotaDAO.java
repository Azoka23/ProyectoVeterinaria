package veterinaria.AccesoADatos;

import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Sexo;

import veterinaria.Utilidades;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

public class MascotaDAO extends DAO {

    public MascotaDAO() throws ClassNotFoundException, SQLException {
        conectarBase();
    }

    public void guardarMascota(Mascota mascota) throws Exception {
        //Utilidades.validar(mascota);
        validarMascota(mascota);
        String sql = "INSERT INTO mascotas (alias, sexo, especie, raza, colorDePelo, fechaNac, pesoM,pesoA,idCliente, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, mascota.getAlias());
            preparedStatement.setString(2, mascota.getSexo().name());
            preparedStatement.setString(3, mascota.getEspecie());
            preparedStatement.setString(4, mascota.getRaza());
            preparedStatement.setString(5, mascota.getColorDePelo());
            preparedStatement.setDate(6, Date.valueOf(mascota.getFechaNacimiento()));
            preparedStatement.setDouble(7, mascota.getPesoMedia());
            preparedStatement.setDouble(8, mascota.getPesoActual());
            preparedStatement.setInt(9, mascota.getIdCliente().getIdCliente());
            preparedStatement.setBoolean(10, mascota.isEstado());
//JOptionPane.showMessageDialog(null, preparedStatement);
            insertarModificarEliminar(preparedStatement);

//        } catch (SQLException ex) {
//            // Manejar la excepción si es necesario
//            throw ex;
//        } finally {
//            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public int modificarMascota(Mascota mascota) throws Exception {
        // Utilidades.validar(mascota);
        validarMascota(mascota);
        String sql = "UPDATE mascotas SET alias=?, sexo=?, especie=?, raza=?, colorDePelo=?, fechaNac=?, pesoM=?,pesoA=?,idCliente=?, estado=? WHERE idMascota=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, mascota.getAlias());
            preparedStatement.setString(2, mascota.getSexo().name());
            preparedStatement.setString(3, mascota.getEspecie());
            preparedStatement.setString(4, mascota.getRaza());
            preparedStatement.setString(5, mascota.getColorDePelo());
            preparedStatement.setDate(6, Date.valueOf(mascota.getFechaNacimiento()));
            preparedStatement.setDouble(7, mascota.getPesoMedia());
            preparedStatement.setDouble(8, mascota.getPesoActual());
            preparedStatement.setInt(9, mascota.getIdCliente().getIdCliente());
            preparedStatement.setBoolean(10, mascota.isEstado());
            preparedStatement.setInt(11, mascota.getIdMascota());

            //JOptionPane.showMessageDialog(null, preparedStatement);
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public void bajaLogica(int codigo) throws Exception {
        String sql = "UPDATE mascotas SET estado=? WHERE idMascota=?";

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
        String sql = "UPDATE mascotas SET estado=? WHERE idMascota=?";

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

    public int contarTotalRegistros() throws Exception {
        //Cuenta la cantidad de registros y cuando devuelvo el entero necesito sumar 1
        String sql = "SELECT COUNT(*) FROM mascotas";
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

    public Mascota buscarListaMascotaxDni(int idMascota) throws Exception {
        String sql = "SELECT * FROM `mascotas` WHERE idMascota=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMascota);

            resultado = consultarBase(preparedStatement);
//JOptionPane.showMessageDialog(null, resultado);
            Mascota mascota = null;

            if (resultado.next()) {
                mascota = obtenerMascotaDesdeResultado(resultado);
            }
            return mascota;

        }
    }
        public Mascota buscarListaMascotaxAliasIdCliente(String alias,int idCliente) throws Exception {
        String sql = "SELECT * FROM `mascotas` WHERE alias=? AND idCliente=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
           
            preparedStatement.setString(1, alias);
            preparedStatement.setInt(2, idCliente);
//JOptionPane.showMessageDialog(null, preparedStatement);
            resultado = consultarBase(preparedStatement);
//JOptionPane.showMessageDialog(null, resultado);
            Mascota mascota = null;

            if (resultado.next()) {
                mascota = obtenerMascotaDesdeResultado(resultado);
            }
            return mascota;

        }
    }
    public Collection<Mascota> buscarListaMascotaxAlias(String alias) throws Exception {
        String sql = "SELECT DISTINCT * FROM `mascotas` WHERE alias=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, alias);
            resultado = consultarBase(preparedStatement);

            Collection<Mascota> mascotas = new ArrayList();

            while (resultado.next()) {
                mascotas.add(obtenerMascotaDesdeResultado(resultado));
            }

            return mascotas;

        }
    }
    
    public Mascota obtenerMascotaPorId(int idMascota) throws Exception {
        String sql = "SELECT * FROM `mascotas` WHERE idMascota=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMascota);
            resultado = consultarBase(preparedStatement);

            Mascota mascota = null;

            if (resultado.next()) {

                mascota = obtenerMascotaDesdeResultado(resultado);

            }

            return mascota;

        }
    }

    public Collection<Mascota> listarMascotas() throws Exception {
        String sql = "SELECT * FROM `mascotas`";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            resultado = consultarBase(preparedStatement);

            Collection<Mascota> mascotas = new ArrayList();

            while (resultado.next()) {
                mascotas.add(obtenerMascotaDesdeResultado(resultado));
            }

            return mascotas;

        }
    }

    public Collection<Mascota> listarMascotasxIdCliente(int idCliente) throws Exception {
        String sql = "SELECT * FROM `mascotas` WHERE idCliente=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCliente);
            resultado = consultarBase(preparedStatement);

            Collection<Mascota> mascotas = new ArrayList();

            while (resultado.next()) {
                mascotas.add(obtenerMascotaDesdeResultado(resultado));
            }

            return mascotas;

        }
    }

    private Mascota obtenerMascotaDesdeResultado(ResultSet result) throws SQLException, ClassNotFoundException, Exception {
        // Crear una instancia de ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();

        Mascota mascota = new Mascota();

        mascota.setIdMascota(result.getInt("idMascota"));

        mascota.setAlias(result.getString("alias"));
        mascota.setSexo(Sexo.valueOf(result.getString("sexo")));
        mascota.setEspecie(result.getString("especie"));
        mascota.setRaza(result.getString("raza"));
        mascota.setColorDePelo(result.getString("colorDePelo"));
        mascota.setFechaNacimiento(result.getDate("fechaNac").toLocalDate());
        mascota.setPesoMedia(result.getDouble("pesoM"));
        mascota.setPesoActual(result.getDouble("pesoA"));
        mascota.setIdCliente(clienteDAO.obtenerClientexId(result.getInt("idCliente")));
        mascota.setEstado(result.getBoolean("estado"));

        return mascota;
    }

    private void validarMascota(Mascota mascota) throws Exception {
        if (mascota == null) {
            throw new Exception("Debe indicar un Cliente");
        }
    }
}
