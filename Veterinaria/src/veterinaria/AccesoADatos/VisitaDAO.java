package veterinaria.AccesoADatos;

import veterinaria.Entidades.Visita;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class VisitaDAO extends DAO {

    public VisitaDAO() throws ClassNotFoundException, SQLException {
        conectarBase();
    }

    public int guardarVisita(Visita visita) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO visitas (fechaV,detallesSintomas, pesoActual,importe ) VALUES ( ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
           // preparedStatement.setInt(1, visita.getIdMascota().getIdMascota());
           // preparedStatement.setInt(2, visita.getIdTratamiento().getIdTratamiento());
            preparedStatement.setDate(1, Date.valueOf(visita.getFechaVisita()));
            preparedStatement.setString(2, visita.getDetallesSintoma());
            preparedStatement.setDouble(3, visita.getPesoActual());
            preparedStatement.setDouble(4, visita.getImporteVisita());
            

            return insertarModificarEliminar(preparedStatement);
        }
    }

}
