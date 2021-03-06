package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import model.Ranking;
import tool.NotClosingOutputStream;

public class ServerNew {
	public ServerNew(int port) {
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("server started");
			System.out.println("watting for a client...");
			while (true) {
				//Tạo 1 cái luồng mới
				new ClientHandler(server.accept()).start();
				System.out.println("Client accepted");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class ClientHandler extends Thread {
		private final Socket clientSocket;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
				
				try (
						clientSocket;
						//Phải dùng ObjectOutputStream mới gửi được map, object
						//Cái kia chỉ gửi được String
						ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
						ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
					) {
						
					//Nhận public key RSA từ client
					PublicKey publicKey = (PublicKey) input.readObject();
					//Khai báo kiểu mã hoá là RSA và sử dụng public key để mã hoá
					Cipher encryptRSA = Cipher.getInstance("RSA");
					encryptRSA.init(Cipher.ENCRYPT_MODE, publicKey);
					
					//System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
					
					//Khởi tạo độ dài SecretKey
					SecureRandom rnd = new SecureRandom();
				    byte[] iv = new byte[16];
				    rnd.nextBytes(iv);
	
				    //Khởi tạo khoá bí mật kiểu AES
				    SecretKeySpec spec = new SecretKeySpec(iv, "AES");
				    Cipher encryptAES = Cipher.getInstance("AES");
				    encryptAES.init(Cipher.ENCRYPT_MODE, spec);
				    //System.out.println(Base64.getEncoder().encodeToString(spec.getEncoded()));
					
					//Split cái chuỗi nhận vào theo ký tự |
					String[] receive = input.readUTF().split("\\|");
					
					String secretKeyEncrypted = Base64.getEncoder().encodeToString(encryptRSA.doFinal(spec.getEncoded()));
					//String secretKeyEncrypted = new String(encryptRSA.doFinal(spec.getEncoded()));
					System.out.println(Base64.getEncoder().encodeToString(spec.getEncoded()));
					System.out.println(secretKeyEncrypted);
					//Gửi secretKey dạng base64 về client
					output.writeUTF(secretKeyEncrypted);
					output.flush();
				    
				    //Lấy thông tin sinh viên và điểm, nếu sinh viên không tồn tại = null
					Map<String, List<String>>[] map = mapTTSV(receive[0]);
					//Trả về lần 1 là TTSV, Môn, Điểm
					CipherOutputStream cos = new CipherOutputStream(clientSocket.getOutputStream(), encryptAES);
					ObjectOutputStream oos = new ObjectOutputStream(cos);
					oos.writeObject(map);
					oos.flush();
					//Phải có flush mới gửi tiếp dc
					//Trả về lần 2
					if(map != null) {
						String id = map[0].get("Thông tin sinh viên").get(0);
						//Kiểm tra các giá trị xếp hạng
						//khoa
						//Nếu không chọn => giá trị = false, sẽ = null 
						//còn nếu chọn => giá trị = true, sẽ lấy trong map thông tin sinh viên và vị trí số 5
						//ngành
						String faculty = Boolean.valueOf(receive[3]) ? map[0].get("Thông tin sinh viên").get(5) : null;
						
						if(map[0].get("Thông tin sinh viên").size() == 12) {
							//khoá
							String year = Boolean.valueOf(receive[2]) ? map[0].get("Thông tin sinh viên").get(9).substring(0, 4) : null;
							//khoa
							String department = Boolean.valueOf(receive[1]) ? map[0].get("Thông tin sinh viên").get(7) : null;
							List<Ranking> studs = ranking(id,department,year,faculty);
							oos.writeObject(studs);
							oos.flush();
						}else {
							//khoá
							String year = Boolean.valueOf(receive[2]) ? map[0].get("Thông tin sinh viên").get(8).substring(0, 4) : null;
							//khoa
							String department = Boolean.valueOf(receive[1]) ? map[0].get("Thông tin sinh viên").get(6) : null;
							List<Ranking> studs = ranking(id,department,year,faculty);
							oos.writeObject(studs);
							oos.flush();
						}
					}
					oos.close();
				}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ServerNew s = new ServerNew(5000);
	}
	
	//Hàm lấy xếp hạng
	public static List<Ranking> ranking(String id,String department, String year, String faculty) {
		List<Ranking> rank = null;
		try (SessionFactory factory = new Configuration().configure().addAnnotatedClass(Ranking.class)
				.buildSessionFactory();) {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			
			StringBuilder sb = new StringBuilder();
			
			//Khoa
			if(department != null) {
				sb.append(" WHERE khoa = ")
				.append("'")
				.append(department)
				.append("'");
			}
			
			//Khoá
			if(year != null) {
				if(sb.toString().contains("WHERE")) {
					sb.append(" AND namhoc= ");
				} else {
					sb.append(" WHERE namhoc= ");
				}
				
				sb.append("'")
				.append(year)
				.append("'");
			}
			
			//Ngành
			if(faculty != null) {
				if(sb.toString().contains("WHERE")) {
					sb.append(" AND nganh = ");
				} else {
					sb.append(" WHERE nganh = ");
				}
				sb.append("'")
				.append(faculty)
				.append("'");
			}
			NativeQuery p = session.createNativeQuery("SELECT * FROM( SELECT *, RANK() OVER (ORDER BY diemhebon DESC,id) ranking FROM sinhvien "+ sb.toString() +") AS svMark\r\n"
					+ "WHERE id = :id\r\n"
					+ "union all (\r\n"
					+ "  SELECT * FROM( SELECT *, RANK() OVER (ORDER BY diemhebon DESC,id) ranking FROM sinhvien "+ sb.toString() +") AS svMark\r\n"
					+ "  where ranking <  (SELECT ranking FROM( SELECT *, RANK() OVER (ORDER BY diemhebon DESC,id) ranking FROM sinhvien " + sb.toString() +") AS svMark WHERE id = :id) \r\n"
					+ "  order by ranking desc limit 10\r\n"
					+ ") \r\n"
					+ "union all (\r\n"
					+ "  SELECT * FROM( SELECT *, RANK() OVER (ORDER BY diemhebon DESC,id) ranking FROM sinhvien "+ sb.toString() +") AS svMark\r\n"
					+ "  where ranking > (SELECT ranking FROM( SELECT *, RANK() OVER (ORDER BY diemhebon DESC,id) ranking FROM sinhvien "+sb.toString()+") AS svMark WHERE id = :id) \r\n"
					+ "  order by ranking asc limit 10\r\n"
					+ ") order by ranking asc");
			p.setParameter("id", id);
			
			//Lấy danh sách trả về mà ép thành Ranking List<Ranking>
			rank = p.addEntity(Ranking.class).getResultList();
			session.getTransaction().commit();
		}
		return rank;
	}
	
	//Hàm lấy thông tin sinh viên
	public static LinkedHashMap<String, List<String>>[] mapTTSV(String ms) {
		LinkedHashMap<String, List<String>> mapTT = new LinkedHashMap<>();
		LinkedHashMap<String, List<String>> mapMon = new LinkedHashMap<>();
		LinkedHashMap<String, List<String>> mapDiem = new LinkedHashMap<>();
		LinkedHashMap<String, List<String>>[] map = new LinkedHashMap[] {mapTT,mapMon,mapDiem};
		String url = "http://thongtindaotao.sgu.edu.vn/default.aspx?page=nhapmasv&flag=XemDiemThi";
		StringBuilder builder;
		try {
			String UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36";
			Response response = Jsoup.connect(url).method(Connection.Method.GET).execute();
			Document loginPage = response.parse();
			response = Jsoup.connect(url).data("__EVENTTARGET", "").data("__EVENTARGUMENT", "")
					.data("__VIEWSTATE", loginPage.getElementById("__VIEWSTATE").val())
					.data("__VIEWSTATEGENERATOR", loginPage.getElementById("__VIEWSTATEGENERATOR").val())
					.data("ctl00$ContentPlaceHolder1$ctl00$txtMaSV", ms)
					.data("ctl00$ContentPlaceHolder1$ctl00$btnOK", "OK").userAgent(UserAgent).timeout(0)
					.followRedirects(true).cookies(response.cookies()).method(Connection.Method.POST).execute();
			loginPage = response.parse();
			if (loginPage.getElementById("ctl00_ContentPlaceHolder1_ctl00_ucThongTinSV_lblMaSinhVien") == null) {
				return null;
			} else {
				//Hàm lấy thông tin sinh viên
				//Chọn cái bảng thông tin sinh viên
				//Bảng div class là infor-member lấy tất cả các dòng tr
				Elements info = loginPage.select("div.infor-member tr");
				builder = new StringBuilder();
				List<String> ttsv = new ArrayList<>();
				//Vòng lặp để lấy thông tin sinh viên
				for (var i : info) {
					ttsv.add(i.select("td:nth-child(2) span").text());
				}
				//Thêm cái thông tinh sinh viên vào HashMap Thông tin
				mapTT.put("Thông tin sinh viên", ttsv);
				
				//Bảng table có class là view-table chọn tất cả các dòng tr
				Elements table = loginPage.select("table.view-table tr");
				List<String> hk = null;
				List<String> diem = null;
				String tongTinChi = null;
				for (Element e : table) {

					builder = new StringBuilder();
					switch (e.className()) {
					case "title-hk-diem":
						if(diem != null && !diem.isEmpty()) {
							tongTinChi = diem.get(diem.size() -1);
						}
						hk = new ArrayList<>();
						diem = new ArrayList<>();
						mapMon.put(e.text(), hk);
						mapDiem.put(e.text(), diem);
						break;
					case "row-diem":
						//Nếu mà là row-diem nghĩa là môn học lấy các cột 2,3,4,10,13,14
						builder.append(e.select("td:nth-child(2) span").text()).append("|")
								.append(e.select("td:nth-child(3) span").text()).append("|")
								.append(e.select("td:nth-child(4) span").text()).append("|")
								.append(e.select("td:nth-child(10) span").text()).append("|")
								.append(e.select("td:nth-child(13) span").text()).append("|")
								.append(e.select("td:nth-child(14) span").text());
						hk.add(builder.toString());
						break;
					case "row-diemTK":
						//Nếu là row-diemTK tương ứng điểm tb lấy điểm
							diem.add(e.select("td span:nth-child(2)").text());
						break;
					default:
						break;
					}
				}
				ttsv.add(tongTinChi);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
