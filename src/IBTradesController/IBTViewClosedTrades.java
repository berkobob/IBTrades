package IBTradesController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import IBTradesData.IBTClosedTrade;
import IBTradesData.IBTradeMapKey;
import IBTradesData.IBTradesData;
import IBTradesView.IBTradesView;

public class IBTViewClosedTrades implements ListSelectionListener{
	
	IBTradesData data;
	IBTradesView view;
	JTable table;
	
	List<IBTClosedTrade> tableData;
	
	public IBTViewClosedTrades(IBTradesView view, IBTradesData data) {
		this.data = data;
		this.view = view;
		view.clearScreen();
		buildTableData();
		table = view.viewClosedTradesMap(tableData, this);
	}
	
	private void buildTableData() {
		Map<IBTradeMapKey, List<IBTClosedTrade>> map = data.getTradeMap();
		tableData = new ArrayList<IBTClosedTrade>();
		
		for (IBTradeMapKey key: map.keySet()) {
			tableData.add(new IBTClosedTrade(key.getSymbol(), key.getCount(), key.getProceeds(), key.getCommission(), key.getNetCash()));
			if (key.show)
				tableData.addAll(map.get(key));
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Map<IBTradeMapKey, List<IBTClosedTrade>> map = data.getTradeMap();
//		System.out.println(e.getSource().toString());
//		System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());
		
		for (IBTradeMapKey key: map.keySet()){
			if (key.getSymbol().equals(table.getValueAt(table.getSelectedRow(), 1).toString()) && table.getValueAt(table.getSelectedRow(), 0) == null)
				if (key.show == true)
					key.show = false;
				else
					key.show = true;
			
		}
		buildTableData();
		table.repaint();
		view.clearScreen();
		table = view.viewClosedTradesMap(tableData, this);
	}

}
