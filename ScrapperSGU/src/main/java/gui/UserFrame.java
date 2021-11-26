package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.StringUtils;

import gui.panel.GPAPanel;
import gui.panel.ProgressPanel;
import gui.panel.RankingPanel;
import model.Course;
import model.ListMonHoc;
import model.Student;
import tool.Design;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserFrame extends JFrame implements MouseListener {

	public int i = 0;
	public JPanel mainPanel;
	public JPanel leftPanel, headLeft, bodyLeft;
	public JScrollPane scrollpane;
	public JPanel header, centerPanel;
	public JPanel gpaPanel, progressPanel, rankingPanel;
	public JLabel lbGPA, lbProgress, lbRanking;
	public JLabel lbLogo;
	public JButton btnSearch;
	public JTextField mssvField;
	public JPanel insideCenter;
	public String ipAddress;
	public int port;
	public Student student = null;

	public UserFrame() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1280, 720);

		setLocationRelativeTo(null);
		setTitle("Quản lí tiến độ học tập");
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);

		ipAddress = "127.0.0.1";
		port = 5000;

		leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(Color.WHITE, 0, true));
		leftPanel.setPreferredSize(new Dimension(180, 650));
		leftPanel.setBackground(new Color(122, 172, 240));
		leftPanel.setLayout(null);
		mainPanel.add(leftPanel, BorderLayout.WEST);

		gpaPanel = new JPanel();
		gpaPanel.setBounds(0, 100, 180, 40);
		gpaPanel.setBackground(new Color(45, 118, 232));
		gpaPanel.addMouseListener(this);
		leftPanel.add(gpaPanel);

		lbGPA = new JLabel("GPA");
		lbGPA.setHorizontalAlignment(SwingConstants.CENTER);
		lbGPA.setForeground(Color.WHITE);
		lbGPA.setBounds(0, 0, 180, 40);
		ImageIcon iconS = Design.resizeIcon("./icon/icons8_book_64.png", gpaPanel.getWidth() / 3,
				(int) (gpaPanel.getHeight() * 1.4));
		gpaPanel.setLayout(null);
		lbGPA.setIcon(iconS);
		gpaPanel.add(lbGPA);
		// 0,y+45,200,40
		progressPanel = new JPanel();
		progressPanel.setBounds(0, 145, 180, 40);
		progressPanel.setBackground(new Color(45, 118, 232));
		progressPanel.addMouseListener(this);
		leftPanel.add(progressPanel);

		lbProgress = new JLabel("Tiến độ học tập");
		lbProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lbProgress.setForeground(Color.WHITE);
		lbProgress.setBounds(0, 0, 180, 40);
		lbProgress.addMouseListener(this);
		ImageIcon iconP = Design.resizeIcon("./icon/icons8_Bill_64.png", progressPanel.getWidth() / 3,
				(int) (progressPanel.getHeight() * 1.5));
		progressPanel.setLayout(null);
		lbProgress.setIcon(iconP);
		progressPanel.add(lbProgress);

		lbLogo = new JLabel("");
		lbLogo.setBounds(54, 14, 70, 70);
		leftPanel.add(lbLogo);

		ImageIcon logo = Design.resizeIcon("./icon/book1.png", lbLogo.getWidth(), lbLogo.getHeight());
		lbLogo.setIcon(logo);

		header = new JPanel();
		header.setPreferredSize(new Dimension(1200, 40));
		header.setBackground(new Color(45, 118, 232));
		mainPanel.add(header, BorderLayout.NORTH);
		header.setLayout(null);

		mssvField = new JTextField();
		mssvField.setBounds(180, 10, 290, 20);
		header.add(mssvField);
		mssvField.setColumns(10);

		btnSearch = new JButton("Tìm");
		btnSearch.setBounds(480, 9, 85, 21);
		btnSearch.addMouseListener(this);
		header.add(btnSearch);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		ImageIcon iconPN = Design.resizeIcon("./icon/icons8_Bill_64.png", progressPanel.getWidth() / 3,
				(int) (progressPanel.getHeight() * 1.5));
		ImageIcon iconPM = Design.resizeIcon("./icon/icons8_Bill_64.png", progressPanel.getWidth() / 3,
				(int) (progressPanel.getHeight() * 1.5));
		ImageIcon iconPP = Design.resizeIcon("./icon/icons8_Bill_64.png", progressPanel.getWidth() / 3,
				(int) (progressPanel.getHeight() * 1.5));

		rankingPanel = new JPanel();
		rankingPanel.setLayout(null);
		rankingPanel.setBackground(new Color(45, 118, 232));
		rankingPanel.setBounds(0, 190, 180, 40);
		rankingPanel.addMouseListener(this);
		leftPanel.add(rankingPanel);

		lbRanking = new JLabel("Xếp hạng");
		lbRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lbRanking.setForeground(Color.WHITE);
		lbRanking.setBounds(0, 0, 180, 40);
		lbRanking.addMouseListener(this);
		rankingPanel.add(lbRanking);

		insideCenter = new GPAPanel(student);
		centerPanel.add(insideCenter, BorderLayout.CENTER);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == gpaPanel || e.getSource() == lbGPA) {
			centerPanel.removeAll();
			insideCenter = new GPAPanel(student);
			centerPanel.add(insideCenter, BorderLayout.CENTER);
			centerPanel.repaint();
			centerPanel.revalidate();
		}
		if (e.getSource() == progressPanel || e.getSource() == lbProgress) {
			centerPanel.removeAll();
			insideCenter = new ProgressPanel(student);
			centerPanel.add(insideCenter, BorderLayout.CENTER);
			centerPanel.repaint();
			centerPanel.revalidate();
		}
		if (e.getSource() == rankingPanel || e.getSource() == lbRanking) {
			centerPanel.removeAll();
			insideCenter = new RankingPanel();
			centerPanel.add(insideCenter, BorderLayout.CENTER);
			centerPanel.repaint();
			centerPanel.revalidate();
		}
		if (e.getSource() == btnSearch) {

			try (Socket socket = new Socket(ipAddress, port);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					ObjectInputStream mapInputStream = new ObjectInputStream(socket.getInputStream());) {
				System.out.println("Connected");
				out.write(mssvField.getText());
				out.newLine();
				out.flush();
				Map<String, List<String>>[] map = (LinkedHashMap<String, List<String>>[]) mapInputStream.readObject();
				List<Course> courses = new ArrayList<>();
				Map<String, List<Float>> markMap = new LinkedHashMap<>();
				String[] array_TheChat = { "BODA", "BOBA", "BOCH", "CALO", "BORO" };
				List<Course> remain = new ArrayList<>();
				
				if(map != null) {
					
					map[0].values().forEach(x -> {
						student = new Student();
						student.setId(x.get(0));
						student.setName(x.get(1));
						student.setSex(x.get(2));
						student.setPob(x.get(3));
						student.setUniClass(StringUtils.substringBefore(x.get(4), "("));
						student.setDepartment(x.get(5));
						student.setMajor(x.get(6));
						student.setFaculty(x.get(7));
						student.setCourseYear(Integer.parseInt(x.get(9).substring(0, 4)));
						student.setCourseDuration(x.get(9));
						student.setCounselor(x.get(10));
					});
					
					map[1].values().forEach(x -> {
						for (var course : x) {
							String[] splits = course.split("\\|");
							Course c = new Course();
							
							if(splits[0].equals("KSTA60")) {
								continue;
							}
							
							if(splits[0].equals("841324")) {
								c.setCourseID("868001");
								c.setCourseName(splits[1]);
								c.setCourseCredit(Integer.parseInt(splits[2]));
								c.setGradeBase10( splits[3].isEmpty() ? 0 : Float.parseFloat(splits[3]));
								c.setGradeBase4(splits[3].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
								c.setPass(splits[5].equals("Đạt") ? true : false);
								courses.add(c);
								continue;
							}
								
							if (Arrays.stream(array_TheChat).anyMatch(b -> splits[0].startsWith(b))) {
								if (!courses.stream().anyMatch(b -> b.getCourseID().equals("862102"))) {
									c.setCourseID("862102");
									c.setCourseName("Giáo dục thểchất (II)");
									c.setCourseCredit(Integer.parseInt(splits[2]));
									c.setGradeBase10( splits[3].isEmpty() ? 0 : Float.parseFloat(splits[3]));
									c.setGradeBase4(splits[3].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
									c.setPass(splits[5].equals("Đạt") ? true : false);
									courses.add(c);
									continue;
								}
								c.setCourseID("862103");
								c.setCourseName("Giáo dục thểchất (III)");
								c.setCourseCredit(Integer.parseInt(splits[2]));
								c.setGradeBase10( splits[3].isEmpty() ? 0 : Float.parseFloat(splits[3]));
								c.setGradeBase4(splits[3].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
								c.setPass(splits[5].equals("Đạt") ? true : false);
								courses.add(c);
								continue;
							}
							
							c.setCourseID(splits[0]);
							c.setCourseName(splits[1]);
							c.setCourseCredit(Integer.parseInt(splits[2]));
							c.setGradeBase10( splits[3].isEmpty() ? 0 : Float.parseFloat(splits[3]));
							c.setGradeBase4(splits[3].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
							c.setPass(splits[5].equals("Đạt") ? true : false);
							courses.add(c);
						}
					});
					
					map[2].forEach((k,v) -> {
						if(v.isEmpty())
							return;
						List<Float> diem = new ArrayList<Float>();
						v.forEach(d -> diem.add(Float.parseFloat(d)));
						markMap.put(k, diem);
					});
					
					ListMonHoc.MonHocs.forEach((k,v) -> {
						if(!courses.stream().anyMatch(n -> n.getCourseID().equals(String.valueOf(k)))) {
							remain.add(v);
						}
						
					});
					
				} else {
					
				}
				
				/*for (var entry : map.entrySet()) {
					System.out.println(entry.getKey());

					if (entry.getKey().equals("Error")) {
						student = null;
						break;
					}
					if (entry.getKey().equals("Thông tin sinh viên")) {
						student = new Student();
						student.setId(entry.getValue().get(0));
						student.setName(entry.getValue().get(1));
						student.setSex(entry.getValue().get(2));
						student.setPob(entry.getValue().get(3));
						student.setUniClass(StringUtils.substringBefore(entry.getValue().get(4), "("));
						student.setDepartment(entry.getValue().get(5));
						student.setMajor(entry.getValue().get(6));
						student.setFaculty(entry.getValue().get(7));
						student.setCourseYear(Integer.parseInt(entry.getValue().get(9).substring(0, 4)));
						student.setCourseDuration(entry.getValue().get(9));
						student.setCounselor(entry.getValue().get(10));
						continue;
					}

					if (entry.getKey().startsWith("Điểm")) {
						if(entry.getValue() == null)
							continue;
						List<Float> diem = new ArrayList<Float>();
						entry.getValue().forEach(x -> diem.add(Float.parseFloat(x)));
						markMap.put(StringUtils.substringAfter(entry.getKey(), " "), diem);
						continue;
					}

					for (var course : entry.getValue()) {
						String[] splits = course.split("\\|");
						Course c = new Course();
						
						if(splits[0].equals("KSTA60")) {
							continue;
						}
							
						if (Arrays.stream(array_TheChat).anyMatch(x -> splits[0].startsWith(x))) {
							if (!courses.stream().anyMatch(x -> x.getCourseID().equals("862102"))) {
								c.setCourseID("862102");
								c.setCourseName("Giáo dục thểchất (II)");
								c.setCourseCredit(Integer.parseInt(splits[2]));
								c.setGradeBase10( splits[3].isEmpty() ? 0 : Float.parseFloat(splits[3]));
								c.setGradeBase4(splits[3].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
								c.setPass(splits[5].equals("Đạt") ? true : false);
								courses.add(c);
								continue;
							}
							c.setCourseID("862103");
							c.setCourseName("Giáo dục thểchất (III)");
							c.setCourseCredit(Integer.parseInt(splits[2]));
							c.setGradeBase10( splits[3].isEmpty() ? 0 : Float.parseFloat(splits[3]));
							c.setGradeBase4(splits[3].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
							c.setPass(splits[5].equals("Đạt") ? true : false);
							courses.add(c);
							continue;
						}
						c.setCourseID(splits[0]);
						c.setCourseName(splits[1]);
						c.setCourseCredit(Integer.parseInt(splits[2]));
						c.setGradeBase10( splits[3].isEmpty() ? 0 : Float.parseFloat(splits[3]));
						c.setGradeBase4(splits[3].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
						c.setPass(splits[5].equals("Đạt") ? true : false);
						courses.add(c);
					}

				}*/
				student.setCourses(courses);
				student.setMarkMap(markMap);
				student.setRemainCourses(remain);
				
				centerPanel.removeAll();
				insideCenter = new GPAPanel(student);
				centerPanel.add(insideCenter, BorderLayout.CENTER);
				centerPanel.repaint();
				centerPanel.revalidate();
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
