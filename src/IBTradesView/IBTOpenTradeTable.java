package IBTradesView;

import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class IBTOpenTradeTable extends JTable {
	
	private static final long serialVersionUID = 9091698542888484108L;
	private final int COLUMNMARGIN = 25;

	public IBTOpenTradeTable() {
		// TODO Auto-generated constructor stub
	}

	public IBTOpenTradeTable(TableModel dm) {
		super(dm);
		setAutoCreateRowSorter(true);
		setDefaultRenderer(Date.class, new IBTDateRenderer());
		setDefaultRenderer(Double.class, new IBTDoubleRenderer());
		getColumnModel().setColumnMargin(COLUMNMARGIN);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		getColumnModel().getColumn(14).setCellRenderer(centerRenderer);
	}

	public IBTOpenTradeTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		// TODO Auto-generated constructor stub
	}

	public IBTOpenTradeTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public IBTOpenTradeTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public IBTOpenTradeTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public IBTOpenTradeTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		// TODO Auto-generated constructor stub
	}

}
