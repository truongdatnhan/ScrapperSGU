package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ServerNew {
	public ServerNew(int port) {
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("server started");
			System.out.println("watting for a client...");
			while (true) {
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
			try {
				try (
						// Socket socket = clientSocket;
						clientSocket;
						ObjectOutputStream mapOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
						BufferedReader input = new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()));) {

					Map<String, List<String>>[] map = mapTTSV(input.readLine());
					mapOutputStream.writeObject(map);
					mapOutputStream.flush();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ServerNew s = new ServerNew(5000);
	}

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
				Elements info = loginPage.select("div.infor-member tr");
				builder = new StringBuilder();
				List<String> ttsv = new ArrayList<>();
				for (var i : info) {
					ttsv.add(i.select("td:nth-child(2) span").text());
				}
				mapTT.put("Thông tin sinh viên", ttsv);
				Elements table = loginPage.select("table.view-table tr");
				List<String> hk = null;
				List<String> diem = null;
				for (Element e : table) {
					if (e.className().equals("title-hk-diem")) {
						hk = new ArrayList<>();
						diem = new ArrayList<>();
						mapMon.put(e.text(), hk);
						mapDiem.put(e.text(), diem);
					}

					builder = new StringBuilder();
					switch (e.className()) {
					case "row-diem":
						builder.append(e.select("td:nth-child(2) span").text()).append("|")
								.append(e.select("td:nth-child(3) span").text()).append("|")
								.append(e.select("td:nth-child(4) span").text()).append("|")
								.append(e.select("td:nth-child(10) span").text()).append("|")
								.append(e.select("td:nth-child(13) span").text()).append("|")
								.append(e.select("td:nth-child(14) span").text());
						hk.add(builder.toString());
						break;
					case "row-diemTK":
							diem.add(e.select("td span:nth-child(2)").text());
						break;
					default:
						break;
					}

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
