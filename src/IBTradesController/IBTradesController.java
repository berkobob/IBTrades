package IBTradesController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import IBTradesView.IBTradesView;
import IBTradesData.IBTradesData;

public class IBTradesController implements ActionListener{
	
	private final boolean DEBUG = true;
	private IBTradesView view;
	private IBTradesData data;

	public IBTradesController(IBTradesView view, IBTradesData data) {
		this.view = view;
		this.data = data;
		if (DEBUG) System.out.println("Instantiating Controller");
		System.err.println("This should print to the error log!?!");
		view.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (DEBUG) System.out.println("Action being performed " + e.getActionCommand());
		String command = e.getActionCommand();
		switch (command) {
			case "Exit": 
				if (view.sure("Quit IB Trades")) System.exit(0);
				break;
			case "Import":
				view.clearScreen();
				File fileName = view.getFileName();
				if (fileName != null) {
					data.importTrades(fileName);
					view.viewImportedTrades(data.getImportedTrades());
				}
				break;
			case "Add":
				data.addImportedTrades();
				view.clearScreen();
				break;
			case "All":
				view.clearScreen();
				data.sortAllTrades();
				view.viewOpenTrades(data.getAllTrades());
				break;
			case "Delete":
				data.deleteTradesFile();
				view.clearScreen();
				break;
			case "Table":
				view.clearScreen();
				//view.viewClosedTrades(data.getClosedTrades());
				new IBTViewClosedTrades(view, data);
				break;
			case "Tree":
				view.clearScreen();
				//view.tradeTree(data.getClosedTrades());
				//view.tradeTree(data.getTradeMap());
				view.closedBySymbol(data.getTradeMap());
				break;
			case "Reject":
				data.emptyImportedTrades();
				view.clearScreen();
				break;
			case "Sort":
				data.findClosedTrades();
				break;
			case "Open":
				view.clearScreen();
				view.viewOpenTrades(data.getOpenTrades());
				break;
			case "Clear":
				view.clearScreen();
				break;
			default:
				view.notYetImplemented();
		}
	}	
}
