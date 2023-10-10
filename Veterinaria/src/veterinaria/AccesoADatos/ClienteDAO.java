package veterinaria.AccesoADatos;

//import java.beans.Statement;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class ClienteDAO extends DAO {

    public ClienteDAO() throws ClassNotFoundException, SQLException {
        conectarBase();
    }

//    public void guardarCliente(Cliente cliente) throws ClassNotFoundException, SQLException, Exception {
//
//        //Utilidades.validar(cliente);
//        validarCliente(cliente);
//        String sql = "INSERT INTO clientes (dni, apellido, nombre, direccion, telefono, contactoN,contactoTel,estado,correoElectronico) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
//        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
//            preparedStatement.setInt(1, cliente.getDni());
//            preparedStatement.setString(2, cliente.getApellido());
//            preparedStatement.setString(3, cliente.getNombre());
//            preparedStatement.setString(4, cliente.getDireccion());
//            preparedStatement.setString(5, cliente.getTelefono());
//            preparedStatement.setString(6, cliente.getContactoNombre());
//            preparedStatement.setString(7, cliente.getContactoTelefono());
//            preparedStatement.setBoolean(8, cliente.isEstado());
//            preparedStatement.setString(9, cliente.getEmail());
//
//            insertarModificarEliminar(preparedStatement);
//
//        }
//
//    }
    public int guardarCliente(Cliente cliente) throws ClassNotFoundException, SQLException, Exception {
        validarCliente(cliente);
        String sql = "INSERT INTO clientes (dni, apellido, nombre, direccion, telefono, contactoN, contactoTel, estado, correoElectronico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, cliente.getDni());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getNombre());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.setString(5, cliente.getTelefono());
            preparedStatement.setString(6, cliente.getContactoNombre());
            preparedStatement.setString(7, cliente.getContactoTelefono());
            preparedStatement.setBoolean(8, cliente.isEstado());
            preparedStatement.setString(9, cliente.getEmail());

            return insertarModificarEliminar(preparedStatement);
        }
    }
    
    
    public void modificarCliente(Cliente cliente) throws Exception {
        // Utilidades.validar(cliente);
        validarCliente(cliente);
        String sql = "UPDATE clientes SET apellido=?, nombre=?, direccion=?, telefono=?, contactoN=?,contactoTel=?,estado=?, correoElectronico=? WHERE dni=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, cliente.getApellido());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getContactoNombre());
            preparedStatement.setString(6, cliente.getContactoTelefono());
            preparedStatement.setBoolean(7, cliente.isEstado());
            preparedStatement.setString(8, cliente.getEmail());
            preparedStatement.setInt(9, cliente.getDni());

            insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public void bajaLogica(int dni) throws Exception {
        String sql = "UPDATE clientes SET estado=? WHERE dni=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, dni);
            insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public void altaLogica(int dni) throws Exception {
        String sql = "UPDATE clientes SET estado=? WHERE dni=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, dni);
            insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            throw ex;
        } finally {
            desconectarBase(); // Asegura que la desconexión se realice incluso en caso de excepción.
        }
    }

    public Cliente buscarListaClientexDni(int dni) throws Exception {
        String sql = "SELECT * FROM clientes WHERE dni=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, dni);

            resultado = consultarBase(preparedStatement);

            Cliente cliente = null;

            if (resultado.next()) {

                cliente = obtenerClienteDesdeResultado(resultado);
            }

            return cliente;
        }

    }

    public Cliente obtenerClientexId(int idCliente) throws Exception {
        String sql = "SELECT * FROM `clientes` WHERE idCliente=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCliente);

            resultado = consultarBase(preparedStatement);
            Cliente cliente = null;
            if (resultado.next()) {
                cliente = obtenerClienteDesdeResultado(resultado);
            }
            return cliente;

        }
    }

    public List<Cliente> obtenerClientes() throws ClassNotFoundException, SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        resultado = consultarBase(preparedStatement);
        if (resultado != null) {
            while (resultado.next()) {

                clientes.add(obtenerClienteDesdeResultado(resultado));
            }
            resultado.close();
        }
        return clientes;
    }

//    public Collection<Mascota> obtenerMascotasxIdCliente(int idCliente) throws Exception {
//
//        //MascotaDAO mascotaD = new MascotaDAO();
////        String sql = "SELECT DISTINCT m.idMateria, m.nombre "
////                + "FROM materias m "
////                + "JOIN inscripciones i ON m.idMateria = i.idMateria "
////                + "WHERE i.idAlumno = ? ";
//        String sql = "SELECT * FROM `mascotas` WHERE idCliente=?";
//        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
//            preparedStatement.setInt(1, idCliente);
//            resultado = consultarBase(preparedStatement);
//
//            Collection<Mascota> mascota = new ArrayList();
//            while (resultado.next()) {
//                int idMateria = resultado.getInt("idMascota");
//                mascota.add(mascotaD.obtenerMascotaPorId(idMateria));
//            }
//            return mascota;
//        }
//    }
    private Cliente obtenerClienteDesdeResultado(ResultSet result) throws SQLException {
        Cliente cliente = new Cliente();

        cliente.setIdCliente(result.getInt("idCliente"));
        cliente.setDni(result.getInt("dni"));
        cliente.setApellido(result.getString("apellido"));
        cliente.setNombre(result.getString("nombre"));
        cliente.setDireccion(result.getString("direccion"));
        cliente.setTelefono(result.getString("telefono"));
        cliente.setContactoNombre(result.getString("contactoN"));
        cliente.setContactoTelefono(result.getString("contactoTel"));
        cliente.setEstado(result.getBoolean("estado"));
        cliente.setEmail(result.getString("correoElectronico"));

        return cliente;
    }

    public Collection<Cliente> listarCliente() throws Exception {
        String sql = "SELECT * FROM `clientes`";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            resultado = consultarBase(preparedStatement);
            Collection<Cliente> clientes = new ArrayList<>();
            while (resultado.next()) {
                clientes.add(obtenerClienteDesdeResultado(resultado));
            }

            return clientes;

        }
    }

    private void validarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("Debe indicar un Cliente");
        }
    }

}
