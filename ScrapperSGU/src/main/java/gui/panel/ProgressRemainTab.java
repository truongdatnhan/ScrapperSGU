package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import gui.table.ProgressTable;
import model.Student;

public class ProgressRemainTab extends JPanel {

	public ProgressTable table;
	
	public ProgressRemainTab(Student student) {
		setLayout(new BorderLayout());
		table = new ProgressTable();
		add(table,BorderLayout.CENTER);
		if(student != null) {
			table.setData(student.getRemainCourses());
			table.loadData();
		}
	}
	
}
