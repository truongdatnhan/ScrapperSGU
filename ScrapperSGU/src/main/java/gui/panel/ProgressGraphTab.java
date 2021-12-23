package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import model.Student;
import net.miginfocom.swing.MigLayout;
import tool.DualAxis;

public class ProgressGraphTab extends JPanel {
	
	public ProgressGraphTab(Student student) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
        if(student != null) {
        	//Đoạn code lấy dữ liệu cho đồ thị
        	ArrayList<String> hk = new ArrayList<>();
            ArrayList<Float> dtb4 = new ArrayList<Float>();
            ArrayList<Float> dtb10 = new ArrayList<Float>();
        	student.getMarkMap().forEach((k,v) -> {
        		if(!k.startsWith("Học kỳ 3")) {
        			hk.add(k);
        			dtb4.add(v.get(2));
        			dtb10.add(v.get(3));
        		}
        	});
        	ChartPanel chartPanel = new ChartPanel(new DualAxis(hk,dtb10,dtb4).getChart());
        	//Tạo graph xong chỉ cần thay null add vào
    		add(chartPanel,BorderLayout.CENTER);
        }	
	}
	
}
