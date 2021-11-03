package gui.tableModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import model.Student;

public class RankingTableModel extends DefaultTableModel {

    public ArrayList<Student> students;
    private static String[] colName = {"STT","Mã sinh viên","Tên sinh viên","Khoa","Khoá","Ngành","Tra Cứu"};

    public RankingTableModel() {
        super(colName, 0);
    }

    public void setData(ArrayList<Student> list) {
        this.students = list;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void loadData() {
        deleteAll();
        int i=1;
        for (Student s : students) {
            Vector<String> row = new Vector<String>();
            row.add(Integer.toString(i++));
            row.add(s.getId());
            row.add(s.getName());
            row.add(s.getDepartment());
            row.add(Integer.toString(s.getCourseYear()));
            row.add(s.getFaculty());
            row.add("Tra cứu");
            super.addRow(row);
        }
    }

    public void addRow(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("NULL");
        }
        students.add(s);
        Vector<String> row = new Vector<String>();
        row.add(Integer.toString(students.size()));
        row.add(s.getId());
        row.add(s.getName());
        row.add(s.getDepartment());
        row.add(Integer.toString(s.getCourseYear()));
        row.add(s.getFaculty());
        row.add("Tra cứu");
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

    public Student getStudent(int index) {
        return students.get(index);
    }
}
