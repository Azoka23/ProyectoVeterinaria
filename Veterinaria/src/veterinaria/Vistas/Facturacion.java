package veterinaria.Vistas;

// Importaciones necesarias
import veterinaria.Vistas.CustomPanel;
import veterinaria.Vistas.CustomTableCellRenderer;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import veterinaria.Utilidades;

/**
 * Clase que representa la interfaz de usuario para la facturación.
 */
public class Facturacion extends javax.swing.JInternalFrame {

    private boolean actualizandoTotales = false; // Bandera para evitar recursión infinita al actualizar totales
    private double des = 0.0; // Variable para almacenar el descuento

    // Modelo de tabla por defecto con personalización para hacer las primeras 5 filas editables
    private DefaultTableModel tratamientoModel = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            return fila < 5;
        }
    };

    /**
     * Constructor de la clase Facturacion.
     */
    public Facturacion() {
        CustomPanel customPanel = new CustomPanel(); // Crea un panel personalizado
        this.setContentPane(customPanel); // Establece el panel personalizado como el contenido del marco interno
        initComponents(); // Inicializa los componentes de la interfaz

        armarCabeceraTipo(); // Configura la cabecera de la tabla
        armarTabla(); // Configura la tabla de tratamientos

        jTTTratamientos.setModel(tratamientoModel); // Establece el modelo de tabla en la tabla visual

        mostrarFechaHoraActual(); // Muestra la fecha y hora actuales en la interfaz

        // Agrega un TableModelListener para manejar los cambios en la tabla
        tratamientoModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Maneja los cambios en la tabla aquí
                // Por ejemplo, puedes actualizar los totales llamando a un método como actualizarTotales()
                actualizarTotales();
                //tratamientoModel.fireTableDataChanged(); // Llama a este método después de actualizar los datos de la tabla

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTCliente1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTMascota = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTTTratamientos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jBImprimir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTCliente = new javax.swing.JTextField();
        jTFecha1 = new javax.swing.JTextField();
        jTFecha = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTDiaYHora = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel1.setText("PETDOCTOR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setOpaque(false);

        jTCliente1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTCliente1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setOpaque(false);

        jTMascota.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTMascota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTMascotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTMascota, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        jTTTratamientos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        jScrollPane1.setViewportView(jTTTratamientos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setOpaque(false);

        jBImprimir.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jBImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/Print_icon-icons.com_73705.png"))); // NOI18N
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/vcsupdaterequired_93493.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/diagram-31_24486.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/cash_512_icon-icons.com_75969.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(67, 67, 67))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBImprimir))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel2.setText("Calle de los Animales Felices, 123 Colonia Mascotaville - Ciudad Petrópolis, PV");

        jLabel3.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        jLabel3.setText("PV Código Postal: 12345 - info@petdoctorveterinaria.com");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTCliente.setEditable(false);
        jTCliente.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jTCliente.setText("Cliente:");
        jTCliente.setBorder(null);

        jTFecha1.setEditable(false);
        jTFecha1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jTFecha1.setText("Mascota");
        jTFecha1.setBorder(null);
        jTFecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFecha1ActionPerformed(evt);
            }
        });

        jTFecha.setEditable(false);
        jTFecha.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jTFecha.setText("Fecha:");
        jTFecha.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);

        jTDiaYHora.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTDiaYHora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTDiaYHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTDiaYHoraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTDiaYHora, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTDiaYHora, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(157, 157, 157)
                                .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTMascotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTMascotaActionPerformed

    private void jTFecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFecha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFecha1ActionPerformed

    private void jTDiaYHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTDiaYHoraActionPerformed
        LocalDate actual = LocalDate.now();
        System.out.print(actual);
    }//GEN-LAST:event_jTDiaYHoraActionPerformed

    private void jBImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirActionPerformed

        generarPDF();
        limpiar();
    }//GEN-LAST:event_jBImprimirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Limpiar todos los campos
        limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        des = 0.1;
        actualizarTotales();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        des = 0.0;
        actualizarTotales();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBImprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTCliente;
    private javax.swing.JTextField jTCliente1;
    private javax.swing.JTextField jTDiaYHora;
    private javax.swing.JTextField jTFecha;
    private javax.swing.JTextField jTFecha1;
    private javax.swing.JTextField jTMascota;
    private javax.swing.JTable jTTTratamientos;
    // End of variables declaration//GEN-END:variables

    private void armarCabeceraTipo() {
        // Configura las columnas de la tabla
        tratamientoModel.setColumnCount(0); // Limpia las columnas existentes
        tratamientoModel.addColumn("Detalles"); // Agrega la columna de detalles
        tratamientoModel.addColumn("Importe"); // Agrega la columna de importe
        jTTTratamientos.setModel(tratamientoModel); // Establece el modelo de tabla en la tabla visual
    }

    private void armarTabla() {
        // Añade filas fijas a la tabla
        for (int i = 0; i < 5; i++) {
            tratamientoModel.addRow(new Object[]{"", 0.0}); // Agrega filas con valores iniciales en blanco y 0.0
        }

        // Calcula el total de las primeras 5 filas
        double total = calcularTotal();
        tratamientoModel.addRow(new Object[]{"Importe Total", total}); // Agrega fila con el importe total

        // Descuento inicial del 0%
        double descuento = total * 0.0;
        tratamientoModel.addRow(new Object[]{"Descuento %", descuento}); // Agrega fila con el descuento inicial

        // Calcula el total después del descuento
        double totalConDescuento = total - descuento;
        tratamientoModel.addRow(new Object[]{"Total", totalConDescuento}); // Agrega fila con el total después del descuento

        // Configura el ancho de la columna "Detalles" y el alto de las filas
        jTTTratamientos.getColumnModel().getColumn(0).setPreferredWidth(300);
        jTTTratamientos.setRowHeight(20);

        // Configura el renderizador personalizado para la columna de importe
        CustomTableCellRenderer renderer = new CustomTableCellRenderer(5); // Alinea hacia la izquierda las primeras 5 filas
        jTTTratamientos.getColumnModel().getColumn(0).setCellRenderer(renderer);
    }

    private double calcularTotal() {
        double total = 0;
        // Suma los importes de las primeras 5 filas
        for (int i = 0; i < 5; i++) {
            Object importe = tratamientoModel.getValueAt(i, 1);
            if (importe instanceof Number) {
                total += ((Number) importe).doubleValue();
            }
        }
        return total;
    }

    private void mostrarFechaHoraActual() {
        // Obtiene la fecha y hora actual y la muestra en el campo correspondiente
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatter);
        jTDiaYHora.setText(fechaHoraFormateada);
    }

    // Método para limpiar todos los campos del formulario y la tabla de tratamientos
    private void limpiar() {
        // Limpia los campos de texto Cliente y Mascota
        Utilidades.limpiarSetText(jTCliente1, jTCliente1, jTMascota);

        // Establece los importes de las filas en la columna 1 de la tabla de tratamientos a 0.0
        for (int i = 0; i < 8; i++) {
            tratamientoModel.setValueAt(0.0, i, 1); // Establece el importe en 0.0
        }

        // Establece los detalles de las primeras 5 filas en blanco
        for (int i = 0; i < 5; i++) {
            tratamientoModel.setValueAt("", i, 0); // Establece los detalles en blanco
        }

        // Establece los detalles de las últimas 3 filas en blanco y el descuento a 0.0
        for (int i = 0; i < 3; i++) {
            tratamientoModel.setValueAt(0.0, i + 5, 1); // Establece el importe en 0.0
        }

        des = 0.0; // Restablece el descuento a 0.0
    }

    // Método para generar el contenido de la factura
    public String generarFactura() {
        int numeroRemito = 5;
        StringBuilder factura = new StringBuilder();
        // Agrega el número de remito y la fecha al encabezado de la factura
        factura.append("Número de Remito: ").append(numeroRemito).append("\n"); // Número de remito
        factura.append("Fecha: ").append(obtenerFechaDelDia()).append("\n");
        // Agrega los detalles del cliente y la mascota
        factura.append("Cliente: ").append(jTCliente1.getText()).append("\n"); // Obtener texto de jTCliente1
        factura.append("Mascota: ").append(jTMascota.getText()).append("\n"); // Obtener texto de jTMascota

        // Separador
        factura.append("---------------------------------------------\n");

        factura.append("Servicios:\n");

        for (int i = 0; i < 8; i++) {
            Object importeObject = tratamientoModel.getValueAt(i, 1);
            String descripcion = (String) tratamientoModel.getValueAt(i, 0);

            if (importeObject != null) {
                String importeString = importeObject.toString();
                try {
                    double importe = Double.parseDouble(importeString);
                    factura.append("- ").append(descripcion.trim()).append(": $").append(importe).append("\n");
                } catch (NumberFormatException e) {
                    //factura.append("- ").append(descripcion.trim()).append(": $").append(0.0).append("\n");
                    // Manejar el caso donde importeObject es null
                    System.out.println("Error: Importe $ 0.0.");
                }
            } else {
                // Manejar el caso donde importeObject es null
                System.out.println("Error: La celda de importe está vacía.");
            }
        }
        // Separador
        factura.append("---------------------------------------------\n");
        factura.append("Calle de los Animales Felices, 123 Colonia Mascotaville - Ciudad Petrópolis, PV.\n");
        factura.append("PV Código Postal: 12345 - info@petdoctorveterinaria.com.\n");

        System.out.println(factura.toString());
        return factura.toString();
    }

    private String obtenerFechaDelDia() {
        // Obtener la fecha actual del sistema
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha como una cadena en el formato deseado (por ejemplo, "dd de MMMM de yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        return fechaFormateada;
    }

    public void generarPDF() {
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
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12); // Fuente para el contenidoSystem.out.println("Estoy acqui");
            String facturaContent = generarFactura();

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

            String clienteText = jTCliente1.getText().trim(); // Obtener el texto del jTextField y eliminar espacios en blanco
            String nombreArchivo = "factura" + clienteText + ".pdf";
            document.save(nombreArchivo);
            //document.save("factura"+jTCliente1.toString().trim()+".pdf");
            document.close();
            JOptionPane.showMessageDialog(this, "Remito generado correctamente.");
            System.out.println("Remito generado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para asociar eventos Enter a los campos del formulario para navegar entre ellos
    private void asociarEnterConComponentes() {
        Utilidades.asociarEnterConComponente(jTCliente1, jTMascota);
        Utilidades.asociarEnterConComponente(jTMascota, jTDiaYHora);
        Utilidades.asociarEnterConComponente(jTDiaYHora, jTTTratamientos);

    }

    // Método para actualizar los totales en la tabla
    private void actualizarTotales() {
        boolean flag = false;
        int cont = 0;
        // Evita la recursión infinita
        if (actualizandoTotales) {
            return;
        }
        actualizandoTotales = true;

        try {
            double total = 0;
            // Recorrer las filas de la tabla y sumar los importes
            for (int i = 0; i < 5; i++) {
                Object importeObject = tratamientoModel.getValueAt(i, 1);

                // Intentar convertir el valor a Double si es una cadena
                try {
                    double importe = Double.parseDouble(importeObject.toString());
                    total += importe;
                } catch (NumberFormatException e) {
                    // Manejar el caso en el que la cadena no pueda ser convertida a Double
                    System.out.println("Error: No se pudo convertir la cadena a Double.");
                    JOptionPane.showMessageDialog(this, "Error: No se pudo convertir la cadena a Double.");

                    // Establecer el valor problemático a 0.0
                    tratamientoModel.setValueAt(0.0, i, 1);
                }
            }

            // Actualizar la fila de total en la tabla
            tratamientoModel.setValueAt(total, tratamientoModel.getRowCount() - 3, 1);

            // Calcular y actualizar el descuento y el total después del descuento
            double descuento = total * des;
            tratamientoModel.setValueAt(descuento, tratamientoModel.getRowCount() - 2, 1);
            tratamientoModel.setValueAt(total - descuento, tratamientoModel.getRowCount() - 1, 1);
        } finally {
            actualizandoTotales = false;
        }
    }
}
