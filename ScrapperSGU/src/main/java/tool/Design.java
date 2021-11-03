package tool;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Design {
	public static ImageIcon resizeIcon(String url,int width,int height) {
		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}
	
}
