package veterinaria.AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

// Clase base para acceder a la base de datos y realizar operaciones CRUD
public class DAO {

    private static DAO instancia; // Instancia única de la clase (patrón Singleton)
    protected int filasAfectadas; // Número de filas afectadas por la última operación
    protected Connection conexion = null; // Objeto de conexión a la base de datos
    protected ResultSet resultado = null; // Resultado de las consultas a la base de datos
    protected Statement sentencia = null; // Sentencia SQL para ejecutar consultas sin parámetros

    private final String USUARIO = "root"; // Nombre de usuario para la base de datos
    private final String PASSWORD = ""; // Contraseña para la base de datos
    private final String DB = "clinicavete"; // Nombre de la base de datos
    private final String DRIVER = "org.mariadb.jdbc.Driver"; // Driver para la conexión a la base de datos
    private final String URL = "jdbc:mariadb://Localhost:3306/"; // URL de la base de datos

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    protected DAO() {
        try {
            // Cargar el driver de la base de datos y establecer la conexión
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL + DB + "?useSSL=false&serverTimezone=UTC", USUARIO, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            // Manejar las excepciones si ocurren al establecer la conexión
            ex.printStackTrace();
        }
    }

    // Método para obtener la instancia única de la clase (patrón Singleton)
    public static synchronized DAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new DAO(); // Crea una nueva instancia si no existe una
        }
        return instancia;
    }

    /**
     * Método para consultar la base de datos.
     * 
     * @param preparedStatement La consulta preparada a ejecutar.
     * @return El resultado de la consulta.
     */
    protected ResultSet consultarBase(PreparedStatement preparedStatement) {
        try {
            // Ejecutar la consulta y devolver el resultado
            ResultSet resultado = preparedStatement.executeQuery();
            return resultado;
        } catch (SQLException e) {
            // Mostrar un mensaje de error en caso de fallo y devolver null
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta.");
            return null;
        }
    }

    /**
     * Método para insertar, modificar o eliminar registros en la base de datos.
     * 
     * @param preparedStatement La consulta preparada a ejecutar.
     * @return El ID generado por la operación, -1 si no se generó ninguna clave.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */
    public int insertarModificarEliminar(PreparedStatement preparedStatement) throws SQLException {
        int idGenerado = -1; // Valor predeterminado en caso de que no se genere ninguna clave

        try {
            // Ejecutar la PreparedStatement que recibiste como argumento y obtener las claves generadas
            filasAfectadas = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1); // Obtener el ID generado
                }
            }
        } catch (SQLException ex) {
            // Manejar las excepciones si ocurren al insertar, modificar o eliminar registros
            ex.printStackTrace();
        }

        return idGenerado;
    }
}
