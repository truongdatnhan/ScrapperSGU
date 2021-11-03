package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import gui.table.ProgressTable;

public class ProgressRemainTab extends JPanel {

	public ProgressTable table;
	
	public ProgressRemainTab() {
		setLayout(new BorderLayout());
		table = new ProgressTable();
		
		add(table,BorderLayout.CENTER);
	}
	
}
