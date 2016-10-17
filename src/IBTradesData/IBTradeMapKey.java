package IBTradesData;


public class IBTradeMapKey implements Comparable<Object> {
	
	private String symbol;
	private Integer count;
	private Double proceeds;
	private Double commission;
	private Double netCash;
	public boolean show;

	public IBTradeMapKey(String symbol, Integer count, Double proceeds, Double commission, Double netCash) {
		this.symbol = symbol;
		this.count = count;
		this.proceeds = proceeds;
		this.commission = commission;
		this.netCash = netCash;
		show = false;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public Integer getCount() {
		return count;
	}

	public Double getProceeds() {
		return proceeds;
	}
	
	public Double getCommission() {
		return commission;
	}
	
	public Double getNetCash(){
		return netCash;
	}
	
	public String toString() {
		return symbol + "\t" + count + "\t" + proceeds + "\t" + commission + "\t" + netCash;
	}

	public int compare(IBTradeMapKey o1, IBTradeMapKey o2) {
		return o1.getSymbol().compareTo(o2.getSymbol());
	}

	@Override
	public int compareTo(Object o) {
		IBTradeMapKey t = (IBTradeMapKey)o;
		return getSymbol().compareTo(t.getSymbol());
	}

}
