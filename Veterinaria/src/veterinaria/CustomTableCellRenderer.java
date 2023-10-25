package veterinaria;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private int rowCount;

    public CustomTableCellRenderer(int rowCount) {
        this.rowCount = rowCount;
        setOpaque(true); // Asegura que el fondo de las celdas sea visible
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

        // Personaliza el color de fondo de la celda seleccionada
        if (isSelected && table.getSelectedRow() == row && table.getSelectedColumn() == column) {
            setBackground(Color.DARK_GRAY); // Cambia el color de fondo solo si está seleccionado y es la celda actual
            setForeground(Color.BLACK); // Cambia el color del texto a negro para mejorar la visibilidad
        } else {
            setBackground(Color.WHITE); // Restaura el color de fondo predeterminado
            setForeground(Color.BLACK); // Restaura el color del texto a negro
        }

        return this;
    }

    public static class CustomCellEditor extends CustomTableCellRenderer implements TableCellEditor {

        private JTable table;

        public CustomCellEditor(int rowCount) {
            super(rowCount);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            return super.getTableCellRendererComponent(table, value, isSelected, true, row, column);
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return true;
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            if (anEvent instanceof KeyEvent) {
                KeyEvent ke = (KeyEvent) anEvent;
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = table.getEditingRow();
                    int column = table.getEditingColumn() + 1; // Mueve a la siguiente columna
                    if (column >= table.getColumnCount()) {
                        column = 0; // Vuelve a la primera columna si alcanza el final
                        row++; // Mueve a la siguiente fila
                    }
                    if (row < table.getRowCount()) {
                        table.changeSelection(row, column, false, false);
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public boolean stopCellEditing() {
            return true;
        }

        @Override
        public void cancelCellEditing() {
        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {
        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {
        }
    }
}
