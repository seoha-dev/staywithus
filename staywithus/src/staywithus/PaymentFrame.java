package staywithus;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PaymentFrame extends JFrame implements ActionListener {
	private Image bkImg = new ImageIcon("src/staywithus/images/background.png").getImage();
	private int check;
	private int totalCost;
	// 카드사 버튼 배열
	private JButton cardBtn[] = new JButton[12]; // 크기 8 지정
	private String cardName[] = { "KAKAOPAY", "LPAY", "현대카드", "삼성카드", "비씨카드", "KB국민", "신한카드", "롯데카드", "NH농협", "하나카드",
			"씨티카드", "UnionPay" };
	private Checkbox agmCb1, agmCb2, agmCb3;
	private Checkbox allChkCb;
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	private String userId;
	private JButton nextBtn;
	private Color cardLbColor;
	private int clickCnt;
	


	public static void main(String[] args) {// 실행
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentFrame paymentFrame = new PaymentFrame();
					paymentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 그리기 함수
	public void paint(Graphics g) {
		// 배경이미지
		g.drawImage(bkImg, 0, 10, null);

		// 구분선
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);

		// 폰트
		Font f1 = new Font("나눔고딕", Font.BOLD, 18); // 타이틀 폰트
		Font f2 = new Font("나눔고딕", Font.BOLD, 11); // 버튼 폰트
		Font f3 = new Font("나눔고딕", Font.PLAIN, 11); // 텍스트 폰트
		Font f4 = new Font("나눔고딕", Font.BOLD, 13); // 텍스트 폰트
		Font f5 = new Font("나눔고딕", Font.PLAIN, 13); // 텍스트 폰트

		// 신용카드 라벨
		JLabel cardLb = new JLabel("신용카드");
		add(cardLb);
		cardLb.setFont(f1);

		// 배경 레드 컬러
		cardLbColor = new Color(226, 56, 61);
		g.setColor(cardLbColor);
		cardLb.setForeground(Color.WHITE);
		cardLb.setBackground(g.getColor());
		cardLb.setOpaque(true);
		cardLb.setBounds(10, 40, 90, 30);

		// 이용약관 라벨
		JLabel agmTitle = new JLabel("이용약관");
		agmTitle.setFont(f2);
		add(agmTitle);
		agmTitle.setBackground(Color.white);
		agmTitle.setOpaque(true);
		agmTitle.setBounds(120, 20, 70, 20);

		// 3개 약관 라벨
		JLabel agmLb1 = new JLabel("전자금융거래 이용약관");
		agmLb1.setFont(f3);
		add(agmLb1);
		agmLb1.setBackground(Color.WHITE);
		agmLb1.setOpaque(true);
		agmLb1.setBounds(120, 60, 140, 20);

		JLabel agmLb2 = new JLabel("개인정보 수집 및 이용안내");
		agmLb2.setFont(f3);
		add(agmLb2);
		agmLb2.setBackground(Color.WHITE);
		agmLb2.setOpaque(true);
		agmLb2.setBounds(120, 80, 140, 20);

		JLabel agmLb3 = new JLabel("개인정보 제공 및 위탁안내");
		agmLb3.setFont(f3);
		add(agmLb3); 
		agmLb3.setBackground(Color.WHITE);
		agmLb3.setOpaque(true);
		agmLb3.setBounds(350, 60, 140, 20);

		// 3개의 약관 체크박스 - 1
		agmCb1 = new Checkbox(); // 체크박스 배열 객체 생성
		add(agmCb1);
		agmCb1.setBackground(Color.WHITE);
		agmCb1.setBounds(270, 60, 10, 20);

		// 아이템 리스너
		agmCb1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					check++; // private 변수로 변경해서 상단에 올리니 해결
					System.out.println(check);
					if (agmCb1.getState() && agmCb2.getState() && agmCb3.getState()) {
						check = 3;
						allChkCb.setState(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					check--;
					System.out.println(check);
					if (agmCb1.getState() || agmCb2.getState() || agmCb3.getState()) {
						allChkCb.setState(false);
					}
				}
			}
		});

		JLabel agmAgreeLb1 = new JLabel("동의");
		agmAgreeLb1.setFont(f3);
		add(agmAgreeLb1);
		agmAgreeLb1.setBackground(Color.WHITE);
		agmAgreeLb1.setOpaque(true);
		agmAgreeLb1.setBounds(283, 60, 30, 20);
		agmAgreeLb1.setCursor(cursor);

		agmAgreeLb1.addMouseListener(new MouseListener() {
			// 체크박스에 텍스트 "동의" 바로 넣어주고 액션리스너 해도 되지만,
			// 체크박스의 텍스트 폰트 변경이 안되어서
			// 따로 텍스트 "동의"를 라벨로 생성 후, 클릭하였을 때 체크박스 on/off 토글기능을 구현하였다.

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == agmAgreeLb1) {
					if (!agmCb1.getState()) { // 체크박스 false 상태일 때 (=미체크상태)
						agmCb1.setState(true); // false -> true
						check++;
						System.out.println(check);
						if (agmCb1.getState() && agmCb2.getState() && agmCb3.getState()) { // 모두 선택되어있다면
							check = 3;
							allChkCb.setState(true); // 전체동의 CB 자동체크
						}
					} else { // 체크박스 true 상태일 때 (=미체크상태)
						agmCb1.setState(false); // true -> false
						check--;
						System.out.println(check);
						allChkCb.setState(false); // 최소 1개는 false 상태가 되었으니, 전체동의 CB 체크해제

					}
				}
			}
		});

		// 3개의 약관 체크박스 - 2
		agmCb2 = new Checkbox(); // 체크박스 배열 객체 생성
		add(agmCb2);
		agmCb2.setBackground(Color.WHITE);
		agmCb2.setBounds(270, 80, 10, 20);

		// 아이템 리스너
		agmCb2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					check++; // private 변수로 변경해서 상단에 올리니 해결
					System.out.println(check);
					if (agmCb1.getState() && agmCb2.getState() && agmCb3.getState()) {
						check = 3;
						allChkCb.setState(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					check--;
					System.out.println(check);
					allChkCb.setState(false);

				}
			}
		});

		JLabel agmAgreeLb2 = new JLabel("동의");
		agmAgreeLb2.setFont(f3);
		add(agmAgreeLb2);
		agmAgreeLb2.setBackground(Color.WHITE);
		agmAgreeLb2.setOpaque(true);
		agmAgreeLb2.setBounds(283, 80, 30, 20);
		agmAgreeLb2.setCursor(cursor);
		agmAgreeLb2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == agmAgreeLb2) {
					if (!agmCb2.getState()) {
						agmCb2.setState(true);
						check++;
						System.out.println(check);
						if (agmCb1.getState() && agmCb2.getState() && agmCb3.getState()) {
							check = 3;
							allChkCb.setState(true);
						}
					} else {
						agmCb2.setState(false);
						check--;
						System.out.println(check);
						allChkCb.setState(false);

					}
				}
			}
		});

		// 3개의 약관 체크박스 - 3
		agmCb3 = new Checkbox(); // 체크박스 배열 객체 생성
		add(agmCb3);
		agmCb3.setBackground(Color.WHITE);
		agmCb3.setBounds(530, 60, 10, 20);

		// 아이템 리스너
		agmCb3.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					check++; // private 변수로 변경해서 상단에 올리니 해결
					System.out.println(check);
					if (agmCb1.getState() && agmCb2.getState() && agmCb3.getState()) {
						check = 3;
						allChkCb.setState(true);
					}
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					check--;
					System.out.println(check);

					allChkCb.setState(false);

				}
			}
		});

		JLabel agmAgreeLb3 = new JLabel("동의");
		agmAgreeLb3.setFont(f3);
		add(agmAgreeLb3);
		agmAgreeLb3.setBackground(Color.WHITE);
		agmAgreeLb3.setOpaque(true);
		agmAgreeLb3.setBounds(543, 60, 30, 20);
		agmAgreeLb3.setCursor(cursor);
		agmAgreeLb3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == agmAgreeLb3) {
					if (!agmCb3.getState()) {
						agmCb3.setState(true);
						check++;
						System.out.println(check);
						if (agmCb1.getState() && agmCb2.getState() && agmCb3.getState()) {
							check = 3;
							allChkCb.setState(true);
						}
					} else {
						agmCb3.setState(false);
						check--;
						System.out.println(check);
						allChkCb.setState(false);
					}
				}
			}
		});

		// 전체동의 체크박스
		allChkCb = new Checkbox();
		add(allChkCb);
		allChkCb.setBackground(Color.WHITE);
		allChkCb.setBounds(530, 20, 10, 20);
		allChkCb.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getSource() == allChkCb) {
					if (!allChkCb.getState()) { // 전체동의 CB false 상태
						if (!agmCb1.getState() || !agmCb2.getState() || !agmCb3.getState()) { // 한 개라도 체크박스 false 상태일 때
							agmCb1.setState(true);
							agmCb2.setState(true);
							agmCb3.setState(true);
							
							allChkCb.setState(true);
							check = 3;
							System.out.println(check);
						}
					} else if (allChkCb.getState()) {// 전체동의 CB true 상태
						agmCb1.setState(false);
						agmCb2.setState(false);
						agmCb3.setState(false);
						allChkCb.setState(false);
						check = 0;
						System.out.println(check);
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		// 전체동의 라벨
		JLabel allChkLb = new JLabel("전체동의");
		allChkLb.setFont(f3);
		add(allChkLb);
		allChkLb.setBackground(Color.white);
		allChkLb.setOpaque(true);
		allChkLb.setBounds(543, 20, 50, 20);
		allChkLb.setCursor(cursor);
		allChkLb.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getSource() == allChkLb) {
					if (!allChkCb.getState()) { // 전체동의 CB false 상태
						if (!agmCb1.getState() || !agmCb2.getState() || !agmCb3.getState()) { // 한 개라도 체크박스 false 상태일 때
							agmCb1.setState(true);
							agmCb2.setState(true);
							agmCb3.setState(true);
							allChkCb.setState(true);
							check = 3;
							System.out.println(check);
						}
					} else if (allChkCb.getState()) {// 전체동의 CB true 상태
						agmCb1.setState(false);
						agmCb2.setState(false);
						agmCb3.setState(false);
						allChkCb.setState(false);
						check = 0;
						System.out.println(check);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		for (int i = 0; i < cardBtn.length; i++) {
			cardBtn[i] = new JButton(cardName[i]);
			cardBtn[i].setBackground(Color.WHITE);
			cardBtn[i].setFont(f2);
			add(cardBtn[i]);
			cardBtn[i].addActionListener(this);
		}

		cardBtn[0].setBounds(113, 155, 487, 40);
		cardBtn[1].setBounds(113, 200, 487, 40);
		cardBtn[2].setBounds(113, 245, 118, 40);
		cardBtn[3].setBounds(236, 245, 118, 40);
		cardBtn[4].setBounds(359, 245, 118, 40);
		cardBtn[5].setBounds(482, 245, 118, 40);
		cardBtn[6].setBounds(113, 290, 118, 40);
		cardBtn[7].setBounds(236, 290, 118, 40);
		cardBtn[8].setBounds(359, 290, 118, 40);
		cardBtn[9].setBounds(482, 290, 118, 40);

		// 결제금액 텍스트부분 라벨
		JLabel costLb = new JLabel("결제금액");
		add(costLb);
		costLb.setFont(f5);
		costLb.setBackground(g.getColor());
		costLb.setForeground(Color.WHITE);
		costLb.setOpaque(true);
		costLb.setBounds(620, 140, 50, 40);

		// 결제금액 금액부분 라벨
		JLabel getCostLb = new JLabel(); // 일단 공백
		PaymentMgr mgr = new PaymentMgr();
		if(mgr.totalCostChk(userId)>0) {
			int totalCost = mgr.totalCostChk(userId);
			DecimalFormat df = new DecimalFormat("###,###"); // 금액 표기 형식
			getCostLb.setText(df.format(totalCost)+"원");
		}
		add(getCostLb);
		getCostLb.setFont(f1);
		getCostLb.setBackground(g.getColor());
		getCostLb.setForeground(Color.WHITE);
		getCostLb.setOpaque(true);
		getCostLb.setBounds(640, 200, 130, 40);

		JLabel nextLb = new JLabel("다   음");
		nextLb.setFont(f4);
		nextLb.setBackground(g.getColor());
		nextLb.setForeground(Color.WHITE);

		nextBtn = new JButton(nextLb.getText() + "");
		add(nextBtn);
		nextBtn.setFont(f4);
		nextBtn.setBackground(Color.WHITE);
		nextBtn.setForeground(g.getColor());
		nextBtn.setBounds(620, 510, 150, 40);
		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (check == 3 /* 1. CB 모두 체크되었다면 */ && clickCnt == 1 /* 2.결제수단 한 개 선택되었다면*/) {
					System.out.println(userId);
					dispose();
					CardPaymentFrame cpf = new CardPaymentFrame(userId);
					cpf.setVisible(true);

				} else if(check < 3 && clickCnt == 1) { // 결제수단은 골랐지만 약관 3개 모두 체크가 아니라면
					JOptionPane.showMessageDialog(null, "모든 약관에 동의해주세요.");
					System.out.println("약관동의 갯수 : " + check + "개, 결제수단 체크 갯수 : " + clickCnt + "개");
				} else if(check == 3 && clickCnt < 1) { // 약관 3개 모두 체크했지만 결제수단 고르지 않았다면 
					JOptionPane.showMessageDialog(null, "결제수단을 선택해주세요.");
					System.out.println("결제수단 체크 갯수 : " + clickCnt + "개");
				}

			}
		});

	}

	// 생성자
	public PaymentFrame() {
		this(null);
	}

	// 생성자
	public PaymentFrame(String userId) {
		this.userId = userId;
		// 프레임 설정
		setTitle(userId + "님 결제");// 타이틀
		setSize(800, 600);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setLayout(null);// 레이아웃을 내맘대로 설정가능하게 해줌.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//창 닫을 때 메인화면 같이 안 닫히게..3월25일 박인화수정

		
		validate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < cardBtn.length; i++) {
			if (e.getSource() == cardBtn[i]) {
				cardBtn[i].setBackground(cardLbColor);
				
				cardBtn[i].setForeground(Color.WHITE);
				
				clickCnt++;
				System.out.println(clickCnt);
				if(e.getSource() == cardBtn[i]) {
					if (clickCnt > 1) {
						JOptionPane.showMessageDialog(null, "결제수단을 하나만 선택하세요.");
						clickCnt = 0;
						System.out.println(clickCnt);
						for (int j = 0; j < cardBtn.length; j++) {
							cardBtn[j].setBackground(Color.WHITE);
							cardBtn[j].setForeground(Color.BLACK);
						}
					}	
				}
			} 
		}
		
	}

}
