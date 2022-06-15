/*-- 관리자페이지 예약관리 | 마지막 수정날짜: 2022-03-25 | 마지막 수정인: 김서하--*/

package staywithus;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();

	private JPanel p1;
	private JLabel titleLb, res_noLb, idLb, r_roomLb, sdLb, edLb, hcLb, r_statLb, p_costLb, searchLb;
	private JButton uptBtn, delBtn, homeBtn, searchBtn, freshBtn;
	private JTextField idTf, r_roomTf, sdTf, edTf, hcTf, r_statTf, p_costTf, res_noTf, searchTf;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result;
	private JTable table;
	private JScrollPane sp;
	private java.util.Date utilStartDate;
	private java.util.Date utilEndDate;
	private int p_cost;

	// 메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationAdmin reservationAdmin = new ReservationAdmin();
					reservationAdmin.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자 (매개변수)
	@SuppressWarnings({ "serial", "unchecked" })
	public ReservationAdmin(String userId) {
		// 기본 셋팅
		jf.setSize(1200, 800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 모니터 크기
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// 프레임의 크기
		Dimension fDim = jf.getSize();

		// 프레임의 왼쪽 모서리 좌표
		// 중앙좌표 : (모니터 크기 - 프레임 크기) / 2
		int x = (int) ((dim.getWidth() - fDim.getWidth()) / 2);
		int y = (int) ((dim.getHeight() - fDim.getHeight()) / 2);

		// 프레임 위치 시키기
		jf.setLocation(x, y);

		// font 설정
		Font f1 = new Font("맑은 고딕", Font.BOLD, 40); // 타이틀 폰트
		Font f2 = new Font("맑은 고딕", Font.BOLD, 12); // 버튼 폰트
		Font f3 = new Font("맑은 고딕", Font.BOLD, 12); // 라벨 폰트

		jf.setTitle("관리자페이지(" + userId + ")");
		p1 = new JPanel();

		model = new DefaultTableModel() {
			// 테이블 셀 수정 불가 (디폴트: 수정가능)
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		sp = new JScrollPane(table);

		// 파넬 설정
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		// 예약데이터 테이블
		result = mgr.selectAll();
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

		// 테이블 위치
		sp.setBounds(450, 200, 550, 400);
		p1.add(sp);

		// 홈버튼
		homeBtn = new JButton("홈으로");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);

		// 타이틀라벨
		titleLb = new JLabel("전체 예약 조회");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

		// 아이디 라벨
		idLb = new JLabel("아이디");
		p1.add(idLb);
		idLb.setFont(f3);
		idLb.setBounds(150, 200, 40, 20);

		// 아이디 텍스트필드
		idTf = new JTextField();
		p1.add(idTf);
		idTf.setBounds(220, 200, 150, 20);
		idTf.setEditable(false);

		// 객실 라벨
		r_roomLb = new JLabel("객실");
		p1.add(r_roomLb);
		r_roomLb.setFont(f3);
		r_roomLb.setBounds(150, 230, 40, 20);

		// 객실 텍스트필드
		r_roomTf = new JTextField();
		p1.add(r_roomTf);
		r_roomTf.setBounds(220, 230, 150, 20);

		// 체크인 라벨
		sdLb = new JLabel("체크인");
		p1.add(sdLb);
		sdLb.setFont(f3);
		sdLb.setBounds(150, 260, 40, 20);
		// 체크인 텍스트필드
		sdTf = new JTextField();
		p1.add(sdTf);
		sdTf.setBounds(220, 260, 150, 20);

		// 체크아웃 라벨
		edLb = new JLabel("체크아웃");
		p1.add(edLb);
		edLb.setFont(f3);
		edLb.setBounds(150, 290, 50, 20);
		// 체크아웃 텍스트필드
		edTf = new JTextField();
		p1.add(edTf);
		edTf.setBounds(220, 290, 150, 20);

		// 예약인원 라벨
		hcLb = new JLabel("에약인원");
		p1.add(hcLb);
		hcLb.setFont(f3);
		hcLb.setBounds(150, 320, 50, 20);
		// 예약인원 텍스트필드
		hcTf = new JTextField();
		p1.add(hcTf);
		hcTf.setBounds(220, 320, 150, 20);

		// 결제 상태 라벨
		r_statLb = new JLabel("결제상태");
		p1.add(r_statLb);
		r_statLb.setFont(f3);
		r_statLb.setBounds(150, 350, 50, 20);
		// 결제상태 텍스트필드
		r_statTf = new JTextField();
		p1.add(r_statTf);
		r_statTf.setBounds(220, 350, 150, 20);
		r_statTf.setEditable(false);

		// 결제금액 라벨
		p_costLb = new JLabel("결제금액");
		p1.add(p_costLb);
		p_costLb.setFont(f3);
		p_costLb.setBounds(150, 380, 50, 20);

		// 결제금액 텍스트필드
		p_costTf = new JTextField();
		p1.add(p_costTf);
		p_costTf.setBounds(220, 380, 150, 20);
		p_costTf.setEditable(false);

		// 예약번호 라벨
		res_noLb = new JLabel("예약번호");
		p1.add(res_noLb);
		res_noLb.setFont(f3);
		res_noLb.setBounds(150, 410, 50, 20);
		// 예약번호 텍스트필드
		res_noTf = new JTextField();
		p1.add(res_noTf);
		res_noTf.setBounds(220, 410, 150, 20);
		res_noTf.setEditable(false);

		// 예약 추가 버튼
//		addBtn = new JButton("예약 추가");
//		addBtn.setBounds(200, 620, 100, 30);
//		addBtn.setFont(f2);
//		addBtn.setForeground(Color.black);
//		addBtn.setBackground(Color.white);
//		p1.add(addBtn);

		// 예약 수정 버튼
		uptBtn = new JButton("예약 수정");
		uptBtn.setBounds(150, 500, 220, 30);
		uptBtn.setFont(f2);
		uptBtn.setForeground(Color.black);
		uptBtn.setBackground(Color.white);
		p1.add(uptBtn);

		// 예약 삭제 버튼
		delBtn = new JButton("예약 삭제");
		delBtn.setBounds(150, 550, 220, 30);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.black);
		delBtn.setBackground(Color.white);
		p1.add(delBtn);

		// 검색 라벨
		searchLb = new JLabel("예약자 ID를 입력하세요");
		p1.add(searchLb);
		searchLb.setFont(f3);
		searchLb.setBounds(450, 603, 250, 20);

		// 검색 텍스트필드
		searchTf = new JTextField();
		p1.add(searchTf);
		searchTf.setBounds(585, 605, 150, 20);

		// 검색 버튼
		searchBtn = new JButton("검색");
		p1.add(searchBtn);
		searchBtn.setFont(f2);
		searchBtn.setBounds(740, 605, 60, 20);
		searchBtn.setForeground(Color.white);
		searchBtn.setBackground(Color.black);

		// 새로고침 버튼
		freshBtn = new JButton("새로고침");
		p1.add(freshBtn);
		freshBtn.setFont(f2);
		freshBtn.setBounds(915, 605, 85, 20);
		freshBtn.setForeground(Color.black);
		freshBtn.setBackground(Color.white);

		/* 기능 구현 */

		// 테이블 클릭시 선택cell값 -> 텍스트필드로 끌어오기
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				String id = (String) table.getValueAt(row, 0);
				String r_room = (String) table.getValueAt(row, 1);
				String sd = (String) table.getValueAt(row, 2);
				String ed = (String) table.getValueAt(row, 3);
				String hc = (String) table.getValueAt(row, 4);
				String r_stat = (String) table.getValueAt(row, 5);
				String p_cost = (String) table.getValueAt(row, 6);
				String res_no = (String) table.getValueAt(row, 7);

				idTf.setText(id);
				r_roomTf.setText(r_room);
				sdTf.setText(sd);
				edTf.setText(ed);
				hcTf.setText(hc);
				r_statTf.setText(r_stat);
				p_costTf.setText(p_cost);
				res_noTf.setText(res_no);
				System.out.println("[ReservationAdmin]선택된 예약번호:" + res_no);

			}
		});

		// 예약수정: 수정시 기존예약조회 -> 기존예약없으면 수정
		uptBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 수정원하는 값 가져오기
				String r_room =r_roomTf.getText(); // 예약 룸
				String headcount = hcTf.getText(); // 예약인원
				String res_no = res_noTf.getText(); // 예약번호
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Date형식으로 바꾸는 클래스
				// String -> java.util.date
				try {
					utilStartDate = sdf.parse(sdTf.getText().trim().replaceAll("[^0-9]", ""));
					utilEndDate = sdf.parse(edTf.getText().trim().replaceAll("[^0-9]", ""));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				// java.util.date -> java.sql.date 변환
				java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
				java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());
				
				ReservationBean bean = new ReservationBean();
				// 종료일이 시작일보다 빠르게 예약수정하는 경우
				if(Integer.parseInt(sdTf.getText().trim().replaceAll("[^0-9]", "")) >
				Integer.parseInt(edTf.getText().trim().replaceAll("[^0-9]", ""))) {
					JOptionPane.showMessageDialog(null, "종료일이 시작일보다 빠를 수 없습니다.");
					
				} else {
					if (mgr.resChk(r_room, sqlStartDate, sqlEndDate, Integer.parseInt(res_no))) { // 중복 일정 존재하는 경우
						System.out.println("룸 번호 : " + r_room + " 시작날짜 : " + sqlStartDate + ", 종료날짜 : " + sqlEndDate);
						System.out.println("[ReservationAdmin] 기존예약존재");
						JOptionPane.showMessageDialog(null, "해당 일정은 예약이 불가능합니다.");
					} else { // 중복 일정 존재하지 않는 경우
						System.out.println("[ReservationAdmin] 기존예약없음");
						System.out.println("룸 번호 : " + r_room + " 시작날짜 : " + sqlStartDate + ", 종료날짜 : " + sqlEndDate);
						
						ReservationMgr resMgr = new ReservationMgr(); // 예약룸의 최대수용인원과 1박당 가격 가져오기
						int capacity = resMgr.capacityChk(Integer.parseInt(r_room));
						if (Integer.parseInt(headcount) > capacity) { // 최대수용인원 초과할 경우
							JOptionPane.showMessageDialog(null, r_room + "룸의 최대 수용인원은 " + capacity + "명 입니다.");
							
						} else { // 초과하지 않는 경우
							
							bean.setR_room(r_room);
							bean.setStartdate(sqlStartDate);
							bean.setEnddate(sqlEndDate);
							bean.setHeadcount(headcount);
							bean.setRes_no(res_no);
							
							p_cost = resMgr.totalCostChk(Integer.parseInt(r_room));
							System.out.println(p_cost);

							// 1박 가격에 곱하는 예약일수 차이 구하기
							Calendar calStartDate = Calendar.getInstance();
							calStartDate.setTime(sqlStartDate);

							Calendar calEndDate = Calendar.getInstance();
							calEndDate.setTime(sqlEndDate); 
							
							long diffSec = (calEndDate.getTimeInMillis() - calStartDate.getTimeInMillis()) / 1000;
							int diffDays = (int)diffSec / (24*60*60); //일자수 차이
							
							// 예약일수 * 1박당 가격 구해서 bean에 set
							bean.setP_cost(diffDays*p_cost);
							System.out.println(diffDays*p_cost);
							
							
							mgr.resUpdt(bean);
							System.out.println("[ReservationAdmin] 예약번호:" + res_no + " 예약수정완료");
							JOptionPane.showMessageDialog(null, "예약수정이 성공적으로 처리되었습니다.");
							// 수정후 DB값 새로불러오기
							@SuppressWarnings("rawtypes")
							Vector result = mgr.selectAll();
							model.setDataVector(result, title);
						}
						

					}
				}
				

			}

		});

		// 예약삭제: 위에서 텍필로 끌어온 값 -> AdminMgr로 써서 삭제
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String res_no = res_noTf.getText();
				mgr.delete(res_no);

				// 삭제 후 디비값 불러오기
				@SuppressWarnings("rawtypes")
				Vector result = mgr.selectAll();
				model.setDataVector(result, title);

			}
		});

		// 검색버튼: id의 예약내역만 출력
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = searchTf.getText();
				System.out.println(searchTf.getText());
				mgr.selectId(id);
				result = mgr.selectId(id);
				model.setDataVector(result, title);
			}
		});

		// 새로고침 버튼: 다시 전체 출력
		freshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vector result = mgr.selectAll();
				model.setDataVector(result, title);
			}
		});

		// 홈버튼 클릭 액션: 메인으로 이동
		homeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();

			}
		});

		// 새로고침
		jf.validate();
	}

	// 생성자
	public ReservationAdmin() {
		this(null);
	}
}
