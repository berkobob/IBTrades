package IBTradesData;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class IBTrade implements Serializable{

	@SuppressWarnings("unused")
	private String[] importedTrade; 
	
	private List<Object> trade = new ArrayList<Object>();
	
	
	public final static int TRADEDATE   = 0;
	public final static int BUYORSELL   = 1;
	public final static int QUANTITY    = 2;
	public final static int SYMBOL      = 3;
	public final static int EXPIRY      = 4;
	public final static int STRIKE      = 5;
	public final static int PUTORCALL   = 6;
	public final static int PRICE       = 7;
	public final static int PROCEEDS    = 8;
	public final static int COMMISSION  = 9;
	public final static int NETCASH     = 10;
	public final static int ASSETCLASS  = 11;
	public final static int OPENORCLOSE = 12;
	
	public IBTrade(String[] importedTrade) {
		this.importedTrade = importedTrade;
			
		/*
		 * 0. Trade date
		 */
		SimpleDateFormat tradeDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			trade.add(tradeDateFormat.parse(importedTrade[0]));
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage() + "\t" + importedTrade[3]);
			throw new IllegalArgumentException("Every trade must have a date");
		}

		// 1. Buy or Sell
		trade.add(importedTrade[1]);
		
		// 2. Quantity
		trade.add(Integer.parseInt(importedTrade[2]));
		
		// 3. Symbol
		if (importedTrade[3].contains(" ")) 
			if (importedTrade[3].indexOf(' ') == 5 )
				trade.add(importedTrade[3].substring(0, importedTrade[3].indexOf(' ')-1));
			else
				trade.add(importedTrade[3].substring(0, importedTrade[3].indexOf(' ')));
		else
			trade.add(importedTrade[3]);
		
		/*
		 * 4. Expiry 
		 */
		SimpleDateFormat expiryDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			trade.add(expiryDateFormat.parse(importedTrade[4]));
		} catch (ParseException e) {
			trade.add(null);
		}
		
		// 5. Strike
		try {
			trade.add(Double.parseDouble(importedTrade[5]));
		} catch (Exception e) {
			trade.add(null);
		}
		
		// 6. Put or Call
		switch(importedTrade[6]) {
		case "P": trade.add("PUT"); break;
		case "C": trade.add("CALL"); break;
		default: trade.add(" ");
		}
		
		// 7. Price
		trade.add(Double.parseDouble(importedTrade[7]));
		
		// 8. Proceeds
		trade.add(Double.parseDouble(importedTrade[8]));
		
		// 9. Commission
		trade.add(Double.parseDouble(importedTrade[9]));
		
		// 10. Net cash
		trade.add(Double.parseDouble(importedTrade[10]));
		
		// 11. Asset Class
		switch(importedTrade[11]) {
		case "STK": trade.add("Stock");  break;
		case "OPT": trade.add("Option"); break;
		default: throw new IllegalArgumentException("A trade must be for a STOCK or an OPTION"); 
		}
		
		// 12. Open or Close
		switch(importedTrade[12]){
		case "O": trade.add("Open"); break;
		case "C": trade.add("Close"); break;
		default: throw new IllegalArgumentException("A trade must either open or close");
		}
		
		// Multiplier
		trade.add(Integer.parseInt(importedTrade[13]));
		
		// Notes
		trade.add(importedTrade[14]);
	}

	public String toString(){
		if (trade.get(IBTrade.ASSETCLASS).equals(new String ("Stock")))
				return (String) (trade.get(IBTrade.SYMBOL) + " stock");
		else {
			SimpleDateFormat tradeDateFormat = new SimpleDateFormat("dd-MM-yy");
			String value = tradeDateFormat.format((Date)trade.get(IBTrade.EXPIRY));
			return (String)trade.get(IBTrade.SYMBOL) + "\t" + value + "\t" + (int)trade.get(IBTrade.QUANTITY) + "\t" + (Double)trade.get(IBTrade.STRIKE) + "\t" + (String)trade.get(IBTrade.PUTORCALL) + "\t" + (String)trade.get(IBTrade.OPENORCLOSE);
		}
	}
	
	public Object[] getImportedTrade() {
		return trade.toArray(); 
	}
	
	public Object get(int x){
		return trade.get(x);
	}
	
	public boolean sameTrade(IBTrade t) {
		
		if (t.get(IBTrade.SYMBOL).equals(trade.get(IBTrade.SYMBOL))) {
			if ((((String)trade.get(IBTrade.ASSETCLASS)).equals(new String("Stock"))) &&
				    (((String)t.get(IBTrade.ASSETCLASS)).equals(new String("Stock"))))
				return true;
			else if ((((String)trade.get(IBTrade.ASSETCLASS)).equals(new String("Option"))) &&
				    (((String)t.get(IBTrade.ASSETCLASS)).equals(new String("Option")))      &&
			        (t.get(IBTrade.EXPIRY).equals(trade.get(IBTrade.EXPIRY)) &&
					 t.get(IBTrade.STRIKE).equals(trade.get(IBTrade.STRIKE)) &&
					 t.get(IBTrade.PUTORCALL).equals(trade.get(IBTrade.PUTORCALL)))) 
				return true;
		} 
		return false;
	}
	
}

