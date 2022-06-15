/* 사용자 마이페이지 내정보수정 | 마지막 수정날짜: 2022-03-25 | 마지막 수정인: 김서하 */

package staywithus;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UpdateUser {

	LoginMgr mgr = new LoginMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, pwdLb, newPwdLb, idLb, nameLb, emailLb, birthdayLb, genderLb, phoneLb; 
	private JButton updateBtn, listBtn, saveBtn, homeBtn, delUserBtn;
	private JTextField idTf, nameTf, emailTf, birthdayTf, phoneTf, genderTf;
	private JPasswordField pwdTf, newPwdTf;
	
	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUser updateUser = new UpdateUser();
					updateUser.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자 (매개변수)
	public UpdateUser(String userId) {
		
		// 기본 셋팅
		jf.setSize(1200,800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//모니터 크기
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//프레임의 크기
		Dimension fDim = jf.getSize();
		
		// 프레임의 왼쪽 모서리 좌표
		// 중앙좌표 : (모니터 크기 - 프레임 크기) / 2
		int x = (int)((dim.getWidth()-fDim.getWidth())/2);
		int y = (int)((dim.getHeight()- fDim.getHeight())/2);
		
		// 프레임 위치 시키기
		jf.setLocation(x, y);
		
		// font 설정
		Font f1 = new Font("맑은 고딕", Font.BOLD, 40); //타이틀 폰트
		Font f2 = new Font("맑은 고딕", Font.BOLD, 12); //버튼 폰트
		Font f3 = new Font("맑은 고딕", Font.BOLD, 12); //라벨 폰트
		

		jf.setTitle(userId+"  마이페이지");
		p1 = new JPanel();
		
		//파넬꾸미기
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		//정보수정버튼(현재선택됨)
		updateBtn = new JButton("내정보수정");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.black);
		updateBtn.setBackground(Color.white);
		updateBtn.setEnabled(false);
		p1.add(updateBtn);
		
		//예약조회버튼
		listBtn = new JButton("내예약조회");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.white);
		listBtn.setBackground(Color.black);
		p1.add(listBtn);
		
		//회원탈퇴버튼
		delUserBtn = new JButton ("회원탈퇴");
		p1.add(delUserBtn);
		delUserBtn.setBounds(20, 300, 100, 30);
		delUserBtn.setFont(f2);
		delUserBtn.setForeground(Color.white);
		delUserBtn.setBackground(Color.lightGray);
		
		//홈버튼(메인으로)
		homeBtn = new JButton("홈으로");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		
		//타이틀라벨
		titleLb = new JLabel("내 정보 수정");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);
		
		//"아이디" 라벨
		idLb = new JLabel("아이디");
		idLb.setFont(f3);
		idLb.setBounds(450, 200, 70, 20);
		p1.add(idLb);
		//"아이디" 텍스트필드
		idTf = new JTextField();
		idTf.setColumns(10);
		idTf.setBounds(450, 220, 240, 20);
		idTf.setText(userId);
		idTf.setEditable(false);
		p1.add(idTf);
		
		//"비밀번호" 라벨
		pwdLb = new JLabel("비밀번호");
		pwdLb.setFont(f3);
		pwdLb.setBounds(450, 250, 70, 20);
		p1.add(pwdLb);
		//"비밀번호" 텍스트필드
		pwdTf = new JPasswordField();
		pwdTf.setColumns(10);
		pwdTf.setBounds(450, 270, 240, 20);
		pwdTf.setEchoChar('●');
		p1.add(pwdTf);		
		
		//"새 비밀번호" 라벨
		newPwdLb = new JLabel("새 비밀번호");
		newPwdLb.setFont(f3);
		newPwdLb.setBounds(450, 300, 70, 20);
		p1.add(newPwdLb);
		//"새 비밀번호" 텍스트필드
		newPwdTf = new JPasswordField();
		newPwdTf.setColumns(10);
		newPwdTf.setBounds(450, 320, 240, 20);
		newPwdTf.setEchoChar('●');
		p1.add(newPwdTf);	
		
		//"이름" 라벨
		nameLb = new JLabel("이름");
		nameLb.setFont(f3);
		nameLb.setBounds(450, 350, 70, 20);
		p1.add(nameLb);
		//"이름" 텍스트필드
		nameTf = new JTextField();
		nameTf.setColumns(10);
		nameTf.setBounds(450, 370, 240, 20);
		nameTf.setEditable(false);
		p1.add(nameTf);
		
		//"생년월일" 라벨
		birthdayLb = new JLabel("생년월일");
		birthdayLb.setFont(f3);
		birthdayLb.setBounds(450, 400, 70, 20);
		p1.add(birthdayLb);
		//"생년월일" 텍스트필드
		birthdayTf = new JTextField();
		birthdayTf.setColumns(10);
		birthdayTf.setBounds(450, 420, 240, 20);
		birthdayTf.setEditable(false);
		p1.add(birthdayTf);
		
		//"성별" 라벨
		genderLb = new JLabel("성별");
		genderLb.setFont(f3);
		genderLb.setBounds(450, 450, 70, 20);
		p1.add(genderLb);
		//"성별" 텍스트필드 (굳이 radio로 해야하나? 너무 복잡해지지않나)
		genderTf = new JTextField();
		genderTf.setColumns(10);
		genderTf.setBounds(450, 470, 240, 20);
		genderTf.setEditable(false);
		p1.add(genderTf);
		
		//"이메일" 라벨
		emailLb = new JLabel("이메일");
		emailLb.setFont(f3);
		emailLb.setBounds(450, 500, 70, 20);
		p1.add(emailLb);
		//"이메일" 텍스트필드
		emailTf = new JTextField();
		emailTf.setColumns(10);
		emailTf.setBounds(450, 520, 240, 20);
		p1.add(emailTf);		
		
		//"연락처" 라벨
		phoneLb = new JLabel("연락처");
		phoneLb.setFont(f3);
		phoneLb.setBounds(450, 550, 70, 20);
		p1.add(phoneLb);
		//"연락처" 텍스트필드
		phoneTf = new JTextField();
		phoneTf.setColumns(10);
		phoneTf.setBounds(450, 570, 240, 20);
		p1.add(phoneTf);		
		
		//"저장" 버튼
		saveBtn = new JButton("저장");
		saveBtn.setBounds(450, 650, 240, 40);
		saveBtn.setFont(f2);
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setBackground(Color.BLACK);
		p1.add(saveBtn);
				
		/*기능 구현*/
		
		//id값으로 내 정보 받아오기
		nameTf.setText(mgr.userInfo(userId).getName());
		birthdayTf.setText(mgr.userInfo(userId).getBirthday());
		genderTf.setText(mgr.userInfo(userId).getGender());
		emailTf.setText(mgr.userInfo(userId).getEmail());
		phoneTf.setText(mgr.userInfo(userId).getPhone());
		
		// 정보 잘 받아옴?
		if (!nameTf.getText().equals("") && !birthdayTf.getText().equals("") && !genderTf.getText().equals("")) {
			System.out.println("[updateUser]: 회원정보(" + userId + ") 불러오기 성공");
		}else {
			System.out.println("[updateUser]: 회원정보(" + userId + ") 불러오기 실패");
		}
		
		
		//수정된 정보 저장
		saveBtn.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				//비밀번호, 이메일, 연락처 빈 칸으로 둔 경우
				if (pwdTf.getText().equals("")) { // 비밀번호 안씀
					JOptionPane.showMessageDialog(null, "[내정보수정] 비밀번호를 입력하세요.");
				} else if (emailTf.getText().equals("")) { // 이메일 안씀
					JOptionPane.showMessageDialog(null, "[내정보수정] 이메일을 입력하세요.");
				} else if (phoneTf.getText().equals("")) { // 연락처 안씀
					JOptionPane.showMessageDialog(null, "[내정보수정] 연락처를 입력하세요");
				} else if (!newPwdTf.getText().equals("")) { // 새 비밀번호 씀
					if (pwdTf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "[내정보수정] 기존 비밀번호를 입력하세요.");
					} else if (mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim()).length()>0/* true */) { // 비밀번호와 아이디 체크
						UserBean bean = new UserBean();
						bean.setId(idTf.getText());
						bean.setPwd(newPwdTf.getText());
						bean.setEmail(emailTf.getText());
						bean.setPhone(phoneTf.getText());
						mgr.userUpdt(bean);
						System.out.println("새 비밀번호:"+newPwdTf.getText());
						pwdTf.setText("");
						newPwdTf.setText("");
						JOptionPane.showMessageDialog(null, "[내정보수정] 비밀번호가 재설정되었습니다.");
						System.out.println("[UpdateUser] 회원(" + userId + ")비밀번호 재설정 완료");
					} else { // 비밀번호 아이디 불일치
						JOptionPane.showMessageDialog(null, "[내정보수정] 비밀번호가 일치하지 않습니다.");
					}
				} else if (newPwdTf.getText().equals("")) { // 새 비밀번호 없이 다른 정보만 변경저장
					if(mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim()).length()>0) {
						UserBean bean = new UserBean();
						bean.setId(idTf.getText());
						bean.setPwd(pwdTf.getText()); //기존 비밀번호 그대로 덮어씌움
						bean.setEmail(emailTf.getText());
						bean.setPhone(phoneTf.getText());
						mgr.userUpdt(bean);
						JOptionPane.showMessageDialog(null, "[내정보수정] 회원정보가 수정되었습니다.");	
						System.out.println("[UpdateUser] 회원정보 ("+userId+") 재설정 완료");
					}else {
						JOptionPane.showMessageDialog(null, "[내정보수정] 비밀번호가 일치하지 않습니다.");
					}
				}
		// 새로고침
		jf.validate();
	}
});
		// 예약조회버튼 액션: 내 예약내역 조회
		listBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReservationUser(userId);
				jf.dispose();
			}
		});
		
		// 회원탈퇴버튼 액션: 회원탈퇴창으로 이동
		delUserBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteUser(userId);
				jf.dispose();
			}
		});
		
		
		//홈버튼액션: 메인으로
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();
			}
		});
		
}

	// 생성자
	public UpdateUser() {
		this(null);
	}

}
		
	
