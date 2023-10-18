package veterinaria.Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import veterinaria.AccesoADatos.TratamientoDAO;
import veterinaria.Entidades.Cliente;
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

        // Obtener el Document asociado al campo de texto jTPesoA
        AbstractDocument doc = (AbstractDocument) jTImporte.getDocument();

        // Aplicar el DocumentFilter para reemplazar comas por puntos
        doc.setDocumentFilter(new DecimalDocumentFilter());

        // Agregar ActionListener al campo jTDocumento
        jTCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Método para procesar el código
                codigo = Utilidades.obtenerTextoDesdeCampo(jTCodigo).trim();
                if (codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debes escribir el Codigo");
                } else {
                    limpiarBuscar();
                    estado = Estado.BUSCAR;
                    buscarxCodigo();
                }
            }
        });
        // Asociar tecla "Enter" con componentes
        asociarCamposConEnter();

        // Configurar ActionListener para el botón de estado
        configurarEstadoTratamiento();
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
        setForeground(new java.awt.Color(0, 204, 204));
        setTitle("Formulario Tratamiento");
        setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLcodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLcodigo.setText("Codigo");

        jLTipo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLTipo.setText("Tipo");

        jLDescripcion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLDescripcion.setText("Descripcion");

        jTADescripcion.setColumns(20);
        jTADescripcion.setRows(5);
        jScrollPane1.setViewportView(jTADescripcion);

        jLImporte.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLImporte.setText("Importe");

        jRBEstado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRBEstado.setText("Estado");
        jRBEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBEstadoActionPerformed(evt);
            }
        });

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/search_find_lupa_21889.png"))); // NOI18N
        jBBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
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
//            try {
            limpiarBuscar();
            estado = Estado.BUSCAR;
            buscarxCodigo();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
//            }

        }       // TODO add your handling code here:
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBEstadoActionPerformed


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

    private void buscarxCodigo() {

        TratamientoDAO tratamientoD = TratamientoDAO.obtenerInstancia();
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
        TratamientoDAO tratamientoD = TratamientoDAO.obtenerInstancia();
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
        TratamientoDAO tratamientoD = TratamientoDAO.obtenerInstancia();
        return tratamientoD.contarTotalRegistros();

    }

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
        estadoTratamiento = tratamiento.isEstado();

    }

    // Método para asociar la tecla Enter con componentes específicos
    private void asociarCamposConEnter() {
        // Utilidades.asociarEnterConComponente asocia la tecla Enter con el componente proporcionado como argumento  
        Utilidades.asociarEnterConComponente(jTCodigo, jTTipo);
        Utilidades.asociarEnterConComponente(jTTipo, jTADescripcion);
        Utilidades.asociarEnterConComponente(jTADescripcion, jTImporte);
        Utilidades.asociarEnterConComponente(jTImporte, jBBuscar);
    }

    // Método para configurar el cambio de estado de la mascota
    private void configurarEstadoTratamiento() {
        // Configura un ActionListener para el cambio de estado de la mascota 
        jRBEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estado.equals(Estado.BUSCAR)) {
                    confirmarCambioEstadoTratamiento();
                }
            }
        });
    }

    // Método para confirmar el cambio de estado del tratamiento
    private void confirmarCambioEstadoTratamiento() {
        //if (estado.equals(Estado.BUSCAR)) {
        int option = JOptionPane.showConfirmDialog(
                FormularioTratamiento.this,
                "¿Está seguro de cambiar el estado del Tratamiento?",
                "Confirmar Cambio de Estado",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            int codigo = Integer.parseInt(jTCodigo.getText());
            TratamientoDAO tratamientoD = TratamientoDAO.obtenerInstancia();

            try {
                if (estadoTratamiento) {
                    tratamientoD.bajaLogica(codigo);
                    setTitle("Tratamiento -- Codigo dado de Baja");
                    JOptionPane.showMessageDialog(FormularioTratamiento.this, "El tratamiento ha sido dado de baja");
                } else {
                    tratamientoD.altaLogica(codigo);
                    setTitle("Mascota");
                    JOptionPane.showMessageDialog(FormularioTratamiento.this, "El tratamiento ha sido dado de alta");
                }
            } catch (Exception ex) {
                Utilidades.mostrarError(ex, FormularioTratamiento.this);
            }
        } else {
            jRBEstado.setSelected(!jRBEstado.isSelected());
        }
        // }
    }
}
