package veterinaria.Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

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

public class FormularioMascotasBuscar extends javax.swing.JInternalFrame {

    private Estado estado;

    private Cliente selectedCliente = null;
    private int idMascotas = 0;
    private int idCliente = 0;
    private Cliente cliente=null;
    private boolean estadoMascota;
    private String alias;

    /**
     * Creates new form InfoMateria
     */
    public FormularioMascotasBuscar(int idCliente) throws ClassNotFoundException {
        try {
            this.idCliente = idCliente;
            initComponents();
            setTitle("Buscar Mascotas");
//            jLCodigo.setVisible(false);
//            jTCodigo.setVisible(false);
            //          try {
            //cargarCombo();
            cargarComboSexo();
//            } catch (SQLException ex) {
//                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
//            }

            // Agregar FocusListener al campo jTDocumento
            jTAlias.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    //String codigo = jTCodigo.getText().trim();
                    alias = jTAlias.getText().trim();
                    if (alias.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debes escribir el Alias");
                        return;
                    } else {
                        try {
                            //limpiarBuscar();
                            //estado = Estado.BUSCAR;
                            // buscarxCodigo();
                            limpiarMostrar();
                            buscarxAlias();
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
                        alias = jTAlias.getText().trim();
                        if (alias.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debes escribir el Alias");
                            return;
                        } else {
                            try {
                                //limpiarBuscar();
                                //estado = Estado.BUSCAR;
                                // buscarxCodigo();
                                limpiarMostrar();
                                buscarxAlias();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }
                }
            });
            Utilidades.asociarEnterConComponente(jTAlias, JCSexo);
            Utilidades.asociarEnterConComponente(JCSexo, jTColorDePelo);
            Utilidades.asociarEnterConComponente(jTColorDePelo, jTEspecies);
            Utilidades.asociarEnterConComponente(jTEspecies, jTRaza);
            Utilidades.asociarEnterConComponente(jTRaza, jTPesoA);
            Utilidades.asociarEnterConComponente(jTPesoA, jDCFechaNac);
            Utilidades.asociarEnterConComponente(jDCFechaNac, jBGuardar);

            jRBEstado.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Verifica si el cliente ya existe (es decir, está en modo edición)
                    if (estado.equals(Estado.BUSCAR)) {
                        int option = JOptionPane.showConfirmDialog(
                                FormularioMascotasBuscar.this, // El componente padre para el cuadro de diálogo
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
                                        JOptionPane.showMessageDialog(FormularioMascotasBuscar.this, "La Mascota ha sido dado de baja");
                                    } catch (Exception ex) {
                                        Utilidades.mostrarError(ex, FormularioMascotasBuscar.this);
                                    }
                                } else {
                                    // Si el estado actual es false, llama a altaLogica(int dni)
                                    try {
                                        mascotaD.altaLogica(codigo);
                                        setTitle("Mascota");
                                        JOptionPane.showMessageDialog(FormularioMascotasBuscar.this, "La Mascota ha sido dado de alta");
                                    } catch (Exception ex) {
                                        Utilidades.mostrarError(ex, FormularioMascotasBuscar.this);
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
            //jRBEstado.setSelected(true);
            //jTCodigo.setText(ultimoRegistro() + "");
            // Establecer el foco en jTAlias cuando el formulario se carga
            SwingUtilities.invokeLater(() -> {
                jTAlias.requestFocusInWindow();
            });
            limpiarInicial();

        } catch (Exception ex) {
            Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jBBuscar = new javax.swing.JButton();
        jRBEstado = new javax.swing.JRadioButton();
        jBSalir = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTCliente = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Buscar Mascota");

        jLCodigo.setText("Codigo");

        jLAlias.setText("Alias");

        jLSexo.setText("Sexo");

        jLColorDePelo.setText("Color de pelo");

        jLEspecie.setText("Especie");

        jLRaza.setText("Raza");

        jLPesoM.setText("Peso Promedio");

        jLFechaNac.setText("Fecha de Nac");

        jLClientes.setText("Cliente");

        jLPesoA.setText("Peso Actual");

        jTCodigo.setForeground(new java.awt.Color(255, 255, 255));

        jBBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/search_find_lupa_21889.png"))); // NOI18N
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

        jRBEstado.setText("Estado");
        jRBEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBEstadoActionPerformed(evt);
            }
        });

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLSexo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLRaza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLEspecie, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLPesoM, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLColorDePelo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTColorDePelo)
                                    .addComponent(jTEspecies)
                                    .addComponent(jTRaza)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jTPesoM, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLPesoA)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 5, Short.MAX_VALUE)))
                                .addGap(123, 123, 123))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JCSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(jRBEstado)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCodigo)
                            .addComponent(jLAlias)
                            .addComponent(jLClientes))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLFechaNac)
                        .addGap(40, 40, 40))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLCodigo)
                                .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLAlias)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBGuardar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLClientes)
                        .addComponent(jTCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBSalir))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLColorDePelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTColorDePelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEspecie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTEspecies))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRaza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTRaza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBEstado))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLPesoA)
                        .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTPesoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLPesoM)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFechaNac)
                    .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
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

        //String codigo = jTCodigo.getText().trim();
        alias = jTAlias.getText().trim();
        if (alias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes escribir el Alias");
            return;
        } else {
            try {
                //limpiarBuscar();
                //estado = Estado.BUSCAR;
                // buscarxCodigo();
                limpiarMostrar();
                buscarxAlias();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBEstadoActionPerformed

    private void jBBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBBuscarKeyPressed

        //String codigo = jTCodigo.getText().trim();
        alias = jTAlias.getText().trim();
        if (alias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes escribir el Alias");
            return;
        } else {
            try {
                //limpiarBuscar();
                //estado = Estado.BUSCAR;
                // buscarxCodigo();
                limpiarMostrar();
                buscarxAlias();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FormularioMascotasBuscar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jBBuscarKeyPressed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {
        salirAplicacion();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Sexo> JCSexo;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTAlias;
    private javax.swing.JTextField jTCliente;
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

    private void limpiarInicial() {
        //Utilidades.limpiarSetText(jTAlias, jTColorDePelo, jTEspecies, jTRaza, jTColorDePelo, jTPesoM, jTPesoA);
        // Para limpiar el formulario y deseleccionar los JComboBox
        JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        estado = Estado.NADA;
        jTPesoM.setEnabled(false);
        jTPesoA.setEnabled(false);
        jTCodigo.setEnabled(false);
        jTCliente.setEnabled(false);
        JCSexo.setEnabled(false);
        jTColorDePelo.setEnabled(false);
        jTEspecies.setEnabled(false);
        jTRaza.setEnabled(false);
        jDCFechaNac.setEnabled(false);

    }

    private void limpiarMostrar() {
        //Utilidades.limpiarSetText(jTAlias, jTColorDePelo, jTEspecies, jTRaza, jTColorDePelo, jTPesoM, jTPesoA);
        // Para limpiar el formulario y deseleccionar los JComboBox
        //JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        //jCBClientes.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox
        estado = Estado.BUSCAR;
        jTPesoA.setEnabled(true);
        //jTCodigo.setEnabled(true);
        //jTCliente.setEnabled(true);
        JCSexo.setEnabled(true);
        jTColorDePelo.setEnabled(true);
        jTEspecies.setEnabled(true);
        jTRaza.setEnabled(true);
        jDCFechaNac.setEnabled(true);

    }

//    private void buscarxCodigo() throws ClassNotFoundException, SQLException {
//
//        MascotaDAO mascotaD = new MascotaDAO();
//        int codigo = 0;
//
//        try {
//            codigo = Integer.parseInt(jTCodigo.getText());
//
//            Mascota mascota = mascotaD.buscarListaMascotaxDni(codigo);
//
////            if (mascota == null) {
////                estado = Estado.NUEVO;
////            }
////            
//            if (mascota != null) {
//                setTitle("Cargar Mascota" + (mascota.isEstado() ? "" : " -- Codigo dado de Baja"));
//                jRBEstado.setSelected(mascota.isEstado());
//
//                mostrarMascotaseEnFormulario(mascota);
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
//    }
    private void buscarxAlias() throws ClassNotFoundException, SQLException {

        MascotaDAO mascotaD = new MascotaDAO();
        //int codigo = 0;
        //String alias = "";

        try {
            //codigo = Integer.parseInt(jTCodigo.getText());
            alias = jTAlias.getText();

            Collection<Mascota> mascotas;
            mascotas = new ArrayList<>();
            //ascota mascota = mascotaD.buscarListaMascotaxDni(codigo);
            mascotas = mascotaD.buscarListaMascotaxAlias(alias);
            JComboBox<Cliente> jCBClientesDialogo = new JComboBox<>();

            // Itera sobre las mascotas encontradas y carga los clientes en el JComboBox
            for (Mascota mascota : mascotas) {
                cliente = mascota.getIdCliente();
                if (cliente != null && cliente.isEstado()) {
                    // jCBClientes.addItem(cliente);
                    jCBClientesDialogo.addItem(cliente);
                }
            }

            // Mostrar un cuadro de diálogo con el JComboBox de clientes
            int option = JOptionPane.showOptionDialog(
                    this,
                    jCBClientesDialogo,
                    "Seleccione un Cliente",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null);
            // Verificar si el usuario seleccionó un cliente y configurar el foco
            if (option == JOptionPane.OK_OPTION && jCBClientesDialogo.getSelectedItem() != null) {
                // Realizar operaciones con el cliente seleccionado
                Cliente clienteSeleccionado = (Cliente) jCBClientesDialogo.getSelectedItem();
                // ...
                //JOptionPane.showMessageDialog(this, alias+"  "+clienteSeleccionado.getIdCliente());
                // Por ejemplo: jLClienteSeleccionado.setText("Cliente seleccionado: " + clienteSeleccionado.getNombre());
                //jCBClientes.setSelectedItem(clienteSeleccionado);
                idCliente = clienteSeleccionado.getIdCliente();
                cliente=clienteSeleccionado;
                Mascota mascota = mascotaD.buscarListaMascotaxAliasIdCliente(alias, idCliente);
                //JOptionPane.showMessageDialog(this, mascota);
                mostrarMascotaseEnFormulario(mascota);
                // Establecer el foco en el JComboBox
                //jCBClientes.requestFocus();
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

                //if (mascota != null) {
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

            // Cliente idCliente = (Cliente) jCBClientes.getSelectedItem();
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
            mascota.setIdCliente(cliente);

            //mascota.setIdCliente(idCliente);
            JOptionPane.showMessageDialog(this, mascota);

            mascota.setEstado(estadoMascota);

            //Mascota mascota = new Mascota(codigo, nombre, year, estadoMascota);
//            if (estado.equals(Estado.NUEVO)) {
//
//                try {
//                    mascota.setEstado(true);
//                    mascotaD.guardarMascota(mascota);
//                } catch (Exception ex) {
//                    Utilidades.mostrarError(ex, this);
//                }
//
//            } else if (estado.equals(Estado.BUSCAR)) {
//                // mascota.setEstado(true);
            mascotaD.modificarMascota(mascota);
            //}
        } catch (NumberFormatException ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

//    private int ultimoRegistro() throws ClassNotFoundException, SQLException, Exception {
//        MascotaDAO mascotaD = new MascotaDAO();
//        return mascotaD.contarTotalRegistros();
//
//    }
    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private boolean camposVacios() {
        return jTCodigo.getText().isEmpty() || jTAlias.getText().isEmpty() || jTColorDePelo.getText().isEmpty();
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
    // Método para crear un JComboBox con los valores del enum Sexo
    public void cargarComboSexo() {
        for (Sexo sexo : Sexo.values()) {
            JCSexo.addItem(sexo);
        }
    }

    private void mostrarMascotaseEnFormulario(Mascota mascota) {
        //JOptionPane.showMessageDialog(null, cliente);
        jTCodigo.setText(mascota.getIdMascota() + "");
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
        //JOptionPane.showMessageDialog(null, mascota.getIdCliente());
        //jCBClientes.setSelectedItem(mascota.getIdCliente());
        jTCliente.setText(mascota.getIdCliente().toString());

    }
}
