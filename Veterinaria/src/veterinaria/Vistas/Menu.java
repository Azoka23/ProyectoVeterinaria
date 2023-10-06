package veterinaria.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends javax.swing.JFrame {

    DesktopPaneWithBackground jEscritorio;

    /**
     * Creates new form FormMenu
     */
    public Menu() {

        initComponents();
        //backgroundLabel = new javax.swing.JLabel(new javax.swing.ImageIcon("/Users/marcelaaliciaarroyo/Desktop/JAVA/Curso Java ULP/NetBeansProjects/ClinicaVeterinaria/Imagenes/veterinary_icon_180438.png"));
//        

        // Configura otras propiedades del JFrame
        jEscritorio = new DesktopPaneWithBackground("/veterinaria/Imagenes/veterinary_icon_180438.png");
        setContentPane(jEscritorio);

        // Configura otras propiedades del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        pack(); // Ajusta el tamaño inicial según el contenido
        setVisible(true);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setResizable(true);
//        pack(); // Ajusta el tamaño inicial según el contenido
//        setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(Menu::new);
    }

    //}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jBarClientes = new javax.swing.JMenu();
        jMIBuscar = new javax.swing.JMenuItem();
        jMINuevo = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jBarMascotas = new javax.swing.JMenu();
        jMIBuscarMascotas = new javax.swing.JMenuItem();
        jMINuevaMasc = new javax.swing.JMenuItem();
        jMbarAdm = new javax.swing.JMenu();
        jMIVisitas = new javax.swing.JMenuItem();
        jMITratam = new javax.swing.JMenuItem();
        jMIOtros = new javax.swing.JMenuItem();
        jMBarFacturacion = new javax.swing.JMenu();
        jMICosto = new javax.swing.JMenuItem();
        jMBarSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PET DOCTOR");
        setPreferredSize(new java.awt.Dimension(800, 700));

        jBarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/people_114360.png"))); // NOI18N
        jBarClientes.setText("Clientes");

        jMIBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMIBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/free-30-instagram-stories-icons23_122570.png"))); // NOI18N
        jMIBuscar.setText("Buscar");
        jMIBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIBuscarActionPerformed(evt);
            }
        });
        jBarClientes.add(jMIBuscar);

        jMINuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMINuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/free-30-instagram-stories-icons53_122600.png"))); // NOI18N
        jMINuevo.setText("Nuevo");
        jMINuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMINuevoActionPerformed(evt);
            }
        });
        jBarClientes.add(jMINuevo);

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");
        jBarClientes.add(jRadioButtonMenuItem1);

        jMenuBar1.add(jBarClientes);

        jBarMascotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/labrador_dog_animal_15967.png"))); // NOI18N
        jBarMascotas.setText("Mascotas");

        jMIBuscarMascotas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMIBuscarMascotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/free-30-instagram-stories-icons21_122568.png"))); // NOI18N
        jMIBuscarMascotas.setText("Buscar");
        jBarMascotas.add(jMIBuscarMascotas);

        jMINuevaMasc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMINuevaMasc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/free-30-instagram-stories-icons51_122598.png"))); // NOI18N
        jMINuevaMasc.setText("Nueva");
        jMINuevaMasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMINuevaMascActionPerformed(evt);
            }
        });
        jBarMascotas.add(jMINuevaMasc);

        jMenuBar1.add(jBarMascotas);

        jMbarAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/pet_folder_file_document_page_icon_124627.png"))); // NOI18N
        jMbarAdm.setText("Administracion");
        jMbarAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMbarAdmActionPerformed(evt);
            }
        });

        jMIVisitas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMIVisitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/checklist_medical_healthcare_pen_clipboard_clipchart_icon_142002.png"))); // NOI18N
        jMIVisitas.setText("Visitas");
        jMIVisitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIVisitasActionPerformed(evt);
            }
        });
        jMbarAdm.add(jMIVisitas);

        jMITratam.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMITratam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/medical-12_icon-icons.com_73944.png"))); // NOI18N
        jMITratam.setText("Tratamiento");
        jMITratam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMITratamActionPerformed(evt);
            }
        });
        jMbarAdm.add(jMITratam);

        jMIOtros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMIOtros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/household_chores_feeding_pet_feed_dog_food_icon_133347.png"))); // NOI18N
        jMIOtros.setText("Otros");
        jMIOtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIOtrosActionPerformed(evt);
            }
        });
        jMbarAdm.add(jMIOtros);

        jMenuBar1.add(jMbarAdm);

        jMBarFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/budget256_24775.png"))); // NOI18N
        jMBarFacturacion.setText("Facturacion");

        jMICosto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMICosto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/pet_rat_mouse_11098.png"))); // NOI18N
        jMICosto.setText("Costo visita");
        jMBarFacturacion.add(jMICosto);

        jMenuBar1.add(jMBarFacturacion);

        jMBarSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veterinaria/Imagenes/477A86B10B034C3087ADC368774218B1.png"))); // NOI18N
        jMBarSalir.setText("Salir");
        jMenuBar1.add(jMBarSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMIBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMIBuscarActionPerformed

    private void jMINuevaMascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMINuevaMascActionPerformed
        try {
            jEscritorio.removeAll();
            jEscritorio.repaint();
            //jEscritorio.add(jLULP);
            FormularioMascotas cargarMascotas = new FormularioMascotas();

            // Configurar el tamaño del JInternalFrame
            cargarMascotas.setSize(600, 500);
            cargarMascotas.pack();
            // Calcular el centro del JDesktopPane
            int x = (jEscritorio.getWidth() - cargarMascotas.getWidth()) / 2;
            int y = (jEscritorio.getHeight() - cargarMascotas.getHeight()) / 2;

            // Establecer la ubicación y agregar el JInternalFrame
            cargarMascotas.setBounds(x, y, cargarMascotas.getWidth(), cargarMascotas.getHeight());
            jEscritorio.add(cargarMascotas);
            cargarMascotas.setVisible(true);

            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMINuevaMascActionPerformed

    private void jMITratamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMITratamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMITratamActionPerformed

    private void jMIOtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIOtrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMIOtrosActionPerformed

    private void jMINuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMINuevoActionPerformed
        jEscritorio.removeAll();
        jEscritorio.repaint();

        FormularioCliente cargarCliente = new FormularioCliente();

        cargarCliente.setSize(600, 500);

        int x = (jEscritorio.getWidth() - cargarCliente.getWidth()) / 2;
        int y = (jEscritorio.getHeight() - cargarCliente.getHeight()) / 2;

        cargarCliente.setBounds(x, y, cargarCliente.getWidth(), cargarCliente.getHeight());
        jEscritorio.add(cargarCliente);
        cargarCliente.setVisible(true);
    }//GEN-LAST:event_jMINuevoActionPerformed

    private void jMbarAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMbarAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMbarAdmActionPerformed

    private void jMIVisitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIVisitasActionPerformed
        jEscritorio.removeAll();
        jEscritorio.repaint();
        //jEscritorio.add(jLULP);
        FormularioVisitas visitas = new FormularioVisitas();
        // Configurar el tamaño del JInternalFrame
        visitas.setSize(600, 500);
        visitas.pack();
        // Calcular el centro del JDesktopPane
        int x = (jEscritorio.getWidth() - visitas.getWidth()) / 2;
        int y = (jEscritorio.getHeight() - visitas.getHeight()) / 2;
        // Establecer la ubicación y agregar el JInternalFrame
        visitas.setBounds(x, y, visitas.getWidth(), visitas.getHeight());
        jEscritorio.add(visitas);
        visitas.setVisible(true);
    }//GEN-LAST:event_jMIVisitasActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormMenu().setVisible(true);
//            }
//        });
    //}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jBarClientes;
    private javax.swing.JMenu jBarMascotas;
    private javax.swing.JMenu jMBarFacturacion;
    private javax.swing.JMenu jMBarSalir;
    private javax.swing.JMenuItem jMIBuscar;
    private javax.swing.JMenuItem jMIBuscarMascotas;
    private javax.swing.JMenuItem jMICosto;
    private javax.swing.JMenuItem jMINuevaMasc;
    private javax.swing.JMenuItem jMINuevo;
    private javax.swing.JMenuItem jMIOtros;
    private javax.swing.JMenuItem jMITratam;
    private javax.swing.JMenuItem jMIVisitas;
    private javax.swing.JMenu jMbarAdm;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    // End of variables declaration//GEN-END:variables

}
