package IBTradesView;

import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class IBTDoubleRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public IBTDoubleRenderer() {
		super();
		setHorizontalAlignment(JLabel.RIGHT);
		setHorizontalTextPosition(SwingConstants.RIGHT);
	}

	public void setValue(Object value){
		if ((value instanceof Double) && value != null) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			value = nf.format(value);
		}
		super.setValue(value);
	}
}
