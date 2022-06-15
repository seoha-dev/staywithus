package staywithus;

import java.awt.Button;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CardPaymentFrame extends JFrame {

	private Image bkImg = new ImageIcon("src/staywithus/images/background.png").getImage();
	PaymentMgr mgr = new PaymentMgr();
	private String userId;
	private JTextField c_numTf1, c_numTf2, c_numTf3, c_numTf4; // 카드번호 16자리 
	private JTextField yyTf, mmTf; // 유효기간 년월
	private JRadioButton pcRBtn, ccRBtn; // 개인, 법인 구분 
	private JTextField c_pwdTf; // 카드 비밀번호
	private JTextField idNumTf1, idNumTf2; // 주민등록번호
	private JCheckBox chk1; // 전체동의 체크박스

	// 그리기 함수
	public void paint(Graphics g) {
		
		// 배경이미지
		g.drawImage(bkImg, 0, 10, null);

		// 구분선
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);
		
		//배경색상
		Color bgColor = new Color(226, 56, 61);
		g.setColor(bgColor);
		
		//폰트
		Font f1 = new Font("나눔고딕", Font.BOLD, 20); // 타이틀 폰트
		Font f2 = new Font("나눔고딕", Font.BOLD, 10); // 라벨폰트
		Font f3 = new Font("나눔고딕", Font.PLAIN, 11); // 텍스트 폰트
		Font f4 = new Font("나눔고딕", Font.PLAIN, 10); // 텍스트폰트
		Font f5 = new Font("나눔고딕", Font.PLAIN, 13); // 텍스트 폰트
		Font f6 = new Font("나눔고딕", Font.BOLD, 13); // 텍스트 폰트
		Font f7 = new Font("나눔고딕", Font.BOLD, 30); 
		
		//커서
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR); // 클릭 커서 모양
		

		// 타이틀 라벨
		JLabel title = new JLabel("결제하기");
		add(title);
		title.setFont(f1);
		title.setBounds(120, 10, 100, 30);
		title.setForeground(Color.GRAY);
		title.setBackground(Color.WHITE);
		title.setOpaque(true);
		

		// 카드번호 라벨
		JLabel c_numLb = new JLabel("카드번호");
		add(c_numLb);
		c_numLb.setBounds(120, 70, 100, 20);
		c_numLb.setFont(f2);
	

		// 유효기간 라벨
		JLabel c_yymm = new JLabel("유효기간");
		add(c_yymm);
		c_yymm.setBounds(120, 95, 100, 20);
		c_yymm.setFont(f2);


		// "월" 라벨
		JLabel mm = new JLabel("월");
		add(mm);
		mm.setBounds(260, 95, 20, 20);
		mm.setFont(f4);
		mm.setBackground(Color.WHITE);
		mm.setOpaque(true);


		// "년" 라벨
		JLabel yy = new JLabel("년");
		add(yy);
		yy.setBounds(310, 95, 20, 20);
		yy.setFont(f4);
		yy.setBackground(Color.white);
		yy.setOpaque(true);
	

		// 카드구분 라벨
		JLabel c_classLb = new JLabel("카드구분");
		add(c_classLb);
		c_classLb.setBounds(120, 120, 100, 20);
		c_classLb.setFont(f2);
		

		// 카드비밀번호 라벨
		JLabel c_pwdLb = new JLabel("카드 비밀번호");
		add(c_pwdLb);
		c_pwdLb.setBounds(120, 145, 100, 20);
		c_pwdLb.setFont(f2);

		// 주민등록번호 라벨
		JLabel idnumLb = new JLabel("주민등록번호");
		add(idnumLb);
		idnumLb.setBounds(120, 170, 100, 20);
		idnumLb.setFont(f2);

		
		// 카드번호 텍스트필드
		c_numTf1 = new JTextField(); 
		c_numTf2 = new JTextField(); 
		c_numTf3 = new JTextField(); 
		c_numTf4 = new JTextField(); 
		add(c_numTf1);
		add(c_numTf2);
		add(c_numTf3);
		add(c_numTf4);
		c_numTf1.setBounds(230, 70, 40, 20);
		c_numTf2.setBounds(280, 70, 40, 20);
		c_numTf3.setBounds(330, 70, 40, 20);
		c_numTf4.setBounds(380, 70, 40, 20);	
		
		c_numTf1.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(c_numTf1.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "네 자리로 작성해주세요.");
					c_numTf1.setText(""); // 초기화
				} else if (c_numTf1.getText().trim().length()==4) {
					c_numTf2.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		c_numTf2.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(c_numTf2.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "네 자리로 작성해주세요.");
					c_numTf2.setText(""); // 초기화
				} else if (c_numTf2.getText().trim().length()==4) {
					c_numTf3.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		c_numTf3.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(c_numTf3.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "네 자리로 작성해주세요.");
					c_numTf3.setText(""); // 초기화
				} else if (c_numTf3.getText().trim().length()==4) {
					c_numTf4.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		c_numTf4.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(c_numTf4.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "네 자리로 작성해주세요.");
					c_numTf4.setText(""); // 초기화
				} else if (c_numTf4.getText().trim().length()==4) {
					mmTf.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		
		//카드 유효기간 "월" 텍스트필드
		mmTf = new JTextField();
		add(mmTf);
		mmTf.setBounds(230, 95, 30, 20);
		mmTf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(mmTf.getText().trim().length()>2) { // 두 자리 넘으면
					JOptionPane.showMessageDialog(null, "두 자리로 작성해주세요.");
					mmTf.setText(""); // 초기화
				} else if (mmTf.getText().trim().length()==2) { // 두 자리 일 때 
					yyTf.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		
		//카드 유효기간 "년" 텍스트필드
		yyTf = new JTextField();
		add(yyTf);
		yyTf.setBounds(280, 95, 30, 20);
		yyTf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(yyTf.getText().trim().length()>2) {
					JOptionPane.showMessageDialog(null, "두 자리로 작성해주세요.");
					yyTf.setText(""); // 초기화
				} else if (yyTf.getText().trim().length()==2) {
					pcRBtn.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

		
		// 카드구분 radio
		pcRBtn = new JRadioButton("개인카드", true);
		ccRBtn = new JRadioButton("법인카드");
		ButtonGroup group = new ButtonGroup();
		add(pcRBtn);
		add(ccRBtn);
		group.add(pcRBtn);
		group.add(ccRBtn);
		pcRBtn.setBackground(Color.white);
		pcRBtn.setBounds(230, 120, 80, 15);
		ccRBtn.setBackground(Color.white);
		ccRBtn.setBounds(320, 120, 80, 15);

		// 카드비밀번호 텍스트필드
		c_pwdTf = new JTextField();
		add(c_pwdTf);
		c_pwdTf.setBounds(230, 145, 30, 20);
		c_pwdTf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(c_pwdTf.getText().trim().length()>2) {
					JOptionPane.showMessageDialog(null, "두 자리로 작성해주세요.");
					c_pwdTf.setText(""); // 초기화
				} else if (c_pwdTf.getText().trim().length()==2) {
					idNumTf1.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

//		// 카드비밀번호 라벨2
		JLabel c_pwdLb2 = new JLabel("X X (앞 두자리)");
		add(c_pwdLb2);
		c_pwdLb2.setBounds(260, 145, 80, 20);
		c_pwdLb2.setBackground(Color.WHITE); // 왜 안먹지?
		c_pwdLb2.setOpaque(true);
		c_pwdLb2.setFont(f3);

		// 주민등록번호 텍스트필드
		idNumTf1 = new JTextField();
		idNumTf2 = new JTextField();
		add(idNumTf1);
		add(idNumTf2);
		idNumTf1.setBounds(230, 170, 70, 20);
		idNumTf2.setBounds(310, 170, 70, 20);
		
		idNumTf1.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(idNumTf1.getText().trim().length()>6) {
					JOptionPane.showMessageDialog(null, "주민번호 앞자리는 여섯자리입니다.");
					idNumTf1.setText(""); // 초기화
				} else if (idNumTf1.getText().trim().length()==6) {
					idNumTf2.requestFocus(); // 포커스 이동
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		idNumTf2.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // 누른 키를 떼는 순간 
				if(idNumTf2.getText().trim().length()>7) {
					JOptionPane.showMessageDialog(null, "주민번호 뒷자리는 일곱자리입니다.");
					idNumTf2.setText(""); // 초기화
				} else if (idNumTf2.getText().trim().length()==7) {
					chk1.requestFocus();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		chk1 = new JCheckBox("전체 동의합니다.");
		add(chk1);
		chk1.setFont(f3);
		chk1.addItemListener(null);
		chk1.setBounds(120, 200, 180, 20);
		chk1.setBackground(Color.white);
		JCheckBox chk2 = new JCheckBox("정기과금 이용약관");
		add(chk2);
		chk2.setFont(f3);
		chk2.setBounds(310, 200, 180, 20);
		chk2.setBackground(Color.white);
		JCheckBox chk3 = new JCheckBox("전자금융 이용약관");
		add(chk3);
		chk3.setFont(f3);
		chk3.setBounds(120, 220, 180, 20);
		chk3.setBackground(Color.white);
		JCheckBox chk4 = new JCheckBox("고유식별정보수집 및 이용약관");
		add(chk4);
		chk4.setFont(f3);
		chk4.setBounds(310, 220, 190, 20);
		chk4.setBackground(Color.white);
		JCheckBox chk5 = new JCheckBox("개인정보수집 및 이용안내");
		add(chk5);
		chk5.setFont(f3);
		chk5.setBounds(120, 240, 180, 20);
		chk5.setBackground(Color.white);
		JCheckBox chk6 = new JCheckBox("개인정보제공 및 위탁안내");
		add(chk6);
		chk6.setFont(f3);
		chk6.setBounds(310, 240, 180, 20);
		chk6.setBackground(Color.white);
		
		// 전체동의 체크시 다른 체크박스들도 함께 체크됨
		chk1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED) {// 전체동의 체크박스 선택 
					chk2.setSelected(true);
					chk3.setSelected(true);
					chk4.setSelected(true);
					chk5.setSelected(true);
					chk6.setSelected(true);
				} else if (e.getStateChange()==ItemEvent.DESELECTED) { // 전체동의 체크박스 선택 해제
					// 최소 한개 ~ 모든 체크박스가 선택되지 않은 상태라면
					if (!chk2.isSelected() || !chk3.isSelected() || !chk4.isSelected() || !chk5.isSelected() || !chk6.isSelected()) {
						chk1.setSelected(false); // 전체동의 체크박스 선택 해제
					} else {
						// 모든 체크박스가 선택된 상태라면
						chk2.setSelected(false);
						chk3.setSelected(false);
						chk4.setSelected(false);
						chk5.setSelected(false);
						chk6.setSelected(false);
					}
				}		
			}
		});
		
		// 체크박스2 해제시 전체동의 해제
		chk2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					chk1.setSelected(false);
				} else if(e.getStateChange() == ItemEvent.SELECTED) {
					if(chk2.isSelected() && chk3.isSelected() && chk4.isSelected() && chk5.isSelected() && chk6.isSelected()) {
						chk1.setSelected(true);
					}
				}
			}
		});
		// 체크박스3 해제시 전체동의 해제
		chk3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					chk1.setSelected(false);
				} else if(e.getStateChange() == ItemEvent.SELECTED) {
					if(chk2.isSelected() && chk3.isSelected() && chk4.isSelected() && chk5.isSelected() && chk6.isSelected()) {
						chk1.setSelected(true);
					}
				}
			}
		});
		// 체크박스4 해제시 전체동의 해제
		chk4.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					chk1.setSelected(false);
				} else if(e.getStateChange() == ItemEvent.SELECTED) {
					if(chk2.isSelected() && chk3.isSelected() && chk4.isSelected() && chk5.isSelected() && chk6.isSelected()) {
						chk1.setSelected(true);
					}
				}
			}
		});
		// 체크박스5 해제시 전체동의 해제
		chk5.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					chk1.setSelected(false);
				} else if(e.getStateChange() == ItemEvent.SELECTED) {
					if(chk2.isSelected() && chk3.isSelected() && chk4.isSelected() && chk5.isSelected() && chk6.isSelected()) {
						chk1.setSelected(true);
					}
				}
			}
		});
		// 체크박스6 해제시 전체동의 해제
		chk6.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					chk1.setSelected(false);
				} else if(e.getStateChange() == ItemEvent.SELECTED) {
					if(chk2.isSelected() && chk3.isSelected() && chk4.isSelected() && chk5.isSelected() && chk6.isSelected()) {
						chk1.setSelected(true);
					}
				}
			}
		});		
		
		// 결제버튼
		JButton pBtn = new JButton("결   제");
		add(pBtn);
		pBtn.setBounds(620, 510, 150, 40);
		pBtn.setBackground(Color.WHITE);
		pBtn.setForeground(g.getColor());
		pBtn.setFont(f6);
		pBtn.setCursor(cursor); // 마우스커서 설정
		
		//텍스트필드&약관동의 다 됐는지 검사
		pBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			if(mmTf.getText().length() != 2 || yyTf.getText().length() != 2) {
				JOptionPane.showMessageDialog(null, "카드 유효기간을 입력하세요.");
			} else if(c_pwdTf.getText().length() != 2) {
				JOptionPane.showMessageDialog(null, "카드 비밀번호를 입력하세요.");
			} else if(idNumTf1.getText().length() != 6  || idNumTf2.getText().length() != 7  ) {
				JOptionPane.showMessageDialog(null, "주민등록 번호를 입력하세요.");
			} else if(c_numTf1.getText().length() != 4 || c_numTf2.getText().length() != 4 
					|| c_numTf3.getText().length() != 4 || c_numTf4.getText().length() != 4) {
				JOptionPane.showMessageDialog(null, "카드 번호를 입력하세요.");
			} else if (!chk2.isSelected()||!chk3.isSelected()||!chk4.isSelected()||!chk5.isSelected()||!chk6.isSelected()) { 
				JOptionPane.showMessageDialog(null, "모든 약관에 동의해주세요.");
			} else {
				System.out.println("결제완료!");
				PaymentMgr mgr = new PaymentMgr();
				mgr.updateStatus(userId);
				System.out.println(userId + "님 결제상태가 결제 전에서 결제완료로 변경되었습니다.");
				JOptionPane.showMessageDialog(null, "결제 성공하였습니다. 예약해주셔서 감사합니다.");
				dispose();
				}
			}
		});
		

			

	
	}

	// 생성자
	public CardPaymentFrame() {
		this(null);
	}

	// 생성자 (매개변수)
	public CardPaymentFrame(String userId) {
		this.userId = userId;

		// 프레임설정
		setTitle(userId + "님 카드결제창");
		setSize(800, 600);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setLayout(null);// 레이아웃을 내맘대로 설정가능하게 해줌.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게



	}

	public static void main(String[] args) {// 실행
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardPaymentFrame CardPaymentFrame = new CardPaymentFrame();
					CardPaymentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
