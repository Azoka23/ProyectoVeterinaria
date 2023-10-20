/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria.Vistas;

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
        initComponents();

        cargarComboHistoriaL();

        setTitle("Historial - Listar todas las visitas de una misma mascota");
        inicializarCabezeras();
        cargarComboHistoriaL();

        configurarListeners();
        seleccionarCliente();
        seleccionarMascotas();
        seleccionarTratamiento();
        cargarTablaClientes();
        ocultarColumna(jTClientes, "Id");
        estado = Estado.CLIENTE_MASCOTA;
        // para la tabla jTMascotas
        jTClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
        packAll(jTClientes); // Ajusta el ancho de las columnas

        // para la tabla jTMascotas
        jTMascotas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
        packAll(jTMascotas); // Ajusta el ancho de las columnas
////
////        // Guarda el nombre de la columna que deseas ocultar
////        String nombreIdClienteOculto = "Id";
////        indiceColumnaOcultaCliente = jTClientes.getColumn(nombreIdClienteOculto).getModelIndex();
////
////        // Oculta la columna por su nombre
////        jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setWidth(0);
////        jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setMinWidth(0);
////        jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setMaxWidth(0);
////
////        // Listener para la tabla de Clientes
////        jTClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
////            @Override
////            public void valueChanged(ListSelectionEvent e) {
////                // Verifica si la selección está cambiada y no está en modo de ajuste
////                if (!e.getValueIsAdjusting()) {
////                    // Obtiene la fila seleccionada
////                    int selectedRow = jTClientes.getSelectedRow();
////                    // Verifica si hay una fila seleccionada
////                    if (selectedRow != -1) {
////                        // Obtén el valor de todas las columnas en la fila seleccionada
////
////                        // idCliente = (Integer) jTClientes.getValueAt(selectedRow, 0);
////                        idCliente = (Integer) jTClientes.getValueAt(selectedRow, indiceColumnaOcultaCliente);
//////                        apellido = (String) jTClientes.getValueAt(selectedRow, 1);
////                        // para la tabla jTClientes
////                        jTClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
////                        packAll(jTClientes); // Ajusta el ancho de las columnas
////                        //JOptionPane.showMessageDialog(null, idCliente);
////                        cargarTablaMascotas(idCliente);
////
////                        // Guarda el nombre de la columna que deseas ocultar
////                        String nombreIdMascotaOcultoMascota = "Id";
////                        indiceColumnaOcultaMacota = jTMascotas.getColumn(nombreIdMascotaOcultoMascota).getModelIndex();
////
////                        // Oculta la columna por su nombre
////                        jTMascotas.getColumnModel().getColumn(indiceColumnaOcultaMacota).setWidth(0);
////                        jTMascotas.getColumnModel().getColumn(indiceColumnaOcultaMacota).setMinWidth(0);
////                        jTMascotas.getColumnModel().getColumn(indiceColumnaOcultaMacota).setMaxWidth(0);
////
////                    }
////                }
////            }
////        });
////        // Listener para la tabla de mascotas
////        jTMascotas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
////            @Override
////            public void valueChanged(ListSelectionEvent e) {
////                // Verifica si la selección está cambiada y no está en modo de ajuste
////                if (!e.getValueIsAdjusting()) {
////                    // Obtiene la fila seleccionada
////                    int selectedRow = jTMascotas.getSelectedRow();
////                    // Verifica si hay una fila seleccionada
////                    if (selectedRow != -1) {
////                        // Obtén el valor de todas las columnas en la fila seleccionada
////                        //idMascotas = (String) jTMascotas.getValueAt(selectedRow, 0);
////                        idMascotas = (Integer) jTMascotas.getValueAt(selectedRow, indiceColumnaOcultaMacota);
////                        alias = (String) jTMascotas.getValueAt(selectedRow, 1);
////                        jLHistoriales.setText("Historial - Mascota con Alias: " + alias);
////                        // Limpia los campos de visita y carga los tratamientos
////                        //limpiarHistorial();
////                        tablaHistorialVisita(idMascotas);
//////                        // Habilita la edición y muestra información de la mascota seleccionada
//////                        jTTratamiento.setEnabled(true);
//////                        jTPesoA.setEditable(true);
//////                        jLMascota1.setText("Mascota: " + alias);
//////                        jTADescripcion.setEditable(true);
//////                        estadoGuardado = Estado.NO_GUARDADO;
////                    }
////                }
////            }
////        });
////        // Listener para la tabla de tratamiento
////        jTTTratamientos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
////            @Override
////            public void valueChanged(ListSelectionEvent e) {
////                // Verifica si la selección está cambiada y no está en modo de ajuste
////                if (!e.getValueIsAdjusting()) {
////                    // Obtiene la fila seleccionada
////                    int selectedRow = jTTTratamientos.getSelectedRow();
////                    // Verifica si hay una fila seleccionada
////                    if (selectedRow != -1) {
////                        try {
////                            // Obtén el valor de todas las columnas en la fila seleccionada
////                            idTratamiento = (Integer) jTTTratamientos.getValueAt(selectedRow, indiceColumnaOcultaTratamiento);
////                            //alias = (String) jTMascotas.getValueAt(selectedRow, 1);
////                            // Limpia los campos de visita y carga los tratamientos
////                            //limpiarHistorial();
////                            cargarMascotaXTipo(idTratamiento);
//////                        // Habilita la edición y muestra información de la mascota seleccionada
//////                        jTTratamiento.setEnabled(true);
//////                        jTPesoA.setEditable(true);
//////                        jLMascota1.setText("Mascota: " + alias);
//////                        jTADescripcion.setEditable(true);
//////                        estadoGuardado = Estado.NO_GUARDADO;
////                        } catch (Exception ex) {
////                            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
////                        }
////                    }
////                }
////            }
////        });
////        // Agrega el ActionListener al JComboBox
////        jCBListar.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                // Obtén el índice del elemento seleccionado
////                int selectedIndex = jCBListar.getSelectedIndex();
////
////                // Actualiza la cabecera de la tabla según el elemento seleccionado
////                if (selectedIndex == 0) {
////                    jLHistoriales.setText("Historial");
////                    tratamientoModel.setRowCount(0);
////                    armarCabeceraCliente();
////                    cargarTablaClientes();
////
////                    // Guarda el nombre de la columna que deseas ocultar
////                    String nombreIdClienteOculto = "Id";
////                    indiceColumnaOcultaCliente = jTClientes.getColumn(nombreIdClienteOculto).getModelIndex();
////
////                    // Oculta la columna por su nombre
////                    jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setWidth(0);
////                    jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setMinWidth(0);
////                    jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setMaxWidth(0);
////
////                    // Si se selecciona la primera opción en el JComboBox
////                    armarCabeceraHistorialVisita();
////                    //jLSlecciones.setText("Listar todas las visitas de una misma mascota");
////                    setTitle("Historial - Listar todas las visitas de una misma mascota");
//////                    // para la tabla jTListas
//////                    jTListas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
//////                    packAll(jTListas); // Ajusta el ancho de las columnas
////
////                } else if (selectedIndex == 1) {
////                    // Si se selecciona la segunda opción en el JComboBox
////                    armarCabeceraHistorialTratamiento();
////                    armarCabeceraTipo();
////
////                    tablaTipoTratamiento();
////
////                    clientesModel.setRowCount(0);
////                    mascotaModel.setRowCount(0);
////                    // jLSlecciones.setText("Listar todas las mascotas que hacen el mismo tratamiento");
////                    setTitle("Historial - Listar todas las mascotas que hacen el mismo tratamiento");
////
////                    // Guarda el nombre de la columna que deseas ocultar
////                    String nombreIdTratamientoOculto = "Id";
////                    indiceColumnaOcultaTratamiento = jTTTratamientos.getColumn(nombreIdTratamientoOculto).getModelIndex();
////
////                    // Oculta la columna por su nombre
////                    jTTTratamientos.getColumnModel().getColumn(indiceColumnaOcultaTratamiento).setWidth(0);
////                    jTTTratamientos.getColumnModel().getColumn(indiceColumnaOcultaTratamiento).setMinWidth(0);
////                    jTTTratamientos.getColumnModel().getColumn(indiceColumnaOcultaTratamiento).setMaxWidth(0);
////                    jLHistoriales.setText("Historial - Mascotas");
//////                    // para la tabla jTListas
//////                    jTListas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
//////                    packAll(jTListas); // Ajusta el ancho de las columnas
////                } else if (selectedIndex == 2) {
////                    jLHistoriales.setText("Historial");
////                    // Si se selecciona la tercera opción en el JComboBox
////                    armarCabeceraHistorialCliente();
////                    setTitle("Historial - Listar todas las mascotas por cliente");
////                    tratamientoModel.setRowCount(0);
////                    armarCabeceraCliente();
////                    cargarTablaClientes();
////
////                    // Guarda el nombre de la columna que deseas ocultar
////                    String nombreIdClienteOculto = "Id";
////                    indiceColumnaOcultaCliente = jTClientes.getColumn(nombreIdClienteOculto).getModelIndex();
////
////                    // Oculta la columna por su nombre
////                    jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setWidth(0);
////                    jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setMinWidth(0);
////                    jTClientes.getColumnModel().getColumn(indiceColumnaOcultaCliente).setMaxWidth(0);
////
//////                    // para la tabla jTListas
//////                    jTListas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
//////                    packAll(jTListas); // Ajusta el ancho de las columnas
////                } else if (selectedIndex == 3) {
////                    jLHistoriales.setText("Historial");
////                    // Si se selecciona la cuarta opción en el JComboBox
////                    armarCabeceraHistorialEstado();
////                    setTitle("Historial - Listar los tratamientos de la mascota en una visita");
//////                    // para la tabla jTListas
//////                    jTListas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
//////                    packAll(jTListas); // Ajusta el ancho de las columnas
////
////                } else if (selectedIndex == 4) {
////                    jLHistoriales.setText("Historial");
////                    setTitle("Historial - Listar los tratamientos de la mascota en una visita");
////                    // Si se selecciona la quinta opción en el JComboBox
////                    armarCabeceraHistorialTratamientoXMascota();
//////                    // para la tabla jTListas
//////                    jTListas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático para poder ajustar manualmente
//////                    packAll(jTListas); // Ajusta el ancho de las columnas
////
////                }
////
////                // Llama a tu método aquí
////                //tuMetodo();
////            }
////        });
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
        jLMascotas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMascotas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTTTratamientos = new javax.swing.JTable();
        jBbuscar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jLHistoriales = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jCBListar = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTHistoriales = new javax.swing.JTable();
        jLListado = new javax.swing.JLabel();
        jLTratamientos = new javax.swing.JLabel();
        jLClientes = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTClientes = new javax.swing.JTable();

        setClosable(true);
        setTitle("Historial");
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));

        jLMascotas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
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

        jBbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Save_37110.png"))); // NOI18N

        jBSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/home256_24783.png"))); // NOI18N
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jLHistoriales.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLHistoriales.setText("Historiales");

        jCBListar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jCBListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBListarActionPerformed(evt);
            }
        });

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

        jLListado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLListado.setText("Seleccionar Listados");

        jLTratamientos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLTratamientos.setText("Tipos de Tratamientos");

        jLClientes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLHistoriales)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBSalir)
                                    .addComponent(jBbuscar)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLListado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCBListar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLClientes))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLMascotas)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLTratamientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLListado)
                    .addComponent(jCBListar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLClientes)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLMascotas)
                        .addComponent(jLTratamientos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLHistoriales)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBbuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(141, 141, 141))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        salirAplicacion();
        // TODO add your handling code here:
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jCBListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBListarActionPerformed


    }//GEN-LAST:event_jCBListarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTHistoriales;
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jBbuscar;
    private javax.swing.JComboBox<ConsultasLista> jCBListar;
    private javax.swing.JLabel jLClientes;
    private javax.swing.JLabel jLHistoriales;
    private javax.swing.JLabel jLListado;
    private javax.swing.JLabel jLMascotas;
    private javax.swing.JLabel jLTratamientos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTClientes;
    private javax.swing.JTable jTMascotas;
    private javax.swing.JTable jTTTratamientos;
    // End of variables declaration//GEN-END:variables

    public void cargarComboHistoriaL() {
        try {
            jCBListar.removeAllItems(); // Limpiar el JComboBox
            ConsultaDAO consultas = ConsultaDAO.obtenerInstancia();
            Collection<ConsultasLista> listasDeConsultas = new ArrayList<>();
            listasDeConsultas = consultas.listarConsultas();

            for (ConsultasLista tipo : listasDeConsultas) {
                jCBListar.addItem(tipo);
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void armarCabeceraHistorialVisita() {// listar todas las visitas de una misma mascota
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Fecha");
        historialModel.addColumn("Diagnostico");
        historialModel.addColumn("Importe");
        historialModel.addColumn("Alias");
        JTHistoriales.setModel(historialModel);
    }

    private void armarCabeceraHistorialTratamiento() {//listar todas las mascotas que hacen el mismo tratamiento
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        //historialModel.addColumn("Id");
        historialModel.addColumn("Alias");
        historialModel.addColumn("Peso");
        JTHistoriales.setModel(historialModel);
    }

    private void armarCabeceraHistorialMascotaActivo() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Alias");
        historialModel.addColumn("Peso Actual");
        historialModel.addColumn("Apellido");
        historialModel.addColumn("Nombre");

        JTHistoriales.setModel(historialModel);
    }

    private void armarCabeceraHistorialMascotaTratamientos() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Fecha Visita");
        historialModel.addColumn("Tipo Tratamiento");
        historialModel.addColumn("Descripcion");

        JTHistoriales.setModel(historialModel);
    }

    private void armarCabeceraHistorialTratamientoXEstado() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Tipo");
        historialModel.addColumn("Descripcion");
        historialModel.addColumn("Importe");
        JTHistoriales.setModel(historialModel);
    }

    private void armarCabeceraHistorialClienteXEstado() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Apellido");
        historialModel.addColumn("Nombre");
        historialModel.addColumn("DNI");
        historialModel.addColumn("Telefono");
        historialModel.addColumn("E-mail");
        JTHistoriales.setModel(historialModel);
    }

    private void armarCabeceraClienteConSinMascota() {
        historialModel.setColumnCount(0); // Elimina todas las columnas existentes
        historialModel.addColumn("Apellido");
        historialModel.addColumn("Nombre");
        historialModel.addColumn("DNI");
        JTHistoriales.setModel(historialModel);
    }

    private void armarCabeceraMascota() {
        mascotaModel.setColumnCount(0); // Elimina todas las columnas existentes
        mascotaModel.addColumn("Id");
        mascotaModel.addColumn("Alias");

        mascotaModel.addColumn("Peso");

        jTMascotas.setModel(mascotaModel);
    }

    private void armarCabeceraCliente() {

        clientesModel.setColumnCount(0); // Elimina todas las columnas existentes
        clientesModel.addColumn("Id");
        clientesModel.addColumn("DNI");
        clientesModel.addColumn("Apellido");

        clientesModel.addColumn("Nombre");

        jTClientes.setModel(clientesModel);
    }

    private void armarCabeceraTipo() {
        tratamientoModel.setColumnCount(0); // Elimina todas las columnas existentes
        tratamientoModel.addColumn("Id");
        tratamientoModel.addColumn("Tipo");

        jTTTratamientos.setModel(tratamientoModel);
    }

    private void salirAplicacion() {
        if (Utilidades.confirmarSalida(this)) {
            dispose();
        }
    }

    private void cargarTablaMascotas(int documento) {

        try {

            MascotaDAO cursadas = MascotaDAO.obtenerInstancia();
            Collection<Mascota> listaMascota = new ArrayList<>(); // Inicialización predeterminada

            listaMascota = cursadas.listarMascotasxIdCliente(documento);
            mascotaModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
            for (Mascota tipo : listaMascota) {
                if (tipo.isEstado()) {
                    // mascotaModel.addRow(new Object[]{tipo.getIdMascota(), tipo.getAlias(), tipo.getPesoActual()});
                    mascotaModel.addRow(new Object[]{tipo.getIdMascota(), tipo.getAlias(), tipo.getPesoActual()});

                }

            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cargarTablaMascotasActivoInactivo(boolean activo) {
        try {
            MascotaDAO mascotaDAO = MascotaDAO.obtenerInstancia();
            Collection<Mascota> listaMascotas = mascotaDAO.listarMascotasPorEstado(activo);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            for (Mascota mascota : listaMascotas) {
                historialModel.addRow(new Object[]{mascota.getAlias(), mascota.getPesoActual(),
                    mascota.getIdCliente().getApellido(), mascota.getIdCliente().getNombre()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarTablaTratamientoActivoInactivo(boolean activo) {
        try {
            TratamientoDAO tratamientoD = TratamientoDAO.obtenerInstancia();
            Collection<Tratamiento> listaTratamiento = tratamientoD.listarTratamientoPorEstado(activo);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            for (Tratamiento tipo : listaTratamiento) {
                historialModel.addRow(new Object[]{tipo.getTipo(), tipo.getDescripcion(), tipo.getImporte()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarTablaClienteActivoInactivo(boolean activo) {
        try {
            ClienteDAO clienteD = ClienteDAO.obtenerInstancia();
            Collection<Cliente> listaCliente = clienteD.listarClientePorEstado(activo);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            for (Cliente tipo : listaCliente) {
                historialModel.addRow(new Object[]{tipo.getApellido(), tipo.getNombre(), tipo.getDni(), tipo.getTelefono(), tipo.getEmail()});
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarTablaClientes() {

        try {
            ClienteDAO cursadas = ClienteDAO.obtenerInstancia();
            Collection<Cliente> listaCliente = new ArrayList<>(); // Inicialización predeterminada

            listaCliente = cursadas.obtenerClientes();
            clientesModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
            for (Cliente tipo : listaCliente) {
                if (tipo.isEstado()) {
                    // clientesModel.addRow(new Object[]{tipo.getIdCliente(), tipo.getDni(), tipo.getApellido(), tipo.getNombre()});
                    clientesModel.addRow(new Object[]{tipo.getIdCliente(), tipo.getDni(), tipo.getApellido(), tipo.getNombre()});

                }

            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cargarTablaClientesConSinMascota(boolean conMascota) {

        try {
            ClienteDAO cursadas = ClienteDAO.obtenerInstancia();
            Collection<Cliente> listaCliente = new ArrayList<>(); // Inicialización predeterminada
            if (conMascota) {
                listaCliente = cursadas.obtenerClientesConMascota();
            } else {
                listaCliente = cursadas.obtenerClientesSinMascota();
            }

            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
            for (Cliente tipo : listaCliente) {
                if (tipo.isEstado()) {
                    // clientesModel.addRow(new Object[]{tipo.getIdCliente(), tipo.getDni(), tipo.getApellido(), tipo.getNombre()});
                    historialModel.addRow(new Object[]{tipo.getApellido(), tipo.getNombre(), tipo.getDni()});

                }

            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cargarMascotaXTipo(int idTratamiento) throws Exception {

        MascotaDAO cursadas = MascotaDAO.obtenerInstancia();
        Collection<Mascota> listaMascota = new ArrayList<>(); // Inicialización predeterminada

        listaMascota = cursadas.listarMascotasXTipoTratamiento(idTratamiento);
        historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
        for (Mascota tipo : listaMascota) {
            if (tipo.isEstado()) {
                // historialModel.addRow(new Object[]{tipo.getIdMascota(), tipo.getAlias(), tipo.getPesoActual()});
                historialModel.addRow(new Object[]{tipo.getAlias(), tipo.getPesoActual()});

            }

        }

    }

    private void tablaHistorialVisita(int idMascotas) {
        try {
            VisitaDAO cursadas = VisitaDAO.obtenerInstancia();
            Collection<Visita> listaVistas = new ArrayList<>(); // Inicialización predeterminada

            listaVistas = cursadas.listarMascotasPorVisitas(idMascotas);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
            for (Visita tipo : listaVistas) {
                //if (tipo.isEstado()) {
                historialModel.addRow(new Object[]{tipo.getFechaVisita(), tipo.getDetallesSintoma(), tipo.getImporteVisita()});

                // }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tablaHistorialMascotaTratamientos(int idMascotas) {
        try {
            TratamientoRealizadoDAO cursadas = TratamientoRealizadoDAO.obtenerInstancia();
            Collection<TratamientoRealizado> listaMascotaTratamientos = new ArrayList<>(); // Inicialización predeterminada

            listaMascotaTratamientos = cursadas.obtenerTratamientosDeMascota(idMascotas);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
            for (TratamientoRealizado tipo : listaMascotaTratamientos) {
                //if (tipo.isEstado()) {
                historialModel.addRow(new Object[]{tipo.getIdVisita().getFechaVisita(), tipo.getIdTratamiento().getTipo(), tipo.getIdTratamiento().getDescripcion()});
               // JOptionPane.showMessageDialog(null, tipo);
                // }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tablaHistorialVisitaCliente(int idCliente) {
        try {
            VisitaDAO cursadas = VisitaDAO.obtenerInstancia();
            Map<Visita, String> visitasConAlias = new LinkedHashMap<>();
            visitasConAlias = cursadas.obtenerVisitasDeCliente(idCliente);
            historialModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos

            if (visitasConAlias.isEmpty()) {
                // El mapa está vacío, no hay datos para mostrar en la tabla
                // Puedes mostrar un mensaje o realizar alguna acción adecuada al caso vacío
                // Por ejemplo, mostrar un mensaje en un JLabel o JTextArea que diga "No hay visitas para mostrar".
                JOptionPane.showMessageDialog(this, "El Cliente todavia no realizo visitas!");
            } else {
                // El mapa contiene datos, itera y agrega filas a la tabla
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

    private void tablaTipoTratamiento() {
        try {

            TratamientoDAO cursadas = TratamientoDAO.obtenerInstancia();
            Collection<Tratamiento> listaTratamiento = new ArrayList<>(); // Inicialización predeterminada

            listaTratamiento = cursadas.obtenerTratamientos();
            tratamientoModel.setRowCount(0); // Limpia la tabla de tratamientos antes de agregar nuevos datos
            for (Tratamiento tipo : listaTratamiento) {
                if (tipo.isEstado()) {
                    //tratamientoModel.addRow(new Object[]{tipo.getIdTratamiento(), tipo.getTipo()});
                    tratamientoModel.addRow(new Object[]{tipo.getIdTratamiento(), tipo.getTipo()});

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Método para ajustar el ancho de las columnas automáticamente
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

    private void inicializarCabezeras() {
        armarCabeceraMascota();
        armarCabeceraTipo();
        armarCabeceraCliente();
        armarCabeceraHistorialVisita();
    }

    private void ocultarColumna(JTable table, String nombreColumna) {
        int indiceColumnaOculta = table.getColumn(nombreColumna).getModelIndex();
        TableColumn columna = table.getColumnModel().getColumn(indiceColumnaOculta);
        columna.setWidth(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
    }

    private void configurarListeners() {
        // Listener para el JComboBox
        jCBListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = jCBListar.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        setTitle("Historial - Listar todas las visitas de una misma mascota.");
                        ////                    jLHistoriales.setText("Historial");
                        jLHistoriales.setText("Historial - Listar todas las visitas de una misma mascota");
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraCliente();
                        cargarTablaClientes();
                        armarCabeceraHistorialVisita();
                        ocultarColumna(jTClientes, "Id");
                        //seleccionarCliente();
                        estado = Estado.CLIENTE_MASCOTA;
                        break;
                    case 1:
                        setTitle("Historial - Listar todas las visitas de un mismo Cliente.");
                        ////                    jLHistoriales.setText("Historial");
                        jLHistoriales.setText("Historial - Listar todas las visitas de un mismo Cliente.");
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraCliente();
                        cargarTablaClientes();
                        armarCabeceraHistorialVisita();
                        ocultarColumna(jTClientes, "Id");
                        //seleccionarCliente();
                        estado = Estado.CLIENTE_VISITA;
                        break;
                    case 11:

                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraHistorialTratamiento();
                        armarCabeceraTipo();
                        tablaTipoTratamiento();
                        estado = Estado.NADA;
                        setTitle("Historial - Listar todas las mascotas que hacen el mismo tratamiento");
                        //seleccionarTratamiento();
                        ocultarColumna(jTTTratamientos, "Id");
                        jLHistoriales.setText("Historial - Mascotas");
                        break;
                    case 2:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        //jLHistoriales.setText("Historial");
                        // Si se selecciona la cuarta opción en el JComboBox
                        //armarCabeceraHistorialEstado();
//                        setTitle("Historial - Listar los tratamientos de la mascota en una visita");
                        jLHistoriales.setText("Historial - Listar Clientes que tienen Mascotas");
////                    // Si se selecciona la tercera opción en el JComboBox
                        armarCabeceraClienteConSinMascota();
                        setTitle("Historial - Listar Clientes que tienen Mascotas");
                        cargarTablaClientesConSinMascota(true);
                        estado = Estado.NADA;
                        //cargarTablaClientes();
                        //ocultarColumna(JTHistoriales, "Id");
                        ;
                        break;
                    case 3:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        //jLHistoriales.setText("Historial");
                        // Si se selecciona la cuarta opción en el JComboBox
                        //armarCabeceraHistorialEstado();
//                        setTitle("Historial - Listar los tratamientos de la mascota en una visita");
                        jLHistoriales.setText("Historial - Listar Clientes que no tienen Mascotas");
////                    // Si se selecciona la tercera opción en el JComboBox
                        armarCabeceraClienteConSinMascota();
                        setTitle("Historial - Listar Clientes que no tienen Mascotas");
                        cargarTablaClientesConSinMascota(false);
                        estado = Estado.NADA;
                        //cargarTablaClientes();
                        //ocultarColumna(JTHistoriales, "Id");
                        // ...
                        break;
                    case 5:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraHistorialMascotaActivo();
                        cargarTablaMascotasActivoInactivo(true);
                        jLHistoriales.setText("Historial - Activas");
                        setTitle("Historial - Listar Mascotas Activos");
                        estado = Estado.NADA;
                        break;
                    case 4:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraHistorialMascotaActivo();
                        cargarTablaMascotasActivoInactivo(false);
                        jLHistoriales.setText("Historial - Inactivas");
                        setTitle("Historial - Listar Mascotas Inactivas");
                        estado = Estado.NADA;
                        break;
                    case 6:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraHistorialTratamientoXEstado();
                        cargarTablaTratamientoActivoInactivo(true);
                        jLHistoriales.setText("Historial - Activas");
                        setTitle("Historial - Listar Tratamientos Activos");
                        estado = Estado.NADA;
                        break;
                    case 7:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraHistorialTratamientoXEstado();
                        cargarTablaTratamientoActivoInactivo(false);
                        jLHistoriales.setText("Historial - Inactivas");
                        setTitle("Historial - Listar Tratamientos Inactivos");
                        estado = Estado.NADA;
                        break;
                    case 8:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraHistorialClienteXEstado();
                        cargarTablaClienteActivoInactivo(true);
                        jLHistoriales.setText("Historial - Activos");
                        setTitle("Historial - Listar Clientes Activos");
                        estado = Estado.NADA;
                        break;
                    case 9:
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraHistorialClienteXEstado();
                        cargarTablaClienteActivoInactivo(false);
                        jLHistoriales.setText("Historial - Inactivos");
                        setTitle("Historial - Listar Clientes Inactivos");
                        estado = Estado.NADA;
                        break;
                    case 10:
                        setTitle("Historial - Listar los Tratamientos Realizados por Mascota.");
                        ////                    jLHistoriales.setText("Historial");
                        jLHistoriales.setText("Historial - Listar los Tratamientos Realizados por Mascota");
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        tratamientoModel.setRowCount(0);
                        armarCabeceraCliente();
                        cargarTablaClientes();
                        armarCabeceraHistorialMascotaTratamientos();
                        ocultarColumna(jTClientes, "Id");
                        //seleccionarCliente();
                        estado = Estado.MASCOTA_TRATAMIENTO;
                        break;
                    default:
                        setTitle("Historial");
                        jLHistoriales.setText("Historial");
                        tratamientoModel.setRowCount(0);
                        clientesModel.setRowCount(0);
                        mascotaModel.setRowCount(0);
                        historialModel.setRowCount(0);
                        estado = Estado.NADA;
                        break;
                }
            }
        });

    }

    private void seleccionarCliente() {

        jTClientes.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = jTClientes.getSelectedRow();
            if (selectedRow != -1) {
                idCliente = (Integer) jTClientes.getValueAt(selectedRow, indiceColumnaOcultaCliente);
                jTClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                packAll(jTClientes);
                if (estado.equals(Estado.CLIENTE_MASCOTA) || estado.equals(Estado.MASCOTA_TRATAMIENTO)) {
                    cargarTablaMascotas(idCliente);
                    ocultarColumna(jTMascotas, "Id");
                    jLHistoriales.setText("Historial - Mascotas");
                } else if (estado.equals(Estado.CLIENTE_VISITA)) {
                    tablaHistorialVisitaCliente(idCliente);
                    jLHistoriales.setText("Historial - Mascotas");
                }

            }
        });

        // Configurar otras operaciones relacionadas con listar visitas de mascota aquí
        // ...
    }

    private void seleccionarMascotas() {

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
                        idMascotas = (Integer) jTMascotas.getValueAt(selectedRow, indiceColumnaOcultaMacota);
                        alias = (String) jTMascotas.getValueAt(selectedRow, 1);

                        if (estado.equals(Estado.CLIENTE_MASCOTA)) {
                            jLHistoriales.setText("Historial - Mascota con Alias: " + alias);
                            //JOptionPane.showMessageDialog(null, "estoy en la opcion 0");
                            tablaHistorialVisita(idMascotas);
                        } else if (estado.equals(Estado.MASCOTA_TRATAMIENTO)) {
                            jLHistoriales.setText("Historial - Mascota con Alias: " + alias);
                            // JOptionPane.showMessageDialog(null, "estoy en la opcion 11");
                            tablaHistorialMascotaTratamientos(idMascotas);
                        }
                    }
                }
            }
        });
    }

    private void seleccionarTratamiento() {
        // Listener para la tabla de tratamiento
        jTTTratamientos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verifica si la selección está cambiada y no está en modo de ajuste
                if (!e.getValueIsAdjusting()) {
                    // Obtiene la fila seleccionada
                    int selectedRow = jTTTratamientos.getSelectedRow();
                    // Verifica si hay una fila seleccionada
                    if (selectedRow != -1) {
                        try {
                            // Obtén el valor de todas las columnas en la fila seleccionada
                            idTratamiento = (Integer) jTTTratamientos.getValueAt(selectedRow, indiceColumnaOcultaTratamiento);
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
