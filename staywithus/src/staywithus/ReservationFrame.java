package staywithus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import staywithus.CalendarFunc;

public class ReservationFrame {
	private JFrame jf;
	private String r_status;
	private int check; // insert 되면 1, delete 되면 0 셋팅됨 -> 1이면 결제창 열기
	private String date1;
	private String date2;

	private java.util.Date utilStartDate;
	private java.util.Date utilEndDate;

	private int sMove;
	private int eMove;
	
	ReservationMgr mgr = new ReservationMgr();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationFrame reservationAwt = new ReservationFrame();
					reservationAwt.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자
	public ReservationFrame(String userId) {
		jf = new JFrame();
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setUndecorated(false);
		jf.setVisible(true);
		

		// 전체틀 레이아웃 지정
		jf.setLayout(new BorderLayout());

		Color bkColor = new Color(253, 253, 246); // 백그라운드 색상
		Color c1 = new Color(184, 178, 166);
		Color c2 = new Color(187, 153, 129);
		Color c3 = new Color(219, 233, 183);
		Color c4 = new Color(244, 218, 218);

		Font f1 = new Font("나눔고딕", Font.BOLD, 20);
		Font f2 = new Font("나눔고딕", Font.PLAIN, 15);
		Font f3 = new Font("나눔고딕", Font.PLAIN, 6);
		Font f4 = new Font("나눔고딕", Font.BOLD, 30);
		Font f5 = new Font("나눔고딕", Font.PLAIN, 20);

		Cursor cursor = new Cursor(Cursor.HAND_CURSOR); // 클릭 커서 모양

		JLabel titleLb = new JLabel("예약하기");
		titleLb.setForeground(c1);
		JLabel roomLb = new JLabel("   룸 선택   ");
		roomLb.setForeground(c1);
		JLabel rsRoomLb = new JLabel("선택하신 룸 : ");
		rsRoomLb.setForeground(c1);
		JLabel rsSDateLb = new JLabel("시작 일정 : ");
		rsSDateLb.setForeground(c1);
		JLabel rsEDateLb = new JLabel("종료 일정 : ");
		rsEDateLb.setForeground(c1);

		JLabel rsHeadcountLb = new JLabel("예약 인원 : ");
		rsHeadcountLb.setForeground(c1);
		JTextField rsHeadcountTf = new JTextField();

		JButton paymentBtn = new JButton("결제하기");
		paymentBtn.setForeground(c1);
		paymentBtn.setBorder(BorderFactory.createLineBorder(c4, 8));
		paymentBtn.setCursor(cursor);

		JTextField rsRoomTf = new JTextField();
		rsRoomTf.setForeground(c2);
		JTextField rsSDateTf = new JTextField();
		rsSDateTf.setForeground(c2);
		JTextField rsEDateTf = new JTextField();
		rsEDateTf.setForeground(c2);

		JRadioButton roomBtn[];

		JPanel panel1 = new JPanel(); // 전체틀 중첩 보더
		panel1.setBackground(bkColor);
		panel1.setOpaque(true);
		JPanel panel2 = new JPanel(); // 전체틀 중첩 보더 센터 : 달력
		panel2.setBackground(bkColor);
		panel2.setOpaque(true);
		JPanel panel7 = new JPanel(); // 전체틀 중첩 보더1 북쪽 : 날짜 표시 및 이동
		panel7.setBackground(bkColor);
		panel7.setOpaque(true);
		JPanel panel8 = new JPanel(); // panel2의 중첩보더 (그리드 행 1)
		panel8.setBackground(bkColor);
		panel8.setOpaque(true);
		JPanel panel10 = new JPanel(); // panel2의 중첩보더인 panel8의 북쪽 : 날짜표시 및 이동
		panel10.setBackground(bkColor);
		panel10.setOpaque(true);
		JPanel panel12 = new JPanel(); // panel2의 중첩보더인 panel8의 센터 : 달력
		panel12.setBackground(bkColor);
		panel12.setOpaque(true);

		JPanel panel9 = new JPanel(); // panel2의 중첩보더 (그리드 행 2)
		panel9.setBackground(bkColor);
		panel9.setOpaque(true);
		JPanel panel11 = new JPanel(); // panel2의 중첩보더인 panel9의 북쪽 : 날짜표시 및 이동
		panel11.setBackground(bkColor);
		panel11.setOpaque(true);
		JPanel panel13 = new JPanel(); // panel2의 중첩보더인 panel9의 센터 : 달력
		panel13.setBackground(bkColor);
		panel13.setOpaque(true);

		JPanel panel3 = new JPanel(); // 전체틀 보더북쪽 타이틀 : "예약하기"
		panel3.setBackground(bkColor);
		panel3.setOpaque(true);
		JPanel panel4 = new JPanel(); // 전체틀 보더왼쪽 : "룸선택" 및 룸호수 선택
		panel4.setBackground(bkColor);
		panel4.setOpaque(true);
		JPanel panel5 = new JPanel(); // 전체틀 보더 오른쪽 : "선택하신 룸" 텍스트필드, "일정" 텍스트필드
		panel5.setBackground(bkColor);
		panel5.setOpaque(true);
		JPanel panel6 = new JPanel(); // 전체틀 보더 하단 : 결제하기 버튼
		panel6.setBackground(bkColor);
		panel6.setOpaque(true);

		// 인원수 증가 버튼
		JButton cPlusBtn = new JButton("증가 (+)");
		cPlusBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		cPlusBtn.setBackground(c4);
		cPlusBtn.setForeground(c1);
		cPlusBtn.setCursor(cursor);
		cPlusBtn.setBorder(BorderFactory.createLineBorder(c4, 8));

		// 인원수 감소 버튼
		JButton cMinusBtn = new JButton("감소 (-)");
		cMinusBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		cMinusBtn.setBackground(c4);
		cMinusBtn.setForeground(c1);
		cMinusBtn.setCursor(cursor);
		cMinusBtn.setBorder(BorderFactory.createLineBorder(c4, 8));
		
		// 시작일자달력과 종료일자달력 before After 버튼
		JButton sBeforeBtn = new JButton("   Before   ");
		JButton eBeforeBtn = new JButton("   Before   ");
		JButton sAfterBtn = new JButton("   After   ");
		JButton eAfterBtn = new JButton("   After   ");

		// 기본 셋팅 0000년 00월
		JLabel sLabel = new JLabel("0000년 00월");
		sLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		sLabel.setForeground(c1);

		JLabel eLabel = new JLabel("0000년 00월");
		eLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		eLabel.setForeground(c1);

		// 달력 버튼
		JButton[] sDayBtn = new JButton[49];
		String[] sDayName = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		JButton[] eDayBtn = new JButton[49];
		String[] eDayName = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

		
		
		
		// CalendarFunction 클래스로부터 sCF 객체 생성
		CalendarFunc sCF = new CalendarFunc(); // 시작일 달력 기능
		CalendarFunc eCF = new CalendarFunc(); // 종료일 달력 기능

		int rsSDate; // DB와 연동될 시작날짜 최종값 (YYYYMMDD)
		int rsEDate; // DB와 연동될 종료날짜 최종값 (YYYYMMDD)
		int rsRoom; // DB와 연동될 선택룸 최종값 (101, 102, 201, 202 중 하나)


		// Panel 위치 지정
		jf.add(panel1, "Center");

		jf.add(panel3, "North");
		titleLb.setFont(new Font("나눔고딕", Font.BOLD, 34));
		panel3.add(titleLb);

		jf.add(panel4, "West");
		panel4.setLayout(new GridLayout(5, 1));
		panel4.add(roomLb);
		roomLb.setFont(new Font("나눔고딕", Font.BOLD, 34));

		roomBtn = new JRadioButton[4];
		ButtonGroup bgr = new ButtonGroup();

		roomBtn[0] = new JRadioButton("    101호    ", true);
		roomBtn[1] = new JRadioButton("    102호    ", false);
		roomBtn[2] = new JRadioButton("    201호    ", false);
		roomBtn[3] = new JRadioButton("    202호    ", false);

		for (int i = 0; i < roomBtn.length; i++) {
			roomBtn[i].setFont(f1);
			bgr.add(roomBtn[i]);
			panel4.add(roomBtn[i]);
			roomBtn[i].setBackground(bkColor);
			roomBtn[i].setForeground(c1);
			roomBtn[i].setOpaque(true);
		}

		roomBtn[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[0].getText());
				rsRoomTf.setFont(f2);

			}
		});

		roomBtn[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[1].getText());
				rsRoomTf.setFont(f2);
			}
		});

		roomBtn[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[2].getText());
				rsRoomTf.setFont(f2);
			}
		});

		roomBtn[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[3].getText());
				rsRoomTf.setFont(f2);
			}
		});

		panel5.setLayout(new GridLayout(9, 1)); /* 보더랑헷갈려서 헛짓..; */
		jf.add(panel5, "East");
		rsRoomLb.setFont(f1);
		panel5.add(rsRoomLb);
		rsRoomTf.setFont(f5);
		rsRoomTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsRoomTf);

		// 시작일정 텍스트필드
		rsSDateLb.setFont(f1);
		panel5.add(rsSDateLb);
		rsSDateTf.setFont(f5);
		rsSDateTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsSDateTf);

		// 종료일정 텍스트필드
		rsEDateLb.setFont(f1);
		panel5.add(rsEDateLb);
		rsEDateTf.setFont(f5);
		rsEDateTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsEDateTf);

		// 인원 라벨
		rsHeadcountLb.setFont(f1);
		panel5.add(rsHeadcountLb);

		// 인원 텍스트필드 : 기본값 1
		rsHeadcountTf.setText("   " + 1 + "   ");
		rsHeadcountTf.setFont(f5);
		rsHeadcountTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsHeadcountTf);
		rsHeadcountTf.setForeground(c2);

		JPanel panel17 = new JPanel(); // 마지막 행
		panel17.setLayout(new GridLayout(1, 2));
		panel5.add(panel17);
		panel17.setBackground(bkColor);
		panel17.setOpaque(true);

		panel17.add(cPlusBtn); // 오름 버튼
		panel17.add(cMinusBtn); // 내림 버튼

		cPlusBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("인원 수 증가버튼 클릭");

				// 1. 현재 예약희망인원 Tf 값 가져오기
				int headcount = Integer.parseInt(rsHeadcountTf.getText().trim()); // headcount = 1;

				// 2. headcount +1씩 증가시키고, Tf에 셋팅
				headcount++;
				rsHeadcountTf.setText(headcount + "");

				// 3. 최대수용인원과 예약희망인원 비교위해 룸 Tf로부터 선택 룸 가져오기
				int rsRoom = (Integer.parseInt(rsRoomTf.getText().trim().replaceAll("호", "")));

				// 4. DB연동해서 선택룸의 최대 수용인원 가져오기 (SEELCT r_capacity)
				int r_capacity = mgr.capacityChk(rsRoom);

				// 5. headcount(+1 증가된 인원)과 rsMgr.r_capacity(DB상 룸별 최대 수용인원) 비교
				if (headcount > r_capacity) { // 예약인원 > 최대 수용인원
					// 경고 알림창
					JOptionPane.showMessageDialog(null, rsRoom + "호의 최대 수용인원은 " + r_capacity + "명 입니다.",
							"예약인원을 체크해주세요.", JOptionPane.ERROR_MESSAGE);

					// 다시 1로 셋팅
					headcount = 1;
					rsHeadcountTf.setText(headcount + "");
				}

			}
		});

		cMinusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("인원 수 감소버튼 클릭");

				// 1. 현재 예약희망인원 Tf 값 가져오기
				int headcount = Integer.parseInt(rsHeadcountTf.getText().trim());

				// 2. headcount +1씩 증가시키고, Tf에 셋팅
				headcount--;
				rsHeadcountTf.setText(headcount + "");

				// 3. 예약희망인원은 최소 1 이상!
				if (headcount < 1) {
					// 경고 알림창
					JOptionPane.showMessageDialog(null, "1명 이상의 인원만 예약 가능압니다.", "예약인원을 체크해주세요.",
							JOptionPane.ERROR_MESSAGE);

					// 다시 1로 셋팅
					headcount = 1;
					rsHeadcountTf.setText(headcount + "");
				}
			}
		});
		
		panel6.add(paymentBtn);
		paymentBtn.setFont(f4);
		paymentBtn.setBackground(c4);
		jf.add(panel6, "South");

		// 결제버튼 클릭 이벤트
		paymentBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 0~9 이외의 모든 문자 제거한 Tf -> String
				String rsRoom = rsRoomTf.getText().trim().replaceAll("[^0-9]", ""); // 선택 룸
				String rsSDate = rsSDateTf.getText().trim().replaceAll("[^0-9]", ""); // 선택 시작일자
				String rsEDate = rsEDateTf.getText().trim().replaceAll("[^0-9]", ""); // 선택 종료일자
				System.out.println("선택하신 룸 : " + rsRoom + "\n" + "시작날짜 : " + rsSDate + "\n" + "종료날짜 : " + rsEDate + "\n");
				
				if(rsSDate.trim().length() ==0 || rsEDate.trim().length()==0) { // 시작일자 OR 종료일자 미선택
					JOptionPane.showMessageDialog(null, "일정을 선택해주세요.");
				} else { // 시작일자 AND 종료일자 선택
					// Date형식으로 바꾸는 클래스
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
					
					try {
						// String -> java.util.date
						utilStartDate = sdf.parse(rsSDate); 
						utilEndDate = sdf.parse(rsEDate); 

						// java.util.date -> java.sql.date 변환 (DB 데이터 다루기 위해)	
						java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
						java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());													
						
						if (mgr.dateChk(Integer.parseInt(rsRoom), sqlStartDate, sqlEndDate) /* true 반환 -> 중복일정 존재한다는 의미 */) {
							System.out.println("중복되는 일정이 있으므로 선택하신 일정이 모두 예약취소 되었습니다. 다시 선택해주십시오.");
							JOptionPane.showMessageDialog(null, "중복되는 일정이 있으므로 선택하신 일정이 모두 예약취소 되었습니다. 다시 선택해주십시오.");
							
							check = 0; // 예약실패와 성공의 구분 변수
							
						} else { /*false 반환 -> 중복일정 존재하지 않는다는 의미*/
							// 위아래버튼 클릭 안했다면 headcount = 1; 기본셋팅, 클릭했으면 값 설정되어 있음
							int headcount = Integer.parseInt(rsHeadcountTf.getText().trim()); // 최대수용인원 초과하지 않는 인원 가져오기

							// 결제 전 단계인 예약파트에서 결제하기 버튼 누르면 무조건 결제 전 상태 셋팅됨
							r_status = "결제 전";
							
							
							// 전체 가격 구하기
							// 1. 선택 룸 1박 가격 가져오기
							int p_cost = mgr.totalCostChk(Integer.parseInt(rsRoom)); 
							System.out.println(p_cost + ": 예약하신 룸의 1박당 가격입니다.");
							
							// 2. 1박 가격에 곱하는 예약일수 차이 구하기
							Calendar calStartDate = Calendar.getInstance();
							calStartDate.setTime(utilStartDate);

							Calendar calEndDate = Calendar.getInstance();
							calEndDate.setTime(utilEndDate); //특정 일자
							
							long diffSec = (calEndDate.getTimeInMillis() - calStartDate.getTimeInMillis()) / 1000;
							int diffDays = (int)diffSec / (24*60*60); //일자수 차이
							
							
							// 3. DB INSERT : diffDays*p_cost 곱하기
							mgr.InsertDate(userId, Integer.parseInt(rsRoom), sqlStartDate, 
									sqlEndDate, headcount, r_status, diffDays*p_cost);
							
							check = 1;
							
							try {
								JOptionPane.showMessageDialog(null, sqlStartDate + " ~ " + sqlEndDate + " : 일정이 무사히 예약되셨습니다. 결제창으로 넘어갑니다.");
								System.out.println(sqlStartDate + " ~ " + sqlEndDate + " : 일정이 무사히 예약되셨습니다. 결제창으로 넘어갑니다.");
							} catch (Exception e2) {
								e2.printStackTrace();
							}

						}

						if (check == 1) { // 예약 성공했을 때만 결제창 띄우기
							PaymentFrame cpf = new PaymentFrame(userId);
							cpf.setVisible(true);
							jf.dispose();
						}

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
				}

			}
		});



		// 중첩 레이아웃
		panel1.setLayout(new BorderLayout());
		panel1.add(panel7, "North");
		panel1.add(panel2, "Center");

		panel2.setLayout(new GridLayout(2, 1));
		panel2.add(panel8); // 1행
		panel2.add(panel9); // 2행

		// 시작일 달력
		panel8.setLayout(new BorderLayout());
		panel8.add(panel10, "North");
		panel8.add(panel12, "Center");

		// 가로로 늘어진 형태
		panel10.setLayout(new FlowLayout());
		panel10.add(sBeforeBtn);
		panel10.add(sLabel);
		panel10.add(sAfterBtn);

		// 종료일 달력
		panel9.setLayout(new BorderLayout());
		panel9.add(panel11, "North");
		panel9.add(panel13, "Center");

		// 가로로 늘어진 형태
		panel11.setLayout(new FlowLayout());
		panel11.add(eBeforeBtn);
		panel11.add(eLabel);
		panel11.add(eAfterBtn);

		// 시작일 달력 액션리스너 연결
		sAfterBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sMove = 1;

				// sCF객체의 startCal 메소드 호출 : +1씩 더해서 12 초과하면 내년, 1 미만이면 작년 셋팅
				sCF.startCal(sMove);

				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				sLabel.setText("시작일자 선택 : " + sCF.setCalText());

			}
		});

		sBeforeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sMove = -1;

				// sCF객체의 sMove 메소드 호출
				sCF.startCal(sMove);

				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				sLabel.setText("시작일자 선택 : " + sCF.setCalText());
			}
		});

		sAfterBtn.setFont(f1);
		sAfterBtn.setBackground(c3);
		sAfterBtn.setForeground(bkColor);
		sAfterBtn.setCursor(cursor);
		sAfterBtn.setBorder(BorderFactory.createLineBorder(c3, 8));

		sBeforeBtn.setFont(f1);
		sBeforeBtn.setBackground(c3);
		sBeforeBtn.setForeground(bkColor);
		sBeforeBtn.setCursor(cursor);
		sBeforeBtn.setBorder(BorderFactory.createLineBorder(c3, 8));

		sLabel.setFont(f1);

		// setText : 초기화 -> 해당 달력의 년도와 월로 새로 셋팅
		sLabel.setText("시작일자 선택 : " + sCF.setCalText());

		// 종료일 달력 액션리스너 연결
		eAfterBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int eMove = 1;

				// eCF객체의 startCal 메소드 호출
				eCF.startCal(eMove);

				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				eLabel.setText("종료일자 선택 : " + eCF.setCalText());
			}
		});
		eBeforeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int eMove = -1;

				// eCF객체의 startCal 메소드 호출
				eCF.startCal(eMove);

				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				eLabel.setText("종료일자 선택 : " + eCF.setCalText());

			}
		});

		eAfterBtn.setFont(f1);
		eAfterBtn.setBackground(c1);
		eAfterBtn.setForeground(bkColor);
		eAfterBtn.setCursor(cursor);
		eAfterBtn.setBorder(BorderFactory.createLineBorder(c1, 8));

		eBeforeBtn.setFont(f1);
		eBeforeBtn.setBackground(c1);
		eBeforeBtn.setForeground(bkColor);
		eBeforeBtn.setCursor(cursor);
		eBeforeBtn.setBorder(BorderFactory.createLineBorder(c1, 8));

		eLabel.setFont(f1);
		// setText : 초기화 -> 해당 달력의 년도와 월로 새로 셋팅
		eLabel.setText("종료일자 선택 : " + eCF.setCalText());

		// 시작일 달력 표시
		panel12.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < sDayBtn.length; i++) {
			sDayBtn[i] = new JButton(); // 버튼 배열 객체 생성

			if (i >= 0 && i < 7) {
				sDayBtn[i].setBackground(c3);
				sDayBtn[i].setForeground(bkColor);
				sDayBtn[i].setBorder(BorderFactory.createLineBorder(c3, 1));
			} else {
				sDayBtn[i].setBackground(bkColor);
				sDayBtn[i].setForeground(c1);
				sDayBtn[i].setBorder(BorderFactory.createLineBorder(c1, 1));
			}

			sDayBtn[i].setCursor(cursor);

			// 액션리스너 연결
			sDayBtn[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 7; i < sDayBtn.length; i++) {
						// 일자 시작하는 날부터
						if (sDayBtn[i].getText().length() > 0) {
							if (e.getSource() == sDayBtn[i]) {
								if (sDayBtn[i].getText().length() == 1) { //한자리 일자
									rsSDateTf.setText(sLabel.getText().substring(10)
											+ String.format("%02d", Integer.parseInt(sDayBtn[i].getText())) + "일");
								} else { // 두자리 일자
									rsSDateTf.setText(sLabel.getText().substring(10) + sDayBtn[i].getText() + "일");
								} 
								
								if(rsSDateTf.getText().trim().length()>0 && rsEDateTf.getText().trim().length()>0) { // 시작일정 골라놨을 때 종료일자와 비교가능 
									int rsSDateInt = Integer.parseInt(rsSDateTf.getText().trim().replaceAll("[^0-9]", "")); // 선택 시작일자
									int rsEDateInt = Integer.parseInt(rsEDateTf.getText().trim().replaceAll("[^0-9]", "")); // 선택 종료일자
									
									if(rsEDateInt <= rsSDateInt) { // 종료일자가 시작일자보다 빠르다면(8자리 숫자로 봤을 때 작다면)
										rsEDateTf.setText(""); // 종료일자 초기화
										JOptionPane.showMessageDialog(null, "종료일자가 시작일자보다 같거나 빠를 수 없습니다.");
									}
								}
							}
						}
					}
				}
			});
			panel12.add(sDayBtn[i]);

			sDayBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 24));

			// 요일 배열 넣기
			if (i < 7)
				sDayBtn[i].setText(sDayName[i]);

			if (i % 7 == 0) // 일요일
				sDayBtn[i].setForeground(Color.RED);
			if (i % 7 == 6) // 토요일
				sDayBtn[i].setForeground(Color.BLUE);
		}

		sCF.setBtn(sDayBtn);
		sCF.setCal();

		// 종료일 달력 표시
		panel13.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < eDayBtn.length; i++) {
			eDayBtn[i] = new JButton(); // 버튼 배열 객체 생성

			if (i >= 0 && i < 7) {
				eDayBtn[i].setBackground(c1);
				eDayBtn[i].setForeground(bkColor);
				eDayBtn[i].setBorder(BorderFactory.createLineBorder(c1, 1));
			} else {
				eDayBtn[i].setBackground(bkColor);
				eDayBtn[i].setForeground(c1);
				eDayBtn[i].setBorder(BorderFactory.createLineBorder(c1, 1));
			}

			eDayBtn[i].setCursor(cursor);

			// 액션리스너 연결
			eDayBtn[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 7; i < eDayBtn.length; i++) {
						// 일자 시작하는 날부터
						if (eDayBtn[i].getText().length() > 0) {
							if (e.getSource() == eDayBtn[i]) {
								if (eDayBtn[i].getText().length() == 1) { // 한자리 일자
									rsEDateTf.setText(eLabel.getText().substring(10)
											+ String.format("%02d", Integer.parseInt(eDayBtn[i].getText())) + "일");
								} else { // 두자리 일자
									rsEDateTf.setText(eLabel.getText().substring(10) + eDayBtn[i].getText() + "일");
								}
								
								if(rsSDateTf.getText().trim().length()>0 && rsEDateTf.getText().length()>0) { // 시작일정 골라놨을 때 종료일자와 비교가능 
									int rsSDateInt = Integer.parseInt(rsSDateTf.getText().trim().replaceAll("[^0-9]", "")); // 선택 시작일자
									int rsEDateInt = Integer.parseInt(rsEDateTf.getText().trim().replaceAll("[^0-9]", "")); // 선택 종료일자
									
									if(rsEDateInt <= rsSDateInt) { // 종료일자가 시작일자보다 빠르다면(8자리 숫자로 봤을 때 작다면)
										rsEDateTf.setText(""); // 종료일자 초기화
										JOptionPane.showMessageDialog(null, "종료일자가 시작일자보다 같거나 빠를 수 없습니다.");
									}
								}
							}
						}
					}
				}
			});

			panel13.add(eDayBtn[i]);

			eDayBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 24));

			// 요일 배열 넣기
			if (i < 7)
				eDayBtn[i].setText(eDayName[i]);

			if (i % 7 == 0) // 일요일
				eDayBtn[i].setForeground(Color.RED);
			if (i % 7 == 6) // 토요일
				eDayBtn[i].setForeground(Color.BLUE);
		}

		eCF.setBtn(eDayBtn); // 일자, 요일 셋팅
		eCF.setCal(); // 달력 버튼의 날짜 셋팅

		rsRoomTf.setText(roomBtn[0].getText());

		// 새로고침
		jf.validate();
	}

	// 생성자
	public ReservationFrame() {
		this(null);
	}

}
