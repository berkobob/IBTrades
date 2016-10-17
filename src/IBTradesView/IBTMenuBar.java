package IBTradesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class IBTMenuBar extends JMenuBar {

	private static final long serialVersionUID = -8426748219623502007L;
	private ActionListener actionListener = null;
	
	public IBTMenuBar(ActionListener actionListener) {
		
		this.actionListener = actionListener;
		
		/*
		 * FILE MENU
		 */
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		add(fileMenu);
		
		fileMenu.add(menuItem(new JMenuItem(), "Import", KeyEvent.VK_I));
		fileMenu.add(menuItem(new JMenuItem(), "Delete", KeyEvent.VK_D));
		fileMenu.add(menuItem(new JMenuItem(), "Sort",   KeyEvent.VK_S));
		fileMenu.add(menuItem(new JMenuItem(), "Exit",   KeyEvent.VK_E));
		
		/*
		 * VIEW MENU
		 */
		JMenu viewMenu = new JMenu("View");
		fileMenu.setMnemonic(KeyEvent.VK_V);
		add(viewMenu);
		
		viewMenu.add(menuItem(new JMenuItem(), "All",    KeyEvent.VK_A));
		viewMenu.add(menuItem(new JMenuItem(), "Open",   KeyEvent.VK_O));
		//viewMenu.add(menuItem(new JMenuItem(), "Closed", KeyEvent.VK_C));
		JMenu closedMenu = new JMenu("Closed");
		closedMenu.setMnemonic(KeyEvent.VK_C);
		closedMenu.add(menuItem(new JMenuItem(), "Table", KeyEvent.VK_T));
		closedMenu.add(menuItem(new JMenuItem(), "Tree",  KeyEvent.VK_R));
		viewMenu.add(closedMenu);
		viewMenu.add(menuItem(new JMenuItem(), "Clear",  KeyEvent.VK_X));
		}

	
/*
 * INTERNAL METHODS	
 */
	
	private JMenuItem menuItem(JMenuItem menuItem, String cmd, int key) {
		menuItem.setText(cmd);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(key, ActionEvent.ALT_MASK));
		menuItem.setActionCommand(cmd);
		menuItem.addActionListener(actionListener);
		return menuItem;
	}
	
	
}
