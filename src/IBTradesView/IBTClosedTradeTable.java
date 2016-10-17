package IBTradesView;

import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class IBTClosedTradeTable extends JTable {
	
	private static final long serialVersionUID = 9091698542888484108L;
	private final int COLUMNMARGIN = 25;

	public IBTClosedTradeTable() {
		// TODO Auto-generated constructor stub
	}

	public IBTClosedTradeTable(TableModel dm) {
		super(dm);
		setAutoCreateRowSorter(true);
		setDefaultRenderer(Date.class, new IBTDateRenderer());
		setDefaultRenderer(Double.class, new IBTDoubleRenderer());
		getColumnModel().setColumnMargin(COLUMNMARGIN);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
	}

	public IBTClosedTradeTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		// TODO Auto-generated constructor stub
	}

	public IBTClosedTradeTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public IBTClosedTradeTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public IBTClosedTradeTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public IBTClosedTradeTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		// TODO Auto-generated constructor stub
	}

}
