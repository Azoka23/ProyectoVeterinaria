package veterinaria.Vistas;

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
import javax.swing.SwingUtilities;

public class FormularioMascotas extends javax.swing.JInternalFrame {

    //private JButton botonAnterior = null; // Variable para almacenar el botón anterior
    //private JButton botonAnterior = null; // Variable para almacenar el botón anterior
    private Estado estado;
    private MascotaFormListener listener;
    private Cliente selectedCliente = null;
    private int idMascotas = 0;
    private int idCliente = 0;
    private boolean estadoMascota;

    /**
     * Creates new form InfoMateria
     */
    public FormularioMascotas(int idCliente) throws ClassNotFoundException {
        try {
            this.idCliente = idCliente;
            initComponents();
            setTitle("Cargar Mascotas");

            try {
                cargarCombo();
                cargarComboSexo();
            } catch (SQLException ex) {
                Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
            Utilidades.asociarEnterConComponente(jTAlias, JCSexo);
            Utilidades.asociarEnterConComponente(JCSexo, jTColorDePelo);
            Utilidades.asociarEnterConComponente(jTColorDePelo, jTEspecies);
            Utilidades.asociarEnterConComponente(jTEspecies, jTRaza);
            Utilidades.asociarEnterConComponente(jTRaza, jTPesoA);
            Utilidades.asociarEnterConComponente(jTPesoA, jDCFechaNac);
            Utilidades.asociarEnterConComponente(jDCFechaNac, jBGuardar);
            //        jRBEstado.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Verifica si el cliente ya existe (es decir, está en modo edición)
//                if (estado.equals(Estado.BUSCAR)) {
//                    int option = JOptionPane.showConfirmDialog(FormularioMascotas.this, // El componente padre para el cuadro de diálogo
//                            "¿Está seguro de cambiar el estado de la Mascota?", // El mensaje que se mostrará
//                            "Confirmar Cambio de Estado", // El título del cuadro de diálogo
//                            JOptionPane.YES_NO_OPTION); // Tipo de opciones (Sí o No)
//
//                    // Si el usuario selecciona "Sí" en el cuadro de diálogo
//                    if (option == JOptionPane.YES_OPTION) {
//                        try {
//                            int codigo = Integer.parseInt(jTCodigo.getText());
//                            MascotaDAO mascotaD = new MascotaDAO();
//
//                            // Si el estado actual es true, llama a bajaLogica(int dni)
//                            if (estadoMascota) {
//                                try {
//                                    mascotaD.bajaLogica(codigo);
//                                    setTitle("Mascota -- Codigo dado de Baja");
//                                    JOptionPane.showMessageDialog(FormularioMascotas.this, "La Mascota ha sido dado de baja");
//                                } catch (Exception ex) {
//                                    Utilidades.mostrarError(ex, FormularioMascotas.this);
//                                }
//                            } else {
//                                // Si el estado actual es false, llama a altaLogica(int dni)
//                                try {
//                                    mascotaD.altaLogica(codigo);
//                                    setTitle("Mascota");
//                                    JOptionPane.showMessageDialog(FormularioMascotas.this, "La Mascota ha sido dado de alta");
//                                } catch (Exception ex) {
//                                    Utilidades.mostrarError(ex, FormularioMascotas.this);
//                                }
//                            }
//                        } catch (ClassNotFoundException ex) {
//                            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    } else {
//                        // Si el usuario selecciona "No", deshace el cambio en el estado del JRadioButton
//                        jRBEstado.setSelected(!jRBEstado.isSelected());
//                    }
//                }
//            }
//        });
            jRBEstado.setSelected(true);
            jTCodigo.setText(ultimoRegistro() + "");
            // Establecer el foco en jTAlias cuando el formulario se carga
            SwingUtilities.invokeLater(() -> {
                jTAlias.requestFocusInWindow();
            });
            jTPesoM.setEnabled(false);
        } catch (Exception ex) {
            Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
        //Utilidades.asociarEnterConComponente(jTCodigo, jTAlias);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLCodigo = new javax.swing.JLabel();
        jLAlias = new javax.swing.JLabel();
        jLSexo = new javax.swing.JLabel();
        jLColorDePelo = new javax.swing.JLabel();
        jLEspecie = new javax.swing.JLabel();
        jLRaza = new javax.swing.JLabel();
        jLPesoM = new javax.swing.JLabel();
        jLFechaNac = new javax.swing.JLabel();
        jLClientes = new javax.swing.JLabel();
        jLPesoA = new javax.swing.JLabel();
        jTCodigo = new javax.swing.JTextField();
        jTAlias = new javax.swing.JTextField();
        jTColorDePelo = new javax.swing.JTextField();
        jTEspecies = new javax.swing.JTextField();
        jTRaza = new javax.swing.JTextField();
        jTPesoM = new javax.swing.JTextField();
        jTPesoA = new javax.swing.JTextField();
        jDCFechaNac = new com.toedter.calendar.JDateChooser();
        JCSexo = new javax.swing.JComboBox<>();
        jCBClientes = new javax.swing.JComboBox<>();
        jBGuardar = new javax.swing.JButton();
        jRBEstado = new javax.swing.JRadioButton();
        jBSalir = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cargar Mascota");
        setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        jPanel1.setForeground(new java.awt.Color(0, 204, 204));
        jPanel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLCodigo.setText("Codigo");

        jLAlias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLAlias.setText("Alias");

        jLSexo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLSexo.setText("Sexo");

        jLColorDePelo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLColorDePelo.setText("Color de pelo");

        jLEspecie.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLEspecie.setText("Especie");

        jLRaza.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLRaza.setText("Raza");

        jLPesoM.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLPesoM.setText("Peso Promedio");

        jLFechaNac.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLFechaNac.setText("Fecha de Nac");

        jLClientes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLClientes.setText("Cliente");

        jLPesoA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLPesoA.setText("Peso Actual");

        jTCodigo.setEditable(false);
        jTCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTCodigo.setRequestFocusEnabled(false);

        jTAlias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTColorDePelo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTEspecies.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTRaza.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTPesoM.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTPesoA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        JCSexo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jCBClientes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jRBEstado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jRBEstado.setText("Estado");
        jRBEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBEstadoActionPerformed(evt);
            }
        });

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLAlias)
                    .addComponent(jLCodigo)
                    .addComponent(jLSexo)
                    .addComponent(jLColorDePelo)
                    .addComponent(jLEspecie)
                    .addComponent(jLRaza)
                    .addComponent(jLPesoM)
                    .addComponent(jLFechaNac)
                    .addComponent(jLClientes))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jTAlias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(JCSexo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 96, Short.MAX_VALUE)
                    .addComponent(jTColorDePelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jTEspecies, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jTRaza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jTPesoM, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jBSalir)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLPesoA)
                        .addGap(37, 37, 37)
                        .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(jRBEstado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jBGuardar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLCodigo)
                            .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLAlias)
                            .addComponent(jTAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLSexo)
                            .addComponent(JCSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLColorDePelo)
                            .addComponent(jTColorDePelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLEspecie)
                            .addComponent(jTEspecies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLRaza)
                            .addComponent(jTRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLPesoM)
                            .addComponent(jTPesoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBGuardar)
                            .addComponent(jBSalir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLPesoA)
                            .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLFechaNac)
                    .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLClientes)
                    .addComponent(jCBClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBEstado))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                jTCodigo.setText(ultimoRegistro() + "");

            }

        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed
        jRBEstado.setSelected(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBEstadoActionPerformed

//    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {
//        String codigo = jTCodigo.getText().trim();
//
//        if (codigo.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Debes escribir un Codigo");
//            return;
//        } else {
//            try {
//                limpiarBuscar();
//                estado = Estado.BUSCAR;
//                buscarxCodigo();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Sexo> JCSexo;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<Cliente> jCBClientes;
    private com.toedter.calendar.JDateChooser jDCFechaNac;
    private javax.swing.JLabel jLAlias;
    private javax.swing.JLabel jLClientes;
    private javax.swing.JLabel jLCodigo;
    private javax.swing.JLabel jLColorDePelo;
    private javax.swing.JLabel jLEspecie;
    private javax.swing.JLabel jLFechaNac;
    private javax.swing.JLabel jLPesoA;
    private javax.swing.JLabel jLPesoM;
    private javax.swing.JLabel jLRaza;
    private javax.swing.JLabel jLSexo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRBEstado;
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

                //if (mascota != null && estado.equals(Estado.NUEVO)) {
                if (mascota != null) {

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
//            double pesoM = 0.0;
            double pesoA = 0.0;
            try {
                String pesoAString = jTPesoA.getText().trim();
                if (!pesoAString.isEmpty()) {
                    pesoA = Double.parseDouble(pesoAString);
                }
            } catch (NumberFormatException ex) {
                Utilidades.mostrarError(ex, this);
            }

            double pesoM = pesoA;

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
            //if (estado.equals(Estado.NUEVO)) {
            try {
                mascota.setEstado(true);
                mascotaD.guardarMascota(mascota);
            } catch (Exception ex) {
                Utilidades.mostrarError(ex, this);
            }

//            } else if (estado.equals(Estado.BUSCAR)) {
//                // mascota.setEstado(true);
//
//                mascotaD.modificarMascota(mascota);
//            }
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

            if (listener != null) {
                listener.onMascotaFormClosed();
            }
            dispose();
        }
    }

    private boolean camposVacios() {
        return jTCodigo.getText().isEmpty() || jTAlias.getText().isEmpty() || jDCFechaNac.getDate() == null;
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
            if (cliente.isEstado() && (idCliente == 0 || idCliente == cliente.getIdCliente())) {
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

    public void setMascotaFormListener(MascotaFormListener listener) {
        this.listener = listener;
    }
}
