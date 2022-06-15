package staywithus;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Guide {

	private JFrame frame;

	public static void main(String[] args) {//����
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guide guide = new Guide();
					guide.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Guide() {//������
		this(null);
	}
	
	public Guide(String userId) {
		frame = new JFrame();
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		frame.setTitle(userId + "�� �ݰ����ϴ�.");
		
		JLabel titleLb = new JLabel("��� �Ұ�");
		titleLb.setBackground(Color.WHITE);
		titleLb.setFont(new Font("���� ���", Font.BOLD, 15));
		titleLb.setBounds(12, 10, 101, 15);
		frame.getContentPane().add(titleLb);
		
		//�� - 101ȣ ����
		JLabel room101 = new JLabel();
		Toolkit kit1 = Toolkit.getDefaultToolkit();
		Image img1 = kit1.getImage("src/staywithus/images/101.jpg");
		ImageIcon icon1 = new ImageIcon(img1);
		room101.setIcon(icon1);
		room101.setBounds(12, 38, 267, 356);
		frame.getContentPane().add(room101);
		
		//�� - 102ȣ ����
		JLabel room102 = new JLabel();
		Toolkit kit2 = Toolkit.getDefaultToolkit();
		Image img2 = kit2.getImage("src/staywithus/images/102.jpg");
		ImageIcon icon2 = new ImageIcon(img2);
		room102.setIcon(icon2);
		room102.setBounds(308, 38,  267, 356);
		frame.getContentPane().add(room102);
		
		//�� - 201ȣ ����
		JLabel room201 = new JLabel();
		Toolkit kit3 = Toolkit.getDefaultToolkit();
		Image img3 = kit3.getImage("src/staywithus/images/201.jpg");
		ImageIcon icon3 = new ImageIcon(img3);
		room201.setIcon(icon3);
		room201.setBounds(610, 38,  267, 356);
		frame.getContentPane().add(room201);
		
		//�� - 202ȣ ����
		JLabel room202 = new JLabel();
		Toolkit kit4 = Toolkit.getDefaultToolkit();
		Image img4 = kit4.getImage("src/staywithus/images/202.jpg");
		ImageIcon icon4 = new ImageIcon(img4);
		room202.setIcon(icon4);
		room202.setBounds(907, 38,  267, 356);
		frame.getContentPane().add(room202);
		
		//101ȣ ��ư -> Ŭ�� �� ���Ǿȳ� ������ �̵�
		JButton rb101 = new JButton("101ȣ");
		rb101.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Room101(userId);
				frame.dispose();
			}
		});
		rb101.setFont(new Font("���� ���", Font.BOLD, 12));
		rb101.setBackground(Color.WHITE);
		rb101.setBounds(101, 404, 95, 23);
		frame.getContentPane().add(rb101);
		
		//102ȣ ��ư -> Ŭ�� �� ���Ǿȳ� ������ �̵�
		JButton rb102 = new JButton("102ȣ");
		rb102.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Room102(userId);
				frame.dispose();
			}
		});
		rb102.setFont(new Font("���� ���", Font.BOLD, 12));
		rb102.setBackground(Color.WHITE);
		rb102.setBounds(395, 404, 95, 23);
		frame.getContentPane().add(rb102);
		
		
		//201ȣ ��ư -> Ŭ�� �� ���Ǿȳ� ������ �̵�
		JButton rb201 = new JButton("201ȣ");
		rb201.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Room201(userId);
				frame.dispose();
			}
		});
		rb201.setFont(new Font("���� ���", Font.BOLD, 12));
		rb201.setBackground(Color.WHITE);
		rb201.setBounds(700, 404, 95, 23);
		frame.getContentPane().add(rb201);
		
		
		//202ȣ ��ư -> Ŭ�� �� ���Ǿȳ� ������ �̵�
		JButton rb202 = new JButton("202ȣ");
		rb202.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Room202(userId);
				frame.dispose();
			}
		});
		rb202.setFont(new Font("���� ���", Font.BOLD, 12));
		rb202.setBackground(Color.WHITE);
		rb202.setBounds(993, 404, 95, 23);
		frame.getContentPane().add(rb202);
		
		//�� - ���ô� �� 
		JLabel map = new JLabel("���ô� ��");
		map.setFont(new Font("���� ���", Font.BOLD, 13));
		map.setBounds(782, 464, 67, 15);
		frame.getContentPane().add(map);
		
		//�� - ���� ����
		JLabel imgmap = new JLabel();
		Toolkit kit5 = Toolkit.getDefaultToolkit();
		Image img5 = kit5.getImage("src/staywithus/images/map.jpg");
		ImageIcon icon5 = new ImageIcon(img5);
		imgmap.setIcon(icon5);
		imgmap.setBounds(531, 486, 557, 267);
		frame.getContentPane().add(imgmap);
		
		//�ؽ�Ʈ Pane - �ּ� �߰�
		JTextPane addr = new JTextPane();
		addr.setFont(new Font("���� ���", Font.BOLD | Font.ITALIC, 13));
		addr.setText("-Address\r\n"
				+ "���θ� : �泲 ���ر� ���� ������� 1803-18\r\n"
				+ "1803-18, Namseo-daero, Seo-myeon, Namgae-gun,\r\n"
				+ "Gyeongsangnam-do, Republic of Korea");
		addr.setBounds(211, 534, 279, 120);
		addr.setEditable(false);
		frame.getContentPane().add(addr);
		
		//Ȩ ��ư -> Ŭ�� �� ����ȭ������ �̵�
		JButton homeBtn = new JButton("Home");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				frame.dispose();
			}
		});
		homeBtn.setBackground(Color.WHITE);
		homeBtn.setFont(new Font("���� ���", Font.PLAIN, 10));
		homeBtn.setForeground(Color.BLACK);
		homeBtn.setBounds(12, 730, 67, 23);
		frame.getContentPane().add(homeBtn);
	}
	
	
}
