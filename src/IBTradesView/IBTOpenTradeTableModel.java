package IBTradesView;

import java.util.Date;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import IBTradesData.IBTrade;

public class IBTOpenTradeTableModel implements TableModel {
	
	private List<IBTrade> data;
	private final String[] titles = {"Trade Date", "Buy/Sell", "Quantity", "Symbol", "Expiry", "Strike", "Put/Call", "Trade Price", "Proceeds", "Commission", "Net Cash", "Asset Class", "Open/Close", "Multiplier", "Notes"};
	
	private Class<?> classes[] = new Class[] {Date.class, String.class, Integer.class, String.class, Date.class, Double.class, String.class,
			Double.class, Double.class, Double.class, Double.class, String.class, String.class, Integer.class, String.class};

	public IBTOpenTradeTableModel(Object[] headers, List<IBTrade> data) {
		this.data = data;
	}
	
	public IBTOpenTradeTableModel(List<IBTrade> data) {
		this.data = data;
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return classes[arg0];
	}

	@Override
	public int getColumnCount() {
		return titles.length;
	}

	@Override
	public String getColumnName(int arg0) {
		return titles[arg0].toString();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0).get(arg1);
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
}
