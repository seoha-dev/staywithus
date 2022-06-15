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
	private JTextField c_numTf1, c_numTf2, c_numTf3, c_numTf4; // ī���ȣ 16�ڸ� 
	private JTextField yyTf, mmTf; // ��ȿ�Ⱓ ���
	private JRadioButton pcRBtn, ccRBtn; // ����, ���� ���� 
	private JTextField c_pwdTf; // ī�� ��й�ȣ
	private JTextField idNumTf1, idNumTf2; // �ֹε�Ϲ�ȣ
	private JCheckBox chk1; // ��ü���� üũ�ڽ�

	// �׸��� �Լ�
	public void paint(Graphics g) {
		
		// ����̹���
		g.drawImage(bkImg, 0, 10, null);

		// ���м�
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);
		
		//������
		Color bgColor = new Color(226, 56, 61);
		g.setColor(bgColor);
		
		//��Ʈ
		Font f1 = new Font("�������", Font.BOLD, 20); // Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("�������", Font.BOLD, 10); // ����Ʈ
		Font f3 = new Font("�������", Font.PLAIN, 11); // �ؽ�Ʈ ��Ʈ
		Font f4 = new Font("�������", Font.PLAIN, 10); // �ؽ�Ʈ��Ʈ
		Font f5 = new Font("�������", Font.PLAIN, 13); // �ؽ�Ʈ ��Ʈ
		Font f6 = new Font("�������", Font.BOLD, 13); // �ؽ�Ʈ ��Ʈ
		Font f7 = new Font("�������", Font.BOLD, 30); 
		
		//Ŀ��
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR); // Ŭ�� Ŀ�� ���
		

		// Ÿ��Ʋ ��
		JLabel title = new JLabel("�����ϱ�");
		add(title);
		title.setFont(f1);
		title.setBounds(120, 10, 100, 30);
		title.setForeground(Color.GRAY);
		title.setBackground(Color.WHITE);
		title.setOpaque(true);
		

		// ī���ȣ ��
		JLabel c_numLb = new JLabel("ī���ȣ");
		add(c_numLb);
		c_numLb.setBounds(120, 70, 100, 20);
		c_numLb.setFont(f2);
	

		// ��ȿ�Ⱓ ��
		JLabel c_yymm = new JLabel("��ȿ�Ⱓ");
		add(c_yymm);
		c_yymm.setBounds(120, 95, 100, 20);
		c_yymm.setFont(f2);


		// "��" ��
		JLabel mm = new JLabel("��");
		add(mm);
		mm.setBounds(260, 95, 20, 20);
		mm.setFont(f4);
		mm.setBackground(Color.WHITE);
		mm.setOpaque(true);


		// "��" ��
		JLabel yy = new JLabel("��");
		add(yy);
		yy.setBounds(310, 95, 20, 20);
		yy.setFont(f4);
		yy.setBackground(Color.white);
		yy.setOpaque(true);
	

		// ī�屸�� ��
		JLabel c_classLb = new JLabel("ī�屸��");
		add(c_classLb);
		c_classLb.setBounds(120, 120, 100, 20);
		c_classLb.setFont(f2);
		

		// ī���й�ȣ ��
		JLabel c_pwdLb = new JLabel("ī�� ��й�ȣ");
		add(c_pwdLb);
		c_pwdLb.setBounds(120, 145, 100, 20);
		c_pwdLb.setFont(f2);

		// �ֹε�Ϲ�ȣ ��
		JLabel idnumLb = new JLabel("�ֹε�Ϲ�ȣ");
		add(idnumLb);
		idnumLb.setBounds(120, 170, 100, 20);
		idnumLb.setFont(f2);

		
		// ī���ȣ �ؽ�Ʈ�ʵ�
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
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(c_numTf1.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "�� �ڸ��� �ۼ����ּ���.");
					c_numTf1.setText(""); // �ʱ�ȭ
				} else if (c_numTf1.getText().trim().length()==4) {
					c_numTf2.requestFocus(); // ��Ŀ�� �̵�
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
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(c_numTf2.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "�� �ڸ��� �ۼ����ּ���.");
					c_numTf2.setText(""); // �ʱ�ȭ
				} else if (c_numTf2.getText().trim().length()==4) {
					c_numTf3.requestFocus(); // ��Ŀ�� �̵�
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
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(c_numTf3.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "�� �ڸ��� �ۼ����ּ���.");
					c_numTf3.setText(""); // �ʱ�ȭ
				} else if (c_numTf3.getText().trim().length()==4) {
					c_numTf4.requestFocus(); // ��Ŀ�� �̵�
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
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(c_numTf4.getText().trim().length()>4) {
					JOptionPane.showMessageDialog(null, "�� �ڸ��� �ۼ����ּ���.");
					c_numTf4.setText(""); // �ʱ�ȭ
				} else if (c_numTf4.getText().trim().length()==4) {
					mmTf.requestFocus(); // ��Ŀ�� �̵�
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		
		//ī�� ��ȿ�Ⱓ "��" �ؽ�Ʈ�ʵ�
		mmTf = new JTextField();
		add(mmTf);
		mmTf.setBounds(230, 95, 30, 20);
		mmTf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(mmTf.getText().trim().length()>2) { // �� �ڸ� ������
					JOptionPane.showMessageDialog(null, "�� �ڸ��� �ۼ����ּ���.");
					mmTf.setText(""); // �ʱ�ȭ
				} else if (mmTf.getText().trim().length()==2) { // �� �ڸ� �� �� 
					yyTf.requestFocus(); // ��Ŀ�� �̵�
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		
		//ī�� ��ȿ�Ⱓ "��" �ؽ�Ʈ�ʵ�
		yyTf = new JTextField();
		add(yyTf);
		yyTf.setBounds(280, 95, 30, 20);
		yyTf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(yyTf.getText().trim().length()>2) {
					JOptionPane.showMessageDialog(null, "�� �ڸ��� �ۼ����ּ���.");
					yyTf.setText(""); // �ʱ�ȭ
				} else if (yyTf.getText().trim().length()==2) {
					pcRBtn.requestFocus(); // ��Ŀ�� �̵�
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

		
		// ī�屸�� radio
		pcRBtn = new JRadioButton("����ī��", true);
		ccRBtn = new JRadioButton("����ī��");
		ButtonGroup group = new ButtonGroup();
		add(pcRBtn);
		add(ccRBtn);
		group.add(pcRBtn);
		group.add(ccRBtn);
		pcRBtn.setBackground(Color.white);
		pcRBtn.setBounds(230, 120, 80, 15);
		ccRBtn.setBackground(Color.white);
		ccRBtn.setBounds(320, 120, 80, 15);

		// ī���й�ȣ �ؽ�Ʈ�ʵ�
		c_pwdTf = new JTextField();
		add(c_pwdTf);
		c_pwdTf.setBounds(230, 145, 30, 20);
		c_pwdTf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(c_pwdTf.getText().trim().length()>2) {
					JOptionPane.showMessageDialog(null, "�� �ڸ��� �ۼ����ּ���.");
					c_pwdTf.setText(""); // �ʱ�ȭ
				} else if (c_pwdTf.getText().trim().length()==2) {
					idNumTf1.requestFocus(); // ��Ŀ�� �̵�
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

//		// ī���й�ȣ ��2
		JLabel c_pwdLb2 = new JLabel("X X (�� ���ڸ�)");
		add(c_pwdLb2);
		c_pwdLb2.setBounds(260, 145, 80, 20);
		c_pwdLb2.setBackground(Color.WHITE); // �� �ȸ���?
		c_pwdLb2.setOpaque(true);
		c_pwdLb2.setFont(f3);

		// �ֹε�Ϲ�ȣ �ؽ�Ʈ�ʵ�
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
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(idNumTf1.getText().trim().length()>6) {
					JOptionPane.showMessageDialog(null, "�ֹι�ȣ ���ڸ��� �����ڸ��Դϴ�.");
					idNumTf1.setText(""); // �ʱ�ȭ
				} else if (idNumTf1.getText().trim().length()==6) {
					idNumTf2.requestFocus(); // ��Ŀ�� �̵�
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
			public void keyReleased(KeyEvent e) { // ���� Ű�� ���� ���� 
				if(idNumTf2.getText().trim().length()>7) {
					JOptionPane.showMessageDialog(null, "�ֹι�ȣ ���ڸ��� �ϰ��ڸ��Դϴ�.");
					idNumTf2.setText(""); // �ʱ�ȭ
				} else if (idNumTf2.getText().trim().length()==7) {
					chk1.requestFocus();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		chk1 = new JCheckBox("��ü �����մϴ�.");
		add(chk1);
		chk1.setFont(f3);
		chk1.addItemListener(null);
		chk1.setBounds(120, 200, 180, 20);
		chk1.setBackground(Color.white);
		JCheckBox chk2 = new JCheckBox("������� �̿���");
		add(chk2);
		chk2.setFont(f3);
		chk2.setBounds(310, 200, 180, 20);
		chk2.setBackground(Color.white);
		JCheckBox chk3 = new JCheckBox("���ڱ��� �̿���");
		add(chk3);
		chk3.setFont(f3);
		chk3.setBounds(120, 220, 180, 20);
		chk3.setBackground(Color.white);
		JCheckBox chk4 = new JCheckBox("�����ĺ��������� �� �̿���");
		add(chk4);
		chk4.setFont(f3);
		chk4.setBounds(310, 220, 190, 20);
		chk4.setBackground(Color.white);
		JCheckBox chk5 = new JCheckBox("������������ �� �̿�ȳ�");
		add(chk5);
		chk5.setFont(f3);
		chk5.setBounds(120, 240, 180, 20);
		chk5.setBackground(Color.white);
		JCheckBox chk6 = new JCheckBox("������������ �� ��Ź�ȳ�");
		add(chk6);
		chk6.setFont(f3);
		chk6.setBounds(310, 240, 180, 20);
		chk6.setBackground(Color.white);
		
		// ��ü���� üũ�� �ٸ� üũ�ڽ��鵵 �Բ� üũ��
		chk1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED) {// ��ü���� üũ�ڽ� ���� 
					chk2.setSelected(true);
					chk3.setSelected(true);
					chk4.setSelected(true);
					chk5.setSelected(true);
					chk6.setSelected(true);
				} else if (e.getStateChange()==ItemEvent.DESELECTED) { // ��ü���� üũ�ڽ� ���� ����
					// �ּ� �Ѱ� ~ ��� üũ�ڽ��� ���õ��� ���� ���¶��
					if (!chk2.isSelected() || !chk3.isSelected() || !chk4.isSelected() || !chk5.isSelected() || !chk6.isSelected()) {
						chk1.setSelected(false); // ��ü���� üũ�ڽ� ���� ����
					} else {
						// ��� üũ�ڽ��� ���õ� ���¶��
						chk2.setSelected(false);
						chk3.setSelected(false);
						chk4.setSelected(false);
						chk5.setSelected(false);
						chk6.setSelected(false);
					}
				}		
			}
		});
		
		// üũ�ڽ�2 ������ ��ü���� ����
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
		// üũ�ڽ�3 ������ ��ü���� ����
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
		// üũ�ڽ�4 ������ ��ü���� ����
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
		// üũ�ڽ�5 ������ ��ü���� ����
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
		// üũ�ڽ�6 ������ ��ü���� ����
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
		
		// ������ư
		JButton pBtn = new JButton("��   ��");
		add(pBtn);
		pBtn.setBounds(620, 510, 150, 40);
		pBtn.setBackground(Color.WHITE);
		pBtn.setForeground(g.getColor());
		pBtn.setFont(f6);
		pBtn.setCursor(cursor); // ���콺Ŀ�� ����
		
		//�ؽ�Ʈ�ʵ�&������� �� �ƴ��� �˻�
		pBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			if(mmTf.getText().length() != 2 || yyTf.getText().length() != 2) {
				JOptionPane.showMessageDialog(null, "ī�� ��ȿ�Ⱓ�� �Է��ϼ���.");
			} else if(c_pwdTf.getText().length() != 2) {
				JOptionPane.showMessageDialog(null, "ī�� ��й�ȣ�� �Է��ϼ���.");
			} else if(idNumTf1.getText().length() != 6  || idNumTf2.getText().length() != 7  ) {
				JOptionPane.showMessageDialog(null, "�ֹε�� ��ȣ�� �Է��ϼ���.");
			} else if(c_numTf1.getText().length() != 4 || c_numTf2.getText().length() != 4 
					|| c_numTf3.getText().length() != 4 || c_numTf4.getText().length() != 4) {
				JOptionPane.showMessageDialog(null, "ī�� ��ȣ�� �Է��ϼ���.");
			} else if (!chk2.isSelected()||!chk3.isSelected()||!chk4.isSelected()||!chk5.isSelected()||!chk6.isSelected()) { 
				JOptionPane.showMessageDialog(null, "��� ����� �������ּ���.");
			} else {
				System.out.println("�����Ϸ�!");
				PaymentMgr mgr = new PaymentMgr();
				mgr.updateStatus(userId);
				System.out.println(userId + "�� �������°� ���� ������ �����Ϸ�� ����Ǿ����ϴ�.");
				JOptionPane.showMessageDialog(null, "���� �����Ͽ����ϴ�. �������ּż� �����մϴ�.");
				dispose();
				}
			}
		});
		

			

	
	}

	// ������
	public CardPaymentFrame() {
		this(null);
	}

	// ������ (�Ű�����)
	public CardPaymentFrame(String userId) {
		this.userId = userId;

		// �����Ӽ���
		setTitle(userId + "�� ī�����â");
		setSize(800, 600);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		setLayout(null);// ���̾ƿ��� ������� ���������ϰ� ����.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame�� ���������� ����ǰ�



	}

	public static void main(String[] args) {// ����
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
