/*-- ����ȭ�� | ������ ������¥: 2022-03-27 | ������ ������: �輭��--*/
package staywithus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// // 0322 ������ ���� : 1. �Խ��� ����
public class MainPage {
	
	LoginMgr mgr = new LoginMgr();
	private JFrame frame;
	
	
	//����
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
							MainPage mainPage = new MainPage();
							mainPage.frame.setVisible(true);	
						

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
	
	
	//������ (�Ű�����)
	public MainPage(String userId) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		if(userId == null) {
			frame.setTitle("Stay With Us :: Reservation Program (��ȸ�� ��)");
		} else {
			frame.setTitle("Stay With Us :: Reservation Program (" + userId + " ��)");
		}
//		System.out.println(userId + "���� �̿����Դϴ�.");
	
		
		//JLabel �̹����߰�
		JLabel jImg = new JLabel();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("src/staywithus/images/mainimage.png");
		ImageIcon icon = new ImageIcon(img);
		jImg.setIcon(icon);
		jImg.setBounds(0, 43, 1186, 720);
		frame.getContentPane().add(jImg);
		
		
		//ȸ������ ��ư - Ŭ���� ȸ������ â ȣ�� -> ȸ������ �Ϸ� �� ����������
		JButton joinBtn = new JButton("ȸ������");
		joinBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		joinBtn.setBackground(Color.WHITE);
		joinBtn.setBounds(603, 10, 95, 23);
		frame.getContentPane().add(joinBtn);
		
		if(userId == null) { // ��ȸ�� (null)
			joinBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						new JoinFrame();
				}
			});
		} else {
			joinBtn.setEnabled(false);
		}
		
		//�α��� ��ư - Ŭ���� �α��� â ȣ�� -> �α��� �Ϸ� �� ����������
		JButton loginBtn = new JButton("�α���");		
		loginBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setBounds(699, 10, 95, 23);
		
		if (userId == null) { // ��ȸ�� (null)
			loginBtn.setEnabled(true);
			loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				frame.dispose();
			}
		});

		} else { // ȸ�� (userId)
			loginBtn.setEnabled(false);
		}
		
		//���������� ��ư - Ŭ���� ������������ �̵�
		JButton myPageBtn = new JButton("����������");
		myPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userId == null) { // ��ȸ�� (null)
					JOptionPane.showMessageDialog(null, "�α����� ���� �������ּ���.");
					System.out.println(userId + " : ���̵� �� �ҷ���");	
				} else { // ȸ�� (userId)
					new UpdateUser(userId);
					frame.dispose();
					System.out.println(userId + "���� ���������� ��ư�� Ŭ���߽��ϴ�.");
				}
			}
		});
		myPageBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		myPageBtn.setBackground(Color.WHITE);
		myPageBtn.setBounds(796, 10, 95, 23);
		frame.getContentPane().add(myPageBtn);
		
		
		//�����ư - Ŭ���� ���� �������� �̵�
		JButton reservationBtn = new JButton("����");
		reservationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(userId ==null) { // ��ȸ�� (null)
					JOptionPane.showMessageDialog(null, "�α����� ���� �������ּ���.");
					System.out.println(userId + " : ���̵� �� �ҷ���");
				} else { // ȸ�� (userId)
					new ReservationFrame(userId);
					System.out.println(userId + "���� �����ư�� Ŭ���߽��ϴ�.");
				}
				
			}
		});
		reservationBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		reservationBtn.setBackground(Color.WHITE);
		reservationBtn.setBounds(382, 10, 113, 23);
		frame.getContentPane().add(reservationBtn);
		
		//��� �ѷ����� ��ư - Ŭ���� �ѷ����� �������� �̵�
		JButton guideBtn = new JButton("��� �ѷ�����");
		guideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Guide(userId);
				frame.dispose();
			}
		});
		guideBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		guideBtn.setBackground(Color.WHITE);
		guideBtn.setBounds(244, 10, 136, 23);
		frame.getContentPane().add(guideBtn);
		
		//������������ ��ư
		JButton adminBtn = new JButton("������");
		adminBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		adminBtn.setBackground(Color.WHITE);
		adminBtn.setBounds(893, 10, 95, 23);
		adminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReservationAdmin(userId);
				frame.dispose();
			}
		});
		
		//�α���id mode������ �����ڹ�ư ���̱� ����
		if(mgr.modeChk(userId)==1) {
			System.out.println("[MainPage] ������(" + userId + ") �α���");
			frame.getContentPane().add(adminBtn);
		}else if (userId==null) {
			System.out.println("[MainPage] ��ȸ��(" + userId + ") ����");
		}else if (mgr.modeChk(userId)==0) {
			System.out.println("[MainPage] �����(" + userId + ") �α���");
		}
		
		
		// �α׾ƿ� ��ư 
		JButton logoutBtn = new JButton("�α׾ƿ�");
		logoutBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		logoutBtn.setBackground(Color.WHITE);
		logoutBtn.setBounds(699, 10, 95, 23);
		
		// ��ȸ���� ��쿡�� �α��� ��ư ���̱�, ȸ��(�α��οϷ�)�� ��쿡�� �α׾ƿ� ��ư ���̱�
		if(userId==null) {
			frame.getContentPane().add(loginBtn);
		}else {
			frame.getContentPane().add(logoutBtn);
		}
		
		// �α׾ƿ� ��ư �׼�
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage();
				frame.dispose();
			}
		});

	}

	// ������
	public MainPage() {
		this(null);
	}

}
