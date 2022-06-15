package staywithus;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// 0322 ���� ������ : 1. getId setId �޼ҵ� ����


public class Login {
	LoginMgr mgr = new LoginMgr();

	private JFrame frame;
	private JTextField logIdTf;
	private JTextField pwdTf;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		//�޼ҵ� ȣ��
		initialize();
		
	}


	//������ ����
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 500, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //�˾��� ȭ�� ����� �߱�
		frame.setTitle("�α���");
		frame.setVisible(true);
		
		
		//panel ����
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 462, 556);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//��� panel (�α��� Text)
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 438, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		//�ϴ� panel
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 108, 438, 438);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		//�α��� Label
		JLabel loginLb = new JLabel("�α���");
		loginLb.setHorizontalAlignment(SwingConstants.CENTER);
		loginLb.setFont(new Font("���� ���", Font.BOLD, 34));
		loginLb.setBounds(0, 0, 438, 89);
		panel_1.add(loginLb);
		
		//���̵� Label
		JLabel logIdLb = new JLabel("���̵�");
		logIdLb.setBounds(105, 29, 180, 40);
		logIdLb.setFont(new Font("���� ���", Font.BOLD, 16));
		panel_2.add(logIdLb);
		
		//���̵� �ؽ�Ʈ�ʵ�
		logIdTf = new JTextField();
		logIdTf.setBounds(105, 67, 216, 31);
		panel_2.add(logIdTf);
		logIdTf.setColumns(10);
		
		//��й�ȣ Label
		JLabel logPwdLb = new JLabel("��й�ȣ");
		logPwdLb.setBounds(105, 121, 180, 31);
		logPwdLb.setFont(new Font("���� ���", Font.BOLD, 16));
		panel_2.add(logPwdLb);
		
		//��й�ȣ �ؽ�Ʈ�ʵ�
		TextField logPwdTf = new TextField();
		logPwdTf.setBounds(105, 155, 216, 31);
		panel_2.add(logPwdTf);
		logPwdTf.setColumns(10);
		logPwdTf.setEchoChar('*');//��й�ȣ �Է½� *�� ���
		
		
		//�α��� ��ư Ŭ�� ��, DB���̽� �����Ǿ� �α��� ���� or ���п��� Ȯ��
		JButton loginBtn = new JButton("�α���");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				//Ŭ�� �̺�Ʈ 
				if(obj == loginBtn) {
					String userId = logIdTf.getText().trim();
					String userPwd = logPwdTf.getText().trim();
					
					String logSucId = mgr.loginChk(userId, userPwd);
					if(logSucId != null/*true*/) {
						// �α��� ������ ���
						JOptionPane.showMessageDialog(null, userId + "�� �α��� �����Ͽ����ϴ�.");
						frame.dispose();
						MainPage mainPage = new MainPage(userId); //new MainPage(); �α��� ���� �� ����ȭ������ �̵�
					
					}else {
						JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��Ȯ���� �ʽ��ϴ�.");
						logIdTf.setText("");//���̵� �Է� ĭ ����
						logPwdTf.setText("");//��й�ȣ �Է� ĭ ����
					}
				}
			}

			
		});
		
		
		loginBtn.setFont(new Font("���� ���", Font.BOLD, 16));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(Color.BLACK);
		loginBtn.setBounds(105, 240, 216, 40);
		panel_2.add(loginBtn);
		
		/*���̵� ã�� ��ư - Ŭ���� ���̵�ã�� ȭ������ ��ȯ 
		JButton idfbutton = new JButton("���̵�");
		idfbutton.setForeground(Color.BLACK);
		idfbutton.setFont(new Font("���� ���", Font.BOLD, 13));
		idfbutton.setBackground(Color.WHITE);
		idfbutton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			}
		});
		idfbutton.setBounds(105, 327, 82, 23);
		panel_2.add(idfbutton);*/
		
		/*��й�ȣ ã�� ��ư - Ŭ���� ��й�ȣ ã�� ȭ������ ��ȯ.
		JButton pwdfbutton = new JButton("��й�ȣã��");
		pwdfbutton.setFont(new Font("���� ���", Font.BOLD, 13));
		pwdfbutton.setBackground(Color.WHITE);
		pwdfbutton.setBounds(185, 327, 136, 23);
		panel_2.add(pwdfbutton);*/
		
		//ȸ������ ��ư - Ŭ���� ȸ������ ȭ������ ��ȯ 
		JButton joinbutton = new JButton("ȸ������");
		joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinFrame();//ȸ������ â���� �̵�
			}
		});
		joinbutton.setFont(new Font("���� ���", Font.BOLD, 16));
		joinbutton.setBackground(Color.WHITE);
		joinbutton.setBounds(105, 314, 216, 40);
		panel_2.add(joinbutton);
		
		//Ȩ ��ư -> Ŭ�� �� ����ȭ������ �̵�
		JButton homeBtn = new JButton("Home");
		homeBtn.setForeground(Color.BLACK);
		homeBtn.setFont(new Font("���� ���", Font.PLAIN, 10));
		homeBtn.setBackground(Color.WHITE);
		homeBtn.setBounds(10, 570, 67, 23);
		frame.getContentPane().add(homeBtn);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ȩ���� ���ư���");
				new MainPage();
				frame.dispose();
			}
		});

		frame.setVisible(true);
		frame.validate(); // ���ΰ�ħ
	}
	
	
}
