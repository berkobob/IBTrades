package IBTradesView;

import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import IBTradesData.IBTClosedTrade;

public class IBTClosedTradeTableModel implements TableModel {
	
	private List<IBTClosedTrade> trades;
	private final String[] titles = {        "Open Date", "Symbol",     "Quantity",    "Close Date", "Proceeds",   "Commission", "Net Cash",   "Expiry",   "Strike",     "Put or Call"};
	private Class<?> classes[] = new Class[] {Date.class, String.class, Integer.class, Date.class,   Double.class, Double.class, Double.class, Date.class, Double.class, String.class};
	
	public IBTClosedTradeTableModel(List<IBTClosedTrade> trades) {
		this.trades = trades;
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
		return titles[arg0];
	}

	@Override
	public int getRowCount() {
		return trades.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch (arg1){
		case 0: return trades.get(arg0).getStartDate();
		case 1: return trades.get(arg0).getSymbol();
		case 2: return trades.get(arg0).getQuantity();
		case 3: return trades.get(arg0).getEndDate();
		case 4: return trades.get(arg0).getProceeds();
		case 5: return trades.get(arg0).getCommission();
		case 6: return trades.get(arg0).getNetCash();
		
		case 7: if (trades.get(arg0).getAssetClass().equals("Option"))
					return trades.get(arg0).getExpiry();
				else
					return null;
		case 8: if (trades.get(arg0).getAssetClass().equals("Option"))
					return trades.get(arg0).getStrike();
				else
					return null;
		case 9: if (trades.get(arg0).getAssetClass().equals("Option"))
					return trades.get(arg0).getPutOrCall();
				else
					return null;
		default: return null;
		}
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
