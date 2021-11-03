package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ProgressGraphTab extends JPanel {
	
	public ProgressGraphTab() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		
		//Tạo graph xong chỉ cần thay null add vào
		add(null,BorderLayout.CENTER);
	}
	
}
