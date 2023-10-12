package veterinaria.Vistas;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.AccesoADatos.TratamientoDAO;
import veterinaria.AccesoADatos.TratamientoRealizadoDAO;
import veterinaria.AccesoADatos.VisitaDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Entidades.TratamientoRealizado;
import veterinaria.Entidades.Visita;
import veterinaria.Utilidades;

public class FormularioVisitas extends javax.swing.JInternalFrame {

    private Estado estado;
    private Estado estadoGuardado;
    private String alias;
    private Cliente selectedCliente = null;

    private Tratamiento tratamiento;
    //private Mascota mascota;

    private int idMascotas = 0;
    private int idCliente = 0;
    private int idTratamiento = 0;
    private boolean estadoTratamiento;
    private String dni;
    private Map<Integer, Tratamiento> tratamientosSeleccionados = new HashMap<>();
    private DefaultTableModel modeloMascota = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private DefaultTableModel modeloTratamiento = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public FormularioVisitas() {

        initComponents();
        setTitle("Cargar la Visita");
        armarCabecera();
        armarCabeceraTratamiento();
        //cargarTratamientos();
        limpiar();
        //            // Agregar FocusListener al campo jTDocumento
//            jTDNI.addFocusListener(new FocusAdapter() {
//                @Override
//                public void focusLost(FocusEvent e) {
//                    //String codigo = jTCodigo.getText().trim();
//                    dni = jTDNI.getText().trim();
//                    if (dni.isEmpty()) {
//                        JOptionPane.showMessageDialog(null, "Debes escribir el DNI");
//                        return;
//                    } else {
//                        try {
//                            limpiarBuscar();
//                            estado = Estado.BUSCAR;
//                            buscarxCodigo();
////                            limpiarMostrar();
////                                buscarxAlias();
//                        } catch (ClassNotFoundException ex) {
//                            Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                    }
//
//                }
//
//                public void keyPressed(KeyEvent e) {
//                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                        //String codigo = jTCodigo.getText().trim();
//                        dni = jTDNI.getText().trim();
//                        if (dni.isEmpty()) {
//                            JOptionPane.showMessageDialog(null, "Debes escribir el Codigo");
//                            return;
//                        } else {
//                            try {
//                                limpiarBuscar();
//                                estado = Estado.BUSCAR;
//                                buscarxCodigo();
////                                limpiarMostrar();
////                                buscarxAlias();
//                            } catch (ClassNotFoundException ex) {
//                                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (SQLException ex) {
//                                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        }
//
//                    }
//                }
//            });
// Agregar el ListSelectionListener aquí------- // Agrega un oyente de eventos de mouse a la tabla dando 1 Click
//o 2 click --- resulto que funcionaba pero solia fallar -- depende como uno hace el clic -- ahora
//pruebo el ListSelectionListener (osea cuando se encuentra seleccionada) parece que funciona bien
        jTMascotas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verifica si la selección está cambiada y no está en modo de ajuste
                if (!e.getValueIsAdjusting()) {

//                        // Si los datos de la visita no están guardados, muestra una advertencia
//                        if (estadoGuardado.equals(Estado.NO_GUARDADO)) {
//                            try {
//                                int respuesta = JOptionPane.showInternalConfirmDialog(
//                                        FormularioVisitas.this,
//                                        "¿Desea guardar los datos de la visita actual antes de cambiar la mascota?",
//                                        "Advertencia",
//                                        JOptionPane.YES_NO_OPTION
//                                );
//
//                                // Si el usuario elige "No", no cambia la selección de la mascota
//                                if (respuesta == JOptionPane.NO_OPTION) {
//                                    // Aquí puedes poner el código para mantener la selección actual en la tabla de mascotas
//                                    // ...
//                                    //limpiarBuscar();
//                                    guardar();
//                                    return;
//                                }
//                                limpiarVisita();
//                                // Si el usuario elige "Sí", guarda los datos de la visita actual y continúa cambiando la mascota
//                                // Guarda los datos aquí ...
//                            } catch (Exception ex) {
//                                Logger.getLogger(FormularioVisitas.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
                    // Obtiene la fila seleccionada
                    int selectedRow = jTMascotas.getSelectedRow();
                    // Verifica si hay una fila seleccionada
                    if (selectedRow != -1) {
                        try {
                            // Obtén el valor de todas las columnas en la fila seleccionada
                            idMascotas = (Integer) jTMascotas.getValueAt(selectedRow, 0);
                            alias = (String) jTMascotas.getValueAt(selectedRow, 1);

                            limpiarVisita();
                            cargarTratamientos();
                            jTTratamiento.setEnabled(true);
                            jTPesoA.setEditable(true);
                            jLMascota1.setText("Mascota: " + alias);
                            jTADescripcion.setEditable(true);
                            estadoGuardado = Estado.NO_GUARDADO;

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(FormularioVisitas.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(FormularioVisitas.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        });
        jTTratamiento.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verifica si la selección está cambiada y no está en modo de ajuste
                if (!e.getValueIsAdjusting()) {
//                        // Obtiene la fila seleccionada
//                        int selectedRow = jTTratamiento.getSelectedRow();

                    // Obtiene las filas seleccionadas
                    int[] selectedRows = jTTratamiento.getSelectedRows();
                    double sumaImportes = 0.0;

                    // Crea un nuevo mapa para almacenar los tratamientos seleccionados
                    Map<Integer, Tratamiento> tratamientosSeleccionadosTemp = new HashMap<>();

                    // Tratamiento tratamiento = new Tratamiento();
                    // Suma los importes de las filas seleccionadas
                    for (int selectedRow : selectedRows) {

                        idTratamiento = (Integer) jTTratamiento.getValueAt(selectedRow, 0);
                        String tipo = (String) jTTratamiento.getValueAt(selectedRow, 1);
                        String descripcion = (String) jTTratamiento.getValueAt(selectedRow, 2);
                        double importe = (Double) jTTratamiento.getValueAt(selectedRow, 3);

                        //JOptionPane.showMessageDialog(null, idTratamiento);
                        tratamiento = new Tratamiento(idTratamiento, tipo, descripcion, importe, true);

//                        tratamiento.setIdTratamiento(idTratamiento);
//                        tratamiento.setTipo(tipo);
//                        tratamiento.setDescripcion(descripcion);
//                        tratamiento.setImporte(importe);
//                        tratamiento.setEstado(true);
//
                        tratamientosSeleccionadosTemp.put(idTratamiento, tratamiento);
                        sumaImportes += importe;
                    }
                    // Actualiza el mapa tratamientosSeleccionados solo después de procesar todas las selecciones
                    tratamientosSeleccionados.clear();
                    tratamientosSeleccionados.putAll(tratamientosSeleccionadosTemp);

                    // Muestra la suma en jTImporteTotal
                    jTImporteTotal.setText(String.valueOf(sumaImportes));

//                        // Verifica si hay una fila seleccionada
//                        if (selectedRow != -1) {
//                            // Obtén el valor de la columna de importe en la fila seleccionada
//                            double importeTotal = (Double) jTTratamiento.getValueAt(selectedRow, 1);
//                            // alias = (String) jTMascotas.getValueAt(selectedRow, 1);
//                            //jTTratamiento.setEnabled(true);
////                            jTPesoA.setEditable(true);
////                            jLMascota1.setText("Mascota: " + alias);
////                            jTADescripcion.setEditable(true);
//                            jTImporteTotal.setText(importeTotal + "");
//                        }
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

        jPanel1 = new javax.swing.JPanel();
        jLDNI = new javax.swing.JLabel();
        jLApellido = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLMascota = new javax.swing.JLabel();
        jBBuscar = new javax.swing.JButton();
        jTDNI = new javax.swing.JTextField();
        jTApellido = new javax.swing.JTextField();
        jTNombre = new javax.swing.JTextField();
        jBSalir = new javax.swing.JButton();
        jBbuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMascotas = new javax.swing.JTable();
        jLTratamiento = new javax.swing.JLabel();
        jLDescripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescripcion = new javax.swing.JTextArea();
        jLPeso = new javax.swing.JLabel();
        jTPesoA = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLMascota1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTImporteTotal = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTTratamiento = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();

        setClosable(true);
        setTitle("Formulario visitas");
        setPreferredSize(new java.awt.Dimension(700, 600));

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 335));

        jLDNI.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLDNI.setText("DNI Cliente");

        jLApellido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLApellido.setText("Apellido");

        jLNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLNombre.setText("Nombre");

        jLMascota.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLMascota.setText("Mascotas");

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/search_find_lupa_21889.png"))); // NOI18N
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jTDNI.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTApellido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbuscarActionPerformed(evt);
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

        jLTratamiento.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLTratamiento.setText("Tratamiento");

        jLDescripcion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLDescripcion.setText("Descripcion");

        jTADescripcion.setColumns(20);
        jTADescripcion.setRows(5);
        jScrollPane2.setViewportView(jTADescripcion);

        jLPeso.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLPeso.setText("Peso");

        jLMascota1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLMascota1.setText("Mascota");

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Importe Total");

        jTImporteTotal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTTratamiento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTTratamiento);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDNI)
                            .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLNombre))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLMascota)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBBuscar)
                                    .addComponent(jBSalir)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLMascota1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLDescripcion)
                                    .addComponent(jLTratamiento))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLPeso)
                                                .addGap(15, 15, 15)
                                                .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(jBbuscar))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jTImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(jLNombre))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLMascota)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(jBSalir))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMascota1))
                .addGap(18, 18, 18)
                .addComponent(jLTratamiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLDescripcion)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLPeso)
                            .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBbuscar)))
                .addGap(101, 101, 101))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String dni = jTDNI.getText().trim();

        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes escribir un DNI");
            return;
        } else {
            try {
                limpiarBuscar();
                estado = Estado.BUSCAR;
                buscarxCodigo();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbuscarActionPerformed
        try {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(this, "No debe dejar algun dato vacio");
            } else {

                guardar();

                limpiarVisita();
                estadoGuardado = Estado.GUARDADO;

            }

        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jBbuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jBbuscar;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLDNI;
    private javax.swing.JLabel jLDescripcion;
    private javax.swing.JLabel jLMascota;
    private javax.swing.JLabel jLMascota1;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLPeso;
    private javax.swing.JLabel jLTratamiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTADescripcion;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTDNI;
    private javax.swing.JTextField jTImporteTotal;
    private javax.swing.JTable jTMascotas;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTPesoA;
    private javax.swing.JTable jTTratamiento;
    // End of variables declaration//GEN-END:variables

    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private boolean camposVacios() {
        //return jTDNI.getText().isEmpty() || jTCodigo.getText().isEmpty() || jTImporte.getText().isEmpty() || jTADescripcion.getText().trim().isEmpty();
        return jTDNI.getText().isEmpty();
    }

    private void limpiar() {
        Utilidades.limpiarSetText(jTDNI, jTApellido, jTNombre);
//        jRBEstado.setSelected(false);
//        jTADescripcion.setText("");
        estado = Estado.NADA;
        // Para limpiar el formulario y deseleccionar los JComboBox
        //JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        // jLTrat.setEnabled(false);
        jTPesoA.setEditable(false);
        jTADescripcion.setEditable(false);
        jTTratamiento.setEnabled(false);
        jTImporteTotal.setEditable(false);
        estadoGuardado = Estado.NO_GUARDADO;

    }

    private void limpiarBuscar() {
        Utilidades.limpiarSetText(jTApellido, jTNombre);
        //jTADescripcion.setText("");
        // Para limpiar el formulario y deseleccionar los JComboBox
        // JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //  jLTrat.setEnabled(false);
        jTPesoA.setEditable(false);
        jTADescripcion.setEditable(false);
        jTTratamiento.setEnabled(false);
        jTImporteTotal.setEditable(false);
        estadoGuardado = Estado.NADA;

    }

    private void limpiarVisita() {
        Utilidades.limpiarSetText(jTPesoA, jTImporteTotal);
        //jTADescripcion.setText("");
        // Para limpiar el formulario y deseleccionar los JComboBox
        // JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //  jLTrat.setEnabled(false);
        jTPesoA.setEditable(false);
        jTADescripcion.setEditable(false);
        jTADescripcion.setText("");
        modeloTratamiento.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
        jTTratamiento.setEnabled(false);
        jTImporteTotal.setEditable(false);
        estadoGuardado = Estado.NADA;

    }

    private void buscarxCodigo() throws ClassNotFoundException, SQLException {

        ClienteDAO clienteD = new ClienteDAO();

        int dni = 0;

        try {
            dni = Integer.parseInt(jTDNI.getText());
            Cliente cliente = clienteD.buscarListaClientexDni(dni);

            if (cliente != null) {
                mostrarClienteEnFormulario(cliente);
                idCliente = cliente.getIdCliente();
                cargarTabla(cliente.getIdCliente());

            } else {
                estado = Estado.NUEVO;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se encontro el DNI");
        }

//        try {
        //          codigo = Integer.parseInt(jTCodigo.getText());
//
//            Tratamiento tratamiento = tratamientoD.buscarListaTratamientoxId(codigo);
//
//            if (tratamiento != null) {
//                setTitle("Cargar Tratamiento" + (tratamiento.isEstado() ? "" : " -- Codigo dado de Baja"));
//                jRBEstado.setSelected(tratamiento.isEstado());
//
//                mostrarTratamientoEnFormulario(tratamiento);
//            } else {
//                estado = Estado.NUEVO;
//                JOptionPane.showMessageDialog(this, "No se encontró el codigo,el codigo disponible es " + ultimoRegistro());
//                jTCodigo.setText(ultimoRegistro() + "");
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Error: El código debe ser un número valido.");
//        } catch (Exception ex) {
//            Utilidades.mostrarError(ex, this);
//        }
    }

    private void guardar() throws Exception {
        VisitaDAO visitaD = new VisitaDAO();
        MascotaDAO mascotaD = new MascotaDAO();
        TratamientoRealizadoDAO tratRealizadoD = new TratamientoRealizadoDAO();

//        for (Map.Entry<Integer, Tratamiento> entry : tratamientosSeleccionados.entrySet()) {
        // Visita visita = new Visita();
//            TratamientoRealizado tratamientoRealizado = new TratamientoRealizado();
//            Tratamiento tratamiento = entry.getValue();
        // Crear una única visita para todos los tratamientos seleccionados
        Visita visita = new Visita();
        String descripcion = jTADescripcion.getText();
        double importe = Double.parseDouble(jTImporteTotal.getText());
        double pesoA = 0.0;

        try {
            String pesoAString = jTPesoA.getText().trim();
            if (!pesoAString.isEmpty()) {
                pesoA = Double.parseDouble(pesoAString);
            }
        } catch (NumberFormatException ex) {
            Utilidades.mostrarError(ex, this);
        }

        LocalDate fechaVisita = LocalDate.now();

        // Asignar los valores al objeto Visita
        visita.setPesoActual(pesoA);
        visita.setDetallesSintoma(descripcion);
        visita.setImporteVisita(importe);
        visita.setFechaVisita(fechaVisita);

        //JOptionPane.showMessageDialog(this, mascota);
//                JOptionPane.showMessageDialog(this, tratamiento);
//                JOptionPane.showMessageDialog(this, visita);
//                
//        try {
        int idTrat = visitaD.guardarVisita(visita);
        visita.setIdVisita(idTrat);
        // Iterar sobre los tratamientos seleccionados y guardarlos como tratamientos realizados
        for (Map.Entry<Integer, Tratamiento> entry : tratamientosSeleccionados.entrySet()) {
            Tratamiento tratamiento = entry.getValue();
            TratamientoRealizado tratamientoRealizado = new TratamientoRealizado();

            //tratamientoRealizado.setIdTratamientoRealizado(idTrat);
            tratamientoRealizado.setIdVisita(visita);
            tratamientoRealizado.setIdMascota(mascotaD.buscarListaMascotaxDni(idMascotas));
            tratamientoRealizado.setIdTratamiento(tratamiento);
            tratamientoRealizado.setImporte(tratamiento.getImporte());

            // Guardar el tratamiento realizado en la base de datos
            tratRealizadoD.guardarTratamientoRealizado(tratamientoRealizado);

        }
        // Modificar la mascota fuera del bucle, si es necesario hacerlo solo una vez

        Mascota mascota = mascotaD.buscarListaMascotaxDni(idMascotas);
        mascotaD.modificarMascotaPeso(mascota);
//            }catch (Exception ex) {
//                Utilidades.mostrarError(ex, this);
//            }

    }

//                JOptionPane.showMessageDialog(this, mascota);
//                JOptionPane.showMessageDialog(this, tratamiento);
//                JOptionPane.showMessageDialog(this, visita);
    private int ultimoRegistro() throws ClassNotFoundException, SQLException, Exception {
        TratamientoDAO tratamientoD = new TratamientoDAO();
        return tratamientoD.contarTotalRegistros();

    }

    private void cargarTratamientos() throws ClassNotFoundException, SQLException {

        TratamientoDAO cursadas = new TratamientoDAO();
        Collection<Tratamiento> listarTratamientos = new ArrayList<>(); // Inicialización predeterminada

        listarTratamientos = cursadas.obtenerTratamientos();

        for (Tratamiento tipo : listarTratamientos) {
            if (tipo.isEstado()) {
                modeloTratamiento.addRow(new Object[]{tipo.getIdTratamiento(), tipo.getTipo(), tipo.getDescripcion(), tipo.getImporte()});
//                modeloTratamiento.addRow(new Object[]{tipo.getTipo(), tipo.getImporte()});

            }

        }
    }

//    private void mostrarTratamientoEnFormulario(Tratamiento tratamiento) {
//
//        jTTipo.setText(tratamiento.getTipo());
//
//        jTADescripcion.setText(tratamiento.getDescripcion());
//
//        jTImporte.setText(tratamiento.getImporte() + "");
//
//        if (tratamiento.isEstado()) {
//            setTitle("Cargar Tratamiento");
//        } else {
//            setTitle("Tratamiento -- Codigo dado de Baja");
//        }
//
//        jRBEstado.setSelected(tratamiento.isEstado());
//
//    }
    private void mostrarClienteEnFormulario(Cliente cliente) {
        //JOptionPane.showMessageDialog(null, cliente);
        jTApellido.setText(cliente.getApellido());
        jTNombre.setText(cliente.getNombre());
//        jTDireccion.setText(cliente.getDireccion());
//        jTtelefono.setText(cliente.getTelefono());
//        jTMail.setText(cliente.getEmail());

//        if (cliente.isEstado()) {
//            setTitle("Cargar Clientes");
//        } else {
//            setTitle("Cliente -- DNI dado de Baja");
//        }
//
//        jRBEstado.setSelected(cliente.isEstado());
//        estadoCliente = cliente.isEstado();
//        jTContNombre.setText(cliente.getContactoNombre());
//        jTContactoTelefono.setText(cliente.getContactoTelefono());
    }

    private void cargarTabla(int idCliente) throws Exception {

        MascotaDAO cursadas = new MascotaDAO();
        Collection<Mascota> listaMascota = new ArrayList<>(); // Inicialización predeterminada

        listaMascota = cursadas.listarMascotasxIdCliente(idCliente);

        for (Mascota tipo : listaMascota) {
            if (tipo.isEstado()) {
                modeloMascota.addRow(new Object[]{tipo.getIdMascota(), tipo.getAlias(), tipo.getPesoActual()});

            }

        }

    }

    private void armarCabecera() {
        //TablaMaterias.addColumn(aColumn);
        modeloMascota.addColumn("Codigo");
        modeloMascota.addColumn("Alias");
        modeloMascota.addColumn("Peso");
        jTMascotas.setModel(modeloMascota);
    }

    private void armarCabeceraTratamiento() {
//        TablaMaterias.addColumn(aColumn);
        modeloTratamiento.addColumn("Codigo");
        modeloTratamiento.addColumn("Tipo");
        modeloTratamiento.addColumn("Descripcion");
        modeloTratamiento.addColumn("Importe");
        jTTratamiento.setModel(modeloTratamiento);
    }

}
