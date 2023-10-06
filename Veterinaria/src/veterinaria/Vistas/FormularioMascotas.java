package veterinaria.Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Sexo;
import veterinaria.Utilidades;
import java.sql.Date;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class FormularioMascotas extends javax.swing.JInternalFrame {

    //private JButton botonAnterior = null; // Variable para almacenar el botón anterior
    //private JButton botonAnterior = null; // Variable para almacenar el botón anterior
    private Estado estado;

    private Cliente selectedCliente = null;
    private int idMascotas = 0;
    private int idCliente = 0;
    private boolean estadoMascota;

    /**
     * Creates new form InfoMateria
     */
    public FormularioMascotas() throws ClassNotFoundException {
        initComponents();
        setTitle("Cargar Mascotas");
        try {
            cargarCombo();
            cargarComboSexo();
        } catch (SQLException ex) {
            Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
        }

        jRBEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si el cliente ya existe (es decir, está en modo edición)
                if (estado.equals(Estado.BUSCAR)) {
                    int option = JOptionPane.showConfirmDialog(
                            FormularioMascotas.this, // El componente padre para el cuadro de diálogo
                            "¿Está seguro de cambiar el estado de la Mascota?", // El mensaje que se mostrará
                            "Confirmar Cambio de Estado", // El título del cuadro de diálogo
                            JOptionPane.YES_NO_OPTION); // Tipo de opciones (Sí o No)

                    // Si el usuario selecciona "Sí" en el cuadro de diálogo
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            int codigo = Integer.parseInt(jTCodigo.getText());
                            MascotaDAO mascotaD = new MascotaDAO();

                            // Si el estado actual es true, llama a bajaLogica(int dni)
                            if (estadoMascota) {
                                try {
                                    mascotaD.bajaLogica(codigo);
                                    setTitle("Mascota -- Codigo dado de Baja");
                                    JOptionPane.showMessageDialog(FormularioMascotas.this, "La Mascota ha sido dado de baja");
                                } catch (Exception ex) {
                                    Utilidades.mostrarError(ex, FormularioMascotas.this);
                                }
                            } else {
                                // Si el estado actual es false, llama a altaLogica(int dni)
                                try {
                                    mascotaD.altaLogica(codigo);
                                    setTitle("Mascota");
                                    JOptionPane.showMessageDialog(FormularioMascotas.this, "La Mascota ha sido dado de alta");
                                } catch (Exception ex) {
                                    Utilidades.mostrarError(ex, FormularioMascotas.this);
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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        jLCodigo = new javax.swing.JLabel();
        jLAlias = new javax.swing.JLabel();
        jLSexo = new javax.swing.JLabel();
        jLAño2 = new javax.swing.JLabel();
        jLEspecie = new javax.swing.JLabel();
        jLAño1 = new javax.swing.JLabel();
        jLAño3 = new javax.swing.JLabel();
        jLAño = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTCodigo = new javax.swing.JTextField();
        jTAlias = new javax.swing.JTextField();
        JCSexo = new javax.swing.JComboBox<>();
        jTColorDePelo = new javax.swing.JTextField();
        jTEspecies = new javax.swing.JTextField();
        jTRaza = new javax.swing.JTextField();
        jTPesoM = new javax.swing.JTextField();
        jLAño4 = new javax.swing.JLabel();
        jTPesoA = new javax.swing.JTextField();
        jDCFechaNac = new com.toedter.calendar.JDateChooser();
        jCBClientes = new javax.swing.JComboBox<>();
        jRBEstado = new javax.swing.JRadioButton();
        jBGuardar = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 51, 51));
        setBorder(null);
        setClosable(true);
        setTitle("Mascota");

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane1.setDividerSize(1);

        jLCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLCodigo.setText("Codigo");

        jLAlias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLAlias.setText("Alias");

        jLSexo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLSexo.setText("Sexo");

        jLAño2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLAño2.setText("Color de Pelo");

        jLEspecie.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLEspecie.setText("Especie");

        jLAño1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLAño1.setText("Raza");

        jLAño3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLAño3.setText("Peso Promedio");

        jLAño.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLAño.setText("Fecha de Nac");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Cliente");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLAño2)
                    .addComponent(jLEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLAño1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLAño3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLAño, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLAlias, jLAño, jLAño1, jLAño2, jLAño3, jLCodigo, jLEspecie, jLSexo, jLabel1});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLAlias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLSexo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLAño2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLEspecie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLAño1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLAño3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLAño, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLAlias, jLAño, jLAño1, jLAño2, jLAño3, jLCodigo, jLEspecie, jLSexo, jLabel1});

        jSplitPane1.setLeftComponent(jPanel6);

        jTCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTAlias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        JCSexo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTColorDePelo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTEspecies.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTRaza.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTPesoM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTPesoM.setEnabled(false);

        jLAño4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLAño4.setText("Peso Actual");

        jTPesoA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTPesoA.setEnabled(false);

        jRBEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRBEstado.setText("Estado");

        jBGuardar.setText("Guardar");
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

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTEspecies, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                .addComponent(jTRaza, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDCFechaNac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTPesoM, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLAño4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBSalir))
                            .addComponent(JCSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jBGuardar)
                                .addGap(33, 33, 33)
                                .addComponent(jRBEstado))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTColorDePelo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTAlias, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jCBClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBSalir)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTColorDePelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jTEspecies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTPesoM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLAño4)))
                .addGap(18, 18, 18)
                .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jCBClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBGuardar)
                    .addComponent(jRBEstado))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Mascotas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Sexo> JCSexo;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<Cliente> jCBClientes;
    private com.toedter.calendar.JDateChooser jDCFechaNac;
    private javax.swing.JLabel jLAlias;
    private javax.swing.JLabel jLAño;
    private javax.swing.JLabel jLAño1;
    private javax.swing.JLabel jLAño2;
    private javax.swing.JLabel jLAño3;
    private javax.swing.JLabel jLAño4;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLEspecie;
    private javax.swing.JLabel jLSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRBEstado;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTAlias;
    private javax.swing.JTextField jTCodigo;
    private javax.swing.JTextField jTColorDePelo;
    private javax.swing.JTextField jTEspecies;
    private javax.swing.JTextField jTPesoA;
    private javax.swing.JTextField jTPesoM;
    private javax.swing.JTextField jTRaza;
    // End of variables declaration//GEN-END:variables

    private void limpiar() {
        Utilidades.limpiarSetText(jTCodigo, jTAlias, jTColorDePelo, jTEspecies, jTRaza, jTColorDePelo, jTPesoM, jTPesoA);
        jRBEstado.setSelected(false);
        estado = Estado.NADA;
        // Para limpiar el formulario y deseleccionar los JComboBox
        JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox

    }

    private void limpiarBuscar() {
        Utilidades.limpiarSetText(jTAlias, jTColorDePelo, jTEspecies, jTRaza, jTColorDePelo, jTPesoM, jTPesoA);
        // Para limpiar el formulario y deseleccionar los JComboBox
        JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
    }

//    private void eliminadologico() throws ClassNotFoundException, SQLException {
//        if (botonAnterior == jBBuscar) {
//            MateriaDAO materiaD = new MateriaDAO();
//
//            int codigo = Integer.parseInt(jTCodigo.getText());
//
//            try {
//                materiaD.eliminarLogico(codigo);
//                JOptionPane.showMessageDialog(this, "la materia fue dada de baja");
//                limpiar();
//            } catch (Exception ex) {
//                Utilidades.mostrarError(ex, this);
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de codigo que exista.");
//        }
//    }
    private void buscarxCodigo() throws ClassNotFoundException, SQLException {

        MascotaDAO mascotaD = new MascotaDAO();
        int codigo = 0;

        try {
            codigo = Integer.parseInt(jTCodigo.getText());

            Mascota mascota = mascotaD.buscarListaMascotaxDni(codigo);

//            if (mascota == null) {
//                estado = Estado.NUEVO;
//            }
//            
            if (mascota != null) {
                setTitle("Cargar Mascota" + (mascota.isEstado() ? "" : " -- Codigo dado de Baja"));
                jRBEstado.setSelected(mascota.isEstado());

                mostrarMascotaseEnFormulario(mascota);
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
        MascotaDAO mascotaD = new MascotaDAO();
        Mascota mascota = new Mascota();
        int codigo = 0;
        // int year = 0;
        try {
            try {
                codigo = Integer.parseInt(jTCodigo.getText());

                mascota = mascotaD.obtenerMascotaPorId(codigo);

                if (mascota != null && estado.equals(Estado.NUEVO)) {

                    JOptionPane.showMessageDialog(this, "El Codigo ya existe, no puede darlo de Alta.");
                    return;
                } else {
                    mascota = new Mascota();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: Debes ingresar un número de documento válido.");
                return;
            }

            String alias = jTAlias.getText();
            Sexo sexo = (Sexo) JCSexo.getSelectedItem();
            String especie = jTEspecies.getText();
            String raza = jTRaza.getText();
            String colorDePelo = jTColorDePelo.getText();
            LocalDate fechaNacimiento = jDCFechaNac.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
//            double pesoM = Double.parseDouble(jTPesoM.getText());
//            double pesoA = Double.parseDouble(jTPesoA.getText());
            double pesoM = 0.0;
            double pesoA = 0.0;

            estadoMascota = jRBEstado.isSelected();

            Cliente idCliente = (Cliente) jCBClientes.getSelectedItem();
            //Cliente idCliente = jTContNombre.getText();
            //String contactoTel = jTContactoTelefono.getText();

            //String nombre = jTAlias.getText();
            // year = Integer.parseInt(jTColorDePelo.getText());
            //estadoMascota = jRBEstado.isSelected();
            // Asignar los valores al objeto Mascota
            mascota.setIdMascota(codigo);
            mascota.setAlias(alias);
            mascota.setSexo(sexo);
            mascota.setEspecie(especie);
            mascota.setRaza(raza);
            mascota.setColorDePelo(colorDePelo);
            mascota.setFechaNacimiento(fechaNacimiento);
            mascota.setPesoMedia(pesoM);
            mascota.setPesoActual(pesoA);
            mascota.setIdCliente(idCliente);

            mascota.setEstado(estadoMascota);

            //Mascota mascota = new Mascota(codigo, nombre, year, estadoMascota);
            if (estado.equals(Estado.NUEVO)) {

                try {
                    mascota.setEstado(true);
                    mascotaD.guardarMascota(mascota);
                } catch (Exception ex) {
                    Utilidades.mostrarError(ex, this);
                }

            } else if (estado.equals(Estado.BUSCAR)) {
                // mascota.setEstado(true);

                mascotaD.modificarMascota(mascota);
            }
        } catch (NumberFormatException ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

    private int ultimoRegistro() throws ClassNotFoundException, SQLException, Exception {
        MascotaDAO mascotaD = new MascotaDAO();
        return mascotaD.contarTotalRegistros();

    }

    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private boolean camposVacios() {
        return jTCodigo.getText().isEmpty() || jTAlias.getText().isEmpty() || jTColorDePelo.getText().isEmpty();
    }

    private void cargarCombo() throws ClassNotFoundException, SQLException {

        ClienteDAO clienteD = new ClienteDAO();

        Collection<Cliente> clientes;
        clientes = new ArrayList<>();

        try {
            clientes = clienteD.listarCliente();
        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }

        // Llena el JComboBox con los valores tipo Alumno
        for (Cliente cliente : clientes) {
            if (cliente.isEstado()) {
                jCBClientes.addItem(cliente);
            }

        }
    }

    // Método para crear un JComboBox con los valores del enum Sexo
    public void cargarComboSexo() {
        for (Sexo sexo : Sexo.values()) {
            JCSexo.addItem(sexo);
        }
    }

    private void mostrarMascotaseEnFormulario(Mascota mascota) {
        //JOptionPane.showMessageDialog(null, cliente);
        jTAlias.setText(mascota.getAlias());

        JCSexo.setSelectedItem(mascota.getSexo());

        jTEspecies.setText(mascota.getEspecie());
        jTRaza.setText(mascota.getRaza());
        jTColorDePelo.setText(mascota.getColorDePelo());
        jDCFechaNac.setDate(Date.valueOf(mascota.getFechaNacimiento()));
        jTPesoM.setText(mascota.getPesoMedia() + "");
        jTPesoA.setText(mascota.getPesoMedia() + "");

        if (mascota.isEstado()) {
            setTitle("Cargar Mascotas");
        } else {
            setTitle("Mascota -- Codigo dado de Baja");
        }

        jRBEstado.setSelected(mascota.isEstado());
        estadoMascota = mascota.isEstado();
        jCBClientes.setSelectedItem(mascota.getIdCliente());

    }
}
