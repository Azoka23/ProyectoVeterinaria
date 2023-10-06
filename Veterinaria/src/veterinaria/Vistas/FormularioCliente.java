package veterinaria.Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Utilidades;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class FormularioCliente extends javax.swing.JInternalFrame {

    //private JButton botonAnterior = null; // Variable para almacenar el botón anterior
    private Estado estado = Estado.NADA;

    private Cliente selectedCliente = null;
    private int idMascotas = 0;
    private int idCliente = 0;
    private boolean estadoCliente;

    private DefaultTableModel modelo = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    /**
     * Creates new form InfoAlumno
     */
    public FormularioCliente() {
        initComponents();
        setTitle("Cargar Cliente");
        armarCabecera();
        //jBBuscar.setVisible(false);
        // Establecer el foco en jTDocumento
        jTDocumento.requestFocusInWindow();

        jRBEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si el cliente ya existe (es decir, está en modo edición)
                if (estado.equals(Estado.BUSCAR)) {
                    int option = JOptionPane.showConfirmDialog(
                            FormularioCliente.this, // El componente padre para el cuadro de diálogo
                            "¿Está seguro de cambiar el estado del cliente?", // El mensaje que se mostrará
                            "Confirmar Cambio de Estado", // El título del cuadro de diálogo
                            JOptionPane.YES_NO_OPTION); // Tipo de opciones (Sí o No)

                    // Si el usuario selecciona "Sí" en el cuadro de diálogo
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            int dni = Integer.parseInt(jTDocumento.getText());
                            ClienteDAO clienteD = new ClienteDAO();

                            // Si el estado actual es true, llama a bajaLogica(int dni)
                            //if (jRBEstado.isSelected()) {
                            if (estadoCliente) {
                                try {
                                    clienteD.bajaLogica(dni);
                                    setTitle("Cliente -- DNI dado de Baja");
                                    JOptionPane.showMessageDialog(FormularioCliente.this, "El cliente ha sido dado de baja");
                                } catch (Exception ex) {
                                    Utilidades.mostrarError(ex, FormularioCliente.this);
                                }
                            } else {
                                // Si el estado actual es false, llama a altaLogica(int dni)
                                try {
                                    clienteD.altaLogica(dni);
                                    setTitle("Cliente");
                                    JOptionPane.showMessageDialog(FormularioCliente.this, "El cliente ha sido dado de alta");
                                } catch (Exception ex) {
                                    Utilidades.mostrarError(ex, FormularioCliente.this);
                                }
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        // Si el usuario selecciona "No", deshace el cambio en el estado del JRadioButton
                        jRBEstado.setSelected(!jRBEstado.isSelected());
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setTitle(" Cargar Cliente Nuevo");
        setVisible(false);

        jLDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLDocumento.setText("Documento");

        jLApellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLApellido.setText("Apellido");

        jLNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLNombre.setText("Nombre");

        jLDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLDireccion.setText("Direccion");

        jLTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLTelefono.setText("Telefono");

        jLEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLEmail.setText("E-Mail");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Contacto extra");

        jTDocumento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jTContactoTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jTContNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jRBEstado.setText("Estado");
        jRBEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBEstadoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Apellido, Nombre");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Telefono");

        jBMascotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/77_85219.png"))); // NOI18N
        jBMascotas.setText("Agregar ");
        jBMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMascotasActionPerformed(evt);
            }
        });

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
                                .addGap(78, 78, 78)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBMascotas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jBSalir)))
                        .addGap(269, 269, 269))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jTDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jBMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTtelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        try {
            if (jTDocumento.getText().isEmpty() || jTApellido.getText().isEmpty() || jTNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No debe dejar algun dato vacio");
            } else {
                if (estado.equals(Estado.NUEVO) || estado.equals(Estado.BUSCAR)) {

                    guardar();
                    limpiar();

                } else {

                    JOptionPane.showMessageDialog(this, "Elija buscar o Nuevo DNI");
                    limpiar();
                }

            }

        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
            //  JOptionPane.showMessageDialog(this, "exception " + ex);
        }


    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed

//
//        // TODO add your handling code here:
//        if (jRBEstado.isSelected() ) {
//            jRBEstado.setText("Cliente dado de Alta");
//        }else{
//                   jRBEstado.setText("Cliente dado de Baja");
//        }

    }//GEN-LAST:event_jRBEstadoActionPerformed

    private void jBMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMascotasActionPerformed
        // Agregar formulario mascota
        try {
            jPanel1.removeAll();
            jPanel1.repaint();
            //jEscritorio.add(jLULP);
            FormularioMascotas cargarMascotas = new FormularioMascotas();

            // Configurar el tamaño del JInternalFrame
            cargarMascotas.setSize(600, 500);
            cargarMascotas.pack();
            // Calcular el centro del JDesktopPane
            int x = (jPanel1.getWidth() - cargarMascotas.getWidth()) / 2;
            int y = (jPanel1.getHeight() - cargarMascotas.getHeight()) / 2;

            // Establecer la ubicación y agregar el JInternalFrame
            cargarMascotas.setBounds(x, y, cargarMascotas.getWidth(), cargarMascotas.getHeight());
            jPanel1.add(cargarMascotas);
            cargarMascotas.setVisible(true);

            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jBMascotasActionPerformed


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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTContNombre;
    private javax.swing.JTextField jTContactoTelefono;
    private javax.swing.JTextField jTDireccion;
    private javax.swing.JTextField jTDocumento;
    private javax.swing.JTextField jTMail;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTtelefono;
    // End of variables declaration//GEN-END:variables

    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private void limpiar() {

        Utilidades.limpiarSetText(jTDocumento, jTApellido, jTNombre, jTDireccion, jTtelefono, jTMail, jTContNombre, jTContactoTelefono);
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

        Utilidades.limpiarSetText(jTApellido, jTNombre, jTDireccion, jTtelefono, jTMail, jTContNombre, jTContactoTelefono);
        modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
    }

//    private void eliminadologico() {
//        //if (botonAnterior == jBBuscar) {
//        if (estado.equals(Estado.BUSCAR)) {
//            try {
//                int dni = Integer.parseInt(jTDocumento.getText());
//                ClienteDAO clienteD = new ClienteDAO();
//                clienteD.eliminarLogico(dni);
//                JOptionPane.showMessageDialog(this, "El alumno fue dado de baja");
//                limpiar();
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de documento válido.");
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, "Se produjo un error al eliminar el alumno.");
//                ex.printStackTrace();
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de documento que exista.");
//        }
//    }
    private void buscarxDni() throws ClassNotFoundException, SQLException {

        ClienteDAO clienteD = new ClienteDAO();
        int dni = 0;
        try {
            dni = Integer.parseInt(jTDocumento.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
            return;
        }

        try {

            Cliente cliente = clienteD.buscarListaClientexDni(dni);
            if (cliente == null) {

                estado = Estado.NUEVO;
            }

            mostrarClienteEnFormulario(cliente);
            cargarTabla(cliente.getIdCliente());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se encontro el DNI");
        }
    }

    private void guardar() throws Exception {
        ClienteDAO clienteD = new ClienteDAO();
        Cliente cliente = new Cliente();
        int documento;
        try {
            try {

                documento = Integer.parseInt(jTDocumento.getText());

                cliente = clienteD.buscarListaClientexDni(documento);

                if (cliente != null && estado.equals(Estado.NUEVO)) {

                    JOptionPane.showMessageDialog(this, "El Documento ya existe, no puede darlo de Alta.");
                    return;
                } else {
                    cliente = new Cliente();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
                return;
            }

            String apellido = jTApellido.getText();
            String nombre = jTNombre.getText();
            String direccion = jTDireccion.getText();
            String telefono = jTtelefono.getText();
            String email = jTMail.getText();
            //boolean estadoCliente = jRBEstado.isSelected();
            estadoCliente = jRBEstado.isSelected();
            String contactoNombre = jTContNombre.getText();
            String contactoTel = jTContactoTelefono.getText();

            // Asignar los valores al objeto Cliente
            cliente.setDni(documento);
            cliente.setApellido(apellido);
            cliente.setNombre(nombre);

            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setContactoNombre(contactoNombre);
            cliente.setContactoTelefono(contactoTel);
            cliente.setEstado(estadoCliente);
            cliente.setEmail(email);

            cliente.setEstado(estadoCliente);

            // Llamar al método para guardar el alumno en la base de datos
            //solo grabar si fue elegida la opcion Nuevo - boton 
            //   if (botonAnterior == jBNuevo) {
            if (estado.equals(Estado.NUEVO)) {

                try {
                    cliente.setEstado(true);
                    clienteD.guardarCliente(cliente);
                } catch (Exception ex) {

                    Utilidades.mostrarError(ex, this);
                }

            } else if (estado.equals(Estado.BUSCAR)) {
//                    if (cliente.isEstado()) {
//                        Utilidades.confirmarEstado(this);
//                    }
                clienteD.modificarCliente(cliente);
            }

        } catch (NumberFormatException ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

    private void mostrarClienteEnFormulario(Cliente cliente) {
        //JOptionPane.showMessageDialog(null, cliente);
        jTApellido.setText(cliente.getApellido());
        jTNombre.setText(cliente.getNombre());
        jTDireccion.setText(cliente.getDireccion());
        jTtelefono.setText(cliente.getTelefono());
        jTMail.setText(cliente.getEmail());

        if (cliente.isEstado()) {
            setTitle("Cargar Clientes");
        } else {
            setTitle("Cliente -- DNI dado de Baja");
        }

        jRBEstado.setSelected(cliente.isEstado());
        estadoCliente=cliente.isEstado();
        jTContNombre.setText(cliente.getContactoNombre());
        jTContactoTelefono.setText(cliente.getContactoTelefono());
    }

    private void armarCabecera() {
        //TablaMaterias.addColumn(aColumn);
        modelo.addColumn("Codigo");
        modelo.addColumn("Alias");
        modelo.addColumn("Peso");
        //jTMascotas.setModel(modelo);
    }

    private void cargarTabla(int idCliente) throws Exception {

        MascotaDAO cursadas = new MascotaDAO();
        Collection<Mascota> listaMascota = new ArrayList<>(); // Inicialización predeterminada

        listaMascota = cursadas.listarMascotasxIdCliente(idCliente);

        for (Mascota tipo : listaMascota) {
            if (tipo.isEstado()) {
                modelo.addRow(new Object[]{tipo.getIdMascota(), tipo.getAlias(), tipo.getPesoActual()});

            }

        }

    }
}
