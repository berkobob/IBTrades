package IBTradesData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IBTradesData {

	private static final Boolean DEBUG = true;
	private IBTOpenTrades   allTrades      = null;
	private IBTOpenTrades   importedTrades = null;
	private IBTOpenTrades   openTrades     = null;
	private IBTClosedTrades closedTrades   = null;

	public IBTradesData() {
		loadAllTrades();
		if (allTrades.size() > 0) findClosedTrades();
		if (closedTrades != null) closedTrades.makeTradeMap();
	}
	
	/*
	 * PROCESS MAIN TRADE LOG I.E. ALL TRADES
	 */
	private void loadAllTrades() {
		allTrades = new IBTOpenTrades();
		try {
			FileInputStream fis = new FileInputStream("trades");
			ObjectInputStream ois = new ObjectInputStream(fis);
			allTrades = (IBTOpenTrades) ois.readObject();
			ois.close();
			fis.close();
			if (DEBUG) System.out.println(allTrades.size() + " trades loaded");
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}
	
	private void saveAllTrades() {
		try {
			FileOutputStream fos = new FileOutputStream("trades", false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(allTrades);
			oos.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		if (DEBUG) System.out.println(allTrades.size()+" trades saved");
	}

	public List<IBTrade> getAllTrades() {
		return allTrades.get();
	}
	
	public void sortAllTrades(){
		allTrades.sort();
	}
	
	public void deleteTradesFile() {
		try {
			File file = new File("trades");
			if(file.delete()){
				System.out.println("File deleted!");
				allTrades.clear();
			}
			else
				System.out.println("File did not delete probably because it doesn't exist.");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	
	/*
	 * PROCESS CLOSED TRADES
	 */
	public List<IBTClosedTrade> getClosedTrades() {
		if (closedTrades == null)
			return null;
		
		return closedTrades.get();
	}
	
	public Map<IBTradeMapKey, List<IBTClosedTrade>> getTradeMap() {
		return closedTrades.getMap();
	}

	public void findClosedTrades() {

		openTrades = new IBTOpenTrades();
		closedTrades = new IBTClosedTrades();
		if (allTrades.size() == 0) return;
		
		List<IBTrade> sameTrade = new ArrayList<IBTrade>();
		IBTrade thisTrade, nextTrade = null;
		
		if (DEBUG) System.out.println("Sorting " + allTrades.size()+ " trades.");
		
		allTrades.sort();
		
		Iterator<IBTrade> list = allTrades.iterator();
		thisTrade = list.next();
		sameTrade.add(thisTrade);
		int quantity = (int)thisTrade.get(IBTrade.QUANTITY);
		
		while (list.hasNext()) {
			nextTrade = list.next();
			if (thisTrade.sameTrade(nextTrade)) {
				sameTrade.add(nextTrade);
				quantity += (int)nextTrade.get(IBTrade.QUANTITY);
				if (quantity == 0) {
					closedTrades.add(sameTrade);
					sameTrade.clear();
				}
			} else {
				if (sameTrade.size() > 0) {
					if (DEBUG) {
						System.out.println("We have an open trade made up of:");
						for (IBTrade t: sameTrade)
							System.out.println(t);
					}
					openTrades.add(sameTrade);
					sameTrade.clear();
				}
				sameTrade.add(nextTrade);
				quantity = (int)nextTrade.get(IBTrade.QUANTITY);
			}
			thisTrade = nextTrade;
		}
		
		if (sameTrade.size() > 0) {
			if (DEBUG) {System.out.println("The last trade is open and made up of");
				for (IBTrade t: sameTrade)
					System.out.println(t);
			}
			openTrades.add(sameTrade);
		} else {
			if (DEBUG) System.out.println("The last trade closed everything out");
		}
		
		if (DEBUG) {
			System.out.println("No of open trades is " + openTrades.size() + " and here they are:");
			for(IBTrade o: openTrades) {
				System.out.println(o);
			}
			System.out.println("");
			System.out.println("No of CLOSED trades is " + closedTrades.size() + " and here they are:");
			for(IBTClosedTrade c: closedTrades) {
				System.out.println(c);
			}
		}
	}

	
	/*
	 * PROCESS IMPORTED TRADES
	 */
	public void importTrades(File fileName) {
		
		String line = null;
		importedTrades = new IBTOpenTrades();
		
		if (DEBUG) System.out.println("Importing " + fileName);
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName)); 
			
			if ((line = file.readLine()) != null) {
				while ((line = file.readLine()) != null) {
					line = line.replaceAll("\"", "");
					if (line.charAt(line.length()-1) == ',') line += "   ";
					this.importedTrades.add(new IBTrade(line.split(",")));
				}
			}
			
			file.close();
			
		} catch (IOException e) {
			System.out.println("*** Error reading the file: " + fileName);
			e.printStackTrace();
		}
	}

	public List<IBTrade> getImportedTrades() {
		return importedTrades.get();
	}

	public void addImportedTrades() {
		allTrades.add(importedTrades.get());
		saveAllTrades();
	}
	
	public void emptyImportedTrades() {
		importedTrades.clear();
	}
	
	
	/*
	 * PROCESS OPEN TRADES
	 */
	public int getOpenTradeCount() {
		return openTrades.size();
	}
	
	public List<IBTrade> getOpenTrades() {
		return openTrades.get();
	}
}
