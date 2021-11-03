package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ImagePanel  {
	private BufferedImage myPicture;
	private ImageIcon scaledImage;
	public ImagePanel() {
	}
	
	public void setImage(String path,JLabel a) {
		try {
                        myPicture=ImageIO.read(new File(path));
			scaledImage = new ImageIcon(myPicture.getScaledInstance(a.getWidth(),a.getHeight(),Image.SCALE_SMOOTH));
			a.setIcon(scaledImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
        
//	@Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(scaledImage, 100, 100, this); // see javadoc for more info on the parameters            
//    }
}
