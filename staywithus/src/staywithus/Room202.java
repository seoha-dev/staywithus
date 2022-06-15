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
	
	//����
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

	//������
	public Room202() {
		this(null);

	}
	

	//������
	public Room202(String userId) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		//��� ���� ���Ǿȳ� ��
		JLabel roomguideLb = new JLabel("���Ǿȳ�");
		roomguideLb.setFont(new Font("���� ���", Font.BOLD, 15));
		roomguideLb.setBounds(12, 10, 62, 15);
		frame.getContentPane().add(roomguideLb);
		
		//�̹��� Ŭ�� �ȳ� ��
		JLabel icLb = new JLabel("�̹����� Ŭ�� �ϼ���!");
		icLb.setBackground(Color.WHITE);
		icLb.setFont(new Font("���� ���", Font.PLAIN, 12));
		icLb.setBounds(321, 82, 147, 15);
		frame.getContentPane().add(icLb);

		// 202ȣ �̹��� - Ŭ���ϸ� �̹��� �ٲ��
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

		rmimgLb.addMouseListener(new MouseAdapter() {//���콺 �̺�Ʈ - �̹��� Ŭ�� �� ���� ����
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
		
		//���� Ÿ��Ʋ �� 
				JLabel rmtitle = new JLabel("202ȣ (Premier Room)");
				rmtitle.setFont(new Font("���� ���", Font.BOLD | Font.ITALIC, 14));
				rmtitle.setBounds(746, 151, 198, 24);
				frame.getContentPane().add(rmtitle);

		
		//���� �Ұ� �ؽ�Ʈ
		JTextArea rmtext = new JTextArea();
		rmtext.setFont(new Font("���� ���", Font.PLAIN, 14));
		rmtext.setText("üũ�� : PM 15:00 ~ 21:00\r\n" + "üũ�ƿ� : AM 11:00\r\n" + "���� ���� �ο� : 4/6\r\n" + "�ִ� �ο� : 6\r\n\r\n"
				+ "Facilities : �����̺� ������ , �߿� ��õ��, �̴�����\r\n\r\n" + "Amenity : TV/������/������/�����ְ�/�����/����/\r\n"
				+ "�̴Ϲ�/1ȸ�� ĩ�� �� ġ��/��尡��/������̾�\r\n\r\n" + "Price : 300,000");
		rmtext.setBounds(668, 204, 429, 323);
		rmtext.setEditable(false);
		frame.getContentPane().add(rmtext);

		//Back ��ư -> ��Ǿȳ� �������� ���ư��� �ٸ� ���� ���� �� �� �ְ�
				JButton backbtn = new JButton("<Back");
				backbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Guide(userId);
						frame.dispose();
					}
				});
				backbtn.setBackground(Color.WHITE);
				backbtn.setFont(new Font("���� ���", Font.PLAIN, 10));
				backbtn.setForeground(Color.BLACK);
				backbtn.setBounds(12, 730, 67, 23);
				frame.getContentPane().add(backbtn);

	}

}

