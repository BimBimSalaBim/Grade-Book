package management;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class MyTableModel extends DefaultTableModel {
	List<Color> rowColours = Arrays.asList(
	        Color.RED,
	        Color.GREEN,
	        Color.CYAN
	    );

	    public void setRowColour(int row, Color c) {
	        rowColours.set(row, c);
	        fireTableRowsUpdated(row, row);
	    }

	    public Color getRowColour(int row) {
	        return rowColours.get(row);
	    }

	    @Override
	    public int getRowCount() {
	        return Main.StudentList.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return Main.ColumnCount;
	    }

	    @Override
	    public Object getValueAt(int row, int column) {
	        return String.format("%d %d", row, column);
	    }
	}
	

	class MyTableCellRenderer extends DefaultTableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        MyTableModel model = (MyTableModel) table.getModel();
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        c.setBackground(model.getRowColour(row));
	        return c;
	    }
	}

