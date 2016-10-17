package IBTradesData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class IBTOpenTrades implements Iterable<IBTrade>, Serializable{

	private List<IBTrade> trades;

	public IBTOpenTrades() {
		trades = new ArrayList<>();
	}
	
	public void add(IBTrade trade){
		trades.add(trade);
	}
	
	public void add(List<IBTrade> trades) {
		this.trades.addAll(trades);
	}
	
	public int size() {
		return trades.size();
	}
	
	public List<IBTrade> get() {
		return trades;
	}
	
	public void clear() {
		trades.clear();
	}

	@Override
	public Iterator<IBTrade> iterator() {
		return trades.iterator();
	}
	
	
	/*
	 * SORTING METHODS
	 */

	public void sort() {
		Comparator<IBTrade> comparator = new Comparator<IBTrade>() {
			public int compare(IBTrade t1, IBTrade t2) {
				int v1 = ((String)t1.get(IBTrade.SYMBOL)).compareTo((String)t2.get(IBTrade.SYMBOL));
				if (v1 == 0) {
					int v2 = ((String)t1.get(IBTrade.ASSETCLASS)).compareTo((String)t2.get(IBTrade.ASSETCLASS));
					if (v2 == 0 && (String)t1.get(IBTrade.ASSETCLASS) == "Option") {
						int v3 = ((Date)t1.get(IBTrade.EXPIRY)).compareTo((Date)t2.get(IBTrade.EXPIRY));
						if (v3 == 0) {
							int v4 = ((Double)t1.get(IBTrade.STRIKE)).compareTo((Double)t2.get(IBTrade.STRIKE));
							if (v4 == 0) 
								//return Character.compare((char)t1.get(IBTrade.PUTORCALL), (char)t2.get(IBTrade.PUTORCALL));
								return ((String)t1.get(IBTrade.PUTORCALL)).compareTo((String)t2.get(IBTrade.PUTORCALL));
							else return v4;
						}
						else return v3;
					}
					else return v2;
				}
				else return v1;
			}
		};		
		Collections.sort(trades, comparator);
	}
	
	public void sortTradesBySymbolAndDate() {
		Comparator<IBTrade> comparator = new Comparator<IBTrade>() {
			
			public int compare(IBTrade t1, IBTrade t2) {
				int v1 = ((String)t1.get(IBTrade.SYMBOL)).compareTo((String)t2.get(IBTrade.SYMBOL));
				if (v1 == 0) 
					return ((Date)t1.get(IBTrade.TRADEDATE)).compareTo((Date)t2.get(IBTrade.TRADEDATE));
				else
					return v1;
			}
		};
		Collections.sort(trades, comparator);
	}
	
	public void sortTradesByDate() {
		Comparator<IBTrade> comparator = new Comparator<IBTrade>() {

			public int compare(IBTrade t1, IBTrade t2) {
				return ((Date)t1.get(IBTrade.TRADEDATE)).compareTo((Date)t2.get(IBTrade.TRADEDATE));
			}			
		};
		Collections.sort(trades, comparator);
	}
}
