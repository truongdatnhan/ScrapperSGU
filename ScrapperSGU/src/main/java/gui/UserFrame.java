package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.StringUtils;

import gui.panel.GPAPanel;
import gui.panel.ProgressPanel;
import gui.panel.RankingPanel;
import model.Course;
import model.ListMonHoc;
import model.Ranking;
import model.Student;
import tool.TraCuuListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextField;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;
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
	public TraCuuListener traCuuListener;
	public int port;
	public Student student = null;
	public boolean department = false;
	public boolean year = false;
	public boolean faculty = false;
	public List<Ranking> rank = null;

	public UserFrame() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1280, 720);

		setLocationRelativeTo(null);
		setTitle("Qu???n l?? ti???n ????? h???c t???p");
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
		gpaPanel.setLayout(null);
		gpaPanel.add(lbGPA);
		// 0,y+45,200,40
		progressPanel = new JPanel();
		progressPanel.setBounds(0, 145, 180, 40);
		progressPanel.setBackground(new Color(45, 118, 232));
		progressPanel.addMouseListener(this);
		leftPanel.add(progressPanel);

		lbProgress = new JLabel("Ti???n ????? h???c t???p");
		lbProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lbProgress.setForeground(Color.WHITE);
		lbProgress.setBounds(0, 0, 180, 40);
		lbProgress.addMouseListener(this);
		progressPanel.setLayout(null);
		progressPanel.add(lbProgress);

		lbLogo = new JLabel("");
		lbLogo.setBounds(54, 14, 70, 70);
		leftPanel.add(lbLogo);

		header = new JPanel();
		header.setPreferredSize(new Dimension(1200, 40));
		header.setBackground(new Color(45, 118, 232));
		mainPanel.add(header, BorderLayout.NORTH);
		header.setLayout(null);

		mssvField = new JTextField();
		mssvField.setBounds(180, 10, 290, 20);
		header.add(mssvField);
		mssvField.setColumns(10);

		btnSearch = new JButton("T??m");
		btnSearch.setBounds(480, 9, 85, 21);
		btnSearch.addMouseListener(this);
		btnSearch.addActionListener((e) -> {
			
			//Try-with-Resource
			try (Socket socket = new Socket(ipAddress, port);
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					) {
				System.out.println("Connected");

				//T???o c???p kho?? RSA
				KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
				generator.initialize(1024);
				KeyPair pair = generator.generateKeyPair();

				PrivateKey privateKey = pair.getPrivate();
				PublicKey publicKey = pair.getPublic();
			    
				//G???i publicKey l??n serevr
				out.writeObject(publicKey);
				out.flush();
				
				//N???i chu???i MSSV v?? c??c option ????? l???c ranking
				StringBuilder sb = new StringBuilder();
				sb.append(mssvField.getText()).append("|")
				.append(String.valueOf(department)).append("|")
				.append(String.valueOf(year)).append("|")
				.append(String.valueOf(faculty));
				//Send qua server
				out.writeUTF(sb.toString());
				//Ph???i flush th?? d??? li???u m???i ??i
				out.flush();
				
				//T???o ki???u gi??i m?? RSA b???ng privateKey
				Cipher decryptRSA = Cipher.getInstance("RSA");
				decryptRSA.init(Cipher.DECRYPT_MODE, privateKey);

				//Nh???n secretKey ???? m?? ho??
				String secretKeyEncrypted = in.readUTF();
				//Gi???i m?? secretKey
				byte[] aesKey = decryptRSA.doFinal(Base64.getDecoder().decode(secretKeyEncrypted));
				System.out.println(aesKey);
				
				//T???o secretKey chu???n
			    SecretKeySpec spec = new SecretKeySpec(aesKey, "AES");
				System.out.println(Base64.getEncoder().encodeToString(spec.getEncoded()));

				//T???o ki???u gi??i m?? AES b???ng secretKey
				Cipher decryptAES = Cipher.getInstance("AES");
			    decryptAES.init(Cipher.DECRYPT_MODE, spec);
			    ObjectInputStream ois = new ObjectInputStream(new CipherInputStream(socket.getInputStream(), decryptAES));
			    
			    //?????c c??i Map TTSV,M??n, ??i???m
			    Map<String, List<String>>[] map = (Map<String, List<String>>[]) ois.readObject();
				//?????c list ranking n???u map kh??c null
				if(map != null) {
					rank = (List<Ranking>) ois.readObject();
				}
				List<Course> courses = new ArrayList<>();
				Map<String, List<Float>> markMap = new LinkedHashMap<>();
				String[] array_TheChat = { "BODA", "BOBA", "BOCH", "CALO", "BORO" };
				String[] monThayThe = {"841308","841072","841073"};
				List<Course> remain = new ArrayList<>();
				
				//N???u map = null t????ng ??????ng sinh vi??n kh??ng t???n t???i
				if(map != null) {
					
					map[0].values().forEach(x -> {
						student = new Student();
						student.setId(x.get(0));
						student.setName(x.get(1));
						student.setSex(x.get(2));
						student.setPob(x.get(3));
						student.setUniClass(StringUtils.substringBefore(x.get(4), "("));
						student.setDepartment(x.get(5));
						student.setFaculty(x.get(5));
						if(map[0].get("Th??ng tin sinh vi??n").size() == 12) {
							student.setMajor(x.get(6));
							student.setDepartment(x.get(7));
							student.setCourseYear(Integer.parseInt(x.get(9).substring(0, 4)));
							student.setCourseDuration(x.get(9));
							student.setCounselor(x.get(10));
							if(x.get(11) != null) {
								student.setCurrentCredit(Integer.parseInt(x.get(11)));
							} else {
								student.setCurrentCredit(0);
							}
						} else {
							student.setDepartment(x.get(6));
							student.setCourseYear(Integer.parseInt(x.get(8).substring(0, 4)));
							student.setCourseDuration(x.get(8));
							student.setCounselor(x.get(9));
							if(x.get(10) != null) {
								student.setCurrentCredit(Integer.parseInt(x.get(10)));
							} else {
								student.setCurrentCredit(0);
							}
						}
						
					});
					
					map[1].values().forEach(x -> {
						for (var course : x) {
							//T??ch chu???i
							String[] splits = course.split("\\|");
							Course c = new Course();
							
							//L?? m??n ?????u v??o ti???ng anh n??n skip
							if(splits[0].equals("KSTA60")) {
								continue;
							}
							
							//Ph???i check v?? kh??c m?? m??n h???c so v???i CT??T
							if(splits[0].equals("841324")) {
								c.setCourseID("868001");
								c.setCourseName(splits[1]);
								c.setCourseCredit(Integer.parseInt(splits[2]));
								c.setGradeBase10( splits[3].isEmpty() || splits[3].equalsIgnoreCase("R") ? 0 : Float.parseFloat(splits[3]));
								c.setGradeBase4(splits[4].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
								c.setPass(splits[5].equals("?????t") ? true : false);
								courses.add(c);
								continue;
							}
							
							//Ki???m tra m??n h???c c?? b???t ?????u m???t trong nh???ng chu???i ??? m???ng array_TheChat
							if (Arrays.stream(array_TheChat).anyMatch(b -> splits[0].startsWith(b))) {
								//Ki???m tra trong list m??n h???c hi???n t???i ???? c?? 862102
								if (!courses.stream().anyMatch(b -> b.getCourseID().equals("862102"))) {
									c.setCourseID("862102");
									c.setCourseName("Gi??o d???c th???ch???t (II)");
									c.setCourseCredit(Integer.parseInt(splits[2]));
									c.setGradeBase10( splits[3].isEmpty() || splits[3].equalsIgnoreCase("R") ? 0 : Float.parseFloat(splits[3]));
									c.setGradeBase4(splits[4].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
									c.setPass(splits[5].equals("?????t") ? true : false);
									courses.add(c);
									continue;
								}
								//N???u list m??n h???c hi???n t???i ???? c?? 862102 th??
								c.setCourseID("862103");
								c.setCourseName("Gi??o d???c th???ch???t (III)");
								c.setCourseCredit(Integer.parseInt(splits[2]));
								c.setGradeBase10( splits[3].isEmpty() || splits[3].equalsIgnoreCase("R") ? 0 : Float.parseFloat(splits[3]));
								c.setGradeBase4(splits[4].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
								c.setPass(splits[5].equals("?????t") ? true : false);
								courses.add(c);
								continue;
							}
							//V???i c??c m??n bt th?? th??m v??o
							c.setCourseID(splits[0]);
							c.setCourseName(splits[1]);
							c.setCourseCredit(Integer.parseInt(splits[2]));
							c.setGradeBase10( splits[3].isEmpty() || splits[3].equalsIgnoreCase("R") ? 0 : Float.parseFloat(splits[3]));
							c.setGradeBase4(splits[4].isEmpty() ? 0 : Integer.parseInt(splits[4].substring(0, 1)));
							c.setPass(splits[5].equals("?????t") ? true : false);
							courses.add(c);
						}
					});
					
					
					//Lambda Expression forEach(k,v) c?? ngh??a l?? n?? s??? l???p theo k=key l???y k=key hi???n t???i,v = value
					map[2].forEach((k,v) -> {
						if(v.isEmpty())
							return;
						List<Float> diem = new ArrayList<Float>();
						//v??ng l???p l???y ??i???m
						v.forEach(d -> diem.add(Float.parseFloat(d)));
						markMap.put(k, diem);
					});
					
					//Ki???m tra n???u m??n h???c kh??ng c?? trong list courses th?? add v??o danh s??ch m??n c??n l???i
					if(student.getFaculty().equals("C??ng ngh??? th??ng tin")) {
						ListMonHoc.MonHocs.forEach((k,v) -> {
							if(!courses.stream().anyMatch(n -> n.getCourseID().equals(String.valueOf(k)))) {
								remain.add(v);
							}
						});
						//Ki???m tra n???u c?? ????ng k?? m??n kho?? lu???n th?? xo?? 3 m??n thay th???
						if(courses.stream().anyMatch(n -> n.getCourseID().equals("841099"))) {
							remain.removeIf(x -> Arrays.stream(monThayThe).anyMatch(b -> b.equals(x.getCourseID())));
						}
						
						//Ki???m tra n???u trong danh s??ch m??n ??ang h???c c?? 1 trong 3 thay th???, th?? s??? xo?? m??n kho?? lu???n
						if(courses.stream().anyMatch(n -> Arrays.stream(monThayThe).anyMatch(b -> b.equals(n.getCourseID())))) {
							remain.removeIf(x -> x.getCourseID().equals("841099"));
						}
					}
					
					//Ki???m tra c?? m??n c???i thi???n
					Map<String, Course> caiThien = new LinkedHashMap<String,  Course>();
					for (Course c : courses) {
					     if(caiThien.containsKey(c.getCourseID()) ) {
						    	System.out.println(c.getCourseName());
						        if( caiThien.get(c.getCourseID()).getGradeBase4() < c.getGradeBase4()  ) {
						            System.out.println("M??n c???i thi???n ??i???m cao h??n");
						            caiThien.put(c.getCourseID(), c);
						        }
						    } else {
						    	caiThien.put(c.getCourseID(), c);
						    }
					}
					
					courses.clear();
					courses.addAll(caiThien.values());
					
					student.setCourses(courses);
					student.setMarkMap(markMap);
					student.setRemainCourses(remain);
					
					centerPanel.removeAll();
					insideCenter = new GPAPanel(student);
					centerPanel.add(insideCenter, BorderLayout.CENTER);
					centerPanel.repaint();
					centerPanel.revalidate();
					//cis.close();
					ois.close();
				} else {
					JOptionPane.showMessageDialog(this, "M?? s??? sinh vi??n kh??ng t???n t???i","Th??ng b??o",JOptionPane.ERROR_MESSAGE);
				}
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		header.add(btnSearch);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		rankingPanel = new JPanel();
		rankingPanel.setLayout(null);
		rankingPanel.setBackground(new Color(45, 118, 232));
		rankingPanel.setBounds(0, 190, 180, 40);
		rankingPanel.addMouseListener(this);
		leftPanel.add(rankingPanel);

		lbRanking = new JLabel("X???p h???ng");
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
			RankingPanel center = new RankingPanel(department,year,faculty,rank);
			center.setCheckBoxListener((name,bool) -> {
				switch(name) {
				case "department":
					department = bool;
					break;
				case "year":
					year = bool;
					break;
				case "faculty":
					faculty = bool;
					break;
				default:
				}
			});
			center.getTable().setTraCuuListener(mssv -> {
				mssvField.setText(mssv);
				btnSearch.doClick();
			});
			centerPanel.add(center, BorderLayout.CENTER);
			centerPanel.repaint();
			centerPanel.revalidate();
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
