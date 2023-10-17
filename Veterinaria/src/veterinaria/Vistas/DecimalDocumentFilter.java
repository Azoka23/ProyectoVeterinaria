
package veterinaria.Vistas;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

// Clase que extiende DocumentFilter para filtrar la entrada de texto y reemplazar comas por puntos
public class DecimalDocumentFilter extends DocumentFilter {
    
    // Sobrescribe el método insertString para filtrar la inserción de texto en el documento
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        // Reemplazar comas por puntos al insertar texto
        string = string.replace(",", ".");
        super.insertString(fb, offset, string, attr); // Llama al método original para realizar la inserción
    }

    // Sobrescribe el método replace para filtrar la sustitución de texto en el documento
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Reemplazar comas por puntos al reemplazar texto
        text = text.replace(",", ".");
        super.replace(fb, offset, length, text, attrs); // Llama al método original para realizar la sustitución
    }
}
