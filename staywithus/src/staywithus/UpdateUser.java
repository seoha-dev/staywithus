/* ����� ���������� ���������� | ������ ������¥: 2022-03-25 | ������ ������: �輭�� */

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
	
	//����
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

	// ������ (�Ű�����)
	public UpdateUser(String userId) {
		
		// �⺻ ����
		jf.setSize(1200,800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//����� ũ��
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//�������� ũ��
		Dimension fDim = jf.getSize();
		
		// �������� ���� �𼭸� ��ǥ
		// �߾���ǥ : (����� ũ�� - ������ ũ��) / 2
		int x = (int)((dim.getWidth()-fDim.getWidth())/2);
		int y = (int)((dim.getHeight()- fDim.getHeight())/2);
		
		// ������ ��ġ ��Ű��
		jf.setLocation(x, y);
		
		// font ����
		Font f1 = new Font("���� ���", Font.BOLD, 40); //Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("���� ���", Font.BOLD, 12); //��ư ��Ʈ
		Font f3 = new Font("���� ���", Font.BOLD, 12); //�� ��Ʈ
		

		jf.setTitle(userId+"  ����������");
		p1 = new JPanel();
		
		//�ĳڲٹ̱�
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		//����������ư(���缱�õ�)
		updateBtn = new JButton("����������");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.black);
		updateBtn.setBackground(Color.white);
		updateBtn.setEnabled(false);
		p1.add(updateBtn);
		
		//������ȸ��ư
		listBtn = new JButton("��������ȸ");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.white);
		listBtn.setBackground(Color.black);
		p1.add(listBtn);
		
		//ȸ��Ż���ư
		delUserBtn = new JButton ("ȸ��Ż��");
		p1.add(delUserBtn);
		delUserBtn.setBounds(20, 300, 100, 30);
		delUserBtn.setFont(f2);
		delUserBtn.setForeground(Color.white);
		delUserBtn.setBackground(Color.lightGray);
		
		//Ȩ��ư(��������)
		homeBtn = new JButton("Ȩ����");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		
		//Ÿ��Ʋ��
		titleLb = new JLabel("�� ���� ����");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);
		
		//"���̵�" ��
		idLb = new JLabel("���̵�");
		idLb.setFont(f3);
		idLb.setBounds(450, 200, 70, 20);
		p1.add(idLb);
		//"���̵�" �ؽ�Ʈ�ʵ�
		idTf = new JTextField();
		idTf.setColumns(10);
		idTf.setBounds(450, 220, 240, 20);
		idTf.setText(userId);
		idTf.setEditable(false);
		p1.add(idTf);
		
		//"��й�ȣ" ��
		pwdLb = new JLabel("��й�ȣ");
		pwdLb.setFont(f3);
		pwdLb.setBounds(450, 250, 70, 20);
		p1.add(pwdLb);
		//"��й�ȣ" �ؽ�Ʈ�ʵ�
		pwdTf = new JPasswordField();
		pwdTf.setColumns(10);
		pwdTf.setBounds(450, 270, 240, 20);
		pwdTf.setEchoChar('��');
		p1.add(pwdTf);		
		
		//"�� ��й�ȣ" ��
		newPwdLb = new JLabel("�� ��й�ȣ");
		newPwdLb.setFont(f3);
		newPwdLb.setBounds(450, 300, 70, 20);
		p1.add(newPwdLb);
		//"�� ��й�ȣ" �ؽ�Ʈ�ʵ�
		newPwdTf = new JPasswordField();
		newPwdTf.setColumns(10);
		newPwdTf.setBounds(450, 320, 240, 20);
		newPwdTf.setEchoChar('��');
		p1.add(newPwdTf);	
		
		//"�̸�" ��
		nameLb = new JLabel("�̸�");
		nameLb.setFont(f3);
		nameLb.setBounds(450, 350, 70, 20);
		p1.add(nameLb);
		//"�̸�" �ؽ�Ʈ�ʵ�
		nameTf = new JTextField();
		nameTf.setColumns(10);
		nameTf.setBounds(450, 370, 240, 20);
		nameTf.setEditable(false);
		p1.add(nameTf);
		
		//"�������" ��
		birthdayLb = new JLabel("�������");
		birthdayLb.setFont(f3);
		birthdayLb.setBounds(450, 400, 70, 20);
		p1.add(birthdayLb);
		//"�������" �ؽ�Ʈ�ʵ�
		birthdayTf = new JTextField();
		birthdayTf.setColumns(10);
		birthdayTf.setBounds(450, 420, 240, 20);
		birthdayTf.setEditable(false);
		p1.add(birthdayTf);
		
		//"����" ��
		genderLb = new JLabel("����");
		genderLb.setFont(f3);
		genderLb.setBounds(450, 450, 70, 20);
		p1.add(genderLb);
		//"����" �ؽ�Ʈ�ʵ� (���� radio�� �ؾ��ϳ�? �ʹ� �����������ʳ�)
		genderTf = new JTextField();
		genderTf.setColumns(10);
		genderTf.setBounds(450, 470, 240, 20);
		genderTf.setEditable(false);
		p1.add(genderTf);
		
		//"�̸���" ��
		emailLb = new JLabel("�̸���");
		emailLb.setFont(f3);
		emailLb.setBounds(450, 500, 70, 20);
		p1.add(emailLb);
		//"�̸���" �ؽ�Ʈ�ʵ�
		emailTf = new JTextField();
		emailTf.setColumns(10);
		emailTf.setBounds(450, 520, 240, 20);
		p1.add(emailTf);		
		
		//"����ó" ��
		phoneLb = new JLabel("����ó");
		phoneLb.setFont(f3);
		phoneLb.setBounds(450, 550, 70, 20);
		p1.add(phoneLb);
		//"����ó" �ؽ�Ʈ�ʵ�
		phoneTf = new JTextField();
		phoneTf.setColumns(10);
		phoneTf.setBounds(450, 570, 240, 20);
		p1.add(phoneTf);		
		
		//"����" ��ư
		saveBtn = new JButton("����");
		saveBtn.setBounds(450, 650, 240, 40);
		saveBtn.setFont(f2);
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setBackground(Color.BLACK);
		p1.add(saveBtn);
				
		/*��� ����*/
		
		//id������ �� ���� �޾ƿ���
		nameTf.setText(mgr.userInfo(userId).getName());
		birthdayTf.setText(mgr.userInfo(userId).getBirthday());
		genderTf.setText(mgr.userInfo(userId).getGender());
		emailTf.setText(mgr.userInfo(userId).getEmail());
		phoneTf.setText(mgr.userInfo(userId).getPhone());
		
		// ���� �� �޾ƿ�?
		if (!nameTf.getText().equals("") && !birthdayTf.getText().equals("") && !genderTf.getText().equals("")) {
			System.out.println("[updateUser]: ȸ������(" + userId + ") �ҷ����� ����");
		}else {
			System.out.println("[updateUser]: ȸ������(" + userId + ") �ҷ����� ����");
		}
		
		
		//������ ���� ����
		saveBtn.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				//��й�ȣ, �̸���, ����ó �� ĭ���� �� ���
				if (pwdTf.getText().equals("")) { // ��й�ȣ �Ⱦ�
					JOptionPane.showMessageDialog(null, "[����������] ��й�ȣ�� �Է��ϼ���.");
				} else if (emailTf.getText().equals("")) { // �̸��� �Ⱦ�
					JOptionPane.showMessageDialog(null, "[����������] �̸����� �Է��ϼ���.");
				} else if (phoneTf.getText().equals("")) { // ����ó �Ⱦ�
					JOptionPane.showMessageDialog(null, "[����������] ����ó�� �Է��ϼ���");
				} else if (!newPwdTf.getText().equals("")) { // �� ��й�ȣ ��
					if (pwdTf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "[����������] ���� ��й�ȣ�� �Է��ϼ���.");
					} else if (mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim()).length()>0/* true */) { // ��й�ȣ�� ���̵� üũ
						UserBean bean = new UserBean();
						bean.setId(idTf.getText());
						bean.setPwd(newPwdTf.getText());
						bean.setEmail(emailTf.getText());
						bean.setPhone(phoneTf.getText());
						mgr.userUpdt(bean);
						System.out.println("�� ��й�ȣ:"+newPwdTf.getText());
						pwdTf.setText("");
						newPwdTf.setText("");
						JOptionPane.showMessageDialog(null, "[����������] ��й�ȣ�� �缳���Ǿ����ϴ�.");
						System.out.println("[UpdateUser] ȸ��(" + userId + ")��й�ȣ �缳�� �Ϸ�");
					} else { // ��й�ȣ ���̵� ����ġ
						JOptionPane.showMessageDialog(null, "[����������] ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					}
				} else if (newPwdTf.getText().equals("")) { // �� ��й�ȣ ���� �ٸ� ������ ��������
					if(mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim()).length()>0) {
						UserBean bean = new UserBean();
						bean.setId(idTf.getText());
						bean.setPwd(pwdTf.getText()); //���� ��й�ȣ �״�� �����
						bean.setEmail(emailTf.getText());
						bean.setPhone(phoneTf.getText());
						mgr.userUpdt(bean);
						JOptionPane.showMessageDialog(null, "[����������] ȸ�������� �����Ǿ����ϴ�.");	
						System.out.println("[UpdateUser] ȸ������ ("+userId+") �缳�� �Ϸ�");
					}else {
						JOptionPane.showMessageDialog(null, "[����������] ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					}
				}
		// ���ΰ�ħ
		jf.validate();
	}
});
		// ������ȸ��ư �׼�: �� ���೻�� ��ȸ
		listBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReservationUser(userId);
				jf.dispose();
			}
		});
		
		// ȸ��Ż���ư �׼�: ȸ��Ż��â���� �̵�
		delUserBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteUser(userId);
				jf.dispose();
			}
		});
		
		
		//Ȩ��ư�׼�: ��������
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();
			}
		});
		
}

	// ������
	public UpdateUser() {
		this(null);
	}

}
		
	
