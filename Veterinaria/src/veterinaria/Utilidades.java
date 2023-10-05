package veterinaria;

import veterinaria.Entidades.Cliente;
import veterinaria.Entidades.Mascota;
import veterinaria.Entidades.Tratamiento;
import veterinaria.Entidades.Visita;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

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
    
    
}
