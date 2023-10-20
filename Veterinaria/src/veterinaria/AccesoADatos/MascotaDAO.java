package veterinaria.AccesoADatos;

// Importaciones de clases necesarias
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Sexo;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

// Clase para interactuar con la tabla 'mascotas' en la base de datos
public class MascotaDAO extends DAO {

    // Instancia única de MascotaDAO inicializada de forma segura y perezosa
    private static final MascotaDAO INSTANCE = new MascotaDAO();

    // Constructor privado para evitar la creación de instancias fuera de la clase
    private MascotaDAO() {
    }

    // Método estático para obtener la instancia única de MascotaDAO
    public static MascotaDAO obtenerInstancia() {
        return INSTANCE;
    }

    // Método para guardar una nueva mascota en la base de datos
    public int guardarMascota(Mascota mascota) throws Exception {
        validarMascota(mascota);
        String sql = "INSERT INTO mascotas (alias, sexo, especie, raza, colorDePelo, fechaNac, pesoM,pesoA,idCliente, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            return insertarModificarEliminar(preparedStatement);

        } catch (SQLException ex) {
            // Manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar según la lógica de la aplicación
        }
    }

    // Método para modificar los detalles de una mascota existente en la base de datos
    public int modificarMascota(Mascota mascota) {
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

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar según la lógica de la aplicación
        }
    }

    // Método para modificar el peso actual de una mascota en la base de datos
    public int modificarMascotaPeso(Mascota mascota) throws Exception {
        validarMascota(mascota);
        String sql = "UPDATE mascotas SET pesoA=?, pesoM=? WHERE idMascota=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, mascota.getPesoActual());
            preparedStatement.setDouble(2, mascota.getPesoMedia());
            preparedStatement.setInt(3, mascota.getIdMascota());

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar según la lógica de la aplicación
        }
    }

    // Método para realizar una baja lógica de una mascota en la base de datos
    public int bajaLogica(int codigo) throws Exception {
        String sql = "UPDATE mascotas SET estado=? WHERE idMascota=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, codigo);

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar según la lógica de la aplicación
        }
    }

    // Método para realizar una alta lógica de una mascota en la base de datos
    public int altaLogica(int codigo) throws Exception {
        String sql = "UPDATE mascotas SET estado=? WHERE idMascota=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, codigo);

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar según la lógica de la aplicación
        }
    }

    // Método para contar el total de registros en la tabla 'mascotas'
    public int contarTotalRegistros() throws Exception {
        String sql = "SELECT COUNT(*) FROM mascotas";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count + 1;
            }
        } catch (SQLException ex) {
            // Manejar la excepción SQL
            ex.printStackTrace();
        }
        return 0; // Devuelve 0 si no se encontraron registros
    }

    // Método para buscar una mascota por su ID en la base de datos
    public Mascota obtenerMascotaPorId(int idMascota) {
        String sql = "SELECT * FROM `mascotas` WHERE idMascota=?";
        Mascota mascota = null;

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMascota);
            resultado = consultarBase(preparedStatement);

            if (resultado.next()) {

                mascota = obtenerMascotaDesdeResultado(resultado);

            }

        } catch (SQLException ex) {
            // Manejar las excepciones de SQL
            ex.printStackTrace();
        }

        return mascota;
    }

    public Mascota buscarListaMascotaxAliasIdCliente(String alias, int idCliente) throws Exception {
        String sql = "SELECT * FROM `mascotas` WHERE alias=? AND idCliente=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setString(1, alias);
            preparedStatement.setInt(2, idCliente);
            resultado = consultarBase(preparedStatement);
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

    // Método para listar todas las mascotas almacenadas en la base de datos
    public Collection<Mascota> listarMascotas() throws Exception {
        String sql = "SELECT * FROM `mascotas`";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            resultado = consultarBase(preparedStatement);

            Collection<Mascota> mascotas = new ArrayList();

            while (resultado.next()) {
                mascotas.add(obtenerMascotaDesdeResultado(resultado));
            }

            return mascotas;

        } catch (SQLException ex) {
            // Manejar las excepciones de SQL
            ex.printStackTrace();
        }
        return new ArrayList<>(); // Retorna una lista vacía si no se encontraron registros
    }

    public Collection<Mascota> listarMascotasPorEstado(boolean activo) throws Exception {
        Collection<Mascota> mascotasFiltradas = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE estado = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, activo);
            resultado = consultarBase(preparedStatement);

            while (resultado.next()) {
                Mascota mascota = obtenerMascotaDesdeResultado(resultado);
                mascotasFiltradas.add(mascota);
            }
        } catch (SQLException ex) {
            // Manejar la excepción si es necesario
            ex.printStackTrace();
            // Puedes lanzar una excepción personalizada si lo deseas
            // throw new MiExcepcionPersonalizada("Error al obtener mascotas desde la base de datos", ex);
        }

        return mascotasFiltradas;
    }

    public Collection<Mascota> listarMascotasXTipoTratamiento(int idTratamiento) throws Exception {
        String sql = "SELECT m.* "
                + "FROM mascotas m "
                + "JOIN tratamientosrealizados tr ON m.idMascota = tr.idMascota "
                + "WHERE tr.idTratamiento = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, idTratamiento);

            resultado = consultarBase(preparedStatement);

            Collection<Mascota> mascotas = new ArrayList();

            while (resultado.next()) {

                mascotas.add(obtenerMascotaDesdeResultado(resultado));
                //JOptionPane.showMessageDialog(null, obtenerMascotaDesdeResultado(resultado));
            }

            return mascotas;

        }
    }

    // Método para listar las mascotas de un cliente específico por su ID
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

        } catch (SQLException ex) {
            // Manejar las excepciones de SQL
            ex.printStackTrace();
        }
        return new ArrayList<>(); // Retorna una lista vacía si no se encontraron registros
    }

// Método para obtener una mascota desde un resultado de consulta SQL
    private Mascota obtenerMascotaDesdeResultado(ResultSet result) throws SQLException {
        ClienteDAO clienteDAO = ClienteDAO.obtenerInstancia();
        Mascota mascota = new Mascota();

//        try {
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

    // Método para validar una mascota antes de realizar operaciones en la base de datos
    private void validarMascota(Mascota mascota) {
        if (mascota == null) {
            try {
                throw new Exception("Debe indicar una mascota válida");
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(MascotaDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    public int obtenerIdMascotaPorNombre(String nombreMascota, int idCliente) {
        String sql = "SELECT idMascota FROM mascotas WHERE nombre = ? and idCliente=?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, nombreMascota);
            statement.setInt(2, idCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idMascota");
                } else {
                    return -1; // No se encontró la mascota
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error al ejecutar la consulta
        }
    }
}
