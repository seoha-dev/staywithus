/* 회원가입창 | 마지막 수정날짜: 2022-03-25 | 마지막 수정인: 김서하 */
//회원가입시 mode값은 기본 0 (사용자) 로 설정되어있음
package staywithus;

	import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

	@SuppressWarnings("serial")
	public class JoinFrame extends JFrame {
		
		LoginMgr mgr = new LoginMgr();

		private JPanel p1;
		private JLabel titleLb, emailLb, genderLb, phoneLb, idLb, pwdLb, pwd2Lb, nameLb, birthdayLb; 
		private JButton joinBtn, idCheckBtn;
		private JTextField idTf, nameTf, emailTf, birthdayTf, phoneTf;
		private JPasswordField pwdTf, pwdTf2; // pwdTf2 = 비밀번호 확인
		private JRadioButton femaleRB, maleRB;
		private ButtonGroup group;


		// 메인
		public static void main(String[] args) {
	
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new JoinFrame();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		// 텍스트필드 글자수 제한걸기 -JTextField
		class JTextFieldLimit extends PlainDocument {

			public int limit;

			public JTextFieldLimit(int limit) { // 생성자
				super();
				this.limit = limit;
			}

			public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
				if (str == null)
					return;
				if (getLength() + str.length() <= limit)
					super.insertString(offset, str, attr);
			}
		}
		
		// Create the frame
		public JoinFrame() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//메인페이지에서 회원가입 창 닫아도 메인페이지 같이 안 닫히게 수정
			setSize(430, 700);
			setLocationRelativeTo(null);
			
			// Panel 셋팅
			p1 = new JPanel();
			p1.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(p1);
			p1.setBackground(Color.white);
			p1.setLayout(null);
			
			// font 설정
			Font f1 = new Font("맑은 고딕", Font.BOLD, 20); //타이틀폰트
			Font f2 = new Font("맑은 고딕", Font.BOLD, 12); //라벨폰트
			Font f3 = new Font("맑은 고딕", Font.BOLD, 12); //텍스트필드폰트
		
			// "회원가입" 타이틀 라벨
			titleLb = new JLabel("회원가입");
			titleLb.setFont(f1);
			titleLb.setBounds(159, 41, 101, 30);
			add(titleLb);
			
			/*-- 라벨과 텍스트필드 y값 각각 간격 50씩--*/
			// "아이디" 라벨
			idLb = new JLabel("아이디");
			idLb.setFont(f2);
			idLb.setBounds(69, 113, 69, 20);
			add(idLb);
			// "아이디" 텍스트필드
			idTf = new JTextField();
			idTf.setColumns(10);
			idTf.setBounds(159, 106, 90, 30);
			idTf.setDocument(new JTextFieldLimit(8));
			add(idTf);
			
			// 아이디 "중복확인" 버튼
			idCheckBtn = new JButton("중복확인");
			idCheckBtn.setFont(f3);
			idCheckBtn.setBounds(255, 106, 85, 30);
			idCheckBtn.setForeground(Color.WHITE);
			idCheckBtn.setBackground(Color.BLACK);
			add(idCheckBtn);
			
			// "비밀번호" 라벨
			pwdLb = new JLabel("비밀번호");
			pwdLb.setFont(f2);
			pwdLb.setBounds(69, 163, 69, 20);
			add(pwdLb);
			// "비밀번호" 텍스트필드
			pwdTf = new JPasswordField();
			pwdTf.setColumns(10);
			pwdTf.setBounds(159, 156, 186, 30);
			pwdTf.setEchoChar('●');
			pwdTf.setDocument(new JTextFieldLimit(8));
			add(pwdTf);
			
			// "비밀번호 확인" 라벨
			pwd2Lb = new JLabel("비밀번호 확인");
			pwd2Lb.setFont(f2);
			pwd2Lb.setBounds(69, 213, 80, 20);
			add(pwd2Lb);
			// "비밀번호 확인" 텍스트필드
			pwdTf2 = new JPasswordField();
			pwdTf2.setColumns(10);
			pwdTf2.setBounds(159, 206, 186, 30);
			pwdTf2.setEchoChar('●');
			pwdTf2.setDocument(new JTextFieldLimit(8));
			add(pwdTf2);
			
			// "이름" 라벨
			nameLb = new JLabel("이름");
			nameLb.setFont(f2);
			nameLb.setBounds(69, 263, 69, 20);
			add(nameLb);
			// "이름" 텍스트필드 
			nameTf = new JTextField();
			nameTf.setColumns(10);
			nameTf.setBounds(159, 256, 186, 30);
			add(nameTf);
			
			// "생년월일" 라벨
			birthdayLb = new JLabel("생년월일");
			birthdayLb.setFont(f2);
			birthdayLb.setBounds(69, 313, 69, 20);
			add(birthdayLb);
			// "생년월일" 텍스트필드
			birthdayTf = new JTextField();
			birthdayTf.setColumns(10);
			birthdayTf.setBounds(159, 306, 186, 30);
			birthdayTf.setDocument(new JTextFieldLimit(10));
			add(birthdayTf);
			
			
			// "성별" 라벨
			genderLb = new JLabel("성별");
			genderLb.setFont(f2);
			genderLb.setBounds(69, 363, 69, 20);
			add(genderLb);
			// "성별" radio 
			femaleRB = new JRadioButton("여성",true);
			femaleRB.setBounds(159, 356, 50, 30);
			femaleRB.setBackground(Color.WHITE);
			add(femaleRB);
			maleRB = new JRadioButton("남성");
			maleRB.setBounds(280, 356, 50, 30);
			maleRB.setBackground(Color.WHITE);
			add(maleRB);
			// "성별" 그룹화
			group = new ButtonGroup();
			group.add(femaleRB);
			group.add(maleRB);
			
			// "이메일" 라벨
			emailLb = new JLabel("이메일");
			emailLb.setFont(f2);
			emailLb.setBounds(69, 413, 69, 20);
			add(emailLb);
			// "이메일" 텍스트필드
			emailTf = new JTextField();
			emailTf.setColumns(10);
			emailTf.setBounds(159, 406, 186, 30);
			add(emailTf);
			
			// "연락처" 라벨
			phoneLb = new JLabel("연락처");
			phoneLb.setFont(f2);
			phoneLb.setBounds(69, 463, 69, 20);
			add(phoneLb);
			// "연락처" 텍스트필드
			phoneTf = new JTextField();
			phoneTf.setColumns(10);
			phoneTf.setBounds(159, 456, 186, 30);
			phoneTf.setDocument(new JTextFieldLimit(13));
			add(phoneTf);
			
			// "회원가입 완료" 버튼
			joinBtn = new JButton("회원가입 완료");
			joinBtn.setBounds(147, 533, 139, 30);
			joinBtn.setFont(f3);
			joinBtn.setForeground(Color.WHITE);
			joinBtn.setBackground(Color.BLACK);
			add(joinBtn);
			
			
//			birthdayTf.setText("YYYY-MM-DD");
			setVisible(true);
			
			
			/* 기능설계 */
			
			// 회원가입완료 버튼 액션
			joinBtn.addActionListener(new ActionListener() {
				
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					if (idTf.getText().equals("")) { // 아이디 안씀
						JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
					} else if (pwdTf.getText().equals("")) { // 비밀번호 안씀
						JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
					} else if (!pwdTf2.getText().equals(pwdTf.getText())) { // 비밀번호 확인 불일치
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					} else if (nameTf.getText().equals("")) { // 이름 안씀
						JOptionPane.showMessageDialog(null, "이름을 입력하세요.");
					} else if (birthdayTf.getText().equals("")) { // 생일 안씀
						JOptionPane.showMessageDialog(null, "생일을 입력하세요.");
					} else if (birthdayTf.getText().length() < 8)	 {
						JOptionPane.showMessageDialog(null, "YYYYMMDD 형식으로 생일을 입력하세요.");
					} else if (emailTf.getText().equals("")) { // 이메일 안씀
						JOptionPane.showMessageDialog(null, "이메일을 입력하세요.");
					} else if (phoneTf.getText().equals("")) { // 연락처 안씀
						JOptionPane.showMessageDialog(null, "연락처를 입력하세요");
					} else if (mgr.idChk(idTf.getText().trim())) {
						System.out.println("아이디중복확인: DB에 있음");
						JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요.");
					} else { // 전부 다 쓴거 맞지..? 
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
						UserBean bean = new UserBean();
						bean.setId(idTf.getText());
						bean.setPwd(pwdTf.getText());
						bean.setName(nameTf.getText());
						bean.setEmail(emailTf.getText());
						bean.setPhone(phoneTf.getText());
						bean.setBirthday(birthdayTf.getText());
						// radio박스에서 성별값 찾아오기
						String gen = "여성"; // gender가 not null 이어서 기본 여성으로 정의
						bean.setGender(gen);
						if (femaleRB.isSelected()) {
							gen = "여";
						} else {
							gen = "남";
						}
						if (mgr.userSign(bean)) {
							System.out.println("[joinFrame] "+ idTf.getText()+ "회원 등록완료");
							dispose(); // --등록완료 후 창 종료
						}	
					}
				}

			});
			
			// 생일 숫자만
			birthdayTf.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			
			
			// 아이디 중복확인 버튼 액션
			idCheckBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					if (obj == idCheckBtn) {
						if (mgr.idChk(idTf.getText().trim()/* true */)) {
							System.out.println("아이디중복확인: DB에 있음");
							JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디입니다.");
							idTf.setText("");
						} else {
							System.out.println("아이디중복확인: DB에 없음");
							JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
						}
					} 
				}
			});
		
		}
	}