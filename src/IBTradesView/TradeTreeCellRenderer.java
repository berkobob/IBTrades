package IBTradesView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import IBTradesData.IBTClosedTrade;

public class TradeTreeCellRenderer implements TreeCellRenderer {
	
	Color background;

	public TradeTreeCellRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

		if (row==0) return new DefaultTreeCellRenderer().getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		
		
		//background = tree.getBackground();
		background = UIManager.getColor("Tree.textBackground");
		
		if (leaf) {	
			//return new Leaf(trade, background);
			@SuppressWarnings("unchecked")
			List<IBTClosedTrade> trades = (List<IBTClosedTrade>)node.getUserObject();
			return new aTable(trades);
		} else {
			String trade = (String)node.getUserObject();
			return new JLabel(trade);
		}
	}
}


class aTable extends JPanel{

	private static final long serialVersionUID = 1L;
	List<IBTClosedTrade> trades = new ArrayList<IBTClosedTrade>();
	
	aTable(List<IBTClosedTrade> trades) {
		JTable table = new JTable(new IBTClosedTradeTableModel(trades));
		table.setAutoCreateRowSorter(true);
		table.setDefaultRenderer(Date.class, new IBTDateRenderer());
		table.setDefaultRenderer(Double.class, new IBTDoubleRenderer());
		//table.getColumnModel().setColumnMargin(15);
		add(new JScrollPane(table));
	}
	
}

class Leaf extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int rowHeight = 10;
	final int colWidth = 100;
	
	Leaf(IBTClosedTrade t, Color colour) {
		setLayout(new FlowLayout());
		setBackground(colour);
		
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		SimpleDateFormat tradeDateFormat = new SimpleDateFormat("dd-MM-yy");
		
		add(newLabel(tradeDateFormat.format(t.getStartDate()),  JLabel.CENTER));
		add(newLabel(Integer.toString(t.getQuantity()),         JLabel.CENTER));
		add(newLabel(tradeDateFormat.format(t.getEndDate()),    JLabel.CENTER));
		
		add(newLabel(decimalFormat.format(t.getProceeds()),     JLabel.CENTER));
		add(newLabel(decimalFormat.format(t.getCommission()),   JLabel.CENTER));
		add(newLabel(decimalFormat.format(t.getNetCash()),      JLabel.CENTER));
		
		if (t.getAssetClass().equals("Option")) {
			add(newLabel(tradeDateFormat.format(t.getExpiry()), JLabel.CENTER));
			add(newLabel(decimalFormat.format(t.getStrike()),   JLabel.CENTER));
			add(newLabel(t.getPutOrCall(),                      JLabel.CENTER));
		}
	}
	
	private JLabel newLabel(String label, int pos) {
		JLabel l = new JLabel(label);
		l.setPreferredSize(new Dimension(colWidth,rowHeight));
		l.setHorizontalAlignment(pos);
		return l;
	}
}
