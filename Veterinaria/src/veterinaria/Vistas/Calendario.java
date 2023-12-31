package veterinaria.Vistas;

import veterinaria.CustomPanel;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calendario extends javax.swing.JInternalFrame {

    /**
     * Creates new form Calendario
     */
    public Calendario() {
        CustomPanel customPanel = new CustomPanel();
        this.setContentPane(customPanel);
        initComponents();
        jCalendar1.setSelectableDateRange(new java.util.Date(), null);

        initCalendar();

        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setSize(400, 300);
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
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Turnos de visitas");

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));

        jCalendar1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 204)));
        jCalendar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCalendar1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCalendar1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel1.setText("Selecciona una fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCalendar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCalendar1KeyReleased
        // TODO add your handling code here:
        //selectDateFromCalendar();
        // siguiente();
    }//GEN-LAST:event_jCalendar1KeyReleased

    private void jCalendar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCalendar1KeyPressed
        // TODO add your handling code here:
        jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("calendar".equals(evt.getPropertyName())) {
                    selectDateFromCalendar();
                    siguiente(); // Llama a siguiente() cuando cambia la fecha
                }
            }
        });

    }//GEN-LAST:event_jCalendar1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void initCalendar() {
        // Obtén el JCalendar existente desde tu diseño (supongamos que ya está agregado en jPanel1)
        JCalendar calendar = jCalendar1;

        // Agregar un PropertyChangeListener al JCalendar
        calendar.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("calendar".equals(evt.getPropertyName())) {
                    System.out.println("Cambio en el calendario");
                    // Obtener la fecha seleccionada
                    LocalDate selectedDate = selectDateFromCalendar();
                    // Obtener la fecha actual
            LocalDate currentDate = LocalDate.now();
//Verificar si la fecha seleccionada es igual o posterior a la fecha actual
            if (selectedDate != null && !selectedDate.isBefore(currentDate)) {
                // La fecha seleccionada es válida, permitir la reserva
                System.out.println("Fecha seleccionada: " + selectedDate);
                siguiente();
            } else {
                // La fecha seleccionada no es válida, mostrar un mensaje o realizar alguna acción
                System.out.println("Seleccione una fecha válida (igual o posterior a hoy).");
            }
                    // Mostrar mensaje con la fecha seleccionada
                    //System.out.println("Fecha seleccionada: " + selectedDate);

                    // Pasar a la siguiente ventana
                    //siguiente();
                }
            }
        });
    }

    private LocalDate selectDateFromCalendar() {
        java.util.Date utilDate = jCalendar1.getDate();

        if (utilDate != null) {
            return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            System.out.println("Debes seleccionar una fecha.");
            return null; // O algún valor por defecto o manejo de error
        }
    }








private void siguiente() {
    // Obtén la fecha seleccionada
    LocalDate selectedDate = selectDateFromCalendar();

    // Verifica si la fecha es válida
    if (selectedDate != null) {
        // Crear el mensaje a mostrar
        String message = "Fecha seleccionada: " + selectedDate;

        // Crear botones personalizados
        Object[] options = {"Aceptar", "Cancelar"};

        // Mostrar el cuadro de diálogo
        int result = JOptionPane.showOptionDialog(
                this,        // Componente padre
                message,     // Mensaje a mostrar
                "Confirmar fecha", // Título del cuadro de diálogo
                JOptionPane.DEFAULT_OPTION, // Tipo de botones (puede ser cambiado según tus necesidades)
                JOptionPane.INFORMATION_MESSAGE, // Tipo de mensaje
                null,        // Icono (puede ser null)
                options,     // Botones personalizados
                options[0]    // Botón predeterminado
        );

        // Verificar el resultado
        if (result == 0) {
            // Si el usuario hace clic en "Aceptar", continuar con el flujo normal
            
            updateTableAndHorarios(selectedDate);
        } else {
            // Si el usuario hace clic en "Cancelar"
            System.out.println("Operación cancelada por el usuario.");
        }
    } else {
        System.out.println("Debes seleccionar una fecha.");
    }
}

private void updateTableAndHorarios(LocalDate selectedDate) {
    // Actualizar la tabla
  
    // Crear e iniciar la ventana de horarios
    Horarios horarios = new Horarios(selectedDate);
    horarios.initTimeTable(selectedDate);

    // Agregar la ventana de horarios al JDesktopPane
    JDesktopPane desktopPane = getDesktopPane();
    desktopPane.add(horarios);

    // Hacer visible la ventana de horarios
    horarios.setVisible(true);
}


    
    
    private void showTimeTable(LocalDate selectedDate) {
        System.out.println("Fecha recibida en showTimeTable: " + selectedDate);
        Horarios horarios = new Horarios(selectedDate);

        //horarios.initTimeTable(selectedDate);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(horarios);
        horarios.setVisible(true);
    }

  






}
