package veterinaria.Vistas;

import veterinaria.CustomPanel;
import veterinaria.DesktopPaneWithBackground;
import veterinaria.Entidades.Estado;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.UsuarioDAO;
import veterinaria.Entidades.Usuario;
import veterinaria.Utilidades;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import veterinaria.AccesoADatos.TratamientoDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Tratamiento;

public class RegistroUsuarios extends javax.swing.JInternalFrame {

    private Usuario usuario;
    private Estado estado = Estado.NADA;
    private String nombre;
    private Usuario selectedCliente = null;
    private boolean estadoUsuario;
    private int idUsuario = 0;
    private boolean estadoCliente;
    private final DefaultTableModel modelo = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    /**
     * Creates new form Visitas
     */
    public RegistroUsuarios(DesktopPaneWithBackground desktopPane) {
        initComponents();
        setTitle("Cargar usuario");
        // Establecer el foco en jTDocumento
        jTNombre.requestFocusInWindow();
        // Agregar KeyListener al campo jTDocumento
        jTNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        estado = Estado.BUSCAR;
                        buscarxNombre();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(RegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new CustomPanel();
        jLApellido = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLMail = new javax.swing.JLabel();
        jLMascota = new javax.swing.JLabel();
        jBBuscar = new javax.swing.JButton();
        jTPassword = new javax.swing.JTextField();
        jTRol = new javax.swing.JTextField();
        jRBEstado = new javax.swing.JRadioButton();
        jTNombre = new javax.swing.JTextField();
        jBGuardar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setTitle("Usuarios");

        jLApellido.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLApellido.setText("Nombre");

        jLNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLNombre.setText("Password");

        jLMail.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLMail.setText("Rol");

        jLMascota.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLMascota.setText("Estado");

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/search_find_lupa_21889.png"))); // NOI18N
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jRBEstado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jRBEstado.setText("Estado");

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/gui_cancel_icon_157198.png"))); // NOI18N
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLMail)
                    .addComponent(jLMascota)
                    .addComponent(jLNombre)
                    .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTNombre)
                    .addComponent(jTPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTRol, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBBuscar)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jRBEstado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jBGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBEliminar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNombre)
                            .addComponent(jTPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jBBuscar)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMail))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBEstado)
                    .addComponent(jLMascota))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBGuardar)
                    .addComponent(jBEliminar))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        try {
            if (jTNombre.getText().isEmpty() || jTPassword.getText().isEmpty() || jTRol.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No debe dejar algun dato vacio");
            } else {
                if (estado.equals(Estado.NUEVO) || estado.equals(Estado.BUSCAR)) {

                    guardar();
                    limpiar();

                } else {

                    JOptionPane.showMessageDialog(this, "Elija buscar ");
                    limpiar();
                }

            }

        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
    }//GEN-LAST:event_jBGuardarActionPerformed
    }

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed

        try {
            estado = Estado.BUSCAR;
            buscarxNombre();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        eliminadologico();
    }//GEN-LAST:event_jBEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLMail;
    private javax.swing.JLabel jLMascota;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRBEstado;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTPassword;
    private javax.swing.JTextField jTRol;
    // End of variables declaration//GEN-END:variables
    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private void limpiar() {

        Utilidades.limpiarSetText(jTPassword, jTRol, jTNombre);
        estado = Estado.NADA;
        jRBEstado.setSelected(true);
        //botonAnterior = null;

    }
//        jTApellido.setText(cliente.getApellido());
//        jTNombre.setText(cliente.getNombre());
//        jTDireccion.setText(cliente.getDireccion());
//        jTtelefono.setText(cliente.getTelefono());
//        jTMail.setText(cliente.getEmail());
//
//        if (cliente.isEstado()) {
//            setTitle("Cargar Clientes");
//        } else {
//            setTitle("Cliente -- DNI dado de Baja");
//        }
//
//        jRBEstado.setSelected(cliente.isEstado());
//        jTContNombre.setText(cliente.getContactoNombre());
//        jTContactoTelefono.setText(cliente.getContactoTelefono());

    private void limpiarBuscar() {

        Utilidades.limpiarSetText(jTNombre, jTPassword, jTRol);
    }

    private void eliminadologico() {
        //if (botonAnterior == jBBuscar) {
        if (estado.equals(Estado.BUSCAR)) {
            try {
                nombre = jTNombre.getText();
                UsuarioDAO usuarioD = UsuarioDAO.obtenerInstancia();
                usuarioD.bajaLogica(nombre);
                JOptionPane.showMessageDialog(this, "El alumno fue dado de baja");
                limpiar();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un usuario válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Se produjo un error al eliminar el usuario.");
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un usuario que exista.");
        }
    }

    private void buscarxNombre() throws ClassNotFoundException, SQLException {

        UsuarioDAO usuarioD = UsuarioDAO.obtenerInstancia();
        //

        try {
            nombre = Utilidades.obtenerTextoDesdeCampo(jTNombre);
            //nombre= jTNombre.getText();
            usuario = usuarioD.buscarListaUsuarioxNombre(nombre);
            if (usuario != null) {
                setTitle("Usuario" + (usuario.isEstado() ? "" : " -- Codigo dado de Baja"));
                jRBEstado.setSelected(usuario.isEstado());

                mostrarUsuarioEnFormulario(usuario);
            } else {
                estado = Estado.NUEVO;
                //JOptionPane.showMessageDialog(this, "No se encontró el codigo,el codigo disponible es " + ultimoRegistro());
                //jTCodigo.setText(ultimoRegistro() + "");
                JOptionPane.showMessageDialog(this, "No se encontro el usuario");
            }

            //mostrarUsuarioEnFormulario(usuario);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se encontro el usuario1");
        }
    }

    private void guardar() throws Exception {
        nombre = Utilidades.obtenerTextoDesdeCampo(jTNombre);

        //try {

         //   try {

                // Obtiene los datos del cliente desde los campos de texto
                if (nombre == null) {
                    JOptionPane.showMessageDialog(this, "Error: Debes ingresar un nombre válido.");
                    return;
                }
                UsuarioDAO usuarioD = UsuarioDAO.obtenerInstancia();
                 usuario = usuarioD.obtenerUsuarioxNombre(nombre);

                JOptionPane.showMessageDialog(this, "Hasta aca llego " + usuario.getIdUsuario());
                idUsuario = usuario.getIdUsuario();
                usuario = new Usuario(
                        idUsuario,
                        nombre,
                        Utilidades.obtenerTextoDesdeCampo(jTPassword),
                        Utilidades.obtenerEnteroDesdeCampo(jTRol),
                        jRBEstado.isSelected()
                );
                if (usuario != null && estado.equals(Estado.NUEVO)) {

                    JOptionPane.showMessageDialog(this, "El Codigo ya existe, no puede darlo de Alta.");
                    return;
                } else if (estado.equals(Estado.NUEVO)) {

                    try {
                        JOptionPane.showMessageDialog(this, usuario);
                        usuario.setEstado(true);
                        usuarioD.guardarUsuario(usuario);
                    } catch (Exception ex) {
                        Utilidades.mostrarError(ex, this);
                    }

                } else if (estado.equals(Estado.BUSCAR)) {
                    // mascota.setEstado(true);

                    usuarioD.modificarUsuario(usuario);
                    JOptionPane.showMessageDialog(this, "Hasta aca llego1 " + usuario.getIdUsuario());
                }
           // } catch (Exception e) {
                //JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
               // return;
            }

            // Asignar los valores al objeto Usuario
//            
//            usuario.setNombre(nombreU);
//            usuario.setPassword(password);
//            usuario.setRol(rol);
//            usuario.setEstado(estadoUser);
            //usuario.getIdUsuario(usuarioD.obtenerIDxNombre(nombre));
      //  } catch (NumberFormatException ex) {
            //Utilidades.mostrarError(ex, this);
      //  }
   // }

//No se usa
    private void guardarUsuario() {
        try {

            // Obtiene los datos del cliente desde los campos de texto
            nombre = Utilidades.obtenerTextoDesdeCampo(jTNombre);
            if (nombre == null) {
                JOptionPane.showMessageDialog(this, "Error: Debes ingresar un nombre válido.");
                return;
            }

            // Obtiene los datos del cliente desde los campos de texto
            UsuarioDAO usuarioD = UsuarioDAO.obtenerInstancia();
            //se fija si existe el cliente
            Usuario usuario = usuarioD.buscarListaUsuarioxNombre(nombre);

//            if (usuario != null && estado.equals(Estado.NUEVO)) {
//                JOptionPane.showMessageDialog(this, "El nombre ya existe, no puede darlo de Alta.");
//                return;
//            }
            // Obtiene los datos de los campos de texto
            usuario = new Usuario(
                    nombre,
                    Utilidades.obtenerTextoDesdeCampo(jTPassword),
                    Utilidades.obtenerEnteroDesdeCampo(jTRol),
                    jRBEstado.isSelected()
            );

            // Llama al método para guardar el cliente en la base de datos
            usuario.setEstado(true);
            if (estado.equals(Estado.NUEVO)) {

                idUsuario = usuarioD.guardarUsuario(usuario);
            }
            JOptionPane.showMessageDialog(this, "Usuario dado de Alta correctamente!");
        } catch (Exception ex) {
            // Muestra un mensaje de error si ocurre una excepción al guardar el cliente
            Utilidades.mostrarError(ex, this);
        }
    }
//    private void guardar() throws Exception {
//        UsuarioDAO usuarioD = UsuarioDAO.obtenerInstancia();
//        Usuario usuario = new Usuario();
//        try {
//            try {
//
//                nombre = jTNombre.getText();
//
//                usuario = usuarioD.obtenerUsuarioxNombre(nombre);
//
//                if (usuario != null && estado.equals(Estado.NUEVO)) {
//
//                    JOptionPane.showMessageDialog(this, "El Usuario ya existe, no puede darlo de Alta.");
//                    return;
//                } else {
//                    usuario = new Usuario();
//                }
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
//                return;
//            }
//
//            String nombrejT = jTNombre.getText();
//            String contraseña = jTPassword.getText();
//            int rol = Integer.parseInt(jTRol.getText());
//            boolean estadoCliente = jRBEstado.isSelected();
//
//            // Asignar los valores al objeto Cliente
//           
//            usuario.setNombre(nombrejT);
//            usuario.setPassword(contraseña);
//            usuario.setRol(rol);
//            usuario.setEstado(estadoCliente);
//
//            // Llamar al método para guardar el alumno en la base de datos
//            //solo grabar si fue elegida la opcion Nuevo - boton 
//            //   if (botonAnterior == jBNuevo) {
//            if (estado.equals(Estado.NUEVO)) {
//
//                try {
//                    usuario.setEstado(true);
//                    usuarioD.guardarUsuario(usuario);
//                } catch (Exception ex) {
//
//                    Utilidades.mostrarError(ex, this);
//                }
//
//            } else if (estado.equals(Estado.BUSCAR)) {
////                    if (cliente.isEstado()) {
////                        Utilidades.confirmarEstado(this);
////                    }
//                usuarioD.modificarUsuario(usuario);
//            }
//
//        } catch (NumberFormatException ex) {
//            Utilidades.mostrarError(ex, this);
//        }
//    }

    private void mostrarUsuarioEnFormulario(Usuario usuario) {

        jTNombre.setText(usuario.getNombre());
        jTPassword.setText(usuario.getPassword());
        jTRol.setText(Integer.toString(usuario.getRol()));
        jRBEstado.setSelected(usuario.isEstado());
        if (usuario.isEstado()) {
            setTitle("Usuario");
        } else {
            setTitle("Usuario  dado de baja");
        }
        estadoUsuario = usuario.isEstado();
    }

    /*
     private void cargarTabla(String nombre) throws Exception {

        UsuarioDAO cursadas = new UsuarioDAO();
        Collection<Usuario> listaUsuario = new ArrayList<>(); // Inicialización predeterminada

        listaUsuario = (Collection<Usuario>) cursadas.buscarListaUsuarioxNombre(nombre);

        for (Usuario tipo : listaUsuario) {
            if (tipo.isEstado()) {
                modelo.addRow(new Object[]{tipo.getNombre(), tipo.getNombre(), tipo.getPassword()});

            }

        }
     }
        private void armarCabecera() {
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Rol");
        jTUsuarios.setModel(modelo); 
    }
     */
}
