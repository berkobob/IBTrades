package IBTradesView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import IBTradesData.IBTClosedTrade;
import IBTradesData.IBTrade;
import IBTradesData.IBTradeMapKey;

public class IBTradesView {

	private final boolean DEBUG = true;
	private JFrame frame;
	private IBTMenuBar menuBar;
	private ActionListener actionListener;
	
	public IBTradesView() {
		if (DEBUG) System.out.println("Instatiating View");
		frame = new JFrame("IB Trades");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void tradeTree(List<IBTClosedTrade> closedTrades) {
		IBTradeTree tradeTree = new IBTradeTree(closedTrades);
		frame.add(tradeTree);
		frame.revalidate();
	}
	
	public void tradeTree(Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap) {
		IBTradeTree tradeTree = new IBTradeTree(tradeMap);
		frame.add(tradeTree);
		frame.revalidate();
	}
	
	public void closedBySymbol(Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap) {
		frame.add(new IBTClosedBySymbol(tradeMap));
		frame.revalidate();
	}

	/*
	 * METHOD TO SET ACTION LISTENER AND TO BUILD THE MENU BAR
	 */
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		menuBar = new IBTMenuBar(actionListener);
		frame.setJMenuBar(menuBar);
	}
	
	/*
	 * POP UP WINDOW TO ASK ARE YOU SURE
	 */
	public boolean sure(String msg) {
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", msg, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_NO_OPTION)	return true;
		return false;
	}

	/*
	 * POP UP WINDOW TO SAY NOT YET IMPLEMENTED
	 */
	public void notYetImplemented() {
		JOptionPane.showMessageDialog(frame, "Not yet implemented");
	}

	/*
	 * POP UP WINDOW TO DISPLAY A MESSAGE
	 */
	public void message(String string) {
		JOptionPane.showMessageDialog(frame, string);
	}
	
	/*
	 * POP UP WINDOW TO CHOOSE A FILE
	 */
	public File getFileName() {
		
		JFileChooser fileName = new JFileChooser("C:\\Users\\Antoine\\SkyDrive\\IB Trade Reports");
		int returnVal = fileName.showOpenDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (DEBUG) System.out.println("Going to import: " + fileName.getSelectedFile());
			return fileName.getSelectedFile();
		} else {
			if (DEBUG) System.out.println("No file selected this time");
			return null;
		}
	}

	/*
	 * CREATES A JTABLE TO DISPLAY RAW TRADE DATA
	 */
	public void viewImportedTrades(List<IBTrade> trades) {
		IBTOpenTradeTable table = new IBTOpenTradeTable(new IBTOpenTradeTableModel(trades));
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JButton x = new JButton("Import");
		x.addActionListener(actionListener);
		x.setActionCommand("Add");
		
		JButton y = new JButton("Reject");
		y.addActionListener(actionListener);
		y.setActionCommand("Reject");
		
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttons.add(x);
		buttons.add(y);
		tablePanel.add(buttons, BorderLayout.NORTH);
		
		frame.add(tablePanel);
		frame.revalidate();  
	}
	
	public void viewOpenTrades(List<IBTrade> trades) {
		JPanel panel = new JPanel(new BorderLayout());
		IBTOpenTradeTable table = new IBTOpenTradeTable(new IBTOpenTradeTableModel(trades));
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		panel.add(new JLabel("OPEN TRADES", SwingConstants.CENTER), BorderLayout.NORTH);
		frame.add(panel);
		frame.revalidate();
	}
	
	public void viewClosedTrades(List<IBTClosedTrade> trades){
		JPanel panel = new JPanel(new BorderLayout());
		IBTClosedTradeTable table = new IBTClosedTradeTable(new IBTClosedTradeTableModel(trades));
		panel.add(new JScrollPane(table));
		panel.add(new JLabel("CLOSED TRADES", SwingConstants.CENTER), BorderLayout.NORTH);
		frame.add(panel);
		frame.revalidate();
	}
	
	public JTable viewClosedTradesMap(List<IBTClosedTrade> trades, ListSelectionListener listener){
		JPanel panel = new JPanel(new BorderLayout());
		IBTClosedTradeTable table = new IBTClosedTradeTable(new IBTClosedTradeTableModel(trades));
		panel.add(new JScrollPane(table));
		panel.add(new JLabel("CLOSED TRADES", SwingConstants.CENTER), BorderLayout.NORTH);
		frame.add(panel);
		frame.revalidate();
		table.getSelectionModel().addListSelectionListener(listener);
		return table;
	}
	
	public void clearScreen() {
		frame.getContentPane().removeAll();
		//frame.revalidate();
		frame.getContentPane().repaint();
	}

}

