package gui.tableModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import model.Course;

public class ProgressTableModel extends DefaultTableModel {

    public List<Course> courses;
    private static String[] colName = {"STT","Mã môn","Tên môn","Số tín chỉ"};

    public ProgressTableModel() {
        super(colName, 0);
    }

    public void setData(List<Course> list) {
        this.courses = list;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void loadData() {
        deleteAll();
        int i=1;
        for (Course c : courses) {
            Vector<String> row = new Vector<String>();
            row.add(Integer.toString(i++));
            row.add(c.getCourseID());
            row.add(c.getCourseName());
            row.add(String.valueOf(c.getCourseCredit()));
            super.addRow(row);
        }
    }

    public void addRow(Course c) {
        if (c == null) {
            throw new IllegalArgumentException("NULL");
        }
        courses.add(c);
        Vector<String> row = new Vector<String>();
        row.add(Integer.toString(courses.size()));
        row.add(c.getCourseID());
        row.add(c.getCourseName());
        row.add(String.valueOf(c.getCourseCredit()));
        super.addRow(row);
    }

    public void deleteData(int i) {
        super.removeRow(i);
    }

    /*public void updateData(phieumuonDTO pm, int i) {
        String[] temp = new String[]{pm.getManv(), pm.getMathe(), pm.getNgaymuon(),
            pm.getNgayquidinhtra()};
        for (int j = 0; j < colName.length - 2; j++) {
            super.setValueAt(temp[j], i, j + 1);
        }
    }*/

    public void deleteAll() {
        if (super.getRowCount() > 0) {
            for (int i = super.getRowCount() - 1; i > -1; i--) {
                super.removeRow(i);
            }
        }
    }

    public Course getCourse(int index) {
        return courses.get(index);
    }
}
