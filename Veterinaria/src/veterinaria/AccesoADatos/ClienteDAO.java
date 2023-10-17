package veterinaria.AccesoADatos;


import veterinaria.Entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Statement;

public class ClienteDAO extends DAO {

    private ClienteDAO() {
        // Constructor privado para evitar la creación de instancias desde fuera de la clase
    }

    public static ClienteDAO obtenerInstancia() {
        return ClienteDAOHolder.INSTANCE;
    }

    private static class ClienteDAOHolder {

        private static final ClienteDAO INSTANCE = new ClienteDAO();
    }

    /**
     * Guarda un nuevo cliente en la base de datos.
     *
     * @param cliente El objeto Cliente a guardar.
     * @return El ID generado para el cliente. Devuelve -1 si ocurre un error.
     */
    public int guardarCliente(Cliente cliente) throws Exception {
        try {
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
        } catch (ClassNotFoundException | SQLException ex) {
            // Manejar la excepción aquí, ya sea registrándola, mostrando un mensaje o loggeándola.
            // Por ejemplo, puedes imprimir el mensaje de error en la consola:
            System.err.println("Error al guardar el cliente: " + ex.getMessage());
            // Devolver un valor específico para indicar que ocurrió un error (por ejemplo, -1).
            return -1;
        }
    }

    /**
     * Modifica un cliente existente en la base de datos.
     *
     * @param cliente El objeto Cliente modificado.
     * @return El ID del cliente modificado.
     * @throws Exception Si el cliente no es válido.
     */
    public int modificarCliente(Cliente cliente) throws Exception {
        validarCliente(cliente);
        String sql = "UPDATE clientes SET apellido=?, nombre=?, direccion=?, telefono=?, contactoN=?,contactoTel=?,estado=?, correoElectronico=? WHERE dni=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cliente.getApellido());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getContactoNombre());
            preparedStatement.setString(6, cliente.getContactoTelefono());
            preparedStatement.setBoolean(7, cliente.isEstado());
            preparedStatement.setString(8, cliente.getEmail());
            preparedStatement.setInt(9, cliente.getDni());

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción aquí, ya sea registrándola, mostrando un mensaje o loggeándola.
            // Por ejemplo, puedes imprimir el mensaje de error en la consola:
            System.err.println("Error al guardar el cliente: " + ex.getMessage());
            // Devolver un valor específico para indicar que ocurrió un error (por ejemplo, -1).
            return -1;
        }
    }

    /**
     * Realiza una baja lógica del cliente con el DNI dado.
     *
     * @param dni El DNI del cliente a dar de baja.
     * @throws Exception Si ocurre un error durante la operación.
     */
    public int bajaLogica(int dni) throws Exception {
        String sql = "UPDATE clientes SET estado=? WHERE dni=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, dni);
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    /**
     * Realiza una alta lógica del cliente con el DNI dado.
     *
     * @param dni El DNI del cliente a dar de alta.
     * @throws Exception Si ocurre un error durante la operación.
     */
    public int altaLogica(int dni) throws Exception {
        String sql = "UPDATE clientes SET estado=? WHERE dni=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, dni);
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    /**
     * Busca un cliente en la base de datos por su DNI.
     *
     * @param dni El DNI del cliente a buscar.
     * @return El objeto Cliente encontrado, o null si no se encuentra.
     * @throws Exception Si ocurre un error durante la operación.
     */
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

    /**
     * Obtiene un cliente desde el resultado de una consulta SQL.
     *
     * @param result El resultado de la consulta SQL.
     * @return El objeto Cliente obtenido desde el resultado.
     * @throws SQLException Si ocurre un error de SQL durante la operación.
     */
// Método para obtener un cliente desde un resultado de consulta SQL
    private Cliente obtenerClienteDesdeResultado(ResultSet result) throws SQLException {
        Cliente cliente = new Cliente();
//        try {
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
//        } catch (SQLException ex) {
//            // Manejar excepción de SQL
//            throw new Exception("Error al obtener datos del cliente desde el resultado", ex);
//        }
        return cliente;
    }

    public Cliente obtenerClientexId(int idCliente) {
        String sql = "SELECT * FROM `clientes` WHERE idCliente=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCliente);

            resultado = consultarBase(preparedStatement);
            Cliente cliente = null;
            if (resultado.next()) {
                cliente = obtenerClienteDesdeResultado(resultado);
            }
            return cliente;

        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            ex.printStackTrace(); // Otra opción es utilizar un sistema de registro para registrar el error
            // Puedes lanzar una excepción personalizada si lo deseas
            // throw new MiExcepcionPersonalizada("Error al obtener cliente desde la base de datos", ex);
            return null; // Retorna null o cualquier valor que indique la falta de datos según tu lógica de negocio
        }
    }

    /**
     * Obtiene una lista de todos los clientes en la base de datos.
     *
     * @return Una lista de objetos Cliente que representa a todos los clientes
     * en la base de datos.
     * @throws ClassNotFoundException Si no se encuentra la clase de la base de
     * datos.
     * @throws SQLException Si ocurre un error de SQL durante la operación.
     */
    public List<Cliente> obtenerClientes() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            resultado = consultarBase(preparedStatement);
            if (resultado != null) {
                while (resultado.next()) {
                    clientes.add(obtenerClienteDesdeResultado(resultado));
                }
                resultado.close();
            }
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            ex.printStackTrace(); // Otra opción es utilizar un sistema de registro para registrar el error
            // Puedes lanzar una excepción personalizada si lo deseas
            // throw new MiExcepcionPersonalizada("Error al obtener clientes desde la base de datos", ex);
        }
        return clientes;
    }

    /**
     * Obtiene una colección de todos los clientes en la base de datos.
     *
     * @return Una colección de objetos Cliente que representa a todos los
     * clientes en la base de datos.
     * @throws Exception Si ocurre un error durante la operación.
     */
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

    /**
     * Valida si el objeto Cliente es válido (no nulo).
     *
     * @param cliente El objeto Cliente a validar.
     * @throws Exception Si el cliente es nulo.
     */
    private void validarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo.");
        }
    }
}
