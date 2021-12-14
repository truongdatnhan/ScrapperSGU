package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Student;

public class ProgressPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	public JTabbedPane tabbedPane;
	public JLabel lbProgerss;
	public ProgressGraphTab tab1;
	public ProgressRemainTab tab2;
	public ProgressPanel(Student student) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tab1 = new ProgressGraphTab(student);
		tabbedPane.addTab("Biểu đồ", null, tab1, null);
		tab2 = new ProgressRemainTab(student);
		tabbedPane.addTab("Các môn học còn lại", null, tab2, null);
		add(tabbedPane,BorderLayout.CENTER);
		
		lbProgerss = new JLabel("<html><span style='font-size:20px'>"+"Hãy tìm mã số sinh viên để biết kết quả"+"</span></html>");
		if(student != null) {
			//Làm tròn số sau dấu phẩy
			DecimalFormat df = new DecimalFormat("#.##");
			float percent = ((float) student.getCurrentCredit() /150)*100;
			lbProgerss = new JLabel("<html><span style='font-size:20px'>"+"Bạn đã hoàn thành "+ df.format(percent) +"% học phần"+"</span></html>");
			if(!student.getFaculty().equals("Công nghệ thông tin")) {
				lbProgerss = new JLabel("<html><span style='font-size:20px'>"+"Tính năng này chỉ hoạt động đúng với sinh viên ngành CNTT"+"</span></html>");
				
			}
		}
		add(lbProgerss,BorderLayout.PAGE_END);
		
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
