package gui.table;

import java.awt.BorderLayout;
import java.util.ArrayList;
import gui.tableModel.ProgressTableModel;
import model.Course;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProgressTable extends JPanel {
	public JTable table;
	private ProgressTableModel model;
	TableRowSorter<DefaultTableModel> tr;

	public ProgressTable() {
		model = new ProgressTableModel();
		table = new JTable(model);
		tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);

		table.setEnabled(true);
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public void setData(ArrayList<Course> list) {
		model.setData(list);
	}

	public void refresh() {
		model.fireTableDataChanged();
	}

	public void loadData() {
		model.loadData();
	}

	public void addData(Course c) {
		model.addRow(c);
	}

	public void deleteData(int i) {
		model.deleteData(i);
	}

	/*public void updateData(Course c) {
		model.updateData(c);
	}*/

	public ProgressTableModel getModel() {
		return model;
	}

	public JTable getTable() {
		return table;
	}

	public TableRowSorter<DefaultTableModel> getTr() {
		return tr;
	}

	public void setTr(TableRowSorter<DefaultTableModel> tr) {
		this.tr = tr;
	}

	public Course getCourse(int index) {
		return model.getCourse(index);
	}
}
