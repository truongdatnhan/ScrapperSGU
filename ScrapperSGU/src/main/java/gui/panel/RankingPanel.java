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
import model.Ranking;
import model.Student;
import net.miginfocom.swing.MigLayout;
import tool.CheckBoxListener;
import tool.TraCuuListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JToggleButton;

public class RankingPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	public RankingTable table;
	public JCheckBox departmentCheck,yearCheck,facultyCheck;
	public JLabel lbDepartment,lbYear,lbFaculty;
	public CheckBoxListener checkBoxListener;
	
	public RankingPanel(boolean department, boolean year, boolean faculty, List<Ranking> rank) {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("","[grow,push,fill]", "[][grow]"));
		
		
		lbDepartment = new JLabel("Khoa: ");
		lbDepartment.setHorizontalAlignment(JLabel.CENTER);
		add(lbDepartment,"split 2");
		departmentCheck = new JCheckBox();
		departmentCheck.setSelected(department);
		add(departmentCheck);
		
		departmentCheck.addActionListener((e) -> {
			checkBoxListener.setBoolValue("department", departmentCheck.isSelected());
		});
		
		lbYear = new JLabel("Khoá: ");
		lbYear.setHorizontalAlignment(JLabel.CENTER);
		add(lbYear,"split 2");
		yearCheck = new JCheckBox();
		yearCheck.setSelected(year);
		add(yearCheck);
		yearCheck.addActionListener((e) -> {
			checkBoxListener.setBoolValue("year", yearCheck.isSelected());
		});
		
		lbFaculty = new JLabel("Ngành: ");
		lbFaculty.setHorizontalAlignment(JLabel.CENTER);
		add(lbFaculty,"split 2");
		facultyCheck = new JCheckBox();
		facultyCheck.setSelected(faculty);
		add(facultyCheck,"wrap");
		facultyCheck.addActionListener((e) -> {
			checkBoxListener.setBoolValue("faculty", facultyCheck.isSelected());
		});
		
		table = new RankingTable();
		
		if(rank != null) {
			table.setData(rank);
			table.loadData();
		}
		add(table,"span,grow");
		
	}

	public RankingTable getTable() {
		return table;
	}

	public void setTable(RankingTable table) {
		this.table = table;
	}

	public CheckBoxListener getCheckBoxListener() {
		return checkBoxListener;
	}

	public void setCheckBoxListener(CheckBoxListener checkBoxListener) {
		this.checkBoxListener = checkBoxListener;
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
