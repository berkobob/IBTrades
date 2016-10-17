package IBTradesView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import IBTradesData.IBTClosedTrade;
import IBTradesData.IBTradeMapKey;



public class IBTradeTreeCellRenderer implements TreeCellRenderer {
	
	Color background;

	@SuppressWarnings("unchecked")
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

		// Make sure the node 'top' appears as normal
		if (row==0) return new DefaultTreeCellRenderer().getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

		background = UIManager.getColor("Tree.textBackground");
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		if (leaf) {
			//Map<IBTradeMapKey, List<IBTClosedTrade>> symbol = (Map<IBTradeMapKey, List<IBTClosedTrade>>)node.getUserObject();
			List<IBTClosedTrade> trades = (List<IBTClosedTrade>)node.getUserObject();
			return new JScrollPane(new Table(trades));
		} else {
			IBTradeMapKey key = (IBTradeMapKey)node.getUserObject();
			return new Symbol(key, background);
		}
	}

}


@SuppressWarnings("serial")
class Symbol extends JPanel {
	
	Symbol (IBTradeMapKey key, Color background){
		Dimension dimension = new Dimension(75, 10);
		setBackground(background);
		
		JLabel symbol = new JLabel(key.getSymbol());
		symbol.setPreferredSize(dimension);
		add(symbol);
		
		JLabel count = new JLabel(Integer.toString(key.getCount())) ;
		count.setPreferredSize(dimension);
		count.setHorizontalAlignment(SwingConstants.CENTER);
		add(count);
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String string = nf.format(key.getNetCash());
		JLabel value = new JLabel(string);
		value.setPreferredSize(dimension);
		value.setHorizontalAlignment(SwingConstants.RIGHT);
		add(value);
	}
}


class Table extends JTable {

	private static final long serialVersionUID = 1L;

	Table(List<IBTClosedTrade> trades) {
		super(new IBTClosedTradeTableModel(trades));
		setAutoCreateRowSorter(true);
		setDefaultRenderer(Date.class, new IBTDateRenderer());
		setDefaultRenderer(Double.class, new IBTDoubleRenderer());
	}
}
