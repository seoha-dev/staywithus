/* 사용자 마이페이지 회원탈퇴 | 마지막 수정날짜: 2022-03-25 | 마지막 수정인: 김서하 */

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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class DeleteUser {

	LoginMgr mgr = new LoginMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, pwdLb, noticeLb; 
	private JButton updateBtn, listBtn, delBtn, homeBtn, delUserBtn;
	private JPasswordField pwdTf;
	private JCheckBox notedCb;
	
	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser updateUser = new DeleteUser();
					updateUser.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자 (매개변수)
	public DeleteUser(String userId) {
		
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
		Font f4 = new Font("맑은 고딕", Font.PLAIN, 11); 
		

		jf.setTitle(userId+"  회원탈퇴");
		p1 = new JPanel();
		
		//파넬꾸미기
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		//정보수정버튼
		updateBtn = new JButton("내정보수정");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.white);
		updateBtn.setBackground(Color.black);
		p1.add(updateBtn);
		
		//예약조회버튼
		listBtn = new JButton("내예약조회");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.white);
		listBtn.setBackground(Color.black);
		p1.add(listBtn);
		
		//회원탈퇴버튼(현재선택됨)
		delUserBtn = new JButton ("회원탈퇴");
		p1.add(delUserBtn);
		delUserBtn.setBounds(20, 300, 100, 30);
		delUserBtn.setFont(f2);
		delUserBtn.setForeground(Color.black);
		delUserBtn.setBackground(Color.white);
		delUserBtn.setEnabled(false);
		
		//홈버튼(메인으로)
		homeBtn = new JButton("홈으로");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		
		//타이틀라벨
		titleLb = new JLabel("회원 탈퇴");
		titleLb.setFont(f1);
		titleLb.setBounds(470, 70, 300, 100);
		p1.add(titleLb);
		
		//"회원탈퇴 동의" 라벨
		noticeLb = new JLabel("회원탈퇴시 계정의 모든 정보와 예약 및 결제내역이 삭제됩니다.");
		noticeLb.setFont(f3);
		noticeLb.setBounds(390, 300, 500, 20);
		p1.add(noticeLb);
		//"회원탈퇴 동의" 체크박스
		notedCb = new JCheckBox("본인은 상기내용을 숙지하였습니다.");
		p1.add(notedCb);
		notedCb.setFont(f4);
		notedCb.setBackground(Color.white);
		notedCb.setBounds(450, 330, 500, 20);
		
		//"비밀번호" 라벨
		pwdLb = new JLabel("비밀번호");
		pwdLb.setFont(f3);
		pwdLb.setBounds(450, 370, 70, 20);
		p1.add(pwdLb);
		//"비밀번호" 텍스트필드
		pwdTf = new JPasswordField();
		pwdTf.setColumns(10);
		pwdTf.setBounds(450, 390, 240, 20);
		pwdTf.setEchoChar('●');
		p1.add(pwdTf);				
		
		//"탈퇴" 버튼
		delBtn = new JButton("회원탈퇴");
		delBtn.setBounds(450, 650, 240, 40);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.WHITE);
		delBtn.setBackground(Color.BLACK);
		p1.add(delBtn);
				
		/*기능 구현*/
			
		//회원탈퇴
		delBtn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				// 비밀번호, 이메일, 연락처 빈 칸으로 둔 경우
				if (!notedCb.isSelected()) { // 체크안함
					JOptionPane.showMessageDialog(null, "[회원탈퇴] 안내를 읽고 내용을 숙지했다는 체크를 해주세요.");
				} else if (mgr.loginChk(userId, pwdTf.getText()) != null/* true */) { // 비밀번호 제대로 씀
					mgr.delUser(userId);
					JOptionPane.showMessageDialog(null, "[회원탈퇴] " + userId + " 님의 탈퇴가 완료되었습니다.");
					new MainPage();
					jf.dispose();
				} else if (mgr.loginChk(userId, pwdTf.getText()) == null) { //비밀번호 제대로 안씀
					JOptionPane.showMessageDialog(null, "[회원탈퇴] 비밀번호가 정확하지 않습니다.");
					
				} else {	
					JOptionPane.showMessageDialog(null,"[회원탈퇴] "+ userId + "오류가 발생하였습니다.");
					System.out.println("[DeleteUser]" + userId + "의 회원탈퇴 중 오류가 발생하였습니다.");
				}
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
		
		//홈버튼액션: 메인으로
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();
			}
		});
		
		// 내정보수정버튼 액션 : 내정보수정 이동 
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateUser(userId);
				jf.dispose();
			}
		});
}

	// 생성자
	public DeleteUser() {
		this(null);
	}

}
		
	
