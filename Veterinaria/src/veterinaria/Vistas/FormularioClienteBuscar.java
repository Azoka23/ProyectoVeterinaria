package veterinaria.Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Utilidades;
import veterinaria.Vistas.CustomPanel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import veterinaria.Vistas.DesktopPaneWithBackground;
import veterinaria.Vistas.Estado;
import veterinaria.Vistas.MascotaFormListener;

public class FormularioClienteBuscar extends javax.swing.JInternalFrame implements MascotaFormListener {

    private Estado estado = Estado.NADA;
    private DesktopPaneWithBackground desktopPane;
    private Cliente selectedCliente = null;
    //private int idMascotas = 0;
    private int idCliente = 0;
    private boolean estadoCliente;

    private DefaultTableModel modelo = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public FormularioClienteBuscar(DesktopPaneWithBackground desktopPane) {
        this.desktopPane = desktopPane;
        initComponents();
        setTitle("Buscar  Cliente");
        armarCabecera();

        // Agregar KeyListener al campo jTDocumento
        jTDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarClientePorDni();
                }
            }
        });
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

        jRBEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si el cliente ya existe (es decir, está en modo edición)
                if (estado.equals(Estado.BUSCAR)) {
                    int option = JOptionPane.showConfirmDialog(FormularioClienteBuscar.this, // El componente padre para el cuadro de diálogo
                            "¿Está seguro de cambiar el estado del cliente?", // El mensaje que se mostrará
                            "Confirmar Cambio de Estado", // El título del cuadro de diálogo
                            JOptionPane.YES_NO_OPTION); // Tipo de opciones (Sí o No)

                    // Si el usuario selecciona "Sí" en el cuadro de diálogo
                    if (option == JOptionPane.YES_OPTION) {
                        int dni = Integer.parseInt(jTDocumento.getText());
                        ClienteDAO clienteD = ClienteDAO.obtenerInstancia();
                        // Si el estado actual es true, llama a bajaLogica(int dni)
                        //if (jRBEstado.isSelected()) {
                        //JOptionPane.showMessageDialog(null, estadoCliente);
                        if (estadoCliente) {
                            try {
                                clienteD.bajaLogica(dni);
                                setTitle("Cliente -- DNI dado de Baja");
                                JOptionPane.showMessageDialog(FormularioClienteBuscar.this, "El cliente ha sido dado de baja");
                            } catch (Exception ex) {
                                Utilidades.mostrarError(ex, FormularioClienteBuscar.this);
                            }
                        } else {
                            // Si el estado actual es false, llama a altaLogica(int dni)
                            try {
                                clienteD.altaLogica(dni);
                                setTitle("Cliente");
                                JOptionPane.showMessageDialog(FormularioClienteBuscar.this, "El cliente ha sido dado de alta");
                            } catch (Exception ex) {
                                Utilidades.mostrarError(ex, FormularioClienteBuscar.this);
                            }
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
        jBBuscar = new javax.swing.JButton();
        jTContactoTelefono = new javax.swing.JTextField();
        jTMail = new javax.swing.JTextField();
        jBSalir = new javax.swing.JButton();
        jTContNombre = new javax.swing.JTextField();
        jRBEstado = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMascotas = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jBMascotas = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setTitle("Buscar Cliente");
        setVisible(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

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
        jTDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTDocumentoKeyPressed(evt);
            }
        });

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

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/search_find_lupa_21889.png"))); // NOI18N
        jBBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBBuscar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jBBuscarMouseMoved(evt);
            }
        });
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });
        jBBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBBuscarKeyPressed(evt);
            }
        });

        jTContactoTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTMail.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        jBMascotas.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jBMascotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/77_85219.png"))); // NOI18N
        jBMascotas.setText("+");
        jBMascotas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jBMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(221, 221, 221))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLApellido, jLDireccion, jLDocumento, jLEmail, jLNombre, jLTelefono});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTApellido, jTDireccion, jTDocumento, jTMail, jTNombre, jTtelefono});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jBMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
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
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLApellido, jLDireccion, jLDocumento, jLEmail, jLNombre, jLTelefono});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTApellido, jTDireccion, jTDocumento, jTMail, jTNombre, jTtelefono});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, Short.MAX_VALUE)
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

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        buscarClientePorDni();


    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        guardarxBotones();


    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed


    }//GEN-LAST:event_jRBEstadoActionPerformed

    private void jBMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMascotasActionPerformed
        if (jTDocumento.getText().isEmpty() || jTApellido.getText().isEmpty() || jTNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No debe dejar algun dato vacio");
        } else {
            cargarFormularioMascotas();
        }
    }//GEN-LAST:event_jBMascotasActionPerformed

    private void jBMascotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBMascotasKeyPressed

        if (jTDocumento.getText().isEmpty() || jTApellido.getText().isEmpty() || jTNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No debe dejar algun dato vacio");
        } else {
            cargarFormularioMascotas();
        }
    }//GEN-LAST:event_jBMascotasKeyPressed

    private void jBBuscarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBBuscarMouseMoved


    }//GEN-LAST:event_jBBuscarMouseMoved

    private void jBGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBGuardarKeyPressed
        guardarxBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBGuardarKeyPressed

    private void jBBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBBuscarKeyPressed
        buscarClientePorDni();
    }//GEN-LAST:event_jBBuscarKeyPressed

    private void jTDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTDocumentoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTDocumentoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
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

    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private void limpiar() {

        Utilidades.limpiarSetText(jTDocumento, jTApellido, jTNombre, jTDireccion, jTtelefono, jTMail, jTContNombre, jTContactoTelefono);
        estado = Estado.NADA;
        jRBEstado.setSelected(true);
        modelo.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

    }

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

            estado = Estado.BUSCAR;
            if (cliente == null) {

                estado = Estado.NUEVO;
            }

            mostrarClienteEnFormulario(cliente);
            idCliente = cliente.getIdCliente();

            cargarTabla(cliente.getIdCliente());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
        } catch (Exception ex) {
            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

            if (estado.equals(Estado.BUSCAR)) {
                idCliente = clienteD.modificarCliente(cliente);
            }

            JOptionPane.showMessageDialog(this, "Cliente modificado correctamente!");
        } catch (Exception ex) {
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
        estadoCliente = cliente.isEstado();
        jTContNombre.setText(cliente.getContactoNombre());
        jTContactoTelefono.setText(cliente.getContactoTelefono());
    }

    private void armarCabecera() {
        //TablaMaterias.addColumn(aColumn);
        modelo.addColumn("Codigo");
        modelo.addColumn("Alias");
        modelo.addColumn("Peso");
        jTMascotas.setModel(modelo);
    }

    private void cargarTabla(int idCliente) throws Exception {

        MascotaDAO cursadas = MascotaDAO.obtenerInstancia();
        Collection<Mascota> listaMascota = new ArrayList<>(); // Inicialización predeterminada

        listaMascota = cursadas.listarMascotasxIdCliente(idCliente);
        modelo.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
        for (Mascota tipo : listaMascota) {
            if (tipo.isEstado()) {
                modelo.addRow(new Object[]{tipo.getIdMascota(), tipo.getAlias(), tipo.getPesoActual()});

            }

        }

    }

    private void cargarFormularioMascotas() {

        try {
            FormularioMascotas cargarMascotas = new FormularioMascotas(idCliente);
            cargarMascotas.setMascotaFormListener(this); // Donde `this` es el objeto que implementa el interfaz
            cargarMascotas.setSize(600, 500);
            cargarMascotas.pack();
            int x = (desktopPane.getWidth() - cargarMascotas.getWidth()) / 2;
            int y = (desktopPane.getHeight() - cargarMascotas.getHeight()) / 2;
            cargarMascotas.setBounds(x, y, cargarMascotas.getWidth(), cargarMascotas.getHeight());
            desktopPane.add(cargarMascotas);
            cargarMascotas.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarxBotones() {
        try {
            if (jTDocumento.getText().isEmpty() || jTApellido.getText().isEmpty() || jTNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No debe dejar ningún dato vacío");
            } else {
                if (estado.equals(Estado.BUSCAR)) {
                    guardarCliente();
                    limpiar();
                }

            }
        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

    @Override
    public void onMascotaFormClosed() {
        try {
            // Refrescar la tabla aquí
            modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
            // Luego, vuelve a cargar los datos en el modelo y actualiza la tabla

            cargarTabla(idCliente);
        } catch (Exception ex) {
            Logger.getLogger(FormularioClienteBuscar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
