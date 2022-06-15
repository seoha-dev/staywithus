/* ȸ������â | ������ ������¥: 2022-03-25 | ������ ������: �輭�� */
//ȸ�����Խ� mode���� �⺻ 0 (�����) �� �����Ǿ�����
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
		private JPasswordField pwdTf, pwdTf2; // pwdTf2 = ��й�ȣ Ȯ��
		private JRadioButton femaleRB, maleRB;
		private ButtonGroup group;


		// ����
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
		
		// �ؽ�Ʈ�ʵ� ���ڼ� ���Ѱɱ� -JTextField
		class JTextFieldLimit extends PlainDocument {

			public int limit;

			public JTextFieldLimit(int limit) { // ������
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
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//�������������� ȸ������ â �ݾƵ� ���������� ���� �� ������ ����
			setSize(430, 700);
			setLocationRelativeTo(null);
			
			// Panel ����
			p1 = new JPanel();
			p1.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(p1);
			p1.setBackground(Color.white);
			p1.setLayout(null);
			
			// font ����
			Font f1 = new Font("���� ���", Font.BOLD, 20); //Ÿ��Ʋ��Ʈ
			Font f2 = new Font("���� ���", Font.BOLD, 12); //����Ʈ
			Font f3 = new Font("���� ���", Font.BOLD, 12); //�ؽ�Ʈ�ʵ���Ʈ
		
			// "ȸ������" Ÿ��Ʋ ��
			titleLb = new JLabel("ȸ������");
			titleLb.setFont(f1);
			titleLb.setBounds(159, 41, 101, 30);
			add(titleLb);
			
			/*-- �󺧰� �ؽ�Ʈ�ʵ� y�� ���� ���� 50��--*/
			// "���̵�" ��
			idLb = new JLabel("���̵�");
			idLb.setFont(f2);
			idLb.setBounds(69, 113, 69, 20);
			add(idLb);
			// "���̵�" �ؽ�Ʈ�ʵ�
			idTf = new JTextField();
			idTf.setColumns(10);
			idTf.setBounds(159, 106, 90, 30);
			idTf.setDocument(new JTextFieldLimit(8));
			add(idTf);
			
			// ���̵� "�ߺ�Ȯ��" ��ư
			idCheckBtn = new JButton("�ߺ�Ȯ��");
			idCheckBtn.setFont(f3);
			idCheckBtn.setBounds(255, 106, 85, 30);
			idCheckBtn.setForeground(Color.WHITE);
			idCheckBtn.setBackground(Color.BLACK);
			add(idCheckBtn);
			
			// "��й�ȣ" ��
			pwdLb = new JLabel("��й�ȣ");
			pwdLb.setFont(f2);
			pwdLb.setBounds(69, 163, 69, 20);
			add(pwdLb);
			// "��й�ȣ" �ؽ�Ʈ�ʵ�
			pwdTf = new JPasswordField();
			pwdTf.setColumns(10);
			pwdTf.setBounds(159, 156, 186, 30);
			pwdTf.setEchoChar('��');
			pwdTf.setDocument(new JTextFieldLimit(8));
			add(pwdTf);
			
			// "��й�ȣ Ȯ��" ��
			pwd2Lb = new JLabel("��й�ȣ Ȯ��");
			pwd2Lb.setFont(f2);
			pwd2Lb.setBounds(69, 213, 80, 20);
			add(pwd2Lb);
			// "��й�ȣ Ȯ��" �ؽ�Ʈ�ʵ�
			pwdTf2 = new JPasswordField();
			pwdTf2.setColumns(10);
			pwdTf2.setBounds(159, 206, 186, 30);
			pwdTf2.setEchoChar('��');
			pwdTf2.setDocument(new JTextFieldLimit(8));
			add(pwdTf2);
			
			// "�̸�" ��
			nameLb = new JLabel("�̸�");
			nameLb.setFont(f2);
			nameLb.setBounds(69, 263, 69, 20);
			add(nameLb);
			// "�̸�" �ؽ�Ʈ�ʵ� 
			nameTf = new JTextField();
			nameTf.setColumns(10);
			nameTf.setBounds(159, 256, 186, 30);
			add(nameTf);
			
			// "�������" ��
			birthdayLb = new JLabel("�������");
			birthdayLb.setFont(f2);
			birthdayLb.setBounds(69, 313, 69, 20);
			add(birthdayLb);
			// "�������" �ؽ�Ʈ�ʵ�
			birthdayTf = new JTextField();
			birthdayTf.setColumns(10);
			birthdayTf.setBounds(159, 306, 186, 30);
			birthdayTf.setDocument(new JTextFieldLimit(10));
			add(birthdayTf);
			
			
			// "����" ��
			genderLb = new JLabel("����");
			genderLb.setFont(f2);
			genderLb.setBounds(69, 363, 69, 20);
			add(genderLb);
			// "����" radio 
			femaleRB = new JRadioButton("����",true);
			femaleRB.setBounds(159, 356, 50, 30);
			femaleRB.setBackground(Color.WHITE);
			add(femaleRB);
			maleRB = new JRadioButton("����");
			maleRB.setBounds(280, 356, 50, 30);
			maleRB.setBackground(Color.WHITE);
			add(maleRB);
			// "����" �׷�ȭ
			group = new ButtonGroup();
			group.add(femaleRB);
			group.add(maleRB);
			
			// "�̸���" ��
			emailLb = new JLabel("�̸���");
			emailLb.setFont(f2);
			emailLb.setBounds(69, 413, 69, 20);
			add(emailLb);
			// "�̸���" �ؽ�Ʈ�ʵ�
			emailTf = new JTextField();
			emailTf.setColumns(10);
			emailTf.setBounds(159, 406, 186, 30);
			add(emailTf);
			
			// "����ó" ��
			phoneLb = new JLabel("����ó");
			phoneLb.setFont(f2);
			phoneLb.setBounds(69, 463, 69, 20);
			add(phoneLb);
			// "����ó" �ؽ�Ʈ�ʵ�
			phoneTf = new JTextField();
			phoneTf.setColumns(10);
			phoneTf.setBounds(159, 456, 186, 30);
			phoneTf.setDocument(new JTextFieldLimit(13));
			add(phoneTf);
			
			// "ȸ������ �Ϸ�" ��ư
			joinBtn = new JButton("ȸ������ �Ϸ�");
			joinBtn.setBounds(147, 533, 139, 30);
			joinBtn.setFont(f3);
			joinBtn.setForeground(Color.WHITE);
			joinBtn.setBackground(Color.BLACK);
			add(joinBtn);
			
			
//			birthdayTf.setText("YYYY-MM-DD");
			setVisible(true);
			
			
			/* ��ɼ��� */
			
			// ȸ�����ԿϷ� ��ư �׼�
			joinBtn.addActionListener(new ActionListener() {
				
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					if (idTf.getText().equals("")) { // ���̵� �Ⱦ�
						JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.");
					} else if (pwdTf.getText().equals("")) { // ��й�ȣ �Ⱦ�
						JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
					} else if (!pwdTf2.getText().equals(pwdTf.getText())) { // ��й�ȣ Ȯ�� ����ġ
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					} else if (nameTf.getText().equals("")) { // �̸� �Ⱦ�
						JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���.");
					} else if (birthdayTf.getText().equals("")) { // ���� �Ⱦ�
						JOptionPane.showMessageDialog(null, "������ �Է��ϼ���.");
					} else if (birthdayTf.getText().length() < 8)	 {
						JOptionPane.showMessageDialog(null, "YYYYMMDD �������� ������ �Է��ϼ���.");
					} else if (emailTf.getText().equals("")) { // �̸��� �Ⱦ�
						JOptionPane.showMessageDialog(null, "�̸����� �Է��ϼ���.");
					} else if (phoneTf.getText().equals("")) { // ����ó �Ⱦ�
						JOptionPane.showMessageDialog(null, "����ó�� �Է��ϼ���");
					} else if (mgr.idChk(idTf.getText().trim())) {
						System.out.println("���̵��ߺ�Ȯ��: DB�� ����");
						JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ���� ���ּ���.");
					} else { // ���� �� ���� ����..? 
						JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
						UserBean bean = new UserBean();
						bean.setId(idTf.getText());
						bean.setPwd(pwdTf.getText());
						bean.setName(nameTf.getText());
						bean.setEmail(emailTf.getText());
						bean.setPhone(phoneTf.getText());
						bean.setBirthday(birthdayTf.getText());
						// radio�ڽ����� ������ ã�ƿ���
						String gen = "����"; // gender�� not null �̾ �⺻ �������� ����
						bean.setGender(gen);
						if (femaleRB.isSelected()) {
							gen = "��";
						} else {
							gen = "��";
						}
						if (mgr.userSign(bean)) {
							System.out.println("[joinFrame] "+ idTf.getText()+ "ȸ�� ��ϿϷ�");
							dispose(); // --��ϿϷ� �� â ����
						}	
					}
				}

			});
			
			// ���� ���ڸ�
			birthdayTf.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			
			
			// ���̵� �ߺ�Ȯ�� ��ư �׼�
			idCheckBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					if (obj == idCheckBtn) {
						if (mgr.idChk(idTf.getText().trim()/* true */)) {
							System.out.println("���̵��ߺ�Ȯ��: DB�� ����");
							JOptionPane.showMessageDialog(null, "����� �� ���� ���̵��Դϴ�.");
							idTf.setText("");
						} else {
							System.out.println("���̵��ߺ�Ȯ��: DB�� ����");
							JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.");
						}
					} 
				}
			});
		
		}
	}