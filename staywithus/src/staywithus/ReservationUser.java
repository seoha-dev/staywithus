/* 사용자 마이페이지 내예약조회 | 마지막 수정날짜: 2022-03-25 | 마지막 수정인: 김서하 */

package staywithus;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationUser {

	LoginMgr mgr = new LoginMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, pwdLb; 
	private JButton updateBtn, listBtn, cancelBtn, homeBtn, delUserBtn;
	private JPasswordField pwdTf;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result;
	private JTable table;
	private JScrollPane sp;
	
	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationUser reservationUser = new ReservationUser();
					reservationUser.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자 (매개변수)
	@SuppressWarnings({ "unchecked", "serial" })
	public ReservationUser(String userId) {
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
		
		jf.setTitle("[회원예약조회]: "+userId);
		p1 = new JPanel();
		
		model = new DefaultTableModel() {
			// 테이블 셀 수정 불가 (디폴트: 수정가능)
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		//파넬 설정
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		// 예약데이터 테이블
		result = mgr.resInfo(userId);
		title = new Vector<>();
		title.add("id");
		title.add("객실");
		title.add("체크인");
		title.add("체크아웃");
		title.add("예약인원");
		title.add("결제 상태");
		title.add("결제 금액");
		title.add("예약번호");
		model.setDataVector(result, title);
		
		sp.setBounds(200, 200, 800, 400);
		p1.add(sp);
		
		//정보수정버튼 --> 내정보수정으로 이동
		updateBtn = new JButton("내정보수정");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.white);
		updateBtn.setBackground(Color.black);
		p1.add(updateBtn);

		//예약조회버튼 --> 현재페이지버튼(비활성화)
		listBtn = new JButton("내예약조회");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.black);
		listBtn.setBackground(Color.white);
		listBtn.setEnabled(false);
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
		titleLb = new JLabel("내 예약 조회");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

		//"예약취소" 버튼
		cancelBtn = new JButton("예약취소");
		cancelBtn.setBounds(450, 650, 240, 40);
		cancelBtn.setFont(f2);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBackground(Color.BLACK);
		p1.add(cancelBtn);
		
		//비밀번호 라벨
		pwdLb = new JLabel("비밀번호 입력");
		p1.add(pwdLb);
		pwdLb.setFont(f2);
		pwdLb.setBounds(450, 600, 100, 40);
		//비밀번호 텍스트필드
		pwdTf = new JPasswordField();
		p1.add(pwdTf);
		pwdTf.setEchoChar('●');
		pwdTf.setBounds(540, 614, 140, 20);
		
		
		
		/*기능 구현*/
		
		// id값으로 내 예약정보불러오기
		
		// 예약 취소 LoginMgr로 삭제
		cancelBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(mgr.loginChk(userId, pwdTf.getText())!=null/*true*/) {
					//아이디 비밀번호 일치
					int row = table.getSelectedRow();
					String res_no = (String)table.getValueAt(row, 7);
					mgr.ur_del(res_no);
					//삭제 후 디비값 불러오기
					@SuppressWarnings("rawtypes")
					Vector result = mgr.resInfo(userId);
					model.setDataVector(result, title);
				} else { // 비밀번호 일치 안 했을때
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
			}
		});
		
				
		//정보수정버튼 액션 (내정보수정으로 이동)
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateUser(userId);
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
		
		//홈버튼 액션 (메인으로 이동)
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();
			}
		});
}

	// 생성자
	public ReservationUser() {
		this(null);
	}
}
		
	
