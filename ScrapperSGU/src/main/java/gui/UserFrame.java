package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import gui.panel.GPAPanel;
import gui.panel.ProgressPanel;
import gui.panel.RankingPanel;
import tool.Design;

import java.awt.event.MouseAdapter;
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
    public JPanel gpaPanel,progressPanel,rankingPanel;
    public JLabel lbGPA, lbProgress,lbRanking;
    public JLabel lbLogo;
    public JButton btnSearch;
    public JTextField mssvField;
    public JPanel insideCenter;
    
    public UserFrame() {
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1280, 720);

        setLocationRelativeTo(null);
        setTitle("Quản lí nhân viên");
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainPanel);

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
        //0,y+45,200,40
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

        insideCenter = new GPAPanel();
        centerPanel.add(insideCenter, BorderLayout.CENTER);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == gpaPanel || e.getSource() == lbGPA) {
            centerPanel.removeAll();
            insideCenter = new GPAPanel();
            centerPanel.add(insideCenter, BorderLayout.CENTER);
            centerPanel.repaint();
            centerPanel.revalidate();
        }
        else if (e.getSource() == progressPanel || e.getSource() == lbProgress) {
            centerPanel.removeAll();
            insideCenter = new ProgressPanel();
            centerPanel.add(insideCenter, BorderLayout.CENTER);
            centerPanel.repaint();
            centerPanel.revalidate();
        }
        else if (e.getSource() == rankingPanel || e.getSource() == lbRanking) {
            centerPanel.removeAll();
            insideCenter = new RankingPanel();
            centerPanel.add(insideCenter, BorderLayout.CENTER);
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
