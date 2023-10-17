package veterinaria;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

// Clase de utilidades para operaciones comunes en la aplicación de la clínica veterinaria
public class Utilidades {

    // Método para confirmar la salida de la aplicación
    public static boolean confirmarSalida(JInternalFrame internalFrame) {
        // Muestra un cuadro de diálogo de confirmación para salir de la aplicación
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

        // Retorna verdadero si el usuario selecciona "Sí", falso de lo contrario
        return confirmacion == JOptionPane.YES_OPTION;
    }

    // Método para limpiar el texto en los campos de texto proporcionados
    public static void limpiarSetText(javax.swing.JTextField... campos) {
        // Itera sobre los campos de texto y establece su texto como vacío
        for (javax.swing.JTextField campo : campos) {
            campo.setText("");
        }
    }

    // Método para mostrar un mensaje de error con la excepción proporcionada
    public static void mostrarError(Exception ex, JInternalFrame internalFrame) {
        // Muestra un cuadro de diálogo con el mensaje de error de la excepción
        JOptionPane.showMessageDialog(internalFrame, "Error: " + ex.getMessage());
    }

    // Método para asociar la tecla Enter con un componente específico
    public static void asociarEnterConComponente(JComponent componenteOrigen, JComponent componenteDestino) {
        // Asocia la tecla Enter con el componente de destino para cambiar el foco
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        componenteOrigen.getInputMap().put(enterKey, "EnterAction");
        componenteOrigen.getActionMap().put("EnterAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componenteDestino.requestFocusInWindow(); // Cambia el foco al componente de destino
            }
        });
    }

    // Método para obtener un entero desde un campo de texto
    public static int obtenerEnteroDesdeCampo(JTextField campo) {
        try {
            return Integer.parseInt(campo.getText().trim()); // Convierte el texto en un entero
        } catch (NumberFormatException e) {
            return -1; // Retorna un valor negativo para indicar un error si no se puede convertir
        }
    }

    // Método para obtener texto desde un campo de texto
    public static String obtenerTextoDesdeCampo(JTextField campo) {
        return campo.getText().trim(); // Obtiene y devuelve el texto del campo de texto
    }
}

