package veterinaria.Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.AccesoADatos.TratamientoDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Utilidades;

public class FormularioTratamiento extends javax.swing.JInternalFrame {

    private Estado estado;

    private Cliente selectedCliente = null;
    private int idMascotas = 0;
    private int idCliente = 0;
    private boolean estadoTratamiento;
    private String codigo;

    /**
     * Creates new form FormularioTratamiento
     */
    public FormularioTratamiento() {
        initComponents();
        setTitle("Cargar Tratamientos");

        // Agregar FocusListener al campo jTDocumento
        jTCodigo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                //String codigo = jTCodigo.getText().trim();
                codigo = jTCodigo.getText().trim();
                if (codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debes escribir el Codigo");
                    return;
                } else {
                    try {
                        limpiarBuscar();
                        estado = Estado.BUSCAR;
                        buscarxCodigo();
//                            limpiarMostrar();
//                            buscarxAlias();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //String codigo = jTCodigo.getText().trim();
                    codigo = jTCodigo.getText().trim();
                    if (codigo.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debes escribir el Codigo");
                        return;
                    } else {
                        try {
                            limpiarBuscar();
                            estado = Estado.BUSCAR;
                            buscarxCodigo();
//                                limpiarMostrar();
//                                buscarxAlias();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }
            }
        });

        Utilidades.asociarEnterConComponente(jTCodigo, jTTipo);
        Utilidades.asociarEnterConComponente(jTTipo, jTADescripcion);
        Utilidades.asociarEnterConComponente(jTADescripcion, jTImporte);
        Utilidades.asociarEnterConComponente(jTImporte, jBBuscar);
        //    Utilidades.asociarEnterConComponente(jBBuscar, jBBuscar);
        jRBEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si el cliente ya existe (es decir, está en modo edición)
                if (estado.equals(Estado.BUSCAR)) {
                    int option = JOptionPane.showConfirmDialog(
                            FormularioTratamiento.this, // El componente padre para el cuadro de diálogo
                            "¿Está seguro de cambiar el estado del Tratamiento?", // El mensaje que se mostrará
                            "Confirmar Cambio de Estado", // El título del cuadro de diálogo
                            JOptionPane.YES_NO_OPTION); // Tipo de opciones (Sí o No)

                    // Si el usuario selecciona "Sí" en el cuadro de diálogo
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            int codigo = Integer.parseInt(jTCodigo.getText());
                            TratamientoDAO tratamientoD = new TratamientoDAO();

                            // Si el estado actual es true, llama a bajaLogica(int dni)
                            if (estadoTratamiento) {
                                try {
                                    tratamientoD.bajaLogica(codigo);
                                    setTitle("Tratamiento -- Codigo dado de Baja");
                                    JOptionPane.showMessageDialog(FormularioTratamiento.this, "El tratamiento ha sido dado de baja");
                                } catch (Exception ex) {
                                    Utilidades.mostrarError(ex, FormularioTratamiento.this);
                                }
                            } else {
                                // Si el estado actual es false, llama a altaLogica(int dni)
                                try {
                                    tratamientoD.altaLogica(codigo);
                                    setTitle("Mascota");
                                    JOptionPane.showMessageDialog(FormularioTratamiento.this, "El tratamiento ha sido dado de alta");
                                } catch (Exception ex) {
                                    Utilidades.mostrarError(ex, FormularioTratamiento.this);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLcodigo = new javax.swing.JLabel();
        jTCodigo = new javax.swing.JTextField();
        jLTipo = new javax.swing.JLabel();
        jTTipo = new javax.swing.JTextField();
        jLDescripcion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADescripcion = new javax.swing.JTextArea();
        jLImporte = new javax.swing.JLabel();
        jTImporte = new javax.swing.JTextField();
        jRBEstado = new javax.swing.JRadioButton();
        jBSalir = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Formulario Tratamiento");

        jLcodigo.setText("Codigo");

        jLTipo.setText("Tipo");

        jLDescripcion.setText("Descripcion");

        jTADescripcion.setColumns(20);
        jTADescripcion.setRows(5);
        jScrollPane1.setViewportView(jTADescripcion);

        jLImporte.setText("Importe");

        jRBEstado.setText("Estado");

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/search_find_lupa_21889.png"))); // NOI18N
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLcodigo)
                            .addComponent(jLTipo)
                            .addComponent(jLDescripcion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                            .addComponent(jTTipo)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBBuscar)
                                    .addComponent(jBGuardar)
                                    .addComponent(jBSalir))
                                .addGap(35, 35, 35))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBEstado)
                            .addComponent(jLImporte))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLcodigo)))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBBuscar)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLTipo))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDescripcion)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLImporte)
                            .addComponent(jTImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jRBEstado)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jBGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBSalir)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        try {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(this, "No debe dejar algun dato vacio");
            } else {

                guardar();
                limpiar();

            }

        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        String codigo = jTCodigo.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes escribir un Codigo");
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

        }       // TODO add your handling code here:
    }//GEN-LAST:event_jBBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JLabel jLDescripcion;
    private javax.swing.JLabel jLImporte;
    private javax.swing.JLabel jLTipo;
    private javax.swing.JLabel jLcodigo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRBEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTADescripcion;
    private javax.swing.JTextField jTCodigo;
    private javax.swing.JTextField jTImporte;
    private javax.swing.JTextField jTTipo;
    // End of variables declaration//GEN-END:variables
private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private boolean camposVacios() {
        return jTTipo.getText().isEmpty() || jTCodigo.getText().isEmpty() || jTImporte.getText().isEmpty() || jTADescripcion.getText().trim().isEmpty();
    }

    private void limpiar() {
        Utilidades.limpiarSetText(jTCodigo, jTTipo, jTImporte);
        jRBEstado.setSelected(false);
        jTADescripcion.setText("");
        estado = Estado.NADA;
        // Para limpiar el formulario y deseleccionar los JComboBox
        //JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox

    }

    private void limpiarBuscar() {
        Utilidades.limpiarSetText(jTTipo, jTImporte);
        jTADescripcion.setText("");
        // Para limpiar el formulario y deseleccionar los JComboBox
        // JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
    }

    private void buscarxCodigo() throws ClassNotFoundException, SQLException {

        TratamientoDAO tratamientoD = new TratamientoDAO();
        int codigo = 0;

        try {
            codigo = Integer.parseInt(jTCodigo.getText());

            Tratamiento tratamiento = tratamientoD.buscarListaTratamientoxId(codigo);

            if (tratamiento != null) {
                setTitle("Cargar Tratamiento" + (tratamiento.isEstado() ? "" : " -- Codigo dado de Baja"));
                jRBEstado.setSelected(tratamiento.isEstado());

                mostrarTratamientoEnFormulario(tratamiento);
            } else {
                estado = Estado.NUEVO;
                JOptionPane.showMessageDialog(this, "No se encontró el codigo,el codigo disponible es " + ultimoRegistro());
                jTCodigo.setText(ultimoRegistro() + "");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: El código debe ser un número valido.");
        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

    private void guardar() throws Exception {
        TratamientoDAO tratamientoD = new TratamientoDAO();
        Tratamiento tratamiento = new Tratamiento();
        int codigo = 0;

        try {
            try {
                codigo = Integer.parseInt(jTCodigo.getText());

                tratamiento = tratamientoD.obtenerTratamientoxId(codigo);

                if (tratamiento != null && estado.equals(Estado.NUEVO)) {

                    JOptionPane.showMessageDialog(this, "El Codigo ya existe, no puede darlo de Alta.");
                    return;
                } else {
                    tratamiento = new Tratamiento();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
                return;
            }

            String tipo = jTTipo.getText();
            String descripcion = jTADescripcion.getText();
            double importe = Double.parseDouble(jTImporte.getText());
            boolean estadoMascota = jRBEstado.isSelected();

            // Asignar los valores al objeto Tratamiento
            //tratamiento.setIdTratamiento(tipo);
            tratamiento.setTipo(tipo);

            tratamiento.setDescripcion(descripcion);

            tratamiento.setImporte(importe);
            //tratamiento.setPesoActual(pesoA);
            // tratamiento.setIdCliente(idCliente);

            tratamiento.setEstado(estadoMascota);

            if (estado.equals(Estado.NUEVO)) {

                try {
                    tratamiento.setEstado(true);
                    tratamientoD.guardarTratamiento(tratamiento);
                } catch (Exception ex) {
                    Utilidades.mostrarError(ex, this);
                }

            } else if (estado.equals(Estado.BUSCAR)) {
                // mascota.setEstado(true);

                tratamientoD.modificarTratamiento(tratamiento);
            }
        } catch (NumberFormatException ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

    private int ultimoRegistro() throws ClassNotFoundException, SQLException, Exception {
        TratamientoDAO tratamientoD = new TratamientoDAO();
        return tratamientoD.contarTotalRegistros();

    }

//    private void cargarCombo() throws ClassNotFoundException, SQLException {
//
//        ClienteDAO clienteD = new ClienteDAO();
//
//        Collection<Cliente> clientes;
//        clientes = new ArrayList<>();
//
//        try {
//            clientes = clienteD.listarCliente();
//        } catch (Exception ex) {
//            Utilidades.mostrarError(ex, this);
//        }
//
//        // Llena el JComboBox con los valores tipo Alumno
//        for (Cliente cliente : clientes) {
//            if (cliente.isEstado()) {
//                jCBClientes.addItem(cliente);
//            }
//
//        }
//    }
    private void mostrarTratamientoEnFormulario(Tratamiento tratamiento) {

        jTTipo.setText(tratamiento.getTipo());

        jTADescripcion.setText(tratamiento.getDescripcion());

        jTImporte.setText(tratamiento.getImporte() + "");

        if (tratamiento.isEstado()) {
            setTitle("Cargar Tratamiento");
        } else {
            setTitle("Tratamiento -- Codigo dado de Baja");
        }

        jRBEstado.setSelected(tratamiento.isEstado());

    }
}
