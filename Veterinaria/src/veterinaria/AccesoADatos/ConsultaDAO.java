package veterinaria.AccesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import veterinaria.Entidades.ConsultasLista;

// Clase para acceder a los datos de la tabla de consultas en la base de datos
public class ConsultaDAO extends DAO {

    // Constructor privado para seguir el patrón Singleton
    private ConsultaDAO() {
    }

    // Clase interna para manejar la instancia única de ConsultaDAO (patrón Singleton)
    private static class ConsultaDAOHolder {

        private static final ConsultaDAO INSTANCE = new ConsultaDAO();
    }

    // Método para obtener la instancia única de ConsultaDAO (patrón Singleton)
    public static ConsultaDAO obtenerInstancia() {
        return ConsultaDAOHolder.INSTANCE;
    }

    // Método para listar todas las consultas almacenadas en la base de datos
    public Collection<ConsultasLista> listarConsultas() throws Exception {
        // Consulta SQL para seleccionar todas las filas de la tabla de consultas
        String sql = "SELECT * FROM consultas;";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            // Ejecuta la consulta y obtiene el resultado
            resultado = consultarBase(preparedStatement);

            // Colección para almacenar las consultas recuperadas de la base de datos
            Collection<ConsultasLista> consultas = new ArrayList();

            // Itera a través del resultado y crea objetos de ConsultasLista con los datos recuperados
            while (resultado.next()) {
                consultas.add(obtenerConsultaDesdeResultado(resultado));
            }

            // Devuelve la colección de consultas recuperadas
            return consultas;
        }
    }

    // Método para crear un objeto ConsultasLista a partir de un resultado de base de datos
    private ConsultasLista obtenerConsultaDesdeResultado(ResultSet result) throws SQLException {
        // Crea un nuevo objeto ConsultasLista
        ConsultasLista consulta = new ConsultasLista();

        // Establece los atributos del objeto ConsultasLista usando los datos del resultado
        consulta.setIdConsulta(result.getInt("idConsulta"));
        consulta.setConsulta(result.getString("consulta"));
        consulta.setDescripcion(result.getString("descripcion"));

        // Devuelve el objeto ConsultasLista creado
        return consulta;
    }
}
