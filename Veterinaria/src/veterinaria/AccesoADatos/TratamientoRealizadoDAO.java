
package veterinaria.AccesoADatos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import veterinaria.Entidades.TratamientoRealizado;
import veterinaria.Entidades.Visita;


public class TratamientoRealizadoDAO extends DAO {

    public TratamientoRealizadoDAO() throws ClassNotFoundException, SQLException {
        
          conectarBase();
    }
    
        public void guardarTratamientoRealizado(TratamientoRealizado tratamientoRealizado) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tratamientosrealizados (idVisita, idMascota, idTratamiento, importe ) VALUES (?, ?, ?,?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, tratamientoRealizado.getIdVisita().getIdVisita());
            preparedStatement.setInt(2, tratamientoRealizado.getIdMascota().getIdMascota());
            preparedStatement.setInt(3, tratamientoRealizado.getIdTratamiento().getIdTratamiento());
            preparedStatement.setDouble(4, tratamientoRealizado.getImporte());
            

           insertarModificarEliminar(preparedStatement);
        }
    }
    
}
