package veterinaria.Vistas;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veterinaria.AccesoADatos.UsuarioDAO;
import veterinaria.Entidades.Usuario;
import veterinaria.Utilidades;

public class IngresoAlSistema extends javax.swing.JFrame {

    private boolean passwordVisible = false; // Variable de estado para rastrear la visibilidad de la contraseña
    private String nombre;

    /**
     * Creates new form MenuPrincipal
     */
    public IngresoAlSistema() {
        initComponents();
        Utilidades.asociarEnterConComponente(jTUsuario, jTPassword);
        Utilidades.asociarEnterConComponente(jTPassword, jBIngresar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpLogging = new javax.swing.JPanel();
        jlUsuario = new javax.swing.JLabel();
        jlPassword = new javax.swing.JLabel();
        jTUsuario = new javax.swing.JTextField();
        jBIngresar = new javax.swing.JButton();
        jTPassword = new javax.swing.JPasswordField();
        jLMostrar = new javax.swing.JLabel();
        jlBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pet Doctor Veterinaria");

        jpLogging.setBackground(new java.awt.Color(0, 153, 153));
        jpLogging.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlUsuario.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        jlUsuario.setText("    Usuario");

        jlPassword.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        jlPassword.setText("    Password");

        jTUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTUsuarioActionPerformed(evt);
            }
        });

        jBIngresar.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 14)); // NOI18N
        jBIngresar.setText("Ingresar");
        jBIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIngresarActionPerformed(evt);
            }
        });
        jBIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBIngresarKeyPressed(evt);
            }
        });

        jLMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/vision_view_eye_icon_153887.png"))); // NOI18N
        jLMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLMostrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpLoggingLayout = new javax.swing.GroupLayout(jpLogging);
        jpLogging.setLayout(jpLoggingLayout);
        jpLoggingLayout.setHorizontalGroup(
            jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoggingLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBIngresar)
                    .addGroup(jpLoggingLayout.createSequentialGroup()
                        .addGroup(jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(jTPassword))))
                .addGap(18, 18, 18)
                .addComponent(jLMostrar))
        );
        jpLoggingLayout.setVerticalGroup(
            jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoggingLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpLoggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jBIngresar)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jlBienvenida.setBackground(new java.awt.Color(204, 204, 204));
        jlBienvenida.setFont(new java.awt.Font("Academy Engraved LET", 1, 36)); // NOI18N
        jlBienvenida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/HuellaGreen.png"))); // NOI18N
        jlBienvenida.setText("    PET DOCTOR");
        jlBienvenida.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpLogging, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpLogging, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTUsuarioActionPerformed

    private void jBIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIngresarActionPerformed

        try {
            //ingresar();
            ingresar2();
        } catch (SQLException ex) {
            Logger.getLogger(IngresoAlSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IngresoAlSistema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBIngresarActionPerformed

    private void jLMostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLMostrarMouseClicked
        //jTPassword.setEchoChar((char) 0);

        if (passwordVisible) {
            // Si la contraseña está visible, ocúltala
            jTPassword.setEchoChar('*'); // O establece cualquier otro carácter que desees para ocultar la contraseña
        } else {
            // Si la contraseña está oculta, muéstrala
            jTPassword.setEchoChar((char) 0);
        }

        // Invierte el estado
        passwordVisible = !passwordVisible;
    }//GEN-LAST:event_jLMostrarMouseClicked

    private void jBIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBIngresarKeyPressed
        try {
            // TODO add your handling code here:
            //ingresar();
            ingresar2();
        } catch (SQLException ex) {
            Logger.getLogger(IngresoAlSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IngresoAlSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBIngresarKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IngresoAlSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoAlSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoAlSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoAlSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoAlSistema().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBIngresar;
    private javax.swing.JLabel jLMostrar;
    private javax.swing.JPasswordField jTPassword;
    private javax.swing.JTextField jTUsuario;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jlBienvenida;
    private javax.swing.JLabel jlPassword;
    private javax.swing.JLabel jlUsuario;
    private javax.swing.JPanel jpLogging;
    // End of variables declaration//GEN-END:variables

    public void ingresar() {
        String usuario = jTUsuario.getText();
        char[] passwordChars = jTPassword.getPassword();
        String password = new String(passwordChars);

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
        } else if (usuario.equals("ulp") && password.equals("hola")) {
            // Crear una instancia del Menú
            //Menu menu = new Menu();

            // Establecer el tamaño preferido del Menú
            //menu.setSize(800, 700);
            // Hacer el Menú visible
            //menu.setVisible(true);
            // Centrar el Menú en la pantalla
            //menu.setLocationRelativeTo(null);
            // Cerrar el formulario actual
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos");
            jTUsuario.setText("");
            jTPassword.setText("");
        }
    }

    public void ingresar2() throws ClassNotFoundException, SQLException, Exception {
        {
            nombre = jTUsuario.getText();
            char[] passwordChars = jTPassword.getPassword();
            String password = new String(passwordChars);
            if (nombre.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
            } else {
                try {
                    UsuarioDAO usuarioDAO = UsuarioDAO.obtenerInstancia();
                    Usuario username = new Usuario();
                    username = usuarioDAO.buscarListaUsuarioxNombre(nombre);
                    String passwordDAO = username.getPassword();
                    if (username == null) {
                        JOptionPane.showMessageDialog(this, "El usuario no existe");
                    } else if (password.equals(passwordDAO)) {
                        IngresoAlSistema login = new IngresoAlSistema();
                        login.dispose();
                        Menu menu = new Menu(username);
                        menu.setSize(900, 600);

                        menu.setVisible(true);
                        menu.setLocationRelativeTo(null);
                        // Cerrar el formulario actual
                        this.dispose();

                    } else {
                        JOptionPane.showMessageDialog(this, "Usuario y/aaao contraseña incorrectos");
                        jTUsuario.setText("");
                        jTPassword.setText("");
                    }
                } catch (Exception e) {
                    // JOptionPane.showMessageDialog(this, "Se produjo un error al eliminar el usuario.");
                    System.out.print("se produjo error " + e);
                }
            }

        }
    }
}
