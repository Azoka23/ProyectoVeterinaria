package veterinaria.Vistas;

import veterinaria.CustomPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Utilidades;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import veterinaria.DesktopPaneWithBackground;
import veterinaria.Entidades.Estado;
import veterinaria.MascotaFormListener;

// Clase FormularioCliente que extiende JInternalFrame e implementa la interfaz MascotaFormListener
public class FormularioCliente extends javax.swing.JInternalFrame implements MascotaFormListener {

    // Variables de instancia
    private Estado estado = Estado.NADA;
    private DesktopPaneWithBackground desktopPane;
    private Cliente selectedCliente = null;
    private int idCliente = 0;
    private boolean estadoCliente;

    // Modelo de tabla para mostrar datos
    private DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public FormularioCliente(DesktopPaneWithBackground desktopPane) {

        this.desktopPane = desktopPane;
        initComponents();
        setTitle("Cargar Cliente");
        armarCabecera();

        jRBEstado.setSelected(true);

        // Agregar KeyListener al campo jTDocumento
        jTDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarClientePorDni();
                }
            }
        });
        asociarEnterConComponentes();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new CustomPanel();
        jLDocumento = new javax.swing.JLabel();
        jLApellido = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLDireccion = new javax.swing.JLabel();
        jLTelefono = new javax.swing.JLabel();
        jLEmail = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTDocumento = new javax.swing.JTextField();
        jTApellido = new javax.swing.JTextField();
        jTNombre = new javax.swing.JTextField();
        jTDireccion = new javax.swing.JTextField();
        jTtelefono = new javax.swing.JTextField();
        jBGuardar = new javax.swing.JButton();
        jTContactoTelefono = new javax.swing.JTextField();
        jTMail = new javax.swing.JTextField();
        jBSalir = new javax.swing.JButton();
        jTContNombre = new javax.swing.JTextField();
        jRBEstado = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBMascotas = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMascotas = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setForeground(new java.awt.Color(0, 204, 204));
        setTitle(" Cargar Cliente Nuevo");
        setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        setVisible(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLDocumento.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLDocumento.setText("Documento");

        jLApellido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLApellido.setText("Apellido");

        jLNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLNombre.setText("Nombre");

        jLDireccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLDireccion.setText("Direccion");

        jLTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLTelefono.setText("Telefono");

        jLEmail.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLEmail.setText("E-Mail");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Contacto extra");

        jTDocumento.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTDocumento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTApellido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTDireccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTtelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });
        jBGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBGuardarKeyPressed(evt);
            }
        });

        jTContactoTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTMail.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBSalir.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jBSalirMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jBSalirMouseMoved(evt);
            }
        });
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jTContNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jRBEstado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRBEstado.setText("Estado");
        jRBEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBEstadoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Apellido, Nombre");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Telefono");

        jBMascotas.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jBMascotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/77_85219.png"))); // NOI18N
        jBMascotas.setText("+");
        jBMascotas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBMascotas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBMascotas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jBMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMascotasActionPerformed(evt);
            }
        });
        jBMascotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBMascotasKeyPressed(evt);
            }
        });

        jTMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTMascotas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLApellido)
                            .addComponent(jLNombre)
                            .addComponent(jLDireccion)
                            .addComponent(jLTelefono)
                            .addComponent(jLEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(jTApellido)
                            .addComponent(jTNombre)
                            .addComponent(jTDireccion)
                            .addComponent(jTtelefono)
                            .addComponent(jTMail))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jBMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(214, 214, 214))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jRBEstado))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTContactoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTContNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLApellido, jLDireccion, jLDocumento, jLEmail, jLNombre, jLTelefono});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTApellido, jTDireccion, jTDocumento, jTMail, jTNombre, jTtelefono});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(jBSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addComponent(jBMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTtelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jRBEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTContNombre))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTContactoTelefono))
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLApellido, jLDireccion, jLDocumento, jLEmail, jLNombre, jLTelefono});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTApellido, jTDireccion, jTDocumento, jTMail, jTNombre, jTtelefono});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        guardarxBotones();

    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed
        jRBEstado.setSelected(true);

    }//GEN-LAST:event_jRBEstadoActionPerformed

    private void jBMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMascotasActionPerformed
        cargarFormularioMascotas();
    }//GEN-LAST:event_jBMascotasActionPerformed

    private void jBMascotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBMascotasKeyPressed

        cargarFormularioMascotas();
    }//GEN-LAST:event_jBMascotasKeyPressed

    private void jBGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBGuardarKeyPressed
        guardarxBotones();
    }//GEN-LAST:event_jBGuardarKeyPressed

    private void jBSalirMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBSalirMouseMoved
        // movimiento del mouse salir
        
        
    }//GEN-LAST:event_jBSalirMouseMoved

    private void jBSalirMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBSalirMouseDragged
        // TODO add your handling code here:
        // jBSalir.setText("estoy en otro lado");
    }//GEN-LAST:event_jBSalirMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBMascotas;
    private javax.swing.JButton jBSalir;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLDireccion;
    private javax.swing.JLabel jLDocumento;
    private javax.swing.JLabel jLEmail;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLTelefono;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRBEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTContNombre;
    private javax.swing.JTextField jTContactoTelefono;
    private javax.swing.JTextField jTDireccion;
    private javax.swing.JTextField jTDocumento;
    private javax.swing.JTextField jTMail;
    private javax.swing.JTable jTMascotas;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTtelefono;
    // End of variables declaration//GEN-END:variables

// Método para salir de la aplicación
    private void salirAplicacion() {
        // Verifica si el usuario confirma la salida y cierra la ventana si es así
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

// Método para limpiar los campos del formulario
    private void limpiar() {
        // Limpia los campos de texto y establece el estado a NADA
        Utilidades.limpiarSetText(jTDocumento, jTApellido, jTNombre, jTDireccion, jTtelefono, jTMail, jTContNombre, jTContactoTelefono);
        estado = Estado.NADA;
        jRBEstado.setSelected(true);
        modelo.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
    }

// Método para limpiar los campos de búsqueda
    private void limpiarBuscar() {
        // Limpia los campos de búsqueda y la tabla de mascotas antes de agregar nuevos datos
        Utilidades.limpiarSetText(jTApellido, jTNombre, jTDireccion, jTtelefono, jTMail, jTContNombre, jTContactoTelefono);
        modelo.setRowCount(0);
    }

// Método para buscar cliente por DNI
    private void buscarClientePorDni() {
        // Obtiene el documento ingresado en el campo de texto
        String documento = Utilidades.obtenerTextoDesdeCampo(jTDocumento);

        // Verifica si el campo de documento está vacío y muestra un mensaje de advertencia
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes escribir un documento");
            return;
        }

        try {
            // Obtiene el DNI como entero
            int dni = Utilidades.obtenerEnteroDesdeCampo(jTDocumento);

            // Intenta buscar un cliente con el DNI ingresado en la base de datos
            ClienteDAO clienteD = ClienteDAO.obtenerInstancia();
            Cliente cliente = clienteD.buscarListaClientexDni(dni);

            // Establece el estado como NUEVO y muestra un mensaje si el cliente ya existe
            estado = Estado.NUEVO;
            if (cliente != null) {
                JOptionPane.showMessageDialog(this, "El Documento ya existe");
                estado = Estado.NADA;
                SwingUtilities.invokeLater(() -> {
                    jTDocumento.requestFocusInWindow();
                });
                mostrarClienteEnFormulario(cliente);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
        } catch (Exception ex) {
            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Método para guardar el cliente en la base de datos
    private void guardarCliente() {
        try {

            // Obtiene los datos del cliente desde los campos de texto
            int documento = Utilidades.obtenerEnteroDesdeCampo(jTDocumento);
            if (documento <= 0) {
                JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
                return;
            }

            // Obtiene los datos del cliente desde los campos de texto
            ClienteDAO clienteD = ClienteDAO.obtenerInstancia();
            //se fija si existe el cliente
            Cliente cliente = clienteD.buscarListaClientexDni(documento);

            if (cliente != null && estado.equals(Estado.NUEVO)) {
                JOptionPane.showMessageDialog(this, "El Documento ya existe, no puede darlo de Alta.");
                return;
            }

            // Obtiene los datos de los campos de texto
            cliente = new Cliente(
                    documento,
                    Utilidades.obtenerTextoDesdeCampo(jTApellido),
                    Utilidades.obtenerTextoDesdeCampo(jTNombre),
                    Utilidades.obtenerTextoDesdeCampo(jTDireccion),
                    Utilidades.obtenerTextoDesdeCampo(jTtelefono),
                    Utilidades.obtenerTextoDesdeCampo(jTContNombre),
                    Utilidades.obtenerTextoDesdeCampo(jTContactoTelefono),
                    jRBEstado.isSelected(),
                    Utilidades.obtenerTextoDesdeCampo(jTMail)
            );

            // Llama al método para guardar el cliente en la base de datos
            cliente.setEstado(true);
            if (estado.equals(Estado.NUEVO)) {
                idCliente = clienteD.guardarCliente(cliente);
            }
            JOptionPane.showMessageDialog(this, "Cliente dado de Alta correctamente!");
        } catch (Exception ex) {
            // Muestra un mensaje de error si ocurre una excepción al guardar el cliente
            Utilidades.mostrarError(ex, this);
        }
    }

// Método para mostrar los datos del cliente en el formulario
    private void mostrarClienteEnFormulario(Cliente cliente) {
        // Establece los datos del cliente en los campos de texto y otros componentes
        jTApellido.setText(cliente.getApellido());
        jTNombre.setText(cliente.getNombre());
        jTDireccion.setText(cliente.getDireccion());
        jTtelefono.setText(cliente.getTelefono());
        jTMail.setText(cliente.getEmail());

        // Establece el título de la ventana según el estado del cliente (activo o inactivo)
        if (cliente.isEstado()) {
            setTitle("Cargar Clientes");
        } else {
            setTitle("Cliente -- DNI dado de Baja");
        }

        // Establece el estado del cliente y otros campos del formulario
        jRBEstado.setSelected(cliente.isEstado());
        estadoCliente = cliente.isEstado();
        jTContNombre.setText(cliente.getContactoNombre());
        jTContactoTelefono.setText(cliente.getContactoTelefono());
    }

// Método para armar la cabecera de la tabla de mascotas
    private void armarCabecera() {
        // Define la cabecera de la tabla de mascotas
        modelo.addColumn("Codigo");
        modelo.addColumn("Alias");
        modelo.addColumn("Peso");
        jTMascotas.setModel(modelo);
    }

// Método para guardar datos del cliente a través de los botones de la interfaz
    private void guardarxBotones() {
        try {
            // Verifica que los campos obligatorios no estén vacíos antes de guardar el cliente
            if (jTDocumento.getText().isEmpty() || jTApellido.getText().isEmpty() || jTNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No debe dejar ningún dato vacío");
            } else {
                // Guarda el cliente y limpia los campos del formulario
                guardarCliente();
                limpiar();
            }
        } catch (Exception ex) {
            // Muestra un mensaje de error si ocurre una excepción al guardar el cliente
            Utilidades.mostrarError(ex, this);
        }
    }

// Método para cargar el formulario de mascotas asociado a un cliente
    private void cargarFormularioMascotas() {
        if (jTDocumento.getText().isEmpty() || jTApellido.getText().isEmpty() || jTNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No debe dejar ningún dato vacío");
        } else if (estado.equals(Estado.NADA)) {
            JOptionPane.showMessageDialog(this, "El cliente ya existe. Utilice el formulario Buscar Cliente.");
        } else {
            try {
                // Guarda el cliente antes de cargar el formulario de mascotas
                guardarCliente();

                // Crea una instancia del formulario de mascotas con el ID del cliente actual
                FormularioMascotas cargarMascotas = new FormularioMascotas(idCliente);
                // Asocia el evento de cierre del formulario de mascotas con este formulario
                cargarMascotas.setMascotaFormListener(this);
                // Establece el tamaño y la posición del formulario de mascotas en el escritorio
                cargarMascotas.setSize(600, 500);
                cargarMascotas.pack();
                int x = (desktopPane.getWidth() - cargarMascotas.getWidth()) / 2;
                int y = (desktopPane.getHeight() - cargarMascotas.getHeight()) / 2;
                cargarMascotas.setBounds(x, y, cargarMascotas.getWidth(), cargarMascotas.getHeight());
                // Agrega el formulario de mascotas al escritorio
                desktopPane.add(cargarMascotas);
                // Hace visible el formulario de mascotas
                cargarMascotas.setVisible(true);
            } catch (Exception ex) {
                // Muestra un mensaje de error si ocurre una excepción al cargar el formulario de mascotas
                Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para cargar la tabla de mascotas asociadas al cliente actual
    private void cargarTabla(int idCliente) throws Exception {
        // Obtiene la lista de mascotas del cliente con el ID proporcionado
        MascotaDAO mascotaDAO = MascotaDAO.obtenerInstancia();
        Collection<Mascota> listaMascotas = mascotaDAO.listarMascotasxIdCliente(idCliente);

        // Limpia la tabla antes de agregar nuevos datos
        modelo.setRowCount(0);

        // Agrega las mascotas del cliente a la tabla
        for (Mascota mascota : listaMascotas) {
            if (mascota.isEstado()) {
                modelo.addRow(new Object[]{mascota.getIdMascota(), mascota.getAlias(), mascota.getPesoActual()});
            }
        }
    }

    // Método del evento de cierre del formulario de mascotas
    @Override
    public void onMascotaFormClosed() {
        try {
            // Refresca la tabla de mascotas del cliente al cerrarse el formulario de mascotas
            modelo.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos
            cargarTabla(idCliente); // Recarga las mascotas del cliente en la tabla
        } catch (Exception ex) {
            Logger.getLogger(FormularioClienteBuscar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para asociar eventos Enter a los campos del formulario para navegar entre ellos
    private void asociarEnterConComponentes() {
        Utilidades.asociarEnterConComponente(jTDocumento, jTApellido);
        Utilidades.asociarEnterConComponente(jTApellido, jTNombre);
        Utilidades.asociarEnterConComponente(jTNombre, jTDireccion);
        Utilidades.asociarEnterConComponente(jTDireccion, jTtelefono);
        Utilidades.asociarEnterConComponente(jTtelefono, jTMail);
        Utilidades.asociarEnterConComponente(jTMail, jTContNombre);
        Utilidades.asociarEnterConComponente(jTContNombre, jTContactoTelefono);
        Utilidades.asociarEnterConComponente(jTContactoTelefono, jBMascotas);
        Utilidades.asociarEnterConComponente(jBMascotas, jBGuardar);
        Utilidades.asociarEnterConComponente(jBGuardar, jBSalir);
    }
}
