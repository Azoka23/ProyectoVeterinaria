package veterinaria.AccesoADatos;

import veterinaria.Entidades.Visita;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitaDAO extends DAO {

    public VisitaDAO() throws ClassNotFoundException, SQLException {
        conectarBase();
    }
    
    

    public void guardarVisita(Visita visita) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO visitas (idMascota, idTratamiento, fechaV,detalleSintomas, pesoActual ) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, visita.getIdMascota().getIdMascota());
            preparedStatement.setInt(2, visita.getIdTratamiento().getIdTratamiento());
            preparedStatement.setDate(3, Date.valueOf(visita.getFechaVisita()));
            preparedStatement.setDouble(4, visita.getPesoActual());
            preparedStatement.setString(5, visita.getDetallesSintoma());

            insertarModificarEliminar(preparedStatement);
        }
    }

}
