package veterinaria.Vistas;

// Importación de paquetes y librerías necesarias para la clase
import veterinaria.Vistas.Estado;
import veterinaria.Vistas.DesktopPaneWithBackground;
import veterinaria.Vistas.DecimalDocumentFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.RenderDestination;

import veterinaria.AccesoADatos.ClienteDAO;
import veterinaria.AccesoADatos.MascotaDAO;
import veterinaria.AccesoADatos.TratamientoDAO;
import veterinaria.AccesoADatos.TratamientoRealizadoDAO;
import veterinaria.AccesoADatos.VisitaDAO;
import veterinaria.Vistas.CustomPanel;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Factura;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Entidades.TratamientoRealizado;
import veterinaria.Entidades.Visita;
import veterinaria.Utilidades;

//Declaración de la clase FormularioVisitas
public class FormularioVisitas extends javax.swing.JInternalFrame {

    // Declaración de variables de instancia y atributos para la clase
    private Estado estado; // Variable para controlar el estado del formulario
    private Estado estadoGuardado; // Variable para controlar el estado de guardado del formulario
    private String alias; // Variable para almacenar el alias de la mascota seleccionada
    private Cliente selectedCliente = null; // Cliente seleccionado para la visita
    private Tratamiento tratamiento; // Objeto para almacenar información sobre el tratamiento
    private int idMascotas = 0; // Identificador de la mascota seleccionada
    private int idCliente = 0; // Identificador del cliente de la mascota
    private int idTratamiento = 0; // Identificador del tratamiento seleccionado
    private boolean estadoTratamiento; // Estado del tratamiento (activo o inactivo)
    private DesktopPaneWithBackground desktopPane; // Referencia al panel de escritorio
    private String dni; // Número de documento del cliente
    private Map<Integer, Tratamiento> tratamientosSeleccionados = new HashMap<>(); // Mapa para almacenar tratamientos seleccionados

    // Modelos de tabla para mascotas y tratamientos
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

    // Constructor de la clase
    public FormularioVisitas(DesktopPaneWithBackground desktopPane) {
        CustomPanel customPanel = new CustomPanel(); // Crea un panel personalizado
        this.setContentPane(customPanel); // Establece el panel personalizado como el contenido del marco interno

        // Inicialización del formulario y componentes
        this.desktopPane = desktopPane;
        initComponents();
        setTitle("Cargar la Visita");
        // Configuración de campos y eventos
        armarCabecera();
        armarCabeceraTratamiento();
        limpiar();
        // Obtener el Document asociado al campo de texto jTPesoA
        AbstractDocument doc = (AbstractDocument) jTPesoA.getDocument();
        // Aplicar el DocumentFilter para reemplazar comas por puntos
        doc.setDocumentFilter(new DecimalDocumentFilter());
        // Agregar KeyListener al campo jTDni
        jTDni.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    estado = Estado.CLIENTE;
                    buscarPorDni();
                }
            }
        });

// Listener para la tabla de mascotas
        jTMascotas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verifica si la selección está cambiada y no está en modo de ajuste
                if (!e.getValueIsAdjusting()) {
                    // Obtiene la fila seleccionada
                    int selectedRow = jTMascotas.getSelectedRow();
                    // Verifica si hay una fila seleccionada
                    if (selectedRow != -1) {
                        // Obtén el valor de todas las columnas en la fila seleccionada
                        idMascotas = (Integer) jTMascotas.getValueAt(selectedRow, 0);
                        alias = (String) jTMascotas.getValueAt(selectedRow, 1);
                        // Limpia los campos de visita y carga los tratamientos
                        limpiarVisita();
                        cargarTratamientos();
                        // Habilita la edición y muestra información de la mascota seleccionada
                        jTTratamiento.setEnabled(true);
                        jTPesoA.setEditable(true);
                        jLMascota1.setText("Mascota: " + alias);
                        jTADescripcion.setEditable(true);
                        estadoGuardado = Estado.NO_GUARDADO;
                        estado = Estado.MASCOTA;
                    }
                }
            }
        });

// Listener para la tabla de tratamientos
        jTTratamiento.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verifica si la selección está cambiada y no está en modo de ajuste
                if (!e.getValueIsAdjusting()) {
                    // Obtiene las filas seleccionadas
                    int[] selectedRows = jTTratamiento.getSelectedRows();
                    double sumaImportes = 0.0;

                    // Crea un nuevo mapa para almacenar los tratamientos seleccionados
                    Map<Integer, Tratamiento> tratamientosSeleccionadosTemp = new HashMap<>();

                    // Suma los importes de las filas seleccionadas y crea el mapa de tratamientos seleccionados
                    for (int selectedRow : selectedRows) {
                        idTratamiento = (Integer) jTTratamiento.getValueAt(selectedRow, 0);
                        String tipo = (String) jTTratamiento.getValueAt(selectedRow, 1);
                        String descripcion = (String) jTTratamiento.getValueAt(selectedRow, 2);
                        double importe = (Double) jTTratamiento.getValueAt(selectedRow, 3);

                        // Crea un nuevo objeto Tratamiento y lo agrega al mapa de tratamientos seleccionados
                        tratamiento = new Tratamiento(idTratamiento, tipo, descripcion, importe, true);
                        tratamientosSeleccionadosTemp.put(idTratamiento, tratamiento);

                        // Suma los importes
                        sumaImportes += importe;
                    }

                    // Actualiza el mapa tratamientosSeleccionados solo después de procesar todas las selecciones
                    tratamientosSeleccionados.clear();
                    tratamientosSeleccionados.putAll(tratamientosSeleccionadosTemp);
                    estado = Estado.TRATAMIENTO;
                    // Muestra la suma en jTImporteTotal
                    jTImporteTotal.setText(String.valueOf(sumaImportes));
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLDNI = new javax.swing.JLabel();
        jLApellido = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jTDni = new javax.swing.JTextField();
        jTApellido = new javax.swing.JTextField();
        jTNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLMascota = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMascotas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jBBuscar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLMascota1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLTratamiento = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTTratamiento = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTImporteTotal = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLDescripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADescripcion = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLPeso = new javax.swing.JLabel();
        jTPesoA = new javax.swing.JTextField();
        jBGuardar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLDNI.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLDNI.setText("DNI Cliente");

        jLApellido.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLApellido.setText("Apellido");

        jLNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLNombre.setText("Nombre");

        jTDni.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTApellido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jTNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLDNI)
                    .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombre))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTDni, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLNombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        jLMascota.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLMascota.setText("Mascotas");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLMascota)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLMascota)
                    .addGap(6, 6, 6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBBuscar)
                        .addComponent(jBSalir))
                    .addContainerGap(28, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jBBuscar)
                    .addGap(18, 18, 18)
                    .addComponent(jBSalir)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setOpaque(false);

        jLMascota1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLMascota1.setText("Mascota");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLMascota1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLMascota1))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);

        jLTratamiento.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLTratamiento.setText("Tratamiento");

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTratamiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTratamiento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setText("Importe Total");

        jTImporteTotal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jTImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(28, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jTImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE)))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setOpaque(false);

        jLDescripcion.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLDescripcion.setText("Descripcion");

        jTADescripcion.setColumns(20);
        jTADescripcion.setRows(5);
        jScrollPane2.setViewportView(jTADescripcion);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLDescripcion)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setOpaque(false);

        jLPeso.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLPeso.setText("Peso");

        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLPeso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addComponent(jBGuardar)
                    .addContainerGap(142, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLPeso)
                    .addComponent(jTPesoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addComponent(jBGuardar)
                    .addContainerGap(42, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        // Obtiene el DNI ingresado en el campo de texto
        String dni = jTDni.getText().trim();
        estado = Estado.CLIENTE;
        // Verifica si el campo está vacío
        if (dni.isEmpty()) {
            // Muestra un mensaje de advertencia si el DNI está vacío
            JOptionPane.showMessageDialog(this, "Debes escribir un DNI");
            return;
        } else {
            // Limpia los campos y establece el estado del formulario a buscar
            limpiarBuscar();
            //estado = Estado.BUSCAR;

            // Llama al método para buscar el cliente por el DNI ingresado
            buscarPorDni();
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        try {
            // Verifica si hay campos vacíos en el formulario
            if (camposVacios()) {
                // Muestra un mensaje de advertencia si hay campos vacíos
                JOptionPane.showMessageDialog(this, "No debe dejar algún dato vacío");
            } else {
                // Llama al método para guardar la visita
                guardarVisita();
                if (estado.equals(Estado.TRATAMIENTO)) {
                    // Limpia los campos después de guardar la visita y marca el estado como GUARDADO
                    limpiarVisita();
                    estado = Estado.GUARDADO;
                }

            }
        } catch (Exception ex) {
            // Muestra cualquier excepción ocurrida durante el proceso de guardar la visita
            Utilidades.mostrarError(ex, this);
        }
    }//GEN-LAST:event_jBGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLDNI;
    private javax.swing.JLabel jLDescripcion;
    private javax.swing.JLabel jLMascota;
    private javax.swing.JLabel jLMascota1;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLPeso;
    private javax.swing.JLabel jLTratamiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTADescripcion;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTDni;
    private javax.swing.JTextField jTImporteTotal;
    private javax.swing.JTable jTMascotas;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTPesoA;
    private javax.swing.JTable jTTratamiento;
    // End of variables declaration//GEN-END:variables

// Método para salir de la aplicación
    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

// Método para verificar campos vacíos en el formulario principal
    private boolean camposVacios() {
        return jTDni.getText().isEmpty();
    }

// Método para limpiar los campos del formulario principal
    private void limpiar() {
        // Limpia los campos de texto y deshabilita la edición de ciertos campos y controles
        Utilidades.limpiarSetText(jTDni, jTApellido, jTNombre);
        jTPesoA.setEditable(false);
        jTADescripcion.setEditable(false);
        jTTratamiento.setEnabled(false);
        jTImporteTotal.setEditable(false);
        estadoGuardado = Estado.NO_GUARDADO;
    }

// Método para limpiar los campos de búsqueda
    private void limpiarBuscar() {
        // Limpia los campos de búsqueda y deshabilita la edición de ciertos campos y controles
        Utilidades.limpiarSetText(jTApellido, jTNombre);
        jTPesoA.setEditable(false);
        jTADescripcion.setEditable(false);
        jTTratamiento.setEnabled(false);
        jTImporteTotal.setEditable(false);
        estadoGuardado = Estado.NADA;
    }

// Método para limpiar los campos relacionados con la visita
    private void limpiarVisita() {
        // Limpia los campos relacionados con la visita y deshabilita la edición de ciertos campos y controles
        Utilidades.limpiarSetText(jTPesoA, jTImporteTotal);
        jTADescripcion.setText("");
        modeloTratamiento.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
        jTTratamiento.setEnabled(false);
        jTImporteTotal.setEditable(false);
        estadoGuardado = Estado.NADA;
    }

// Método para buscar un cliente por DNI
    private void buscarPorDni() {

        // Obtiene el DNI ingresado en el campo de texto
        int dni = 0;
        try {
            dni = Integer.parseInt(jTDni.getText());

            // Intenta buscar un cliente con el DNI ingresado en la base de datos
            ClienteDAO clienteD = ClienteDAO.obtenerInstancia();
            Cliente cliente = clienteD.buscarListaClientexDni(dni);

            if (cliente != null) {
                // Si el cliente existe, muestra sus datos en el formulario
                mostrarClienteEnFormulario(cliente);
                idCliente = cliente.getIdCliente();
                cargarTabla(cliente.getIdCliente());

            } else {
                // Si el cliente no existe, muestra un mensaje y da la opción de crear un nuevo cliente
                estado = Estado.NUEVO;

                int respuesta = JOptionPane.showInternalConfirmDialog(FormularioVisitas.this,
                        "¿No se encontró el DNI, Desea dar de Alta un Nuevo Cliente?",
                        "Advertencia",
                        JOptionPane.YES_NO_OPTION
                );

                // Si el usuario elige "No", permite al usuario mantener la selección actual en la tabla de mascotas
                if (respuesta == JOptionPane.YES_OPTION) {
                    cargarFormularioCliente();
                    return;
                }
            }

        } catch (Exception ex) {
            // Muestra un mensaje de error si ocurre una excepción al intentar buscar el cliente
            JOptionPane.showMessageDialog(this, "Error al buscar el DNI: " + ex.getMessage());
        }
    }

    // Método para guardar la visita y tratamientos realizados
    private void guardarVisita() {
        if (estado.equals(Estado.TRATAMIENTO)) {

            try {
                Factura factura = new Factura();
                VisitaDAO visitaD = VisitaDAO.obtenerInstancia();
                MascotaDAO mascotaD = MascotaDAO.obtenerInstancia();
                List<Tratamiento> listaTratamientos = new ArrayList<>();
                TratamientoRealizadoDAO tratRealizadoD = TratamientoRealizadoDAO.obtenerInstancia();

                Visita visita = new Visita();
                String descripcion = jTADescripcion.getText();
                double importe = Double.parseDouble(jTImporteTotal.getText());
                double pesoA = obtenerPeso(jTPesoA);
                double pesoM = visitaD.avgPesoM(idMascotas);
                LocalDate fechaVisita = LocalDate.now();
                factura.setFecha(fechaVisita);
                // Asignar los valores al objeto Visita
                visita.setPesoActual(pesoA);
                visita.setDetallesSintoma(descripcion);
                visita.setImporteVisita(importe);
                visita.setFechaVisita(fechaVisita);

                int idTrat = visitaD.guardarVisita(visita);

                visita.setIdVisita(idTrat);
                factura.setIdVisita(idTrat);

                //factura.setIdVisita(idTrat);idVisita
                // Iterar sobre los tratamientos seleccionados y guardarlos como tratamientos realizados
                for (Map.Entry<Integer, Tratamiento> entry : tratamientosSeleccionados.entrySet()) {
                    Tratamiento tratamiento = entry.getValue();
                    TratamientoRealizado tratamientoRealizado = new TratamientoRealizado();

                    //tratamientoRealizado.setIdTratamientoRealizado(idTrat);
                    tratamientoRealizado.setIdVisita(visita);
                    tratamientoRealizado.setIdMascota(mascotaD.obtenerMascotaPorId(idMascotas));
                    tratamientoRealizado.setIdTratamiento(tratamiento);
                    tratamientoRealizado.setImporte(tratamiento.getImporte());
                    listaTratamientos.add(tratamiento);
                    // Guardar el tratamiento realizado en la base de datos
                    tratRealizadoD.guardarTratamientoRealizado(tratamientoRealizado);

                }
                // Modificar la mascota fuera del bucle, si es necesario hacerlo solo una vez

                Mascota mascota = mascotaD.obtenerMascotaPorId(idMascotas);
                mascota.setPesoActual(pesoA);
                mascota.setPesoMedia(pesoM);
                //clienteD.obtenerClientexId(mascota.getIdCliente());
                factura.setCliente(mascota.getIdCliente());
                factura.setTotal(importe);
                factura.setMascota(mascota);
                factura.setTratamientos(listaTratamientos);
                factura.setDescuento(importe * .1);
                mascotaD.modificarMascotaPeso(mascota);
                generarPDF(factura);
                // msgCierre();
                JOptionPane.showMessageDialog(this, "Se guardo correctamente, y el remito fue generado");

            } catch (Exception ex) {
                Logger.getLogger(FormularioVisitas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione algun tratamiento");
        }
    }

    // Método para cargar los tratamientos en la tabla de tratamientos
    private void cargarTratamientos() {

        TratamientoDAO cursadas = TratamientoDAO.obtenerInstancia();
        Collection<Tratamiento> listarTratamientos = new ArrayList<>(); // Inicialización predeterminada

        listarTratamientos = cursadas.obtenerTratamientos();

        for (Tratamiento tipo : listarTratamientos) {
            if (tipo.isEstado()) {
                modeloTratamiento.addRow(new Object[]{tipo.getIdTratamiento(), tipo.getTipo(), tipo.getDescripcion(), tipo.getImporte()});

            }

        }
    }

    private void mostrarClienteEnFormulario(Cliente cliente) {

        jTApellido.setText(cliente.getApellido());
        jTNombre.setText(cliente.getNombre());

    }

    private void cargarTabla(int idCliente) throws Exception {

        MascotaDAO cursadas = MascotaDAO.obtenerInstancia();
        Collection<Mascota> listaMascota = new ArrayList<>(); // Inicialización predeterminada

        listaMascota = cursadas.listarMascotasxIdCliente(idCliente);
        modeloMascota.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
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

    private void cargarFormularioCliente() {
        try {
            FormularioCliente cargarCliente = new FormularioCliente(desktopPane);
            //cargarCliente.setMascotaFormListener(this); // Donde `this` es el objeto que implementa el interfaz
            cargarCliente.setSize(600, 500);
            cargarCliente.pack();
            int x = (desktopPane.getWidth() - cargarCliente.getWidth()) / 2;
            int y = (desktopPane.getHeight() - cargarCliente.getHeight()) / 2;
            cargarCliente.setBounds(x, y, cargarCliente.getWidth(), cargarCliente.getHeight());
            desktopPane.add(cargarCliente);
            cargarCliente.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FormularioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private double obtenerPeso(JTextField textField) {
        try {

            String pesoString = textField.getText().trim();
            return pesoString.isEmpty() ? 0.0 : Double.parseDouble(pesoString);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public void generarPDF(Factura factura) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14); // Fuente para el encabezado
            float yPosition = 750; // Posición inicial del encabezado

            // Agrega el encabezado al PDF
            contentStream.beginText();
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("REMITO");
            contentStream.endText();

            // Configura la posición inicial del texto en la página para el contenido de la factura
            yPosition -= 30; // Espacio entre el encabezado y el contenido
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12); // Fuente para el contenido

            String facturaContent = factura.generarFactura();

            // Divide el contenido de la factura en líneas
            String[] lines = facturaContent.split("\n");

            // Agrega el contenido de la factura al PDF
            for (String line : lines) {
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText(line);
                contentStream.endText();
                yPosition -= 15; // Espacio entre líneas
            }

            contentStream.close();
            //String directorioApp = System.getProperty("user.dir");

            document.save("remito" + String.valueOf(factura.getIdVisita()) + ".pdf");
            document.close();

            System.out.println("Remito generado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

////    public void MostrarPDF() {
////        try {
////            // Ruta del archivo PDF a abrir
////           File file = new File("remito" + String.valueOf(factura.getIdVisita()) + ".pdf");
////
////            // Crea un objeto PDDocument para el archivo PDF
////            PDDocument document = PDDocument.load(file);
////
////            // Crea un objeto PDFRenderer para renderizar el PDF como imágenes
////            PDFRenderer pdfRenderer = new PDFRenderer(document);
////
////            // Número de página que deseas mostrar
////            int pageNumber = 0;
////
////            // Ajustar la resolución (DPI) para controlar el tamaño de la imagen
////            int dpi = 80; // Puedes ajustar este valor según tus necesidades
////
////            // Renderizar la página del PDF como una imagen
////            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(pageNumber, dpi, ImageType.RGB);
//////
//////              // Redimensionar la imagen a un tamaño específico (opcional)
//////            int width = bufferedImage.getWidth();
//////            int height = bufferedImage.getHeight();
//////            int newWidth = 400; // Ancho deseado
//////            int newHeight = (int) (height * ((double) newWidth / width)); // Calcular la altura proporcional
//////            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//////            resizedImage.getGraphics().drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
//////          
////            // Mostrar la imagen en un cuadro de diálogo usando JOptionPane
////            ImageIcon icon = new ImageIcon(bufferedImage);
////            JOptionPane.showMessageDialog(null, icon);
////
////            // Cierra el documento después de usarlo
////            document.close();
////
////            System.out.println("PDF abierto y mostrado como imágenes.");
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
    public void msgCierre() {
        // Crea un hilo que se encargará de mostrar el JOptionPane
        Thread showMessageThread = new Thread(() -> {
            // Muestra el JOptionPane
            JOptionPane.showMessageDialog(this, "Se guardo correctamente, y pdf generado");
        });

        // Inicia el hilo
        showMessageThread.start();

        // Espera durante 2 segundos antes de interrumpir el hilo
        try {
            Thread.sleep(2000); // Espera 2 segundos (2000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrumpe el hilo después de 2 segundos para cerrar el JOptionPane
        showMessageThread.interrupt();
    }
}
