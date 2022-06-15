package staywithus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import staywithus.*;

public class Room202 {

	private JFrame frame;
	
	//실행
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Room202 room202 = new Room202();
					room202.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//생성자
	public Room202() {
		this(null);

	}
	

	//생성자
	public Room202(String userId) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		//상단 왼쪽 객실안내 라벨
		JLabel roomguideLb = new JLabel("객실안내");
		roomguideLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		roomguideLb.setBounds(12, 10, 62, 15);
		frame.getContentPane().add(roomguideLb);
		
		//이미지 클릭 안내 라벨
		JLabel icLb = new JLabel("이미지를 클릭 하세요!");
		icLb.setBackground(Color.WHITE);
		icLb.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		icLb.setBounds(321, 82, 147, 15);
		frame.getContentPane().add(icLb);

		// 202호 이미지 - 클릭하면 이미지 바뀌게
		JLabel rmimgLb = new JLabel();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img1 = kit.getImage("src/staywithus/images/m202.jpg");
		Image img2 = kit.getImage("src/staywithus/images/202-1.jpg");
		Image img3 = kit.getImage("src/staywithus/images/202-2.jpg");
		Image img4 = kit.getImage("src/staywithus/images/202-3.jpg");
		
		ImageIcon icon1 = new ImageIcon(img1);
		ImageIcon icon2 = new ImageIcon(img2);
		ImageIcon icon3 = new ImageIcon(img3);
		ImageIcon icon4 = new ImageIcon(img4);
		
		rmimgLb.setIcon(icon1);
		rmimgLb.setBounds(131, 108, 505, 543);
		frame.getContentPane().add(rmimgLb);

		rmimgLb.addMouseListener(new MouseAdapter() {//마우스 이벤트 - 이미지 클릭 시 사진 변경
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(rmimgLb.getIcon() == icon1) {
					rmimgLb.setIcon(icon2);
				} else if(rmimgLb.getIcon() == icon2) {
					rmimgLb.setIcon(icon3);
				} else if(rmimgLb.getIcon() == icon3) {
					rmimgLb.setIcon(icon4);
				} else if(rmimgLb.getIcon() == icon4) {
					rmimgLb.setIcon(icon1);
				}
			}
		});
		
		//객실 타이틀 라벨 
				JLabel rmtitle = new JLabel("202호 (Premier Room)");
				rmtitle.setFont(new Font("맑은 고딕", Font.BOLD | Font.ITALIC, 14));
				rmtitle.setBounds(746, 151, 198, 24);
				frame.getContentPane().add(rmtitle);

		
		//객실 소개 텍스트
		JTextArea rmtext = new JTextArea();
		rmtext.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		rmtext.setText("체크인 : PM 15:00 ~ 21:00\r\n" + "체크아웃 : AM 11:00\r\n" + "숙박 기준 인원 : 4/6\r\n" + "최대 인원 : 6\r\n\r\n"
				+ "Facilities : 프라이빗 수영장 , 야외 노천탕, 미니정원\r\n\r\n" + "Amenity : TV/손전등/슬리퍼/구둣주걱/냉장고/옷장/\r\n"
				+ "미니바/1회용 칫솔 및 치약/목욕가운/헤어드라이어\r\n\r\n" + "Price : 300,000");
		rmtext.setBounds(668, 204, 429, 323);
		rmtext.setEditable(false);
		frame.getContentPane().add(rmtext);

		//Back 버튼 -> 펜션안내 페이지로 돌아가서 다른 객실 선택 할 수 있게
				JButton backbtn = new JButton("<Back");
				backbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Guide(userId);
						frame.dispose();
					}
				});
				backbtn.setBackground(Color.WHITE);
				backbtn.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
				backbtn.setForeground(Color.BLACK);
				backbtn.setBounds(12, 730, 67, 23);
				frame.getContentPane().add(backbtn);

	}

}

