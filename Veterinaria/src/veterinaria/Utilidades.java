package veterinaria;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Entidades.Visita;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import veterinaria.Entidades.Sexo;

public class Utilidades {

    public static boolean confirmarSalida(JInternalFrame internalFrame) {
        int confirmacion = JOptionPane.showOptionDialog(
                internalFrame,
                "¿Estás seguro que quieres salir de la aplicación?",
                "Salir de la aplicación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Sí", "No"},
                "No"
        );

        return confirmacion == JOptionPane.YES_OPTION;
    }

    public static void limpiarSetText(javax.swing.JTextField... campos) {
        for (javax.swing.JTextField campo : campos) {
            campo.setText("");
        }
    }

    public static void mostrarError(Exception ex, JInternalFrame internalFrame) {
        JOptionPane.showMessageDialog(internalFrame, "Error: " + ex.getMessage());
    }

//    public static void validar(Object objet) throws Exception {
//
//        if (!(objet instanceof Cliente)) {
//            throw new Exception("Debes indicar un Cliente");
//        } else if (!(objet instanceof Mascota)) {
//            throw new Exception("Debes indicar una Mascota");
//        } else if (!(objet instanceof Tratamiento)) {
//            throw new Exception("Debes indicar un Tratamiento");
//        } else if (!(objet instanceof Visita)) {
//            throw new Exception("Debes indicar una Visita");
//        }
//    }
    // Método para asociar la tecla Enter con un campo de texto
    public static void asociarEnterConComponente(JComponent componenteOrigen, JComponent componenteDestino) {
    //public static void asociarEnterConCampo(JTextField campoOrigen, JTextField campoDestino) {
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        componenteOrigen.getInputMap().put(enterKey, "EnterAction");
        componenteOrigen.getActionMap().put("EnterAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componenteDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
            }
        });
    }

//    // Método para asociar la tecla Enter con un campo de texto y un Button
//    public static void asociarEnterConCampoaBoton(JTextField campoOrigen, JButton botonDestino) {
//        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//        campoOrigen.getInputMap().put(enterKey, "EnterAction");
//        campoOrigen.getActionMap().put("EnterAction", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                botonDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
//            }
//        });
//    }
//
//    // Método para asociar la tecla Enter con un cboton y un Button
//    public static void asociarEnterBotonABoton(JButton botonOrigen, JButton botonDestino) {
//        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//        botonOrigen.getInputMap().put(enterKey, "EnterAction");
//        botonOrigen.getActionMap().put("EnterAction", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                botonDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
//            }
//        });
//    }
//
//    // Método para asociar la tecla Enter con un cboton y un Button
//    public static void asociarEnterCampoACombo(JTextField campoOrigen, JComboBox<Sexo> comboDestino) {
//        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//        campoOrigen.getInputMap().put(enterKey, "EnterAction");
//        campoOrigen.getActionMap().put("EnterAction", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                comboDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
//            }
//        });
//    }
//
//    // Método para asociar la tecla Enter con un cboton y un Button
//    public static void asociarEnterComboACampo(JComboBox<Sexo> campoOrigen, JTextField comboDestino) {
//        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//        campoOrigen.getInputMap().put(enterKey, "EnterAction");
//        campoOrigen.getActionMap().put("EnterAction", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                comboDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
//            }
//        });
//    }
//
//    // Método para asociar la tecla Enter con un cboton y un Button
//    public static void asociarEnterCampoAFecha(JTextField campoOrigen, JDateChooser comboDestino) {
//        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//        campoOrigen.getInputMap().put(enterKey, "EnterAction");
//        campoOrigen.getActionMap().put("EnterAction", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                comboDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
//            }
//        });
//    }
//
//    // Método para asociar la tecla Enter con un cboton y un Button
//    public static void asociarEnterFechaACampo(JDateChooser campoOrigen, JTextField comboDestino) {
//        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//        campoOrigen.getInputMap().put(enterKey, "EnterAction");
//        campoOrigen.getActionMap().put("EnterAction", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                comboDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
//            }
//        });
//    }
//
//    // Método para asociar la tecla Enter con un cboton y un Button
//    public static void asociarEnterFechaABoton(JDateChooser campoOrigen, JButton comboDestino) {
//        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//        campoOrigen.getInputMap().put(enterKey, "EnterAction");
//        campoOrigen.getActionMap().put("EnterAction", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                comboDestino.requestFocusInWindow(); // Cambiar el foco al campo de destino
//            }
//        });
//    }
}
