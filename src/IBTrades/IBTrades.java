package IBTrades;

import javax.swing.SwingUtilities;
import IBTradesController.IBTradesController;
import IBTradesData.IBTradesData;
import IBTradesView.IBTradesView;

public class IBTrades {

	public static void main(String[] args) {
		
		/* I think I created this to see if all the trades should be in an object */
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				IBTradesView view = new IBTradesView();
				IBTradesData data = new IBTradesData();
				new IBTradesController(view, data);
			}
		});
	}
}
