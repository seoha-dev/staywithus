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
// 0322 수정 박혜빈 : 1. getId setId 메소드 없앰


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
		//메소드 호출
		initialize();
		
	}


	//프레임 설정
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 500, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //팝업시 화면 가운데로 뜨기
		frame.setTitle("로그인");
		frame.setVisible(true);
		
		
		//panel 생성
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 462, 556);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//상단 panel (로그인 Text)
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 438, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		//하단 panel
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 108, 438, 438);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		//로그인 Label
		JLabel loginLb = new JLabel("로그인");
		loginLb.setHorizontalAlignment(SwingConstants.CENTER);
		loginLb.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		loginLb.setBounds(0, 0, 438, 89);
		panel_1.add(loginLb);
		
		//아이디 Label
		JLabel logIdLb = new JLabel("아이디");
		logIdLb.setBounds(105, 29, 180, 40);
		logIdLb.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		panel_2.add(logIdLb);
		
		//아이디 텍스트필드
		logIdTf = new JTextField();
		logIdTf.setBounds(105, 67, 216, 31);
		panel_2.add(logIdTf);
		logIdTf.setColumns(10);
		
		//비밀번호 Label
		JLabel logPwdLb = new JLabel("비밀번호");
		logPwdLb.setBounds(105, 121, 180, 31);
		logPwdLb.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		panel_2.add(logPwdLb);
		
		//비밀번호 텍스트필드
		TextField logPwdTf = new TextField();
		logPwdTf.setBounds(105, 155, 216, 31);
		panel_2.add(logPwdTf);
		logPwdTf.setColumns(10);
		logPwdTf.setEchoChar('*');//비밀번호 입력시 *로 출력
		
		
		//로그인 버튼 클릭 시, DB베이스 연동되어 로그인 성공 or 실패여부 확인
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				//클릭 이벤트 
				if(obj == loginBtn) {
					String userId = logIdTf.getText().trim();
					String userPwd = logPwdTf.getText().trim();
					
					String logSucId = mgr.loginChk(userId, userPwd);
					if(logSucId != null/*true*/) {
						// 로그인 성공할 경우
						JOptionPane.showMessageDialog(null, userId + "님 로그인 성공하였습니다.");
						frame.dispose();
						MainPage mainPage = new MainPage(userId); //new MainPage(); 로그인 성공 시 메인화면으로 이동
					
					}else {
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 정확하지 않습니다.");
						logIdTf.setText("");//아이디 입력 칸 리셋
						logPwdTf.setText("");//비밀번호 입력 칸 리셋
					}
				}
			}

			
		});
		
		
		loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(Color.BLACK);
		loginBtn.setBounds(105, 240, 216, 40);
		panel_2.add(loginBtn);
		
		/*아이디 찾기 버튼 - 클릭시 아이디찾기 화면으로 전환 
		JButton idfbutton = new JButton("아이디");
		idfbutton.setForeground(Color.BLACK);
		idfbutton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		idfbutton.setBackground(Color.WHITE);
		idfbutton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			}
		});
		idfbutton.setBounds(105, 327, 82, 23);
		panel_2.add(idfbutton);*/
		
		/*비밀번호 찾기 버튼 - 클릭시 비밀번호 찾기 화면으로 전환.
		JButton pwdfbutton = new JButton("비밀번호찾기");
		pwdfbutton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		pwdfbutton.setBackground(Color.WHITE);
		pwdfbutton.setBounds(185, 327, 136, 23);
		panel_2.add(pwdfbutton);*/
		
		//회원가입 버튼 - 클릭시 회원가입 화면으로 전환 
		JButton joinbutton = new JButton("회원가입");
		joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinFrame();//회원가입 창으로 이동
			}
		});
		joinbutton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		joinbutton.setBackground(Color.WHITE);
		joinbutton.setBounds(105, 314, 216, 40);
		panel_2.add(joinbutton);
		
		//홈 버튼 -> 클릭 시 메인화면으로 이동
		JButton homeBtn = new JButton("Home");
		homeBtn.setForeground(Color.BLACK);
		homeBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		homeBtn.setBackground(Color.WHITE);
		homeBtn.setBounds(10, 570, 67, 23);
		frame.getContentPane().add(homeBtn);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("홈으로 돌아가기");
				new MainPage();
				frame.dispose();
			}
		});

		frame.setVisible(true);
		frame.validate(); // 새로고침
	}
	
	
}
