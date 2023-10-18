package veterinaria.Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Sexo;
import veterinaria.Utilidades;
import java.sql.Date;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

public class FormularioMascotasBuscar extends javax.swing.JInternalFrame {

    private Estado estado;

    private Cliente selectedCliente = null;
    //private int idMascotas = 0;
    private int idCliente = 0;
    private Cliente cliente = null;
    private boolean estadoMascota;
    private String alias;

    public FormularioMascotasBuscar(int idCliente) {
        try {
            this.idCliente = idCliente;
            initComponents();
            setTitle("Buscar Mascotas");
            cargarComboSexo();

            // Obtener el Document asociado al campo de texto jTPesoA
            AbstractDocument doc = (AbstractDocument) jTPesoA.getDocument();

            // Aplicar el DocumentFilter para reemplazar comas por puntos
            doc.setDocumentFilter(new DecimalDocumentFilter());

            // Agregar KeyListener al campo jTDocumento
            jTAlias.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        procesarAlias();
                    }
                }
            });
            asociarCamposConEnter();
            configurarEstadoMascota();
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
        setForeground(new java.awt.Color(0, 204, 204));
        setTitle("Buscar Mascota");
        setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

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

        jTCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTCodigo.setForeground(new java.awt.Color(255, 255, 255));

        jTAlias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTColorDePelo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTEspecies.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTRaza.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTPesoM.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTPesoA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        JCSexo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

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

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jTCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

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
                                        .addGap(0, 4, Short.MAX_VALUE)))
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

                guardarMascota();
                limpiar();

            }

        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed

        buscarMascotaPorAlias();


    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBEstadoActionPerformed

    private void jBBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBBuscarKeyPressed

        buscarMascotaPorAlias();

    }//GEN-LAST:event_jBBuscarKeyPressed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
    }//GEN-LAST:event_jBSalirActionPerformed


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

// Método para limpiar los campos del formulario
    private void limpiar() {
        // Utilidades.limpiarSetText limpia los textos de los campos proporcionados como argumentos
        Utilidades.limpiarSetText(jTCodigo, jTAlias, jTColorDePelo, jTEspecies, jTRaza, jTColorDePelo, jTPesoM, jTPesoA);
        jRBEstado.setSelected(false);
        estado = Estado.NADA;
        // Desselecciona los elementos en los JComboBox
        JCSexo.setSelectedIndex(-1);
        //jCBClientes.setSelectedIndex(-1);
    }

// Método para limpiar y deshabilitar algunos campos al inicio
    private void limpiarInicial() {
        // Deshabilita y deselecciona los elementos en los campos y JComboBox correspondientes
        JCSexo.setSelectedIndex(-1);
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

    // Método para limpiar y habilitar campos para buscar mascotas por alias
    private void limpiarMostrar() {
        estado = Estado.BUSCAR;
        //jTPesoA.setEnabled(true);

        JCSexo.setEnabled(true);
        jTColorDePelo.setEnabled(true);
        jTEspecies.setEnabled(true);
        jTRaza.setEnabled(true);
        jDCFechaNac.setEnabled(true);

    }
    
    // Método para buscar mascota por alias
    private void buscarMascotaPorAlias() {

        MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();
        // Obtiene el alias ingresado en el campo de texto
        alias = Utilidades.obtenerTextoDesdeCampo(jTAlias);

        if (alias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes escribir el Alias");
            return;
        }
        limpiarMostrar();

        try {
            // Código para buscar la mascota y mostrarla en el formulario
            Collection<Mascota> mascotas = mascotaD.buscarListaMascotaxAlias(alias);

            if (!mascotas.isEmpty()) {
                Cliente clienteSeleccionado = mostrarDialogoSeleccionCliente(mascotas);
                if (clienteSeleccionado != null) {
                    idCliente = clienteSeleccionado.getIdCliente();
                    cliente = clienteSeleccionado;
                    Mascota mascota = mascotaD.buscarListaMascotaxAliasIdCliente(alias, idCliente);
                    mostrarMascotaseEnFormulario(mascota);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El Alias no se encontró, elija otro");
                Utilidades.limpiarSetText(jTAlias);
            }

        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

    // Método para mostrar un cuadro de diálogo para seleccionar un cliente
    private Cliente mostrarDialogoSeleccionCliente(Collection<Mascota> mascotas) {
        // Crea un JComboBox con los clientes obtenidos de las mascotas
        JComboBox<Cliente> jCBClientesDialogo = new JComboBox<>();
        // Itera sobre las mascotas encontradas y carga los clientes en el JComboBox
        for (Mascota mascota : mascotas) {
            Cliente cliente = mascota.getIdCliente();
            if (cliente != null && cliente.isEstado()) {
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
            return (Cliente) jCBClientesDialogo.getSelectedItem();
        }
        return null;
    }
    
    // Método para guardar la mascota o actualizarla si ya existe
    private void guardarMascota() {
        try {
            // Obtener el código de la mascota desde el campo de texto
            int codigo = Utilidades.obtenerEnteroDesdeCampo(jTCodigo);

            // Validar que el código sea válido
            if (codigo == -1) {
                JOptionPane.showMessageDialog(this, "Debes ingresar un número de código válido.");
                return;
            }

            MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();
            Mascota mascota = mascotaD.obtenerMascotaPorId(codigo);

            // Si la mascota ya existe en la base de datos, mostrar un mensaje y salir
            if (mascota != null && estado.equals(Estado.NUEVO)) {
                JOptionPane.showMessageDialog(this, "El Código ya existe, no puede darlo de Alta.");
                return;
            }

            // Obtener los valores de los campos del formulario
            Sexo sexo = (Sexo) JCSexo.getSelectedItem();
            LocalDate fechaNacimiento = jDCFechaNac.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Crear un objeto de mascota con los valores obtenidos y guardarlo en la base de datos
            mascota = new Mascota(
                    codigo,
                    Utilidades.obtenerTextoDesdeCampo(jTAlias),
                    sexo,
                    Utilidades.obtenerTextoDesdeCampo(jTEspecies),
                    Utilidades.obtenerTextoDesdeCampo(jTRaza),
                    Utilidades.obtenerTextoDesdeCampo(jTColorDePelo),
                    fechaNacimiento,
                    obtenerPeso(jTPesoM),
                    obtenerPeso(jTPesoA),
                    cliente,
                    jRBEstado.isSelected()
            );

            mascotaD.modificarMascota(mascota);
            JOptionPane.showMessageDialog(this, "Mascota modificada correctamente ");
        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }
    }

    // Método para obtener el peso desde un campo de texto
    private double obtenerPeso(JTextField textField) {
        try {

            String pesoString = textField.getText().trim();
            return pesoString.isEmpty() ? 0.0 : Double.parseDouble(pesoString);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Método para manejar la salida de la aplicación
    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    // Método para verificar si hay campos vacíos en el formulario
    private boolean camposVacios() {
        return jTCodigo.getText().isEmpty() || jTAlias.getText().isEmpty() || jTColorDePelo.getText().isEmpty();
    }

    // Método para crear un JComboBox con los valores del enum Sexo
    public void cargarComboSexo() {
        for (Sexo sexo : Sexo.values()) {
            JCSexo.addItem(sexo);
        }
    }

    // Método para mostrar los datos de la mascota en el formulario
    private void mostrarMascotaseEnFormulario(Mascota mascota) {
        // Código para mostrar los datos de la mascota en el formulario
        jTCodigo.setText(mascota.getIdMascota() + "");
        jTAlias.setText(mascota.getAlias());

        JCSexo.setSelectedItem(mascota.getSexo());

        jTEspecies.setText(mascota.getEspecie());
        jTRaza.setText(mascota.getRaza());
        jTColorDePelo.setText(mascota.getColorDePelo());
        jDCFechaNac.setDate(Date.valueOf(mascota.getFechaNacimiento()));
        jTPesoM.setText(mascota.getPesoMedia() + "");
        jTPesoA.setText(mascota.getPesoActual()+ "");

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

    // Método para asociar la tecla Enter con componentes específicos
    private void asociarCamposConEnter() {
        // Utilidades.asociarEnterConComponente asocia la tecla Enter con el componente proporcionado como argumento  
        Utilidades.asociarEnterConComponente(jTAlias, jTColorDePelo);
        Utilidades.asociarEnterConComponente(jTColorDePelo, jTEspecies);
        Utilidades.asociarEnterConComponente(jTEspecies, jTRaza);
        Utilidades.asociarEnterConComponente(jTRaza, JCSexo);
        Utilidades.asociarEnterConComponente(JCSexo, jTPesoA);
        Utilidades.asociarEnterConComponente(jTPesoA, jTPesoA);
        Utilidades.asociarEnterConComponente(jDCFechaNac, jBGuardar);
    }

    // Método para configurar el cambio de estado de la mascota
    private void configurarEstadoMascota() {
        // Configura un ActionListener para el cambio de estado de la mascota 
        jRBEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estado.equals(Estado.BUSCAR)) {
                    confirmarCambioEstadoMascota();
                }
            }
        });
    }

    private void confirmarCambioEstadoMascota() {
        int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de cambiar el estado de la Mascota?",
                "Confirmar Cambio de Estado", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                int codigo = Integer.parseInt(jTCodigo.getText());
                MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();

                if (estadoMascota) {
                    mascotaD.bajaLogica(codigo);
                    setTitle("Mascota -- Codigo dado de Baja");
                    JOptionPane.showMessageDialog(this, "La Mascota ha sido dado de baja");
                } else {
                    mascotaD.altaLogica(codigo);
                    setTitle("Mascota");
                    JOptionPane.showMessageDialog(this, "La Mascota ha sido dado de alta");
                }
            } catch (Exception ex) {
                Utilidades.mostrarError(ex, this);
            }
        } else {
            jRBEstado.setSelected(!jRBEstado.isSelected());
        }
    }

    // Método para procesar el alias ingresado y buscar la mascota correspondiente
    private void procesarAlias() {
        alias = Utilidades.obtenerTextoDesdeCampo(jTAlias).trim();
        if (alias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes escribir el Alias");
        } else {
            limpiarMostrar();
            buscarMascotaPorAlias();
        }
    }
}
