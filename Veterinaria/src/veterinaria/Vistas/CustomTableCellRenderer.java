package veterinaria.Vistas;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    private int rowCount;

    public CustomTableCellRenderer(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Personaliza el alineamiento basado en el índice de la fila
        if (row < rowCount) {
            setHorizontalAlignment(LEFT); // Alinea las primeras 'rowCount' filas hacia la izquierda
        } else {
            setHorizontalAlignment(RIGHT); // Alinea las filas restantes hacia la derecha
        }

        return this;
    }
}
