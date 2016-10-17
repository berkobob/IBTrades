package IBTradesData;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IBTClosedTrade {
	
	private Date   startDate;
	private String symbol;
	private int    quantity;
	private Date   endDate;
	private Date   expiry;
	private double strike;
	private String putOrCall;
	private double proceeds = 0d;
	private double commission = 0d;
	private double netCash = 0d;
	private String assetClass;
	
	public IBTClosedTrade(List<IBTrade> t) {
		
		if (t.size() > 0) {
			
			startDate = (Date)t.get(0).get(IBTrade.TRADEDATE);
			symbol = (String)t.get(0).get(IBTrade.SYMBOL);
			quantity = (int)t.get(0).get(IBTrade.QUANTITY);
			endDate = (Date)t.get(t.size()-1).get(IBTrade.TRADEDATE);
			assetClass = (String)t.get(0).get(IBTrade.ASSETCLASS);
			
			if (assetClass.equals("Option")) {
				expiry = (Date)t.get(0).get(IBTrade.EXPIRY);
				strike = (Double)t.get(0).get(IBTrade.STRIKE);
				putOrCall = (String)t.get(0).get(IBTrade.PUTORCALL);
			}
			
			for (IBTrade x: t){
				proceeds += (Double)x.get(IBTrade.PROCEEDS);
				commission += (Double)x.get(IBTrade.COMMISSION); 
				netCash += (Double)x.get(IBTrade.NETCASH);
			}

		}
	}
	
	public IBTClosedTrade(String symbol, Integer count, Double proceeds, Double commission, Double netCash) {
		this.startDate   = null;
		this.symbol = symbol;
		this.quantity = count;
		this.endDate = null;
		this.expiry = null;
		this.strike = 0d;
		this.putOrCall = null;
		this.proceeds = proceeds;
		this.commission = commission;
		this.netCash = netCash;
		this.assetClass = "Symbol summary";
	}

	public String toString(){
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		SimpleDateFormat tradeDateFormat = new SimpleDateFormat("dd-MM-yy");
		String string = tradeDateFormat.format(startDate);
		string += "\t" + symbol;
		string += "\t" + quantity;
		string += "\t" + tradeDateFormat.format(endDate);
		
		if (assetClass.equals("Option")) {
			string += "\t" + tradeDateFormat.format(expiry);
			string += "\t" + strike;
			string += "\t" + putOrCall;
		}
		
		string += "\t" + decimalFormat.format(proceeds);
		string += "\t" + decimalFormat.format(commission);
		string += "\t" + decimalFormat.format(netCash);
		
		return string;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getQuantity() {
		return quantity;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getExpiry() {
		return expiry;
	}

	public double getStrike() {
		return strike;
	}

	public String getPutOrCall() {
		return putOrCall;
	}

	public double getProceeds() {
		return proceeds;
	}

	public double getCommission() {
		return commission;
	}

	public double getNetCash() {
		return netCash;
	}

	public String getAssetClass() {
		return assetClass;
	}

}
