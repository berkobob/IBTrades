package IBTradesView;

import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class IBTDateRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;

	SimpleDateFormat tradeDateFormat = new SimpleDateFormat("dd-MM-yy");
	
	public IBTDateRenderer() {
		super();
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void setValue(Object value){		
		if (value != null) value = tradeDateFormat.format(value);
		super.setValue(value);
	}
}
