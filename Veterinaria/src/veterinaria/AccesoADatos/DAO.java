package veterinaria.AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class DAO {

    protected int filasAfectadas;
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;

    private final String USUARIO = "root";
    private final String PASSWORD = "";
    private final String DB = "clinicavete";
    private final String DRIVER = "org.mariadb.jdbc.Driver";
    private final String URL = "jdbc:mariadb://Localhost:3306/";

    protected void conectarBase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL + DB + "?useSSL=false&serverTimezone=UTC", USUARIO, PASSWORD);

        } catch (ClassNotFoundException | SQLException ex) {

            throw ex;
        }
    }

    protected void desconectarBase() {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al desconectarse");
        }
    }

    protected ResultSet consultarBase(PreparedStatement preparedStatement) {
        try {
            // Ejecutar la consulta
            ResultSet resultado = preparedStatement.executeQuery();
            return resultado;
        } catch (SQLException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No encontro");
            return null; // Devuelve null en caso de error
        }
    }

//    protected void insertarModificarEliminar(PreparedStatement preparedStatement) throws ClassNotFoundException, SQLException {
//      //  try {
//      
//            conectarBase();
//            // En su lugar, ejecuta la PreparedStatement que recibiste como argumento
//            filasAfectadas = preparedStatement.executeUpdate();
//
////        } catch (SQLException ex) {
////
////            JOptionPane.showMessageDialog(null, "No se ejecuto");
////        }
//    }
    protected int insertarModificarEliminar(PreparedStatement preparedStatement) throws SQLException, ClassNotFoundException {
        int idGenerado = -1; // Valor predeterminado en caso de que no se genere ninguna clave

        try {
            conectarBase();
            // Ejecuta la PreparedStatement que recibiste como argumento y obtén las claves generadas
            filasAfectadas = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1); // Obtén el ID generado
                }
            }
        } finally {
            desconectarBase();
        }

        return idGenerado;
    }
}
