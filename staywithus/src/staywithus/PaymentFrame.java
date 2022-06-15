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
	// ī��� ��ư �迭
	private JButton cardBtn[] = new JButton[12]; // ũ�� 8 ����
	private String cardName[] = { "KAKAOPAY", "LPAY", "����ī��", "�Ｚī��", "��ī��", "KB����", "����ī��", "�Ե�ī��", "NH����", "�ϳ�ī��",
			"��Ƽī��", "UnionPay" };
	private Checkbox agmCb1, agmCb2, agmCb3;
	private Checkbox allChkCb;
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	private String userId;
	private JButton nextBtn;
	private Color cardLbColor;
	private int clickCnt;
	


	public static void main(String[] args) {// ����
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

	// �׸��� �Լ�
	public void paint(Graphics g) {
		// ����̹���
		g.drawImage(bkImg, 0, 10, null);

		// ���м�
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);

		// ��Ʈ
		Font f1 = new Font("�������", Font.BOLD, 18); // Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("�������", Font.BOLD, 11); // ��ư ��Ʈ
		Font f3 = new Font("�������", Font.PLAIN, 11); // �ؽ�Ʈ ��Ʈ
		Font f4 = new Font("�������", Font.BOLD, 13); // �ؽ�Ʈ ��Ʈ
		Font f5 = new Font("�������", Font.PLAIN, 13); // �ؽ�Ʈ ��Ʈ

		// �ſ�ī�� ��
		JLabel cardLb = new JLabel("�ſ�ī��");
		add(cardLb);
		cardLb.setFont(f1);

		// ��� ���� �÷�
		cardLbColor = new Color(226, 56, 61);
		g.setColor(cardLbColor);
		cardLb.setForeground(Color.WHITE);
		cardLb.setBackground(g.getColor());
		cardLb.setOpaque(true);
		cardLb.setBounds(10, 40, 90, 30);

		// �̿��� ��
		JLabel agmTitle = new JLabel("�̿���");
		agmTitle.setFont(f2);
		add(agmTitle);
		agmTitle.setBackground(Color.white);
		agmTitle.setOpaque(true);
		agmTitle.setBounds(120, 20, 70, 20);

		// 3�� ��� ��
		JLabel agmLb1 = new JLabel("���ڱ����ŷ� �̿���");
		agmLb1.setFont(f3);
		add(agmLb1);
		agmLb1.setBackground(Color.WHITE);
		agmLb1.setOpaque(true);
		agmLb1.setBounds(120, 60, 140, 20);

		JLabel agmLb2 = new JLabel("�������� ���� �� �̿�ȳ�");
		agmLb2.setFont(f3);
		add(agmLb2);
		agmLb2.setBackground(Color.WHITE);
		agmLb2.setOpaque(true);
		agmLb2.setBounds(120, 80, 140, 20);

		JLabel agmLb3 = new JLabel("�������� ���� �� ��Ź�ȳ�");
		agmLb3.setFont(f3);
		add(agmLb3); 
		agmLb3.setBackground(Color.WHITE);
		agmLb3.setOpaque(true);
		agmLb3.setBounds(350, 60, 140, 20);

		// 3���� ��� üũ�ڽ� - 1
		agmCb1 = new Checkbox(); // üũ�ڽ� �迭 ��ü ����
		add(agmCb1);
		agmCb1.setBackground(Color.WHITE);
		agmCb1.setBounds(270, 60, 10, 20);

		// ������ ������
		agmCb1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					check++; // private ������ �����ؼ� ��ܿ� �ø��� �ذ�
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

		JLabel agmAgreeLb1 = new JLabel("����");
		agmAgreeLb1.setFont(f3);
		add(agmAgreeLb1);
		agmAgreeLb1.setBackground(Color.WHITE);
		agmAgreeLb1.setOpaque(true);
		agmAgreeLb1.setBounds(283, 60, 30, 20);
		agmAgreeLb1.setCursor(cursor);

		agmAgreeLb1.addMouseListener(new MouseListener() {
			// üũ�ڽ��� �ؽ�Ʈ "����" �ٷ� �־��ְ� �׼Ǹ����� �ص� ������,
			// üũ�ڽ��� �ؽ�Ʈ ��Ʈ ������ �ȵǾ
			// ���� �ؽ�Ʈ "����"�� �󺧷� ���� ��, Ŭ���Ͽ��� �� üũ�ڽ� on/off ��۱���� �����Ͽ���.

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
					if (!agmCb1.getState()) { // üũ�ڽ� false ������ �� (=��üũ����)
						agmCb1.setState(true); // false -> true
						check++;
						System.out.println(check);
						if (agmCb1.getState() && agmCb2.getState() && agmCb3.getState()) { // ��� ���õǾ��ִٸ�
							check = 3;
							allChkCb.setState(true); // ��ü���� CB �ڵ�üũ
						}
					} else { // üũ�ڽ� true ������ �� (=��üũ����)
						agmCb1.setState(false); // true -> false
						check--;
						System.out.println(check);
						allChkCb.setState(false); // �ּ� 1���� false ���°� �Ǿ�����, ��ü���� CB üũ����

					}
				}
			}
		});

		// 3���� ��� üũ�ڽ� - 2
		agmCb2 = new Checkbox(); // üũ�ڽ� �迭 ��ü ����
		add(agmCb2);
		agmCb2.setBackground(Color.WHITE);
		agmCb2.setBounds(270, 80, 10, 20);

		// ������ ������
		agmCb2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					check++; // private ������ �����ؼ� ��ܿ� �ø��� �ذ�
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

		JLabel agmAgreeLb2 = new JLabel("����");
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

		// 3���� ��� üũ�ڽ� - 3
		agmCb3 = new Checkbox(); // üũ�ڽ� �迭 ��ü ����
		add(agmCb3);
		agmCb3.setBackground(Color.WHITE);
		agmCb3.setBounds(530, 60, 10, 20);

		// ������ ������
		agmCb3.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					check++; // private ������ �����ؼ� ��ܿ� �ø��� �ذ�
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

		JLabel agmAgreeLb3 = new JLabel("����");
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

		// ��ü���� üũ�ڽ�
		allChkCb = new Checkbox();
		add(allChkCb);
		allChkCb.setBackground(Color.WHITE);
		allChkCb.setBounds(530, 20, 10, 20);
		allChkCb.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getSource() == allChkCb) {
					if (!allChkCb.getState()) { // ��ü���� CB false ����
						if (!agmCb1.getState() || !agmCb2.getState() || !agmCb3.getState()) { // �� ���� üũ�ڽ� false ������ ��
							agmCb1.setState(true);
							agmCb2.setState(true);
							agmCb3.setState(true);
							
							allChkCb.setState(true);
							check = 3;
							System.out.println(check);
						}
					} else if (allChkCb.getState()) {// ��ü���� CB true ����
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

		// ��ü���� ��
		JLabel allChkLb = new JLabel("��ü����");
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
					if (!allChkCb.getState()) { // ��ü���� CB false ����
						if (!agmCb1.getState() || !agmCb2.getState() || !agmCb3.getState()) { // �� ���� üũ�ڽ� false ������ ��
							agmCb1.setState(true);
							agmCb2.setState(true);
							agmCb3.setState(true);
							allChkCb.setState(true);
							check = 3;
							System.out.println(check);
						}
					} else if (allChkCb.getState()) {// ��ü���� CB true ����
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

		// �����ݾ� �ؽ�Ʈ�κ� ��
		JLabel costLb = new JLabel("�����ݾ�");
		add(costLb);
		costLb.setFont(f5);
		costLb.setBackground(g.getColor());
		costLb.setForeground(Color.WHITE);
		costLb.setOpaque(true);
		costLb.setBounds(620, 140, 50, 40);

		// �����ݾ� �ݾ׺κ� ��
		JLabel getCostLb = new JLabel(); // �ϴ� ����
		PaymentMgr mgr = new PaymentMgr();
		if(mgr.totalCostChk(userId)>0) {
			int totalCost = mgr.totalCostChk(userId);
			DecimalFormat df = new DecimalFormat("###,###"); // �ݾ� ǥ�� ����
			getCostLb.setText(df.format(totalCost)+"��");
		}
		add(getCostLb);
		getCostLb.setFont(f1);
		getCostLb.setBackground(g.getColor());
		getCostLb.setForeground(Color.WHITE);
		getCostLb.setOpaque(true);
		getCostLb.setBounds(640, 200, 130, 40);

		JLabel nextLb = new JLabel("��   ��");
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
				if (check == 3 /* 1. CB ��� üũ�Ǿ��ٸ� */ && clickCnt == 1 /* 2.�������� �� �� ���õǾ��ٸ�*/) {
					System.out.println(userId);
					dispose();
					CardPaymentFrame cpf = new CardPaymentFrame(userId);
					cpf.setVisible(true);

				} else if(check < 3 && clickCnt == 1) { // ���������� ������� ��� 3�� ��� üũ�� �ƴ϶��
					JOptionPane.showMessageDialog(null, "��� ����� �������ּ���.");
					System.out.println("������� ���� : " + check + "��, �������� üũ ���� : " + clickCnt + "��");
				} else if(check == 3 && clickCnt < 1) { // ��� 3�� ��� üũ������ �������� ���� �ʾҴٸ� 
					JOptionPane.showMessageDialog(null, "���������� �������ּ���.");
					System.out.println("�������� üũ ���� : " + clickCnt + "��");
				}

			}
		});

	}

	// ������
	public PaymentFrame() {
		this(null);
	}

	// ������
	public PaymentFrame(String userId) {
		this.userId = userId;
		// ������ ����
		setTitle(userId + "�� ����");// Ÿ��Ʋ
		setSize(800, 600);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		setLayout(null);// ���̾ƿ��� ������� ���������ϰ� ����.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//â ���� �� ����ȭ�� ���� �� ������..3��25�� ����ȭ����

		
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
						JOptionPane.showMessageDialog(null, "���������� �ϳ��� �����ϼ���.");
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
