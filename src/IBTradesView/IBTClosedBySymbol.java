package IBTradesView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import IBTradesData.IBTClosedTrade;
import IBTradesData.IBTradeMapKey;

public class IBTClosedBySymbol extends JSplitPane implements MouseListener {

	private static final long serialVersionUID = 3262753299409354700L;
	Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap;
	
	public IBTClosedBySymbol(Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap) {
		super(JSplitPane.HORIZONTAL_SPLIT);
		this.tradeMap = tradeMap;
		setLeftComponent(new JScrollPane(new Symbols(tradeMap, this)));
		setRightComponent(new JLabel());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String symbol = ((JPanel)e.getSource()).getName();
		System.out.println("Clicked on " + symbol);
		setRightComponent(new JLabel(symbol));

		for (IBTradeMapKey key: tradeMap.keySet()) {
			if (key.getSymbol().equals(symbol)) {
				JTable table = new JTable(new IBTClosedTradeTableModel(tradeMap.get(key)));
				table.setAutoCreateRowSorter(true);
				table.setDefaultRenderer(Date.class, new IBTDateRenderer());
				table.setDefaultRenderer(Double.class, new IBTDoubleRenderer());
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(JLabel.CENTER);
				table.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
				setRightComponent(new JScrollPane(table));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JPanel panel = (JPanel)e.getSource();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		JPanel panel = (JPanel)e.getSource();
//		panel.setBorder(BorderFactory.createEmptyBorder());
		((JPanel)e.getSource()).setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class Symbols extends JPanel {
	
	private static final long serialVersionUID = -4726622113219425017L;

	public Symbols(Map<IBTradeMapKey, List<IBTClosedTrade>> tradeMap, MouseListener mouseListener) {
	
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Dimension dimension = new Dimension(75, 10);
		
		for(IBTradeMapKey key: tradeMap.keySet()){
			JPanel panel = new JPanel(new FlowLayout());

			JLabel symbol = new JLabel(key.getSymbol());
			symbol.setPreferredSize(dimension);
			panel.add(symbol);

			JLabel count = new JLabel(Integer.toString(key.getCount())) ;
			count.setPreferredSize(dimension);
			count.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(count);

			NumberFormat nf = NumberFormat.getCurrencyInstance();
			String string = nf.format(key.getNetCash());
			JLabel value = new JLabel(string);
			value.setPreferredSize(dimension);
			value.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(value);
			
			panel.setName(key.getSymbol());
			//panel.setName(key.toString());
			panel.addMouseListener(mouseListener);
			
			add(panel);
		}	
	}
}
