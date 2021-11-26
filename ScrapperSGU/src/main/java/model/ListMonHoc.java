package model;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class ListMonHoc {
	
 public static HashMap<Integer,Course> MonHocs = new HashMap<>(
		 Map.ofEntries(
				 entry(861001, new Course("861001", "Những nguyên lí cơ bản của Chủ nghĩa Mác – Lênin", 5)),
				 entry(861002, new Course("861002", "Tư tưởng Hồ Chí Minh", 2)),
				 entry(861003, new Course("861003", "Đường lối cách mạng của ĐCSVN", 3)),
				 entry(866101, new Course("866101", "Tiếng Anh I", 2)),
				 entry(866102, new Course("866102", "Tiếng Anh II", 2)),
				 entry(866103, new Course("866103", "Tiếng Anh III", 3)),
				 entry(865006, new Course("865006", "Pháp luật đại cương", 2)),
				 entry(862101, new Course("862101", "Giáo dục thể chất (I)", 1)),
				 entry(862102, new Course("862102", "Giáo dục thể chất (II)", 1)),
				 entry(862103, new Course("862103", "Giáo dục thể chất (III)", 1)),
				 entry(862306, new Course("862306", "Giáo dục quốc phòng (I)", 2)),
				 entry(862307, new Course("862307", "Giáo dục quốc phòng (II)", 2)),
				 entry(862308, new Course("862308", "Giáo dục quốc phòng (III)", 3)),
				 entry(862309, new Course("862309", "Giáo dục quốc phòng (IV)", 1)),
				 entry(868001, new Course("868001", "Phương pháp NCKH trong CNTT", 2)),
				 entry(864001, new Course("864001", "Xác suất thống kê A", 3)),
				 entry(841301, new Course("861001", "Giải tích", 4)),
				 entry(841101, new Course("841101", "Đại số", 4)),
				 entry(841020, new Course("841020", "Cơ sở lập trình", 3)),
				 entry(841040, new Course("841040", "Kỹ thuật lập trình", 3)),
				 entry(841021, new Course("841021", "Kiến trúc máy tính", 3)),
				 entry(841022, new Course("841022", "Hệ điều hành", 3)),
				 entry(841309, new Course("841309", "Toán rời rạc", 3)),
				 entry(841310, new Course("841310", "Lý thuyết đồ thị", 3)),
				 entry(841104, new Course("841104", "Mạng máy tính", 4)),
				 entry(841107, new Course("841107", "Lập trình java", 4)),
				 entry(841304, new Course("841304", "Phát triển ứng dụng Web 1", 3)),
				 entry(841108, new Course("841108", "Cấu trúc dữ liệu và giải thuật", 4)),
				 entry(841109, new Course("841109", "Cơ sở dữ liệu", 4)),
				 entry(841044, new Course("841044", "Lập trình hướng đối tượng", 4)),
				 entry(841110, new Course("841110", "Cơ sở trí tuệ nhân tạo", 4)),
				 entry(841046, new Course("841046", "Phát triển ứng dụng Web 2", 3)),
				 entry(841047, new Course("841047", "Công nghệ phần mềm", 4)),
				 entry(841048, new Course("841048", "Phân tích thiết kế hệ thống thông tin", 4)),
				 entry(841111, new Course("841111", "Phân tích thiết kế hướng đối tượng", 4)),
				 entry(841058, new Course("841058", "Hệ điều hành mã nguồn mở", 3)),
				 entry(841052, new Course("841052", "Xây dựng phần mềm theo mô hình phân lớp", 3)),
				 entry(841050, new Course("841050", "Kiểm thử phần mềm", 3)),
				 entry(841051, new Course("841051", "Thiết kế giao diện", 3)),
				 entry(841114, new Course("841114", "Phát triển ứng dụng trên thiết bị di động", 3)),
				 entry(841065, new Course("841065", "Các hệ quản trị cơ sở dữ liệu", 3)),
				 entry(841120, new Course("841120", "An toàn và bảo mật dữ liệu trong HTTT", 3)),
				 entry(841067, new Course("841067", "Thương mại điện tử & ứng dụng", 3)),
				 entry(841068, new Course("841068", "Hệ thống thông tin doanh nghiệp", 3)),
				 entry(841059, new Course("841059", "Quản trị mạng", 3)),
				 entry(841061, new Course("841061", "Mạng máy tính nâng cao", 3)),
				 entry(841307, new Course("841307", "Lập trình mạng", 3)),
				 entry(841119, new Course("841119", "An ninh mạng máy tính", 3)),
				 entry(841113, new Course("841113", "Phát triển phần mềm mã nguồn mở", 3)),
				 entry(841121, new Course("841121", "Cơ sở dữ liệu phân tán", 3)),
				 entry(841070, new Course("841070", "Thực tập tốt nghiệp", 6)),
				 entry(841308, new Course("841308", "Khai phá dữ liệu", 3)),
				 entry(841072, new Course("841072", "Các công nghệ lập trình hiện đại", 3)),
				 entry(841073, new Course("841073", "Seminar chuyên đề", 4)),
				 entry(841099, new Course("841099", "Khóa luận tốt nghiệp", 10))
		 )
 );

}
