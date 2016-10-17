package IBTradesView;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import IBTradesData.IBTClosedTrade;
import IBTradesData.IBTradeMapKey;

public class IBTradeTree extends JPanel {

	private static final long serialVersionUID = -5621017267503035572L;
	private List<IBTClosedTrade> closedTrades;
	private Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap;	

	public IBTradeTree(List<IBTClosedTrade> closedTrades) {
		TradeTreeCellRenderer treeCellRenderer = new TradeTreeCellRenderer();
		this.closedTrades = closedTrades;
		JTree tradeTree = new JTree(createTableTree());
		tradeTree.setCellRenderer(treeCellRenderer);
		setLayout(new BorderLayout());
		add(new JScrollPane(tradeTree), BorderLayout.CENTER);
		add(new JLabel("CLOSED TRADES", SwingConstants.CENTER), BorderLayout.NORTH);
	}
	
	public IBTradeTree(Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap) {
		IBTradeTreeCellRenderer treeCellRenderer = new IBTradeTreeCellRenderer();
		this.tradeMap = tradeMap;
		JTree tradeMapTree = new JTree(createTableTreeMap());
		tradeMapTree.setCellRenderer(treeCellRenderer);
		setLayout(new BorderLayout());
		add(new JScrollPane(tradeMapTree), BorderLayout.CENTER);
		add(new JLabel("CLOSED TRADES", SwingConstants.CENTER), BorderLayout.NORTH);
	}
	
	private DefaultMutableTreeNode createTableTreeMap() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode();
		
		for (IBTradeMapKey key: tradeMap.keySet()) {
			DefaultMutableTreeNode branch = new DefaultMutableTreeNode(key);
//			for (IBTClosedTrade trade: tradeMap.get(key)) 
//				branch.add(new DefaultMutableTreeNode(trade));
			branch.add(new DefaultMutableTreeNode(tradeMap.get(key)));
			top.add(branch);
		}
		return top;
	}
	
	private DefaultMutableTreeNode createTableTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode();
		List<IBTClosedTrade> trades = new ArrayList<IBTClosedTrade>();
		DefaultMutableTreeNode branch = null;   
		String symbol = "";
		
		for (IBTClosedTrade t: closedTrades) {
			if (symbol.equals(t.getSymbol())) {
				trades.add(t);
			}
			else {
				if (trades.size() > 0)
					branch.add(new DefaultMutableTreeNode(trades));
				branch = new DefaultMutableTreeNode(t.getSymbol());
				top.add(branch);
				trades = new ArrayList<IBTClosedTrade>();
				trades.add(t);
			}
			symbol = t.getSymbol();
		}
		
		branch.add(new DefaultMutableTreeNode(trades));
		
		return top;
	}
}
