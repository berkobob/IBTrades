package IBTradesData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class IBTClosedTrades implements Iterable<IBTClosedTrade>, Serializable {
	
	private static final boolean DEBUG = false;

	private List<IBTClosedTrade> trades;
	
	private Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap;
	
	public IBTClosedTrades() {
		trades = new ArrayList<IBTClosedTrade>();
	}
	
	public Map<IBTradeMapKey, List<IBTClosedTrade>> getMap() {
		return tradeMap;
	}
	
	public List<IBTClosedTrade> get() {
		return trades;
	}

	public void add(List<IBTrade> trades) {
		this.trades.add(new IBTClosedTrade(trades));
	}
	
	public int size(){
		return trades.size();
	}

	@Override
	public Iterator<IBTClosedTrade> iterator() {
		return trades.iterator();
	}
	
	
	public void makeTradeMap() {
		tradeMap = new LinkedHashMap<IBTradeMapKey, List<IBTClosedTrade>>();
		List<IBTClosedTrade> thisSymbol = new ArrayList<IBTClosedTrade>();
		
		String symbol = trades.get(0).getSymbol();
		Integer count = 0;
		Double proceeds = 0d;
		Double commission = 0d;
		Double netCash = 0d;
		
		sortBySymbol();
		
		for (IBTClosedTrade trade: trades) {
			if (symbol.equals(trade.getSymbol())) {
				thisSymbol.add(trade);
				count += 1;
				proceeds += trade.getProceeds();
				commission += trade.getCommission();
				netCash += trade.getNetCash();
			}
			else {
				tradeMap.put(new IBTradeMapKey(symbol, count, proceeds, commission, netCash), thisSymbol);
				symbol = trade.getSymbol();
				count = 1;
				proceeds = trade.getProceeds();
				commission = trade.getCommission();
				netCash = trade.getNetCash();
				thisSymbol = new ArrayList<IBTClosedTrade>();
				thisSymbol.add(trade);
			}
		}

		if (DEBUG)
			for (IBTradeMapKey key: tradeMap.keySet()) {
				System.out.println(key);
				List<IBTClosedTrade> trades = tradeMap.get(key);
				for (IBTClosedTrade trade: trades)
					System.out.println(trade);
			}
	}
	
	
	private void sortBySymbol() {
		Comparator<IBTClosedTrade> comparator = new Comparator<IBTClosedTrade>() {

			@Override
			public int compare(IBTClosedTrade t1, IBTClosedTrade t2) {
				return t1.getSymbol().compareTo(t2.getSymbol());
			}			
		};
		Collections.sort(trades, comparator);
	}
}
