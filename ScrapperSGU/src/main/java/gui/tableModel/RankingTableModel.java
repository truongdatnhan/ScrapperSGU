package gui.tableModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.Ranking;

public class RankingTableModel extends DefaultTableModel {

    public List<Ranking> students;
    private static String[] colName = {"STT","Mã sinh viên","Tên sinh viên","Khoa","Khoá","Ngành","Điểm","Tra Cứu"};

    public RankingTableModel() {
        super(colName, 0);
    }

    public void setData(List<Ranking> rank) {
        this.students = rank;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void loadData() {
        deleteAll();
        int i=1;
        for (Ranking s : students) {
            Vector<String> row = new Vector<String>();
            row.add(Integer.toString(s.getStt()));
            row.add(s.getId());
            row.add(s.getName());
            row.add(s.getDepartment());
            row.add(Integer.toString(s.getCourseYear()));
            row.add(s.getFaculty());
            row.add(Float.toString(s.getDiem()));
            row.add("Tra cứu");
            super.addRow(row);
        }
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

}
