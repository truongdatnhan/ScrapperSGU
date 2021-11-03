package gui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import gui.table.GPATable;
import model.Course;
import java.util.ArrayList;

public class GPAPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	private JTextField txStudentID;
	private JTextField txName;
	private JTextField txClass;
	private JTextField txPOB;
	private JTextField txDepartment;
	private JTextField txFaculty;
	private JTextField txMajor;
	private JButton btnTailai, btnIn, btnExportExcel;
	private JRadioButton rbNu, rbNam;
	private JDateChooser dateChooser;
	private ButtonGroup groupGioitinh;
	public GPATable table;
	private JTextField txCourseYear;
	private JTextField txCounselor;

	public GPAPanel() {
		setBackground(Color.WHITE);
		setLayout(null);

		ArrayList<Course> cList = new ArrayList<>();
		cList.add(new Course("123456", "LTM", 3, 10, 4));

		JLabel lbStudentID = new JLabel("Mã sinh viên :");
		lbStudentID.setBounds(50, 30, 120, 25);
		add(lbStudentID);

		txStudentID = new JTextField();
		txStudentID.setEditable(false);
		txStudentID.setBounds(175, 30, 372, 25);
		add(txStudentID);
		txStudentID.setColumns(10);

		JLabel lbName = new JLabel("Họ và tên :");
		lbName.setBounds(50, 70, 114, 25);
		add(lbName);

		txName = new JTextField();
		txName.setEditable(false);
		txName.setBounds(175, 70, 185, 25);
		add(txName);
		txName.setColumns(10);

		JLabel lbClass = new JLabel("Lớp :");
		lbClass.setBounds(368, 69, 50, 25);
		add(lbClass);

		txClass = new JTextField();
		txClass.setEditable(false);
		txClass.setBounds(420, 71, 127, 26);
		add(txClass);
		txClass.setColumns(10);

		JLabel lbDOB = new JLabel("Ngày sinh :");
		lbDOB.setBounds(50, 110, 100, 25);
		add(lbDOB);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(175, 110, 372, 25);
		dateChooser.setEnabled(false);
		add(dateChooser);

		JLabel lbGioitinh = new JLabel("Giới tính :");
		lbGioitinh.setBounds(50, 150, 100, 25);
		add(lbGioitinh);

		rbNam = new JRadioButton("Nam");
		rbNam.setEnabled(false);
		rbNam.setHorizontalAlignment(SwingConstants.CENTER);
		rbNam.setBounds(197, 150, 92, 25);
		add(rbNam);

		rbNu = new JRadioButton("Nữ");
		rbNu.setEnabled(false);
		rbNu.setHorizontalAlignment(SwingConstants.CENTER);
		rbNu.setBounds(356, 150, 114, 25);
		add(rbNu);

		groupGioitinh = new ButtonGroup();
		groupGioitinh.add(rbNam);
		groupGioitinh.add(rbNu);

		JLabel lbPOB = new JLabel("Nơi sinh :");
		lbPOB.setBounds(624, 28, 70, 25);
		add(lbPOB);

		txPOB = new JTextField();
		txPOB.setEditable(false);
		txPOB.setBounds(746, 28, 284, 25);
		add(txPOB);
		txPOB.setColumns(10);

		JLabel lbDepartment = new JLabel("Khoa :");
		lbDepartment.setBounds(624, 68, 70, 25);
		add(lbDepartment);

		txDepartment = new JTextField();
		txDepartment.setEditable(false);
		txDepartment.setBounds(746, 68, 284, 25);
		add(txDepartment);
		txDepartment.setColumns(10);

		JLabel lbFaculty = new JLabel("Ngành :");
		lbFaculty.setBounds(624, 108, 120, 25);
		add(lbFaculty);

		txFaculty = new JTextField();
		txFaculty.setEditable(false);
		txFaculty.setBounds(746, 108, 284, 26);
		add(txFaculty);
		txFaculty.setColumns(10);

		JLabel lbMajor = new JLabel("Chuyên ngành :");
		lbMajor.setBounds(624, 148, 92, 25);
		add(lbMajor);

		txMajor = new JTextField();
		txMajor.setEditable(false);
		txMajor.setBounds(746, 148, 284, 25);
		add(txMajor);
		txMajor.setColumns(10);

		btnTailai = new JButton("Tải lại");
		btnTailai.setIcon(new ImageIcon("./icon/icons8_synchronize_32.png"));
		btnTailai.setBounds(197, 230, 145, 30);
		btnTailai.addActionListener(this);
		add(btnTailai);

		btnExportExcel = new JButton("Export Excel");
		btnExportExcel.setIcon(new ImageIcon("./icon/icons8_Microsoft_Excel_2019_32.png"));
		btnExportExcel.setBounds(489, 230, 145, 30);
		btnExportExcel.addActionListener(this);
		add(btnExportExcel);

		btnIn = new JButton("Export PDF");
		btnIn.setIcon(new ImageIcon("./icon/icons8_print_32.png"));
		btnIn.setBounds(746, 230, 145, 30);
		btnIn.addActionListener(this);
		add(btnIn);

		table = new GPATable();
		table.setData(cList);
		table.setBounds(5, 295, 1080, 370);
		add(table);

		JLabel lbCourseYear = new JLabel("Khoá học :");
		lbCourseYear.setBounds(50, 185, 120, 25);
		add(lbCourseYear);

		txCourseYear = new JTextField();
		txCourseYear.setEditable(false);
		txCourseYear.setColumns(10);
		txCourseYear.setBounds(175, 185, 372, 25);
		add(txCourseYear);

		JLabel lbCounselor = new JLabel("Cố vấn học tập :");
		lbCounselor.setBounds(624, 185, 92, 25);
		add(lbCounselor);

		txCounselor = new JTextField();
		txCounselor.setEditable(false);
		txCounselor.setColumns(10);
		txCounselor.setBounds(746, 185, 284, 25);
		add(txCounselor);
		table.loadData();

		/*
		 * table.getTable().addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent e) { int i =
		 * table.table.getSelectedRow(); if (i >= 0) { btnThem.setEnabled(false);
		 * txManv.setEditable(false); nhanvienDTO nv = new nhanvienDTO(); nv =
		 * table.getModel().getNV(i); txManv.setText(nv.getManv());
		 * txHo.setText(nv.getHo()); txTen.setText(nv.getTen()); try { Date ngaysinh =
		 * new SimpleDateFormat("yyyy-MM-dd").parse(nv.getNgaysinh());
		 * dateChooser.setDate(ngaysinh); } catch (ParseException e1) {
		 * System.out.println(e1); } if ("Nam".equals(nv.getGioitinh())) {
		 * rbNam.setSelected(true); } else if ("Nữ".equals(nv.getGioitinh())) {
		 * rbNu.setSelected(true); } txDiachi.setText(nv.getDiachi());
		 * txEmail.setText(nv.getEmail()); txSDT.setText(nv.getSdt());
		 * txLuong.setText(nv.getLuong()); } } });
		 */
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
