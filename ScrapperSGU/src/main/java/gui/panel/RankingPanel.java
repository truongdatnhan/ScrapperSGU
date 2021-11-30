package gui.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import gui.table.GPATable;
import gui.table.RankingTable;
import model.Course;
import model.Student;
import net.miginfocom.swing.MigLayout;
import tool.TraCuuListener;

import java.util.ArrayList;
import javax.swing.JToggleButton;

public class RankingPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	public RankingTable table;
	public JCheckBox departmentCheck,yearCheck,facultyCheck;
	public JLabel lbDepartment,lbYear,lbFaculty;
	
	public RankingPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("","[grow,push,fill]", "[][grow]"));
		
		
		lbDepartment = new JLabel("Khoa: ");
		lbDepartment.setHorizontalAlignment(JLabel.CENTER);
		add(lbDepartment,"split 2");
		departmentCheck = new JCheckBox();
		add(departmentCheck);
		
		lbYear = new JLabel("Khoá: ");
		lbYear.setHorizontalAlignment(JLabel.CENTER);
		add(lbYear,"split 2");
		yearCheck = new JCheckBox();
		add(yearCheck);
		
		lbFaculty = new JLabel("Ngành: ");
		lbFaculty.setHorizontalAlignment(JLabel.CENTER);
		add(lbFaculty,"split 2");
		facultyCheck = new JCheckBox();
		add(facultyCheck,"wrap");
		
		table = new RankingTable();
		ArrayList<Student> sList = new ArrayList<>();
		Student student = new Student();
		student.setId("123");
		student.setName("CC");
		student.setDepartment("CC");
		student.setCourseYear(2018);
		student.setFaculty("CC");
		
		sList.add(student);
		table.setData(sList);
		add(table,"span,grow");
		table.loadData();
	}

	public RankingTable getTable() {
		return table;
	}

	public void setTable(RankingTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

	}

	@Override
	public void keyPressed(KeyEvent evt) {
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
