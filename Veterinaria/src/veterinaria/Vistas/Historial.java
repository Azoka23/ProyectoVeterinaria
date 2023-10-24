package veterinaria.Vistas;

import veterinaria.Vistas.Estado;
import veterinaria.Vistas.DesktopPaneWithBackground;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.ConsultaDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.AccesoADatos.TratamientoDAO;
import veterinaria.AccesoADatos.TratamientoRealizadoDAO;
import veterinaria.AccesoADatos.VisitaDAO;
import veterinaria.Vistas.CustomPanel;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.ConsultasLista;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Entidades.TratamientoRealizado;
import veterinaria.Entidades.Visita;
import veterinaria.Utilidades;

/**
 *
 * @author marcelaaliciaarroyo
 */
public class Historial extends javax.swing.JInternalFrame {

    // Declaración de variables de instancia y atributos para la clase
    private Estado estado; // Variable para controlar el estado del formulario
    private Estado estadoGuardado; // Variable para controlar el estado de guardado del formulario
    private String alias; // Variable para almacenar el alias de la mascota seleccionada
    private Cliente selectedCliente = null; // Cliente seleccionado para la visita
    private Tratamiento tratamiento; // Objeto para almacenar información sobre el tratamiento
    private int idMascotas = 0; // Identificador de la mascota seleccionada
    private int idCliente = 0; // Identificador del cliente
    private int idTratamiento = 0; // Identificador del tratamiento seleccionado
    private boolean estadoTratamiento; // Estado del tratamiento (activo o inactivo)
    private DesktopPaneWithBackground desktopPane; // Referencia al panel de escritorio
    private int dni; // Número de documento del cliente
    private int indiceColumnaOcultaMacota;
    private int indiceColumnaOcultaCliente;
    private int indiceColumnaOcultaTratamiento;
    private Map<Integer, Tratamiento> tratamientosSeleccionados = new HashMap<>(); // Mapa para almacenar tratamientos seleccionados

    private DefaultTableModel historialModel = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private DefaultTableModel mascotaModel = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private DefaultTableModel tratamientoModel = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private DefaultTableModel clientesModel = new DefaultTableModel() {

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    /**
     * Creates new form Historial
     */
    public Historial() {
        CustomPanel customPanel = new CustomPanel(); // Crea un panel personalizado
        this.setContentPane(customPanel); // Establece el panel personalizado como el contenido del marco interno
        
        // Inicializa los componentes del formulario
        initComponents();

        // Carga los datos en el combo box de historias clínicas
        cargarComboHistoriaL();

        // Establece el título del formulario
        setTitle("Historial - Listar todas las visitas de una misma mascota");

        // Inicializa las cabeceras de las tablas
        inicializarCabezeras();

        // Carga los datos en el combo box de historias clínicas nuevamente (repetido)
        cargarComboHistoriaL();

        // Configura los listeners (eventos) para los componentes del formulario
        configurarListeners();

        // Selecciona un cliente en la tabla de clientes
        seleccionarCliente();

        // Selecciona una mascota en la tabla de mascotas
        seleccionarMascotas();

        // Selecciona un tratamiento en la tabla de tratamientos
        seleccionarTratamiento();

        // Carga los datos de los clientes en la tabla de clientes
        cargarTablaClientes();

        // Oculta la columna "Id" en la tabla de clientes
        ocultarColumna(jTClientes, "Id");

        // Establece el estado inicial del formulario como CLIENTE_MASCOTA
        estado = Estado.CLIENTE_MASCOTA;

        // Configura el modo de ajuste automático de las columnas para la tabla de clientes
        jTClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para permitir ajustes manuales
        packAll(jTClientes); // Ajusta el ancho de las columnas

        // Configura el modo de ajuste automático de las columnas para la tabla de mascotas
        jTMascotas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para permitir ajustes manuales
        packAll(jTMascotas); // Ajusta el ancho de las columnas
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLListado = new javax.swing.JLabel();
        jCBListar = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLClientes = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTClientes = new javax.swing.JTable();
        jLMascotas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMascotas = new javax.swing.JTable();
        jLTratamientos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTTTratamientos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLHistoriales = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTHistoriales = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jBSalir = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLListado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLListado.setText("Seleccionar Listados");

        jCBListar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jCBListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBListarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLListado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBListar, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLListado)
                    .addComponent(jCBListar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        jLClientes.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLClientes.setText("Clientes");

        jTClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTClientes);

        jLMascotas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLMascotas.setText("Mascotas");

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

        jLTratamientos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLTratamientos.setText("Tipos de Tratamientos");

        jTTTratamientos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTTTratamientos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLClientes))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLMascotas)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLTratamientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLClientes)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLMascotas)
                        .addComponent(jLTratamientos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        jLHistoriales.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLHistoriales.setText("Historiales");

        JTHistoriales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(JTHistoriales);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLHistoriales)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLHistoriales)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setOpaque(false);

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(jBSalir)
                    .addContainerGap(50, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(118, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBListarActionPerformed

    }//GEN-LAST:event_jCBListarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTHistoriales;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<ConsultasLista> jCBListar;
    private javax.swing.JLabel jLClientes;
    private javax.swing.JLabel jLHistoriales;
    private javax.swing.JLabel jLListado;
    private javax.swing.JLabel jLMascotas;
    private javax.swing.JLabel jLTratamientos;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTClientes;
    private javax.swing.JTable jTMascotas;
    private javax.swing.JTable jTTTratamientos;
    // End of variables declaration//GEN-END:variables

    /**
     * Carga las opciones en el JComboBox para listar diferentes tipos de
     * consultas.
     */
    public void cargarComboHistoriaL() {
        try {
            jCBListar.removeAllItems(); // Limpia el JComboBox para evitar duplicados
            ConsultaDAO consultas = ConsultaDAO.obtenerInstancia();
            Collection<ConsultasLista> listasDeConsultas = new ArrayList<>();
            listasDeConsultas = consultas.listarConsultas();

            // Agrega las opciones al JComboBox
            for (ConsultasLista tipo : listasDeConsultas) {
                jCBListar.addItem(tipo);
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Configura la cabecera de la tabla de historiales para mostrar visitas de
     * una mascota.
     */
    private void armarCabeceraHistorialVisita() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Fecha");
        historialModel.addColumn("Diagnóstico");
        historialModel.addColumn("Importe");
        historialModel.addColumn("Alias");
        JTHistoriales.setModel(historialModel);
    }

    /**
     * Configura la cabecera de la tabla de historiales para mostrar mascotas
     * que hacen el mismo tratamiento.
     */
    private void armarCabeceraHistorialTratamiento() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Alias");
        historialModel.addColumn("Peso");
        JTHistoriales.setModel(historialModel);
    }

    /**
     * Configura la cabecera de la tabla de historiales para mostrar mascotas
     * activas.
     */
    private void armarCabeceraHistorialMascotaActivo() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Alias");
        historialModel.addColumn("Peso Actual");
        historialModel.addColumn("Apellido");
        historialModel.addColumn("Nombre");
        JTHistoriales.setModel(historialModel);
    }

    /**
     * Configura la cabecera de la tabla de historiales para mostrar
     * tratamientos de una mascota en específico.
     */
    private void armarCabeceraHistorialMascotaTratamientos() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Fecha Visita");
        historialModel.addColumn("Tipo Tratamiento");
        historialModel.addColumn("Descripción");
        JTHistoriales.setModel(historialModel);
    }

    /**
     * Configura la cabecera de la tabla de historiales para mostrar
     * tratamientos según su estado.
     */
    private void armarCabeceraHistorialTratamientoXEstado() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Tipo");
        historialModel.addColumn("Descripción");
        historialModel.addColumn("Importe");
        JTHistoriales.setModel(historialModel);
    }

    /**
     * Configura la cabecera de la tabla de historiales para mostrar clientes
     * según su estado.
     */
    private void armarCabeceraHistorialClienteXEstado() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Apellido");
        historialModel.addColumn("Nombre");
        historialModel.addColumn("DNI");
        historialModel.addColumn("Teléfono");
        historialModel.addColumn("E-mail");
        JTHistoriales.setModel(historialModel);
    }

    /**
     * Configura la cabecera de la tabla de historiales para mostrar clientes
     * con o sin mascotas.
     */
    private void armarCabeceraClienteConSinMascota() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Apellido");
        historialModel.addColumn("Nombre");
        historialModel.addColumn("DNI");
        JTHistoriales.setModel(historialModel);
    }

    /**
     * Configura la cabecera de la tabla de mascotas.
     */
    private void armarCabeceraMascota() {
        mascotaModel.setColumnCount(0); // Elimina todas las columnas existentes
        mascotaModel.addColumn("Id");
        mascotaModel.addColumn("Alias");
        mascotaModel.addColumn("Peso");
        jTMascotas.setModel(mascotaModel);
    }

    /**
     * Configura la cabecera de la tabla de clientes.
     */
    private void armarCabeceraCliente() {
        clientesModel.setColumnCount(0); // Elimina todas las columnas existentes
        clientesModel.addColumn("Id");
        clientesModel.addColumn("DNI");
        clientesModel.addColumn("Apellido");
        clientesModel.addColumn("Nombre");
        jTClientes.setModel(clientesModel);
    }

    /**
     * Configura la cabecera de la tabla de tipos de tratamientos.
     */
    private void armarCabeceraTipo() {
        tratamientoModel.setColumnCount(0); // Elimina todas las columnas existentes
        tratamientoModel.addColumn("Id");
        tratamientoModel.addColumn("Tipo");
        jTTTratamientos.setModel(tratamientoModel);
    }

    /**
     * Cierra la aplicación si el usuario confirma la salida.
     */
    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    /**
     * Carga las mascotas de un cliente específico en la tabla de mascotas.
     *
     * @param documento Número de documento del cliente.
     */
    private void cargarTablaMascotas(int documento) {
        try {
            MascotaDAO mascotaDAO = MascotaDAO.obtenerInstancia();
            Collection<Mascota> listaMascota = mascotaDAO.listarMascotasxIdCliente(documento);
            mascotaModel.setRowCount(0); // Limpia la tabla de mascotas antes de agregar nuevos datos

            for (Mascota tipo : listaMascota) {
                if (tipo.isEstado()) {
                    mascotaModel.addRow(new Object[]{tipo.getIdMascota(), tipo.getAlias(), tipo.getPesoActual()});
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga las mascotas activas o inactivas en la tabla de historiales.
     *
     * @param activo Indica si se deben mostrar las mascotas activas (true) o
     * inactivas (false).
     */
    private void cargarTablaMascotasActivoInactivo(boolean activo) {
        try {
            MascotaDAO mascotaDAO = MascotaDAO.obtenerInstancia();
            Collection<Mascota> listaMascotas = mascotaDAO.listarMascotasPorEstado(activo);
            historialModel.setRowCount(0); // Limpia la tabla de historiales antes de agregar nuevos datos

            for (Mascota mascota : listaMascotas) {
                historialModel.addRow(new Object[]{mascota.getAlias(), mascota.getPesoActual(),
                    mascota.getIdCliente().getApellido(), mascota.getIdCliente().getNombre()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga los tratamientos activos o inactivos en la tabla de historiales.
     *
     * @param activo Indica si se deben mostrar los tratamientos activos (true)
     * o inactivos (false).
     */
    private void cargarTablaTratamientoActivoInactivo(boolean activo) {
        try {
            TratamientoDAO tratamientoDAO = TratamientoDAO.obtenerInstancia();
            Collection<Tratamiento> listaTratamiento = tratamientoDAO.listarTratamientoPorEstado(activo);
            historialModel.setRowCount(0); // Limpia la tabla de historiales antes de agregar nuevos datos

            for (Tratamiento tipo : listaTratamiento) {
                historialModel.addRow(new Object[]{tipo.getTipo(), tipo.getDescripcion(), tipo.getImporte()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga los clientes activos o inactivos en la tabla de historiales.
     *
     * @param activo Indica si se deben mostrar los clientes activos (true) o
     * inactivos (false).
     */
    private void cargarTablaClienteActivoInactivo(boolean activo) {
        try {
            ClienteDAO clienteDAO = ClienteDAO.obtenerInstancia();
            Collection<Cliente> listaCliente = clienteDAO.listarClientePorEstado(activo);
            historialModel.setRowCount(0); // Limpia la tabla de historiales antes de agregar nuevos datos

            for (Cliente tipo : listaCliente) {
                historialModel.addRow(new Object[]{tipo.getApellido(), tipo.getNombre(), tipo.getDni(), tipo.getTelefono(), tipo.getEmail()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga todos los clientes activos en la tabla de clientes.
     */
    private void cargarTablaClientes() {
        try {
            ClienteDAO clienteDAO = ClienteDAO.obtenerInstancia();
            Collection<Cliente> listaCliente = clienteDAO.obtenerClientes();
            clientesModel.setRowCount(0); // Limpia la tabla de clientes antes de agregar nuevos datos

            for (Cliente tipo : listaCliente) {
                if (tipo.isEstado()) {
                    clientesModel.addRow(new Object[]{tipo.getIdCliente(), tipo.getDni(), tipo.getApellido(), tipo.getNombre()});
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga los clientes con o sin mascota en la tabla de historiales.
     *
     * @param conMascota Indica si se deben mostrar los clientes con mascotas
     * (true) o sin mascotas (false).
     */
    private void cargarTablaClientesConSinMascota(boolean conMascota) {
        try {
            ClienteDAO clienteDAO = ClienteDAO.obtenerInstancia();
            Collection<Cliente> listaCliente = new ArrayList<>(); // Inicialización predeterminada

            if (conMascota) {
                listaCliente = clienteDAO.obtenerClientesConMascota();
            } else {
                listaCliente = clienteDAO.obtenerClientesSinMascota();
            }

            historialModel.setRowCount(0); // Limpia la tabla de historiales antes de agregar nuevos datos
            for (Cliente tipo : listaCliente) {
                if (tipo.isEstado()) {
                    historialModel.addRow(new Object[]{tipo.getApellido(), tipo.getNombre(), tipo.getDni()});
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga las mascotas que han recibido un tipo de tratamiento específico en
     * la tabla de historiales.
     *
     * @param idTratamiento Identificador del tipo de tratamiento.
     * @throws Exception Si ocurre un error al acceder a la base de datos.
     */
    private void cargarMascotaXTipo(int idTratamiento) throws Exception {
        MascotaDAO mascotaDAO = MascotaDAO.obtenerInstancia();
        Collection<Mascota> listaMascota = mascotaDAO.listarMascotasXTipoTratamiento(idTratamiento);
        historialModel.setRowCount(0); // Limpia la tabla de historiales antes de agregar nuevos datos

        for (Mascota tipo : listaMascota) {
            if (tipo.isEstado()) {
                historialModel.addRow(new Object[]{tipo.getAlias(), tipo.getPesoActual()});
            }
        }
    }

    /**
     * Carga en la tabla de historial las visitas asociadas a una mascota
     * específica.
     *
     * @param idMascotas Identificador de la mascota.
     */
    private void tablaHistorialVisita(int idMascotas) {
        try {
            VisitaDAO cursadas = VisitaDAO.obtenerInstancia();
            Collection<Visita> listaVistas = cursadas.listarMascotasPorVisitas(idMascotas);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            // Itera sobre las visitas y agrega filas a la tabla
            for (Visita tipo : listaVistas) {
                historialModel.addRow(new Object[]{tipo.getFechaVisita(), tipo.getDetallesSintoma(), tipo.getImporteVisita()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga en la tabla de historial los tratamientos realizados para una
     * mascota específica.
     *
     * @param idMascotas Identificador de la mascota.
     */
    private void tablaHistorialMascotaTratamientos(int idMascotas) {
        try {
            TratamientoRealizadoDAO cursadas = TratamientoRealizadoDAO.obtenerInstancia();
            Collection<TratamientoRealizado> listaMascotaTratamientos = cursadas.obtenerTratamientosDeMascota(idMascotas);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            // Itera sobre los tratamientos realizados y agrega filas a la tabla
            for (TratamientoRealizado tipo : listaMascotaTratamientos) {
                historialModel.addRow(new Object[]{tipo.getIdVisita().getFechaVisita(), tipo.getIdTratamiento().getTipo(), tipo.getIdTratamiento().getDescripcion()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga en la tabla de historial las visitas asociadas a un cliente
     * específico.
     *
     * @param idCliente Identificador del cliente.
     */
    private void tablaHistorialVisitaCliente(int idCliente) {
        try {
            VisitaDAO cursadas = VisitaDAO.obtenerInstancia();
            Map<Visita, String> visitasConAlias = cursadas.obtenerVisitasDeCliente(idCliente);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            // Si el mapa está vacío, muestra un mensaje, de lo contrario, agrega filas a la tabla
            if (visitasConAlias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El Cliente todavía no ha realizado visitas!");
            } else {
                for (Map.Entry<Visita, String> entry : visitasConAlias.entrySet()) {
                    Visita tipo = entry.getKey();
                    String val = entry.getValue();
                    historialModel.addRow(new Object[]{tipo.getFechaVisita(), tipo.getDetallesSintoma(), tipo.getImporteVisita(), val});
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga en la tabla de tratamientos los tipos de tratamiento activos.
     */
    private void tablaTipoTratamiento() {
        try {
            TratamientoDAO cursadas = TratamientoDAO.obtenerInstancia();
            Collection<Tratamiento> listaTratamiento = cursadas.obtenerTratamientos();
            tratamientoModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            // Itera sobre los tipos de tratamiento y agrega filas a la tabla
            for (Tratamiento tipo : listaTratamiento) {
                if (tipo.isEstado()) {
                    tratamientoModel.addRow(new Object[]{tipo.getIdTratamiento(), tipo.getTipo()});
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ajusta automáticamente el ancho de las columnas de una tabla.
     *
     * @param table Tabla a la que se le ajustarán las columnas.
     */
    private void packAll(JTable table) {
        TableColumnModel model = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Ancho mínimo
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            model.getColumn(column).setPreferredWidth(width);
        }
    }

    /**
     * Inicializa las cabeceras de las tablas al iniciar la ventana.
     */
    private void inicializarCabezeras() {
        armarCabeceraMascota();
        armarCabeceraTipo();
        armarCabeceraCliente();
        armarCabeceraHistorialVisita();
    }

    /**
     * Oculta una columna específica en una tabla.
     *
     * @param table Tabla en la que se ocultará la columna.
     * @param nombreColumna Nombre de la columna que se ocultará.
     */
    private void ocultarColumna(JTable table, String nombreColumna) {
        int indiceColumnaOculta = table.getColumn(nombreColumna).getModelIndex();
        TableColumn columna = table.getColumnModel().getColumn(indiceColumnaOculta);
        columna.setWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
    }

    /**
     * Configura los listeners para el JComboBox que permite seleccionar
     * diferentes opciones de historial.
     */
    private void configurarListeners() {
        // Listener para el JComboBox
        jCBListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene el índice de la opción seleccionada en el JComboBox
                int selectedIndex = jCBListar.getSelectedIndex();
                switch (selectedIndex) {
                    // Opción 0: Listar todas las visitas de una misma mascota
                    case 0:
                        // Configuración de la interfaz y los datos para mostrar el historial de una mascota específica
                        setTitle("Historial - Listar todas las visitas de una misma mascota.");
                        jLHistoriales.setText("Historial - Listar todas las visitas de una misma mascota");

                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas de clientes y historial de visitas
                        armarCabeceraCliente();
                        armarCabeceraHistorialVisita();

                        // Carga los datos de los clientes en la tabla de clientes
                        cargarTablaClientes();

                        // Oculta la columna de ID en la tabla de clientes
                        ocultarColumna(jTClientes, "Id");

                        // Cambia el estado del programa para indicar que se está mostrando el historial de una mascota
                        estado = Estado.CLIENTE_MASCOTA;
                        break;
                    // Opción 1: Listar todas las visitas de un mismo Cliente
                    case 1:
                        // Configuración de la interfaz y los datos para mostrar el historial de un cliente específico
                        setTitle("Historial - Listar todas las visitas de un mismo Cliente.");
                        jLHistoriales.setText("Historial - Listar todas las visitas de un mismo Cliente.");

                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas de clientes y historial de visitas
                        armarCabeceraCliente();
                        armarCabeceraHistorialVisita();

                        // Carga los datos de los clientes en la tabla de clientes
                        cargarTablaClientes();

                        // Oculta la columna de ID en la tabla de clientes
                        ocultarColumna(jTClientes, "Id");

                        // Cambia el estado del programa para indicar que se está mostrando el historial de un cliente
                        estado = Estado.CLIENTE_VISITA;
                        break;
                    // Opción 11: Listar todas las mascotas que hacen el mismo tratamiento
                    case 11:
                        // Configuración de la interfaz y los datos para mostrar mascotas con un tratamiento específico
                        setTitle("Historial - Listar todas las mascotas que hacen el mismo tratamiento");
                        jLHistoriales.setText("Historial - Listar todas las mascotas que hacen el mismo tratamiento");

                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas de historial de tratamientos y tipos de tratamiento
                        armarCabeceraHistorialTratamiento();
                        armarCabeceraTipo();

                        // Carga los datos de los tipos de tratamiento en la tabla de tratamientos
                        tablaTipoTratamiento();

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;

                        // Oculta la columna de ID en la tabla de tratamientos
                        ocultarColumna(jTTTratamientos, "Id");

                        // Cambia el título de la interfaz y la etiqueta para indicar que se están mostrando mascotas
                        jLHistoriales.setText("Historial - Mascotas");
                        break;
                    // Opción 2: Listar Clientes que tienen Mascotas Activas
                    case 2:
                        // Configura la etiqueta para mostrar que se están listando clientes con mascotas
                        jLHistoriales.setText("Historial - Listar Clientes que tienen Mascotas");
                        setTitle("Historial - Listar Clientes que tienen Mascotas");

                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas de clientes con o sin mascotas
                        armarCabeceraClienteConSinMascota();

                        // Carga los datos de los clientes con mascotas en la tabla de clientes
                        cargarTablaClientesConSinMascota(true);

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;

                    // Opción 3: Listar Clientes que no tienen Mascotas Activas
                    case 3:
                        // Configura la etiqueta para mostrar que se están listando clientes sin mascotas
                        jLHistoriales.setText("Historial - Listar Clientes que no tienen Mascotas");
                        setTitle("Historial - Listar Clientes que no tienen Mascotas");

                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas de clientes con o sin mascotas
                        armarCabeceraClienteConSinMascota();

                        // Carga los datos de los clientes sin mascotas en la tabla de clientes
                        cargarTablaClientesConSinMascota(false);

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;
                    // Opción 5: Listar Mascotas Activas
                    case 5:
                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura la etiqueta para mostrar que se están listando mascotas activas
                        jLHistoriales.setText("Historial - Activas");
                        setTitle("Historial - Listar Mascotas Activos");

                        // Configura las cabeceras de las tablas de mascotas activas
                        armarCabeceraHistorialMascotaActivo();

                        // Carga los datos de las mascotas activas en la tabla de mascotas
                        cargarTablaMascotasActivoInactivo(true);

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                    // Opción 4: Listar Mascotas Inactivas
                    case 4:
                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura la etiqueta para mostrar que se están listando mascotas inactivas
                        jLHistoriales.setText("Historial - Inactivas");
                        setTitle("Historial - Listar Mascotas Inactivas");

                        // Configura las cabeceras de las tablas de mascotas inactivas
                        armarCabeceraHistorialMascotaActivo();

                        // Carga los datos de las mascotas inactivas en la tabla de mascotas
                        cargarTablaMascotasActivoInactivo(false);

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;
                    // Opción 6: Listar Tratamientos Activos
                    case 6:
                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas para mostrar tratamientos activos
                        armarCabeceraHistorialTratamientoXEstado();

                        // Carga los datos de los tratamientos activos en la tabla de tratamientos
                        cargarTablaTratamientoActivoInactivo(true);

                        // Configura las etiquetas y el título para reflejar que se están mostrando tratamientos activos
                        jLHistoriales.setText("Historial - Activas");
                        setTitle("Historial - Listar Tratamientos Activos");

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;
                    // Opción 7: Listar Tratamientos Inactivos
                    case 7:
                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas para mostrar tratamientos inactivos
                        armarCabeceraHistorialTratamientoXEstado();

                        // Carga los datos de los tratamientos inactivos en la tabla de tratamientos
                        cargarTablaTratamientoActivoInactivo(false);

                        // Configura las etiquetas y el título para reflejar que se están mostrando tratamientos inactivos
                        jLHistoriales.setText("Historial - Inactivas");
                        setTitle("Historial - Listar Tratamientos Inactivos");

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;
                    // Opción 8: Listar Clientes Activos
                    case 8:
                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas para mostrar clientes activos
                        armarCabeceraHistorialClienteXEstado();

                        // Carga los datos de los clientes activos en la tabla de clientes
                        cargarTablaClienteActivoInactivo(true);

                        // Configura las etiquetas y el título para reflejar que se están mostrando clientes activos
                        jLHistoriales.setText("Historial - Activos");
                        setTitle("Historial - Listar Clientes Activos");

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;
                    // Opción 9: Listar Clientes Inactivos
                    case 9:
                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas para mostrar clientes inactivos
                        armarCabeceraHistorialClienteXEstado();

                        // Carga los datos de los clientes inactivos en la tabla de clientes
                        cargarTablaClienteActivoInactivo(false);

                        // Configura las etiquetas y el título para reflejar que se están mostrando clientes inactivos
                        jLHistoriales.setText("Historial - Inactivos");
                        setTitle("Historial - Listar Clientes Inactivos");

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;
                    // Opción 10: Listar Tratamientos Realizados por Mascota
                    case 10:
                        // Configura las etiquetas y el título para reflejar que se están mostrando tratamientos realizados por mascota
                        setTitle("Historial - Listar los Tratamientos Realizados por Mascota");
                        jLHistoriales.setText("Historial - Listar los Tratamientos Realizados por Mascota");

                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);

                        // Configura las cabeceras de las tablas para mostrar información de clientes y mascotas
                        armarCabeceraCliente();
                        cargarTablaClientes();
                        armarCabeceraHistorialMascotaTratamientos();

                        // Oculta la columna de identificación del cliente en la tabla de clientes
                        ocultarColumna(jTClientes, "Id");

                        // Cambia el estado del programa para indicar que se está mostrando el historial de tratamientos realizados por mascota
                        estado = Estado.MASCOTA_TRATAMIENTO;
                        break;
                    // Opción por defecto: Mostrar el estado inicial del programa sin ningún historial específico
                    default:
                        // Configura las etiquetas y el título para reflejar que no se está mostrando ningún historial específico
                        setTitle("Historial");
                        jLHistoriales.setText("Historial");

                        // Limpia los datos de las tablas de clientes, mascotas, historial y tratamientos
                        tratamientoModel.setRowCount(0);
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);

                        // Cambia el estado del programa para indicar que no se está mostrando el historial de una mascota o cliente
                        estado = Estado.NADA;
                        break;
                }
            }
        });

    }

    private void seleccionarCliente() {
        // Agregar un listener para detectar cambios en la selección de filas en la tabla de clientes
        jTClientes.getSelectionModel().addListSelectionListener(e -> {
            // Obtener la fila seleccionada en la tabla de clientes
            int selectedRow = jTClientes.getSelectedRow();

            // Verificar si se ha seleccionado una fila válida (-1 indica que no hay selección)
            if (selectedRow != -1) {
                // Obtener el ID del cliente seleccionado en la tabla
                idCliente = (Integer) jTClientes.getValueAt(selectedRow, indiceColumnaOcultaCliente);

                // Deshabilitar el ajuste automático del tamaño de las columnas y ajustar el tamaño de las columnas de la tabla
                jTClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                packAll(jTClientes);

                // Verificar el estado actual del programa para determinar qué acción realizar
                if (estado.equals(Estado.CLIENTE_MASCOTA) || estado.equals(Estado.MASCOTA_TRATAMIENTO)) {
                    // Si se está mostrando el historial de una mascota o tratamientos por mascota,
                    // cargar y mostrar las mascotas asociadas a este cliente
                    cargarTablaMascotas(idCliente);

                    // Ocultar la columna de identificación de mascota en la tabla de mascotas
                    ocultarColumna(jTMascotas, "Id");

                    // Actualizar la etiqueta para indicar que se está mostrando el historial de mascotas
                    jLHistoriales.setText("Historial - Mascotas");
                } else if (estado.equals(Estado.CLIENTE_VISITA)) {
                    // Si se está mostrando el historial de visitas de un cliente,
                    // cargar y mostrar el historial de visitas asociado a este cliente
                    tablaHistorialVisitaCliente(idCliente);

                    // Actualizar la etiqueta para indicar que se está mostrando el historial de visitas
                    jLHistoriales.setText("Historial - Mascotas");
                }
            }
        });

    }

    private void seleccionarMascotas() {
        // Agregar un listener para detectar cambios en la selección de filas en la tabla de mascotas
        jTMascotas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verificar si la selección ha cambiado y no está en modo de ajuste
                if (!e.getValueIsAdjusting()) {
                    // Obtener la fila seleccionada en la tabla de mascotas
                    int selectedRow = jTMascotas.getSelectedRow();
                    // Verificar si hay una fila seleccionada
                    if (selectedRow != -1) {
                        // Obtener el valor de la columna de ID de mascota y el alias de la mascota seleccionada
                        idMascotas = (Integer) jTMascotas.getValueAt(selectedRow, indiceColumnaOcultaMacota);
                        alias = (String) jTMascotas.getValueAt(selectedRow, 1);

                        // Verificar el estado actual del programa para determinar qué acción realizar
                        if (estado.equals(Estado.CLIENTE_MASCOTA)) {
                            // Si se está mostrando el historial de una mascota específica,
                            // cargar y mostrar el historial de visitas de esa mascota
                            jLHistoriales.setText("Historial - Mascota con Alias: " + alias);
                            tablaHistorialVisita(idMascotas);
                        } else if (estado.equals(Estado.MASCOTA_TRATAMIENTO)) {
                            // Si se está mostrando el historial de tratamientos de una mascota específica,
                            // cargar y mostrar el historial de tratamientos de esa mascota
                            jLHistoriales.setText("Historial - Mascota con Alias: " + alias);
                            tablaHistorialMascotaTratamientos(idMascotas);
                        }
                    }
                }
            }
        });
    }

    private void seleccionarTratamiento() {
        // Agregar un listener para detectar cambios en la selección de filas en la tabla de tratamientos
        jTTTratamientos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verificar si la selección ha cambiado y no está en modo de ajuste
                if (!e.getValueIsAdjusting()) {
                    // Obtener la fila seleccionada en la tabla de tratamientos
                    int selectedRow = jTTTratamientos.getSelectedRow();
                    // Verificar si hay una fila seleccionada
                    if (selectedRow != -1) {
                        try {
                            // Obtener el valor de la columna de ID de tratamiento en la fila seleccionada
                            idTratamiento = (Integer) jTTTratamientos.getValueAt(selectedRow, indiceColumnaOcultaTratamiento);
                            // Cargar y mostrar las mascotas asociadas a este tipo de tratamiento
                            cargarMascotaXTipo(idTratamiento);
                        } catch (Exception ex) {
                            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }
}
