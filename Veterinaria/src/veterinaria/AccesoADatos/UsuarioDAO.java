package veterinaria.AccesoADatos;

import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Utilidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import veterinaria.Entidades.Usuario;

public class UsuarioDAO extends DAO {

    private UsuarioDAO() {
        // Constructor privado para evitar la creación de instancias desde fuera de la clase
    }

    public static UsuarioDAO obtenerInstancia() {
        return UsuarioDAOHolder.INSTANCE;
    }

    private static class UsuarioDAOHolder {

        private static final UsuarioDAO INSTANCE = new UsuarioDAO();
    }

    public int guardarUsuario(Usuario usuario) throws Exception {
        try {
            validarUsuario(usuario);
            String sql = "INSERT INTO clientes (nombre, password, rol, estado) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, usuario.getNombre());
                preparedStatement.setString(2, usuario.getPassword());
                preparedStatement.setInt(3, usuario.getRol());
                preparedStatement.setBoolean(4, usuario.isEstado());

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


    public int modificarUsuario(Usuario usuario) throws Exception {
        validarUsuario(usuario);
        String sql = "UPDATE usuario SET =?, nombre=?, password=?, rol=?, estado=? WHERE idUsuario=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setInt(3, usuario.getRol());
            preparedStatement.setBoolean(4, usuario.isEstado());
            preparedStatement.setInt(5, usuario.getIdUsuario());

            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Manejar la excepción aquí, ya sea registrándola, mostrando un mensaje o loggeándola.
            // Por ejemplo, puedes imprimir el mensaje de error en la consola:
            System.err.println("Error al guardar el usuario: " + ex.getMessage());
            // Devolver un valor específico para indicar que ocurrió un error (por ejemplo, -1).
            return -1;
        }
    }

    public int bajaLogica(String nombre) throws Exception {
        String sql = "UPDATE clientes SET estado=? WHERE nombre=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, nombre);
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }
      public int altaLogica(String nombre) throws Exception {
        String sql = "UPDATE clientes SET estado=? WHERE nombre=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, nombre);
            return insertarModificarEliminar(preparedStatement);
        } catch (SQLException ex) {
            // Capturar y manejar la excepción SQL
            ex.printStackTrace();
            return -1; // Retornar un código de error específico o manejar de acuerdo a la lógica de tu aplicación
        }
    }

    public Usuario buscarListaUsuarioxNombre(String nombre) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE nombre=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            JOptionPane.showMessageDialog(null, nombre);
            resultado = consultarBase(preparedStatement);

            Usuario usuario = null;

            if (resultado.next()) {

                usuario = obtenerUsuarioDesdeResultado(resultado);
            }

            return usuario;
        }

    }

    public Usuario buscarListaUsuarioxPassword(String password) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE password=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, password);
            JOptionPane.showMessageDialog(null, password);
            resultado = consultarBase(preparedStatement);

            Usuario usuario = null;

            if (resultado.next()) {

                usuario = obtenerUsuarioDesdeResultado(resultado);
            }

            return usuario;
        }

    }

    public Usuario obtenerUsuarioxNombre(String nombre) throws Exception {
        String sql = "SELECT * FROM `usuarios` WHERE nombre=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);

            resultado = consultarBase(preparedStatement);
            Usuario usuario = null;
            if (resultado.next()) {
                usuario = obtenerUsuarioDesdeResultado(resultado);
            }
            return usuario;

        }
    }

    public List<Usuario> obtenerUsuarios() throws ClassNotFoundException, SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        resultado = consultarBase(preparedStatement);
        if (resultado != null) {
            while (resultado.next()) {

                usuarios.add(obtenerUsuarioDesdeResultado(resultado));
            }
            resultado.close();
        }
        return usuarios;
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
    private Usuario obtenerUsuarioDesdeResultado(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(result.getInt("idUsuario"));
        usuario.setNombre(result.getString("nombre"));
        usuario.setPassword(result.getString("password"));
        usuario.setRol(result.getInt("rol"));
        usuario.setEstado(result.getBoolean("estado"));

        return usuario;
    }

    public Collection<Usuario> listarUsuario() throws Exception {
        String sql = "SELECT * FROM `usuarios`";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            resultado = consultarBase(preparedStatement);
            Collection<Usuario> usuarios = new ArrayList<>();
            while (resultado.next()) {
                usuarios.add(obtenerUsuarioDesdeResultado(resultado));
            }

            return usuarios;

        }
    }

    private void validarUsuario(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("Debe indicar un Usuario");
        }
    }

}
