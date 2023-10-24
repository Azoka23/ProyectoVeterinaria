package veterinaria.Vistas;

// Importación de librerías necesarias
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;

// Definición de la clase del formulario de mascotas
public class FormularioMascotas extends javax.swing.JInternalFrame {

    // Variables miembro
    private Estado estado;
    private MascotaFormListener listener;
    private Cliente selectedCliente = null;
    private int idMascotas = 0;
    private int idCliente = 0;
    private boolean estadoMascota;

    // Constructor de la clase, recibe el ID del cliente como parámetro
    public FormularioMascotas(int idCliente) {
        try {
            // Inicialización de componentes del formulario
            this.idCliente = idCliente;
            initComponents();
            setTitle("Cargar Mascotas");

            // Configuración y carga de datos en los combos y campos de texto
            cargarCombo();
            cargarComboSexo();

            // Configuración del filtro para el campo de peso actual (reemplazar comas por puntos)
            AbstractDocument doc = (AbstractDocument) jTPesoA.getDocument();
            doc.setDocumentFilter(new DecimalDocumentFilter());

            // Asociación de eventos y establecimiento del foco en el campo de alias
            asociarCamposConEnter();
            SwingUtilities.invokeLater(() -> {
                jTAlias.requestFocusInWindow();
            });

            // Configuración del estado inicial de algunos campos y del radio button de estado
            jRBEstado.setSelected(true);
            jTCodigo.setText(ultimoRegistro() + "");
            SwingUtilities.invokeLater(() -> {
                jTAlias.requestFocusInWindow();
            });
            jTPesoM.setEnabled(false);

        } catch (Exception ex) {
            Logger.getLogger(FormularioMascotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new CustomPanel();
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

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
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
        jBGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jBGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jBSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jBSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBGuardar)
                        .addGap(27, 27, 27)
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
            // Validación de campos y llamada al método de guardado si los campos no están vacíos
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

    private void jRBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActionPerformed
        jRBEstado.setSelected(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBEstadoActionPerformed


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

    // Método para limpiar todos los campos del formulario
    private void limpiar() {
        Utilidades.limpiarSetText(jTCodigo, jTAlias, jTColorDePelo, jTEspecies, jTRaza, jTColorDePelo, jTPesoM, jTPesoA);
        jRBEstado.setSelected(false);
        estado = Estado.NADA;
        // Para limpiar el formulario y deseleccionar los JComboBox
        JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox

    }

    // Método para limpiar los campos de búsqueda
    private void limpiarBuscar() {
        Utilidades.limpiarSetText(jTAlias, jTColorDePelo, jTEspecies, jTRaza, jTColorDePelo, jTPesoM, jTPesoA);
        // Para limpiar el formulario y deseleccionar los JComboBox
        JCSexo.setSelectedIndex(-1); // Desselecciona el elemento en el JComboBox

    }

//// Método para buscar una mascota por su código
//    private void buscarxCodigo() {
//        MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();
//        int codigo = Utilidades.obtenerEnteroDesdeCampo(jTCodigo);
//
//        try {
//            // Intentar obtener la mascota desde la base de datos
//            Mascota mascota = mascotaD.obtenerMascotaPorId(codigo);
//
//            // Si se encontró la mascota, mostrarla en el formulario
//            if (mascota != null) {
//                setTitle("Cargar Mascota" + (mascota.isEstado() ? "" : " -- Codigo dado de Baja"));
//                jRBEstado.setSelected(mascota.isEstado());
//                mostrarMascotaseEnFormulario(mascota);
//            } else {
//                // Si no se encontró la mascota, mostrar un mensaje y establecer el estado como NUEVO
//                estado = Estado.NUEVO;
//                JOptionPane.showMessageDialog(this, "No se encontró el código, el código disponible es " + ultimoRegistro());
//                jTCodigo.setText(ultimoRegistro() + "");
//            }
//        } catch (NumberFormatException e) {
//            // Manejar excepción si el código no es un número válido
//            JOptionPane.showMessageDialog(this, "Error: El código debe ser un número válido.");
//        } catch (Exception ex) {
//            // Mostrar cualquier otra excepción que pueda ocurrir
//            Utilidades.mostrarError(ex, this);
//        }
//    }

// Método para guardar una nueva mascota o actualizar una existente
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
            //String alias = Utilidades.obtenerTextoDesdeCampo(jTAlias);
            Sexo sexo = (Sexo) JCSexo.getSelectedItem();
            //String especie = Utilidades.obtenerTextoDesdeCampo(jTEspecies);
            //String raza = Utilidades.obtenerTextoDesdeCampo(jTRaza);
            //String colorDePelo = Utilidades.obtenerTextoDesdeCampo(jTColorDePelo);
            LocalDate fechaNacimiento = jDCFechaNac.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //double pesoActual = obtenerPeso(jTPesoA);
            //double pesoMedia = pesoActual;

            //boolean estadoMascota = jRBEstado.isSelected();
            Cliente idCliente = (Cliente) jCBClientes.getSelectedItem();
            // Crear un objeto de mascota con los valores obtenidos y guardarlo en la base de datos

            mascota = new Mascota(
                    codigo,
                    Utilidades.obtenerTextoDesdeCampo(jTAlias),
                    sexo,
                    Utilidades.obtenerTextoDesdeCampo(jTEspecies),
                    Utilidades.obtenerTextoDesdeCampo(jTRaza),
                    Utilidades.obtenerTextoDesdeCampo(jTColorDePelo),
                    fechaNacimiento,
                    obtenerPeso(jTPesoA),
                    obtenerPeso(jTPesoA),
                    idCliente,
                    jRBEstado.isSelected()
            );

            mascota.setEstado(true);
            mascotaD.guardarMascota(mascota);
            JOptionPane.showMessageDialog(this, "Mascota guardada correctamente ");
        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }
    }
// Método para obtener el último registro de mascotas

    private int ultimoRegistro() throws ClassNotFoundException, SQLException, Exception {
        MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();
        return mascotaD.contarTotalRegistros();

    }
// Método para cerrar la aplicación

    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {

            if (listener != null) {
                listener.onMascotaFormClosed();
            }
            dispose();
        }
    }
// Método para validar si hay campos vacíos en el formulario

    private boolean camposVacios() {
        return jTCodigo.getText().isEmpty() || jTAlias.getText().isEmpty() || jDCFechaNac.getDate() == null;
    }

    private void cargarCombo() {

        ClienteDAO clienteD = ClienteDAO.obtenerInstancia();

        Collection<Cliente> clientes;
        clientes = new ArrayList<>();

        try {
            clientes = clienteD.listarCliente();
        } catch (Exception ex) {
            Utilidades.mostrarError(ex, this);
        }

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

//    // Método para mostrar los datos de una mascota en el formulario
//    private void mostrarMascotaseEnFormulario(Mascota mascota) {
//        jTAlias.setText(mascota.getAlias());
//        JCSexo.setSelectedItem(mascota.getSexo());
//        jTEspecies.setText(mascota.getEspecie());
//        jTRaza.setText(mascota.getRaza());
//        jTColorDePelo.setText(mascota.getColorDePelo());
//        jDCFechaNac.setDate(Date.valueOf(mascota.getFechaNacimiento()));
//        jTPesoM.setText(mascota.getPesoMedia() + "");
//        jTPesoA.setText(mascota.getPesoMedia() + "");
//
//        if (mascota.isEstado()) {
//            setTitle("Cargar Mascotas");
//        } else {
//            setTitle("Mascota -- Codigo dado de Baja");
//        }
//
//        jRBEstado.setSelected(mascota.isEstado());
//        estadoMascota = mascota.isEstado();
//        jCBClientes.setSelectedItem(mascota.getIdCliente());
//    }

    // Método para establecer el listener para el formulario de mascotas
    public void setMascotaFormListener(MascotaFormListener listener) {
        this.listener = listener;
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
// Método para asociar el evento Enter con los componentes del formulario

    private void asociarCamposConEnter() {
        Utilidades.asociarEnterConComponente(jTAlias, JCSexo);
        Utilidades.asociarEnterConComponente(JCSexo, jTColorDePelo);
        Utilidades.asociarEnterConComponente(jTColorDePelo, jTEspecies);
        Utilidades.asociarEnterConComponente(jTEspecies, jTRaza);
        Utilidades.asociarEnterConComponente(jTRaza, jTPesoA);
        Utilidades.asociarEnterConComponente(jTPesoA, jDCFechaNac);
        Utilidades.asociarEnterConComponente(jDCFechaNac, jBGuardar);
    }

//    // Método para configurar el estado de la mascota (dado de alta o baja)
//    private void configurarEstadoMascota() {
//        jRBEstado.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (estado.equals(Estado.BUSCAR)) {
//                    confirmarCambioEstadoMascota();
//                }
//            }
//        });
//    }

//    // Método para confirmar el cambio de estado de la mascota
//    private void confirmarCambioEstadoMascota() {
//        int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de cambiar el estado de la Mascota?",
//                "Confirmar Cambio de Estado", JOptionPane.YES_NO_OPTION);
//        if (option == JOptionPane.YES_OPTION) {
//            try {
//                int codigo = Integer.parseInt(jTCodigo.getText());
//                MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();
//
//                if (estadoMascota) {
//                    mascotaD.bajaLogica(codigo);
//                    setTitle("Mascota -- Codigo dado de Baja");
//                    JOptionPane.showMessageDialog(this, "La Mascota ha sido dado de baja");
//                } else {
//                    mascotaD.altaLogica(codigo);
//                    setTitle("Mascota");
//                    JOptionPane.showMessageDialog(this, "La Mascota ha sido dado de alta");
//                }
//            } catch (Exception ex) {
//                Utilidades.mostrarError(ex, this);
//            }
//        } else {
//            jRBEstado.setSelected(!jRBEstado.isSelected());
//        }
//    }

}
