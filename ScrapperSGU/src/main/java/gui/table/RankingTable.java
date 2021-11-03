package gui.table;

import java.awt.BorderLayout;
import java.util.ArrayList;
import gui.tableModel.GPATableModel;
import gui.tableModel.RankingTableModel;
import model.Course;
import model.Student;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class RankingTable extends JPanel {
	public JTable table;
	private RankingTableModel model;
	TableRowSorter<DefaultTableModel> tr;

	public RankingTable() {
		model = new RankingTableModel();
		table = new JTable(model);
		tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);

		table.setEnabled(true);
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public void setData(ArrayList<Student> list) {
		model.setData(list);
	}

	public void refresh() {
		model.fireTableDataChanged();
	}

	public void loadData() {
		model.loadData();
	}

	public void addData(Student s) {
		model.addRow(s);
	}

	public void deleteData(int i) {
		model.deleteData(i);
	}

	/*public void updateData(Course c) {
		model.updateData(c);
	}*/

	public RankingTableModel getModel() {
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

	public Student getStudent(int index) {
		return model.getStudent(index);
	}
}
